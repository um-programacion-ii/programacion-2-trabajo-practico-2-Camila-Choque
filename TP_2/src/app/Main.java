package app;
import Class.Usuario;
import Class.RecursoDigital;
import  Class.Libro;
import  Class.Revista;
import  Class.AudioLibro;
import  Class.RecursoDigital.TipoEstado;
import  Servicios.ServicioNotificacionesEmail;
import Interfaces.ServicioNotificaciones;
import Servicios.ServicioNotificacionesSMS;


public class Main {
    public static void main(String[] args) {
        Consola consola = new Consola();

        // LISTA DE USUARIOS
        Usuario[] usuarios = {
                new Usuario(1, "Camila Gonzalez", "camila@gmail.com"),
                new Usuario(2, "Santigo Choque", "santi10@hotmail.com"),
                new Usuario(3, "Florencia Gomez", "florencia_go@hotmail.com"),
                new Usuario(4, "Emmanuel Martin", "emma.martin@hotmail.com"),
        };

        // LISTA DE RECURSOS
        RecursoDigital[] recursos = {
                new Libro(101, "El Quijote", TipoEstado.DISPONIBLE, "Cervantes", 500),
                new Libro(102, "Harry Potter", TipoEstado.DISPONIBLE, "J.K.Rowling", 350),
                new Revista(201, "National Geographic", TipoEstado.PRESTADO, "Varios", 130),
                new Revista(202, "Caras", TipoEstado.DISPONIBLE, "Varios", 20),
                new AudioLibro(303, "1984", TipoEstado.RESERVADO, "Orwell", 360),
                new AudioLibro(304, "El señor de los anillos", TipoEstado.RESERVADO, "J.R.R.Tolkien", 400),
        };

        // INYECCION: Servicio de notificaciones
        ServicioNotificaciones notificadorEmail = new ServicioNotificacionesEmail();
        ServicioNotificaciones notificadorSMS = new ServicioNotificacionesSMS();

        boolean salir = false;
        while (!salir) {
            consola.mostrarMenuPrincipal();
            System.out.print(" ⚙️ Seleccionar una opción: ");
            int opcion = consola.leerOpcion();

            switch (opcion) {
                case 1 -> {
                    consola.mostrarMenuUsuarios();
                    int opUsuarios = consola.leerOpcion();
                    if (opUsuarios == 1) {
                        Consola.mostrarUsuarios(usuarios);
                    }
                }
                case 2 -> {
                    consola.mostrarMenuRecursos();
                    int opRecursos = consola.leerOpcion();
                    if (opRecursos == 1) {
                        Consola.mostrarRecursos(recursos);

                        // Simular préstamo
                        Usuario usuario = usuarios[0]; // Camila
                        RecursoDigital recurso = recursos[1]; // Harry Potter

                        if (recurso.getEstado() == TipoEstado.DISPONIBLE) {
                            recurso.setEstado(TipoEstado.PRESTADO);
                            String mensaje = "✅ Recurso '" + recurso.getTitulo() + "' prestado con éxito.";

                            System.out.println("\n*********** NOTIFICACIONES ***********");
                            notificadorEmail.enviarNotificacion(mensaje, usuario);
                            notificadorSMS.enviarNotificacion(mensaje, usuario);
                        } else {
                            System.out.println("                             ");
                            System.out.println("❌ El libro " + recurso.getTitulo() + " no está disponible.");
                        }
                    }
                }
                case 3 -> salir = true;
                default -> System.out.println("⚠️ Opción incorrecta ⚠️. Intente de nuevo.");
            }
        }
    }
}
