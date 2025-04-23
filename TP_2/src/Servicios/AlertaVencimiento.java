package Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import Class.Prestamo;
import Interfaces.ServicioNotificaciones;

 //IMPORTANTE: Para que llegue la notificacion se debe cambiar la fecha de devolucion de prestamos a 1 !!

public class AlertaVencimiento {
    private List<Prestamo> prestamos;
    private ServicioNotificaciones servicioNotificaciones;

    public AlertaVencimiento(List<Prestamo> prestamos, ServicioNotificaciones servicioNotificaciones) {
        this.prestamos = prestamos;
        this.servicioNotificaciones = servicioNotificaciones;
    }

    public void verificarVencimientos() {
        LocalDate hoy = LocalDate.now();
        LocalDate manana = hoy.plusDays(1);
        Scanner scanner = new Scanner(System.in);


        for (Prestamo prestamo : prestamos) {
            if (prestamo.getFechaDevolucion().isEqual(manana)) {
                String mensaje = "\n¡ALERTA VENCIMIENTO!: El préstamo del usuario " + prestamo.getUsuario().getNombre() + " con el recurso " + prestamo.getRecurso().getTitulo() + " vence mañana.";
                servicioNotificaciones.enviarNotificacion(mensaje, prestamo.getUsuario());

                System.out.print("¿Desea renovar el préstamo? (s/n): ");
                String respuesta = scanner.nextLine().trim().toUpperCase();
                if (respuesta.equals("S")) {
                    System.out.print("¿Cuántos días adicionales desea renovar?: ");
                    int dias = Integer.parseInt(scanner.nextLine().trim());
                    prestamo.renovar(dias);
                    servicioNotificaciones.enviarNotificacion("El préstamo ha sido renovado por " + dias + " días adicionales.", prestamo.getUsuario());
                } else if (respuesta.equals("N")) {
                    System.out.println("No se renovó el préstamo.");
                } else {
                    System.out.println("Opción no válida. El préstamo no se renovó.");
                }
            }
        }
    }

}
