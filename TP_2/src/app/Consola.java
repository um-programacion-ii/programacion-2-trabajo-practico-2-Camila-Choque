package app;
import java.util.Scanner;
import Class.Usuario;
import Class.RecursoDigital;

public class Consola {

    //Se implemento con ChatGPT y se realizaron modificaciones personales

    private final Scanner scanner;

    public Consola() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        System.out.println("\n\uD83C\uDF1F ******* MENÚ PRINCIPAL ******* \uD83C\uDF1F ");
        System.out.println("                                 ");
        System.out.println("1. Usuarios");
        System.out.println("2. Recursos");
        System.out.println("3. Salir");

    }

    public void mostrarMenuUsuarios() {
        System.out.println(" \uD83D\uDC64 ******* MENÚ USUARIOS ******* \uD83D\uDC64");
        System.out.println("1. Ver usuarios");
        System.out.println("2. Volver al menú principal");
        System.out.print(" ⚙\uFE0F Seleccionar una opción: ");
    }

    public void mostrarMenuRecursos() {
        System.out.println("\uD83D\uDCDA *******MENÚ RECURSOS ******* \uD83D\uDCDA");
        System.out.println("1. Ver todos los recursos");
        System.out.println("2. Volver al menú principal");
        System.out.print(" ⚙\uFE0F Seleccionar una opción: ");
    }

    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void mostrarUsuarios(Usuario[] usuarios) {
        System.out.println("\n \uD83D\uDC64  Lista de usuarios:");
        for (Usuario u : usuarios) {
            System.out.println("-------------------------");
            System.out.println(u);

        }
    }

    public static void mostrarRecursos(RecursoDigital[] recursos) {
        System.out.println("\n \uD83D\uDDC2\uFE0F  Recursos disponibles:");
        for (RecursoDigital r : recursos) {
            System.out.println("-------------------------");
            System.out.println("\n" + r);

        }
    }
}

