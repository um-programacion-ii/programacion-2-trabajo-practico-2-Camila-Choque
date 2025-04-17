package app;
import Class.Consola;
import Class.Usuario;

public class Main {
    public static void main(String[] args) {
        int id = Consola.leerEntero("ID: ");
        String nombre = Consola.leerTexto("Nombre: ");
        String email = Consola.leerTexto("Email: ");

        Usuario u = new Usuario(id, nombre, email);
        System.out.println("-----DATOS USUARIO-----");
        System.out.println(u);

    }
}
