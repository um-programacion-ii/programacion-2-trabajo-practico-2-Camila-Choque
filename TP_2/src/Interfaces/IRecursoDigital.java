package Interfaces;
import Class.RecursoDigital.TipoEstado;

public interface IRecursoDigital {

    int getId();
    String getTitulo();
    String getEstado();
    String getAutor();

    void setTitulo(String titulo);
    void setEstado(TipoEstado estado);
    void setAutor(String autor);

    String toString();
}
