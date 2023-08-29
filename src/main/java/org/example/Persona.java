package org.example;

public class Persona {

    private int cedula;
    private String nombre;
    private int edad;
    private String direccion;

    public Persona(int cedula, String nombre, int edad, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
