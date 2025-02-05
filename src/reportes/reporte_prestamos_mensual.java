package reportes;

import javax.swing.*;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.io.File;
import java.util.List;
import clases.prestamos;

public class reporte_prestamos_mensual {

    public void mostrarDialogoYGenerarReporte(List<prestamos> prestamos) {
        // Opciones de meses
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        int añoActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

        // JComboBox para meses y años
        JComboBox<String> comboMes = new JComboBox<>(meses);
        JTextField txtAnio = new JTextField(String.valueOf(añoActual)); // Año actual por defecto

        // Crear panel para JOptionPane
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Seleccione el mes:"));
        panel.add(comboMes);
        panel.add(Box.createVerticalStrut(10)); // Espacio entre componentes
        panel.add(new JLabel("Ingrese el año:"));
        panel.add(txtAnio);

        int resultado = JOptionPane.showConfirmDialog(null, panel, "Generar Reporte Mensual",
                                                      JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            String mes = (String) comboMes.getSelectedItem();
            String anio = txtAnio.getText().trim();

            // Validar entrada
            if (anio.isEmpty() || !anio.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(null, "Ingrese un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Selección de ruta para guardar el archivo
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte");
            fileChooser.setSelectedFile(new File("Reporte_prestamos_" + mes + "_" + anio + ".pdf"));

            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();

                // Validar si el archivo ya existe
                if (archivo.exists()) {
                    int opcionSobrescribir = JOptionPane.showConfirmDialog(null,
                            "El archivo ya existe. ¿Desea sobrescribirlo?", "Confirmación",
                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    if (opcionSobrescribir != JOptionPane.YES_OPTION) {
                        return; // Cancelar si no desea sobrescribir
                    }
                }

                // Generar el reporte
                generarReportePDF(mes, anio, prestamos, archivo.getAbsolutePath());

                // Abrir el archivo automáticamente en Microsoft Edge
                try {
                    Runtime.getRuntime().exec("cmd /c start msedge \"" + archivo.getAbsolutePath() + "\"");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo en Microsoft Edge.",
                                                  "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }
    }

    public void generarReportePDF(String mes, String anio, List<prestamos> prestamos, String rutaArchivo) {
        if (prestamos == null || prestamos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron datos para el mes " + mes + " del año " + anio + ".",
                                          "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            // Configuración del PDF
            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4.rotate());
            document.setMargins(50, 50, 50, 50);

            // Encabezado
            encabezado_documentos encabezado = new encabezado_documentos();
            encabezado.agregarEncabezado(document);

            // Título
            document.add(new Paragraph("Reporte Mensual de Préstamos")
                    .setFontSize(15)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold());
            document.add(new Paragraph("Mes: " + mes + " , Año: " + anio)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(10));

            // Tabla con tamaños ajustados
            Table table = new Table(new float[]{0.5f, 1.5f, 2.5f, 2.5f, 2f, 2f, 1.5f, 1.5f, 2f});
            table.setWidth(UnitValue.createPercentValue(100));

            // Encabezados de la tabla
            table.addHeaderCell(new Paragraph("No.").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Código Socio").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Nombres").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Apellidos").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Fecha de aprobación").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Monto solicitado").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Plazos de pago").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Tasa de interés").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Letra mensual").setTextAlignment(TextAlignment.CENTER).setBold());

            // Datos
            int contador = 1;
            for (prestamos p : prestamos) {
                table.addCell(new Paragraph(String.valueOf(contador++)).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(String.valueOf(p.getId())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(p.getNombres_empleado()).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(p.getApellidos_empleado()).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(p.getFecha_aprobacion().toString()).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(String.format("%.2f", (double) p.getMonto_solicitado())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(String.valueOf(p.getPlazo_pago())).setTextAlignment(TextAlignment.CENTER)); // Asegúrate de que se llena bien.
                table.addCell(new Paragraph(String.valueOf(p.getTasa_interes())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(String.format("%.2f", (double) p.getLetra_mensual())).setTextAlignment(TextAlignment.CENTER));
            }

            // Agregar la tabla
            document.add(table);
            document.close();

            JOptionPane.showMessageDialog(null, "El reporte se ha guardado correctamente en: " + rutaArchivo,
                                          "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al generar el reporte: " + e.getMessage(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
