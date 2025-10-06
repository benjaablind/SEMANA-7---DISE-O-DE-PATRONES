package Semana8;
public class NotificacionSMS implements Notificacion {
    private static final int LIMITE_CARACTERES = 100;
    private boolean enviado;
    private int intentos;

    @Override
    public void enviar(String destinatario, String mensaje) {
        if (!validarDestinatario(destinatario)) {
            System.out.println("SMS invalido: " + destinatario);
            return;
        }

        if (mensaje.length() > LIMITE_CARACTERES) {
            System.out.println("Mensaje muy largo para SMS. Limite: " + LIMITE_CARACTERES);
            mensaje = mensaje.substring(0, LIMITE_CARACTERES - 3) + "...";
        }

        System.out.println("Enviando SMS a: " + destinatario);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("[Simulacion: SMS enviado exitosamente]");
        
        this.enviado = true;
        this.intentos++;
    }

    @Override
    public String getTipo() {
        return "SMS";
    }

    @Override
    public boolean verificarEnvio() {
        return enviado;
    }

    public int getIntentos() {
        return intentos;
    }

    private boolean validarDestinatario(String telefono) {
        return telefono != null && telefono.matches("^\\+[0-9]{10,15}$");
    }
}