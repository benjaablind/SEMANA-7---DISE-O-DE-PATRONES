package Semana8;
import java.util.Arrays;
import java.util.List;

public class FabricaNotificaciones {
    public enum TipoNotificacion {
        EMAIL, SMS, PUSH, WHATSAPP
    }

    public Notificacion crearNotificacion(TipoNotificacion tipo) {
        switch (tipo) {
            case EMAIL:
                return new NotificacionEmail();
            case SMS:
                return new NotificacionSMS();
            case PUSH:
                return new NotificacionPush();
            case WHATSAPP:
                return new NotificacionWhatsApp();
            default:
                throw new IllegalArgumentException("Tipo de notificacion no soportado: " + tipo);
        }
    }

    public Notificacion crearNotificacion(String tipo) {
        try {
            TipoNotificacion tipoEnum = TipoNotificacion.valueOf(tipo.toUpperCase());
            return crearNotificacion(tipoEnum);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de notificacion no valido: " + tipo);
        }
    }

    public List<TipoNotificacion> getTiposDisponibles() {
        return Arrays.asList(TipoNotificacion.values());
    }
}