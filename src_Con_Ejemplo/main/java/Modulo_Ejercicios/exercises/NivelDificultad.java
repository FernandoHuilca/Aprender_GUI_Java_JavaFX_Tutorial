package Modulo_Ejercicios.exercises;

public enum NivelDificultad {
    PRINCIPIANTE("Ideal para empezar desde cero"),
    BASICO("Ya conoces lo mínimo"),
    INTERMEDIO("Sabes lo suficiente para avanzar"),
    AVANZADO("Retos más complejos"),
    EXPERTO("Dominio casi total del lenguaje");

    private final String descripcion;

    NivelDificultad(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
