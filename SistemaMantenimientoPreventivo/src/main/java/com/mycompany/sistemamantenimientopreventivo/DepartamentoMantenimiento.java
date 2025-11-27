
package com.mycompany.sistemamantenimientopreventivo;


public class DepartamentoMantenimiento {
    
    private String nombre;
    private java.util.ArrayList<Tecnico> tecnicos;
    private java.util.ArrayList<Maquinaria> maquinarias;
    private java.util.ArrayList<Cliente> clientes;
    private java.util.ArrayList<RegistroMantenimiento> registros;
    
    public DepartamentoMantenimiento(String nombre) {
        this.nombre = nombre;
        this.tecnicos = new java.util.ArrayList<>();
        this.maquinarias = new java.util.ArrayList<>();
        this.clientes = new java.util.ArrayList<>();
        this.registros = new java.util.ArrayList<>();
    }
    
    public void agregarTecnico(Tecnico tecnico) {
        tecnicos.add(tecnico);
    }
    
    public void agregarMaquinaria(Maquinaria maquina) {
        maquinarias.add(maquina);
    }
    
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }
    
    public void registroMantenimiento(String fecha, Maquinaria maquina, 
                                      Tecnico tecnico, Cliente cliente, String descripcion) {
        RegistroMantenimiento registro = new RegistroMantenimiento(fecha, maquina, tecnico, cliente, descripcion);
        registros.add(registro);
    }
    
    public void verificarMantenimientosPendientes() {
        System.out.println("\n--- Verificacion de Mantenimientos Pendientes ---");
        for (Maquinaria m : maquinarias) {
            if (m.requiereMantenimiento()) {
                System.out.println( m.getNombre() + " requiere mantenimiento!");
            }
        }
    }
    
    public void mostrarEstadoDepartamento() {
        System.out.println("\n------ DEPARTAMENTO DE MANTENIMIENTO: " + nombre + " ------");
        System.out.println("Total de tecnicos: " + tecnicos.size());
        System.out.println("Total de clientes: " + clientes.size());
        System.out.println("Total de maquinarias: " + maquinarias.size());
        System.out.println("Total de registros: " + registros.size());
    }
 // Reporte por Cliente
    public void reporteCliente(String idCliente) {
        System.out.println("\n ---------------------------------------------");
        System.out.println("       REPORTE DE MANTENIMIENTOS POR CLIENTE       ");
        System.out.println("---------------------------------------------");
        
        Cliente clienteEncontrado = null;
        for (Cliente c : clientes) {
            if (c.getId().equals(idCliente)) {
                clienteEncontrado = c;
                break;
            }
        }
        
        if (clienteEncontrado == null) {
            System.out.println(" Cliente no encontrado.");
            return;
        }
        
        System.out.println("\n DATOS DEL CLIENTE:");
        System.out.println("   Nombre: " + clienteEncontrado.getNombre());
        System.out.println("   Empresa: " + clienteEncontrado.getEmpresa());
        System.out.println("   Telefono: " + clienteEncontrado.getTelefono());
        System.out.println("   Email: " + clienteEncontrado.getEmail());
        
        System.out.println("\n  MAQUINARIAS REGISTRADAS:");
        for (Maquinaria m : clienteEncontrado.getMaquinarias()) {
            System.out.println("   • " + m.getNombre() + " (ID: " + m.getId() + ")");
        }
        
        System.out.println("\n  HISTORIAL DE MANTENIMIENTOS:");
        int contador = 0;
        for (RegistroMantenimiento reg : registros) {
            if (reg.getCliente().getId().equals(idCliente)) {
                contador++;
                System.out.println("\n   " + contador + ". Fecha: " + reg.getFecha());
                System.out.println("     Maquinaria: " + reg.getMaquinaria().getNombre());
                System.out.println("     Tecnico: " + reg.getTecnico().getNombre());
                System.out.println("     Servicio: " + reg.getTipoServicio());
            }
        }
        
        if (contador == 0) {
            System.out.println("   No hay registros de mantenimiento para este cliente.");
        } else {
            System.out.println("\n Total de mantenimientos realizados: " + contador);
        }
        System.out.println("--------------------------------------------\n");
    }
    
    // Reporte de Técnicos
    public void reporteTecnicos() {
        System.out.println("\n--------------------------------------------");
        System.out.println("         REPORTE DE DESEMPENO DE TECNICOS       ");
        System.out.println("--------------------------------------------\n");
        
        if (tecnicos.isEmpty()) {
            System.out.println(" No hay tecnicos registrados.");
            return;
        }
        
        System.out.println(" ESTADISTICAS POR TECNICO:\n");
        
        for (Tecnico tec : tecnicos) {
            System.out.println("--------------------------------------------");
            System.out.println(" Tecnico: " + tec.getNombre());
            System.out.println("   ID: " + tec.getId());
            System.out.println("   Especialidad: " + tec.getEspecialidad());
            System.out.println("   Total de mantenimientos: " + tec.getMantenimientosRealizados());
            
            // Contar servicios por tipo para este técnico
            java.util.HashMap<String, Integer> serviciosPorTipo = new java.util.HashMap<>();
            for (RegistroMantenimiento reg : registros) {
                if (reg.getTecnico().getId().equals(tec.getId())) {
                    String tipo = reg.getTipoServicio();
                    serviciosPorTipo.put(tipo, serviciosPorTipo.getOrDefault(tipo, 0) + 1);
                }
            }
            
            if (!serviciosPorTipo.isEmpty()) {
                System.out.println("   Servicios realizados: ");
                for (java.util.Map.Entry<String, Integer> entry : serviciosPorTipo.entrySet()) {
                    System.out.println("      • " + entry.getKey() + ": " + entry.getValue());
                }
            }
        }
        
        System.out.println("--------------------------------------------");
        System.out.println("\n Reporte generado exitosamente.\n");
    }
    
    // Reporte de Servicios Más Utilizados
    public void reporteServiciosMasUtilizados() {
        System.out.println("\n--------------------------------------------");
        System.out.println("        REPORTE DE SERVICIOS MAS UTILIZADOS       ");
        System.out.println("--------------------------------------------\n");
        
        if (registros.isEmpty()) {
            System.out.println(" No hay registros de mantenimiento.");
            return;
        }
        
        // Contar servicios
        java.util.HashMap<String, Integer> conteoServicios = new java.util.HashMap<>();
        for (RegistroMantenimiento reg : registros) {
            String tipo = reg.getTipoServicio();
            conteoServicios.put(tipo, conteoServicios.getOrDefault(tipo, 0) + 1);
        }
        
        // Convertir a lista y ordenar
        java.util.List<java.util.Map.Entry<String, Integer>> listaServicios = 
            new java.util.ArrayList<>(conteoServicios.entrySet());
        listaServicios.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        System.out.println(" RANKING DE SERVICIOS: \n");
        
        int ranking = 1;
        int total = registros.size();
        
        for (java.util.Map.Entry<String, Integer> entry : listaServicios) {
            double porcentaje = (entry.getValue() * 100.0) / total;
             //String barra = generarBarraProgreso(porcentaje);
            
            System.out.println(ranking + ". " + entry.getKey());
            System.out.println("   Cantidad: " + entry.getValue() + " servicios");
            System.out.println("   Porcentaje: " + String.format("%.1f", porcentaje) + "%");
            /*System.out.println("---------------" );
            System.out.println();*/
            ranking++;
        }
        
        System.out.println("--------------------------------------------");
        System.out.println(" Total de servicios realizados: " + total);
        System.out.println("--------------------------------------------\n");
    }
    
    // Método auxiliar para generar barra de progreso
    private String barraProgreso(double porcentaje) {
        int longitud = 30;
        int relleno = (int) (longitud * porcentaje / 100);
        StringBuilder barra = new StringBuilder("[");
        
        for (int i = 0; i < longitud; i++) {
            if (i < relleno) {
                barra.append("█");
            } else {
                barra.append("░");
            }
        }
        barra.append("]");
        return barra.toString();
    }
}



