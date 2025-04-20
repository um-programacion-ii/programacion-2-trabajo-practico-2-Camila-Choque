package app;
import Class.Usuario;
import Class.RecursoDigital;
import  Servicios.ServicioNotificacionesEmail;
import Interfaces.ServicioNotificaciones;
import Class.GestorUsuario;
import Class.GestorRecursos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Exceptions.UsuarioNoEncontradoException;
import Exceptions.RecursoNoDisponibleException;


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

                            try {
                                List<Usuario> encontrados = gestorUsuario.buscarPorNombre(nombreBuscado);
                                System.out.println("🔎 Usuarios encontrados:");
                                for (Usuario u : encontrados) {
                                    System.out.println("-------------------------");
                                    System.out.println(u);
                                }
                            } catch (UsuarioNoEncontradoException e) {
                                System.out.println(e.getMessage());
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
                        case 3 -> {
                            System.out.print("🔍 Ingrese el título a buscar: ");
                            String titulo = consola.leerTexto();

                            try {
                                gestorRecursos.buscarRecursoPorTitulo(titulo);
                            } catch (RecursoNoDisponibleException e) {
                                System.out.println(e.getMessage());
                            }

                        }
                        case 4 -> {
                            System.out.print("🏷️ Ingrese la categoría a filtrar: ");
                            String categoria = new Scanner(System.in).nextLine();
                            gestorRecursos.buscarPorCategoria(categoria);
                        }

                        // Opción para ordenar por título
                        case 5 -> {
                            System.out.println("🔠 Ordenando recursos por título...");
                            gestorRecursos.mostrarRecursos();
                        }
                        case 6 -> gestorRecursos.mostrarCategoriasDisponibles();
                        case 7 -> gestorRecursos.prestarRecursoDesdeConsola();

                        case 8 -> System.out.println("↩️ Volviendo al menú principal...");
                        default -> System.out.println("⚠️ Opción inválida.");
                    }
                }
                case 3 -> salir = true;
                default -> System.out.println("⚠️ Opción incorrecta ⚠️. Intente de nuevo.");
            }
        }
    }
}