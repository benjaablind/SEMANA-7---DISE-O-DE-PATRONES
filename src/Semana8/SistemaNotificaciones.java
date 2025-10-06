package Semana8;
import java.util.*;

public class SistemaNotificaciones {
    private FabricaNotificaciones fabrica;
    private Map<String, Integer> estadisticas;

    public SistemaNotificaciones() {
        this.fabrica = new FabricaNotificaciones();
        this.estadisticas = new HashMap<>();
        for (FabricaNotificaciones.TipoNotificacion tipo : fabrica.getTiposDisponibles()) {
            estadisticas.put(tipo.name(), 0);
        }
    }

    public void enviarNotificacionConReintentos(FabricaNotificaciones.TipoNotificacion tipo, 
                                              String destinatario, String mensaje, int maxReintentos) {
        System.out.println("\n--- ENVIO CON REINTENTOS ---");
        System.out.println("Tipo: " + tipo + ", Destinatario: " + destinatario);
        
        for (int intento = 1; intento <= maxReintentos; intento++) {
            System.out.println("Intento " + intento + " de " + maxReintentos);
            
            try {
                Notificacion notificacion = fabrica.crearNotificacion(tipo);
                notificacion.enviar(destinatario, mensaje);
                
                if (notificacion.verificarEnvio()) {
                    estadisticas.put(tipo.name(), estadisticas.get(tipo.name()) + 1);
                    System.out.println("Envio exitoso en intento " + intento);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Error en intento " + intento + ": " + e.getMessage());
            }

            if (intento < maxReintentos) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        
        System.out.println("Fallo despues de " + maxReintentos + " intentos");
    }

    public void enviarNotificacionMasiva(FabricaNotificaciones.TipoNotificacion tipo, 
                                       List<String> destinatarios, String mensaje) {
        System.out.println("\n--- ENVIO MASIVO ---");
        System.out.println("Tipo: " + tipo + ", Destinatarios: " + destinatarios.size());
        
        int exitosos = 0;
        for (String destinatario : destinatarios) {
            try {
                Notificacion notificacion = fabrica.crearNotificacion(tipo);
                notificacion.enviar(destinatario, mensaje);
                
                if (notificacion.verificarEnvio()) {
                    exitosos++;
                }
            } catch (Exception e) {
                System.out.println("Error enviando a " + destinatario + ": " + e.getMessage());
            }
        }
        
        estadisticas.put(tipo.name(), estadisticas.get(tipo.name()) + exitosos);
        System.out.println("Envio masivo completado: " + exitosos + "/" + destinatarios.size() + " exitosos");
    }

    public void generarReporte() {
        System.out.println("\n=== REPORTE DE NOTIFICACIONES ===");
        int total = 0;
        
        for (Map.Entry<String, Integer> entry : estadisticas.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " envios");
            total += entry.getValue();
        }
        
        System.out.println("TOTAL: " + total + " envios");
    }
}