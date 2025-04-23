package Class;
import java.util.*;

public class GestorReportes {
    private final Map<Usuario, Integer> actividadUsuarios;

    public GestorReportes(Map<Usuario, Integer> actividadUsuarios) {
        this.actividadUsuarios = actividadUsuarios;
    }

    public void reporteUsuariosSinActividad(List<Usuario> todosLosUsuarios) {
        System.out.println("📊 Reporte de usuarios SIN actividad:");

        for (Usuario usuario : todosLosUsuarios) {
            if (!actividadUsuarios.containsKey(usuario) || actividadUsuarios.get(usuario) == 0) {
                System.out.println(" Usuario: " + usuario.getNombre());
            }
        }
    }
}
