package Class;

import Interfaces.IRecursoDigital;

public class AudioLibro extends RecursoDigital implements IRecursoDigital {

    private int duracionMinutos;

    //CONSTRUCTOR


    public AudioLibro(int id, String titulo, TipoEstado estado, String autor, String categoria, int duracionMinutos) {
        super(id, titulo, estado, autor, categoria);
        this.duracionMinutos = duracionMinutos;
    }

    //GETTER
    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    //SETTER
    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    @Override
    public String toString() {
        return "\uD83C\uDFA7  AUDIOLIBRO\n" +
                "ID: " + getId() +
                "\nTítulo: " + getTitulo() +
                "\nAutor: " + getAutor() +
                "\nEstado: " + getEstado() +
                "\nDuración: " + duracionMinutos + " minutos";
    }

}
