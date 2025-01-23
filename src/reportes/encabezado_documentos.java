package reportes;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

public class encabezado_documentos {

    public void agregarEncabezado(Document document) {
        try {
        	
        	
            InputStream logoStream = getClass().getResourceAsStream("/imagenes/cooperativa.png");
            if (logoStream == null) {
                throw new FileNotFoundException("No se encontró el recurso: cooperativa.png");
            }
            Image img = new Image(ImageDataFactory.create(logoStream.readAllBytes()));

            img.setWidth(90);
            img.setHeight(90);

            float pageWidth = document.getPdfDocument().getDefaultPageSize().getWidth();
            float pageHeight = document.getPdfDocument().getDefaultPageSize().getHeight();

            if (pageWidth > pageHeight) { // Si la página es horizontal
                img.setFixedPosition(pageWidth - 130, pageHeight - 110);
            } else { // Si la página es vertical
                img.setFixedPosition(pageWidth - 105, pageHeight - 110);
            }

            document.add(img);

            // Crear encabezado de texto
            Paragraph header = new Paragraph("Cooperativa de Ahorro y Préstamos del Instituto \n“El Mundo de los Niños” \n COAHPMUN")
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold();

            Paragraph address = new Paragraph("Col. La Ceibita \nDanli, El Paraíso, Honduras")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER);

            Paragraph phone = new Paragraph("Teléfono: 9671-0574")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER);

            Paragraph correo = new Paragraph("Correo: admin@tcws.edu.hn \n")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER);

            // Obtener fecha y hora actual
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy || HH:mm:ss");
            String fechaHoraActual = now.format(formatter);

            Paragraph fechaHora = new Paragraph("Emitido el: " + fechaHoraActual)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.RIGHT);

            // Agregar elementos al documento
            document.add(header);
            document.add(address);
            document.add(phone);
            document.add(correo);
            document.add(fechaHora);
            document.add(new Paragraph("\n")); // Espacio extra después del encabezado

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
