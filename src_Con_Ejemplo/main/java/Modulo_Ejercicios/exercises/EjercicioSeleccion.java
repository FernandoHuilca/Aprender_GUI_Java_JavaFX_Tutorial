package Modulo_Ejercicios.exercises;

import java.util.ArrayList;

public class EjercicioSeleccion extends EjercicioBase {
    private ArrayList<String> opcionesDeSeleccion;

    /**
     * Constructor privado para usar con Builder
     */
    private EjercicioSeleccion(String instruccion, ArrayList<String> opcionesDeSeleccion, ArrayList<String> respuestasCorrectas,
                              NivelDificultad nivelDeDificultad, Lenguaje lenguaje) {
        super(instruccion, respuestasCorrectas, nivelDeDificultad, lenguaje);
        this.opcionesDeSeleccion = opcionesDeSeleccion;
    }
    
    public ArrayList<String> getListOpciones() {
        return opcionesDeSeleccion;
    }

    public String getOpcion(int numOpcion) {
        return opcionesDeSeleccion.get(numOpcion);
    }

    /**
     * Obtiene las respuestas correctas del ejercicio
     * @return Lista de respuestas correctas
     */
    public ArrayList<String> obtenerRespuestasCorrectas() {
        return respuestasCorrectas;
    }

    @Override
    public ResultadoDeEvaluacion evaluarRespuestas(ArrayList<Respuesta> respuestasUsuario) {
        //Verificar que el usuario haya proporcionado al menos una respuesta
        if (respuestasUsuario == null || respuestasUsuario.isEmpty()) {
            return new ResultadoDeEvaluacion(0);
        }
        
        //Verificar que el número de respuestas del usuario coincida con el número de respuestas correctas
        if (respuestasUsuario.size() != respuestasCorrectas.size()) {
            return new ResultadoDeEvaluacion(0);
        }
        
        //Convertir las respuestas del usuario a strings para comparación
        ArrayList<String> respuestasUsuarioStrings = new ArrayList<>();
        for (Respuesta respuesta : respuestasUsuario) {
            respuestasUsuarioStrings.add(respuesta.getRespuesta().toString());
        }
        
        //Verificar que todas las respuestas del usuario estén en las respuestas correctas
        //y que todas las respuestas correctas estén en las del usuario
        if(respuestasUsuarioStrings.containsAll(respuestasCorrectas) && 
               respuestasCorrectas.containsAll(respuestasUsuarioStrings) ){
                return new ResultadoDeEvaluacion(100);
        };
        return new ResultadoDeEvaluacion(0);
    }

    /**
     * Clase Builder para EjercicioSeleccion
     * Permite construir ejercicios de selección múltiple de forma fluida
     */
    public static class Builder {
        private String instruccion;
        private ArrayList<String> opcionesDeSeleccion = new ArrayList<>();
        private ArrayList<String> respuestasCorrectas = new ArrayList<>();
        private NivelDificultad nivelDificultad = NivelDificultad.BASICO;
        private Lenguaje lenguaje = Lenguaje.JAVA;

        /**
         * Establece la instrucción del ejercicio
         */
        public Builder conInstruccion(String instruccion) {
            this.instruccion = instruccion;
            return this;
        }

        /**
         * Agrega una opción a la lista de opciones
         */
        public Builder conOpcion(String opcion) {
            this.opcionesDeSeleccion.add(opcion);
            return this;
        }

        /**
         * Agrega múltiples opciones a la lista
         */
        public Builder conOpciones(ArrayList<String> opciones) {
            this.opcionesDeSeleccion.addAll(opciones);
            return this;
        }

        /**
         * Agrega una respuesta correcta
         */
        public Builder conRespuestaCorrecta(String respuestaCorrecta) {
            this.respuestasCorrectas.add(respuestaCorrecta);
            return this;
        }

        /**
         * Agrega múltiples respuestas correctas
         */
        public Builder conRespuestasCorrectas(ArrayList<String> respuestasCorrectas) {
            this.respuestasCorrectas.addAll(respuestasCorrectas);
            return this;
        }

        /**
         * Establece el nivel de dificultad
         */
        public Builder conNivel(NivelDificultad nivelDificultad) {
            this.nivelDificultad = nivelDificultad;
            return this;
        }

        /**
         * Establece el lenguaje de programación
         */
        public Builder conLenguaje(Lenguaje lenguaje) {
            this.lenguaje = lenguaje;
            return this;
        }

        /**
         * Construye y valida el EjercicioSeleccion
         * @return EjercicioSeleccion construido
         * @throws IllegalArgumentException si faltan parámetros requeridos
         */
        public EjercicioSeleccion construir() {
            //Validaciones
            if (instruccion == null || instruccion.trim().isEmpty()) {
                throw new IllegalArgumentException("La instrucción es requerida");
            }
            if (opcionesDeSeleccion.isEmpty()) {
                throw new IllegalArgumentException("Debe haber al menos una opción");
            }
            if (respuestasCorrectas.isEmpty()) {
                throw new IllegalArgumentException("Debe haber al menos una respuesta correcta");
            }
            if (nivelDificultad == null) {
                throw new IllegalArgumentException("El nivel de dificultad es requerido");
            }
            if (lenguaje == null) {
                throw new IllegalArgumentException("El lenguaje es requerido");
            }

            return new EjercicioSeleccion(instruccion, opcionesDeSeleccion, respuestasCorrectas, nivelDificultad, lenguaje);
        }
    }
}
