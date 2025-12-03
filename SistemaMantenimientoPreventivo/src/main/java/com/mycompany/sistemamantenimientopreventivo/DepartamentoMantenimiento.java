
package com.mycompany.sistemamantenimientopreventivo;


public class DepartamentoMantenimiento {
    
    private String nombre;
    private java.util.ArrayList<Tecnico> tecnicos;
    private java.util.ArrayList<Maquinaria> maquinarias;
    private java.util.ArrayList<Cliente> clientes;
    private java.util.ArrayList<RegistroMantenimiento> registros;
    private java.util.ArrayList<ServicioMantenimiento> servicios;
    
    public DepartamentoMantenimiento(String nombre) {
        this.nombre = nombre;
        this.tecnicos = new java.util.ArrayList<>();
        this.maquinarias = new java.util.ArrayList<>();
        this.clientes = new java.util.ArrayList<>();
        this.registros = new java.util.ArrayList<>();
        this.servicios = new java.util.ArrayList<>();
        inicializarServicios();
    }
    
    private void inicializarServicios() {
        servicios.add(new ServicioMantenimiento("SRV001", "Mantenimiento Industrial", 
            "Lubricacion, inspeccion electrica y calibracion", 250.00, 4));
        servicios.add(new ServicioMantenimiento("SRV002", "Mantenimiento Vehicular", 
            "Cambio de aceite, revision de frenos e inspeccion", 180.00, 3));
        servicios.add(new ServicioMantenimiento("SRV003", "Mantenimiento de Herramientas", 
            "Limpieza, revision de cableado y ajustes", 120.00, 2));
        servicios.add(new ServicioMantenimiento("SRV004", "Inspeccion General", 
            "Diagnostico completo del equipo", 80.00, 1));
        servicios.add(new ServicioMantenimiento("SRV005", "Mantenimiento Correctivo", 
            "Reparacion de fallas y reemplazo de componentes", 350.00, 6));
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
    
    public void agregarServicio(ServicioMantenimiento servicio) {
        servicios.add(servicio);
    }
    
    public void registroMantenimiento(String fecha, Maquinaria maquina, 
                                      Tecnico tecnico, Cliente cliente, 
                                      String descripcion, ServicioMantenimiento servicio) {
        RegistroMantenimiento registro = new RegistroMantenimiento(
            fecha, maquina, tecnico, cliente, descripcion, servicio);
        registros.add(registro);
    }
    
    // Getters
    public java.util.ArrayList<Tecnico> getTecnicos() { return tecnicos; }
    public java.util.ArrayList<Cliente> getClientes() { return clientes; }
    public java.util.ArrayList<Maquinaria> getMaquinarias() { return maquinarias; }
    public java.util.ArrayList<RegistroMantenimiento> getRegistros() { return registros; }
    public java.util.ArrayList<ServicioMantenimiento> getServicios() { return servicios; }
    
    // Buscar elementos
    public Cliente buscarCliente(String id) {
        for (Cliente c : clientes) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }
    
    public Tecnico buscarTecnico(String id) {
        for (Tecnico t : tecnicos) {
            if (t.getId().equals(id)) return t;
        }
        return null;
    }
    
    public Maquinaria buscarMaquinaria(String id) {
        for (Maquinaria m : maquinarias) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }
    
    public ServicioMantenimiento buscarServicio(String codigo) {
        for (ServicioMantenimiento s : servicios) {
            if (s.getCodigo().equals(codigo)) return s;
        }
        return null;
    }
    
    public void mostrarServicios() {
        System.out.println("\n--------------------------------------------");
        System.out.println("      SERVICIOS DE MANTENIMIENTO DISPONIBLES   ");
        System.out.println("--------------------------------------------\n");
        for (ServicioMantenimiento s : servicios) {
            System.out.println("  [" + s.getCodigo() + "] " + s.getNombre());
            System.out.println("  Precio: $" + String.format("%.2f", s.getPrecio()) + 
                             " | Duracion: " + s.getDuracionHoras() + "h");
            System.out.println("  " + s.getDescripcion());
            System.out.println("--------------------------------------------");
        }
    }
    
    public void mostrarTecnicos() {
        System.out.println("\n--------------------------------------------");
        System.out.println("           TECNICOS DISPONIBLES                 ");
        System.out.println("--------------------------------------------\n");
        for (Tecnico t : tecnicos) {
            System.out.println("  [" + t.getId() + "] " + t.getNombre());
            System.out.println("  Especialidad: " + t.getEspecialidad());
            System.out.println("  Servicios realizados: " + t.getMantenimientosRealizados());
            System.out.println("-----------------------------------------");
        }
    }
    
    public void verificarMantenimientosPendientes() {
        System.out.println("\n--- Verificacion de Mantenimientos Pendientes ---");
        for (Maquinaria m : maquinarias) {
            if (m.requiereMantenimiento()) {
                System.out.println("⚠ " + m.getNombre() + " requiere mantenimiento!");
            }
        }
    }
    
    public void mostrarEstadoDepartamento() {
        System.out.println("\n--------------------------------------------");
        System.out.println("  DEPARTAMENTO DE MANTENIMIENTO: " + nombre);
        System.out.println("--------------------------------------------");
        System.out.println("  Tecnicos: " + tecnicos.size());
        System.out.println("  Clientes: " + clientes.size());
        System.out.println("  Maquinarias: " + maquinarias.size());
        System.out.println("  Servicios: " + servicios.size());
        System.out.println("  Registros: " + registros.size());
        System.out.println("--------------------------------------------\n");
    }
    
    // Métodos para generar PDFs
    public void generarPDFCliente(String idCliente, String rutaArchivo) {
        Cliente cliente = buscarCliente(idCliente);
        if (cliente != null) {
            GeneradorPDF.generarReporteCliente(cliente, registros, rutaArchivo);
        } else {
            System.out.println(" Cliente no encontrado.");
        }
    }
    
    public void generarPDFTecnicos(String rutaArchivo) {
        GeneradorPDF.generarReporteTecnicos(tecnicos, registros, rutaArchivo);
    }
    
    public void generarPDFServicios(String rutaArchivo) {
        GeneradorPDF.generarReporteServicios(registros, rutaArchivo);
    }
    
    // Reportes en consola (mantener los originales)
    public void reporteCliente(String idCliente) {
        System.out.println("\n-------------------------------------------");
        System.out.println("       REPORTE DE MANTENIMIENTOS POR CLIENTE       ");
        System.out.println("--------------------------------------------");
        
        Cliente clienteEncontrado = buscarCliente(idCliente);
        
        if (clienteEncontrado == null) {
            System.out.println(" Cliente no encontrado.");
            return;
        }
        
        System.out.println("\n DATOS DEL CLIENTE:");
        System.out.println("   Nombre: " + clienteEncontrado.getNombre());
        System.out.println("   Empresa: " + clienteEncontrado.getEmpresa());
        System.out.println("   Telefono: " + clienteEncontrado.getTelefono());
        System.out.println("   Email: " + clienteEncontrado.getEmail());
        
        System.out.println("\n MAQUINARIAS REGISTRADAS:");
        for (Maquinaria m : clienteEncontrado.getMaquinarias()) {
            System.out.println("   • " + m.getNombre() + " (ID: " + m.getId() + ")");
        }
        
        System.out.println("\n HISTORIAL DE MANTENIMIENTOS:");
        int contador = 0;
        double totalCosto = 0;
        for (RegistroMantenimiento reg : registros) {
            if (reg.getCliente().getId().equals(idCliente)) {
                contador++;
                System.out.println("\n   " + contador + ". Fecha: " + reg.getFecha());
                System.out.println("      Maquinaria: " + reg.getMaquinaria().getNombre());
                System.out.println("      Tecnico: " + reg.getTecnico().getNombre());
                System.out.println("      Servicio: " + reg.getTipoServicio());
                if (reg.getServicio() != null) {
                    System.out.println("      Costo: $" + String.format("%.2f", reg.getServicio().getPrecio()));
                    totalCosto += reg.getServicio().getPrecio();
                }
            }
        }
        
        if (contador == 0) {
            System.out.println("   No hay registros de mantenimiento para este cliente.");
        } else {
            System.out.println("\n Total de mantenimientos: " + contador);
            System.out.println(" Total invertido: $" + String.format("%.2f", totalCosto));
        }
        System.out.println("-------------------------------------------\n");
    }
    
    public void reporteTecnicos() {
        System.out.println("\n--------------------------------------------");
        System.out.println("         REPORTE DE DESEMPENO DE TECNICOS         ");
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
            
            java.util.HashMap<String, Integer> serviciosPorTipo = new java.util.HashMap<>();
            double totalGenerado = 0;
            
            for (RegistroMantenimiento reg : registros) {
                if (reg.getTecnico().getId().equals(tec.getId())) {
                    String tipo = reg.getTipoServicio();
                    serviciosPorTipo.put(tipo, serviciosPorTipo.getOrDefault(tipo, 0) + 1);
                    if (reg.getServicio() != null) {
                        totalGenerado += reg.getServicio().getPrecio();
                    }
                }
            }
            
            if (!serviciosPorTipo.isEmpty()) {
                System.out.println("   Servicios realizados:");
                for (java.util.Map.Entry<String, Integer> entry : serviciosPorTipo.entrySet()) {
                    System.out.println("      • " + entry.getKey() + ": " + entry.getValue());
                }
                System.out.println("    Total generado: $" + String.format("%.2f", totalGenerado));
            }
        }
        
        System.out.println("--------------------------------------------");
        System.out.println("\n Reporte generado exitosamente.\n");
    }
    
    public void reporteServiciosMasUtilizados() {
        System.out.println("\n--------------------------------------------");
        System.out.println("        REPORTE DE SERVICIOS MÁS UTILIZADOS       ");
        System.out.println("--------------------------------------------\n");
        
        if (registros.isEmpty()) {
            System.out.println(" No hay registros de mantenimiento.");
            return;
        }
        
        java.util.HashMap<String, Integer> conteoServicios = new java.util.HashMap<>();
        java.util.HashMap<String, Double> ingresosPorServicio = new java.util.HashMap<>();
        
        for (RegistroMantenimiento reg : registros) {
            String tipo = reg.getTipoServicio();
            conteoServicios.put(tipo, conteoServicios.getOrDefault(tipo, 0) + 1);
            if (reg.getServicio() != null) {
                double actual = ingresosPorServicio.getOrDefault(tipo, 0.0);
                ingresosPorServicio.put(tipo, actual + reg.getServicio().getPrecio());
            }
        }
        
        java.util.List<java.util.Map.Entry<String, Integer>> listaServicios = 
            new java.util.ArrayList<>(conteoServicios.entrySet());
        listaServicios.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        System.out.println(" RANKING DE SERVICIOS:\n");
        
        int ranking = 1;
        int total = registros.size();
        double totalIngresos = 0;
        
        for (java.util.Map.Entry<String, Integer> entry : listaServicios) {
            double porcentaje = (entry.getValue() * 100.0) / total;
            double ingresos = ingresosPorServicio.getOrDefault(entry.getKey(), 0.0);
            totalIngresos += ingresos;
            
            System.out.println(ranking + ". " + entry.getKey());
            System.out.println("   Cantidad: " + entry.getValue() + " servicios");
            System.out.println("   Porcentaje: " + String.format("%.1f%%", porcentaje));
            System.out.println("   Ingresos: $" + String.format("%.2f", ingresos));
            System.out.println();
            ranking++;
        }
        
        System.out.println("--------------------------------------------");
        System.out.println(" Total de servicios realizados: " + total);
        System.out.println(" Ingresos totales: $" + String.format("%.2f", totalIngresos));
        System.out.println("--------------------------------------------\n");
    }
}



