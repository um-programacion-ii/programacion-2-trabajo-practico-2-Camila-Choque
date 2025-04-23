package app;
import Class.Usuario;
import Class.RecursoDigital;
import  Servicios.ServicioNotificacionesEmail;
import  Servicios.ServicioNotificacionesSMS;
import Interfaces.ServicioNotificaciones;
import Class.GestorUsuario;
import Class.GestorRecursos;
import java.util.List;
import java.util.Scanner;
import Exceptions.UsuarioNoEncontradoException;
import Exceptions.RecursoNoDisponibleException;
import Class.GestorPrestamos;
import Class.Prestamo;
import Class.GestorReservas;
import Servicios.AlertaDisponibilidad;
import Servicios.AlertaVencimiento;
import Class.GestorReportes;



public class Main {



    public static void main(String[] args) {
        Consola consola = new Consola();
        GestorUsuario gestorUsuario = new GestorUsuario();
        GestorRecursos gestorRecursos = new GestorRecursos();

        ServicioNotificaciones email = new ServicioNotificacionesEmail();
        ServicioNotificaciones sms = new ServicioNotificacionesSMS();
        ServicioNotificaciones servicioNotificaciones = new ServicioNotificacionesSMS();
        GestorPrestamos gestorPrestamos = new GestorPrestamos(sms, gestorUsuario);

        GestorReservas gestorReservas = new GestorReservas(email,gestorUsuario);

        List<Prestamo> listaPrestamos = gestorPrestamos.getListaDePrestamos();
        AlertaVencimiento alerta = new AlertaVencimiento(listaPrestamos, servicioNotificaciones);

        AlertaDisponibilidad alertaDisponibilidad = new AlertaDisponibilidad(gestorReservas.getColaReservas(), servicioNotificaciones);
        List<Usuario> Usuarios = gestorUsuario.getUsuarios();
        GestorReportes gestorReportes = new GestorReportes(gestorUsuario.getActividadUsuarios());
        gestorReportes.reporteUsuariosSinActividad(Usuarios);


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

                        case 7 -> gestorRecursos.mostrarEstadisticasPorCategoria();
                        case 8 ->
                            alertaDisponibilidad.verificarDisponibilidad();

                        case 9-> System.out.println("↩️ Volviendo al menú principal...");
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
                            case 5 ->
                                alerta.verificarVencimientos();

                            case 6-> {
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
                //REPORTES
                case 5 -> {
                    boolean salirReportes = false;
                    while (!salirReportes) {
                        consola.mostrarMenuReportes();
                        int opcionReporte = consola.leerOpcion();

                        switch (opcionReporte) {
                            case 1 -> {
                                gestorReportes.reporteUsuariosSinActividad(gestorUsuario.getUsuarios());
                            }
                            case 2 -> gestorUsuario.reporteUsuariosMasActivos();
                            case 3 -> {
                                System.out.println("🔙 Volviendo al menú principal...");
                                salirReportes = true;
                            }
                            default -> System.out.println(" Opción de reporte no válida.");
                        }
                    }
                }
                case 6-> salir = true;
                default -> System.out.println(" Opción incorrecta . Intente de nuevo.");
            }
        }
    }
}