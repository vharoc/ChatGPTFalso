package modelo;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.exceptions.OllamaBaseException;
import io.github.amithkoujalgi.ollama4j.core.models.OllamaResult;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victo
 */
public class SmartLLM implements ILLM {
    
    @Override
    public String obtenerIdentificador(){
        String smart = "Agente OLLAMA";
        return smart;
    }
    
    @Override
    public String speak(String mensajeUsuario) {
        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        
        ollamaAPI.setVerbose(true);
        
        OllamaResult response = null;
        try {
            response = ollamaAPI.ask("mistral",mensajeUsuario);
        } catch (OllamaBaseException | IOException | InterruptedException ex) {
            Logger.getLogger(SmartLLM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String respuesta = response.getResponse();
                
        return respuesta;
    }
}
