package Modulo_Ejercicios.Controladores;

import MetodosGlobales.MetodosFrecuentes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PerfilController {

    @FXML
    private Button buttonBack;

    @FXML
    private void volverALaEntrada(){
        System.out.println("Volviendo al entrada");
        MetodosFrecuentes.cambiarVentana((Stage) buttonBack.getScene().getWindow(), "/Modulo_Ejercicios/Views/Entrada.fxml", "Entrada");
    }

}
