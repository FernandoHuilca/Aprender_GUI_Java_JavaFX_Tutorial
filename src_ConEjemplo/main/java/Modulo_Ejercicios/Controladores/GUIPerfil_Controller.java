package Modulo_Ejercicios.Controladores;

import MetodosGlobales.MetodosFrecuentes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class GUIPerfil_Controller {
    @FXML
    private Button buttonPerfil;

    @FXML
    public void back_buttonPerfil() {
        System.out.println("Cambiando ventana de regreso");
        MetodosFrecuentes.cambiarVentana((Stage) buttonPerfil.getScene().getWindow(), "/Modulo_Ejercicios/views/GUIEntrada.fxml");
    }

}
