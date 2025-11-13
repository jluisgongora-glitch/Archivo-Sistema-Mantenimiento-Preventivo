
package com.mycompany.sistemamantenimientopreventivo;

//Agregacion 
public class Tecnico {
    
    private String id;
    private String nombre;
    private String especialidad;
    private int mantenimientosRealizados;
    
    public Tecnico(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.mantenimientosRealizados = 0;
    }
    
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }
    public int getMantenimientosRealizados() { return mantenimientosRealizados; }
    
    public void realizarMantenimiento(Maquinaria maquina) {
        System.out.println("\n>>> Técnico " + nombre + " realizando mantenimiento...");
        maquina.realizarMantenimiento();
        mantenimientosRealizados++;
        System.out.println(">>> Mantenimiento completado!\n");
    }
    
    public void realizarMantenimiento(Maquinaria maquina, String observaciones) {
        realizarMantenimiento(maquina);
        System.out.println("Observaciones: " + observaciones);
    }
    
    public void mostrarEstadisticas() {
        System.out.println("Técnico: " + nombre);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Mantenimientos realizados: " + mantenimientosRealizados);
    }
}
