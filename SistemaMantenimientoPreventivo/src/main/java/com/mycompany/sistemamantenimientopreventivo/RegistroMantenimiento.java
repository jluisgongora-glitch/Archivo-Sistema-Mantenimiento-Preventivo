
package com.mycompany.sistemamantenimientopreventivo;


public class RegistroMantenimiento {
    
    private String fecha;
    private Maquinaria maquinaria; // Asociación
    private Tecnico tecnico; // Asociación
    private ComponenteMaquinaria componenteReemplazado; // Composición
    private String descripcion;
    
    public RegistroMantenimiento(String fecha, Maquinaria maquinaria, 
                                 Tecnico tecnico, String descripcion) {
        this.fecha = fecha;
        this.maquinaria = maquinaria;
        this.tecnico = tecnico;
        this.descripcion = descripcion;
        this.componenteReemplazado = null;
    }
    
    public void registrarReemplazoComponente(String nombreComp, String codigoComp, int vidaUtil) {
        // Composición: el componente pertenece exclusivamente a este registro
        this.componenteReemplazado = new ComponenteMaquinaria(nombreComp, codigoComp, vidaUtil);
    }
    
    public void mostrarRegistro() {
        System.out.println("=== Registro de Mantenimiento ===");
        System.out.println("Fecha: " + fecha);
        System.out.println("Maquinaria: " + maquinaria.getNombre());
        System.out.println("Técnico: " + tecnico.getNombre());
        System.out.println("Descripción: " + descripcion);
        if (componenteReemplazado != null) {
            System.out.println("Componente reemplazado: " + componenteReemplazado.getNombre());
        }
    }
}
