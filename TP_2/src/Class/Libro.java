package Class;
import Interfaces.IRecursoDigital;
import Enum.CategoriaRecurso;

public class Libro extends RecursoDigital implements IRecursoDigital {

    private int numeroPaginas;

    //CONSTRUCTOR
    public Libro(int id, String titulo, TipoEstado estado, String autor, CategoriaRecurso categoria, int numeroPaginas) {
        super(id, titulo, estado, autor, categoria);
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
        return "------LIBRO------\n" +
                "ID: " + getId() +
                "\nTítulo: " + getTitulo() +
                "\nEstado: " + getEstado() +
                "\nAutor: " + getAutor() +
                "\nNumero de Páginas: " + numeroPaginas;
    }

}
