package Semana8;
import java.util.Arrays;
import java.util.List;

public class PruebaSistemaNotificaciones {
    public static void main(String[] args) {

        System.out.println("1. PROBANDO FABRICA DE NOTIFICACIONES");
        FabricaNotificaciones fabrica = new FabricaNotificaciones();
        
        try {
            Notificacion email = fabrica.crearNotificacion(FabricaNotificaciones.TipoNotificacion.EMAIL);
            Notificacion sms = fabrica.crearNotificacion("SMS");
            Notificacion push = fabrica.crearNotificacion(FabricaNotificaciones.TipoNotificacion.PUSH);
            Notificacion whatsapp = fabrica.crearNotificacion("WHATSAPP");
            
            System.out.println("Fabrica implementada correctamente");
            System.out.println("Tipos disponibles: " + fabrica.getTiposDisponibles());
        } catch (Exception e) {
            System.out.println("Error en la fabrica: " + e.getMessage());
        }

        System.out.println("\n2. PROBANDO SISTEMA BANCARIO");
        SistemaNotificaciones sistema = new SistemaNotificaciones();

        sistema.enviarNotificacionConReintentos(
            FabricaNotificaciones.TipoNotificacion.SMS,
            "+1234567890",
            "Su transferencia de $1,000.00 fue exitosa. Ref: TX9856H32",
            3
        );

        System.out.println("\n3. NOTIFICACION MASIVA");
        List<String> clientes = Arrays.asList(
            "cliente1@banco.com",
            "+1234567890",
            "device_token_abc123",
            "cliente2@banco.com"
        );
        
        sistema.enviarNotificacionMasiva(
            FabricaNotificaciones.TipoNotificacion.EMAIL,
            clientes,
            "Nuevo producto disponible: Cuenta Premium con beneficios exclusivos"
        );

        System.out.println("\n4. REPORTE FINAL");
        sistema.generarReporte();

    }
}