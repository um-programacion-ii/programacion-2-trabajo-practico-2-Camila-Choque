package Servicios;

import Class.RecursoDigital;
import Class.Reserva;
import Interfaces.ServicioNotificaciones;
import java.util.concurrent.PriorityBlockingQueue;

public class AlertaDisponibilidad {
    private PriorityBlockingQueue<Reserva> reservas;
    private ServicioNotificaciones servicioNotificaciones;

    public AlertaDisponibilidad(PriorityBlockingQueue<Reserva> reservas, ServicioNotificaciones servicioNotificaciones) {
        this.reservas = reservas;
        this.servicioNotificaciones = servicioNotificaciones;
    }


    public void verificarDisponibilidad() {
        boolean notificacionEnviada = false;

        for (Reserva reserva : reservas) {
            if (reserva.getRecurso().getEstado() == RecursoDigital.TipoEstado.DISPONIBLE) {
                String mensaje = "ALERTA: El recurso " + reserva.getRecurso().getTitulo() + " está disponible para el usuario " + reserva.getUsuario().getNombre() + ".";
                servicioNotificaciones.enviarNotificacion(mensaje, reserva.getUsuario());
                notificacionEnviada = true;
            }
        }
        if (!notificacionEnviada) {
            System.out.println("No hay notificaciones disponibles.");
        }
    }

}
