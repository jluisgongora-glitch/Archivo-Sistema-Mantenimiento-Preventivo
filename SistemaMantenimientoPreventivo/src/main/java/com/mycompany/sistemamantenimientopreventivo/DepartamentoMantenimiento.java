
package com.mycompany.sistemamantenimientopreventivo;


public class DepartamentoMantenimiento {
    
    private String nombre;
    private java.util.ArrayList<Tecnico> tecnicos; // Agregación: los técnicos pueden existir independientemente
    private java.util.ArrayList<Maquinaria> maquinarias; // Agregación
    private java.util.ArrayList<RegistroMantenimiento> registros; // Composición
    
    public DepartamentoMantenimiento(String nombre) {
        this.nombre = nombre;
        this.tecnicos = new java.util.ArrayList<>();
        this.maquinarias = new java.util.ArrayList<>();
        this.registros = new java.util.ArrayList<>();
    }
    
    public void agregarTecnico(Tecnico tecnico) {
        tecnicos.add(tecnico);
    }
    
    public void agregarMaquinaria(Maquinaria maquina) {
        maquinarias.add(maquina);
    }
    
    public void registrarMantenimiento(String fecha, Maquinaria maquina, 
                                      Tecnico tecnico, String descripcion) {
        RegistroMantenimiento registro = new RegistroMantenimiento(fecha, maquina, tecnico, descripcion);
        registros.add(registro);
    }
    
    public void verificarMantenimientosPendientes() {
        System.out.println("\n=== Verificación de Mantenimientos Pendientes ===");
        for (Maquinaria m : maquinarias) {
            if (m.requiereMantenimiento()) {
                System.out.println("⚠ " + m.getNombre() + " requiere mantenimiento!");
            }
        }
    }
    
    public void mostrarEstadoDepartamento() {
        System.out.println("\n====== DEPARTAMENTO DE MANTENIMIENTO: " + nombre + " ======");
        System.out.println("Total de técnicos: " + tecnicos.size());
        System.out.println("Total de maquinarias: " + maquinarias.size());
        System.out.println("Total de registros: " + registros.size());
    }
}
