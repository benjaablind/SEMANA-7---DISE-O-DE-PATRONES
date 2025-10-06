package Semana8;
public class NotificacionWhatsApp implements Notificacion {
    private static final int LIMITE_CARACTERES = 1000;
    private boolean enviado;

    @Override
    public void enviar(String destinatario, String mensaje) {
        if (!validarDestinatario(destinatario)) {
            System.out.println("Numero de WhatsApp invalido: " + destinatario);
            return;
        }

        if (mensaje.length() > LIMITE_CARACTERES) {
            System.out.println("Mensaje muy largo para WhatsApp. Limite: " + LIMITE_CARACTERES);
            mensaje = mensaje.substring(0, LIMITE_CARACTERES - 3) + "...";
        }

        System.out.println("Enviando WhatsApp a: " + destinatario);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("[Simulacion: Mensaje de WhatsApp enviado]");
        
        this.enviado = true;
    }

    @Override
    public String getTipo() {
        return "WHATSAPP";
    }

    @Override
    public boolean verificarEnvio() {
        return enviado;
    }

    private boolean validarDestinatario(String telefono) {
        return telefono != null && telefono.matches("^\\+[0-9]{10,15}$");
    }
}