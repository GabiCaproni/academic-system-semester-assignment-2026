package org.example.academic.system.controller;

import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import org.example.academic.system.context.ApplicationContext;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;

public class ReportController {

    @FXML
    private Button btnPersistencia;

    @FXML
    private TextArea resultadoArea;

    private AcademicSystemController academicController;
    private User usuarioLogado;

    public void setAcademicController(AcademicSystemController academicController) {
        // Se recebeu null, tenta recuperar do contexto
        if (academicController == null) {
            System.out.println("ReportController recebeu AcademicController NULL! Recuperando do ApplicationContext...");
            this.academicController = ApplicationContext.getInstance().getAcademicController();
            System.out.println("ReportController recuperou: " + this.academicController);
        } else {
            this.academicController = academicController;
            System.out.println("ReportController recebeu AcademicController: " + academicController);
        }
        
        // Verifica se o controller está funcionando
        if (this.academicController != null) {
            System.out.println("Total de turmas disponíveis: " + this.academicController.getClasses().size());
        }
    }

    public void configurarUsuario(User user) {
        this.usuarioLogado = user;
        System.out.println("ReportController configurando usuário: " + (user != null ? user.getUsername() : "null"));

        if (user != null) {
            if (user.getRole() == Role.ADMIN) {
                btnPersistencia.setVisible(true);
                btnPersistencia.setManaged(true);
            } else {
                btnPersistencia.setVisible(false);
                btnPersistencia.setManaged(false);
            }
        }
    }

    @FXML
    private void gerarResumo() {
        try {
            System.out.println("gerarResumo() chamado!");
            
            // Verifica se o controller está disponível
            if (academicController == null) {
                academicController = ApplicationContext.getInstance().getAcademicController();
            }
            
            if (academicController == null) {
                resultadoArea.setText("Erro: AcademicController não foi inicializado!");
                mostrarErro("AcademicController não foi inicializado!");
                return;
            }

            String relatorio = academicController.generateSummary();
            System.out.println("Relatório gerado com sucesso. Tamanho: " + relatorio.length());
            
            resultadoArea.setText(relatorio);
            
        } catch (Exception e) {
            e.printStackTrace();
            resultadoArea.setText("Erro ao gerar relatório: " + e.getMessage());
            mostrarErro("Erro ao gerar relatório resumido: " + e.getMessage());
        }
    }

    @FXML
    private void gerarPeso() {
        try {
            System.out.println("gerarPeso() chamado!");
            
            if (academicController == null) {
                academicController = ApplicationContext.getInstance().getAcademicController();
            }
            
            if (academicController == null) {
                resultadoArea.setText("Erro: AcademicController não foi inicializado!");
                mostrarErro("AcademicController não foi inicializado!");
                return;
            }

            String relatorio = academicController.generateWeightReport();
            System.out.println("Relatório de peso gerado com sucesso. Tamanho: " + relatorio.length());
            
            resultadoArea.setText(relatorio);
            
        } catch (Exception e) {
            e.printStackTrace();
            resultadoArea.setText("Erro ao gerar relatório: " + e.getMessage());
            mostrarErro("Erro ao gerar relatório de peso: " + e.getMessage());
        }
    }

    @FXML
    private void gerarPersistencia() {
        try {
            System.out.println("gerarPersistencia() chamado!");
            
            if (academicController == null) {
                academicController = ApplicationContext.getInstance().getAcademicController();
            }
            
            if (academicController == null) {
                resultadoArea.setText("Erro: AcademicController não foi inicializado!");
                mostrarErro("AcademicController não foi inicializado!");
                return;
            }

            // Verifica se o usuário é ADMIN
            if (usuarioLogado == null || usuarioLogado.getRole() != Role.ADMIN) {
                resultadoArea.setText("Acesso negado! Apenas administradores podem acessar este relatório.");
                mostrarErro("Acesso negado! Apenas administradores podem acessar este relatório.");
                return;
            }

            String relatorio = academicController.generatePersistenceConfigurationReport();
            System.out.println("Relatório de persistência gerado com sucesso. Tamanho: " + relatorio.length());
            
            resultadoArea.setText(relatorio);
            
        } catch (Exception e) {
            e.printStackTrace();
            resultadoArea.setText("Erro ao gerar relatório: " + e.getMessage());
            mostrarErro("Erro ao gerar relatório de persistência: " + e.getMessage());
        }
    }

    @FXML
    private void voltar() {
        try {
            System.out.println("voltar() chamado!");
            
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/dashboard.fxml")
            );

            Parent root = loader.load();

            DashboardController controller = loader.getController();
            controller.configurarUsuario(usuarioLogado);
            controller.setAcademicController(academicController);
            controller.setAuthenticationController(null);

            Stage stage = (Stage) resultadoArea.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Principal");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarErro("Erro ao voltar: " + e.getMessage());
        }
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}