package Class;
import java.util.Scanner;
public class Consola {
    static Scanner scanner = new Scanner(System.in);

    public static void mostrar(String mensaje) {
        System.out.println(mensaje);
    }

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(scanner.nextLine());
    }
}
