package modelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author victo
 * 
 */

public class JSON implements IRepositorio{
    
    // Path ruta = Paths.get(System.getProperty("user.home"), "Desktop", "jLLM", "output.json");
    // File f = ruta.toFile();

    @Override
    public void exportarConversacion(List<Conversacion> conversacion) {
        Path ruta = Paths.get(System.getProperty("user.home"), "OneDrive", "Escritorio", "jLLM", "output.json");
        File f = ruta.toFile();
        try {
            Gson gson = new Gson();
            String json = gson.toJson(conversacion);
            Files.write(f.toPath(), json.getBytes(StandardCharsets.UTF_8));
            
        } catch (IOException ex) {
            System.err.println("Error:" + ex.getMessage());
        }
    }
    
    @Override
    public List<Conversacion> importarConversacion() {
        Path ruta = Paths.get(System.getProperty("user.home"), "OneDrive", "Escritorio", "jLLM", "input.json");
        File f = ruta.toFile();
        try {
            Gson gson = new Gson();
            String json = new String(Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8);
            // Obtiene el tipo de la lista
            Type tipoDeLista = new TypeToken<List<Conversacion>>() {}.getType();
            return gson.fromJson(json, tipoDeLista);
        } catch (IOException ex) {
            // TODO veremos como cambiar esto cuando tratemos excepciones
            // de momento retornaremos null si hay algún problema
            System.err.println("Error:" + ex.getMessage());
            return null;
        }
    }
    
    
}
