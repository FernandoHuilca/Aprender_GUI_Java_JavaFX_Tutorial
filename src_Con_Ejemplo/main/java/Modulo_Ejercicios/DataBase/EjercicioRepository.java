package Modulo_Ejercicios.DataBase;

import Modulo_Ejercicios.exercises.EjercicioSeleccion;
import Modulo_Ejercicios.exercises.EjercicioCompletarCodigo;
import Modulo_Ejercicios.exercises.NivelDificultad;
import Modulo_Ejercicios.exercises.Lenguaje;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase responsable de la persistencia de ejercicios
 * Maneja la carga y guardado de ejercicios desde/hacia archivos de texto
 */
public class EjercicioRepository {
    
    private static final String RUTA_BASE = "src/main/resources/Modulo_Ejercicios/data/";
    private static final String ARCHIVO_SELECCION = "DB_EjerciciosSeleccion.txt";
    private static final String ARCHIVO_COMPLETAR = "DB_EjerciciosCompletarCodigo.txt";
    
    /**
     * Carga todos los ejercicios de selección desde el archivo
     */
    public static List<EjercicioSeleccion> cargarEjerciciosSeleccion() {
        List<EjercicioSeleccion> ejercicios = new ArrayList<>();
        String rutaCompleta = RUTA_BASE + ARCHIVO_SELECCION;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaCompleta))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    EjercicioSeleccion ejercicio = parsearEjercicioSeleccion(linea);
                    if (ejercicio != null) {
                        ejercicios.add(ejercicio);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar ejercicios de selección: " + e.getMessage());
        }
        
        return ejercicios;
    }
    
    /**
     * Carga todos los ejercicios de completar código desde el archivo
     */
    public static List<EjercicioCompletarCodigo> cargarEjerciciosCompletarCodigo() {
        List<EjercicioCompletarCodigo> ejercicios = new ArrayList<>();
        String rutaCompleta = RUTA_BASE + ARCHIVO_COMPLETAR;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaCompleta))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    EjercicioCompletarCodigo ejercicio = parsearEjercicioCompletarCodigo(linea);
                    if (ejercicio != null) {
                        ejercicios.add(ejercicio);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar ejercicios de completar código: " + e.getMessage());
        }
        
        return ejercicios;
    }
    
    /**
     * Guarda un nuevo ejercicio de selección en el archivo
     */
    public static void guardarEjercicioSeleccion(EjercicioSeleccion ejercicio) {
        String rutaCompleta = RUTA_BASE + ARCHIVO_SELECCION;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaCompleta, true))) {
            String linea = serializarEjercicioSeleccion(ejercicio);
            writer.write(linea);
            writer.newLine();
            System.out.println("Ejercicio de selección guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar ejercicio de selección: " + e.getMessage());
        }
    }
    
    /**
     * Guarda un nuevo ejercicio de completar código en el archivo
     */
    public static void guardarEjercicioCompletarCodigo(EjercicioCompletarCodigo ejercicio) {
        String rutaCompleta = RUTA_BASE + ARCHIVO_COMPLETAR;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaCompleta, true))) {
            String linea = serializarEjercicioCompletarCodigo(ejercicio);
            writer.write(linea);
            writer.newLine();
            System.out.println("Ejercicio de completar código guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar ejercicio de completar código: " + e.getMessage());
        }
    }
    
    /**
     * Parsea una línea de texto y crea un EjercicioSeleccion
     * Formato: instruccion|opcion1,opcion2,opcion3|respuesta1,respuesta2|nivel|lenguaje
     */
    private static EjercicioSeleccion parsearEjercicioSeleccion(String linea) {
        try {
            String[] partes = linea.split("\\|");
            if (partes.length >= 5) {
                String instruccion = partes[0];
                String[] opciones = partes[1].split(",");
                String[] respuestas = partes[2].split(",");
                NivelDificultad nivel = NivelDificultad.valueOf(partes[3]);
                Lenguaje lenguaje = Lenguaje.valueOf(partes[4]);
                
                EjercicioSeleccion.Builder builder = new EjercicioSeleccion.Builder()
                    .conInstruccion(instruccion)
                    .conNivel(nivel)
                    .conLenguaje(lenguaje);
                
                // Agregar opciones
                for (String opcion : opciones) {
                    builder.conOpcion(opcion.trim());
                }
                
                // Agregar respuestas correctas
                for (String respuesta : respuestas) {
                    builder.conRespuestaCorrecta(respuesta.trim());
                }
                
                return builder.construir();
            }
        } catch (Exception e) {
            System.out.println("Error al parsear ejercicio de selección: " + linea);
        }
        return null;
    }
    
    /**
     * Parsea una línea de texto y crea un EjercicioCompletarCodigo
     * Formato: instruccion|codigoIncompleto|parteFaltante|respuestaEsperada|nivel|lenguaje
     */
    private static EjercicioCompletarCodigo parsearEjercicioCompletarCodigo(String linea) {
        try {
            String[] partes = linea.split("\\|");
            if (partes.length >= 6) {
                String instruccion = partes[0];
                String codigoIncompleto = partes[1];
                String parteFaltante = partes[2];
                String respuestaEsperada = partes[3];
                NivelDificultad nivel = NivelDificultad.valueOf(partes[4]);
                Lenguaje lenguaje = Lenguaje.valueOf(partes[5]);
                
                return new EjercicioCompletarCodigo.Builder()
                    .conInstruccion(instruccion)
                    .conCodigoIncompleto(codigoIncompleto)
                    .conParteFaltante(parteFaltante)
                    .conRespuestaEsperada(respuestaEsperada)
                    .conNivel(nivel)
                    .conLenguaje(lenguaje)
                    .construir();
            }
        } catch (Exception e) {
            System.out.println("Error al parsear ejercicio de completar código: " + linea);
        }
        return null;
    }
    
    /**
     * Serializa un EjercicioSeleccion a formato de texto
     */
    private static String serializarEjercicioSeleccion(EjercicioSeleccion ejercicio) {
        StringBuilder sb = new StringBuilder();
        sb.append(ejercicio.getInstruccion()).append("|");
        
        // Opciones
        for (int i = 0; i < ejercicio.getListOpciones().size(); i++) {
            if (i > 0) sb.append(",");
            sb.append(ejercicio.getOpcion(i));
        }
        sb.append("|");
        
        // Respuestas correctas
        List<String> respuestasCorrectas = ejercicio.obtenerRespuestasCorrectas();
        for (int i = 0; i < respuestasCorrectas.size(); i++) {
            if (i > 0) sb.append(",");
            sb.append(respuestasCorrectas.get(i));
        }
        sb.append("|");
        
        sb.append(ejercicio.getNivel()).append("|");
        sb.append(ejercicio.getLenguaje());
        
        return sb.toString();
    }
    
    /**
     * Serializa un EjercicioCompletarCodigo a formato de texto
     */
    private static String serializarEjercicioCompletarCodigo(EjercicioCompletarCodigo ejercicio) {
        return ejercicio.getInstruccion() + "|" +
               ejercicio.obtenerCodigoIncompleto() + "|" +
               ejercicio.obtenerPartesFaltantes().get(0) + "|" +
               ejercicio.obtenerRespuestasEsperadas().get(0) + "|" +
               ejercicio.getNivel() + "|" +
               ejercicio.getLenguaje();
    }
} 