package Modulo_Comunidad.Controladores;

import MetodosGlobales.MetodosFrecuentes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Comunidad {
    @FXML
    private Button buttonBack;
    @FXML
    private void VolverALaAnterior() {

        MetodosFrecuentes.cambiarVentana((Stage) buttonBack.getScene().getWindow(), "/Modulo_Ejercicios/Views/Entrada.fxml", "Entrada");
    }

}
