package vista;

import static com.coti.tools.Esdia.*;
import controlador.Controlador;
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
                    // eliminarAlumnoPorDNI();
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
        
             
    }
    
}
