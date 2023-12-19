package vista;

import com.coti.tools.Esdia;
import static com.coti.tools.Esdia.readInt;
import static com.coti.tools.Esdia.readString_ne;
import io.github.jonelo.jAdapterForNativeTTS.engines.SpeechEngine;
import io.github.jonelo.jAdapterForNativeTTS.engines.SpeechEngineNative;
import io.github.jonelo.jAdapterForNativeTTS.engines.Voice;
import io.github.jonelo.jAdapterForNativeTTS.engines.VoicePreferences;
import io.github.jonelo.jAdapterForNativeTTS.engines.exceptions.SpeechEngineCreationException;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Conversacion;
import modelo.Mensaje;

/**
 *
 * @author victo
 * 
 */
      
public class ConsolaVozVista extends AplicacionVista {
    
    public void voz(String texto) throws IOException, InterruptedException{
        try{
            SpeechEngine speechEngine = SpeechEngineNative.getInstance();
            List<Voice> voices = speechEngine.getAvailableVoices();
                    
            VoicePreferences voicePreferences = new VoicePreferences();
            voicePreferences.setLanguage("es"); 
            voicePreferences.setCountry("ES"); 
            voicePreferences.setGender(VoicePreferences.Gender.FEMALE);
            Voice voice = speechEngine.findVoiceByPreferences(voicePreferences);
            
            if(voice == null){
                voice = voices.get(0);
            }
            
            speechEngine.setVoice(voice.getName());
            speechEngine.say(texto);
           
            try{
                Thread.sleep(texto.length() * 100);
            }catch(InterruptedException e){
                System.err.println(e.getMessage());
            }
            
        }catch (SpeechEngineCreationException e) {
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public void mostrarInicioAplicacion(String infoInicio){
        try {
            System.out.println(infoInicio);
            try {
                voz(infoInicio);
            } catch (InterruptedException ex) {
                Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void mostrarMenuPrincipal(){
        int i = 0;
        int opcion;
        do {
            if(i==0){
               System.out.println("\n--- MENU CONSOLA VOZ ---");
                System.out.println("1. Nueva conversacion");

                try {
                    voz("1. Nueva conversacion");                
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("2. Listar/Eliminar conversaciones");

                try {
                    voz("2. Listar/Eliminar conversaciones");                
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("3. Exportacion chats");

                try {
                    voz("3. Exportacion chats");                
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("4. Salir");

                try {
                    voz("4. Salir");
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("");

                try {
                    voz("Ingrese una opción: ");

                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                } 
                
            }
            
            if(i==1){
                System.out.println("\n--- MENU CONSOLA VISTA ---");
                System.out.println("1. Nueva conversacion");
                System.out.println("2. Listar/Eliminar conversaciones");
                System.out.println("3. Exportacion/Importacion chats");
                System.out.println("4. Salir");
            }
            
            opcion = readInt("Ingrese una opcion: ");
            i = 1;
            limpiarPantalla();
            
            switch (opcion) {
                case 1:
                    nuevaConversacion();
                    break;
                case 2:
                    mostrarConversaciones();
                    break;
                case 3:
                    importarExportarChats();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
        
    }
    
    @Override
    public void mostrarFinPrograma(String infoFin){
        System.out.println(infoFin);
    }
    
    
    public void nuevaConversacion(){

        int i = 0;
        
        String illm = c.obtenerIdentificador();
        List<Mensaje> nuevoMsg = new ArrayList<>();
        Mensaje nuevo = null;
        
        long fechaInicio = Instant.now().getEpochSecond();
                
        while (true) {
            
            if (i == 0){
                long fecha = Instant.now().getEpochSecond();
                String formato = c.formatearFecha(fecha);
                
                String mensaje = readString_ne("Yo [" + formato + "]: ");

                if ("/salir".equals(mensaje) || "/".equals(mensaje)) {
                    break;
                }
                
                nuevo = c.crearMensaje("Yo", mensaje, formato);
                nuevoMsg.add(nuevo);
                
                i = 1;
            }
            
            if ( i == 1 ){
                
                long fecha2 = Instant.now().getEpochSecond();
                String formato2 = c.formatearFecha(fecha2);
                
                String m = c.getContenidoMensaje(nuevo);
                
                String respuesta = c.respuestaBot(m);
                
                System.out.println(illm + " [" + formato2 + "]: " + respuesta);
                
                try {
                    voz(respuesta);
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                nuevoMsg.add(c.crearMensaje(illm, respuesta, formato2));
                                                
                i = 0;
            }
           
        }
        
        long fechaFin = Instant.now().getEpochSecond();
        
        Conversacion nuevaConv = c.crearConversacion(nuevoMsg, illm, fechaInicio, fechaFin);
        
        c.agregarConversacionAConversaciones(nuevaConv);
        
        limpiarPantalla();
        
    }
        
    int j = 0; 
    
    public void mostrarConversaciones() {
        
        if(c.obtenerConversaciones().size() <= 0){
            System.out.println("No existen conversaciones, debes crear o importar una al menos");
            try {
                voz("No existen conversaciones, debes crear o importar una al menos");
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("Lista de conversaciones:");
            
            List<Conversacion> conversaciones = c.obtenerConversaciones();

            for (int i = 0; i < conversaciones.size(); i++) {

                Conversacion conversacion = conversaciones.get(i);

                long epoch = c.obtenerEpoch(conversacion);
                int numeroMensajes = c.obtenerMensajes(conversacion).size();
                String primeros20Caracteres = obtenerPrimeros20Caracteres(conversacion);

                System.out.println(String.format("%d. %d | %d | %s", i, epoch, numeroMensajes, primeros20Caracteres)); 

            }
            
            try {
                voz("Lista de conversaciones");
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int opcion; 
            
            do{
                if(j == 0){
                    System.out.println("");
                    System.out.println("===================================================");
                    System.out.println("           MENU SELENCCION OPCIONES                ");
                    System.out.println("===================================================");
                    System.out.println("0. MOSTRAR TODOS LOS MENSAJES DE UNA CONVERSACION");
                    try {
                        voz("0. MOSTRAR TODOS LOS MENSAJES DE UNA CONVERSACION");
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("1. ELIMINAR UNA CONVERSACION");
                    try {
                        voz("1. ELIMINAR UNA CONVERSACION");
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("2. SALIR");
                    try {
                        voz("2. SALIR");
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if(j == 1){
                    System.out.println("");
                    System.out.println("===================================================");
                    System.out.println("           MENU SELENCCION OPCIONES                ");
                    System.out.println("===================================================");
                    System.out.println("0. MOSTRAR TODOS LOS MENSAJES DE UNA CONVERSACION");
                    System.out.println("1. ELIMINAR UNA CONVERSACION");
                    System.out.println("2. SALIR");
                }
                
                j = 1;
                opcion = readInt("OPCION ... ");
                System.out.println("");
                switch(opcion){
                    case 0:
                        System.out.println("De que conversacion quieres ver los mensajes?: ");
                        try {
                            voz("De que conversacion quieres ver los mensajes?: ");
                        } catch (IOException | InterruptedException ex) {
                            Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                        int opcionConversacion = readInt("");
                            
                            for(int i = 0; i < conversaciones.size(); i++){
                                if(i == opcionConversacion){
                                    System.out.println("");
                                    Conversacion conversacion = conversaciones.get(i);
                                    long dia = c.obtenerDia(conversacion);
                                    String formato = c.formatearFecha(dia);
                                    System.out.println("Conversacion del " + formato);
                                    System.out.println("");

                                    List<Mensaje> mensajes = c.obtenerMensajes(conversacion);
                                    for(Mensaje m : mensajes){
                                        String emisor = c.getEmisor(m);
                                        String hora = c.getHora(m);
                                        String mensaje = c.getContenidoMensaje(m);
                                        System.out.println(emisor + " [" + hora + "]: " + mensaje);
                                    }
                                }
                            }
                        opcion = 2;
                        break;


                    case 1:
                        System.out.println("Que conversacion quieres eliminar?: ");
                        try {
                            voz("Que conversacion quieres eliminar?: ");
                        } catch (IOException | InterruptedException ex) {
                            Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                        int opcionEliminar = readInt("");
                        
                        for(int i = 0; i < conversaciones.size(); i++){
                            if(i == opcionEliminar){
                                System.out.println("");
                                Conversacion conversacionEliminar = conversaciones.get(i);
                                if(c.eliminarConversacion(conversacionEliminar)){
                                    System.out.println("Se elimino correctamente");
                                }
                            }
                        }
                        opcion = 2;
                        break;

                    case 2:
                        break;

                    default:
                        System.out.println("opcion no valida\n");
                }
            }while(opcion != 2);

            esperarEnter();
            limpiarPantalla();
        }
        
    }
    
    int z = 0;
    
    public void importarExportarChats(){
        
        int opcion;
        
        do {
            if(z == 0){
                System.out.println("-- IMPORTAR Y EXPORTAR CHATS --");
                System.out.println("0. IMPORTAR");
                try {
                    voz("0. IMPORTAR");
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("1. EXPORTAR");
                try {
                    voz("1. EXPORTAR");
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("2. SALIR");
                try {
                    voz("2. SALIR");
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(z == 1){
                System.out.println("-- IMPORTAR Y EXPORTAR CHATS --");
                System.out.println("0. IMPORTAR");
                System.out.println("1. EXPORTAR");
                System.out.println("2. SALIR");
            }
            
            z = 1;
            opcion = readInt("OPCION ... ");
            
            System.out.println("");

            switch (opcion) {
                case 0:
                    if(c.importarConversacion()){
                        System.out.println("Importacion realizada con exito");
                        
                        try {
                            voz("Importacion realizada con exito");
                        } catch (IOException | InterruptedException ex) {
                            Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("");
                        
                    }else{
                        System.out.println("Fallo en la importacion");
                        try {
                            voz("Fallo en la importacion");
                        } catch (IOException | InterruptedException ex) {
                            Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            System.out.println("");
                    }
                    opcion = 2;
                    break;
                    
                case 1:
                    List<Conversacion> conversaciones = c.obtenerConversaciones();
                    if(!conversaciones.isEmpty()){
                        c.exportarConversacion();
                        System.out.println("Exportacion realizada con exito");
                        
                        try {
                            voz("Exportacion realizada con exito");
                        } catch (IOException | InterruptedException ex) {
                            Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        System.out.println(""); 
                    }else{
                        System.out.println("Debes tener conversaciones para poder exportar");
                        try {
                            voz("Debes tener conversaciones para poder exportar");
                        } catch (IOException | InterruptedException ex) {
                            Logger.getLogger(ConsolaVozVista.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("");
                    }     
                    opcion = 2;
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0 && opcion != 1 && opcion != 2);
     
    }
        
    // Método auxiliar para obtener los primeros 20 caracteres del primer mensaje
    private String obtenerPrimeros20Caracteres(Conversacion conversacion) {
        List<Mensaje> mensajes = c.obtenerMensajes(conversacion);
        if (!mensajes.isEmpty()) {
            Mensaje primerMensaje = c.obtenerMensajes(conversacion).get(0);
            String contenidoPrimerMensaje = c.getContenidoMensaje(primerMensaje);
            return contenidoPrimerMensaje.length() > 21 ? contenidoPrimerMensaje.substring(0, 21) : contenidoPrimerMensaje;
        }
        return ""; // Agrega un valor de retorno por defecto o manejo de error, si es necesario
    }
    
    public void limpiarPantalla(){
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    private static void esperarEnter() {
        Esdia.readString("\nPresiona Enter para continuar... ");
    }
    
}

