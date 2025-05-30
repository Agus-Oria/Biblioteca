package modelo;

public class Autor {
    private String nombre;
    private String nacionalidad;
    private String añoNacimiento;

    public Autor(String nombre, String nacionalidad, String añoNacimiento) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.añoNacimiento = añoNacimiento;
    }

    @Override
    public String toString() {
        return "Nombre: "+ nombre + " nacionalidad: " + nacionalidad + " año nacimiento: "+ añoNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(String añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }
}
