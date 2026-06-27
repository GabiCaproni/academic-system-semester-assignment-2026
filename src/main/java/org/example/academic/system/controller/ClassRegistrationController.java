package org.example.academic.system.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.example.academic.system.context.ApplicationContext;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassRegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(ClassRegistrationController.class);

    @FXML
    private TextField codeField;

    @FXML
    private TextField nameField;

    @FXML
    private Label mensagemLabel;

    private AcademicSystemController academicController;
    private AuthenticationController authController;
    private User usuarioLogado;

    @FXML
    public void initialize() {
        logger.info("Inicializando ClassRegistrationController...");
    }

    public void setAcademicController(AcademicSystemController academicController) {
        if (academicController == null) {
            logger.warn("ClassRegistration recebeu AcademicController NULL! Recuperando do ApplicationContext...");
            this.academicController = ApplicationContext.getInstance().getAcademicController();
            logger.info("ClassRegistration recuperou AcademicController: {}", this.academicController);
        } else {
            this.academicController = academicController;
            logger.debug("ClassRegistration recebeu AcademicController: {}", academicController);
        }
    }

    public void setAuthenticationController(AuthenticationController authController) {
        this.authController = authController;
        logger.debug("AuthenticationController configurado no ClassRegistrationController");
    }

    public void configurarUsuario(User usuario) {
        this.usuarioLogado = usuario;
        logger.info("ClassRegistrationController configurando usuário: {}", 
                   usuario != null ? usuario.getUsername() : "null");
    }

    @FXML
    private void cadastrarTurma() {
        try {
            logger.info("Usuário {} tentando cadastrar turma", 
                       usuarioLogado != null ? usuarioLogado.getUsername() : "desconhecido");

            // Verificação de permissão
            if (usuarioLogado == null || usuarioLogado.getRole() != Role.ADMIN) {
                logger.warn("Usuário não autorizado tentou cadastrar turma: {}", 
                           usuarioLogado != null ? usuarioLogado.getUsername() : "null");
                mensagemLabel.setText("Acesso negado. Apenas administradores podem cadastrar turmas.");
                mensagemLabel.setStyle("-fx-text-fill: red;");
                mostrarErro("Acesso negado! Apenas administradores podem cadastrar turmas.");
                return;
            }

            // Validação dos campos
            String codigo = codeField.getText().trim();
            String nome = nameField.getText().trim();

            if (codigo.isEmpty() || nome.isEmpty()) {
                logger.warn("Tentativa de cadastro de turma com campos vazios");
                mensagemLabel.setText("Preencha todos os campos!");
                mensagemLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            // Verifica se o código já existe
            if (academicController == null) {
                logger.error("AcademicController não foi inicializado!");
                mensagemLabel.setText("Erro interno: AcademicController não disponível!");
                mensagemLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            AcademicClass existing = academicController.findClassByCode(codigo);
            if (existing != null) {
                logger.warn("Tentativa de cadastro com código duplicado: {}", codigo);
                mensagemLabel.setText("Já existe uma turma com este código: " + codigo);
                mensagemLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            // Cria e registra a turma
            AcademicClass turma = new AcademicClass(codigo, nome);
            academicController.registerClass(turma);

            mensagemLabel.setText("Turma cadastrada com sucesso!");
            mensagemLabel.setStyle("-fx-text-fill: green;");
            
            logger.info("Turma '{}' - '{}' cadastrada com sucesso por {}", 
                       codigo, nome, usuarioLogado.getUsername());

            // Limpa os campos após sucesso
            codeField.clear();
            nameField.clear();

        } catch (Exception e) {
            logger.error("Erro ao cadastrar turma para usuário {}: {}", 
                        usuarioLogado != null ? usuarioLogado.getUsername() : "desconhecido", 
                        e.getMessage(), e);
            mensagemLabel.setText("Erro ao cadastrar turma: " + e.getMessage());
            mensagemLabel.setStyle("-fx-text-fill: red;");
            mostrarErro("Erro ao cadastrar turma: " + e.getMessage());
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

            Stage stage = (Stage) codeField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Principal");
            
            logger.info("Dashboard carregado com sucesso para usuário: {}", 
                       usuarioLogado != null ? usuarioLogado.getUsername() : "desconhecido");

        } catch (Exception e) {
            logger.error("Erro ao voltar para dashboard: {}", e.getMessage(), e);
            mensagemLabel.setText("Erro ao voltar: " + e.getMessage());
            mensagemLabel.setStyle("-fx-text-fill: red;");
            mostrarErro("Erro ao voltar para o dashboard: " + e.getMessage());
        }
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}