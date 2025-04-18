package Class;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorRecursos {
    private ArrayList<RecursoDigital> recursos;
    private Scanner scanner;

    public GestorRecursos() {
        recursos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void crearRecursoDesdeConsola() {
        System.out.println("📚 ¿Qué tipo de recurso desea crear?");
        System.out.println("1. Libro");
        System.out.println("2. Revista");
        System.out.println("3. Audiolibro");
        System.out.print("Ingrese una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        System.out.print("🔤 Título: ");
        String titulo = scanner.nextLine();

        System.out.print("👤 Autor: ");
        String autor = scanner.nextLine();

        System.out.print("🆔 ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        RecursoDigital nuevo = null;

        switch (opcion) {
            case 1 -> {
                System.out.print("📄 Cantidad de páginas: ");
                int paginas = scanner.nextInt();
                nuevo = new Libro(id, titulo, RecursoDigital.TipoEstado.DISPONIBLE, autor, paginas);
            }
            case 2 -> {
                System.out.print("📖 Número de edición: ");
                int edicion = scanner.nextInt();
                nuevo = new Revista(id, titulo, RecursoDigital.TipoEstado.DISPONIBLE, autor, edicion);
            }
            case 3 -> {
                System.out.print("🎧 Duración en minutos: ");
                int duracion = scanner.nextInt();
                nuevo = new AudioLibro(id, titulo, RecursoDigital.TipoEstado.DISPONIBLE, autor, duracion);
            }
            default -> {
                System.out.println("⚠️ Opción inválida.");
                return;
            }
        }

        recursos.add(nuevo);
        System.out.println("✅ Recurso creado exitosamente.");
    }

    public void mostrarRecursos() {
        if (recursos.isEmpty()) {
            System.out.println("⚠️ No hay recursos registrados.");
        } else {
            System.out.println("\n📚 Lista de recursos:");
            for (RecursoDigital r : recursos) {
                System.out.println("-------------------------");
                System.out.println(r);
            }
        }
    }

    public ArrayList<RecursoDigital> getRecursos() {
        return recursos;
    }
}
