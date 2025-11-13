
package com.mycompany.sistemamantenimientopreventivo;


public class Cliente {
    
    private String id;
    private String nombre;
    private String empresa;
    private String telefono;
    private String email;
    private java.util.ArrayList<Maquinaria> maquinarias; // Asociación
    
    public Cliente(String id, String nombre, String empresa, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.empresa = empresa;
        this.telefono = telefono;
        this.email = email;
        this.maquinarias = new java.util.ArrayList<>();
    }
    
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmpresa() { return empresa; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    
    public void agregarMaquinaria(Maquinaria maquina) {
        maquinarias.add(maquina);
    }
    
    public java.util.ArrayList<Maquinaria> getMaquinarias() {
        return maquinarias;
    }
    
    public void mostrarInfo() {
        System.out.println("=== Información del Cliente ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Empresa: " + empresa);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Email: " + email);
        System.out.println("Maquinarias registradas: " + maquinarias.size());
    }
    
}

