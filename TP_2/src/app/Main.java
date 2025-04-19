package app;
import Class.Usuario;
import Class.RecursoDigital;
import  Servicios.ServicioNotificacionesEmail;
import Interfaces.ServicioNotificaciones;
import Class.GestorUsuario;
import Class.GestorRecursos;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Consola consola = new Consola();
        GestorUsuario gestorUsuario = new GestorUsuario();
        GestorRecursos gestorRecursos = new GestorRecursos();

        List<Usuario> usuarios = new ArrayList<>();
        List<RecursoDigital> recursos = new ArrayList<>();

        ServicioNotificaciones notificador = new ServicioNotificacionesEmail();

        boolean salir = false;
        while (!salir) {
            consola.mostrarMenuPrincipal();
            System.out.print(" ⚙️ Seleccionar una opción: ");
            int opcion = consola.leerOpcion();

            switch (opcion) {
                //USUARIOS
                case 1 -> {
                    consola.mostrarMenuUsuarios();
                    int opUsuarios = consola.leerOpcion();
                    switch (opUsuarios) {
                        case 1 -> gestorUsuario.mostrarUsuarios();
                        case 2 -> gestorUsuario.agregarUsuarioDesdeConsola();
                        case 3 -> {
                            System.out.print("🔍 Ingrese el nombre a buscar: ");
                            String nombreBuscado = consola.leerTexto();
                            List<Usuario> encontrados = gestorUsuario.buscarPorNombre(nombreBuscado);
                            if (encontrados.isEmpty()) {
                                System.out.println("⚠️ No se encontraron usuarios con ese nombre.");
                            } else {
                                System.out.println("🔎 Usuarios encontrados:");
                                for (Usuario u : encontrados) {
                                    System.out.println("-------------------------");
                                    System.out.println(u);
                                }
                            }
                        }
                        case 4 -> System.out.println("↩️ Volviendo al menú principal...");
                        default -> System.out.println("⚠️ Opción incorrecta.");
                    }
                }
                //RECURSOS
                case 2 -> {
                    consola.mostrarMenuRecursos();
                    int opRecursos = consola.leerOpcion();
                    switch (opRecursos) {
                        case 1 -> {
                            gestorRecursos.mostrarRecursos();
                        }
                        case 2 -> gestorRecursos.crearRecursoDesdeConsola();
                        case 3 -> System.out.println("↩️ Volviendo al menú principal...");
                        default -> System.out.println("⚠️ Opción inválida.");
                    }
                }
                case 3 -> salir = true;
                default -> System.out.println("⚠️ Opción incorrecta ⚠️. Intente de nuevo.");
            }
        }
    }
}