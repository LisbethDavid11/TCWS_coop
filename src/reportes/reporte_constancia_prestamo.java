package reportes;

import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import clases.prestamos;

public class reporte_constancia_prestamo {

    public void generarConstanciaPrestamo(prestamos datosPrestamo, String rutaArchivo) throws IOException {
        try {
            // Validar la ruta del archivo
            File archivo = new File(rutaArchivo);
            if (!archivo.getParentFile().exists()) {
                archivo.getParentFile().mkdirs();
            }

            
            
            // Crear el documento PDF
            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);


            document.add(new Paragraph("Constancia No. " + datosPrestamo.getId())
                    .setBold().setFontSize(12).setTextAlignment(TextAlignment.LEFT));
            
            // Agregar encabezado
            encabezado_documentos encabezado = new encabezado_documentos();
            encabezado.agregarEncabezado(document);
            
            
            // Título del reporte
	        document.add(new Paragraph("Constancia de aprobación de préstamo")
	                .setFontSize(15)
	                .setTextAlignment(TextAlignment.CENTER)
	                .setBold());

            // Calcular intereses y total a pagar
            double totalIntereses = datosPrestamo.getMonto_solicitado() * (datosPrestamo.getTasa_interes() / 100.0);
            double totalPagar = datosPrestamo.getMonto_solicitado() + totalIntereses;
            //String fechaRegistro = datosPrestamo.getFecha_aprobacion().toString();

            // Contenido del documento

            document.add(new Paragraph("Yo, " + datosPrestamo.getAutorizado1() + ", en mi calidad de " + datosPrestamo.getCargo_autorizado1() + 
                    " de la Cooperativa de Ahorros y Préstamos del Instituto “El Mundo de los Niños” (COAHPMUN), hago constar que se ha autorizado un préstamo a favor del afiliado " +
                    datosPrestamo.getNombres_empleado() + " " + datosPrestamo.getApellidos_empleado() + ", miembro de esta cooperativa bajo el código " + datosPrestamo.getId_empleado() + 
                    " y quien labora en la institución con el cargo de " + datosPrestamo.getCargo_empleado() + " en el área de " + datosPrestamo.getArea_empleado() + ".  El préstamo se "
                    		+ "otorga bajo los siguientes términos y condiciones, comprometiendo al beneficiario a cumplir con lo establecido por la cooperativa: ")
                    .setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED));

            // I. Términos y condiciones
            document.add(new Paragraph("\n I. Términos y condiciones del Préstamo:")
                    .setBold().setFontSize(11).setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("A. Tasa de interés mensual\n Este préstamo tendrá una tasa de interés del " + datosPrestamo.getTasa_interes() + "% .")
                    .setFontSize(11).setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("B. Plazo de pago\n \t El plazo de pago será de " + datosPrestamo.getPlazo_pago() + " meses.")
                    .setFontSize(11).setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("C. Cuotas mensuales \n Los pagos mensuales serán de  L. " + datosPrestamo.getLetra_mensual() + ", y deberán realizarse en las fechas"
            		+ " acordadas con la \r Cooperativa.")
                    .setFontSize(11).setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("D. Uso del préstamo \n Los fondos del préstamo deben destinarse exclusivamente al propósito registrado"
            		+ " en la solicitud aprobada.")
                    .setFontSize(11).setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("E. Cancelación del préstamo \n La Cooperativa se reserva el derecho de cancelar el préstamo si se detecta"
            		+ " información falsa o incumplimiento de las condiciones. \n\n")
                    .setFontSize(11).setTextAlignment(TextAlignment.LEFT));
            
            
            //II. Compromiso del prestatario
            document.add(new Paragraph("II. Compromisos del prestatario:")
                    .setBold().setFontSize(11).setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph(" Al aceptar los términos y condiciones, el prestatario declara que: \r\n"
            		+ "• La tasa de interés aplicable según el monto del préstamo, como se detalla en el"
            		+ "primer punto. \r\n"
            		+ "• Los plazos de pago acordados entre la cooperativa y mi persona como prestatario. \r\n"
            		+ "• Realizar los pagos mensuales del préstamo (cuotas/letras) en las fechas "
            		+ "establecidas. \r\n"
            		+ "• Utilizar los fondos del préstamo únicamente para el propósito especificado en la "
            		+ "solicitud. \r\n"
            		+ "• Confirma que la información que proporciono en la solicitud del préstamo es veraz "
            		+ "y precisa. \n\n")
                    .setFontSize(11).setTextAlignment(TextAlignment.LEFT));
            
            
            //III. Información requerida 

            document.add(new Paragraph("III. Información requerida: ")
                    .setBold().setFontSize(11).setTextAlignment(TextAlignment.LEFT));

            document.add(new Paragraph("Nombre completo del prestatario \n" + datosPrestamo.getNombres_empleado() + " " + datosPrestamo.getApellidos_empleado() )
                    .setFontSize(11).setTextAlignment(TextAlignment.LEFT));

            document.add(new Paragraph("Número de cuenta bancaria \n" + datosPrestamo.getCuenta_empleado())
                    .setFontSize(11).setTextAlignment(TextAlignment.LEFT));
            
            document.add(new Paragraph("Motivo del préstamo \n" + datosPrestamo.getMotivo_prestamo()+ "\n\n\n")
                    .setFontSize(11).setTextAlignment(TextAlignment.LEFT));
            
            
            
            // Tabla final con datos resumen
            Table tabla = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();
            tabla.addHeaderCell("Cantidad solicitada").setBackgroundColor(ColorConstants.WHITE);
            tabla.addHeaderCell("Tasa de interés").setBackgroundColor(ColorConstants.WHITE);
            tabla.addHeaderCell("Plazo de pago en meses").setBackgroundColor(ColorConstants.WHITE);
            tabla.addHeaderCell("Total intereses").setBackgroundColor(ColorConstants.WHITE);
            tabla.addHeaderCell("Total a pagar").setBackgroundColor(ColorConstants.WHITE);
            tabla.addHeaderCell("Cuota mensual").setBackgroundColor(ColorConstants.WHITE);

            tabla.addCell("L. " + datosPrestamo.getMonto_solicitado());
            tabla.addCell(datosPrestamo.getTasa_interes() + "%");
            tabla.addCell(datosPrestamo.getPlazo_pago() + " meses");
            tabla.addCell("L. " + String.format("%.2f", totalIntereses));
            tabla.addCell("L. " + String.format("%.2f", totalPagar));
            tabla.addCell("L. " + datosPrestamo.getLetra_mensual());

            document.add(tabla);

            // Firmas
            document.add(new Paragraph("\n\n\n\n _________________________________\nPrestatario\n" +
                    datosPrestamo.getNombres_empleado() + " " + datosPrestamo.getApellidos_empleado())
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("\n\n\n_________________________________\n" + datosPrestamo.getAutorizado1() + "\n" + datosPrestamo.getCargo_autorizado1())
                    .setTextAlignment(TextAlignment.CENTER));
            
            document.add(new Paragraph("\n\n\n_________________________________\n" + datosPrestamo.getAutorizado2() + "\n" + datosPrestamo.getCargo_autorizado2())
                    .setTextAlignment(TextAlignment.CENTER));
            

            // Cerrar documento
            document.close();
        } catch (Exception e) {
            throw new IOException("Error al generar la constancia: " + e.getMessage(), e);
        }
    }
}
