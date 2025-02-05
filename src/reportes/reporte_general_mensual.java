package reportes;

import java.awt.Desktop;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

public class reporte_general_mensual {

    public void generarReporteMensualPDF(int mes, int anio, String rutaArchivo) {
        consultas_reportes consultas = new consultas_reportes();
        List<Map<String, Object>> reporte = consultas.obtenerReporteMensual(mes, anio);

        if (reporte.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron datos para el mes y año seleccionados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        String mesNombre = meses[mes - 1];  // Convierte 2 -> "Febrero"

        


        try {
            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4.rotate());
            document.setMargins(50, 50, 50, 50);

            encabezado_documentos encabezado = new encabezado_documentos();
            encabezado.agregarEncabezado(document);

            document.add(new Paragraph("Reporte General Mensual de Socios")
                    .setFontSize(15)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold());
            document.add(new Paragraph("Mes: " + mesNombre + " | Año: " + anio)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(10));

            Table table = new Table(new float[]{1, 3, 3, 2, 2, 2, 2, 1.5f, 2, 2, 2, 2});
            table.setWidth(UnitValue.createPercentValue(100));

            // Encabezados de la tabla
            String[] headers = {
                "No.", "Código Socio", "Nombre Completo", "Aportación Mensual", "Monto Solicitado",
                "Fecha de Solicitud", "Plazo (meses)", "Tasa (%)", "Interés", "Cuota Mensual",
                "Total a Pagar", "Total Acumulado"
            };

            for (String header : headers) {
                table.addHeaderCell(new Paragraph(header).setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(10));
            }

            // Variables de Totales
            BigDecimal totalAportaciones = BigDecimal.ZERO;
            BigDecimal totalMontoSolicitado = BigDecimal.ZERO;
            BigDecimal totalCuotaMensual = BigDecimal.ZERO;
            BigDecimal totalPagar = BigDecimal.ZERO;
            BigDecimal totalInteres = BigDecimal.ZERO;
            BigDecimal totalAcumulado = BigDecimal.ZERO;

            int contador = 1;

            for (Map<String, Object> fila : reporte) {
                String codigoSocio = fila.getOrDefault("codigo_socio", "0").toString();
                String nombreCompleto = fila.get("nombre_completo") != null ? (String) fila.get("nombre_completo") : "---";

                BigDecimal aportacion = new BigDecimal(fila.getOrDefault("aportacion_mensual", "0.00").toString());
                BigDecimal montoSolicitado = new BigDecimal(fila.getOrDefault("monto_solicitado", "0.00").toString());
                
                // ✅ Modificación aquí para que la fecha sea "0" si no hay préstamo
                boolean tienePrestamo = montoSolicitado.compareTo(BigDecimal.ZERO) > 0;
                String fechaSolicitud = fila.get("fecha_solicitud") != null && tienePrestamo 
                    ? fila.get("fecha_solicitud").toString() 
                    : "---"; 

                BigDecimal plazoMeses = new BigDecimal(fila.getOrDefault("plazo_meses", "0").toString());
                BigDecimal tasaInteres = new BigDecimal(fila.getOrDefault("tasa_interes", "0.00").toString());
                BigDecimal interes = montoSolicitado.multiply(tasaInteres.divide(BigDecimal.valueOf(100)));
                BigDecimal cuotaMensual = new BigDecimal(fila.getOrDefault("cuota_mensual", "0.00").toString());
                BigDecimal totalPagarDespuesPlazo = montoSolicitado.add(interes);
                BigDecimal totalAcumuladoFinal = totalPagarDespuesPlazo.subtract(montoSolicitado);

                String montoSolicitadoStr = tienePrestamo ? String.format("%.2f", montoSolicitado) : "---";
                String totalPagarStr = tienePrestamo ? String.format("%.2f", totalPagarDespuesPlazo) : "---";
                String totalAcumuladoStr = tienePrestamo ? String.format("%.2f", totalAcumuladoFinal) : "---";
                String interesStr = tienePrestamo ? String.format("%.2f", interes) : "---";
                String cuotaMensualStr = tienePrestamo ? String.format("%.2f", cuotaMensual) : "---";

                totalAportaciones = totalAportaciones.add(aportacion);
                totalMontoSolicitado = totalMontoSolicitado.add(montoSolicitado);
                totalCuotaMensual = totalCuotaMensual.add(cuotaMensual);
                totalPagar = totalPagar.add(totalPagarDespuesPlazo);
                totalInteres = totalInteres.add(interes);
                totalAcumulado = totalAcumulado.add(totalAcumuladoFinal);

                table.addCell(new Paragraph(String.valueOf(contador++)).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(codigoSocio).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(nombreCompleto).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Paragraph(String.format("%.2f", aportacion)).setTextAlignment(TextAlignment.RIGHT));
                table.addCell(new Paragraph(montoSolicitadoStr).setTextAlignment(TextAlignment.RIGHT));
                table.addCell(new Paragraph(fechaSolicitud).setTextAlignment(TextAlignment.CENTER)); // ✅ Ahora muestra "0" si no hay préstamo
                table.addCell(new Paragraph(plazoMeses.compareTo(BigDecimal.ZERO) > 0 ? String.valueOf(plazoMeses.intValue()) : "---").setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(tasaInteres.compareTo(BigDecimal.ZERO) > 0 ? String.format("%.0f", tasaInteres) + "%" : "---").setTextAlignment(TextAlignment.RIGHT));
                table.addCell(new Paragraph(interesStr).setTextAlignment(TextAlignment.RIGHT));
                table.addCell(new Paragraph(cuotaMensualStr).setTextAlignment(TextAlignment.RIGHT));
                table.addCell(new Paragraph(totalPagarStr).setTextAlignment(TextAlignment.RIGHT));
                table.addCell(new Paragraph(totalAcumuladoStr).setTextAlignment(TextAlignment.RIGHT));
            }


            // Fila de Totales dentro de la tabla
            table.addCell(new Paragraph("Totales").setTextAlignment(TextAlignment.CENTER).setBold());
            
            for (int i = 1; i < 3; i++) table.addCell(new Paragraph(""));
            table.addCell(new Paragraph(String.format("%.2f", totalAportaciones)).setTextAlignment(TextAlignment.RIGHT).setBold());
            table.addCell(new Paragraph(String.format("%.2f", totalMontoSolicitado)).setTextAlignment(TextAlignment.RIGHT).setBold());
            for (int i = 1; i < 4; i++) table.addCell(new Paragraph(""));
            table.addCell(new Paragraph(String.format("%.2f", totalInteres)).setTextAlignment(TextAlignment.RIGHT).setBold());
            table.addCell(new Paragraph(String.format("%.2f", totalCuotaMensual)).setTextAlignment(TextAlignment.RIGHT).setBold());
            table.addCell(new Paragraph(String.format("%.2f", totalPagar)).setTextAlignment(TextAlignment.RIGHT).setBold());
            table.addCell(new Paragraph(String.format("%.2f", totalAcumulado)).setTextAlignment(TextAlignment.RIGHT).setBold());

            document.add(table);

            // Totales Finales Fuera de la Tabla
            document.add(new Paragraph("Total de aportaciones mensuales: " + String.format("%.2f", totalAportaciones))
                    .setTextAlignment(TextAlignment.RIGHT).setBold().setFontSize(12));

            document.add(new Paragraph("Total mensual acumulado: " + String.format("%.2f", totalAportaciones.add(totalCuotaMensual)))
                    .setTextAlignment(TextAlignment.RIGHT).setBold().setFontSize(12));

            document.close();
            Desktop.getDesktop().open(new File(rutaArchivo));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
