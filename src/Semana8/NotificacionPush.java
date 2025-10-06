package Semana8;
public class NotificacionPush implements Notificacion {
    private static final int LIMITE_CARACTERES = 200;
    private boolean enviado;

    @Override
    public void enviar(String destinatario, String mensaje) {
        if (!validarDestinatario(destinatario)) {
            System.out.println("Token push invalido: " + destinatario);
            return;
        }

        if (mensaje.length() > LIMITE_CARACTERES) {
            System.out.println("Mensaje muy largo para Push. Limite: " + LIMITE_CARACTERES);
            mensaje = mensaje.substring(0, LIMITE_CARACTERES - 3) + "...";
        }

        System.out.println("Enviando Push a dispositivo: " + destinatario.substring(0, 10) + "...");
        System.out.println("Titulo: Notificacion Bancaria");
        System.out.println("Mensaje: " + mensaje);

        simularEnvioAsincrono();
        
        System.out.println("[Simulacion: Notificacion Push enviada]");
        this.enviado = true;
    }

    @Override
    public String getTipo() {
        return "PUSH";
    }

    @Override
    public boolean verificarEnvio() {
        return enviado;
    }

    private boolean validarDestinatario(String deviceToken) {
        return deviceToken != null && deviceToken.length() >= 10 && deviceToken.matches("^[a-zA-Z0-9_]+$");
    }

    private void simularEnvioAsincrono() {
        try {
            System.out.println("[Simulando envio asincrono...]");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Envio asincrono interrumpido");
        }
    }
}