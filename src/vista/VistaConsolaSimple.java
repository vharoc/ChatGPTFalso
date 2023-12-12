package vista;

import com.coti.tools.Esdia;
import static com.coti.tools.Esdia.*;

import modelo.FakeLLM;
import modelo.Conversacion;
import modelo.Mensaje;

import java.util.List;
import java.util.ArrayList;

import java.time.Instant;

/**
 *
 * @author victo
 * 
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
            System.out.println("");
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
                
                nuevoMsg.add(c.crearMensaje(illm, respuesta, formato2));
                                                
                i = 0;
            }
           
        }
        
        long fechaFin = Instant.now().getEpochSecond();
        
        Conversacion nuevaConv = c.crearConversacion(nuevoMsg, illm, fechaInicio, fechaFin);
        
        c.agregarConversacionAConversaciones(nuevaConv);
        
        limpiarPantalla();
        
    }
        
    public void mostrarConversaciones() {
        
        if(c.obtenerConversaciones().size() <= 0){
            System.out.println("No existen conversaciones, debes crear o importar una al menos");
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
            System.out.println("");
            System.out.println("===================================================");
            System.out.println("           MENU SELENCCION OPCIONES                ");
            System.out.println("===================================================");
            System.out.println("0. MOSTRAR TODOS LOS MENSAJES DE UNA CONVERSACION");
            System.out.println("1. ELIMINAR UNA CONVERSACION");
            System.out.println("2. SALIR");

            int opcion; 
            do{
                opcion = readInt("OPCION ... ");
                System.out.println("");
                switch(opcion){
                    case 0:
                        int opcionConversacion = readInt("De que conversacion quieres ver los mensajes?: ");

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
                        int opcionEliminar = readInt("Que conversacion quieres eliminar?: ");
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
    
    public void importarExportarChats(){
        
        int opcion;
        do {
            System.out.println("-- IMPORTAR Y EXPORTAR CHATS --");
            System.out.println("0. IMPORTAR");
            System.out.println("1. EXPORTAR");
            System.out.println("2. SALIR");
            opcion = readInt("OPCION ... ");
            System.out.println("");

            switch (opcion) {
                case 0:
                    if(c.importarConversacion()){
                        System.out.println("Importacion realizada con exito");
                        System.out.println("");
                    }else{
                        System.out.println("Fallo en la importacion");
                        System.out.println("");
                    }
                    break;
                case 1:
                    List<Conversacion> conversaciones = c.obtenerConversaciones();
                    if(!conversaciones.isEmpty()){
                        c.exportarConversacion();
                        System.out.println("Exportacion realizada con exito");
                        System.out.println(""); 
                    }else{
                        System.out.println("Debes tener conversaciones para poder exportar");
                        System.out.println("");
                    }     
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 2);
     
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

