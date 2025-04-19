package Servicios;
import  Interfaces.ServicioNotificaciones;
import  Class.Usuario;

public class ServicioNotificacionesEmail implements ServicioNotificaciones {
    @Override
    public void enviarNotificacion(String mensaje, Usuario usuario) {
        System.out.println("📧 Enviando email a " + usuario.getNombre() + ": " + mensaje);
    }
}