package org.example.academic.system.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.nio.file.Files;
import java.nio.file.Path;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.academic.system.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistenceReportController {

    // Área de texto onde será exibido o conteúdo dos arquivos
    @FXML
    private TextArea conteudoArea;

    private static final Logger logger
            = LoggerFactory.getLogger(
                    PersistenceReportController.class);

    // Carrega e exibe o conteúdo do arquivo JSON
    @FXML
    private void carregarJson() {

        try {

            // Lê todo o conteúdo do arquivo JSON
            String conteudo
                    = Files.readString(
                            Path.of("academic_data.json"));

            // Exibe o conteúdo na área de texto
            conteudoArea.setText(conteudo);

        } catch (Exception e) {

            // Exibe mensagem de erro caso o arquivo não exista ou ocorra falha na leitura
            conteudoArea.setText(
                    "Erro ao carregar JSON:\n"
                    + e.getMessage());
        }
    }

    // Carrega e exibe o conteúdo do arquivo XML
    @FXML
    private void carregarXml() {

        try {

            // Lê todo o conteúdo do arquivo XML
            String conteudo
                    = Files.readString(
                            Path.of("academic_data.xml"));

            // Exibe o conteúdo na área de texto
            conteudoArea.setText(conteudo);

        } catch (Exception e) {

            // Exibe mensagem de erro caso o arquivo não exista ou ocorra falha na leitura
            conteudoArea.setText(
                    "Erro ao carregar XML:\n"
                    + e.getMessage());
        }
    }

    private AcademicSystemController academicController;
    private User usuarioLogado;

    public void setAcademicController(
            AcademicSystemController academicController) {

        this.academicController = academicController;
    }

    public void configurarUsuario(
            User usuario) {

        this.usuarioLogado = usuario;
    }

    // Retorna para a tela anterior (Dashboard)
    @FXML
    private void voltar() {

        try {

            logger.info(
                    "Usuário {} voltando ao dashboard",
                    usuarioLogado != null
                            ? usuarioLogado.getUsername()
                            : "desconhecido");

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
                    null);

            Stage stage
                    = (Stage) conteudoArea
                            .getScene()
                            .getWindow();

            stage.setScene(
                    new Scene(root));

            stage.setTitle(
                    "Menu Principal");

            stage.show();

            logger.info(
                    "Voltou ao dashboard com sucesso");

        } catch (Exception e) {

            logger.error(
                    "Erro ao voltar",
                    e);

            conteudoArea.setText(
                    "Erro ao voltar:\n"
                    + e.getMessage());
        }
    }
}
