package app;
import java.util.Scanner;

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
        System.out.println("2. Crear nuevo usuario");
        System.out.println("3. Buscar usuario por nombre");
        System.out.println("4. Volver al menú principal");
        System.out.print(" ⚙\uFE0F Seleccionar una opción: ");
    }

    public void mostrarMenuRecursos() {
        System.out.println("\uD83D\uDCDA *******MENÚ RECURSOS ******* \uD83D\uDCDA");
        System.out.println("1. Ver todos los recursos");
        System.out.println("2. Crear nuevo recurso");
        System.out.println("3. Buscar recurso por título");
        System.out.println("4. Buscar por categoría");
        System.out.println("5. Ordenar recursos por título");
        System.out.println("6. Mostrar categorías disponibles");
        System.out.println("7. Volver al menú principal");
        System.out.print(" ⚙\uFE0F Seleccionar una opción: ");
    }

    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public String leerTexto() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}