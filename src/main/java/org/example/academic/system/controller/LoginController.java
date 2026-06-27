package org.example.academic.system.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.example.academic.system.context.ApplicationContext;
import org.example.academic.system.exception.AuthenticationException;
import org.example.academic.system.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private AuthenticationController authController;
    private AcademicSystemController academicController;

    @FXML
    public void initialize() {
        logger.info("Inicializando LoginController...");
        
        ApplicationContext context = ApplicationContext.getInstance();
        this.authController = context.getAuthController();
        this.academicController = context.getAcademicController();
        
        logger.info("LoginController inicializado com sucesso!");
        logger.debug("AcademicController: {}", academicController);
        logger.debug("AuthenticationController: {}", authController);
        
        if (academicController != null) {
            logger.info("Total de turmas disponíveis: {}", academicController.getClasses().size());
        }
    }

    @FXML
    public void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        logger.info("Tentativa de login para usuário: {}", username);

        if (username.isEmpty() || password.isEmpty()) {
            logger.warn("Tentativa de login com campos vazios");
            showError("Preencha todos os campos!");
            return;
        }

        try {
            boolean autenticado = authController.login(username, password);

            if (autenticado) {
                User loggedUser = authController.getLoggedUser();
                logger.info("Login bem-sucedido para usuário: {} (Role: {})", 
                           loggedUser.getUsername(), loggedUser.getRole());
                logger.info("Total de turmas disponíveis: {}", academicController.getClasses().size());
                
                carregarDashboard();
            }

        } catch (AuthenticationException e) {
            logger.warn("Falha de autenticação para usuário {}: {}", username, e.getMessage());
            showError(e.getMessage());
        } catch (Exception e) {
            logger.error("Erro inesperado durante login do usuário {}: {}", username, e.getMessage(), e);
            showError("Erro inesperado: " + e.getMessage());
        }
    }

    private void carregarDashboard() {
        try {
            User loggedUser = authController.getLoggedUser();
            logger.info("Carregando dashboard para usuário: {}", loggedUser.getUsername());
            
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/dashboard.fxml")
            );
            Parent root = loader.load();

            DashboardController controller = loader.getController();
            controller.configurarUsuario(loggedUser);
            controller.setAcademicController(academicController);
            controller.setAuthenticationController(authController);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Academic System");
            stage.show();
            
            logger.info("Dashboard carregado com sucesso para usuário: {}", loggedUser.getUsername());

        } catch (Exception e) {
            logger.error("Erro ao carregar dashboard: {}", e.getMessage(), e);
            showError("Erro ao carregar dashboard.");
        }
    }

    private void showError(String message) {
        logger.debug("Exibindo erro: {}", message);
        
        errorLabel.setText(message);
        errorLabel.setVisible(true);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}