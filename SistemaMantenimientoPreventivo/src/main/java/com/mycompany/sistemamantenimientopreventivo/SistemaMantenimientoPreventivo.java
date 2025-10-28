
package com.mycompany.sistemamantenimientopreventivo;

//P C
public class SistemaMantenimientoPreventivo {

public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  SISTEMA DE GESTIÓN DE MANTENIMIENTO PREVENTIVO");
        System.out.println("=================================================\n");
        
        // Crear departamento (Agregación)
        DepartamentoMantenimiento depto = new DepartamentoMantenimiento("Mantenimiento Industrial");
        
        // Crear técnicos (Agregación)
        Tecnico tecnico1 = new Tecnico("T001", "Carlos Méndez", "Mecánica Industrial");
        Tecnico tecnico2 = new Tecnico("T002", "Ana Rodríguez", "Sistemas Eléctricos");
        
        depto.agregarTecnico(tecnico1);
        depto.agregarTecnico(tecnico2);
        
        // Crear maquinarias (Herencia y Polimorfismo)
        MaquinariaIndustrial torno = new MaquinariaIndustrial(
            "M001", "Torno CNC", "Planta A", "Metalúrgica", 150
        );
        
        VehiculoCarga montacarga = new VehiculoCarga(
            "V001", "Montacargas Toyota", "Almacén Principal", "ABC-123", 3.5
        );
        
        HerramientaElectrica taladro = new HerramientaElectrica(
            "H001", "Taladro Industrial", "Taller B", 2000, false
        );
        
        depto.agregarMaquinaria(torno);
        depto.agregarMaquinaria(montacarga);
        depto.agregarMaquinaria(taladro);
        
        // Simular uso de maquinaria
        System.out.println(">>> Simulando operación de maquinarias...\n");
        torno.registrarHorasUso(150);
        montacarga.registrarHorasUso(110);
        taladro.registrarHorasUso(45);
        
        // Mostrar información (Polimorfismo: Sobrecarga)
        torno.mostrarInfo(true);
        System.out.println();
        montacarga.mostrarInfo(false);
        System.out.println();
        
        // Verificar mantenimientos pendientes
        depto.verificarMantenimientosPendientes();
        
        // Realizar mantenimientos (Polimorfismo: Sobrecarga y Sobreescritura)
        tecnico1.realizarMantenimiento(torno);
        tecnico2.realizarMantenimiento(montacarga, "Revisión por ruidos extraños");
        
        // Crear registro con componente reemplazado (Composición)
        RegistroMantenimiento reg1 = new RegistroMantenimiento(
            "15/10/2025", torno, tecnico1, "Mantenimiento preventivo programado"
        );
        reg1.registrarReemplazoComponente("Filtro de aceite", "FLT-2000", 500);
        reg1.mostrarRegistro();
        
        System.out.println();
        
        // Registrar en el departamento
        depto.registrarMantenimiento("15/10/2025", montacarga, tecnico2, 
                                     "Mantenimiento por horas de uso");
        
        // Generar reportes (Interfaces)
        System.out.println("\n" + torno.generarReporte());
        System.out.println("\n" + montacarga.generarReporte());
        
        // Estadísticas
        System.out.println();
        tecnico1.mostrarEstadisticas();
        System.out.println();
        tecnico2.mostrarEstadisticas();
        
        // Estado del departamento
        depto.mostrarEstadoDepartamento();
        
        // Verificar nuevamente mantenimientos
        depto.verificarMantenimientosPendientes();
        
        System.out.println("\n=================================================");
    }



