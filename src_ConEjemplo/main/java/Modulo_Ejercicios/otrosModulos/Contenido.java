package Modulo_Ejercicios.otrosModulos;

import java.util.ArrayList;

public class Contenido {
    //Sugerencia de guardado
    ArrayList<Leccion> lecciones;


    public Contenido() {
        lecciones = new ArrayList<Leccion>();
    }

    public boolean addLeccion(Leccion nuevaLeccion) {
        return lecciones.add(nuevaLeccion);
    }

    public ArrayList<Leccion> getLecciones() {
    return lecciones;
    }

    public Leccion getLeccion(int numLeccion) {
        return lecciones.get(numLeccion);
    }
}
