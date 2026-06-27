package org.example.academic.system.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersistenceConfigController {

    private static final Logger logger = LoggerFactory.getLogger(PersistenceConfigController.class);

    @FXML
    private TextArea infoArea;

    @FXML
    private Label statusLabel;

    private User usuarioLogado;
    private AcademicSystemController academicController;

    public void setAcademicController(AcademicSystemController academicController) {
        this.academicController = academicController;
        logger.debug("PersistenceConfigController recebeu AcademicController: {}", academicController);
    }

    public void configurarUsuario(User user) {
        this.usuarioLogado = user;
        logger.info("PersistenceConfigController configurando usuário: {}", 
                   user != null ? user.getUsername() : "null");
        
        if (user == null || user.getRole() != Role.ADMIN) {
            logger.warn("Usuário não autorizado tentou acessar configuração de persistência: {}", 
                       user != null ? user.getUsername() : "null");
            statusLabel.setText("Acesso negado! Apenas administradores.");
            statusLabel.setStyle("-fx-text-fill: red;");
        } else {
            carregarInformacoes();
        }
    }

    @FXML
    private void initialize() {
        logger.info("Inicializando PersistenceConfigController...");
    }

    @FXML
    private void handleRefresh() {
        logger.info("Usuário {} atualizando informações de persistência", 
                   usuarioLogado != null ? usuarioLogado.getUsername() : "desconhecido");
        carregarInformacoes();
        statusLabel.setText("✅ Informações atualizadas!");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    private void handleReload() {
        try {
            logger.info("Usuário {} recarregando dados", 
                       usuarioLogado != null ? usuarioLogado.getUsername() : "desconhecido");
            
            if (academicController != null) {
                academicController.saveClasses();
                statusLabel.setText("✅ Dados recarregados com sucesso!");
                statusLabel.setStyle("-fx-text-fill: green;");
                carregarInformacoes();
                logger.info("Dados recarregados com sucesso");
            } else {
                statusLabel.setText("❌ AcademicController não disponível!");
                statusLabel.setStyle("-fx-text-fill: red;");
                logger.error("AcademicController não disponível para recarregar dados");
            }
        } catch (Exception e) {
            logger.error("Erro ao recarregar dados: {}", e.getMessage(), e);
            statusLabel.setText("❌ Erro ao recarregar: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleVoltar() {
        try {
            logger.info("Usuário {} voltando ao dashboard", 
                       usuarioLogado != null ? usuarioLogado.getUsername() : "desconhecido");

            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/dashboard.fxml")
            );
            Parent root = loader.load();

            DashboardController controller = loader.getController();
            controller.configurarUsuario(usuarioLogado);
            controller.setAcademicController(academicController);

            Stage stage = (Stage) statusLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Principal");
            
            logger.info("Dashboard carregado com sucesso");

        } catch (Exception e) {
            logger.error("Erro ao voltar: {}", e.getMessage(), e);
            mostrarErro("Erro ao voltar: " + e.getMessage());
        }
    }

    private void carregarInformacoes() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("=== CONFIGURAÇÃO DE PERSISTÊNCIA ===\n\n");

            File userFile = new File("users.txt");
            sb.append("📁 ARQUIVO DE USUÁRIOS:\n");
            sb.append("   Local: ").append(userFile.getAbsolutePath()).append("\n");
            sb.append("   Existe: ").append(userFile.exists() ? "✅ SIM" : "❌ NÃO").append("\n");
            if (userFile.exists()) {
                sb.append("   Tamanho: ").append(userFile.length()).append(" bytes\n");
                sb.append("   Última modificação: ").append(
                    new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                        .format(new Date(userFile.lastModified()))
                ).append("\n");
            }
            sb.append("\n");

            sb.append("💾 INFORMAÇÕES DO SISTEMA:\n");
            sb.append("   Total de turmas: ").append(
                academicController != null ? academicController.getClasses().size() : 0
            ).append("\n");
            sb.append("   Usuário atual: ").append(
                usuarioLogado != null ? usuarioLogado.getUsername() : "N/A"
            ).append("\n");
            sb.append("   Role: ").append(
                usuarioLogado != null ? usuarioLogado.getRole() : "N/A"
            ).append("\n");

            infoArea.setText(sb.toString());
            logger.debug("Informações de persistência carregadas com sucesso");

        } catch (Exception e) {
            logger.error("Erro ao carregar informações de persistência: {}", e.getMessage(), e);
            infoArea.setText("Erro ao carregar informações: " + e.getMessage());
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

