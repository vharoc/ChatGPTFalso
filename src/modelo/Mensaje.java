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
    private String fechaHora; // String o long

    public Mensaje(String remitente, String contenido, String fechaHora) {
        this.remitente = remitente;
        this.contenido = contenido;
        this.fechaHora = fechaHora;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    
}
