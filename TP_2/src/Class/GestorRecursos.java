package Class;
import java.util.*;
import Enum.CategoriaRecurso;
import Exceptions.RecursoNoDisponibleException;


public class GestorRecursos {
    private ArrayList<RecursoDigital> recursos =  new ArrayList<>();
    private Scanner scanner;
    private Map<CategoriaRecurso, Integer> contadorPorCategoria = new HashMap<>();


    public GestorRecursos() {
        scanner = new Scanner(System.in);

    }

    public void crearRecursoDesdeConsola() {
        System.out.println(" ¿Qué tipo de recurso desea crear?");
        System.out.println("1. Libro");
        System.out.println("2. Revista");
        System.out.println("3. Audiolibro");
        System.out.print(" Ingrese una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        System.out.print(" Título: ");
        String titulo = scanner.nextLine();

        System.out.print(" Autor: ");
        String autor = scanner.nextLine();

        CategoriaRecurso categoria = asignarCategoriaDesdeConsola();


        int id = recursos.size() + 1;
        RecursoDigital nuevo = null;

        switch (opcion) {
            case 1 -> {
                System.out.print(" Cantidad de páginas: ");
                int paginas = scanner.nextInt();
                nuevo = new Libro(id, titulo, RecursoDigital.TipoEstado.DISPONIBLE, autor, categoria, paginas);
            }
            case 2 -> {
                System.out.print(" Número de edición: ");
                int edicion = scanner.nextInt();
                nuevo = new Revista(id, titulo, RecursoDigital.TipoEstado.DISPONIBLE, autor, categoria, edicion);
            }
            case 3 -> {
                System.out.print(" Duración en minutos: ");
                int duracion = scanner.nextInt();
                nuevo = new AudioLibro(id, titulo, RecursoDigital.TipoEstado.DISPONIBLE, autor, categoria, duracion);
            }
            default -> {
                System.out.println("  Opción inválida.");
                return;
            }
        }


        recursos.add(nuevo);
        contadorPorCategoria.put(categoria, contadorPorCategoria.getOrDefault(categoria, 0) + 1);
        System.out.println("✅ Recurso creado exitosamente.");
    }

    public void mostrarRecursos() {
        if (recursos.isEmpty()) {
            System.out.println("  No hay recursos registrados.");
        } else {
            // Ordenar la lista de recursos por título
            Collections.sort(recursos, new ComparadorPorTitulo());

            System.out.println(" Lista de recursos:");
            for (RecursoDigital r : recursos) {
                System.out.println("               ");
                System.out.println(r);
            }
        }
    }

    public RecursoDigital buscarRecursoPorTitulo(String tituloBuscado) throws RecursoNoDisponibleException {
        System.out.println("Buscando recurso con título: " + tituloBuscado);

        return recursos.stream()
                .filter(r -> r.getTitulo().equalsIgnoreCase(tituloBuscado))
                .findFirst()
                .orElseThrow(() -> new RecursoNoDisponibleException("No se encontró un recurso con el título: " + tituloBuscado));
    }

    //Se aprendio a utilizar map con chatGPT
    public void buscarPorCategoria(String categoriaBuscada) {
        List<RecursoDigital> encontrados = recursos.stream()
                .filter(r -> r.getCategoria().name().equalsIgnoreCase(categoriaBuscada))
                .toList();

        if (encontrados.isEmpty()) {
            System.out.println(" No se encontraron recursos en la categoría: " + categoriaBuscada);
        } else {
            System.out.println(" Recursos encontrados en la categoría " + categoriaBuscada + ":");
            for (RecursoDigital r : encontrados) {
                System.out.println("                       ");
                System.out.println(r);
            }
        }
    }


    private CategoriaRecurso asignarCategoriaDesdeConsola() {
        System.out.println(" Seleccione la categoría del recurso:");
        CategoriaRecurso[] categorias = CategoriaRecurso.values();
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + ". " + categorias[i]);
        }
        int opcion;
        do {
            System.out.print(" Ingrese una opción válida (1-" + categorias.length + "): ");
            opcion = scanner.nextInt();
            scanner.nextLine();
        } while (opcion < 1 || opcion > categorias.length);
        return categorias[opcion - 1];


    }

    public void mostrarCategoriasDisponibles() {
        System.out.println(" Recursos disponibles por categoría: ");

        boolean hayLibros = false, hayRevistas = false, hayAudioLibros = false;

        System.out.println(" Libros disponibles:");
        for (RecursoDigital r : recursos) {
            if (r instanceof Libro && r.getEstado() == RecursoDigital.TipoEstado.DISPONIBLE) {
                System.out.println(r);
                hayLibros = true;
            }
        }
        if (!hayLibros) System.out.println(" No hay libros disponibles.");

        System.out.println(" Revistas disponibles:");
        for (RecursoDigital r : recursos) {
            if (r instanceof Revista && r.getEstado() == RecursoDigital.TipoEstado.DISPONIBLE) {
                System.out.println(r);
                hayRevistas = true;
            }
        }
        if (!hayRevistas) System.out.println(" No hay revistas disponibles.");

        System.out.println(" Audiolibros disponibles:");
        for (RecursoDigital r : recursos) {
            if (r instanceof AudioLibro && r.getEstado() == RecursoDigital.TipoEstado.DISPONIBLE) {
                System.out.println(r);
                hayAudioLibros = true;
            }
        }
        if (!hayAudioLibros) System.out.println(" No hay audiolibros disponibles.");
    }
    //REPORTES
    public void mostrarEstadisticasPorCategoria() {
        System.out.println("📊 Recursos creados por categoría:");
        for (Map.Entry<CategoriaRecurso, Integer> entry : contadorPorCategoria.entrySet()) {
            System.out.println("• " + entry.getKey() + ": " + entry.getValue() + " recurso(s)");
        }
    }

}
