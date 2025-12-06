package Bankmanagementsystem;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
import java.util.List;

public class PDFGenerator {

    public static void createPDF(String outputFile, List<String> lines) throws Exception {

        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        PdfWriter.getInstance(document, new FileOutputStream(outputFile));
        document.open();

        // Convert AWT Color → BaseColor
        BaseColor primary = new BaseColor(UIUtils.PRIMARY.getRed(),
                                          UIUtils.PRIMARY.getGreen(),
                                          UIUtils.PRIMARY.getBlue());

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, primary);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

        Paragraph title = new Paragraph("Mini Statement", headerFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        for (String line : lines) {
            document.add(new Paragraph(line, normalFont));
        }

        document.close();
    }
}
