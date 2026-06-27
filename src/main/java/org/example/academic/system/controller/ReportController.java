package org.example.academic.system.controller;

import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import org.example.academic.system.context.ApplicationContext;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @FXML
    private Button btnPersistencia;

    @FXML
    private TextArea resultadoArea;

    private AcademicSystemController academicController;
    private User usuarioLogado;

    public void setAcademicController(AcademicSystemController academicController) {
        if (academicController == null) {
            logger.warn("ReportController recebeu AcademicController NULL! Recuperando do ApplicationContext...");
            this.academicController = ApplicationContext.getInstance().getAcademicController();
            logger.info("ReportController recuperou AcademicController: {}", this.academicController);
        } else {
            this.academicController = academicController;
            logger.debug("ReportController recebeu AcademicController: {}", academicController);
        }

        if (this.academicController != null) {
            logger.info("Total de turmas disponíveis no ReportController: {}", 
                       this.academicController.getClasses().size());
        }
    }

    public void configurarUsuario(User user) {
        this.usuarioLogado = user;
        logger.info("ReportController configurando usuário: {}", user != null ? user.getUsername() : "null");

        if (user != null) {
            if (user.getRole() == Role.ADMIN) {
                btnPersistencia.setVisible(true);
                btnPersistencia.setManaged(true);
                logger.debug("Botão de persistência visível para ADMIN: {}", user.getUsername());
            } else {
                btnPersistencia.setVisible(false);
                btnPersistencia.setManaged(false);
                logger.debug("Botão de persistência oculto para usuário: {}", user.getUsername());
            }
        }
    }

    @FXML
    private void gerarResumo() {
        try {
            logger.info("Gerando relatório resumido para usuário: {}", 
                       usuarioLogado != null ? usuarioLogado.getUsername() : "desconhecido");

            if (academicController == null) {
                academicController = ApplicationContext.getInstance().getAcademicController();
            }

            if (academicController == null) {
                logger.error("AcademicController não foi inicializado!");
                resultadoArea.setText("Erro: AcademicController não foi inicializado!");
                mostrarErro("AcademicController não foi inicializado!");
                return;
            }

            String relatorio = academicController.generateSummary();
            logger.info("Relatório resumido gerado com sucesso. Tamanho: {} caracteres", relatorio.length());

            resultadoArea.setText(relatorio);

        } catch (Exception e) {
            logger.error("Erro ao gerar relatório resumido: {}", e.getMessage(), e);
            resultadoArea.setText("Erro ao gerar relatório: " + e.getMessage());
            mostrarErro("Erro ao gerar relatório resumido: " + e.getMessage());
        }
    }

    @FXML
    private void gerarPeso() {
        try {
            logger.info("Gerando relatório de pesos para usuário: {}", 
                       usuarioLogado != null ? usuarioLogado.getUsername() : "desconhecido");

            if (academicController == null) {
                academicController = ApplicationContext.getInstance().getAcademicController();
            }

            if (academicController == null) {
                logger.error("AcademicController não foi inicializado!");
                resultadoArea.setText("Erro: AcademicController não foi inicializado!");
                mostrarErro("AcademicController não foi inicializado!");
                return;
            }

            String relatorio = academicController.generateWeightReport();
            logger.info("Relatório de pesos gerado com sucesso. Tamanho: {} caracteres", relatorio.length());

            resultadoArea.setText(relatorio);

        } catch (Exception e) {
            logger.error("Erro ao gerar relatório de pesos: {}", e.getMessage(), e);
            resultadoArea.setText("Erro ao gerar relatório: " + e.getMessage());
            mostrarErro("Erro ao gerar relatório de peso: " + e.getMessage());
        }
    }

    @FXML
    private void gerarPersistencia() {
        try {
            logger.info("Gerando relatório de persistência para usuário: {}", 
                       usuarioLogado != null ? usuarioLogado.getUsername() : "desconhecido");

            if (academicController == null) {
                academicController = ApplicationContext.getInstance().getAcademicController();
            }

            if (academicController == null) {
                logger.error("AcademicController não foi inicializado!");
                resultadoArea.setText("Erro: AcademicController não foi inicializado!");
                mostrarErro("AcademicController não foi inicializado!");
                return;
            }

            if (usuarioLogado == null || usuarioLogado.getRole() != Role.ADMIN) {
                logger.warn("Tentativa de acesso ao relatório de persistência por usuário não autorizado: {}", 
                           usuarioLogado != null ? usuarioLogado.getUsername() : "null");
                resultadoArea.setText("Acesso negado! Apenas administradores podem acessar este relatório.");
                mostrarErro("Acesso negado! Apenas administradores podem acessar este relatório.");
                return;
            }

            String relatorio = academicController.generatePersistenceConfigurationReport();
            logger.info("Relatório de persistência gerado com sucesso. Tamanho: {} caracteres", relatorio.length());

            resultadoArea.setText(relatorio);

        } catch (Exception e) {
            logger.error("Erro ao gerar relatório de persistência: {}", e.getMessage(), e);
            resultadoArea.setText("Erro ao gerar relatório: " + e.getMessage());
            mostrarErro("Erro ao gerar relatório de persistência: " + e.getMessage());
        }
    }

    @FXML
    private void voltar() {
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
            controller.setAuthenticationController(null);

            Stage stage = (Stage) resultadoArea.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Principal");
            stage.show();

            logger.info("Voltou ao dashboard com sucesso");

        } catch (Exception e) {
            logger.error("Erro ao voltar: {}", e.getMessage(), e);
            mostrarErro("Erro ao voltar: " + e.getMessage());
        }
    }

    private void mostrarErro(String mensagem) {
        logger.error("Erro no ReportController: {}", mensagem);
        
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}