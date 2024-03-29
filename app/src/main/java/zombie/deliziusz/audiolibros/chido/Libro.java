package zombie.deliziusz.audiolibros.chido;

import java.util.Vector;
/**
 * @author Deliziusz : Karla Yazmín García Pérez
 */

public class Libro {
    public String titulo;
    public String autor;
    public int recursoImagen;
    public String urlAudio;
    public String genero;
    public Boolean novedad;
    public Boolean leido;
    public final static String G_TODOS = "Todos los generos";
    public final static String G_EPICO = "Poema Epico";
    public final static String G_S_XIX = "Literatura siglo XIX";
    public final static String G_SUSPENSE= "Suspense";
    public final static String[] G_ARRAY = new String[] {G_TODOS,G_EPICO,G_S_XIX,G_SUSPENSE};


    public Libro(String titulo,String autor, int recursoImagen,String urlAudio,
                 String genero,Boolean novedad,Boolean leido){
        this.titulo=titulo;
        this.autor=autor;
        this.recursoImagen=recursoImagen;
        this.urlAudio=urlAudio;
        this.genero=genero;
        this.novedad=novedad;
        this.leido=leido;
    }
    public static Vector<Libro> ejemploLibros() {
        final String SERVIDOR =
                "http://www.dcomg.upv.es/~jtomas/android/audiolibros/";
        Vector<Libro> libros = new Vector<Libro>();
        libros.add(new Libro("Kappa", "Akutagawa",
                zombie.deliziusz.audiolibros.R.drawable.kappa, SERVIDOR+"kappa.mp3",
                Libro.G_S_XIX, false, false));
        libros.add(new Libro("Avecilla", "Alas Clarín, Leopoldo",
                zombie.deliziusz.audiolibros.R.drawable.avecilla, SERVIDOR+"avecilla.mp3",
                Libro.G_S_XIX, true, false));
        libros.add(new Libro("Divina Comedia", "Dante",
                zombie.deliziusz.audiolibros.R.drawable.divinacomedia, SERVIDOR+"divina_comedia.mp3",
                Libro.G_EPICO, true, false));
        libros.add(new Libro("Viejo Pancho, El", "Alonso y Trelles, José",
                zombie.deliziusz.audiolibros.R.drawable.viejo_pancho, SERVIDOR+"viejo_pancho.mp3",
                Libro.G_S_XIX, true, true));
        libros.add(new Libro("Canción de Rolando", "Anónimo",
                zombie.deliziusz.audiolibros.R.drawable.cancion_rolando, SERVIDOR+"cancion_rolando.mp3",
                Libro.G_EPICO, false, true));
        libros.add(new Libro("Matrimonio de sabuesos", "Agata Christie",
                zombie.deliziusz.audiolibros.R.drawable.matrimonio_sabuesos,SERVIDOR+"matrim_sabuesos.mp3",
                Libro.G_SUSPENSE, false, true));
        libros.add(new Libro("La iliada", "Homero",
                zombie.deliziusz.audiolibros.R.drawable.iliada, SERVIDOR+"la_iliada.mp3",
                Libro.G_EPICO, true, false));
        return libros;
    }
}
