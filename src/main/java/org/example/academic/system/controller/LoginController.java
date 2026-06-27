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

public class LoginController {

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
        // Usa o ApplicationContext para obter as instâncias únicas
        ApplicationContext context = ApplicationContext.getInstance();
        this.authController = context.getAuthController();
        this.academicController = context.getAcademicController();
        
        System.out.println("LoginController inicializado!");
        System.out.println("AcademicController: " + academicController);
        System.out.println("AuthenticationController: " + authController);
    }

    @FXML
    public void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Preencha todos os campos!");
            return;
        }

        try {
            boolean autenticado = authController.login(username, password);

            if (autenticado) {
                User loggedUser = authController.getLoggedUser();
                System.out.println("Usuário logado: " + loggedUser.getUsername());
                System.out.println("Role: " + loggedUser.getRole());
                System.out.println("Total de turmas: " + academicController.getClasses().size());
                
                carregarDashboard();
            }

        } catch (AuthenticationException e) {
            showError(e.getMessage());
        } catch (Exception e) {
            showError("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void carregarDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/dashboard.fxml")
            );
            Parent root = loader.load();

            DashboardController controller = loader.getController();
            controller.configurarUsuario(authController.getLoggedUser());
            controller.setAcademicController(academicController);
            controller.setAuthenticationController(authController);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Academic System");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showError("Erro ao carregar dashboard.");
        }
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}