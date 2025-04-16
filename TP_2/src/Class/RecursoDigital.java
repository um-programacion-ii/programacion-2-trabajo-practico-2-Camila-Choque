package Class;

import Interfaces.IRecursoDigital;

public abstract class RecursoDigital implements IRecursoDigital {
    private final int id;
    private String titulo;
    private TipoEstado estado;
    private String autor;

    //CONSTRUCTOR


    public RecursoDigital(int id, String titulo, TipoEstado estado, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.estado = estado;
        this.autor = autor;
    }

    //GETTER
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public String getAutor() {
        return autor;
    }

    //SETTER
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public enum TipoEstado {
        DISPONIBLE,
        PRESTADO,
        VENCIDO,
        RESERVADO
    }

}
