package Semana8;
public interface Notificacion {
    void enviar(String destinatario, String mensaje);
    String getTipo();
    boolean verificarEnvio();
}
