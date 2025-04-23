package Class;
import Interfaces.ServicioNotificaciones;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GestorNotificaciones {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public void enviarNotificacion(Usuario usuario, String mensaje, ServicioNotificaciones servicio) {
            executor.submit(() -> servicio.enviarNotificacion(mensaje, usuario));
    }


}

