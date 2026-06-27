package org.example.academic.system.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.example.academic.system.context.ApplicationContext;
import org.example.academic.system.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssessmentRegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(AssessmentRegistrationController.class);

    @FXML
    private ComboBox<AcademicClass> classCombo;

    @FXML
    private ComboBox<String> typeCombo;

    @FXML
    private TextField nameField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField valueField;

    @FXML
    private Label mensagemLabel;

    private AcademicSystemController academicController;
    private AuthenticationController authController;
    private User usuarioLogado;

    @FXML
    public void initialize() {
        logger.info("Inicializando AssessmentRegistrationController...");
        
        // Carrega as turmas e tipos de avaliação
        carregarTurmas();
        carregarTiposAvaliacao();
        
        logger.info("AssessmentRegistrationController inicializado com sucesso");
    }

    public void setAcademicController(AcademicSystemController academicController) {
        if (academicController == null) {
            logger.warn("Assessment recebeu AcademicController NULL! Recuperando do ApplicationContext...");
            this.academicController = ApplicationContext.getInstance().getAcademicController();
            logger.info("Assessment recuperou AcademicController: {}", this.academicController);
        } else {
            this.academicController = academicController;
            logger.debug("Assessment recebeu AcademicController: {}", academicController);
        }
        carregarTurmas();
    }

    public void setAuthenticationController(AuthenticationController authController) {
        this.authController = authController;
        logger.debug("AuthenticationController configurado no AssessmentRegistrationController");
    }

    public void configurarUsuario(User usuario) {
        this.usuarioLogado = usuario;
        logger.info("AssessmentRegistrationController configurando usuário: {}", 
                   usuario != null ? usuario.getUsername() : "null");
    }

    private void carregarTurmas() {
        try {
            if (academicController == null) {
                logger.warn("AcademicController é NULL ao carregar turmas");
                return;
            }

            classCombo.getItems().clear();
            var classes = academicController.getClasses();
            classCombo.getItems().addAll(classes);
            
            logger.info("Carregadas {} turmas para seleção", classes.size());
            
            if (classes.isEmpty()) {
                logger.warn("Nenhuma turma disponível para cadastro de avaliação");
                mensagemLabel.setText("Nenhuma turma cadastrada. Cadastre uma turma primeiro.");
                mensagemLabel.setStyle("-fx-text-fill: orange;");
            }

        } catch (Exception e) {
            logger.error("Erro ao carregar turmas: {}", e.getMessage(), e);
        }
    }

    private void carregarTiposAvaliacao() {
        typeCombo.getItems().clear();
        typeCombo.getItems().addAll(
            "PROVA",
            "TRABALHO",
            "SEMINARIO",
            "ASSIGNMENT"
        );
        logger.debug("Tipos de avaliação carregados: {}", typeCombo.getItems());
    }

    @FXML
    private void cadastrarAvaliacao() {
        try {
            logger.info("Usuário {} tentando cadastrar avaliação", 
                       usuarioLogado != null ? usuarioLogado.getUsername() : "desconhecido");

            // Validação dos campos
            if (classCombo.getValue() == null) {
                mensagemLabel.setText("Selecione uma turma!");
                mensagemLabel.setStyle("-fx-text-fill: red;");
                logger.warn("Tentativa de cadastro sem selecionar turma");
                return;
            }

            if (typeCombo.getValue() == null) {
                mensagemLabel.setText("Selecione o tipo de avaliação!");
                mensagemLabel.setStyle("-fx-text-fill: red;");
                logger.warn("Tentativa de cadastro sem selecionar tipo");
                return;
            }

            AcademicClass turma = classCombo.getValue();
            String tipo = typeCombo.getValue();
            String nome = nameField.getText().trim();
            
            if (nome.isEmpty()) {
                mensagemLabel.setText("Informe o nome da avaliação!");
                mensagemLabel.setStyle("-fx-text-fill: red;");
                logger.warn("Tentativa de cadastro com nome vazio");
                return;
            }

            double peso = Double.parseDouble(weightField.getText().trim());
            double valor = Double.parseDouble(valueField.getText().trim());

            if (peso <= 0 || valor <= 0) {
                mensagemLabel.setText("Peso e valor devem ser maiores que zero!");
                mensagemLabel.setStyle("-fx-text-fill: red;");
                logger.warn("Tentativa de cadastro com peso/valor inválido: peso={}, valor={}", peso, valor);
                return;
            }

            Assessment assessment;
            switch (tipo) {
                case "PROVA":
                    assessment = new Exam(nome, peso, valor);
                    break;
                case "TRABALHO":
                    assessment = new PracticalAssignment(nome, peso, valor, "Java");
                    break;
                case "SEMINARIO":
                    assessment = new Seminar(nome, peso, valor, "Tema");
                    break;
                default:
                    assessment = new Assignment(nome, peso, valor);
            }

            academicController.registerAssessment(turma, assessment);

            mensagemLabel.setText("Avaliação cadastrada com sucesso!");
            mensagemLabel.setStyle("-fx-text-fill: green;");
            
            logger.info("Avaliação '{}' cadastrada na turma {} com sucesso", 
                       nome, turma.getCode());

            // Limpa os campos
            nameField.clear();
            weightField.clear();
            valueField.clear();
            classCombo.getSelectionModel().clearSelection();
            typeCombo.getSelectionModel().selectFirst();

        } catch (NumberFormatException e) {
            logger.warn("Erro de formato numérico: {}", e.getMessage());
            mensagemLabel.setText("Peso e valor devem ser números válidos!");
            mensagemLabel.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            logger.error("Erro ao cadastrar avaliação: {}", e.getMessage(), e);
            mensagemLabel.setText("Erro ao cadastrar: " + e.getMessage());
            mensagemLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void voltarDashboard() {
        try {
            logger.info("Usuário {} voltando ao dashboard", 
                       usuarioLogado != null ? usuarioLogado.getUsername() : "desconhecido");

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/dashboard.fxml")
            );
            Parent root = loader.load();

            DashboardController controller = loader.getController();
            controller.configurarUsuario(usuarioLogado);
            controller.setAcademicController(academicController);
            controller.setAuthenticationController(authController);

            Stage stage = (Stage) classCombo.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Principal");
            
            logger.info("Dashboard carregado com sucesso");

        } catch (Exception e) {
            logger.error("Erro ao voltar para dashboard: {}", e.getMessage(), e);
        }
    }
}