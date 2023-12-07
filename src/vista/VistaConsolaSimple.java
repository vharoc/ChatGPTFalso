package vista;

import static com.coti.tools.Esdia.*;
import controlador.Controlador;
import modelo.Conversacion;
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
            
            System.out.println(String.format("%d. %d | %d | %s", i + 1, epochInicio, numeroMensajes, primeros20Caracteres));        
        }
    }
    
    // Método auxiliar para obtener los primeros 20 caracteres del primer mensaje
    private String obtenerPrimeros20Caracteres(Conversacion conversacion) {
        List<String> mensajes = conversacion.obtenerMensajes();
        if (!mensajes.isEmpty()) {
            String primerMensaje = mensajes.get(0);
            return primerMensaje.length() > 20 ? primerMensaje.substring(0, 20) : primerMensaje;
        } else {
            return "";
        }
    }
    
}
