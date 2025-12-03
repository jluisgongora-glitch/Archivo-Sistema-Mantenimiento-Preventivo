
package com.mycompany.sistemamantenimientopreventivo;


public class ServicioMantenimiento {
    
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int duracionHoras;
    
    public ServicioMantenimiento(String codigo, String nombre, String descripcion, 
                                 double precio, int duracionHoras) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionHoras = duracionHoras;
    }
    
    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public int getDuracionHoras() { return duracionHoras; }
    
    // Setters
    public void setPrecio(double precio) { this.precio = precio; }
    
    public void mostrarInfo() {
        System.out.println("------------------------------");
        System.out.println("Codigo: " + codigo);
        System.out.println("Servicio: " + nombre);
        System.out.println("Descripcion: " + descripcion);
        System.out.println("Precio: $" + String.format("%.2f", precio));
        System.out.println("Duracion: " + duracionHoras + " horas");
        System.out.println("------------------------------");
    }
    
    @Override
    public String toString() {
        return codigo + " - " + nombre + " ($" + String.format("%.2f", precio) + ")";
    }
    
}


