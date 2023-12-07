package vista;

import static com.coti.tools.Esdia.*;
import controlador.Controlador;
import modelo.Conversacion;
import modelo.Mensaje;
import java.util.List;

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
        c.nuevaConversacion();
    }
    
    public void mostrarConversaciones() {
        System.out.println("\nLista de conversaciones:");
        
        List<Conversacion> conver = c.mostrarEliminarConversaciones();
        
        for (int i = 0; i < conver.size(); i++) {
            Conversacion cv = conver.get(i);

            long epochInicio = System.currentTimeMillis();  // Reemplaza esto con el valor correcto
            int numeroMensajes = cv.obtenerMensajes().size();
            String primeros20Caracteres = obtenerPrimeros20Caracteres(cv);
            
            System.out.println(String.format("%d. %d | %d | %s", i, epochInicio, numeroMensajes, primeros20Caracteres));        
        }
        
        int opcion = readInt("Mostrar todos los mensajes una de las conversaciones o salir?: ");
        System.out.println("");
        for(int i = 0; i < conver.size(); i++){
            if(i == opcion){
                Conversacion cv = conver.get(i);
                List<Mensaje> mensajesConversacionSeleccionada = cv.obtenerMensajes();
                for (Mensaje mensaje : mensajesConversacionSeleccionada) {
                    System.out.println("   " + mensaje.mensajeFormato());
                }
            }
        }
    }
    
    // Método auxiliar para obtener los primeros 20 caracteres del primer mensaje
    private String obtenerPrimeros20Caracteres(Conversacion conversacion) {
        List<Mensaje> mensajes = conversacion.obtenerMensajes();
        if (!mensajes.isEmpty()) {
            Mensaje primerMensaje = mensajes.get(0);
            String contenidoPrimerMensaje = primerMensaje.getContenido();
            return contenidoPrimerMensaje.length() > 20 ? contenidoPrimerMensaje.substring(0, 20) : contenidoPrimerMensaje;
        }
        return ""; // Agrega un valor de retorno por defecto o manejo de error, si es necesario
    }




    
}
