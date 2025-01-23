package reportes;


import clases.apotaciones;
import clases.empleados;
import clases.prestamos;
import consultas.consultas_aportaciones;
import consultas.consultas_empleados;
import consultas.consultas_prestamos;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

public class reporte_financiero_socio {

	public void generarReportePDF(int idEmpleadoSeleccionado, String rutaArchivo) {
	    try {
	        // Instancias de consultas para obtener los datos necesarios
	        consultas_empleados consultasEmp = new consultas_empleados();
	        consultas_aportaciones consultasApor = new consultas_aportaciones();
	        consultas_prestamos consultasPres = new consultas_prestamos();

	        // Obtener datos del empleado
	        empleados empleado = consultasEmp.obtenerDatosEmpleadoPorId(idEmpleadoSeleccionado);
	        if (empleado == null) {
	            System.out.println("No se encontraron datos para el empleado con ID: " + idEmpleadoSeleccionado);
	            return;
	        }

	        // Obtener las aportaciones y préstamos del empleado
	        List<apotaciones> aportaciones = consultasApor.obtenerAportacionesPorEmpleado(idEmpleadoSeleccionado);
	        List<prestamos> prestamos = consultasPres.obtenerPrestamosPorEmpleado(idEmpleadoSeleccionado);

	        // Configuración del PDF
	        PdfWriter writer = new PdfWriter(rutaArchivo);
	        PdfDocument pdf = new PdfDocument(writer);
	        Document document = new Document(pdf, PageSize.A4); // Orientación vertical
	        document.setMargins(20, 20, 20, 20);

	        // Agregar encabezado
	        encabezado_documentos encabezado = new encabezado_documentos();
	        encabezado.agregarEncabezado(document);

	        // Título del reporte
	        document.add(new Paragraph("Reporte Financiero de Socio \n")
	                .setTextAlignment(TextAlignment.CENTER).setFontSize(16).setBold());
	        document.add(new Paragraph(empleado.getNombres_empleado() + " " + empleado.getApellidos_empleado())
	                .setTextAlignment(TextAlignment.CENTER).setFontSize(13).setItalic());

	        // Sección de datos del empleado y fotografía
	        Table datosTable = new Table(new float[]{3, 1}); // 3 columnas para los datos, 1 para la foto
	        datosTable.setWidth(UnitValue.createPercentValue(100));

	        // Datos del empleado
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        String fechaInicio = (empleado.getInicio_empleado() != null) ? dateFormat.format(empleado.getInicio_empleado()) : "No disponible";
	        String fechaRenuncia = (empleado.getRenuncia_empleado() != null) ? dateFormat.format(empleado.getRenuncia_empleado()) : "No disponible";

	        String datos = "Código de socio: " + empleado.getId_empleado() + "\n"
	                     + "Identidad: " + (empleado.getIdentidad_empleado() != null ? empleado.getIdentidad_empleado() : "No disponible") + "\n"
	                     + "Teléfono: " + (empleado.getTel_empleado() != null ? empleado.getTel_empleado() : "No disponible") + "\n"
	                     + "Cuenta bancaria: " + (empleado.getCuenta_empleado() != null ? empleado.getCuenta_empleado() : "No disponible") + "\n"
	                     + "Cargo: " + (empleado.getCargo_empleado() != null ? empleado.getCargo_empleado() : "No disponible") + "\n"
	                     + "Área de trabajo: " + (empleado.getArea_empleado() != null ? empleado.getArea_empleado() : "No disponible") + "\n"
	                     + "Fecha de Inicio: " + fechaInicio + "\n"
	                     + "Fecha de Renuncia: " + fechaRenuncia + (empleado.getRenuncia_empleado() != null ? " (Empleado no activo)" : " (Empleado activo)");

	        datosTable.addCell(new Cell().add(new Paragraph(datos)).setBorder(null));

	        // Fotografía
	        if (empleado.getFotografia_empleado() != null && !empleado.getFotografia_empleado().isEmpty()) {
	            File foto = new File(empleado.getFotografia_empleado());
	            if (foto.exists()) {
	                Image img = new Image(com.itextpdf.io.image.ImageDataFactory.create(foto.getAbsolutePath()));
	                img.setWidth(100);
	                img.setHeight(100);
	                datosTable.addCell(new Cell().add(img).setBorder(null).setHorizontalAlignment(HorizontalAlignment.RIGHT));
	            } else {
	                datosTable.addCell(new Cell().add(new Paragraph("Foto no disponible")).setBorder(null));
	            }
	        } else {
	            datosTable.addCell(new Cell().add(new Paragraph("Foto no disponible")).setBorder(null));
	        }

	        document.add(datosTable);

	        // Aportaciones
	        document.add(new Paragraph("\nAportaciones mensuales:"));
	        Table tablaAportaciones = new Table(new float[]{1, 2, 2, 2, 2, 2});
	        tablaAportaciones.setWidth(UnitValue.createPercentValue(100));
	        tablaAportaciones.addHeaderCell(new Cell().add(new Paragraph("No.").setBold().setTextAlignment(TextAlignment.CENTER)));
	        tablaAportaciones.addHeaderCell(new Cell().add(new Paragraph("Mes").setBold().setTextAlignment(TextAlignment.CENTER)));
	        tablaAportaciones.addHeaderCell(new Cell().add(new Paragraph("Aportación").setBold().setTextAlignment(TextAlignment.CENTER)));
	        tablaAportaciones.addHeaderCell(new Cell().add(new Paragraph("Cuota").setBold().setTextAlignment(TextAlignment.CENTER)));
	        tablaAportaciones.addHeaderCell(new Cell().add(new Paragraph("Interés").setBold().setTextAlignment(TextAlignment.CENTER)));
	        tablaAportaciones.addHeaderCell(new Cell().add(new Paragraph("Total").setBold().setTextAlignment(TextAlignment.CENTER)));
	        
	        int contador = 1;
	        if (aportaciones.isEmpty()) {
	            tablaAportaciones.addCell(new Cell(1, 6).add(new Paragraph("No hay aportaciones disponibles")).setTextAlignment(TextAlignment.CENTER));
	        } else {
	            for (apotaciones aportacion : aportaciones) {
	                double cuota = prestamos.isEmpty() ? 0 : prestamos.get(0).getLetra_mensual();
	                int tasaInteres = prestamos.isEmpty() ? 0 : prestamos.get(0).getTasa_interes();

	                tablaAportaciones.addCell(new Cell().add(new Paragraph(String.valueOf(contador++)).setTextAlignment(TextAlignment.CENTER)));
	                tablaAportaciones.addCell(new Cell().add(new Paragraph(aportacion.getMes()).setTextAlignment(TextAlignment.CENTER)));
	                tablaAportaciones.addCell(new Cell().add(new Paragraph(String.format("%.2f", aportacion.getAportacion())).setTextAlignment(TextAlignment.CENTER)));
	                tablaAportaciones.addCell(new Cell().add(new Paragraph(String.format("%.2f", cuota)).setTextAlignment(TextAlignment.CENTER)));
	                tablaAportaciones.addCell(new Cell().add(new Paragraph(String.valueOf(tasaInteres)+ " %").setTextAlignment(TextAlignment.CENTER)));
	                tablaAportaciones.addCell(new Cell().add(new Paragraph(String.format("%.2f", aportacion.getTotal())).setTextAlignment(TextAlignment.CENTER)));
	                 }
	        }
	        document.add(tablaAportaciones);

	        // Préstamos
	        document.add(new Paragraph("\nPréstamos:"));
	        Table tablaPrestamos = new Table(new float[]{1, 2, 2, 2, 2, 2, 3, 2, 2});
	        tablaPrestamos.setWidth(UnitValue.createPercentValue(100));
	        tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("No.").setBold().setTextAlignment(TextAlignment.CENTER)));
	        tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Fecha de aprobación").setBold().setTextAlignment(TextAlignment.CENTER)));
	        tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Monto solicitado").setBold().setTextAlignment(TextAlignment.CENTER)));
	        tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Interés").setBold().setTextAlignment(TextAlignment.CENTER)));
	        tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Plazo de pago").setBold().setTextAlignment(TextAlignment.CENTER)));
	        tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Cuota").setBold().setTextAlignment(TextAlignment.CENTER)));
	        tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Cuotas pendientes").setBold().setTextAlignment(TextAlignment.CENTER)));
	        tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Descripción").setBold().setTextAlignment(TextAlignment.CENTER)));
	       tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Estado").setBold().setTextAlignment(TextAlignment.CENTER)));

	        contador = 1;
	        if (prestamos.isEmpty()) {
	            tablaPrestamos.addCell(new Cell(1, 9).add(new Paragraph("No hay préstamos disponibles")).setTextAlignment(TextAlignment.CENTER));
	        } else {
	            for (prestamos prest : prestamos) {
	                String estado = (prest.getPlazo_pago() > 0) ? "Activo" : "Finalizado";
	                String fechaAprobacion = prest.getFecha_aprobacion() != null ? dateFormat.format(prest.getFecha_aprobacion()) : "No disponible";

	                tablaPrestamos.addCell(new Cell().add(new Paragraph(String.valueOf(contador++)).setTextAlignment(TextAlignment.CENTER)));
	                tablaPrestamos.addCell(new Cell().add(new Paragraph(fechaAprobacion).setTextAlignment(TextAlignment.CENTER)));
	                tablaPrestamos.addCell(new Cell().add(new Paragraph(String.format("%.2f", (double) prest.getMonto_solicitado())).setTextAlignment(TextAlignment.CENTER)));
	                tablaPrestamos.addCell(new Cell().add(new Paragraph(String.valueOf(prest.getTasa_interes())+ "%").setTextAlignment(TextAlignment.CENTER)));
	                tablaPrestamos.addCell(new Cell().add(new Paragraph(prest.getPlazo_pago() + " meses").setTextAlignment(TextAlignment.CENTER)));
	                tablaPrestamos.addCell(new Cell().add(new Paragraph(String.format("%.2f", prest.getLetra_mensual())).setTextAlignment(TextAlignment.CENTER)));
	                tablaPrestamos.addCell(new Cell().add(new Paragraph(String.valueOf(prest.getPlazo_pago())).setTextAlignment(TextAlignment.CENTER)));
	                tablaPrestamos.addCell(new Cell().add(new Paragraph(prest.getMotivo_prestamo()).setTextAlignment(TextAlignment.CENTER)));
	                tablaPrestamos.addCell(new Cell().add(new Paragraph(estado).setTextAlignment(TextAlignment.CENTER)));
	            }
	        }
	        document.add(tablaPrestamos);

	        // Cerrar documento
	        document.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}

