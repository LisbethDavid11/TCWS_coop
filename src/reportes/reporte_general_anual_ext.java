package reportes;

import java.awt.Desktop;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import consultas.consultas_reportes;

public class reporte_general_anual_ext {

	@SuppressWarnings({ "deprecation" })
	public void generarReporteAnualPDF(int anio, String nombreArchivo) {
	    consultas_reportes consultas = new consultas_reportes();
	    List<Map<String, Object>> reporte = consultas.obtenerReporteAnual(anio);

	    if (reporte.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No existen registros para el año " + anio, "Información", JOptionPane.INFORMATION_MESSAGE);
	        return;
	    }

	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Guardar Reporte General Anual");
	    fileChooser.setSelectedFile(new File(nombreArchivo));

	    int userSelection = fileChooser.showSaveDialog(null);
	    if (userSelection != JFileChooser.APPROVE_OPTION) {
	        return;
	    }

	    File archivo = fileChooser.getSelectedFile();

	    if (archivo.exists()) {
	        int respuesta = JOptionPane.showConfirmDialog(null, "El archivo ya existe. ¿Desea sobrescribirlo?", "Confirmar", JOptionPane.YES_NO_OPTION);
	        if (respuesta != JOptionPane.YES_OPTION) {
	            return;
	        }
	    }

	    try {
	        PdfWriter writer = new PdfWriter(archivo);
	        PdfDocument pdf = new PdfDocument(writer);
	        Document document = new Document(pdf, PageSize.A4.rotate());
	        document.setMargins(50, 50, 50, 50);

	        encabezado_documentos encabezado = new encabezado_documentos();
	        encabezado.agregarEncabezado(document);

	        document.add(new Paragraph("Reporte General Anual de Socios")
	                .setFontSize(15)
	                .setTextAlignment(TextAlignment.CENTER)
	                .setBold());
	        document.add(new Paragraph("Año: " + anio)
	                .setFontSize(12)
	                .setTextAlignment(TextAlignment.CENTER)
	                .setMarginBottom(10));

	        Table table = new Table(new float[]{0.5f, 1.5f, 2, 1.2f, 1.5f, 1.2f, 1.5f, 1.5f, 1.5f, 1, 1, 1.5f, 1.5f, 1.5f, 1.5f});
	        table.setWidth(UnitValue.createPercentValue(100));

	        String[] headers = {
	            "No.", "Código Socio", "Nombre Completo", "Aportación Mensual",
	            "Total Aportaciones", "Dividendo %", "Total Dividendos", "Fecha Solicitud",
	            "Monto Solicitado", "Plazo", "Tasa Interés", "Total Interés",
	            "Pago Mensual", "Monto Total a Pagar", "Total Acumulado"
	        };

	        for (String header : headers) {
	            table.addHeaderCell(new Paragraph(header).setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(9));
	        }

	        BigDecimal totalAportaciones = BigDecimal.ZERO;
	        BigDecimal totalDividendos = BigDecimal.ZERO;
	        BigDecimal totalMontoSolicitado = BigDecimal.ZERO;
	        BigDecimal totalInteres = BigDecimal.ZERO;
	        BigDecimal totalPagoMensual = BigDecimal.ZERO;
	        BigDecimal totalMontoPagar = BigDecimal.ZERO;
	        BigDecimal totalAcumulado = BigDecimal.ZERO;
	        BigDecimal totalAportacionMensual = BigDecimal.ZERO;

	        Map<String, BigDecimal> aportacionesPorEmpleado = new HashMap<>();
	        BigDecimal totalAportacionesGlobal = BigDecimal.ZERO;

	        for (Map<String, Object> fila : reporte) {
	            String codigoSocio = fila.get("codigo_socio").toString();
	            BigDecimal aportacion = convertirBigDecimal(fila.get("aportacion_mensual"));

	            if (!aportacionesPorEmpleado.containsKey(codigoSocio)) {
	                aportacionesPorEmpleado.put(codigoSocio, aportacion);
	                totalAportacionesGlobal = totalAportacionesGlobal.add(aportacion);
	            }
	        }

	        int contador = 1;
	        String ultimoCodigoSocio = "";
	        BigDecimal porcentajeTotalDividendos = BigDecimal.ZERO;

	        for (Map<String, Object> fila : reporte) {
	            String codigoSocio = fila.get("codigo_socio").toString();
	            boolean mostrarAportaciones = !codigoSocio.equals(ultimoCodigoSocio);
	            ultimoCodigoSocio = codigoSocio;

	            BigDecimal aportacion = convertirBigDecimal(fila.get("aportacion_mensual"));
	            
	            //BigDecimal totalAportacion = convertirBigDecimal(fila.get("total_aportaciones"));
	            
	         // Obtener la fecha de inicio del socio en la cooperativa
	            String fechaInicioStr = fila.get("inicio_empleado") != null ? fila.get("inicio_empleado").toString() : null;
	            LocalDate fechaInicio = (fechaInicioStr != null && !fechaInicioStr.isEmpty()) ? LocalDate.parse(fechaInicioStr) : null;

	            // Fecha actual hasta donde se calcula el reporte
	            LocalDate fechaActual = LocalDate.now();

	            // Calcular los meses de antigüedad en la cooperativa
	            long mesesAntiguedad = (fechaInicio != null) ? ChronoUnit.MONTHS.between(fechaInicio.withDayOfMonth(1), fechaActual.withDayOfMonth(1)) : 0;

	            // Si el socio ingresó después de la fecha del reporte, su antigüedad es 0
	            mesesAntiguedad = Math.max(0, mesesAntiguedad);

	            // Calcular "Total Aportaciones" basado en los meses de antigüedad y la aportación mensual
	            BigDecimal totalAportacion = aportacion.multiply(new BigDecimal(mesesAntiguedad));

	            
	            BigDecimal dividendos = convertirBigDecimal(fila.get("total_dividendos"));
	            BigDecimal montoSolicitado = convertirBigDecimal(fila.get("monto_solicitado"));
	            BigDecimal interes = convertirBigDecimal(fila.get("total_interes"));
	            BigDecimal pagoMensual = convertirBigDecimal(fila.get("pago_mensual"));
	            BigDecimal montoPagar = convertirBigDecimal(fila.get("monto_total_pagar"));
	            BigDecimal acumulado = convertirBigDecimal(fila.get("total_acumulado"));

	            BigDecimal porcentajeDividendos = (totalAportacionesGlobal.compareTo(BigDecimal.ZERO) > 0 && mostrarAportaciones)
	                    ? aportacionesPorEmpleado.get(codigoSocio).multiply(new BigDecimal("100")).divide(totalAportacionesGlobal, 2, BigDecimal.ROUND_HALF_UP)
	                    : BigDecimal.ZERO;

	            porcentajeTotalDividendos = porcentajeTotalDividendos.add(porcentajeDividendos);

	            if (mostrarAportaciones) {
	                totalAportacionMensual = totalAportacionMensual.add(aportacion);
	                totalAportaciones = totalAportaciones.add(totalAportacion);
	                totalDividendos = totalDividendos.add(dividendos);
	            }
	            
	            totalMontoSolicitado = totalMontoSolicitado.add(montoSolicitado);
	            totalInteres = totalInteres.add(interes);
	            totalPagoMensual = totalPagoMensual.add(pagoMensual);
	            totalMontoPagar = totalMontoPagar.add(montoPagar);
	            totalAcumulado = totalAcumulado.add(acumulado);

	            table.addCell(new Paragraph(String.valueOf(contador++)).setFontSize(9));
	            table.addCell(new Paragraph(codigoSocio).setFontSize(9));
	            table.addCell(new Paragraph(fila.get("nombre_completo").toString()).setFontSize(9));
	            table.addCell(new Paragraph(mostrarAportaciones ? formatearNumero(aportacion) : "").setFontSize(9));
	            table.addCell(new Paragraph(mostrarAportaciones ? formatearNumero(totalAportacion) : "").setFontSize(9));
	            table.addCell(new Paragraph(mostrarAportaciones ? porcentajeDividendos + "%" : "").setFontSize(9));
	            table.addCell(new Paragraph(mostrarAportaciones ? formatearNumero(dividendos) : "").setFontSize(9));
	            table.addCell(new Paragraph(fila.get("fecha_solicitud") != null ? fila.get("fecha_solicitud").toString() : "---").setFontSize(9));
	            table.addCell(new Paragraph(formatearNumero(montoSolicitado)).setFontSize(9));
	            table.addCell(new Paragraph(fila.get("plazo_meses").toString()).setFontSize(9));
	            table.addCell(new Paragraph(fila.get("tasa_interes").toString()).setFontSize(9));
	            table.addCell(new Paragraph(formatearNumero(interes)).setFontSize(9));
	            table.addCell(new Paragraph(formatearNumero(pagoMensual)).setFontSize(9));
	            table.addCell(new Paragraph(formatearNumero(montoPagar)).setFontSize(9));
	            table.addCell(new Paragraph(formatearNumero(acumulado)).setFontSize(9));
	        }

	        // fila de totales
	        table.addCell(new Paragraph("Totales").setBold().setFontSize(9));
	        table.addCell(new Paragraph(""));
	        table.addCell(new Paragraph(""));
	        table.addCell(new Paragraph(formatearNumero(totalAportacionMensual)).setBold().setFontSize(9));
	        table.addCell(new Paragraph(formatearNumero(totalAportaciones)).setBold().setFontSize(9));
	        table.addCell(new Paragraph("100%").setBold().setFontSize(9));  
	        table.addCell(new Paragraph(formatearNumero(totalDividendos)).setBold().setFontSize(9));
	        table.addCell(new Paragraph(""));
	        table.addCell(new Paragraph(formatearNumero(totalMontoSolicitado)).setBold().setFontSize(9)); 
	        table.addCell(new Paragraph(""));
	        table.addCell(new Paragraph(""));
	        table.addCell(new Paragraph(formatearNumero(totalInteres)).setBold().setFontSize(9)); 
	        table.addCell(new Paragraph(formatearNumero(totalPagoMensual)).setBold().setFontSize(9)); 
	        table.addCell(new Paragraph(formatearNumero(totalMontoPagar)).setBold().setFontSize(9)); 
	        table.addCell(new Paragraph(formatearNumero(totalAcumulado)).setBold().setFontSize(9)); 

	        // Pasar correctamente los totales a la tabla de Totales Generales
	        Map<String, Object> totalesGenerales = new HashMap<>();
	        totalesGenerales.put("cantidad_prestamos", contador - 1);
	        totalesGenerales.put("total_aportaciones", totalAportaciones); // Total de la columna "Total Aportaciones"
	        totalesGenerales.put("total_mensual_prestamos", totalMontoPagar); // Total de la columna "Monto Total a Pagar"
	        totalesGenerales.put("total_prestamos", totalMontoSolicitado); // Total de la columna "Monto Solicitado"

	        
	        
	        
	        document.add(table);
	        //agregarTablaTotalesGenerales(document, consultas.obtenerTotalesGenerales(anio));
	        agregarTablaTotalesGenerales(document, totalesGenerales);

	        document.close();
	        Desktop.getDesktop().open(archivo);
	        
	        
	     


	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


    
    

    private BigDecimal convertirBigDecimal(Object valor) {
        if (valor == null) return BigDecimal.ZERO;
        return new BigDecimal(valor.toString());
    }
    
    
    
    
    private String formatearNumero(Object valor) {
        if (valor == null) {
            return "0.00";
        }
        try {
            BigDecimal numero = new BigDecimal(valor.toString());
            return String.format("%,.2f", numero);
        } catch (NumberFormatException e) {
            return "0.00";
        }
    }
    
    
    

    
    private void agregarTablaTotalesGenerales(Document document, Map<String, Object> totalesGenerales) {
        document.add(new Paragraph("\nTotales Generales").setBold().setFontSize(12));

        Table tablaTotales = new Table(new float[]{4, 2, 2});
        tablaTotales.setWidth(UnitValue.createPercentValue(60));

        // **Tomamos los valores directamente de la fila de Totales**
        BigDecimal totalAportaciones = convertirBigDecimal(totalesGenerales.get("total_aportaciones")); // "Total Aportaciones"
        BigDecimal totalMensualPrestamos = convertirBigDecimal(totalesGenerales.get("total_mensual_prestamos")); // "Monto Total a Pagar"
        BigDecimal totalPrestamos = convertirBigDecimal(totalesGenerales.get("total_prestamos")); // "Monto Solicitado"

        // **Cálculo de intereses y gastos administrativos**
        BigDecimal totalIntereses = totalMensualPrestamos.subtract(totalPrestamos); // Intereses = Monto Total a Pagar - Monto Solicitado
        BigDecimal gastosAdministrativos = totalIntereses.multiply(new BigDecimal("0.10")); // 10% de los intereses
        BigDecimal totalGlobal = totalAportaciones.add(totalPrestamos).add(totalIntereses); // Total Global

        String[][] totalesDatos = {
            {"Cantidad de préstamos aprobados durante el año", String.valueOf(totalesGenerales.get("cantidad_prestamos")), ""},
            {"Total anual de aportaciones por socio", "L " + formatearNumero(totalAportaciones), "L " + formatearNumero(totalAportaciones)}, 
            {"Total anual de lo mensual pagado en préstamos por los socios", "L " + formatearNumero(totalMensualPrestamos), "L " + formatearNumero(totalMensualPrestamos)}, 
            {"Total anual de préstamos que ha brindado la cooperativa", "L " + formatearNumero(totalPrestamos), "L " + formatearNumero(totalPrestamos)}, 
            {"Total anual de intereses", "L " + formatearNumero(totalIntereses), "L " + formatearNumero(totalIntereses)},
            {"El 10% para gastos administrativos de la cooperativa obtenidos de los intereses", "L " + formatearNumero(gastosAdministrativos), "L " + formatearNumero(totalIntereses.subtract(gastosAdministrativos))},
            {"Total global de la cooperativa", "L " + formatearNumero(totalGlobal), "L " + formatearNumero(totalGlobal)}
        };

        for (String[] fila : totalesDatos) {
            tablaTotales.addCell(new Paragraph(fila[0]).setBold().setFontSize(9));
            tablaTotales.addCell(new Paragraph(fila[1]).setTextAlignment(TextAlignment.RIGHT).setFontSize(9));
            tablaTotales.addCell(new Paragraph(fila[2]).setTextAlignment(TextAlignment.RIGHT).setFontSize(9));
        }

        document.add(tablaTotales);
    }




   

}