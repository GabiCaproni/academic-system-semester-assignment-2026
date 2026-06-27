package org.example.academic.system.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;

public class ClassRegistrationController {

    @FXML
    private TextField codeField;

    @FXML
    private TextField nameField;

    @FXML
    private Label mensagemLabel;

    private AcademicSystemController academicController;

    private AuthenticationController authController;

    private User usuarioLogado;

    public void setAcademicController(
            AcademicSystemController academicController) {

        this.academicController = academicController;
    }

    public void setAuthenticationController(
            AuthenticationController authController) {

        this.authController = authController;
    }

    public void configurarUsuario(
            User usuario) {

        this.usuarioLogado = usuario;
    }

    @FXML
    private void cadastrarTurma() {

        try {

            if (usuarioLogado.getRole() != Role.ADMIN) {

                mensagemLabel.setText(
                        "Acesso negado.");

                return;
            }

            String codigo =
                    codeField.getText().trim();

            String nome =
                    nameField.getText().trim();

            AcademicClass turma =
                    new AcademicClass(
                            codigo,
                            nome);

            academicController.registerClass(
                    turma);

            mensagemLabel.setText(
                    "Turma cadastrada com sucesso!");

            codeField.clear();
            nameField.clear();

        } catch (Exception e) {

            mensagemLabel.setText(
                    e.getMessage());
        }
    }

    @FXML
    private void voltarDashboard() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/fxml/dashboard.fxml"));

            Parent root =
                    loader.load();

            DashboardController controller =
                    loader.getController();

            controller.configurarUsuario(
                    usuarioLogado);

            controller.setAcademicController(
                    academicController);

            controller.setAuthenticationController(
                    authController);

            Stage stage =
                    (Stage) codeField
                            .getScene()
                            .getWindow();

            stage.setScene(
                    new Scene(root));

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}