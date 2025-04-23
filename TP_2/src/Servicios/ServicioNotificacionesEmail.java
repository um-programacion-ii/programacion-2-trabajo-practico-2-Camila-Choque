package Servicios;
import  Interfaces.ServicioNotificaciones;
import  Class.Usuario;
import Enum.NivelUrgencia;

public class ServicioNotificacionesEmail implements ServicioNotificaciones {
    @Override
    public void enviarNotificacion(String mensaje, Usuario usuario) {
        System.out.println("📧 Enviando email a " + usuario.getNombre() + ": " + mensaje);
    }

    public void notificacionurgencia(String mensaje, Usuario usuario, NivelUrgencia nivel) {
        String nivelStr = switch (nivel) {
            case INFO -> "INFO";
            case WARNING -> "WARNING";
            case ERROR -> "ERROR";
        };
        System.out.println("[" + nivelStr + "] Enviando Email a " + usuario.getNombre() + ": " + mensaje);
    }
}