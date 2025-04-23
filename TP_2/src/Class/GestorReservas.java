package Class;
import java.util.concurrent.PriorityBlockingQueue;
import java.time.LocalDate;
import Interfaces.ServicioNotificaciones;


public class GestorReservas {

    private PriorityBlockingQueue<Reserva> colaReservas = new PriorityBlockingQueue<>();
    private int contadorPrioridad = 1;

    private final ServicioNotificaciones notificador;
    private final GestorUsuario gestorUsuario;

    public GestorReservas(ServicioNotificaciones notificador, GestorUsuario gestorUsuario) {
        this.notificador = notificador;
        this.gestorUsuario = gestorUsuario;
    }


    public void agregarReserva(Usuario usuario, RecursoDigital recurso) {
        Reserva reserva = new Reserva(usuario, recurso, LocalDate.now(), contadorPrioridad++);
        colaReservas.add(reserva);
        gestorUsuario.incrementarActividad(usuario);
        recurso.setEstado(RecursoDigital.TipoEstado.RESERVADO);
        System.out.println("                 ");
        System.out.println("✅ Reserva registrada con prioridad " + reserva.getPrioridad() + " para " + usuario.getNombre() + " del recurso " + recurso.getTitulo());
        String mensaje = "El recurso ha sido reservado correctamente.";
        notificador.enviarNotificacion(mensaje, usuario);

        System.out.println("                 ");
        System.out.println(reserva);

    }


    public void cancelarReserva(Usuario usuario, RecursoDigital recurso) {
        boolean reservaCancelada = false;
        for (Reserva reserva : colaReservas) {
            if (reserva.getRecurso().equals(recurso) && reserva.getUsuario().equals(usuario)) {
                colaReservas.remove(reserva);
                gestorUsuario.incrementarActividad(usuario);
                recurso.setEstado(RecursoDigital.TipoEstado.DISPONIBLE);
                System.out.println(" Verificando Reserva.... ");
                System.out.println("                     ");
                System.out.println(reserva);
                System.out.println("                     ");
                System.out.println("✅ Reserva cancelada para " + usuario.getNombre() + " del recurso " + recurso.getTitulo());
                String mensaje = "El recurso ha sido cancelado correctamente.";
                notificador.enviarNotificacion(mensaje, usuario);
                reservaCancelada = true;
                break;
            }
        }

        if (reservaCancelada) {
            notificarDisponibilidad(recurso);
        } else {
            System.out.println("No se encontró la reserva para este usuario y recurso.");
        }
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
    private void notificarDisponibilidad(RecursoDigital recurso) {
        for (Reserva reserva : colaReservas) {
            if (reserva.getRecurso().equals(recurso)) {
                String mensaje = "ALERTA: El recurso " + recurso.getTitulo() + " está disponible para el usuario " + reserva.getUsuario().getNombre() + ".";
                notificador.enviarNotificacion(mensaje, reserva.getUsuario());
                return;
            }
        }
    }


    public PriorityBlockingQueue<Reserva> getColaReservas() {
        return colaReservas;
    }

}
