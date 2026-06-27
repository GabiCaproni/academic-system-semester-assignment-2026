package org.example.academic.system.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AcademicSystemApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/login.fxml"));

        Scene scene = new Scene(loader.load());

        stage.setTitle("Academic System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
    launch(args);
}
}