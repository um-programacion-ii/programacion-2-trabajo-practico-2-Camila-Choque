package Class;
import java.time.LocalDate;


public class Reserva implements Comparable <Reserva>{
    private Usuario usuario;
    private RecursoDigital recurso;
    private LocalDate fechaReserva;
    private int prioridad;

    //CONSTRUCTOR


    public Reserva(Usuario usuario, RecursoDigital recurso, LocalDate fechaReserva, int prioridad) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = fechaReserva;
        this.prioridad = prioridad;
    }

    //GETTER
    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public int getPrioridad() {
        return prioridad;
    }

    //SETTER
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setRecurso(RecursoDigital recurso) {
        this.recurso = recurso;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public int compareTo(Reserva otra) {
        return this.fechaReserva.compareTo(otra.getFechaReserva()); // Más antigua = mayor prioridad
    }

    @Override
    public String toString() {
        return "------RESERVA------\n" +
                "Usuario: " + usuario.getNombre() + "\n" +
                "Recurso: " + recurso.getTitulo() + "\n" +
                "Fecha: " + fechaReserva + "\n" +
                "Prioridad: " + prioridad;
    }
}
