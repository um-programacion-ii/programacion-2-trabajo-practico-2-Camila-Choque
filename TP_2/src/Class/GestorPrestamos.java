package Class;
import Exceptions.RecursoNoDisponibleException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class GestorPrestamos {

    private List<Prestamo> prestamos;

    public GestorPrestamos() {
        prestamos = new ArrayList<>();
    }


    //PEDIR
    public void prestarRecurso(Usuario usuario, RecursoDigital recurso) throws RecursoNoDisponibleException {
        if (recurso.getEstado() != RecursoDigital.TipoEstado.DISPONIBLE) {
            throw new RecursoNoDisponibleException("❌ El recurso '" + recurso.getTitulo() + "' no está disponible.");
        }
        recurso.setEstado(RecursoDigital.TipoEstado.PRESTADO);
        Prestamo prestamo = new Prestamo(usuario, recurso, LocalDate.now());
        prestamos.add(prestamo);
        System.out.println("✅ Recurso prestado exitosamente a " + usuario.getNombre());
        System.out.println("                     " );
        System.out.println("📋 Préstamo agregado: " );
        System.out.println(prestamo);
        System.out.println("📋 Total de préstamos: " + prestamos.size());
    }
    //ACTIVOS
    public void mostrarPrestamosActivos() {
        System.out.println("📋 Préstamos activos:");
        boolean hayPrestamosActivos = false;
        for (Prestamo p : prestamos) {
            if (p.getFechaDevolucion() == null) {
                System.out.println("-------------------------");
                System.out.println(p);
                System.out.println("Fecha de préstamo: " + p.getFechaPrestamo());
            }
        }
        if (!hayPrestamosActivos) {
            System.out.println("📋 Total de préstamos activos: " + prestamos.size());
        } else {
            System.out.println("⚠️ No hay préstamos activos.");

        }
    }
    // DEVOLUCION: ChatGPT me ayudo con algunos problemas que tenia
    public void devolverRecurso(Usuario usuario, RecursoDigital recurso) {
        boolean encontrado = false;
        for (Prestamo p : prestamos) {
            System.out.println("🔍 Verificando préstamo: " );
            System.out.println("-----------");
            System.out.println(p);
            if (p.getRecurso().getTitulo().equals(recurso.getTitulo()) &&
                    p.getUsuario().getNombre().equals(usuario.getNombre()) &&
                    p.getFechaDevolucion() == null) {
                recurso.setEstado(RecursoDigital.TipoEstado.DISPONIBLE);
                p.setFechaDevolucion(LocalDate.now());
                System.out.println("🔁 Recurso devuelto correctamente.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("⚠️ No se encontró un préstamo activo para ese recurso.");
        }
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
}
