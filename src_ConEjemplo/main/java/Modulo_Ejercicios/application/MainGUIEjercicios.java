package Modulo_Ejercicios.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGUIEjercicios extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Modulo_Ejercicios/views/GUIEntrada.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("EntradaAplicativo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}


