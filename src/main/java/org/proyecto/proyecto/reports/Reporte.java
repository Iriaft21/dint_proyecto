package org.proyecto.proyecto.reports;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Clase que genera un informe a partir de una tabla de la base de datos
 */
public class Reporte {

    /**
     * Método para generar informes
     *
     * @param conn La conexion para conectarse a la base de datos
     */
    public void generateReport(Connection conn) {
        try {
            // Crea un documento PDF y un PdfDocument asociado
            PdfWriter writer = new PdfWriter(new FileOutputStream("reports/reporte_proyectos.pdf"));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Título del reporte
            Paragraph title = new Paragraph("REPORTE DE TODOS LOS PROYECTOS DE NUESTRA APLICACIÓN").setFontSize(16);
            title.setTextAlignment(TextAlignment.CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Crear la tabla con 2 columnas
            Table table = new Table(10); // 10 columnas

            // Establecer los anchos de las columnas en porcentaje
            table.setWidth(UnitValue.createPercentValue(50)); // Hace que la tabla ocupe el 100% del ancho disponible

            // Agregar los encabezados de la tabla
            table.addCell(new Cell().add(new Paragraph("Nombre")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Descripcion")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Diseñador")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Alto")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Largo")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Estado")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Progreso")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("P.Totales")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("F.Inicio")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("F.Fin")).setTextAlignment(TextAlignment.CENTER));

            // Obtener los datos de la base de datos
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NOMBRE, DESCRIPCION, DISENIADOR, ALTO, LARGO, ESTADO, PROGRESO, PUNTADASTOTALES, FECHAINICIO, FECHAFIN FROM PROYECTO");

            // Agregar los datos de la base de datos a la tabla
            while (rs.next()) {
                table.addCell(new Cell().add(new Paragraph(rs.getString("NOMBRE"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("DESCRIPCION"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("DISENIADOR"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("ALTO"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("LARGO"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("ESTADO"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("PROGRESO"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("PUNTADASTOTALES"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("FECHAINICIO"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("FECHAFIN"))).setTextAlignment(TextAlignment.LEFT));
            }

            // Añadir la tabla al documento PDF
            document.add(table);

            // Cerrar el documento
            document.close();

            //Leer el documento
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                //se crea un File que se asociara al PDF
                File pdfFile = new File("reports/reporte_proyectos.pdf");
                //Si el archivo existe
                if (pdfFile.exists()) {
                    //lo abre
                    desktop.open(pdfFile);
                }
            }

            System.out.println("Reporte generado exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
