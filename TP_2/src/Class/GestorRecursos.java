package Class;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;
import Enum.CategoriaRecurso;
import Exceptions.RecursoNoDisponibleException;


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

        CategoriaRecurso categoria = asignarCategoriaDesdeConsola();


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

    public RecursoDigital buscarRecursoPorTitulo(String tituloBuscado) throws RecursoNoDisponibleException {
        return recursos.stream()
                .filter(r -> r.getTitulo().equalsIgnoreCase(tituloBuscado))
                .findFirst()
                .orElseThrow(() -> new RecursoNoDisponibleException("❌ No se encontró un recurso con el título: " + tituloBuscado));
    }

    //Se aprendio a utilizar map con chatGPT
    public void buscarPorCategoria(String categoriaBuscada) {
        List<RecursoDigital> encontrados = recursos.stream()
                .filter(r -> r.getCategoria().name().equalsIgnoreCase(categoriaBuscada))
                .toList();

        if (encontrados.isEmpty()) {
            System.out.println("⚠️ No se encontraron recursos en la categoría: " + categoriaBuscada);
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
    private CategoriaRecurso asignarCategoriaDesdeConsola() {
        System.out.println("📚 Seleccione la categoría del recurso:");
        CategoriaRecurso[] categorias = CategoriaRecurso.values();
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + ". " + categorias[i]);
        }

        int opcion;
        do {
            System.out.print("Ingrese una opción válida (1-" + categorias.length + "): ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
        } while (opcion < 1 || opcion > categorias.length);

        return categorias[opcion - 1];


    }

    public void mostrarCategoriasDisponibles() {
        System.out.println("📚 Recursos disponibles por categoría:\n");

        boolean hayLibros = false, hayRevistas = false, hayAudioLibros = false;

        System.out.println("🔹 Libros disponibles:");
        for (RecursoDigital r : recursos) {
            if (r instanceof Libro && r.getEstado() == RecursoDigital.TipoEstado.DISPONIBLE) {
                System.out.println(r);
                hayLibros = true;
            }
        }
        if (!hayLibros) System.out.println("  ❌ No hay libros disponibles.");

        System.out.println("\n🔹 Revistas disponibles:");
        for (RecursoDigital r : recursos) {
            if (r instanceof Revista && r.getEstado() == RecursoDigital.TipoEstado.DISPONIBLE) {
                System.out.println(r);
                hayRevistas = true;
            }
        }
        if (!hayRevistas) System.out.println("  ❌ No hay revistas disponibles.");

        System.out.println("\n🔹 Audiolibros disponibles:");
        for (RecursoDigital r : recursos) {
            if (r instanceof AudioLibro && r.getEstado() == RecursoDigital.TipoEstado.DISPONIBLE) {
                System.out.println(r);
                hayAudioLibros = true;
            }
        }
        if (!hayAudioLibros) System.out.println("  ❌ No hay audiolibros disponibles.");
    }


    private void prestarRecurso(RecursoDigital recurso) throws RecursoNoDisponibleException {
        if (recurso.getEstado() != RecursoDigital.TipoEstado.DISPONIBLE) {
            throw new RecursoNoDisponibleException("❌ El recurso '" + recurso.getTitulo() + "' no está disponible.");
        }

        recurso.setEstado(RecursoDigital.TipoEstado.PRESTADO);
        System.out.println("✅ Recurso prestado exitosamente.");
    }

    //PUEDE LLEGAR A MODIFICARSE CUANDO SE IMPLEMENTE PRESTAMOS
    public void prestarRecursoDesdeConsola() {
        if (recursos.isEmpty()) {
            System.out.println("⚠️ No hay recursos disponibles para prestar.");
            return;
        }
        System.out.println("📋 Recursos disponibles para préstamo:");
        List<RecursoDigital> disponibles = new ArrayList<>();

        int index = 1;
        for (RecursoDigital r : recursos) {
            if (r.getEstado() == RecursoDigital.TipoEstado.DISPONIBLE) {
                System.out.println(index + ". " + r.getTitulo());
                disponibles.add(r);
                index++;
            }
        }
        if (disponibles.isEmpty()) {
            System.out.println("❌ No hay recursos en estado disponible.");
            return;
        }
        System.out.print("➡️ Seleccione el número del recurso que desea prestar: ");
        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (seleccion < 1 || seleccion > disponibles.size()) {
            System.out.println("❌ Selección inválida.");
            return;
        }
        RecursoDigital recurso = disponibles.get(seleccion - 1);
        try {
            prestarRecurso(recurso);
        } catch (RecursoNoDisponibleException e) {
            System.out.println(e.getMessage());
        }
    }



}
