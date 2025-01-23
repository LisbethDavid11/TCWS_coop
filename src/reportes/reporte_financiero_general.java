package reportes;

import java.text.SimpleDateFormat;
import java.util.List;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import clases.apotaciones;
import clases.empleados;
import clases.prestamos;
import consultas.consultas_reportes;

public class reporte_financiero_general {

	public void generarReporteFinancieroGeneral(String rutaArchivo) {
	    try {
	        // Instancia de consultas para obtener los datos
	        consultas_reportes consultas = new consultas_reportes();
	        List<empleados> empleadosList = consultas.obtenerTodosLosEmpleados();

	        // Configuración del PDF
	        PdfWriter writer = new PdfWriter(rutaArchivo);
	        PdfDocument pdf = new PdfDocument(writer);
	        Document document = new Document(pdf, PageSize.A4);
	        document.setMargins(70, 70, 70, 70); // 2.5 cm aprox.

	        // Evento para agregar números de página
	        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new PdfPageNumberingEventHandler(document));

	        // Agregar encabezado
	        encabezado_documentos encabezado = new encabezado_documentos();
	        encabezado.agregarEncabezado(document);

	        // Título del reporte
	        document.add(new Paragraph("Reporte Financiero General")
	                .setTextAlignment(TextAlignment.CENTER)
	                .setFontSize(14).setBold());

	        for (empleados empleado : empleadosList) {
	            // Datos del empleado
	            document.add(new Paragraph("Empleado: " + empleado.getNombres_empleado() + " " + empleado.getApellidos_empleado()
	                    + " (ID: " + empleado.getId_empleado() + ")")
	                    .setFontSize(10)
	                    .setBold());

	            // Aportaciones
	            document.add(new Paragraph("Aportaciones Mensuales:"));
	            Table tablaAportaciones = new Table(new float[]{1, 2, 2, 2, 2, 2});
	            tablaAportaciones.setWidth(UnitValue.createPercentValue(100));
	            tablaAportaciones.addHeaderCell(new Cell().add(new Paragraph("No.").setBold()));
	            tablaAportaciones.addHeaderCell(new Cell().add(new Paragraph("Mes").setBold()));
	            tablaAportaciones.addHeaderCell(new Cell().add(new Paragraph("Aportación").setBold()));
	            tablaAportaciones.addHeaderCell(new Cell().add(new Paragraph("Total").setBold()));
	            tablaAportaciones.addHeaderCell(new Cell().add(new Paragraph("Cuota").setBold()));
	            tablaAportaciones.addHeaderCell(new Cell().add(new Paragraph("Tasa de Interés").setBold()));

	            List<apotaciones> aportaciones = consultas.obtenerAportacionesPorEmpleado(empleado.getId_empleado());
	            int contador = 1;
	            if (aportaciones.isEmpty()) {
	                tablaAportaciones.addCell(new Cell(1, 6).add(new Paragraph("No hay aportaciones disponibles")).setTextAlignment(TextAlignment.CENTER));
	            } else {
	                for (apotaciones aportacion : aportaciones) {
	                    tablaAportaciones.addCell(new Cell().add(new Paragraph(String.valueOf(contador++))));
	                    tablaAportaciones.addCell(new Cell().add(new Paragraph(aportacion.getMes())));
	                    tablaAportaciones.addCell(new Cell().add(new Paragraph(String.format("%.2f", aportacion.getAportacion()))));
	                    tablaAportaciones.addCell(new Cell().add(new Paragraph(String.format("%.2f", aportacion.getTotal()))));
	                    tablaAportaciones.addCell(new Cell().add(new Paragraph(String.format("%.2f", aportacion.getCuota()))));
	                    tablaAportaciones.addCell(new Cell().add(new Paragraph(String.format("%.2f", aportacion.getInteres()))));
	                }
	            }
	            document.add(tablaAportaciones);

	            // Préstamos
	            document.add(new Paragraph("Préstamos:"));
	            Table tablaPrestamos = new Table(new float[]{1, 3, 2, 2, 2, 2, 2, 2, 2});
	            tablaPrestamos.setWidth(UnitValue.createPercentValue(100));
	            tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("No.").setBold()));
	            tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Descripción").setBold()));
	            tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Intereses (%)").setBold()));
	            tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Cuotas Pendientes").setBold()));
	            tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Plazo Total").setBold()));
	            tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Letra Mensual").setBold()));
	            tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Monto Solicitado").setBold()));
	            tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Fecha de Aprobación").setBold()));
	            tablaPrestamos.addHeaderCell(new Cell().add(new Paragraph("Estado").setBold()));

	            List<prestamos> prestamos = consultas.obtenerPrestamosPorEmpleado(empleado.getId_empleado());
	            contador = 1;
	            if (prestamos.isEmpty()) {
	                tablaPrestamos.addCell(new Cell(1, 9).add(new Paragraph("No hay préstamos disponibles")).setTextAlignment(TextAlignment.CENTER));
	            } else {
	                for (prestamos prestamo : prestamos) {
	                    String estado = (prestamo.getPlazo_pago() > 0) ? "Activo" : "Finalizado";
	                    String fechaAprobacion = prestamo.getFecha_aprobacion() != null ? 
	                                             new SimpleDateFormat("dd-MM-yyyy").format(prestamo.getFecha_aprobacion()) : "No disponible";

	                    tablaPrestamos.addCell(new Cell().add(new Paragraph(String.valueOf(contador++))));
	                    tablaPrestamos.addCell(new Cell().add(new Paragraph(prestamo.getMotivo_prestamo())));
	                    tablaPrestamos.addCell(new Cell().add(new Paragraph(String.valueOf(prestamo.getTasa_interes()))));
	                    tablaPrestamos.addCell(new Cell().add(new Paragraph(String.valueOf(prestamo.getPlazo_pago()))));
	                    tablaPrestamos.addCell(new Cell().add(new Paragraph(prestamo.getPlazo_pago() + " meses")));
	                    tablaPrestamos.addCell(new Cell().add(new Paragraph(String.format("%.2f", prestamo.getLetra_mensual()))));
	                    tablaPrestamos.addCell(new Cell().add(new Paragraph(String.format("%.2f", (double) prestamo.getMonto_solicitado()))));
	                    tablaPrestamos.addCell(new Cell().add(new Paragraph(fechaAprobacion)));
	                    tablaPrestamos.addCell(new Cell().add(new Paragraph(estado)));
	                }
	            }
	            document.add(tablaPrestamos);

	            // Espacio entre empleados
	            document.add(new Paragraph("\n"));
	        }

	        // Cerrar documento
	        document.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	// Clase para manejar los números de página
	class PdfPageNumberingEventHandler implements IEventHandler {
	    private Document document;

	    public PdfPageNumberingEventHandler(Document document) {
	        this.document = document;
	    }

	    @Override
	    public void handleEvent(Event event) {
	        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
	        PdfPage page = docEvent.getPage();
	        PdfCanvas canvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), docEvent.getDocument());
	        Canvas pdfCanvas = new Canvas(canvas, document.getPdfDocument(), page.getPageSize());
	        int pageNumber = docEvent.getDocument().getPageNumber(page);
	        pdfCanvas.showTextAligned(
	                new Paragraph("Página " + pageNumber),
	                page.getPageSize().getWidth() - 70, // Derecha
	                page.getPageSize().getBottom() + 20, // Abajo
	                TextAlignment.RIGHT
	        );
	        pdfCanvas.close();
	    }
	}




}
