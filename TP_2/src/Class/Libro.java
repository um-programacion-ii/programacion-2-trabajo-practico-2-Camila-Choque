package Class;

public class Libro extends RecursoDigital{

    private int numeroPaginas;

    //CONSTRUCTOR


    public Libro(int id, String titulo, TipoEstado estado, String autor, int numeroPaginas) {
        super(id, titulo, estado, autor);
        this.numeroPaginas = numeroPaginas;
    }

    //GETTER
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    //SETTER
    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String toString() {
        return "\uD83D\uDCD8  LIBRO\n" +
                "ID: " + getId() +
                "\nTítulo: " + getTitulo() +
                "\nEstado: " + getEstado() +
                "\nAutor: " + getAutor() +
                "\nNumero de Páginas: " + numeroPaginas;
    }

}
