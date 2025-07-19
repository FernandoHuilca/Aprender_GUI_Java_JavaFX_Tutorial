package Modulo_Ejercicios.exercises;

import java.util.ArrayList;

/**
 * Clase que representa un ejercicio de completar código
 * El usuario debe completar las partes faltantes del código
 */
public class EjercicioCompletarCodigo extends EjercicioBase {
    private String codigoIncompleto;
    private ArrayList<String> partesFaltantes;
    private ArrayList<String> respuestasEsperadas;

    /**
     * Constructor privado para usar con Builder
     */
    private EjercicioCompletarCodigo(String instruccion, String codigoIncompleto, 
                                   ArrayList<String> partesFaltantes, ArrayList<String> respuestasEsperadas,
                                   NivelDificultad nivelDificultad, Lenguaje lenguaje) {
        super(instruccion, respuestasEsperadas, nivelDificultad, lenguaje);
        this.codigoIncompleto = codigoIncompleto;
        this.partesFaltantes = partesFaltantes;
        this.respuestasEsperadas = respuestasEsperadas;
    }

    /**
     * Obtiene el código incompleto del ejercicio
     * @return El código con espacios en blanco
     */
    public String obtenerCodigoIncompleto() {
        return codigoIncompleto;
    }

    /**
     * Obtiene las partes que faltan por completar
     * @return Lista de partes faltantes
     */
    public ArrayList<String> obtenerPartesFaltantes() {
        return partesFaltantes;
    }

    /**
     * Obtiene las respuestas esperadas para cada parte
     * @return Lista de respuestas correctas
     */
    public ArrayList<String> obtenerRespuestasEsperadas() {
        return respuestasEsperadas;
    }

    /**
     * Obtiene el número de partes que faltan por completar
     * @return Número de partes faltantes
     */
    public int obtenerNumeroPartesFaltantes() {
        return partesFaltantes.size();
    }

    @Override
    public ResultadoDeEvaluacion evaluarRespuestas(ArrayList<Respuesta> respuestasUsuario) {
        //Verificar que el usuario haya proporcionado al menos una respuesta
        if (respuestasUsuario == null || respuestasUsuario.isEmpty()) {
            return new ResultadoDeEvaluacion(0);
        }
        
        //Verificar que el número de respuestas del usuario coincida con el número de partes faltantes
        if (respuestasUsuario.size() != partesFaltantes.size()) {
            return new ResultadoDeEvaluacion(0);
        }
        
        //Convertir las respuestas del usuario a strings para comparación
        ArrayList<String> respuestasUsuarioStrings = new ArrayList<>();
        for (Respuesta respuesta : respuestasUsuario) {
            respuestasUsuarioStrings.add(respuesta.getRespuesta().toString().trim());
        }
        
        //Comparar cada respuesta del usuario con la respuesta esperada correspondiente
        for (int i = 0; i < respuestasEsperadas.size(); i++) {
            String respuestaEsperada = respuestasEsperadas.get(i).trim();
            String respuestaUsuario = respuestasUsuarioStrings.get(i);
            
            //Comparación case-insensitive para ser más flexible
            if (!respuestaEsperada.equalsIgnoreCase(respuestaUsuario)) {
                return new ResultadoDeEvaluacion(0);
            }
        }
        
        return new ResultadoDeEvaluacion(100);
    }

    /**
     * Clase Builder para EjercicioCompletarCodigo
     * Permite construir ejercicios de completar código de forma fluida
     */
    public static class Builder {
        private String instruccion;
        private String codigoIncompleto;
        private ArrayList<String> partesFaltantes = new ArrayList<>();
        private ArrayList<String> respuestasEsperadas = new ArrayList<>();
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
         * Establece el código incompleto
         */
        public Builder conCodigoIncompleto(String codigoIncompleto) {
            this.codigoIncompleto = codigoIncompleto;
            return this;
        }

        /**
         * Agrega una parte faltante
         */
        public Builder conParteFaltante(String parteFaltante) {
            this.partesFaltantes.add(parteFaltante);
            return this;
        }

        /**
         * Agrega múltiples partes faltantes
         */
        public Builder conPartesFaltantes(ArrayList<String> partesFaltantes) {
            this.partesFaltantes.addAll(partesFaltantes);
            return this;
        }

        /**
         * Agrega una respuesta esperada
         */
        public Builder conRespuestaEsperada(String respuestaEsperada) {
            this.respuestasEsperadas.add(respuestaEsperada);
            return this;
        }

        /**
         * Agrega múltiples respuestas esperadas
         */
        public Builder conRespuestasEsperadas(ArrayList<String> respuestasEsperadas) {
            this.respuestasEsperadas.addAll(respuestasEsperadas);
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
         * Construye y valida el EjercicioCompletarCodigo
         * @return EjercicioCompletarCodigo construido
         * @throws IllegalArgumentException si faltan parámetros requeridos
         */
        public EjercicioCompletarCodigo construir() {
            //Validaciones
            if (instruccion == null || instruccion.trim().isEmpty()) {
                throw new IllegalArgumentException("La instrucción es requerida");
            }
            if (codigoIncompleto == null || codigoIncompleto.trim().isEmpty()) {
                throw new IllegalArgumentException("El código incompleto es requerido");
            }
            if (partesFaltantes.isEmpty()) {
                throw new IllegalArgumentException("Debe haber al menos una parte faltante");
            }
            if (respuestasEsperadas.isEmpty()) {
                throw new IllegalArgumentException("Debe haber al menos una respuesta esperada");
            }
            if (partesFaltantes.size() != respuestasEsperadas.size()) {
                throw new IllegalArgumentException("El número de partes faltantes debe coincidir con el número de respuestas esperadas");
            }
            if (nivelDificultad == null) {
                throw new IllegalArgumentException("El nivel de dificultad es requerido");
            }
            if (lenguaje == null) {
                throw new IllegalArgumentException("El lenguaje es requerido");
            }

            return new EjercicioCompletarCodigo(instruccion, codigoIncompleto, partesFaltantes, respuestasEsperadas, nivelDificultad, lenguaje);
        }
    }
} 