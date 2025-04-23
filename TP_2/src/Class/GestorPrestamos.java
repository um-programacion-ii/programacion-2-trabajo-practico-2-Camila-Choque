package Class;
import Exceptions.RecursoNoDisponibleException;
import Interfaces.ServicioNotificaciones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GestorPrestamos {

    private List<Prestamo> prestamos = new ArrayList<>();
    private final ServicioNotificaciones notificador;

    private final GestorUsuario gestorUsuario;
    private Map<RecursoDigital, Integer> contadorRecursosPrestados = new HashMap<>();

    public GestorPrestamos(ServicioNotificaciones notificador,GestorUsuario gestorUsuario) {
        this.notificador = notificador;
        this.gestorUsuario = gestorUsuario;
        this.prestamos = new ArrayList<>();
    }
    //
    public List<Prestamo> getListaDePrestamos() {
        return prestamos;
    }


    public void prestarRecurso(Usuario usuario, RecursoDigital recurso) throws RecursoNoDisponibleException {
        synchronized (this) {
            System.out.println("🔄 Hilo " + Thread.currentThread().getName() + " procesando préstamo...");

            if (recurso.getEstado() != RecursoDigital.TipoEstado.DISPONIBLE) {
                throw new RecursoNoDisponibleException(" El recurso '" + recurso.getTitulo() + "' no está disponible.");
            }
            recurso.setEstado(RecursoDigital.TipoEstado.PRESTADO);
            Prestamo prestamo = new Prestamo(usuario, recurso, LocalDate.now());
            prestamos.add(prestamo);
            contadorRecursosPrestados.put(recurso, contadorRecursosPrestados.getOrDefault(recurso, 0) + 1);
            gestorUsuario.incrementarActividad(usuario);
            System.out.println("                     ");
            System.out.println(" Préstamo agregado:");
            System.out.println(prestamo);
            System.out.println(" Total de préstamos: " + prestamos.size());
            System.out.println("                     ");
            String mensaje = "El recurso ha sido prestado correctamente.";
            notificador.enviarNotificacion(mensaje, usuario);
        }
    }

    public void mostrarPrestamosActivos() {
        System.out.println(" Préstamos activos:");
        boolean hayPrestamosActivos = false;

        for (Prestamo p : prestamos) {
            if (p.getFechaDevolucion().isAfter(LocalDate.now())) {
                hayPrestamosActivos = true;
                System.out.println("                         ");
                System.out.println(p);
            }
        }
        if (hayPrestamosActivos) {
            System.out.println(" Total de préstamos activos: " + prestamos.size());
        } else {
            System.out.println(" No hay préstamos activos.");
        }
    }

    // DEVOLUCION: ChatGPT me ayudo con algunos problemas que tenia
    public void devolverRecurso(Usuario usuario, RecursoDigital recurso) {
        synchronized (this) {
            System.out.println("🔄 Hilo " + Thread.currentThread().getName() + " procesando devolución...");

            boolean encontrado = false;

            for (Prestamo p : prestamos) {
                System.out.println(" Verificando préstamo.... ");
                System.out.println("Usuario en préstamo: " + p.getUsuario().getNombre());
                System.out.println("Recurso en préstamo: " + p.getRecurso().getTitulo());
                System.out.println("Fecha devolución actual: " + p.getFechaDevolucion());

                if (p.getUsuario().equals(usuario) &&
                        p.getRecurso().equals(recurso) &&
                        p.getFechaDevolucion().isAfter(LocalDate.now())) {

                    recurso.setEstado(RecursoDigital.TipoEstado.DISPONIBLE);
                    p.setFechaDevolucion(LocalDate.now());

                    System.out.println("         ");
                    System.out.println("✅ Recurso devuelto correctamente.");
                    String mensaje = "El recurso ha sido devuelto correctamente.";
                    notificador.enviarNotificacion(mensaje, usuario);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("⚠️ No se encontró un préstamo activo para ese recurso.");
            }
        }
    }

    //REPORTES
    public void reporteRecursosMasPrestados() {
        System.out.println("📊 Reporte de los recursos más prestados:");

        contadorRecursosPrestados.entrySet().stream()
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()))
                .forEach(entry -> System.out.println("Recurso: " + entry.getKey().getTitulo() + " | Veces prestado: " + entry.getValue()));
    }



}
