
package com.mycompany.sistemamantenimientopreventivo;


public class HerramientaElectrica extends Maquinaria {
    
    private int potenciaWatts;
    private boolean portatil;
    
    public HerramientaElectrica(String id, String nombre, String ubicacion, 
                                int potenciaWatts, boolean portatil) {
        super(id, nombre, ubicacion);
        this.potenciaWatts = potenciaWatts;
        this.portatil = portatil;
    }
    
    @Override
    public int getIntervaloMantenimiento() {
        return 50; // 50 horas
    }
    
    @Override
    public void realizarMantenimiento() {
        System.out.println("Realizando mantenimiento de herramienta: " + getNombre());
        System.out.println("- Limpieza de componentes");
        System.out.println("- Revisión de cableado");
        reiniciarContadorMantenimiento();
    }
}
