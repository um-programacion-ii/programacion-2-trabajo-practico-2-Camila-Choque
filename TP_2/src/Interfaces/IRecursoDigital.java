package Interfaces;
import Class.RecursoDigital.TipoEstado;

public interface IRecursoDigital {

    int getId();
    String getTitulo();
    TipoEstado getEstado();
    String getAutor();

    void setTitulo(String titulo);
    void setEstado(TipoEstado estado);
    void setAutor(String autor);

    String toString();
}
