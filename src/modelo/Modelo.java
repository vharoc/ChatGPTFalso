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
    
    private List<Conversacion> conver = new ArrayList<>();
    
    /*
    public Modelo()
        this.illm = illm;
        this.repo = repo;
    */
    
    public void nuevaConversacion(){
        
        Conversacion nuevaConv = new Conversacion();
        String remitente = readString_ne("Remitente: ");
        FakeLLM fake = new FakeLLM();
        
        while (true) {
            
            String mensaje = readString_ne("Tu mensaje: ");

            if ("/salir".equals(mensaje)) {
                break;
            }

            nuevaConv.agregarMensaje(remitente, mensaje);
            
            String respuesta = fake.generarRespuesta(mensaje);
            System.out.println("Er Fake: " + respuesta);
            
            nuevaConv.agregarMensaje("FakerCum", respuesta);
        }
        
        conver.add(nuevaConv);
    }
    
    public List<Conversacion> obtenerConversaciones() {
        return new ArrayList<>(conver);
    }
    
}
