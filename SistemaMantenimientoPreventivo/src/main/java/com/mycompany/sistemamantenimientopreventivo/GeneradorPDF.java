
package com.mycompany.sistemamantenimientopreventivo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneradorPDF {
    
    private static Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
    private static Font subtituloFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
    private static Font normalFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK);
    private static Font boldFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK);
    
    // REPORTE DE CLIENTE
    public static void generarReporteCliente(Cliente cliente, 
                                             java.util.ArrayList<RegistroMantenimiento> registros,
                                             String rutaArchivo) {
        try {
            Document documento = new Document(PageSize.LETTER);
            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
            documento.open();
            
            // Encabezado
            agregarEncabezado(documento, "REPORTE DE CLIENTE");
            
            // Información del cliente
            Paragraph infoCliente = new Paragraph();
            infoCliente.add(new Phrase("DATOS DEL CLIENTE\n\n", subtituloFont));
            infoCliente.add(new Phrase("ID: ", boldFont));
            infoCliente.add(new Phrase(cliente.getId() + "\n", normalFont));
            infoCliente.add(new Phrase("Nombre: ", boldFont));
            infoCliente.add(new Phrase(cliente.getNombre() + "\n", normalFont));
            infoCliente.add(new Phrase("Empresa: ", boldFont));
            infoCliente.add(new Phrase(cliente.getEmpresa() + "\n", normalFont));
            infoCliente.add(new Phrase("Telefono: ", boldFont));
            infoCliente.add(new Phrase(cliente.getTelefono() + "\n", normalFont));
            infoCliente.add(new Phrase("Email: ", boldFont));
            infoCliente.add(new Phrase(cliente.getEmail() + "\n\n", normalFont));
            documento.add(infoCliente);
            
            // Maquinarias
            documento.add(new Paragraph("MAQUINARIAS REGISTRADAS\n\n", subtituloFont));
            PdfPTable tablaMaquinas = new PdfPTable(2);
            tablaMaquinas.setWidthPercentage(100);
            tablaMaquinas.addCell(crearCeldaEncabezado("ID"));
            tablaMaquinas.addCell(crearCeldaEncabezado("Nombre"));
            for (Maquinaria m : cliente.getMaquinarias()) {
                tablaMaquinas.addCell(m.getId());
                tablaMaquinas.addCell(m.getNombre());
            }
            documento.add(tablaMaquinas);
            documento.add(new Paragraph("\n"));
            
            // Historial de mantenimientos
            documento.add(new Paragraph("HISTORIAL DE MANTENIMIENTOS\n\n", subtituloFont));
            PdfPTable tablaMantenimientos = new PdfPTable(4);
            tablaMantenimientos.setWidthPercentage(100);
            tablaMantenimientos.addCell(crearCeldaEncabezado("Fecha"));
            tablaMantenimientos.addCell(crearCeldaEncabezado("Maquinaria"));
            tablaMantenimientos.addCell(crearCeldaEncabezado("Tecnico"));
            tablaMantenimientos.addCell(crearCeldaEncabezado("Servicio"));
            
            int total = 0;
            for (RegistroMantenimiento reg : registros) {
                if (reg.getCliente().getId().equals(cliente.getId())) {
                    tablaMantenimientos.addCell(reg.getFecha());
                    tablaMantenimientos.addCell(reg.getMaquinaria().getNombre());
                    tablaMantenimientos.addCell(reg.getTecnico().getNombre());
                    tablaMantenimientos.addCell(reg.getTipoServicio());
                    total++;
                }
            }
            documento.add(tablaMantenimientos);
            
            Paragraph resumen = new Paragraph("\n\nTotal de mantenimientos: " + total, boldFont);
            documento.add(resumen);
            
            agregarPiePagina(documento);
            documento.close();
            
            System.out.println(" PDF generado: " + rutaArchivo);
            
        } catch (Exception e) {
            System.err.println("Error al generar PDF: " + e.getMessage());
        }
    }
    
    // REPORTE DE TÉCNICOS
    public static void generarReporteTecnicos(java.util.ArrayList<Tecnico> tecnicos,
                                              java.util.ArrayList<RegistroMantenimiento> registros,
                                              String rutaArchivo) {
        try {
            Document documento = new Document(PageSize.LETTER);
            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
            documento.open();
            
            agregarEncabezado(documento, "REPORTE DE DESEMPENO DE TECNICOS");
            
            for (Tecnico tec : tecnicos) {
                Paragraph infoTecnico = new Paragraph();
                infoTecnico.add(new Phrase("\nTECNICO: " + tec.getNombre() + "\n", subtituloFont));
                infoTecnico.add(new Phrase("ID: ", boldFont));
                infoTecnico.add(new Phrase(tec.getId() + "\n", normalFont));
                infoTecnico.add(new Phrase("Especialidad: ", boldFont));
                infoTecnico.add(new Phrase(tec.getEspecialidad() + "\n", normalFont));
                infoTecnico.add(new Phrase("Mantenimientos realizados: ", boldFont));
                infoTecnico.add(new Phrase(tec.getMantenimientosRealizados() + "\n\n", normalFont));
                documento.add(infoTecnico);
                
                // Servicios realizados
                java.util.HashMap<String, Integer> servicios = new java.util.HashMap<>();
                for (RegistroMantenimiento reg : registros) {
                    if (reg.getTecnico().getId().equals(tec.getId())) {
                        String tipo = reg.getTipoServicio();
                        servicios.put(tipo, servicios.getOrDefault(tipo, 0) + 1);
                    }
                }
                
                if (!servicios.isEmpty()) {
                    PdfPTable tabla = new PdfPTable(2);
                    tabla.setWidthPercentage(70);
                    tabla.addCell(crearCeldaEncabezado("Tipo de Servicio"));
                    tabla.addCell(crearCeldaEncabezado("Cantidad"));
                    for (java.util.Map.Entry<String, Integer> entry : servicios.entrySet()) {
                        tabla.addCell(entry.getKey());
                        tabla.addCell(entry.getValue().toString());
                    }
                    documento.add(tabla);
                }
                
                documento.add(new Paragraph("\n" + "─".repeat(50) + "\n"));
            }
            
            agregarPiePagina(documento);
            documento.close();
            
            System.out.println(" PDF generado: " + rutaArchivo);
            
        } catch (Exception e) {
            System.err.println("Error al generar PDF: " + e.getMessage());
        }
    }
    
    // REPORTE DE SERVICIOS MÁS UTILIZADOS
    public static void generarReporteServicios(java.util.ArrayList<RegistroMantenimiento> registros,
                                               String rutaArchivo) {
        try {
            Document documento = new Document(PageSize.LETTER);
            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
            documento.open();
            
            agregarEncabezado(documento, "REPORTE DE SERVICIOS MÁS UTILIZADOS");
            
            // Contar servicios
            java.util.HashMap<String, Integer> conteo = new java.util.HashMap<>();
            for (RegistroMantenimiento reg : registros) {
                String tipo = reg.getTipoServicio();
                conteo.put(tipo, conteo.getOrDefault(tipo, 0) + 1);
            }
            
            // Ordenar
            java.util.List<java.util.Map.Entry<String, Integer>> lista = 
                new java.util.ArrayList<>(conteo.entrySet());
            lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));
            
            // Crear tabla
            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(90);
            tabla.addCell(crearCeldaEncabezado("Ranking"));
            tabla.addCell(crearCeldaEncabezado("Servicio"));
            tabla.addCell(crearCeldaEncabezado("Cantidad"));
            
            int ranking = 1;
            int total = registros.size();
            for (java.util.Map.Entry<String, Integer> entry : lista) {
                double porcentaje = (entry.getValue() * 100.0) / total;
                tabla.addCell(String.valueOf(ranking));
                tabla.addCell(entry.getKey() + " (" + String.format("%.1f%%", porcentaje) + ")");
                tabla.addCell(entry.getValue().toString());
                ranking++;
            }
            
            documento.add(tabla);
            
            Paragraph totalServicios = new Paragraph("\n\nTotal de servicios realizados: " + total, boldFont);
            documento.add(totalServicios);
            
            agregarPiePagina(documento);
            documento.close();
            
            System.out.println(" PDF generado: " + rutaArchivo);
            
        } catch (Exception e) {
            System.err.println("Error al generar PDF: " + e.getMessage());
        }
    }
    
    // FACTURA
    public static void generarFactura(Cliente cliente, ServicioMantenimiento servicio,
                                      Tecnico tecnico, Maquinaria maquinaria,
                                      String numeroFactura, String rutaArchivo) {
        try {
            Document documento = new Document(PageSize.LETTER);
            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
            documento.open();
            
            // Título
            Paragraph titulo = new Paragraph("FACTURA DE SERVICIO\n\n", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            
            // Número de factura y fecha
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Paragraph info = new Paragraph();
            info.add(new Phrase("Factura N°: ", boldFont));
            info.add(new Phrase(numeroFactura + "\n", normalFont));
            info.add(new Phrase("Fecha: ", boldFont));
            info.add(new Phrase(sdf.format(new Date()) + "\n\n", normalFont));
            documento.add(info);
            
            // Datos del cliente
            documento.add(new Paragraph("DATOS DEL CLIENTE", subtituloFont));
            Paragraph datosCliente = new Paragraph();
            datosCliente.add(new Phrase("Nombre: ", boldFont));
            datosCliente.add(new Phrase(cliente.getNombre() + "\n", normalFont));
            datosCliente.add(new Phrase("Empresa: ", boldFont));
            datosCliente.add(new Phrase(cliente.getEmpresa() + "\n", normalFont));
            datosCliente.add(new Phrase("Telefono: ", boldFont));
            datosCliente.add(new Phrase(cliente.getTelefono() + "\n", normalFont));
            datosCliente.add(new Phrase("Email: ", boldFont));
            datosCliente.add(new Phrase(cliente.getEmail() + "\n\n", normalFont));
            documento.add(datosCliente);
            
            // Detalles del servicio
            documento.add(new Paragraph("DETALLE DEL SERVICIO\n\n", subtituloFont));
            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{3, 1, 2, 2});
            tabla.addCell(crearCeldaEncabezado("Descripcion"));
            tabla.addCell(crearCeldaEncabezado("Cant."));
            tabla.addCell(crearCeldaEncabezado("Precio Unit."));
            tabla.addCell(crearCeldaEncabezado("Total"));
            
            tabla.addCell(servicio.getNombre() + "\n" + servicio.getDescripcion());
            tabla.addCell("1");
            tabla.addCell("$" + String.format("%.2f", servicio.getPrecio()));
            tabla.addCell("$" + String.format("%.2f", servicio.getPrecio()));
            
            documento.add(tabla);
            
            // Información adicional
            Paragraph adicional = new Paragraph();
            adicional.add(new Phrase("\n\nMaquinaria: ", boldFont));
            adicional.add(new Phrase(maquinaria.getNombre() + "\n", normalFont));
            adicional.add(new Phrase("Tecnico asignado: ", boldFont));
            adicional.add(new Phrase(tecnico.getNombre() + " (" + tecnico.getEspecialidad() + ")\n", normalFont));
            adicional.add(new Phrase("Duracion estimada: ", boldFont));
            adicional.add(new Phrase(servicio.getDuracionHoras() + " horas\n\n", normalFont));
            documento.add(adicional);
            
            // Total
            Paragraph total = new Paragraph();
            total.setAlignment(Element.ALIGN_RIGHT);
            total.add(new Phrase("TOTAL A PAGAR: $" + String.format("%.2f", servicio.getPrecio()) + "\n\n", 
                                new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK)));
            documento.add(total);
            
            // Pie
            Paragraph pie = new Paragraph("\n\nGracias por confiar en nuestros servicios.\n" +
                                         "Sistema de Mantenimiento Preventivo", 
                                         new Font(Font.FontFamily.HELVETICA, 9, Font.ITALIC, BaseColor.GRAY));
            pie.setAlignment(Element.ALIGN_CENTER);
            documento.add(pie);
            
            documento.close();
            
            System.out.println(" Factura generada: " + rutaArchivo);
            
        } catch (Exception e) {
            System.err.println("Error al generar factura: " + e.getMessage());
        }
    }
    
    // Métodos auxiliares
    private static void agregarEncabezado(Document doc, String titulo) throws DocumentException {
        Paragraph header = new Paragraph();
        header.add(new Phrase("SISTEMA DE MANTENIMIENTO PREVENTIVO\n", 
                             new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.GRAY)));
        header.add(new Phrase(titulo + "\n\n", tituloFont));
        header.setAlignment(Element.ALIGN_CENTER);
        doc.add(header);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Paragraph fecha = new Paragraph("Fecha de generacion: " + sdf.format(new Date()) + "\n\n", 
                                       new Font(Font.FontFamily.HELVETICA, 9, Font.ITALIC, BaseColor.GRAY));
        doc.add(fecha);
    }
    
    private static void agregarPiePagina(Document doc) throws DocumentException {
        Paragraph pie = new Paragraph("\n\n" + "─".repeat(70) + "\n" +
                                     "Documento generado automaticamente por el Sistema de Mantenimiento Preventivo",
                                     new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC, BaseColor.GRAY));
        pie.setAlignment(Element.ALIGN_CENTER);
        doc.add(pie);
    }
    
    private static PdfPCell crearCeldaEncabezado(String texto) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, boldFont));
        celda.setBackgroundColor(new BaseColor(220, 220, 220));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(5);
        return celda;
    }
    
}
