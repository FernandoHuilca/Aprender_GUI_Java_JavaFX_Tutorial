package Modulo_Ejercicios.Controladores;

import MetodosGlobales.MetodosFrecuentes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class GUIEntrada_Controller {

    @FXML
    private Button Button_Perfil;

    @FXML
    private Button Button_Comunidad;

    @FXML
    private void CambiarAPerfil_Button_Perfil(){
        System.out.println("Cambiando perfil");
        MetodosFrecuentes.cambiarVentana((Stage) Button_Perfil.getScene().getWindow(), "/Modulo_Ejercicios/views/GUIPerfil.fxml", "Perfil");

    }


    @FXML
    private void CambiarAComunidad_Button_Comunidad(){
        System.out.println("Cambiando Comunidad");

    }
}
