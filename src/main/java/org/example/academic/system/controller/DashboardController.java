package org.example.academic.system.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import org.example.academic.system.context.ApplicationContext;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;

import java.util.Optional;

public class DashboardController {

    @FXML
    private Button btnCadastrarTurma;

    @FXML
    private Button btnRelatorios;

    private User usuarioLogado;
    private AcademicSystemController academicController;
    private AuthenticationController authController;

    public void configurarUsuario(User user) {
        this.usuarioLogado = user;
        System.out.println("Dashboard configurando usuário: " + (user != null ? user.getUsername() : "null"));

        if (user != null && user.getRole() != Role.ADMIN) {
            btnCadastrarTurma.setVisible(false);
            btnCadastrarTurma.setManaged(false);
        }
    }

    public void setAcademicController(AcademicSystemController academicController) {
        if (academicController == null) {
            System.out.println("Dashboard recebeu AcademicController NULL! Recuperando do ApplicationContext...");
            this.academicController = ApplicationContext.getInstance().getAcademicController();
            System.out.println("Dashboard recuperou: " + this.academicController);
        } else {
            this.academicController = academicController;
            System.out.println("Dashboard recebeu AcademicController: " + academicController);
        }
    }

    public void setAuthenticationController(AuthenticationController authController) {
        this.authController = authController;
    }

    @FXML
    private void abrirCadastroTurma() {
        try {
            if (academicController == null) {
                academicController = ApplicationContext.getInstance().getAcademicController();
            }

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/class-registration.fxml")
            );
            Parent root = loader.load();

            ClassRegistrationController controller = loader.getController();
            controller.setAcademicController(academicController);
            controller.configurarUsuario(usuarioLogado);

            Stage stage = (Stage) btnCadastrarTurma.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Cadastro de Turma");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirVisualizacao() {
        try {
            if (academicController == null) {
                academicController = ApplicationContext.getInstance().getAcademicController();
            }

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/viewclasses.fxml")
            );
            Parent root = loader.load();

            ViewClassesController controller = loader.getController();
            controller.setAcademicController(academicController);
            controller.configurarUsuario(usuarioLogado);

            Stage stage = (Stage) btnRelatorios.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirRelatorios() {
        try {
            System.out.println("abrirRelatorios() chamado!");
            System.out.println("academicController antes da verificação: " + academicController);

            if (academicController == null) {
                System.out.println("AcademicController é NULL! Recuperando do ApplicationContext...");
                academicController = ApplicationContext.getInstance().getAcademicController();
                System.out.println("AcademicController recuperado: " + academicController);
            }

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/report.fxml")
            );
            Parent root = loader.load();

            ReportController controller = loader.getController();
            controller.setAcademicController(academicController);
            controller.configurarUsuario(usuarioLogado);

            System.out.println("ReportController configurado com academicController: " + academicController);
            System.out.println("ReportController configurado com usuário: " + (usuarioLogado != null ? usuarioLogado.getUsername() : "null"));

            Stage stage = (Stage) btnRelatorios.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Relatórios");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarErro("Erro ao abrir tela de relatórios: " + e.getMessage());
        }
    }

    @FXML
    private void abrirCadastroAvaliacao() {
        try {
            if (academicController == null) {
                academicController = ApplicationContext.getInstance().getAcademicController();
            }

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/assessment-registration.fxml")
            );
            Parent root = loader.load();

            AssessmentRegistrationController controller = loader.getController();
            controller.setAcademicController(academicController);
            controller.setAuthenticationController(authController);
            controller.configurarUsuario(usuarioLogado);

            Stage stage = (Stage) btnRelatorios.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Cadastro de Avaliação");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {
        try {
            if (authController != null) {
                authController.logout();
            }

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/login.fxml")
            );
            Parent root = loader.load();

            Stage stage = (Stage) btnRelatorios.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sairDoSistema() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair do Sistema");
        alert.setHeaderText("Deseja realmente sair do sistema?");
        alert.setContentText("Todas as alterações não salvas serão perdidas.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Faz logout antes de sair
            if (authController != null) {
                authController.logout();
            }

            System.out.println("[LOG] Sistema encerrado por "
                    + (usuarioLogado != null ? usuarioLogado.getUsername() : "desconhecido"));

            // Fecha completamente a aplicação
            Platform.exit();
            System.exit(0);
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
