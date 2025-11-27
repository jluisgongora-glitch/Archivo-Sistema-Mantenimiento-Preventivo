
package com.mycompany.sistemamantenimientopreventivo;

//Composicion
public class ComponenteMaquinaria {
    
    private String nombre;
    private String codigo;
    private int vidaUtil;
    
    public ComponenteMaquinaria(String nombre, String codigo, int vidaUtil) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.vidaUtil = vidaUtil;
    }
    
    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
    public int getVidaUtil() { return vidaUtil; }
}
