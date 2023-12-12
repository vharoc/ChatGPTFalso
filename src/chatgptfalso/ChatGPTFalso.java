package chatgptfalso;

import controlador.Controlador;
import modelo.Modelo;
import modelo.IRepositorio;
import modelo.JSON;
import modelo.XML;
import modelo.ILLM;
import modelo.FakeLLM;
import modelo.RandomCSVLLM;
import vista.AplicacionVista;
import vista.VistaConsolaSimple;

/**
 *
 * @author victo
 * 
 */

public class ChatGPTFalso {

    public static void main(String[] args) {
        
        IRepositorio repository;
        AplicacionVista view;
        ILLM illm;
        
        // LLamada esperada java -jar app.jar repository model view
        // por ejemplo: java -jar app.jar xml fake consola
        
        if(args.length == 3){
            repository = obtenerRepositorio(args[0]);
            illm = obternerILLM(args[1]);
            view = obtenerVista(args[2]);
            
        }else{
            // Opciones por defecto:
            view = new VistaConsolaSimple();
            repository = new JSON();
            illm = new FakeLLM();
        }
        
        Modelo m = new Modelo(repository, illm);   
        Controlador c = new Controlador(m, view);
        
        c.IniciarChat();
        
    }
    
    public static AplicacionVista obtenerVista(String argumento){
        switch (argumento) {
            case "consola":
                return new VistaConsolaSimple();
            case "voz":
                // return new ConsolaSubMenusView();
            default:
                return new VistaConsolaSimple();
        }
    }
    
    public static IRepositorio obtenerRepositorio(String argumento){
        switch(argumento){
            case "xml":
                return new XML();
            case "json":
                return new JSON();
            default:
                return new JSON();
        }
    }
    
    public static ILLM obternerILLM(String argumento){
        switch(argumento){
            case "fake":
                return new FakeLLM();
            case "csv":
                return new RandomCSVLLM();
            default:
                return new FakeLLM();
        }
    }
    
}
