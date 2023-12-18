package ma.emsi.dataExport;

import ma.emsi.entities.Client;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

public class ExcelFile {
    public static void exportClients(List<Client> clients, String filePath) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Clients");
            sheet.setDefaultColumnWidth(20);
            fillClientSheet(sheet, clients);
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(12);
            style.setFont(font);
            XSSFRow headerRow = sheet.createRow(0);
            createCell(headerRow, 0,"IDENTIFIANT", style);
            createCell(headerRow, 1,"NOM", style);
            createCell(headerRow, 2,"PRENOM", style);
            createCell(headerRow, 3,"PAYS", style);
            createCell(headerRow, 4,"VILLE", style);
            createCell(headerRow, 5,"ADRESSE", style);
            createCell(headerRow, 6,"TELEPHONE", style);
            workbook.write(new FileOutputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillClientSheet(XSSFSheet sheet, List<Client> clients) {
        int rowNum = 0;
        for (Client client : clients) {
            XSSFRow row = sheet.createRow(++rowNum);
            row.createCell(0).setCellValue(client.getId());
            row.createCell(1).setCellValue(client.getNom());
            row.createCell(2).setCellValue(client.getPrenom());
            row.createCell(3).setCellValue(client.getPays());
            row.createCell(4).setCellValue(client.getVille());
            row.createCell(5).setCellValue(client.getAdresse());
            row.createCell(6).setCellValue(client.getTelephone());
        }
    }
}
