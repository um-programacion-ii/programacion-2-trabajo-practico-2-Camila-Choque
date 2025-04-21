package Class;
import java.util.concurrent.PriorityBlockingQueue;
import java.time.LocalDate;
import Interfaces.ServicioNotificaciones;


public class GestorReservas {

    private PriorityBlockingQueue<Reserva> colaReservas = new PriorityBlockingQueue<>();
    private int contadorPrioridad = 1;

    private final ServicioNotificaciones notificador;
    private final GestorNotificaciones gestorNotificaciones;

    public GestorReservas(ServicioNotificaciones notificador, GestorNotificaciones gestorNotificaciones) {
        this.notificador = notificador;
        this.gestorNotificaciones = gestorNotificaciones;
    }


    public void agregarReserva(Usuario usuario, RecursoDigital recurso) {
        Reserva reserva = new Reserva(usuario, recurso, LocalDate.now(), contadorPrioridad++);
        colaReservas.add(reserva);
        recurso.setEstado(RecursoDigital.TipoEstado.RESERVADO);
        System.out.println("                 ");
        System.out.println("✅ Reserva registrada con prioridad " + reserva.getPrioridad() + " para " + usuario.getNombre() + " del recurso " + recurso.getTitulo());
        String mensaje = "El recurso ha sido reservado correctamente.";
        notificador.enviarNotificacion(mensaje, usuario);

        System.out.println("                 ");
        System.out.println(reserva);

    }


    public void cancelarReserva(Usuario usuario, RecursoDigital recurso) {
        for (Reserva reserva : colaReservas) {
            if (reserva.getRecurso().equals(recurso) && reserva.getUsuario().equals(usuario)) {
                colaReservas.remove(reserva);
                recurso.setEstado(RecursoDigital.TipoEstado.DISPONIBLE);
                System.out.println(" Verificando Reserva.... ");
                System.out.println("                     ");
                System.out.println(reserva);
                System.out.println("                     ");
                System.out.println("✅ Reserva cancelada para " + usuario.getNombre() + " del recurso " + recurso.getTitulo());
                String mensaje = "El recurso ha sido cancelado correctamente.";
                notificador.enviarNotificacion(mensaje, usuario);
                return;
            }
        }
        System.out.println("No se encontró la reserva para este usuario y recurso.");
    }


    public void mostrarReservas() {
        if (colaReservas.isEmpty()) {
            System.out.println("No hay reservas.");
        } else {
            System.out.println(" Listado de reservas:");
            for (Reserva reserva : colaReservas) {
                System.out.println("           ");
                System.out.println(reserva);
            }
        }
    }
    /*
    //SE PUEDE LLEGAR A IMPLEMENTAR
    public Usuario obtenerSiguienteReserva(RecursoDigital recurso) {
        for (Reserva r : colaReservas) {
            if (r.getRecurso().equals(recurso)) {
                return r.getUsuario(); // devuelve el próximo usuario para ese recurso
            }
        }
        return null;
    }
     */
}
