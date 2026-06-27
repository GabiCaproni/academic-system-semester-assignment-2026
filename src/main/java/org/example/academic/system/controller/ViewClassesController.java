package org.example.academic.system.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.model.User;

public class ViewClassesController {

    @FXML
    private TextArea resultadoArea;

    private AcademicSystemController academicController;
    private User usuarioLogado;

    public void setAcademicController(
            AcademicSystemController academicController) {

        this.academicController = academicController;
    }

    public void configurarUsuario(User user) {
        this.usuarioLogado = user;
    }

    @FXML
    private void carregarTurmas() {

        StringBuilder texto = new StringBuilder();

        for (AcademicClass turma :
                academicController.getClasses()) {

            texto.append("Turma: ")
                    .append(turma.getCode())
                    .append(" - ")
                    .append(turma.getName())
                    .append("\n\n");

            for (Assessment avaliacao :
                    turma.getAssessments()) {

                texto.append("Tipo: ")
                        .append(avaliacao.getClass()
                                .getSimpleName())
                        .append("\n");

                texto.append("Nome: ")
                        .append(avaliacao.getName())
                        .append("\n");

                texto.append("Peso: ")
                        .append(avaliacao.getWeight())
                        .append("\n");

                texto.append("Valor: ")
                        .append(avaliacao.getValue())
                        .append("\n\n");
            }

            texto.append("---------------------------------\n");
        }

        resultadoArea.setText(texto.toString());
    }

    @FXML
    private void voltarDashboard() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/fxml/dashboard.fxml"));

            Parent root = loader.load();

            DashboardController controller =
                    loader.getController();

            controller.configurarUsuario(
                    usuarioLogado);

            Stage stage =
                    (Stage) resultadoArea
                            .getScene()
                            .getWindow();

            stage.setScene(
                    new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}