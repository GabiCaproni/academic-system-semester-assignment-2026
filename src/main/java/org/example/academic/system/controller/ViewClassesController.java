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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViewClassesController {

    private static final Logger logger = LoggerFactory.getLogger(ViewClassesController.class);

    @FXML
    private TextArea resultadoArea;

    private AcademicSystemController academicController;
    private User usuarioLogado;

    public void setAcademicController(AcademicSystemController academicController) {
        this.academicController = academicController;
        logger.debug("ViewClassesController recebeu AcademicController: {}", academicController);
    }

    public void configurarUsuario(User user) {
        this.usuarioLogado = user;
        logger.info("ViewClassesController configurando usuário: {}", user != null ? user.getUsername() : "null");
    }

    @FXML
    private void carregarTurmas() {
        try {
            logger.info("Carregando turmas para visualização. Usuário: {}", 
                       usuarioLogado != null ? usuarioLogado.getUsername() : "desconhecido");

            if (academicController == null) {
                logger.error("AcademicController não foi inicializado!");
                resultadoArea.setText("Erro: AcademicController não foi inicializado!");
                return;
            }

            var classes = academicController.getClasses();
            logger.info("Total de turmas a serem exibidas: {}", classes.size());

            StringBuilder texto = new StringBuilder();

            if (classes.isEmpty()) {
                texto.append("Nenhuma turma cadastrada no sistema.\n");
                texto.append("Cadastre uma turma para visualizar as avaliações.");
            } else {
                for (AcademicClass turma : classes) {
                    texto.append("Turma: ")
                            .append(turma.getCode())
                            .append(" - ")
                            .append(turma.getName())
                            .append("\n\n");

                    var assessments = turma.getAssessments();
                    if (assessments.isEmpty()) {
                        texto.append("  Nenhuma avaliação cadastrada.\n\n");
                    } else {
                        for (Assessment avaliacao : assessments) {
                            texto.append("  Tipo: ")
                                    .append(avaliacao.getClass().getSimpleName())
                                    .append("\n");
                            texto.append("  Nome: ")
                                    .append(avaliacao.getName())
                                    .append("\n");
                            texto.append("  Peso: ")
                                    .append(avaliacao.getWeight())
                                    .append("\n");
                            texto.append("  Valor: ")
                                    .append(avaliacao.getValue())
                                    .append("\n\n");
                        }
                    }
                    texto.append("---------------------------------\n");
                }
            }

            resultadoArea.setText(texto.toString());
            logger.info("Visualização de turmas carregada com sucesso");

        } catch (Exception e) {
            logger.error("Erro ao carregar turmas: {}", e.getMessage(), e);
            resultadoArea.setText("Erro ao carregar turmas: " + e.getMessage());
        }
    }

    @FXML
    private void voltarDashboard() {
        try {
            logger.info("Voltando ao dashboard. Usuário: {}", 
                       usuarioLogado != null ? usuarioLogado.getUsername() : "desconhecido");

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/dashboard.fxml")
            );
            Parent root = loader.load();

            DashboardController controller = loader.getController();
            controller.configurarUsuario(usuarioLogado);
            controller.setAcademicController(academicController);

            Stage stage = (Stage) resultadoArea.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Principal");
            
            logger.info("Dashboard carregado com sucesso");

        } catch (Exception e) {
            logger.error("Erro ao voltar para dashboard: {}", e.getMessage(), e);
        }
    }
}