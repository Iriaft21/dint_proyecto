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
 * Clase para generar informes con filtros
 */
public class ReporteFiltro {
    /**
     * Método para generar informes a partir de las diferentes tablas de la base de datos
     *
     * @param conn Conexion para la base de datos
     * @param filtro    Que filtro es el que se esta aplicando en ese momento
     * @param valor     Valor por el que se va a filtrar
     */
    public void generarReporte(Connection conn, String filtro, String valor) {
        try {
            // Crear un documento PDF y un PdfDocument asociado
            PdfWriter writer = new PdfWriter(new FileOutputStream("REPORTS/reporte_" + filtro + ".pdf"));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Título del reporte
            Paragraph title = new Paragraph("REPORTE DEL HILO (" + filtro.toUpperCase() + " = '" + valor + "')").setFontSize(16);
            title.setTextAlignment(TextAlignment.CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));  // Salto de línea

            // Crear la tabla con 3 columnas
            Table table = new Table(3); // 3 columnas

            // Establecer el ancho de la tabla al 100% del espacio disponible
            table.setWidth(UnitValue.createPercentValue(100));

            // Agregar los encabezados de la tabla
            table.addCell(new Cell().add(new Paragraph("NOMBRE")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("MARCA")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("CANTIDAD")).setTextAlignment(TextAlignment.CENTER));

            // Consulta SQL filtrada según el parámetro de filtro proporcionado
            Statement stmt = conn.createStatement();
            String sql = "SELECT NOMBRE, MARCA, CANTIDAD FROM hilo WHERE " + filtro + " = '" + valor + "'";
            ResultSet rs = stmt.executeQuery(sql);

            // Agregar los datos de la base de datos a la tabla
            while (rs.next()) {
                table.addCell(new Cell().add(new Paragraph(rs.getString("NOMBRE"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("MARCA"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("CANTIDAD"))).setTextAlignment(TextAlignment.LEFT));
            }

            // Añadir la tabla al documento PDF
            document.add(table);

            // Cerrar el documento
            document.close();

            //Leer el documento
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                //se crea un File que se asociara al PDF
                File pdfFile = new File("REPORTS/reporte_" + filtro + ".pdf");
                //Si el archivo existe
                if (pdfFile.exists()) {
                    //lo abre
                    desktop.open(pdfFile);
                }
            }

            System.out.println("Reporte generado exitosamente para " + filtro);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
