package Class;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;


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

        System.out.print("🏷️ Categoría: ");
        String categoria = scanner.nextLine();


        int id = recursos.size() + 1;
        RecursoDigital nuevo = null;

        switch (opcion) {
            case 1 -> {
                System.out.print("📄 Cantidad de páginas: ");
                int paginas = scanner.nextInt();
                nuevo = new Libro(id, titulo, RecursoDigital.TipoEstado.DISPONIBLE, autor, categoria, paginas);
            }
            case 2 -> {
                System.out.print("📖 Número de edición: ");
                int edicion = scanner.nextInt();
                nuevo = new Revista(id, titulo, RecursoDigital.TipoEstado.DISPONIBLE, autor, categoria, edicion);
            }
            case 3 -> {
                System.out.print("🎧 Duración en minutos: ");
                int duracion = scanner.nextInt();
                nuevo = new AudioLibro(id, titulo, RecursoDigital.TipoEstado.DISPONIBLE, autor, categoria, duracion);
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
            // Ordenar la lista de recursos por título
            Collections.sort(recursos, new ComparadorPorTitulo());

            System.out.println("\n📚 Lista de recursos:");
            for (RecursoDigital r : recursos) {
                System.out.println("-------------------------");
                System.out.println(r);
            }
        }
    }
    //Se aprendio a utilizar map con chatGPT
    public void buscarRecursoPorTitulo(String tituloBuscado) {
        List<RecursoDigital> encontrados = recursos.stream()
                .filter(r -> r.getTitulo().toLowerCase().contains(tituloBuscado.toLowerCase()))
                .toList();

        if (encontrados.isEmpty()) {
            System.out.println("🔍 No se encontraron recursos con el título: " + tituloBuscado);
        } else {
            System.out.println("📄 Recursos encontrados:");
            for (RecursoDigital r : encontrados) {
                System.out.println("-------------------------");
                System.out.println(r);
            }
        }
    }
    //Se aprendio a utilizar map con chatGPT
    public void buscarPorCategoria(String categoriaBuscada) {
        List<RecursoDigital> encontrados = recursos.stream()
                .filter(r -> r.getCategoria().equalsIgnoreCase(categoriaBuscada))
                .toList();

        if (encontrados.isEmpty()) {
            System.out.println("🔍 No se encontraron recursos en la categoría: " + categoriaBuscada);
        } else {
            System.out.println("📂 Recursos encontrados en la categoría \"" + categoriaBuscada + "\":");
            for (RecursoDigital r : encontrados) {
                System.out.println("-------------------------");
                System.out.println(r);
            }
        }
    }

    public ArrayList<RecursoDigital> getRecursos() {
        return recursos;
    }
}
