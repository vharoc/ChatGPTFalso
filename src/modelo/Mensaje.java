package modelo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author victo
 * 
 */

public class Mensaje implements Serializable{

    private String remitente;
    private String contenido;
    private String fechaHora; // String o long
    
    public Mensaje(){
        
    }
    
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
    
    public static String getMensajeFromDelimitedString(List<Mensaje> mensajes) {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        for (Mensaje mensaje : mensajes) {
            stringBuilder.append(mensaje.getRemitente()).append(",");
            stringBuilder.append(mensaje.getContenido()).append(",");
            stringBuilder.append(mensaje.getFechaHora()).append(";");
        } 

        return stringBuilder.toString();
    }
}
