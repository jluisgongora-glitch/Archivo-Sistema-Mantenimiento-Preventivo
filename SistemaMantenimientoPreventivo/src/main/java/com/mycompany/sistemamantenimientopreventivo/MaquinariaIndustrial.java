
package com.mycompany.sistemamantenimientopreventivo;

//Clase heredada de maquinaria e implenta un metodo de la clase mantenible
public class MaquinariaIndustrial  extends Maquinaria implements Reportable {
   
    private String tipoIndustria;
    private int capacidadProduccion;
    
    public MaquinariaIndustrial(String id, String nombre, String ubicacion, 
                                String tipoIndustria, int capacidadProduccion) {
        super(id, nombre, ubicacion);
        this.tipoIndustria = tipoIndustria;
        this.capacidadProduccion = capacidadProduccion;
    }
    
    @Override
    public int getIntervaloMantenimiento() {
        return 200;
    }
    
    @Override
    public String getTipoServicio() {
        return "Mantenimiento Industrial";
    }
    
    @Override
    public void realizarMantenimiento() {
        System.out.println("Realizando mantenimiento industrial en: " + getNombre());
        System.out.println("- Lubricación de partes móviles");
        System.out.println("- Inspección de sistema eléctrico");
        System.out.println("- Calibración de sensores");
        reiniciarContadorMantenimiento();
    }
    
    @Override
    public String generarReporte() {
        return "REPORTE MAQUINARIA INDUSTRIAL\n" +
               "Nombre: " + getNombre() + "\n" +
               "Tipo: " + tipoIndustria + "\n" +
               "Capacidad: " + capacidadProduccion + " unidades/hora\n" +
               "Horas de uso: " + getHorasUso();
    }
    
    @Override
    public void mostrarInfo(boolean detallado) {
        super.mostrarInfo(detallado);
        if (detallado) {
            System.out.println("Tipo de industria: " + tipoIndustria);
            System.out.println("Capacidad de producción: " + capacidadProduccion + " unidades/hora");
        }
    }
}
