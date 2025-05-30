package modelo;

public class Libro {
    private String nombre;
    private String añoPublicacion;
    private String genero;
    private Autor autor;

    public Libro(String nombre, String añoPublicacion, String genero, Autor autor) {
        this.nombre = nombre;
        this.añoPublicacion = añoPublicacion;
        this.genero = genero;
        this.autor = autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(String añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Nombre: "+ nombre +" Año Publicacion: "+ añoPublicacion + " Genero: " + genero + " Autor: " + autor.getNombre();
    }
}
