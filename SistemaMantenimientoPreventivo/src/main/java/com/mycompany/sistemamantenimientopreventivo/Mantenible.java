
package com.mycompany.sistemamantenimientopreventivo;


interface Mantenible {
    
     void realizarMantenimiento();
    boolean requiereMantenimiento();
}

interface Reportable {
    String generarReporte();
}
