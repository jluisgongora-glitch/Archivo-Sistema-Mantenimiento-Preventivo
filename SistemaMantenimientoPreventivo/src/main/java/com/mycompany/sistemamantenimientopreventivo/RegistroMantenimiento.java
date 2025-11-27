
package com.mycompany.sistemamantenimientopreventivo;


public class RegistroMantenimiento {
    
    private String fecha;
    private Maquinaria maquinaria;
    private Tecnico tecnico;
    private Cliente cliente;
    private ComponenteMaquinaria componenteReemplazado;
    private String descripcion;
    private String tipoServicio;
    
    public RegistroMantenimiento(String fecha, Maquinaria maquinaria, 
                                 Tecnico tecnico, Cliente cliente, String descripcion) {
        this.fecha = fecha;
        this.maquinaria = maquinaria;
        this.tecnico = tecnico;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.tipoServicio = maquinaria.getTipoServicio();
        this.componenteReemplazado = null;
    }
    
    public String getFecha() { return fecha; }
    public Maquinaria getMaquinaria() { return maquinaria; }
    public Tecnico getTecnico() { return tecnico; }
    public Cliente getCliente() { return cliente; }
    public String getTipoServicio() { return tipoServicio; }
    
    public void registrarReemplazoComponente(String nombreComp, String codigoComp, int vidaUtil) {
        this.componenteReemplazado = new ComponenteMaquinaria(nombreComp, codigoComp, vidaUtil);
    }
    
    public void mostrarRegistro() {
        System.out.println("--- Registro de Mantenimiento ---");
        System.out.println("Fecha: " + fecha);
        System.out.println("Cliente: " + cliente.getNombre() + " - " + cliente.getEmpresa());
        System.out.println("Maquinaria: " + maquinaria.getNombre());
        System.out.println("Tecnico: " + tecnico.getNombre());
        System.out.println("Tipo de servicio: " + tipoServicio);
        System.out.println("Descripcion: " + descripcion);
        if (componenteReemplazado != null) {
            System.out.println("Componente reemplazado: " + componenteReemplazado.getNombre());
        }
    }
}

