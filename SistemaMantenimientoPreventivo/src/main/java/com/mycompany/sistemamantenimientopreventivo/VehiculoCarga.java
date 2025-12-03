
package com.mycompany.sistemamantenimientopreventivo;



public class VehiculoCarga extends Maquinaria implements Reportable {
    
    private String placa;
    private double capacidadToneladas;
    
    public VehiculoCarga(String id, String nombre, String ubicacion, 
                        String placa, double capacidadToneladas) {
        super(id, nombre, ubicacion);
        this.placa = placa;
        this.capacidadToneladas = capacidadToneladas;
    }
    
    @Override
    public int getIntervaloMantenimiento() {
        return 100;
    }
    
    @Override
    public String getTipoServicio() {
        return " Mantenimiento Vehicular";
    }
    
    @Override
    public void realizarMantenimiento() {
        System.out.println(" Realizando mantenimiento vehicular en: " + getNombre());
        System.out.println("- Cambio de aceite");
        System.out.println("- Revision de frenos");
        System.out.println("- Inspeccion de neumaticos");
        reiniciarContadorMantenimiento();
    }
    
    @Override
    public String generarReporte() {
        return "REPORTE VEHICULO DE CARGA\n" +
               "Nombre: " + getNombre() + "\n" +
               "Placa: " + placa + "\n" +
               "Capacidad: " + capacidadToneladas + " toneladas\n" +
               "Horas de uso: " + getHorasUso();
    }
}
