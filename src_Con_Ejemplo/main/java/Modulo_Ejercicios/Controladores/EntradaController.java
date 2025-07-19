package Modulo_Ejercicios.Controladores;

import MetodosGlobales.MetodosFrecuentes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EntradaController {
    @FXML
    private Button buttonPerfil;

    @FXML
    private Button buttonComunidad;

    @FXML
    private void cambiarAPerfil() {
        System.out.println("Cambiando perfil");
        MetodosFrecuentes.cambiarVentana((Stage) buttonPerfil.getScene().getWindow(), "/Modulo_Ejercicios/Views/Perfil.fxml", "Perfil");
    }


    @FXML
    private void cambiarAComunidad() {
        System.out.println("Cambiando comunidad");
        MetodosFrecuentes.cambiarVentana((Stage) buttonComunidad.getScene().getWindow(), "/Modulo_Comunidad/Views/Comunidad.fxml");
    }

}
