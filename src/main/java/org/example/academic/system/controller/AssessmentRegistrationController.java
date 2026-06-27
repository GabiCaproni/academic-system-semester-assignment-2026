package org.example.academic.system.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.example.academic.system.model.*;

public class AssessmentRegistrationController {

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

    public void setAcademicController(
        AcademicSystemController academicController) {

    System.out.println(
            "Assessment recebeu controller: "
            + academicController);

    this.academicController = academicController;

    carregarTurmas();
}

    public void setAuthenticationController(
            AuthenticationController authController) {

        this.authController = authController;
    }

    public void configurarUsuario(
            User usuario) {

        this.usuarioLogado = usuario;
    }

    private void carregarTurmas() {

        if (academicController == null) {
            return;
        }

        classCombo.getItems().clear();
        typeCombo.getItems().clear();

        classCombo.getItems().addAll(
                academicController.getClasses());

        typeCombo.getItems().addAll(
                "PROVA",
                "TRABALHO",
                "SEMINARIO",
                "ASSIGNMENT");
    }

    @FXML
    private void cadastrarAvaliacao() {

        try {

            AcademicClass turma
                    = classCombo.getValue();

            String tipo
                    = typeCombo.getValue();

            String nome
                    = nameField.getText();

            double peso
                    = Double.parseDouble(
                            weightField.getText());

            double valor
                    = Double.parseDouble(
                            valueField.getText());

            Assessment assessment;

            switch (tipo) {

                case "PROVA":

                    assessment
                            = new Exam(
                                    nome,
                                    peso,
                                    valor);
                    break;

                case "TRABALHO":

                    assessment
                            = new PracticalAssignment(
                                    nome,
                                    peso,
                                    valor,
                                    "Java");
                    break;

                case "SEMINARIO":

                    assessment
                            = new Seminar(
                                    nome,
                                    peso,
                                    valor,
                                    "Tema");
                    break;

                default:

                    assessment
                            = new Assignment(
                                    nome,
                                    peso,
                                    valor);
            }

            academicController.registerAssessment(
                    turma,
                    assessment);

            mensagemLabel.setText(
                    "Avaliação cadastrada com sucesso!");

        } catch (Exception e) {

            mensagemLabel.setText(
                    e.getMessage());
        }
    }

    @FXML
    private void voltarDashboard() {

        try {

            FXMLLoader loader
                    = new FXMLLoader(
                            getClass().getResource(
                                    "/fxml/dashboard.fxml"));

            Parent root
                    = loader.load();

            DashboardController controller
                    = loader.getController();

            controller.configurarUsuario(
                    usuarioLogado);

            controller.setAcademicController(
                    academicController);

            controller.setAuthenticationController(
                    authController);

            Stage stage
                    = (Stage) classCombo
                            .getScene()
                            .getWindow();

            stage.setScene(
                    new Scene(root));

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
