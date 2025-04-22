package app;
import Class.Usuario;
import Class.RecursoDigital;
import  Servicios.ServicioNotificacionesEmail;
import  Servicios.ServicioNotificacionesSMS;
import Interfaces.ServicioNotificaciones;
import Class.GestorUsuario;
import Class.GestorRecursos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Exceptions.UsuarioNoEncontradoException;
import Exceptions.RecursoNoDisponibleException;
import Class.GestorPrestamos;
import Class.Prestamo;
import Class.GestorReservas;
import Class.GestorNotificaciones;


public class Main {
    private static Usuario usuarioActual;

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuarioActual) {
        Main.usuarioActual = usuarioActual;
    }

    public static void main(String[] args) {
        Consola consola = new Consola();
        GestorUsuario gestorUsuario = new GestorUsuario();
        GestorRecursos gestorRecursos = new GestorRecursos();
        GestorNotificaciones gestorNotificaciones = new GestorNotificaciones();

        ServicioNotificaciones email = new ServicioNotificacionesEmail();
        ServicioNotificaciones sms = new ServicioNotificacionesSMS();
        GestorPrestamos gestorPrestamos = new GestorPrestamos(sms, gestorNotificaciones,gestorUsuario);
        GestorReservas gestorReservas = new GestorReservas(email, gestorNotificaciones,gestorUsuario);


        List<Usuario> usuarios = new ArrayList<>();
        List<RecursoDigital> recursos = new ArrayList<>();
        List<Prestamo> prestamos = new ArrayList<>();


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
                            System.out.print(" Ingrese el nombre a buscar: ");
                            String nombreBuscado = consola.leerTexto();

                            try {
                                List<Usuario> encontrados = gestorUsuario.buscarPorNombre(nombreBuscado);
                                System.out.println(" Usuarios encontrados:");
                                for (Usuario u : encontrados) {
                                    System.out.println("-------------------------");
                                    System.out.println(u);
                                }
                            } catch (UsuarioNoEncontradoException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        case 4 -> gestorUsuario.reporteUsuariosMasActivos();
                        case 5 -> System.out.println("↩️ Volviendo al menú principal...");
                        default -> System.out.println("  Opción incorrecta.");
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
                            System.out.print(" Ingrese el título a buscar: ");
                            String titulo = consola.leerTexto();
                            RecursoDigital recurso = null;
                            try {
                                recurso = gestorRecursos.buscarRecursoPorTitulo(titulo);
                                System.out.println("--------------------------");
                                System.out.println(recurso);
                            } catch (RecursoNoDisponibleException e) {
                                System.out.println(e.getMessage());
                            }

                        }
                        case 4 -> {
                            System.out.print(" Ingrese la categoría a filtrar: ");
                            String categoria = new Scanner(System.in).nextLine();
                            gestorRecursos.buscarPorCategoria(categoria);
                        }

                        // Opción para ordenar por título
                        case 5 -> {
                            System.out.println("🔠 Ordenando recursos por título...");
                            gestorRecursos.mostrarRecursos();
                        }
                        case 6 -> gestorRecursos.mostrarCategoriasDisponibles();

                        case 7 -> {gestorRecursos.mostrarEstadisticasPorCategoria();}
                        case 8-> System.out.println("↩️ Volviendo al menú principal...");
                        default -> System.out.println(" Opción inválida.");
                    }
                }

                // PRESTAMOS
                case 3 -> {
                    boolean salirPrestamos = false;
                    while (!salirPrestamos) {
                        consola.mostrarMenuPrestamos();
                        int opcionPrestamo = consola.leerOpcion();

                        switch (opcionPrestamo) {
                            case 1 -> {
                                Usuario usuario = null;
                                RecursoDigital recurso = null;

                                while (usuario == null) {
                                    System.out.print(" Ingrese el nombre del usuario: ");
                                    String nombreUsuario = consola.leerTexto();

                                    try {
                                        List<Usuario> usuariosEncontrados = gestorUsuario.buscarPorNombre(nombreUsuario);
                                        if (!usuariosEncontrados.isEmpty()) {
                                            usuario = usuariosEncontrados.get(0); // tomamos el primero
                                        } else {
                                            System.out.println(" No se encontró ningún usuario con ese nombre.");
                                        }
                                    } catch (UsuarioNoEncontradoException e) {
                                        System.out.println(" Usuario no encontrado. Intente nuevamente.");
                                    }
                                }

                                while (recurso == null) {
                                    System.out.print(" Ingrese el título del recurso: ");
                                    String titulo = consola.leerTexto();
                                    try {
                                        recurso = gestorRecursos.buscarRecursoPorTitulo(titulo);
                                    } catch (RecursoNoDisponibleException e) {
                                        System.out.println(" Recurso no disponible. Intente con otro título.");
                                    }

                                }

                                try {
                                    gestorPrestamos.prestarRecurso(usuario, recurso);
                                } catch (RecursoNoDisponibleException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            case 2 -> {
                                Usuario usuario = null;
                                RecursoDigital recurso = null;

                                while (usuario == null) {
                                    System.out.print(" Ingrese el nombre del usuario: ");
                                    String nombreUsuario = consola.leerTexto();

                                    try {
                                        List<Usuario> usuariosEncontrados = gestorUsuario.buscarPorNombre(nombreUsuario);
                                        if (!usuariosEncontrados.isEmpty()) {
                                            usuario = usuariosEncontrados.get(0);
                                        } else {
                                            System.out.println(" No se encontró ningún usuario con ese nombre.");
                                        }
                                    } catch (UsuarioNoEncontradoException e) {
                                        System.out.println(" Usuario no encontrado. Intente nuevamente.");
                                    }
                                }

                                while (recurso == null) {
                                    System.out.print(" Ingrese el título del recurso a devolver: ");
                                    String titulo = consola.leerTexto();

                                    try {
                                        recurso = gestorRecursos.buscarRecursoPorTitulo(titulo);
                                    } catch (RecursoNoDisponibleException e) {
                                        System.out.println(" Recurso no disponible o no existe. Intente nuevamente.");
                                    }
                                }

                                gestorPrestamos.devolverRecurso(usuario, recurso);
                            }

                            case 3 -> gestorPrestamos.mostrarPrestamosActivos();
                            case 4 -> {
                                gestorPrestamos.reporteRecursosMasPrestados();
                            }
                            case 5 -> {
                                System.out.println("↩️ Volviendo al menú principal...");
                                salirPrestamos = true;
                            }
                            default -> System.out.println(" Opción inválida.");
                        }
                    }
                }
                //RESERVAS
                case 4 -> {
                    boolean salirReservas = false;
                    while (!salirReservas) {
                        consola.mostrarMenuReservas();
                        int opcionReserva = consola.leerOpcion();

                        switch (opcionReserva) {

                            case 1 -> {
                                System.out.print(" Ingrese el nombre del usuario: ");
                                String nombreUsuario = consola.leerTexto();
                                Usuario usuario = null;
                                try {
                                    List<Usuario> usuariosEncontrados = gestorUsuario.buscarPorNombre(nombreUsuario);
                                    if (!usuariosEncontrados.isEmpty()) {
                                        usuario = usuariosEncontrados.get(0);
                                    } else {
                                        System.out.println(" No se encontró el usuario.");
                                        break;
                                    }
                                } catch (UsuarioNoEncontradoException e) {
                                    System.out.println(" Usuario no encontrado.");
                                    break;
                                }

                                System.out.print(" Ingrese el título del recurso a reservar: ");
                                String titulo = consola.leerTexto();
                                RecursoDigital recurso = null;
                                try {
                                    recurso = gestorRecursos.buscarRecursoPorTitulo(titulo);

                                    gestorReservas.agregarReserva(usuario, recurso);
                                } catch (RecursoNoDisponibleException e) {
                                    System.out.println(" Recurso no disponible.");
                                } catch (Exception e) {
                                    System.out.println("  Error: " + e.getMessage());
                                }
                            }

                            case 2 -> {
                                gestorReservas.mostrarReservas();
                            }
                            case 3 -> {
                                System.out.print(" Ingrese el nombre del usuario: ");
                                String nombreUsuarioCancelar = consola.leerTexto();
                                Usuario usuarioCancelar = null;
                                try {
                                    List<Usuario> usuariosEncontradosCancelar = gestorUsuario.buscarPorNombre(nombreUsuarioCancelar);
                                    if (!usuariosEncontradosCancelar.isEmpty()) {
                                        usuarioCancelar = usuariosEncontradosCancelar.get(0);
                                    } else {
                                        System.out.println(" No se encontró el usuario.");
                                        break;
                                    }
                                } catch (UsuarioNoEncontradoException e) {
                                    System.out.println(" Usuario no encontrado.");
                                    break;
                                }

                                System.out.print(" Ingrese el título del recurso a cancelar: ");
                                String tituloCancelar = consola.leerTexto();
                                RecursoDigital recursoCancelar = null;
                                try {
                                    recursoCancelar = gestorRecursos.buscarRecursoPorTitulo(tituloCancelar);
                                    gestorReservas.cancelarReserva(usuarioCancelar, recursoCancelar);
                                } catch (RecursoNoDisponibleException e) {
                                    System.out.println(" Recurso no disponible.");
                                } catch (Exception e) {
                                    System.out.println(" Error: " + e.getMessage());
                                }
                            }
                            case 4 -> {
                                System.out.println("↩️ Volviendo al menú principal...");
                                salirReservas = true;
                            }
                            default -> System.out.println("Opción inválida.");
                        }
                    }
                }
                case 5-> salir = true;
                default -> System.out.println(" Opción incorrecta . Intente de nuevo.");
            }
        }
    }
}