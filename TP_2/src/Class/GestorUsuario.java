package Class;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.*;

public class GestorUsuario {
    private ArrayList<Usuario> usuarios;
    private Map<String, List<Usuario>> usuariosPorNombre = new HashMap<>();
    private Scanner scanner;

    public GestorUsuario() {
        usuarios = new ArrayList<>();
        scanner = new Scanner(System.in);
    }


    public void agregarUsuarioDesdeConsola() {
        System.out.println("\n ******CREAR NUEVO USUARIO******");

        System.out.print("👤 Ingresar nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("📧 Ingresar email: ");
        String email = scanner.nextLine();

        int id = usuarios.size() + 1;
        Usuario nuevo = new Usuario(id, nombre, email);
        usuarios.add(nuevo);

        usuariosPorNombre.computeIfAbsent(nombre, k -> new ArrayList<>()).add(nuevo);

        System.out.println("✅ Usuario agregado correctamente.");
    }

    public void mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("⚠️ No hay usuarios registrados.");
        } else {
            System.out.println("👥 Lista de usuarios:");
            for (Usuario u : usuarios) {
                System.out.println("-------------------------");
                System.out.println(u);
            }
        }
    }
    //Se aprendio a utilizar map con chatGPT
    public List<Usuario> buscarPorNombre(String nombre) {
        return usuariosPorNombre.getOrDefault(nombre, new ArrayList<>());
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}
