
package com.mycompany.sistemamantenimientopreventivo;

//Clase abstracta
abstract public class Maquinaria implements Mantenible {
    
     // Atributos privados (Encapsulamiento)
    private String id;
    private String nombre;
    private String ubicacion;
    private int horasUso;
    private int horasUltimoMantenimiento;
    
    // Constructor
    public Maquinaria(String id, String nombre, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.horasUso = 0;
        this.horasUltimoMantenimiento = 0;
    }
    
    // Métodos getter y setter (Encapsulamiento)
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public int getHorasUso() { return horasUso; }
    
    public void setUbicacion(String ubicacion) { 
        this.ubicacion = ubicacion; 
    }
    
    public void registrarHorasUso(int horas) {
        this.horasUso += horas;
    }
    
    public void reiniciarContadorMantenimiento() {
        this.horasUltimoMantenimiento = this.horasUso;
    }
    
    protected int getHorasDesdeMatenimiento() {
        return horasUso - horasUltimoMantenimiento;
    }
    
    // Método abstracto que debe ser implementado por subclases
    public abstract int getIntervaloMantenimiento();
    public abstract String getTipoServicio();
    
    // Implementación de interfaz Mantenible
    @Override
    public boolean requiereMantenimiento() {
        return getHorasDesdeMatenimiento() >= getIntervaloMantenimiento();
    }
    
    // Polimorfismo: Sobrecarga de métodos
    public void mostrarInfo() {
        System.out.println("ID: " + id + ", Nombre: " + nombre);
    }
    
    public void mostrarInfo(boolean detallado) {
        if (detallado) {
            System.out.println("--- Informacion de Maquinaria ---");
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombre);
            System.out.println("Ubicacion: " + ubicacion);
            System.out.println("Horas de uso: " + horasUso);
            System.out.println("Requiere mantenimiento: " + (requiereMantenimiento() ? "Si" : "No"));
        } else {
            mostrarInfo();
        }
    }
}
