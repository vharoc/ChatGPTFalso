package modelo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author victo
 * 
 **/

public class FakeLLM implements ILLM {
    private static final List<String> SALUDOS = Arrays.asList("hola", "hola!", "saludos", "buenos días", "buenas tardes", "buenas noches", "como estas?");
    private static final List<String> CONSEJOS = Arrays.asList("ten cuidado", "sigue así", "no te preocupes demasiado", "disfruta el momento");
    private static final List<String> RESPUESTAS_GENERICAS = Arrays.asList("Interesante", "¿Me puedes contar más?", "¡Eso suena bien!");

    
    @Override
    public String obtenerIdentificador(){
        String fake = "fake";
        return fake;
    }
    
    
    @Override
    public String speak(String mensajeUsuario) {
        // Convertir el mensaje a minúsculas para hacer la comparación de manera insensible a mayúsculas
        mensajeUsuario = mensajeUsuario.toLowerCase();

        if (contieneCualquiera(mensajeUsuario, SALUDOS)) {
            return obtenerRespuestaAleatoria(SALUDOS);
        } else if (contieneCualquiera(mensajeUsuario, CONSEJOS)) {
            return obtenerRespuestaAleatoria(CONSEJOS);
        } else {
            return obtenerRespuestaAleatoria(RESPUESTAS_GENERICAS);
        }
    }

    private boolean contieneCualquiera(String mensaje, List<String> palabrasClave) {
        return palabrasClave.stream().anyMatch(mensaje::contains);
    }

    private String obtenerRespuestaAleatoria(List<String> respuestas) {
        Random random = new Random();
        int indiceRespuesta = random.nextInt(respuestas.size());
        return respuestas.get(indiceRespuesta);
    }
}
