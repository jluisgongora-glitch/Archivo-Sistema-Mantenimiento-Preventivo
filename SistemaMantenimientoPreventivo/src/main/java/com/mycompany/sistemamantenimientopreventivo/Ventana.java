
package com.mycompany.sistemamantenimientopreventivo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ventana extends JFrame {
    
    private DepartamentoMantenimiento depto;
    
    // Componentes de la interfaz
    private JTabbedPane panelPestanas;
    
    // Pestaña Registro Cliente
    private JTextField txtID, txtNombre, txtEmpresa, txtTelefono, txtEmail;
    private JButton btnRegistrarCliente;
    private JLabel lblMensaje;
    
    // Pestaña Solicitar Servicio
    private JComboBox<String> cmbClientes, cmbMaquinarias, cmbServicios, cmbTecnicos;
    private JTextArea txtDescripcion;
    private JLabel lblPrecioServicio, lblTecnicoInfo;
    private JButton btnSolicitarServicio, btnGenerarFactura;
    
    // Colores cálidos
    private final Color COLOR_PRINCIPAL = new Color(255, 243, 224);  // Crema cálido
    private final Color COLOR_SECUNDARIO = new Color(255, 224, 178); // Durazno
    private final Color COLOR_ACENTO = new Color(255, 183, 77);      // Naranja suave
    private final Color COLOR_TEXTO = new Color(62, 39, 35);         // Marrón oscuro
    private final Color COLOR_BOTON = new Color(255, 167, 38);       // Naranja cálido
    private final Color COLOR_BOTON_HOVER = new Color(251, 140, 0);  // Naranja intenso
    
    // Variables para la factura
    private Cliente clienteSeleccionado;
    private ServicioMantenimiento servicioSeleccionado;
    private Tecnico tecnicoSeleccionado;
    private Maquinaria maquinariaSeleccionada;
    private String numeroUltimaFactura;
    
    public Ventana(DepartamentoMantenimiento depto) {
        this.depto = depto;
        inicializarComponentes();
        aplicarEstilos();
        this.setLocationRelativeTo(null);
    }
    
    private void inicializarComponentes() {
        setTitle("Sistema de Mantenimiento Preventivo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        panelPestanas = new JTabbedPane();
        panelPestanas.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        // Crear pestañas
        panelPestanas.addTab(" Registro Cliente", crearPanelRegistro());
        panelPestanas.addTab(" Solicitar Servicio", crearPanelServicio());
        
        add(panelPestanas);
    }
    
    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(COLOR_PRINCIPAL);
        
        // Título
        JLabel lblTitulo = new JLabel("REGISTRO DE CLIENTE");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(COLOR_TEXTO);
        lblTitulo.setBounds(250, 20, 300, 40);
        panel.add(lblTitulo);
        
        // Formulario
        int yPos = 80;
        int espaciado = 50;
        
        // ID
        JLabel lblID = crearEtiqueta("ID:", 100, yPos);
        panel.add(lblID);
        txtID = crearCampoTexto(250, yPos);
        panel.add(txtID);
        
        // Nombre
        yPos += espaciado;
        JLabel lblNombre = crearEtiqueta("Nombre:", 100, yPos);
        panel.add(lblNombre);
        txtNombre = crearCampoTexto(250, yPos);
        panel.add(txtNombre);
        
        // Empresa
        yPos += espaciado;
        JLabel lblEmpresa = crearEtiqueta("Empresa:", 100, yPos);
        panel.add(lblEmpresa);
        txtEmpresa = crearCampoTexto(250, yPos);
        panel.add(txtEmpresa);
        
        // Teléfono
        yPos += espaciado;
        JLabel lblTelefono = crearEtiqueta("Telefono:", 100, yPos);
        panel.add(lblTelefono);
        txtTelefono = crearCampoTexto(250, yPos);
        panel.add(txtTelefono);
        
        // Email
        yPos += espaciado;
        JLabel lblEmail = crearEtiqueta("Email:", 100, yPos);
        panel.add(lblEmail);
        txtEmail = crearCampoTexto(250, yPos);
        panel.add(txtEmail);
        
        // Botón
        btnRegistrarCliente = crearBoton("REGISTRAR CLIENTE", 280, 380);
        btnRegistrarCliente.addActionListener(e -> registrarCliente());
        panel.add(btnRegistrarCliente);
        
        // Mensaje
        lblMensaje = new JLabel("");
        lblMensaje.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setBounds(150, 440, 500, 30);
        panel.add(lblMensaje);
        
        return panel;
    }
    
    private JPanel crearPanelServicio() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(COLOR_PRINCIPAL);
        
        // Título
        JLabel lblTitulo = new JLabel("SOLICITUD DE SERVICIO");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(COLOR_TEXTO);
        lblTitulo.setBounds(230, 20, 350, 40);
        panel.add(lblTitulo);
        
        int yPos = 80;
        int espaciado = 60;
        
        // Cliente
        JLabel lblCliente = crearEtiqueta("Cliente:", 50, yPos);
        panel.add(lblCliente);
        cmbClientes = crearComboBox(200, yPos, 500);
        cmbClientes.addActionListener(e -> actualizarMaquinarias());
        panel.add(cmbClientes);
        
        // Maquinaria
        yPos += espaciado;
        JLabel lblMaquinaria = crearEtiqueta("Maquinaria:", 50, yPos);
        panel.add(lblMaquinaria);
        cmbMaquinarias = crearComboBox(200, yPos, 500);
        panel.add(cmbMaquinarias);
        
        // Servicio
        yPos += espaciado;
        JLabel lblServicio = crearEtiqueta("Servicio:", 50, yPos);
        panel.add(lblServicio);
        cmbServicios = crearComboBox(200, yPos, 350);
        cmbServicios.addActionListener(e -> actualizarPrecioServicio());
        panel.add(cmbServicios);
        
        lblPrecioServicio = new JLabel("");
        lblPrecioServicio.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblPrecioServicio.setForeground(new Color(46, 125, 50));
        lblPrecioServicio.setBounds(560, yPos, 150, 30);
        panel.add(lblPrecioServicio);
        
        // Técnico
        yPos += espaciado;
        JLabel lblTecnico = crearEtiqueta("Tecnico:", 50, yPos);
        panel.add(lblTecnico);
        cmbTecnicos = crearComboBox(200, yPos, 500);
        cmbTecnicos.addActionListener(e -> actualizarInfoTecnico());
        panel.add(cmbTecnicos);
        
        lblTecnicoInfo = new JLabel("");
        lblTecnicoInfo.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblTecnicoInfo.setForeground(COLOR_TEXTO);
        lblTecnicoInfo.setBounds(200, yPos + 35, 500, 20);
        panel.add(lblTecnicoInfo);
        
        // Descripción
        yPos += espaciado + 20;
        JLabel lblDescripcion = crearEtiqueta("Observaciones:", 50, yPos);
        panel.add(lblDescripcion);
        
        txtDescripcion = new JTextArea();
        txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        scrollDescripcion.setBounds(200, yPos, 500, 60);
        panel.add(scrollDescripcion);
        
        // Botones
        btnSolicitarServicio = crearBoton("SOLICITAR SERVICIO", 200, 450);
        btnSolicitarServicio.addActionListener(e -> solicitarServicio());
        panel.add(btnSolicitarServicio);
        
        btnGenerarFactura = crearBoton("GENERAR FACTURA PDF", 420, 450);
        btnGenerarFactura.addActionListener(e -> generarFacturaPDF());
        btnGenerarFactura.setEnabled(false);
        panel.add(btnGenerarFactura);
        
        // Cargar datos iniciales
        cargarClientes();
        cargarServicios();
        cargarTecnicos();
        
        return panel;
    }
    
    private void registrarCliente() {
        try {
            // Validar campos
            if (txtID.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()) {
                mostrarMensaje("Por favor complete los campos obligatorios", Color.RED);
                return;
            }
            
            String id = txtID.getText().trim();
            String nombre = txtNombre.getText().trim();
            String empresa = txtEmpresa.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String email = txtEmail.getText().trim();
            
            // Registrar en el sistema
            Cliente cliente = new Cliente(id, nombre, empresa, telefono, email);
            depto.agregarCliente(cliente);
            
            // Registrar en la base de datos
            try {
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_smp", "root", "");
                PreparedStatement pst = cn.prepareStatement("INSERT INTO cliente VALUES(?,?,?,?,?)");
                pst.setString(1, id);
                pst.setString(2, nombre);
                pst.setString(3, empresa);
                pst.setString(4, telefono);
                pst.setString(5, email);
                pst.executeUpdate();
                cn.close();
            } catch (SQLException ex) {
                System.err.println("Error BD: " + ex.getMessage());
            }
            
            // Limpiar campos
            txtID.setText("");
            txtNombre.setText("");
            txtEmpresa.setText("");
            txtTelefono.setText("");
            txtEmail.setText("");
            
            mostrarMensaje(" Cliente registrado exitosamente", new Color(46, 125, 50));
            
            // Actualizar combo de clientes
            cargarClientes();
            
        } catch (Exception ex) {
            mostrarMensaje(" Error al registrar cliente", Color.RED);
            ex.printStackTrace();
        }
    }
    
    private void solicitarServicio() {
        try {
            if (cmbClientes.getSelectedIndex() == 0 || cmbMaquinarias.getSelectedIndex() == 0 ||
                cmbServicios.getSelectedIndex() == 0 || cmbTecnicos.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Por favor complete todos los campos", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Obtener datos seleccionados
            String idCliente = cmbClientes.getSelectedItem().toString().split(" - ")[0];
            String idMaquina = cmbMaquinarias.getSelectedItem().toString().split(" - ")[0];
            String codigoServicio = cmbServicios.getSelectedItem().toString().split(" - ")[0];
            String idTecnico = cmbTecnicos.getSelectedItem().toString().split(" - ")[0];
            
            clienteSeleccionado = depto.buscarCliente(idCliente);
            maquinariaSeleccionada = depto.buscarMaquinaria(idMaquina);
            servicioSeleccionado = depto.buscarServicio(codigoServicio);
            tecnicoSeleccionado = depto.buscarTecnico(idTecnico);
            
            // Confirmar servicio
            String mensaje = String.format(
                "¿Confirmar el servicio?\n\n" +
                "Cliente: %s\n" +
                "Servicio: %s\n" +
                "Precio: $%.2f\n" +
                "Técnico: %s",
                clienteSeleccionado.getNombre(),
                servicioSeleccionado.getNombre(),
                servicioSeleccionado.getPrecio(),
                tecnicoSeleccionado.getNombre()
            );
            
            int confirmacion = JOptionPane.showConfirmDialog(this, mensaje, 
                "Confirmar Servicio", JOptionPane.YES_NO_OPTION);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                // Realizar mantenimiento
                tecnicoSeleccionado.realizarMantenimiento(maquinariaSeleccionada);
                
                // Registrar
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fecha = sdf.format(new Date());
                String descripcion = txtDescripcion.getText().trim();
                
                depto.registroMantenimiento(fecha, maquinariaSeleccionada, tecnicoSeleccionado, 
                                          clienteSeleccionado, descripcion, servicioSeleccionado);
                
                JOptionPane.showMessageDialog(this, " Servicio registrado exitosamente", 
                                            "Exito", JOptionPane.INFORMATION_MESSAGE);
                
                // Habilitar botón de factura
                btnGenerarFactura.setEnabled(true);
                
                // Limpiar
                txtDescripcion.setText("");
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, " Error al solicitar servicio: " + ex.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void generarFacturaPDF() {
        if (clienteSeleccionado == null || servicioSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe solicitar un servicio primero", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            numeroUltimaFactura = "FAC-" + sdf.format(new Date());
            String rutaFactura = "Factura_" + numeroUltimaFactura + ".pdf";
            
            GeneradorPDF.generarFactura(clienteSeleccionado, servicioSeleccionado, 
                                       tecnicoSeleccionado, maquinariaSeleccionada, 
                                       numeroUltimaFactura, rutaFactura);
            
            JOptionPane.showMessageDialog(this, 
                " Factura generada exitosamente:\n" + rutaFactura, 
                "Exito", JOptionPane.INFORMATION_MESSAGE);
            
            btnGenerarFactura.setEnabled(false);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, " Error al generar factura: " + ex.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void cargarClientes() {
        cmbClientes.removeAllItems();
        cmbClientes.addItem("Seleccione un cliente...");
        for (Cliente c : depto.getClientes()) {
            cmbClientes.addItem(c.getId() + " - " + c.getNombre() + " (" + c.getEmpresa() + ")");
        }
    }
    
    private void cargarServicios() {
        cmbServicios.removeAllItems();
        cmbServicios.addItem("Seleccione un servicio...");
        for (ServicioMantenimiento s : depto.getServicios()) {
            cmbServicios.addItem(s.getCodigo() + " - " + s.getNombre());
        }
    }
    
    private void cargarTecnicos() {
        cmbTecnicos.removeAllItems();
        cmbTecnicos.addItem("Seleccione un tecnico...");
        for (Tecnico t : depto.getTecnicos()) {
            cmbTecnicos.addItem(t.getId() + " - " + t.getNombre());
        }
    }
    
    private void actualizarMaquinarias() {
        cmbMaquinarias.removeAllItems();
        cmbMaquinarias.addItem("Seleccione una maquinaria...");
        
        if (cmbClientes.getSelectedIndex() > 0) {
            String idCliente = cmbClientes.getSelectedItem().toString().split(" - ")[0];
            Cliente cliente = depto.buscarCliente(idCliente);
            if (cliente != null) {
                for (Maquinaria m : cliente.getMaquinarias()) {
                    cmbMaquinarias.addItem(m.getId() + " - " + m.getNombre());
                }
            }
        }
    }
    
    private void actualizarPrecioServicio() {
        if (cmbServicios.getSelectedIndex() > 0) {
            String codigo = cmbServicios.getSelectedItem().toString().split(" - ")[0];
            ServicioMantenimiento s = depto.buscarServicio(codigo);
            if (s != null) {
                lblPrecioServicio.setText("$" + String.format("%.2f", s.getPrecio()));
            }
        } else {
            lblPrecioServicio.setText("");
        }
    }
    
    private void actualizarInfoTecnico() {
        if (cmbTecnicos.getSelectedIndex() > 0) {
            String id = cmbTecnicos.getSelectedItem().toString().split(" - ")[0];
            Tecnico t = depto.buscarTecnico(id);
            if (t != null) {
                lblTecnicoInfo.setText("Especialidad: " + t.getEspecialidad() + 
                                      " | Servicios realizados: " + t.getMantenimientosRealizados());
            }
        } else {
            lblTecnicoInfo.setText("");
        }
    }
    
    private void mostrarMensaje(String mensaje, Color color) {
        lblMensaje.setText(mensaje);
        lblMensaje.setForeground(color);
        
        Timer timer = new Timer(3000, e -> lblMensaje.setText(""));
        timer.setRepeats(false);
        timer.start();
    }
    
    // Métodos de creación de componentes con estilo
    private JLabel crearEtiqueta(String texto, int x, int y) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setForeground(COLOR_TEXTO);
        lbl.setBounds(x, y, 140, 30);
        return lbl;
    }
    
    private JTextField crearCampoTexto(int x, int y) {
        JTextField txt = new JTextField();
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txt.setBounds(x, y, 350, 35);
        txt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_ACENTO, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return txt;
    }
    
    private JComboBox<String> crearComboBox(int x, int y, int ancho) {
        JComboBox<String> cmb = new JComboBox<>();
        cmb.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cmb.setBounds(x, y, ancho, 35);
        cmb.setBackground(Color.WHITE);
        return cmb;
    }
    
    private JButton crearBoton(String texto, int x, int y) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBounds(x, y, 200, 40);
        btn.setBackground(COLOR_BOTON);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(COLOR_BOTON_HOVER);
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(COLOR_BOTON);
            }
        });
        
        return btn;
    }
    
    private void aplicarEstilos() {
        getContentPane().setBackground(COLOR_PRINCIPAL);
        panelPestanas.setBackground(COLOR_SECUNDARIO);
    }
    
    // Main para ejecutar la ventana directamente (con datos de prueba)
    public static void main(String[] args) {
        // Crear departamento de prueba
        DepartamentoMantenimiento deptoTemp = new DepartamentoMantenimiento("Mantenimiento Industrial");
        
        // Agregar técnicos
        deptoTemp.agregarTecnico(new Tecnico("T001", "Carlos Mendez", "Mecanica Industrial"));
        deptoTemp.agregarTecnico(new Tecnico("T002", "Ana Rodriguez", "Sistemas Electricos"));
        deptoTemp.agregarTecnico(new Tecnico("T003", "Luis Fernandez", "Mantenimiento Vehicular"));
        
        // Agregar clientes de prueba
        Cliente cliente1 = new Cliente("C001", "Juan Perez", "Industrias MetalCorp", 
                                       "555-0101", "juan.perez@metalcorp.com");
        Cliente cliente2 = new Cliente("C002", "Maria Garcia", "Logistica Express", 
                                       "555-0202", "maria.garcia@logexpress.com");
        
        deptoTemp.agregarCliente(cliente1);
        deptoTemp.agregarCliente(cliente2);
        
        // Agregar maquinarias
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
        
        deptoTemp.agregarMaquinaria(torno);
        deptoTemp.agregarMaquinaria(montacarga);
        deptoTemp.agregarMaquinaria(taladro);
        
        System.out.println(" Datos de prueba cargados");
        System.out.println(" Abriendo ventana grafica...");
        
        // Abrir ventana
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Ventana ventana = new Ventana(deptoTemp);
                ventana.setVisible(true);
            }
        });
    }
    
    
    
    
}
