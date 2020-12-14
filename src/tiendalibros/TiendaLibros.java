package tiendalibros;
import net.sf.clipsrules.jni.Environment;

/**
 * Crea entorno CLIPS y ventanas
 * @author pablo
 */
public class TiendaLibros {

    public static void main(String[] args) {
        // Creacion entorno CLIPS
        Environment clips = new Environment();
        clips.load("libros.reglas.clp");
        clips.load("libros.datos.clp");
        clips.reset();
                
        Vinicio vi = new Vinicio(clips);
        vi.setVisible(true);
    }
    
}
