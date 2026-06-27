package org.example.academic.system.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.nio.file.Files;
import java.nio.file.Path;

public class PersistenceReportController {

    @FXML
    private TextArea conteudoArea;

    @FXML
    private void carregarJson() {

        try {

            String conteudo =
                    Files.readString(
                            Path.of("academic_data.json"));

            conteudoArea.setText(conteudo);

        } catch (Exception e) {

            conteudoArea.setText(
                    "Erro ao carregar JSON:\n"
                            + e.getMessage());
        }
    }

    @FXML
    private void carregarXml() {

        try {

            String conteudo =
                    Files.readString(
                            Path.of("academic_data.xml"));

            conteudoArea.setText(conteudo);

        } catch (Exception e) {

            conteudoArea.setText(
                    "Erro ao carregar XML:\n"
                            + e.getMessage());
        }
    }

    @FXML
    private void voltar() {

        // código para retornar ao dashboard
    }
}