package modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author victo
 * 
 */

public class RandomCSVLLM implements ILLM {
    
    @Override
    public String obtenerIdentificador(){
        String csv = "Agente CSV";
        return csv;
    }
    
    @Override
    public String speak(String mensajeUsuario) {
        File f = Paths.get(System.getProperty("user.home"), "OneDrive", "Escritorio", "jLLM", "input.csv").toFile();
        String delimitador = ",";
        
        try {
            List<String> lineas = Files.readAllLines(f.toPath());
            Collections.shuffle(lineas);
            for (String linea : lineas) {
                ModeloCSV respuesta = getMensajeFromDelimitedString(linea, delimitador);
                if (respuesta != null) {
                    if ((mensajeUsuario.contains("?") || mensajeUsuario.contains("curioso"))&& respuesta.getTipo().equals("pregunta")) {
                        return respuesta.getMensaje();
                    } else if (mensajeUsuario.contains("equipo") && respuesta.getTipo().equals("equipo")) {
                        return respuesta.getMensaje();
                    } else if ((mensajeUsuario.contains("respuesta") || mensajeUsuario.contains("necesito") || mensajeUsuario.contains("bien")) && respuesta.getTipo().equals("respuesta")) {
                        return respuesta.getMensaje();
                    } else if ((mensajeUsuario.contains("no") || mensajeUsuario.contains("nunca")) && respuesta.getTipo().equals("negacion")) {
                        return respuesta.getMensaje();
                    } else if ((mensajeUsuario.contains("no?") || mensajeUsuario.contains(".") || mensajeUsuario.contains("si")) && respuesta.getTipo().equals("afirmacion")) {
                        return respuesta.getMensaje();
                    } else if ((mensajeUsuario.contains("hola") || mensajeUsuario.contains("que tal")) && respuesta.getTipo().equals("saludo")) {
                        return respuesta.getMensaje();
                    } else if (mensajeUsuario.contains("despedida") && respuesta.getTipo().equals("despedida")) {
                        return respuesta.getMensaje();
                    } else if (mensajeUsuario.contains("refran") && respuesta.getTipo().equals("refran")) {
                        return respuesta.getMensaje();
                    } else if ((mensajeUsuario.contains("wow") || mensajeUsuario.contains("!")) && respuesta.getTipo().equals("sorpresa")) {
                        return respuesta.getMensaje();
                    }
                }
            }
        } catch (IOException e) {
            return "Error al leer el archivo: " + e.getMessage();
        }
        
        return "No se encontro una respuesta apropiada";
    }

    public static ModeloCSV getMensajeFromDelimitedString(String delimitedString /*linea*/, String delimiter /*,*/) {

        String[] chunks = delimitedString.split(delimiter);

        if (chunks.length != 3) {
            return null;
        }

        try {
            String tipo = chunks[0];
            int tamaño = Integer.parseInt(chunks[1]);
            String mensaje = chunks[2];
            ModeloCSV csv = new ModeloCSV(mensaje, tipo, tamaño);
            return csv;
            
        } catch (NumberFormatException e) {
            // Tomamos linea como inválida
            return null;
        }
    
    }
}
