package Class;

public abstract class RecursoDigital {
    private final int id;
    private String titulo;
    private String estado;
    private String autor;

    //CONSTRUCTOR
    public RecursoDigital(int id, String titulo, String estado, String autor) {
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

    public String getEstado() {
        return estado;
    }

    public String getAutor() {
        return autor;
    }

    //SETTER
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
