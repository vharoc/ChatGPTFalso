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
    
    private static final List<String> SALUDOS = Arrays.asList("hola", "hola!", "saludos", "buenos dias", "buenas tardes", "buenas noches", "como estas?");
    private static final List<String> SALUDOS_RESPUESTA = Arrays.asList("Hola! Como estas?", "Hola, como fue tu dia?");
    private static final List<String> ESTADO = Arrays.asList("bien", "bien y tú?", "contento", "y tú?", "como estas?");
    private static final List<String> ESTADO_POSITIVO = Arrays.asList("Me alegra escucharlo. ¿Algo emocionante sucedió?", "Yo también estoy bien, ¿quieres contarme algo interesante?");
    private static final List<String> DESPEDIDAS = Arrays.asList("adios", "hasta luego", "nos vemos", "chao");
    private static final List<String> DESPEDIDAS_RESPUESTA = Arrays.asList("Hasta luego, ¡ten un buen día!", "Nos vemos. ¡Cuídate!");
    private static final List<String> AGRADECIMIENTOS = Arrays.asList("gracias", "muchas gracias", "agradecido");
    private static final List<String> AGRADECIMIENTOS_RESPUESTA = Arrays.asList("De nada, ¿en qué más puedo ayudarte?", "¡No hay problema! ¿Necesitas algo más?");
    private static final List<String> PREGUNTAS_GENERALES = Arrays.asList("que haces?", "cual es tu funcion?", "explicame algo");
    private static final List<String> RESPUESTAS_GENERALES = Arrays.asList("Soy un programa de chat creado para ayudarte. Pregúntame lo que quieras.", "Mi función es asistirte en cualquier pregunta o tarea que tengas.");
    private static final List<String> REFRANESRESPUESTA = Arrays.asList("Más vale tarde que nunca.", "No dejes para mañana lo que puedes hacer hoy.", "Cuando una puerta se cierra, otra se abre.");
    private static final List<String> REFRAN = Arrays.asList("refran", "cuentame un refran", "refran");
    private static final List<String> CONSEJOSRESPUESTA = Arrays.asList("Escucha más de lo que hablas.", "No te preocupes por cosas que no puedes controlar.", "Haz ejercicio regularmente para mantener cuerpo y mente saludables.");
    private static final List<String> CONSEJO = Arrays.asList("ns que hacer", "dame un consejo", "me preocupa");
    private static final List<String> RESPUESTAS_GENERICAS = Arrays.asList("Interesante", "¿Me puedes contar más?", "¡Eso suena bien!");
    private static final List<String> CHISTE = Arrays.asList("chiste");
    private static final List<String> CHISTE_RESPUESTA = Arrays.asList("Me sacaron del grupo de WhatsApp de paracaidismo. Se ve que no caía bien.", "Eliminar correos no deseados es muy fácil: spam comido.", "¿De qué murió Jack Sparrow? De un disparrow.","¿Cómo se despiden los químicos? Ácido un gusto.");

    
    @Override
    public String obtenerIdentificador(){
        String fake = "Agente FAKE";
        return fake;
    }
    
    
    @Override
    public String speak(String mensajeUsuario) {
        // Convertir el mensaje a minúsculas para hacer la comparación de manera insensible a mayúsculas
        mensajeUsuario = mensajeUsuario.toLowerCase();

        if (contieneCualquiera(mensajeUsuario, SALUDOS)) {
            return obtenerRespuestaAleatoria(SALUDOS_RESPUESTA);
        } else if(contieneCualquiera(mensajeUsuario, ESTADO)){
           return obtenerRespuestaAleatoria(ESTADO_POSITIVO);
        }else if (contieneCualquiera(mensajeUsuario, DESPEDIDAS)) {
            return obtenerRespuestaAleatoria(DESPEDIDAS_RESPUESTA);
        }else if(contieneCualquiera(mensajeUsuario, AGRADECIMIENTOS)){
            return obtenerRespuestaAleatoria(AGRADECIMIENTOS_RESPUESTA);
        }else if(contieneCualquiera(mensajeUsuario, PREGUNTAS_GENERALES)){
            return obtenerRespuestaAleatoria(RESPUESTAS_GENERALES);
        }else if(contieneCualquiera(mensajeUsuario, REFRAN)){
            return obtenerRespuestaAleatoria(REFRANESRESPUESTA);
        }else if(contieneCualquiera(mensajeUsuario, CONSEJO)){
            return obtenerRespuestaAleatoria(CONSEJOSRESPUESTA);
        }else if(contieneCualquiera(mensajeUsuario, CHISTE)){
            return obtenerRespuestaAleatoria(CHISTE_RESPUESTA);
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
