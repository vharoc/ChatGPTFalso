package vista;

import static com.coti.tools.Esdia.*;

import modelo.FakeLLM;
import controlador.Controlador;
import modelo.Conversacion;
import modelo.Mensaje;

import java.util.List;
import java.util.ArrayList;

import java.time.Instant;

/**
 *
 * @author victo
 */
public class VistaConsolaSimple extends AplicacionVista {
    
    @Override
    public void mostrarInicioAplicacion(String infoInicio){
        System.out.println(infoInicio);
    }
    
    @Override
    public void mostrarMenuPrincipal(){
        int opcion;
        do {
            System.out.println("\n--- MENU CONSOLA SIMPLE ---");
            System.out.println("1. Nueva conversacion");
            System.out.println("2. Listar/Eliminar conversaciones");
            System.out.println("3. Exportacion chats");
            System.out.println("4. Salir");
            opcion = readInt("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    nuevaConversacion();
                    break;
                case 2:
                    mostrarConversaciones();
                    break;
                case 3:
                    // importarAlumnos();
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
        //c.nuevaConversacion();
        int i = 0;
        
        FakeLLM fake = new FakeLLM();
                
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
                
                String respuesta = fake.generarRespuesta(m);
                
                System.out.println("Er Fake [" + formato2 + "]: " + respuesta);
                
                nuevoMsg.add(c.crearMensaje("BOT", respuesta, formato2));
                                                
                i = 0;
            }
           
        }
        
        long fechaFin = Instant.now().getEpochSecond();
        
        Conversacion nuevaConv = c.crearConversacion(nuevoMsg, "bot", fechaInicio, fechaFin);
        
        c.agregarConversacionAConversaciones(nuevaConv);
        
    }
        
    public void mostrarConversaciones() {
        
        System.out.println("\nLista de conversaciones:");
        
        List<Conversacion> conversaciones = c.obtenerConversaciones();
        
        for (int i = 0; i < conversaciones.size(); i++) {
        
            Conversacion conversacion = conversaciones.get(i);
            
            long epoch = c.obtenerEpoch(conversacion);
            int numeroMensajes = c.obtenerMensajes(conversacion).size();
            String primeros20Caracteres = obtenerPrimeros20Caracteres(conversacion);
            
            System.out.println(String.format("%d. %d | %d | %s", i, epoch, numeroMensajes, primeros20Caracteres)); 
                  
        }
        System.out.println("");
        
        int opcion = readInt("Mostrar todos los mensajes una de las conversaciones(0), eliminar una conversacion(1) o salir?: ");
        System.out.println("");
        
        if(opcion == 0){
            int opcionConversacion = readInt("De que conversacion quieres ver los mensajes?: ");
            for(int i = 0; i < conversaciones.size(); i++){
                if(i == opcionConversacion){
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
        }
        
        if(opcion == 1){
            int opcionEliminar = readInt("Que conversacion quieres eliminar?: ");
            for(int i = 0; i < conversaciones.size(); i++){
                if(i == opcionEliminar){
                    Conversacion conversacionEliminar = conversaciones.get(i);
                    if(c.eliminarConversacion(conversacionEliminar)){
                        System.out.println("Se elimino correctamente");
                    }
                }
            }
        }
        
    }
    
    // Método auxiliar para obtener los primeros 20 caracteres del primer mensaje
    private String obtenerPrimeros20Caracteres(Conversacion conversacion) {
        List<Mensaje> mensajes = c.obtenerMensajes(conversacion);
        if (!mensajes.isEmpty()) {
            Mensaje primerMensaje = c.obtenerMensajes(conversacion).get(0);
            String contenidoPrimerMensaje = c.getContenidoMensaje(primerMensaje);
            return contenidoPrimerMensaje.length() > 20 ? contenidoPrimerMensaje.substring(0, 20) : contenidoPrimerMensaje;
        }
        return ""; // Agrega un valor de retorno por defecto o manejo de error, si es necesario
    }
    
}
