package modelo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class XML implements IRepositorio{
    
    // Path ruta = Paths.get(System.getProperty("user.home"), "Desktop", "jLLM", "output.json");
    // File f = ruta.toFile();
    
    @Override
    public void exportarConversacion(List<Conversacion> conversacion) {
        Path ruta = Paths.get(System.getProperty("user.home"), "Desktop", "jLLM", "output.xml");
        File f = ruta.toFile();
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(conversacion);
            Files.write(f.toPath(), xml.getBytes(StandardCharsets.UTF_8));
        
        } catch (JsonProcessingException ex) {
            // TODO veremos como cambiar esto cuando tratemos excepciones
            // de momento retornaremos null si hay algún problema
            System.err.println("Error:" + ex.getMessage());

        } catch (IOException ex) {
            // Lo mismo aquí
            System.err.println("Error:" + ex.getMessage());
        }
    }
    
    @Override
    public List<Conversacion> importarConversacion() {
        Path ruta = Paths.get(System.getProperty("user.home"), "Desktop", "jLLM", "input.xml");
        File f = ruta.toFile();
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = new String(Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8);
            // Utiliza TypeFactory para obtener el tipo de lista correcto
            return xmlMapper.readValue(xml, xmlMapper.getTypeFactory().constructCollectionType(List.class, Conversacion.class));
        } catch (IOException ex) {
            // TODO veremos como cambiar esto cuando tratemos excepciones
            // de momento retornaremos null si hay algún problema
            System.err.println("Error:" + ex.getMessage());
            return null;
        }
    }
}
