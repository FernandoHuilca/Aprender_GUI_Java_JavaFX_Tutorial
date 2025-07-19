package Modulo_Ejercicios.application;

import Modulo_Ejercicios.otrosModulos.Contenido;
import Modulo_Ejercicios.otrosModulos.Leccion;
import Modulo_Ejercicios.exercises.EjercicioSeleccion;
import Modulo_Ejercicios.exercises.EjercicioCompletarCodigo;
import Modulo_Ejercicios.exercises.EjercicioBase;
import Modulo_Ejercicios.exercises.Respuesta;
import Modulo_Ejercicios.exercises.RespuestaString;
import Modulo_Ejercicios.exercises.ResultadoDeEvaluacion;
import Modulo_Ejercicios.exercises.Lenguaje;
import Modulo_Ejercicios.exercises.NivelDificultad;
import Modulo_Ejercicios.DataBase.EjercicioRepository;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

//Fernando Huilca y Mateo Quisilema
public class Main {

    private static Scanner inputScanner = new Scanner(System.in); //Scanner para entrada del usuario
    
    public static void main(String[] args) {
        //Variables para el menú principal
        int seleccionUsuario = 1; //Control del bucle del menú principal
        
        //Inicialización del contenido educativo
        Contenido cursoBasico = new Contenido();
        Leccion leccionTiposDatos = new Leccion("Tipos de Datos");
        Leccion leccionSintaxisBasica = new Leccion("Sintaxis Básica");
        cursoBasico.addLeccion(leccionSintaxisBasica);
        cursoBasico.addLeccion(leccionTiposDatos);

        EjercicioSeleccion ejercicioSeleccion = new EjercicioSeleccion.Builder()
                .conInstruccion("HOLA COMO ESTA").conLenguaje(Lenguaje.JAVA)
                .conNivel(NivelDificultad.BASICO).conOpcion("bien").conRespuestaCorrecta("mal").construir();


        EjercicioRepository.guardarEjercicioSeleccion(ejercicioSeleccion);



        //Carga de ejercicios desde el repositorio
        List<EjercicioCompletarCodigo> ejerciciosCompletarCodigo = EjercicioRepository.cargarEjerciciosCompletarCodigo();
        List<EjercicioSeleccion> ejerciciosSeleccion = EjercicioRepository.cargarEjerciciosSeleccion();





        for (EjercicioCompletarCodigo ejercicio : ejerciciosCompletarCodigo) {
            leccionSintaxisBasica.addEjercicio(ejercicio);
        }
        for (EjercicioSeleccion ejercicio : ejerciciosSeleccion) {
            leccionTiposDatos.addEjercicio(ejercicio);
        }

        //Interfaz de usuario principal
        System.out.println("_______________________ Bienvenido a HelloCode _______________________");
        while (seleccionUsuario != 111) {
            mostrarMenuLecciones(cursoBasico);
            seleccionUsuario = inputScanner.nextInt();
            
            if (seleccionUsuario >= 0 && seleccionUsuario < cursoBasico.getLecciones().size()) {
                ejecutarLeccionSeleccionada(cursoBasico, seleccionUsuario);
            } else if (seleccionUsuario != 111) {
                System.out.println("Opción no válida. Por favor, seleccione una lección válida.");
            }
        }
    }

    /**
     * Muestra el menú de lecciones disponibles
     */
    private static void mostrarMenuLecciones(Contenido curso) {
        System.out.println("_________________ Contenido del curso _________________");
        System.out.println("Seleccione la lección que desea realizar: ");
        for (int i = 0; i < curso.getLecciones().size(); i++) {
            System.out.println(i + ". Lección: " + curso.getLecciones().get(i).getNombre());
        }
        System.out.println("111. Salir");
    }

    /**
     * Ejecuta la lección seleccionada por el usuario
     */
    private static void ejecutarLeccionSeleccionada(Contenido curso, int indiceLeccion) {
        Leccion leccionActual = curso.getLeccion(indiceLeccion);
        System.out.println("____________________ Lección: " + leccionActual.getNombre() + " ____________________");
        
        for (int i = 0; i < leccionActual.getNumEjercicios(); i++) {
            EjercicioBase ejercicioActual = leccionActual.getEjercicio(i);
            
            //Aplicación del patrón Strategy para diferentes tipos de ejercicios
            if (ejercicioActual instanceof EjercicioSeleccion) {
                ejecutarEjercicioSeleccionMultiple((EjercicioSeleccion) ejercicioActual);
            } else if (ejercicioActual instanceof EjercicioCompletarCodigo) {
                ejecutarEjercicioCompletarCodigo((EjercicioCompletarCodigo) ejercicioActual);
            }
            //Aquí se pueden agregar más tipos de ejercicios en el futuro
        }
    }

    /**
     * Ejecuta un ejercicio de selección múltiple
     * Esta lógica podría ser parte de una interfaz gráfica en el futuro
     */
    private static void ejecutarEjercicioSeleccionMultiple(EjercicioSeleccion ejercicio) {
        System.out.println("\n" + ejercicio.getInstruccion());
        System.out.println("____ Opciones ____");
        
        for (int i = 0; i < ejercicio.getListOpciones().size(); i++) {
            System.out.println(i + ". " + ejercicio.getOpcion(i));
        }
        
        System.out.println("\nEste ejercicio tiene " + ejercicio.obtenerRespuestasCorrectas().size() + " respuesta(s) correcta(s).");
        
        
        ArrayList<Respuesta> respuestasUsuario = new ArrayList<>();
        
        for (int i = 0; i < ejercicio.obtenerRespuestasCorrectas().size(); i++) {
            System.out.print("Seleccione la respuesta " + (i + 1) + " (número de opción): ");
            int seleccionRespuesta = inputScanner.nextInt();
            
            //Validación de entrada
            if (seleccionRespuesta >= 0 && seleccionRespuesta < ejercicio.getListOpciones().size()) {
                //Verificar que no se haya seleccionado la misma opción antes
                    respuestasUsuario.add(new RespuestaString(ejercicio.getOpcion(seleccionRespuesta)));
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                i--; // Repetir esta iteración
            }
        }
        
        System.out.println("\nSus respuestas seleccionadas:");
        for (int i = 0; i < respuestasUsuario.size(); i++) {
            System.out.println((i + 1) + ". " + respuestasUsuario.get(i).getRespuesta());
        }
        
        ResultadoDeEvaluacion resultadoDeEvaluacion = ejercicio.evaluarRespuestas(respuestasUsuario);

        if (resultadoDeEvaluacion.getPorcentajeDeAcerto() == 100) {
            System.out.println("\n¡Correcto! ¡Felicidades! Todas sus respuestas son correctas.");
        } else {
            System.out.println("\nIncorrecto. ¡Sigue practicando!");
            System.out.println("Respuestas correctas: " + ejercicio.obtenerRespuestasCorrectas());
        }
    }

    /**
     * Ejecuta un ejercicio de completar código
     * Esta lógica podría ser parte de una interfaz gráfica en el futuro
     */
    private static void ejecutarEjercicioCompletarCodigo(EjercicioCompletarCodigo ejercicio) {
        System.out.println("\n" + ejercicio.getInstruccion());
        System.out.println("____ Código a completar ____");
        System.out.println(ejercicio.obtenerCodigoIncompleto());
        
        System.out.println("\nEste ejercicio tiene " + ejercicio.obtenerNumeroPartesFaltantes() + " parte(s) por completar.");
        
        ArrayList<Respuesta> respuestasUsuario = new ArrayList<>();
        
        for (int i = 0; i < ejercicio.obtenerNumeroPartesFaltantes(); i++) {
            System.out.print("Complete la parte " + (i + 1) + ": ");
            String respuestaUsuario = inputScanner.next();
            respuestasUsuario.add(new RespuestaString(respuestaUsuario));
        }
        
        System.out.println("\nSus respuestas:");
        for (int i = 0; i < respuestasUsuario.size(); i++) {
            System.out.println((i + 1) + ". " + respuestasUsuario.get(i).getRespuesta());
        }
        
        ResultadoDeEvaluacion resultadoDeEvaluacion = ejercicio.evaluarRespuestas(respuestasUsuario);
        
        if (resultadoDeEvaluacion.getPorcentajeDeAcerto() == 100) {
            System.out.println("\n¡Correcto! ¡Felicidades! El código está completo.");
        } else {
            System.out.println("\nIncorrecto. ¡Sigue practicando!");
            System.out.println("Respuestas correctas: " + ejercicio.obtenerRespuestasEsperadas());
        }
    }
}