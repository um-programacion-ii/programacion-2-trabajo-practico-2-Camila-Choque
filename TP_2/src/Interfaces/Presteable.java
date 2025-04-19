package Interfaces;
import java.time.LocalDateTime;
import Class.Usuario;

public interface Presteable {
        boolean estaDisponible();
        LocalDateTime getFechaDevolucion();
        void prestar(Usuario usuario);

}
