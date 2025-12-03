
package com.mycompany.sistemamantenimientopreventivo;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

//P C
public class SistemaMantenimientoPreventivo {

    private static Scanner scanner = new Scanner(System.in);
    private static DepartamentoMantenimiento depto;
    private static int contadorFacturas = 1000;
    
public static void main(String[] args) {
    
        //Creacion de ventana grafica
      /* Ventana v1 = new Ventana();
        v1.setVisible(true); */
       
    
     inicializarSistema();
        cargarDatosPrueba();
        
        boolean salir = false;
        
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerEntero("Seleccione una opcion: ");
            
            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    registrarMaquinaria();
                    break;
                case 3:
                    solicitarServicio();
                    break;
                case 4:
                    menuReportes();
                    break;
                case 5:
                    menuReportesPDF();
                    break;
                case 6:
                    consultarServicios();
                    break;
                case 7:
                    consultarTecnicos();
                    break;
                case 8:
                    depto.mostrarEstadoDepartamento();
                    break;
                case 9:
                    abrirVentanaGrafica();
                    break;
                case 0:
                    salir = true;
                    System.out.println("\n¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println(" Opcion invalida. Intente nuevamente.");
            }
        }
        
        scanner.close();
    }
    
    private static void inicializarSistema() {
        System.out.println("-------------------------------------------------");
        System.out.println("   SISTEMA DE GESTION DE MANTENIMIENTO PREVENTIVO    ");
        System.out.println("-------------------------------------------------\n");
        
        depto = new DepartamentoMantenimiento("Mantenimiento Industrial");
        System.out.println(" Sistema inicializado correctamente.\n");
    }
    
    private static void cargarDatosPrueba() {
        // Crear técnicos
        depto.agregarTecnico(new Tecnico("T001", "Carlos Mendez", "Mecanica Industrial"));
        depto.agregarTecnico(new Tecnico("T002", "Ana Rodriguez", "Sistemas Eléctricos"));
        depto.agregarTecnico(new Tecnico("T003", "Luis Fernandez", "Mantenimiento Vehicular"));
        
        // Crear clientes de ejemplo
        Cliente cliente1 = new Cliente("C001", "Juan Perez", "Industrias MetalCorp", 
                                       "555-0101", "juan.perez@metalcorp.com");
        Cliente cliente2 = new Cliente("C002", "Maria Garcia", "Logistica Express", 
                                       "555-0202", "maria.garcia@logexpress.com");
        
        depto.agregarCliente(cliente1);
        depto.agregarCliente(cliente2);
        
        // Crear maquinarias
        MaquinariaIndustrial torno = new MaquinariaIndustrial(
            "M001", "Torno CNC", "Planta A", "Metalurgica", 150
        );
        VehiculoCarga montacarga = new VehiculoCarga(
            "V001", "Montacargas Toyota", "Almacen Principal", "ABC-123", 3.5
        );
        HerramientaElectrica taladro = new HerramientaElectrica(
            "H001", "Taladro Industrial", "Taller B", 2000, false
        );
        
        // Asignar maquinarias a clientes
        cliente1.agregarMaquinaria(torno);
        cliente1.agregarMaquinaria(taladro);
        cliente2.agregarMaquinaria(montacarga);
        
        depto.agregarMaquinaria(torno);
        depto.agregarMaquinaria(montacarga);
        depto.agregarMaquinaria(taladro);
        
        System.out.println(" Tecnicos, clientes y maquinarias cargados.");
    }
    
    private static void mostrarMenuPrincipal() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("                   MENU PRINCIPAL                     ");
        System.out.println("-------------------------------------------------");
        System.out.println("  1. Registrar Cliente                                ");
        System.out.println("  2. Registrar Maquinaria                             ");
        System.out.println("  3. Solicitar Servicio de Mantenimiento              ");
        System.out.println("  4. Ver Reportes (Consola)                           ");
        System.out.println("  5. Generar Reportes PDF                             ");
        System.out.println("  6. Consultar Servicios Disponibles                  ");
        System.out.println("  7. Consultar Técnicos                               ");
        System.out.println("  8. Estado del Departamento                          ");
        System.out.println("  9. Abrir Ventana Gráfica                            ");
        System.out.println("  0. Salir                                            ");
        System.out.println("-------------------------------------------------");
    }
    
    private static void registrarCliente() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("              REGISTRO DE CLIENTE                     ");
        System.out.println("-------------------------------------------------\n");
        
        String id = leerTexto("ID del cliente: ");
        String nombre = leerTexto("Nombre completo: ");
        String empresa = leerTexto("Empresa: ");
        String telefono = leerTexto("Telefono: ");
        String email = leerTexto("Email: ");
        
        Cliente cliente = new Cliente(id, nombre, empresa, telefono, email);
        depto.agregarCliente(cliente);
        
        System.out.println("\n Cliente registrado exitosamente!");
    }
    
    private static void registrarMaquinaria() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("            REGISTRO DE MAQUINARIA                    ");
        System.out.println("-------------------------------------------------\n");
        
        if (depto.getClientes().isEmpty()) {
            System.out.println(" No hay clientes registrados. Registre un cliente primero.");
            return;
        }
        
        // Mostrar clientes
        System.out.println("Clientes disponibles:");
        for (Cliente c : depto.getClientes()) {
            System.out.println("  [" + c.getId() + "] " + c.getNombre() + " - " + c.getEmpresa());
        }
        
        String idCliente = leerTexto("\nID del cliente propietario: ");
        Cliente cliente = depto.buscarCliente(idCliente);
        
        if (cliente == null) {
            System.out.println(" Cliente no encontrado.");
            return;
        }
        
        System.out.println("\nTipo de maquinaria:");
        System.out.println("1. Maquinaria Industrial");
        System.out.println("2. Vehiculo de Carga");
        System.out.println("3. Herramienta Electrica");
        int tipo = leerEntero("Seleccione tipo: ");
        
        String id = leerTexto("ID de la maquinaria: ");
        String nombre = leerTexto("Nombre: ");
        String ubicacion = leerTexto("Ubicacion: ");
        
        Maquinaria maquina = null;
        
        switch (tipo) {
            case 1:
                String tipoInd = leerTexto("Tipo de industria: ");
                int capacidad = leerEntero("Capacidad de produccion: ");
                maquina = new MaquinariaIndustrial(id, nombre, ubicacion, tipoInd, capacidad);
                break;
            case 2:
                String placa = leerTexto("Placa: ");
                double toneladas = leerDouble("Capacidad en toneladas: ");
                maquina = new VehiculoCarga(id, nombre, ubicacion, placa, toneladas);
                break;
            case 3:
                int potencia = leerEntero("Potencia en watts: ");
                boolean portatil = leerTexto("¿Es portatil? (s/n): ").equalsIgnoreCase("s");
                maquina = new HerramientaElectrica(id, nombre, ubicacion, potencia, portatil);
                break;
            default:
                System.out.println(" Tipo invalido.");
                return;
        }
        
        cliente.agregarMaquinaria(maquina);
        depto.agregarMaquinaria(maquina);
        
        System.out.println("\n Maquinaria registrada exitosamente!");
    }
    
    private static void solicitarServicio() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("          SOLICITUD DE SERVICIO                       ");
        System.out.println("-------------------------------------------------\n");
        
        if (depto.getClientes().isEmpty()) {
            System.out.println(" No hay clientes registrados.");
            return;
        }
        
        // Seleccionar cliente
        System.out.println("Clientes registrados:");
        for (Cliente c : depto.getClientes()) {
            System.out.println("  [" + c.getId() + "] " + c.getNombre() + " - " + c.getEmpresa());
        }
        String idCliente = leerTexto("\nID del cliente: ");
        Cliente cliente = depto.buscarCliente(idCliente);
        
        if (cliente == null) {
            System.out.println(" Cliente no encontrado.");
            return;
        }
        
        // Seleccionar maquinaria
        if (cliente.getMaquinarias().isEmpty()) {
            System.out.println("✗ Este cliente no tiene maquinarias registradas.");
            return;
        }
        
        System.out.println("\nMaquinarias del cliente:");
        for (Maquinaria m : cliente.getMaquinarias()) {
            System.out.println("  [" + m.getId() + "] " + m.getNombre());
        }
        String idMaquina = leerTexto("\nID de la maquinaria: ");
        Maquinaria maquina = depto.buscarMaquinaria(idMaquina);
        
        if (maquina == null) {
            System.out.println("✗ Maquinaria no encontrada.");
            return;
        }
        
        // Mostrar y seleccionar servicio
        depto.mostrarServicios();
        String codigoServicio = leerTexto("\nCodigo del servicio deseado: ");
        ServicioMantenimiento servicio = depto.buscarServicio(codigoServicio);
        
        if (servicio == null) {
            System.out.println(" Servicio no encontrado.");
            return;
        }
        
        // Mostrar y seleccionar técnico
        depto.mostrarTecnicos();
        String idTecnico = leerTexto("\nID del tecnico deseado: ");
        Tecnico tecnico = depto.buscarTecnico(idTecnico);
        
        if (tecnico == null) {
            System.out.println(" Tecnico no encontrado.");
            return;
        }
        
        // Confirmar servicio
        System.out.println("\n-------------------------------------------------");
        System.out.println("              RESUMEN DEL SERVICIO                    ");
        System.out.println("-------------------------------------------------");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Maquinaria: " + maquina.getNombre());
        System.out.println("Servicio: " + servicio.getNombre());
        System.out.println("Tecnico: " + tecnico.getNombre());
        System.out.println("Precio: $" + String.format("%.2f", servicio.getPrecio()));
        System.out.println("-------------------------------------------------");
        
        String confirmar = leerTexto("\n¿Confirmar servicio? (s/n): ");
        
        if (confirmar.equalsIgnoreCase("s")) {
            // Realizar mantenimiento
            tecnico.realizarMantenimiento(maquina);
            
            // Registrar
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = sdf.format(new Date());
            String descripcion = leerTexto("Observaciones adicionales: ");
            
            depto.registroMantenimiento(fecha, maquina, tecnico, cliente, descripcion, servicio);
            
            System.out.println("\n Servicio registrado exitosamente!");
            
            // Preguntar si desea factura
            String factura = leerTexto("\n¿Desea generar factura en PDF? (s/n): ");
            if (factura.equalsIgnoreCase("s")) {
                String numeroFactura = "FAC-" + (contadorFacturas++);
                String rutaFactura = "Factura_" + numeroFactura + ".pdf";
                GeneradorPDF.generarFactura(cliente, servicio, tecnico, maquina, numeroFactura, rutaFactura);
                System.out.println(" Factura generada: " + rutaFactura);
            }
        } else {
            System.out.println("\n Servicio cancelado.");
        }
    }
    
    private static void menuReportes() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("               REPORTES EN CONSOLA                    ");
        System.out.println("-------------------------------------------------");
        System.out.println("1. Reporte de Cliente");
        System.out.println("2. Reporte de Tecnicos");
        System.out.println("3. Reporte de Servicios Mas Utilizados");
        System.out.println("0. Volver");
        
        int opcion = leerEntero("\nSeleccione opcion: ");
        
        switch (opcion) {
            case 1:
                String id = leerTexto("ID del cliente: ");
                depto.reporteCliente(id);
                break;
            case 2:
                depto.reporteTecnicos();
                break;
            case 3:
                depto.reporteServiciosMasUtilizados();
                break;
        }
    }
    
    private static void menuReportesPDF() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("               GENERACION DE PDFs                     ");
        System.out.println("------------------------------------------------");
        System.out.println("1. Reporte de Cliente (PDF)");
        System.out.println("2. Reporte de Tecnicos (PDF)");
        System.out.println("3. Reporte de Servicios (PDF)");
        System.out.println("0. Volver");
        
        int opcion = leerEntero("\nSeleccione opcion: ");
        
        switch (opcion) {
            case 1:
                String id = leerTexto("ID del cliente: ");
                String ruta1 = "Reporte_Cliente_" + id + ".pdf";
                depto.generarPDFCliente(id, ruta1);
                break;
            case 2:
                String ruta2 = "Reporte_Tecnicos.pdf";
                depto.generarPDFTecnicos(ruta2);
                break;
            case 3:
                String ruta3 = "Reporte_Servicios.pdf";
                depto.generarPDFServicios(ruta3);
                break;
        }
    }
    
    private static void consultarServicios() {
        depto.mostrarServicios();
    }
    
    private static void consultarTecnicos() {
        depto.mostrarTecnicos();
    }
    
    private static void abrirVentanaGrafica() {
        System.out.println("\n Abriendo ventana grafica...");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Ventana ventana = new Ventana(depto);
                ventana.setVisible(true);
            }
        });
    }
    
    // Métodos auxiliares de lectura
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
    
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println(" Entrada invalida. Ingrese un numero.");
            }
        }
    }
    
    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println(" Entrada invalida. Ingrese un numero.");
            }
        }
    }   
    
}
