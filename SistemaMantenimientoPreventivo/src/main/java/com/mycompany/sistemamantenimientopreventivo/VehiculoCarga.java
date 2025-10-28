
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
        return 100; // 100 horas
    }
    
    @Override
    public void realizarMantenimiento() {
        System.out.println("Realizando mantenimiento vehicular en: " + getNombre());
        System.out.println("- Cambio de aceite");
        System.out.println("- Revisión de frenos");
        System.out.println("- Inspección de neumáticos");
        reiniciarContadorMantenimiento();
    }
    
    @Override
    public String generarReporte() {
        return "REPORTE VEHÍCULO DE CARGA\n" +
               "Nombre: " + getNombre() + "\n" +
               "Placa: " + placa + "\n" +
               "Capacidad: " + capacidadToneladas + " toneladas\n" +
               "Horas de uso: " + getHorasUso();
    }
}
