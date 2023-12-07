package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static com.coti.tools.Esdia.*;
import modelo.Conversacion;

/**
 *
 * @author victo
 */
public class Modelo {
    
    private List<Conversacion> conver;
    
    public Modelo(){
        this.conver = new ArrayList<>();
    }
    
    public void nuevaConversacion(){
        
        Conversacion nuevaConv = new Conversacion();
        
        while (true) {
            
            String mensaje = readString_ne("Tu mensaje: ");

            if ("/salir".equals(mensaje)) {
                break;
            }

            nuevaConv.agregarMensaje(mensaje);
            
        }
        
        System.out.println("Conversacion finalizada... historial:");
        conver.add(nuevaConv);
                
        List<String> chatRecuperar = nuevaConv.obtenerMensajes();
        
        for (String elemento : chatRecuperar) {
            System.out.println(elemento);
        }
    }
    
    public List<Conversacion> obtenerConversaciones() {
        return new ArrayList<>(conver);
    }
    
}
