/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.dataExport;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import ma.emsi.entities.Fournisseur;
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
public class ArticleExport {
    public static void exportArticles(List<Object[]> articles, String filePath) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Articles");
            sheet.setDefaultColumnWidth(20);
            fillArticleSheet(sheet, articles);
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(12);
            style.setFont(font);
            XSSFRow headerRow = sheet.createRow(0);
            createCell(headerRow, 0,"CODE", style);
            createCell(headerRow, 1,"ARTICLE", style);
            createCell(headerRow, 2,"CATEGORIE", style);
            createCell(headerRow, 3,"PRIX", style);
            createCell(headerRow, 4,"QUANTITE", style);            
            createCell(headerRow, 5,"DEPOT", style);
            createCell(headerRow, 6,"DATE DEPOT", style);
            createCell(headerRow, 7,"FOURNISSEUR", style);
            createCell(headerRow, 8,"SOCIETE DISTRIBUTION", style);
            workbook.write(new FileOutputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillArticleSheet(XSSFSheet sheet, List<Object[]> articles) {
        int rowNum = 0;
        for (Object[] article : articles) {
            XSSFRow row = sheet.createRow(++rowNum);
            row.createCell(0).setCellValue((String) (Object) article[0]);
            row.createCell(1).setCellValue((String) article[1]);
            row.createCell(2).setCellValue((String) article[2]);
            row.createCell(3).setCellValue(((BigDecimal) article[3]).toString());            
            row.createCell(4).setCellValue((int)article[4]);            
            row.createCell(5).setCellValue((String) article[5]);            
            row.createCell(6).setCellValue(article[6].toString());
            row.createCell(7).setCellValue((String) article[7]);
            row.createCell(8).setCellValue((String) article[8]);            
        }
    }
}
