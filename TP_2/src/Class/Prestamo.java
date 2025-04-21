package Class;
import java.time.LocalDate;


public class Prestamo {
    private Usuario usuario;
    private RecursoDigital recurso;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    //CONSTRUCTOR
    public Prestamo(Usuario usuario, RecursoDigital recurso, LocalDate fechaPrestamo ) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = null;
    }

    //GETTER
    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    //SETTER
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setRecurso(RecursoDigital recurso) {
        this.recurso = recurso;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String toString() {
        return "📄 Préstamo:\n" +
                "👤 Usuario: " + usuario.getNombre() + "\n" +
                "📚 Recurso: " + recurso.getTitulo() + "\n" +
                "📅 Fecha de préstamo: " + fechaPrestamo + "\n" +
                "📅 Fecha estimada de devolución: " + fechaDevolucion;
    }
}
