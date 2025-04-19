package Class;
import java.util.Comparator;
public class ComparadorPorTitulo implements Comparator<RecursoDigital> {
    @Override
    public int compare(RecursoDigital r1, RecursoDigital r2) {
        return r1.getTitulo().compareToIgnoreCase(r2.getTitulo());
    }
}
