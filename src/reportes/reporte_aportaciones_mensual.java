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

import java.util.List;
import clases.apotaciones;

public class reporte_aportaciones_mensual {

    public void mostrarDialogoYGenerarReporte(List<apotaciones> empleados) {
        // Opciones de meses
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        // Solicitar mes y año
        JComboBox<String> comboMes = new JComboBox<>(meses);
        JTextField txtAnio = new JTextField();

        // Crear panel para el JOptionPane
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

            // Obtener ruta para guardar el archivo
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte");
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath() + ".pdf";
                
                // Generar el reporte
                generarReportePDF(mes, anio, empleados, rutaArchivo);
            }
        }
    }

    public void generarReportePDF(String mes, String anio, List<apotaciones> empleados, String rutaArchivo) {
        if (empleados == null || empleados.isEmpty()) {
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
            document.add(new Paragraph("Reporte Mensual de Aportaciones")
                    .setFontSize(15)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold());
            document.add(new Paragraph("Mes: " + mes + " , Año: " + anio)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(10));

            // Tabla
            Table table = new Table(new float[]{1, 3, 3, 3, 2, 2, 2, 2});
            table.setWidth(UnitValue.createPercentValue(100));

            // Encabezados
            table.addHeaderCell(new Paragraph("No.").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Código").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Nombres").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Apellidos").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Aportación").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Interés").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Cuota").setTextAlignment(TextAlignment.CENTER).setBold());
            table.addHeaderCell(new Paragraph("Total").setTextAlignment(TextAlignment.CENTER).setBold());

            // Datos
            int contador = 1;
            for (apotaciones empleado : empleados) {
                table.addCell(new Paragraph(String.valueOf(contador++)).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(String.valueOf(empleado.getId_empleado())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(empleado.getNombres_empleado()).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(empleado.getApellidos_empleado()).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(String.format("%.2f", empleado.getAportacion())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(String.format("%.2f", empleado.getInteres())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(String.format("%.2f", empleado.getCuota())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Paragraph(String.format("%.2f", empleado.getTotal())).setTextAlignment(TextAlignment.CENTER));
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
