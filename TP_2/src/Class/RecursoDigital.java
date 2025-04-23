package Class;
import Interfaces.IRecursoDigital;
import Enum.CategoriaRecurso;


public abstract class RecursoDigital implements IRecursoDigital {
    private final int id;
    private String titulo;
    private TipoEstado estado;
    private String autor;
    private CategoriaRecurso categoria;


    //CONSTRUCTOR
    public RecursoDigital(int id, String titulo, TipoEstado estado, String autor, CategoriaRecurso categoria) {
        this.id = id;
        this.titulo = titulo;
        this.estado = estado;
        this.autor = autor;
        this.categoria = categoria;
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

    public CategoriaRecurso getCategoria() {return categoria;}

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

    public void setCategoria(CategoriaRecurso categoria) {this.categoria = categoria;}

    public enum TipoEstado {
        DISPONIBLE,
        PRESTADO,
        VENCIDO,
        RESERVADO
    }

}
