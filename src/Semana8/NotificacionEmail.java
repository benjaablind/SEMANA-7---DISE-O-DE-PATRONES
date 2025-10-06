package Semana8;
public class NotificacionEmail implements Notificacion {
    private static final int LIMITE_CARACTERES = 2000;
    private boolean enviado;

    @Override
    public void enviar(String destinatario, String mensaje) {
        if (!validarDestinatario(destinatario)) {
            System.out.println("Email invalido: " + destinatario);
            return;
        }

        if (mensaje.length() > LIMITE_CARACTERES) {
            System.out.println("Mensaje muy largo para email. Limite: " + LIMITE_CARACTERES);
            mensaje = mensaje.substring(0, LIMITE_CARACTERES - 3) + "...";
        }

        System.out.println("Enviando Email a: " + destinatario);
        System.out.println("Asunto: Notificacion Bancaria");
        System.out.println("Mensaje: " + mensaje);
        System.out.println("[Simulacion: Email enviado exitosamente]");

        this.enviado = true;
    }

    @Override
    public String getTipo() {
        return "EMAIL";
    }

    @Override
    public boolean verificarEnvio() {
        return enviado;
    }

    private boolean validarDestinatario(String email) {
        return email != null && email.contains("@") && email.contains(".") && email.length() > 5;
    }
}