/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.dataExport;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import ma.emsi.entities.Fournisseur;
import ma.emsi.entities.SocieteDistribution;
import org.apache.poi.ss.usermodel.CellStyle;
import static org.apache.poi.ss.util.CellUtil.createCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author abdoe
 */
public class SocieteDistributionExport {
    public static void exportSocieteDistribution(List<SocieteDistribution> societeDistributions, String filePath) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("SocieteDistributions");
            sheet.setDefaultColumnWidth(20);
            fillSocieteDistribution(sheet, societeDistributions);
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(12);
            style.setFont(font);
            XSSFRow headerRow = sheet.createRow(0);
            createCell(headerRow, 0,"IDENTIFIANT", style);
            createCell(headerRow, 1,"NOM", style);
            createCell(headerRow, 2,"VILLE", style);
            createCell(headerRow, 3,"ADRESSE", style);
            createCell(headerRow, 4,"TELEPHONE", style);            
            createCell(headerRow, 5,"FOURNISSEUR", style);
            workbook.write(new FileOutputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillSocieteDistribution(XSSFSheet sheet, List<SocieteDistribution> societeDistributions) {
        int rowNum = 0;
        for (SocieteDistribution societeDistribution : societeDistributions) {
            XSSFRow row = sheet.createRow(++rowNum);
            row.createCell(0).setCellValue(societeDistribution.getId());
            row.createCell(1).setCellValue(societeDistribution.getNom());
            row.createCell(2).setCellValue(societeDistribution.getVille());
            row.createCell(3).setCellValue(societeDistribution.getAdresse());
            row.createCell(4).setCellValue(societeDistribution.getTelephone());            
            row.createCell(5).setCellValue(societeDistribution.getFournisseur().getNom());
        }
    }
}
