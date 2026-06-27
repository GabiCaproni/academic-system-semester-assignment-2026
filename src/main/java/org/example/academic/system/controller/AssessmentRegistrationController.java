package org.example.academic.system.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.example.academic.system.context.ApplicationContext;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.model.Exam;
import org.example.academic.system.model.PracticalAssignment;
import org.example.academic.system.model.Seminar;
import org.example.academic.system.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssessmentRegistrationController {

private static final Logger logger =
        LoggerFactory.getLogger(
                AssessmentRegistrationController.class);

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
private Label campoExtraLabel;

@FXML
private TextField campoExtraField;

@FXML
private Label mensagemLabel;

private AcademicSystemController academicController;

private AuthenticationController authController;

private User usuarioLogado;

@FXML
public void initialize() {

    carregarTurmas();
    carregarTiposAvaliacao();

    typeCombo.setOnAction(
            event -> atualizarFormulario());
}

public void setAcademicController(
        AcademicSystemController academicController) {

    if (academicController == null) {

        this.academicController =
                ApplicationContext
                        .getInstance()
                        .getAcademicController();

    } else {

        this.academicController =
                academicController;
    }

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

    try {

        if (academicController == null) {
            return;
        }

        classCombo.getItems().clear();

        classCombo.getItems().addAll(
                academicController.getClasses());

    } catch (Exception e) {

        logger.error(
                "Erro ao carregar turmas",
                e);
    }
}

private void carregarTiposAvaliacao() {

    typeCombo.getItems().clear();

    typeCombo.getItems().addAll(
            "PROVA",
            "SEMINARIO",
            "TRABALHO PRATICO"
    );
}

private void atualizarFormulario() {

    String tipo = typeCombo.getValue();

    campoExtraField.clear();

    if (tipo == null) {

        campoExtraLabel.setVisible(false);

        campoExtraField.setVisible(false);
        campoExtraField.setManaged(false);

        return;
    }

    switch (tipo) {

        case "SEMINARIO" -> {

            campoExtraLabel.setText("Tema");

            campoExtraLabel.setVisible(true);

            campoExtraField.setPromptText(
                    "Informe o tema do seminário");

            campoExtraField.setVisible(true);
            campoExtraField.setManaged(true);
        }

        case "TRABALHO PRATICO" -> {

            campoExtraLabel.setText("Tecnologia");

            campoExtraLabel.setVisible(true);

            campoExtraField.setPromptText(
                    "Ex: Java, Python, React");

            campoExtraField.setVisible(true);
            campoExtraField.setManaged(true);
        }

        default -> {

            campoExtraLabel.setVisible(false);

            campoExtraField.setVisible(false);
            campoExtraField.setManaged(false);
        }
    }
}

@FXML
private void cadastrarAvaliacao() {

    try {

        if (classCombo.getValue() == null) {

            mensagemLabel.setText(
                    "Selecione uma turma!");

            return;
        }

        if (typeCombo.getValue() == null) {

            mensagemLabel.setText(
                    "Selecione um tipo!");

            return;
        }

        AcademicClass turma =
                classCombo.getValue();

        String tipo =
                typeCombo.getValue();

        String nome =
                nameField.getText().trim();

        double peso =
                Double.parseDouble(
                        weightField.getText());

        double valor =
                Double.parseDouble(
                        valueField.getText());

        Assessment assessment;

        switch (tipo) {

            case "PROVA" -> {

                assessment =
                        new Exam(
                                nome,
                                peso,
                                valor);
            }

            case "SEMINARIO" -> {

                String tema =
                        campoExtraField
                                .getText()
                                .trim();

                if (tema.isEmpty()) {

                    mensagemLabel.setText(
                            "Informe o tema!");

                    return;
                }

                assessment =
                        new Seminar(
                                nome,
                                peso,
                                valor,
                                tema);
            }

            case "TRABALHO PRATICO" -> {

                String tecnologia =
                        campoExtraField
                                .getText()
                                .trim();

                if (tecnologia.isEmpty()) {

                    mensagemLabel.setText(
                            "Informe a tecnologia!");

                    return;
                }

                assessment =
                        new PracticalAssignment(
                                nome,
                                peso,
                                valor,
                                tecnologia);
            }

            default ->
                    throw new IllegalArgumentException(
                            "Tipo inválido");
        }

        academicController.registerAssessment(
                turma,
                assessment);

        mensagemLabel.setText(
                "Avaliação cadastrada com sucesso!");

        limparCampos();

    } catch (NumberFormatException e) {

        mensagemLabel.setText(
                "Peso e valor devem ser numéricos!");

    } catch (Exception e) {

        mensagemLabel.setText(
                "Erro: " + e.getMessage());

        logger.error(
                "Erro ao cadastrar avaliação",
                e);
    }
}

private void limparCampos() {

    nameField.clear();
    weightField.clear();
    valueField.clear();

    campoExtraField.clear();

    classCombo.getSelectionModel()
            .clearSelection();

    typeCombo.getSelectionModel()
            .clearSelection();

    campoExtraField.setVisible(false);
    campoExtraField.setManaged(false);

    campoExtraLabel.setVisible(false);
}

@FXML
private void voltarDashboard() {

    try {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass()
                                .getResource(
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
                (Stage) classCombo
                        .getScene()
                        .getWindow();

        stage.setScene(
                new Scene(root));

        stage.setTitle(
                "Menu Principal");

    } catch (Exception e) {

        logger.error(
                "Erro ao voltar",
                e);
    }
}

}
