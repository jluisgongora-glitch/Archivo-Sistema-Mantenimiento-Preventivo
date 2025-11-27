
package com.mycompany.sistemamantenimientopreventivo;

//P C
public class SistemaMantenimientoPreventivo {

public static void main(String[] args) {
    
        //Creacion de ventana grafica
      /* Ventana v1 = new Ventana();
        v1.setVisible(true);
       */
    
       
       
        System.out.println("---------------------------------------------------");
        System.out.println("  SISTEMA DE GESTION DE MANTENIMIENTO PREVENTIVO");
        System.out.println("---------------------------------------------------\n");
        
        // Crear departamento
        DepartamentoMantenimiento depto = new DepartamentoMantenimiento("Mantenimiento Industrial");
        
        // Crear técnicos
        Tecnico tecnico1 = new Tecnico("T001", "Carlos Mendez", "Mecanica Industrial");
        Tecnico tecnico2 = new Tecnico("T002", "Ana Rodriguez", "Sistemas Electricos");
        Tecnico tecnico3 = new Tecnico("T003", "Luis Fernandez", "Mantenimiento Vehicular");
        
        depto.agregarTecnico(tecnico1);
        depto.agregarTecnico(tecnico2);
        depto.agregarTecnico(tecnico3);
        
        // Crear clientes
        Cliente cliente1 = new Cliente("C001", "Juan Perez", "Industrias MetalCorp", 
                                       "555-0101", "juan.perez@metalcorp.com");
        Cliente cliente2 = new Cliente("C002", "Maria García", "Logistica Express", 
                                       "555-0202", "maria.garcia@logexpress.com");
        Cliente cliente3 = new Cliente("C003", "Roberto Sanchez", "Construcciones Andinas", 
                                       "555-0303", "roberto.sanchez@andinas.com");
        
        depto.agregarCliente(cliente1);
        depto.agregarCliente(cliente2);
        depto.agregarCliente(cliente3);
        
        // Crear maquinarias
        MaquinariaIndustrial torno = new MaquinariaIndustrial(
            "M001", "Torno CNC", "Planta A", "Metalurgica", 150
        );
        MaquinariaIndustrial prensa = new MaquinariaIndustrial(
            "M002", "Prensa Hidraulica", "Planta B", "Metalurgica", 200
        );
        
        VehiculoCarga montacarga1 = new VehiculoCarga(
            "V001", "Montacargas Toyota", "Almacen Principal", "ABC-123", 3.5
        );
        VehiculoCarga montacarga2 = new VehiculoCarga(
            "V002", "Montacargas Caterpillar", "Almacen Secundario", "DEF-456", 5.0
        );
        
        HerramientaElectrica taladro = new HerramientaElectrica(
            "H001", "Taladro Industrial", "Taller B", 2000, false
        );
        
        // Asignar maquinarias a clientes
        cliente1.agregarMaquinaria(torno);
        cliente1.agregarMaquinaria(prensa);
        cliente2.agregarMaquinaria(montacarga1);
        cliente2.agregarMaquinaria(montacarga2);
        cliente3.agregarMaquinaria(taladro);
        
        depto.agregarMaquinaria(torno);
        depto.agregarMaquinaria(prensa);
        depto.agregarMaquinaria(montacarga1);
        depto.agregarMaquinaria(montacarga2);
        depto.agregarMaquinaria(taladro);
        
        // Simular uso de maquinaria
        System.out.println(">>> Simulando operacion de maquinarias...\n");
        torno.registrarHorasUso(150);
        prensa.registrarHorasUso(220);
        montacarga1.registrarHorasUso(110);
        montacarga2.registrarHorasUso(95);
        taladro.registrarHorasUso(45);
        
        // Realizar mantenimientos y registrar
        tecnico1.realizarMantenimiento(torno);
        depto.registroMantenimiento("10/11/2025", torno, tecnico1, cliente1, "Mantenimiento preventivo programado");
        
        tecnico2.realizarMantenimiento(prensa);
        depto.registroMantenimiento("10/11/2025", prensa, tecnico2, cliente1, "Inspeccion y ajuste");
        
        tecnico3.realizarMantenimiento(montacarga1);
        depto.registroMantenimiento("10/11/2025", montacarga1, tecnico3, cliente2, "Mantenimiento vehicular");
        
        tecnico1.realizarMantenimiento(prensa);
        depto.registroMantenimiento("11/11/2025", prensa, tecnico1, cliente1, "Mantenimiento correctivo");
        
        tecnico3.realizarMantenimiento(montacarga2);
        depto.registroMantenimiento("11/11/2025", montacarga2, tecnico3, cliente2, "Revision general");
        
        // Estado del departamento
        depto.mostrarEstadoDepartamento();
        
        // ------------------- GENERAR REPORTES -------------------
        
        // Reporte por cliente
        depto.reporteCliente("C001");
        depto.reporteCliente("C002");
        
        // Reporte de técnicos
        depto.reporteTecnicos();
        
        // Reporte de servicios más utilizados
        depto.reporteServiciosMasUtilizados();
        
        System.out.println("---------------------------------------------");
    }
}