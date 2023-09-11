package Utils;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

import javax.swing.border.Border;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class GenerateInvoice {

    private static final Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
    private static final Font invoiceNumber = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.RED);
    private static final Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC);
    private static final Font smallBoldHeaderTable = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.WHITE);
    private static final Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
    private static final Font rightsReseverd = new Font(Font.FontFamily.COURIER, 8, Font.ITALIC);

    public Document generateSingleInvoice(String pathToSave ) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pathToSave));
        document.open();
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 2);
        document.add(preface);

//        //* Header
//        createHeader(document);
//
//        //* Customer and billing info
//        billingCustomerInfo(document);
//
//        //* Producto
//        productInfoTable(document);
//
//        //* Total Table
//        tableTotalProduct(document);
//
//        //* Footer content
//        footerContent(document);
//        document.newPage();
//        document.close();
        return document;
    }

    public void footerContent(Document doc) throws DocumentException {

        Year currentYear = Year.now();

        // Get the value of the current year as an integer
        int yearValue = currentYear.getValue();

        Paragraph footerParagraph = new Paragraph();
        addEmptyLine(footerParagraph, 2);
        doc.add(footerParagraph);
        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(BaseColor.LIGHT_GRAY);
        lineSeparator.setLineWidth(2);
        Chunk lineChunk = new Chunk(lineSeparator);
        footerParagraph.add( new Paragraph(lineChunk));

        footerParagraph.add(new Paragraph("Terminos y condiciones", redFont));
        doc.add(footerParagraph);

        Paragraph terms = new Paragraph("Tienes una garantia de 365 dias, Drive X port te brindara todos los servicios necesarios para el mantenimiendo del vehiculo comprado, la garantia se invalida si el vehiculo sufre algun desperfecto que no sea de fabrica", smallBold);
        terms.setAlignment(Element.ALIGN_LEFT);
        addEmptyLine(terms, 1);
        Paragraph derechos = new Paragraph("Todos los derechos reservados R Drive X Port " + yearValue, rightsReseverd);
        derechos.setAlignment(Element.ALIGN_CENTER);
        doc.add(terms);
        doc.add(derechos);
    }

    public void tableTotalProduct(Document doc, float total_price_iva) throws DocumentException {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        PdfPTable productTotalTable = new PdfPTable(3);
        productTotalTable.setWidthPercentage(100);
        Paragraph p = new Paragraph();
        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(BaseColor.LIGHT_GRAY);
        lineSeparator.setLineWidth(1);
        Chunk lineChunk = new Chunk(lineSeparator);
        p.add( new Paragraph(lineChunk));
        addEmptyLine(p, 1);
        doc.add(p);

        PdfPCell tC = new PdfPCell(new Phrase("", headerFont));
        tC.setBorderWidth(0f);
//        productCell.setCellEvent(headerCellEvent);
        productTotalTable.addCell(tC);

        //********
        tC = new PdfPCell(new Phrase("Total", headerFont));
        tC.setBorderWidth(0f);
        tC.setHorizontalAlignment(Element.ALIGN_CENTER);
        productTotalTable.addCell(tC);

        tC = new PdfPCell(new Phrase(formatter.format(total_price_iva), headerFont));
        tC.setBorderWidth(0f);
        tC.setBorder(PdfPCell.TOP);
        tC.setHorizontalAlignment(Element.ALIGN_RIGHT);
        productTotalTable.addCell(tC);

        doc.add(productTotalTable);
    }

    public void productInfoTable(Document doc, String product_name, int cantidad, float price_with_no_iva, float iva, float ive_price) throws DocumentException {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        Paragraph preface = new Paragraph();
        PdfPTable productTable = new PdfPTable(3);
        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(BaseColor.LIGHT_GRAY);
        lineSeparator.setLineWidth(1);
        Chunk lineChunk = new Chunk(lineSeparator);
        preface.add(lineChunk); //******

//        p.add( new Paragraph(lineChunk));
        preface.add(new Paragraph("Producto", subFont));
        CustomHeaderCellEvent headerCellEvent = new CustomHeaderCellEvent();

        productTable.setWidthPercentage(100);

        PdfPCell productCell = new PdfPCell(new Phrase("Producto", smallBoldHeaderTable));
        productCell.setBorderWidth(0f);
        productCell.setCellEvent(headerCellEvent);
        productTable.addCell(productCell);

        productCell = new PdfPCell(new Phrase("Cantidad", smallBoldHeaderTable));
        productCell.setBorderWidth(0f);
        productCell.setCellEvent(headerCellEvent);
        productCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        productTable.addCell(productCell);

        productCell = new PdfPCell(new Phrase("Precio", smallBoldHeaderTable));
        productCell.setBorderWidth(0f);
        productCell.setCellEvent(headerCellEvent);
        productCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        productTable.addCell(productCell);

        productCell = new PdfPCell(new Phrase(product_name, catFont));
        productCell.setBorderWidth(0f);
        productTable.addCell(productCell);

        productCell = new PdfPCell(new Phrase(String.valueOf(cantidad), catFont));
        productCell.setBorderWidth(0f);
        productCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        productTable.addCell(productCell);

        productCell = new PdfPCell(new Phrase(formatter.format(price_with_no_iva), catFont));
        productCell.setBorderWidth(0f);
        productCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        productTable.addCell(productCell);

        //* Agregar IVA
        productCell = new PdfPCell(new Phrase("IVA", catFont));
        productCell.setBorderWidth(0f);
        productTable.addCell(productCell);

        productCell = new PdfPCell(new Phrase(iva * 100 + " %", catFont));
        productCell.setBorderWidth(0f);
        productCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        productTable.addCell(productCell);

        productCell = new PdfPCell(new Phrase(formatter.format(ive_price), catFont));
        productCell.setBorderWidth(0f);
        productCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        productTable.addCell(productCell);

        addEmptyLine(preface, 1);
        doc.add(preface);
        addEmptyLine(preface, 1);
        doc.add(productTable);// *aca
    }

    public void billingCustomerInfo(Document doc, String full_name, String phone_number, String email_address, String address, String postal_code) throws DocumentException {
        Paragraph preface = new Paragraph();
        PdfPTable headerInfo = new PdfPTable(2);
        PdfPTable userAndBilling =  new PdfPTable(2);
        PdfPTable userTable =  new PdfPTable(2);
        PdfPTable billingTable = new PdfPTable(2);
        userAndBilling.getDefaultCell().setBorderWidth(0f);
        headerInfo.setWidthPercentage(100);
        userAndBilling.setWidthPercentage(100);

        PdfPCell c3 = new PdfPCell(new Phrase("Informacion de cliente", subFont));
        c3.setBorderWidth(0f);
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        headerInfo.addCell(c3);

        c3 = new PdfPCell(new Phrase("Informacion de envio", subFont));
        c3.setBorderWidth(0f);
        c3.setHorizontalAlignment(Element.ALIGN_LEFT);
        headerInfo.addCell(c3);

        //* name
        PdfPCell c4 = new PdfPCell(new Phrase("Nombre: ", headerFont));
        c4.setBorderWidth(0f);
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        userTable.addCell(c4);

        c4 = new PdfPCell(new Phrase(full_name, catFont));
        c4.setBorderWidth(0f);
        c4.setHorizontalAlignment(Element.ALIGN_LEFT);
        userTable.addCell(c4);
        //*

        c4 = new PdfPCell(new Phrase("Telefono: ", headerFont));
        c4.setBorderWidth(0f);
        userTable.addCell(c4);

        c4 = new PdfPCell(new Phrase(phone_number, catFont));
        c4.setBorderWidth(0f);
        userTable.addCell(c4);

        c4 = new PdfPCell(new Phrase("Correo: ", headerFont));
        c4.setBorderWidth(0f);
        userTable.addCell(c4);

        c4 = new PdfPCell(new Phrase(email_address, catFont));
        c4.setBorderWidth(0f);
        userTable.addCell(c4);
        userTable.getDefaultCell().setBorderWidth(0f);
        userAndBilling.addCell(userTable);
        //? -----------------------------------------------------------------------------------------------------------

        PdfPCell c5 = new PdfPCell(new Phrase("Direccion: ", headerFont));
        c5.setBorderWidth(0f);
        billingTable.addCell(c5);

        c5 = new PdfPCell(new Phrase(address, catFont));
        c5.setBorderWidth(0f);
        billingTable.addCell(c5);
        //*

        c5 = new PdfPCell(new Phrase("Codigo postal: ", headerFont));
        c5.setBorderWidth(0f);
        billingTable.addCell(c5);

        c5 = new PdfPCell(new Phrase(postal_code, catFont));
        c5.setBorderWidth(0f);
        billingTable.addCell(c5);
        userAndBilling.addCell(billingTable);

        doc.add(headerInfo);
        //? Agregar linea
        addEmptyLine(preface, 1);
        doc.add(preface);
        doc.add(userAndBilling);
        addEmptyLine(preface, 2);
        doc.add(preface);
    }
    public void createHeader(Document doc) throws DocumentException {
        Random random = new Random();
        // Generate a random 3-digit number (between 100 and 999)
        int randomNumber = random.nextInt(900) + 100;
        int randomNumber2 = random.nextInt(900) + 100;
        String invoice_number = randomNumber + "-" +randomNumber2;
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a date and time format pattern
        String pattern = "yyyy/MM/dd HH:mm:ss"; // Format includes year, month, day, hour, minute, and second

        // Create a DateTimeFormatter with the pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        // Format the current date and time
        String formattedDateTime = currentDateTime.format(formatter);

        Paragraph preface = new Paragraph();
        PdfPTable table = new PdfPTable(2);
        PdfPTable nestedTable = new PdfPTable(2);
        table.setWidthPercentage(100);

        PdfPCell c1 = new PdfPCell(new Phrase("Drive X Port", subFont));
        c1.setBorderWidth(0);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        PdfPCell c0 = new PdfPCell(new Phrase("Factura No.", headerFont));
        c0.setHorizontalAlignment(Element.ALIGN_CENTER);
        c0.setBorderWidth(0);
        nestedTable.addCell(c0);

        // TODO: modifical el codigo que se adinamico
        c0 = new PdfPCell(new Phrase(invoice_number, invoiceNumber));
        c0.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c0.setBorderWidth(0);
        nestedTable.addCell(c0);

        c0 = new PdfPCell(new Phrase("Fecha: ", headerFont));
        c0.setHorizontalAlignment(Element.ALIGN_CENTER);
        c0.setBorderWidth(0);
        nestedTable.addCell(c0);

        // TODO: modifical la fecha para que sea dinamica
        c0 = new PdfPCell(new Phrase(formattedDateTime, catFont));
        c0.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c0.setBorderWidth(0);
        nestedTable.addCell(c0);

        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(BaseColor.LIGHT_GRAY);
        lineSeparator.setLineWidth(3);
        Chunk lineChunk = new Chunk(lineSeparator);
        preface.add( new Paragraph(lineChunk));
        addEmptyLine(preface, 2);
        nestedTable.getDefaultCell().setBorderWidth(0f);
        table.getDefaultCell().setBorderWidth(0f);
        table.addCell(nestedTable);
        doc.add(table);
        doc.add(preface);
    }

    private static class CustomHeaderCellEvent implements PdfPCellEvent {
        private final BaseColor backgroundColor = new BaseColor(54, 69, 79); // Light blue color
        @Override
        public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.BACKGROUNDCANVAS];
            canvas.setColorFill(backgroundColor);
            canvas.rectangle(position.getLeft(), position.getBottom(), position.getWidth(), position.getHeight());
            canvas.fill();
        }
    }

    private static void addEmptyLine(com.itextpdf.text.Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
