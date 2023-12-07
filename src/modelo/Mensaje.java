package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author victo
 */
public class Mensaje {

    private String remitente;
    private String contenido;
    private LocalDateTime fechaHora;

    public Mensaje(String remitente, String contenido) {
        this.remitente = remitente;
        this.contenido = contenido;
        this.fechaHora = LocalDateTime.now();
    }

    public String mensajeFormato() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        String fechaHoraFormato = fechaHora.format(formato);
        return String.format("%s [%s]: %s", fechaHoraFormato, remitente, contenido);
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    
}
