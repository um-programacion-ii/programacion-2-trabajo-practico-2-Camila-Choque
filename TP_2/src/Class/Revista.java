package Class;

import Interfaces.IRecursoDigital;
import Enum.CategoriaRecurso;

public class Revista extends RecursoDigital implements IRecursoDigital {

    private int numeroEdicion;

    //CONSTRUCTOR
    public Revista(int id, String titulo, TipoEstado estado, String autor, CategoriaRecurso categoria, int numeroEdicion) {
        super(id, titulo, estado, autor, categoria);
        this.numeroEdicion = numeroEdicion;
    }

    //GETTER
    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    //SETTER
    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }

    @Override
    public String toString() {
        return "------REVISTA------\n" +
                "ID: " + getId() +
                "\nTítulo: " + getTitulo() +
                "\nAutor: " + getAutor() +
                "\nEstado: " + getEstado() +
                "\nEdición: " + numeroEdicion;
    }

}
