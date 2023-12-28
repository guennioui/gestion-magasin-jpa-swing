/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.dataExport;

import java.io.FileOutputStream;
import java.io.IOException;
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
public class FournisseurExport {
    public static void exportFournisseurs(List<Fournisseur> fournisseurs, String filePath) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Fournisseurs");
            sheet.setDefaultColumnWidth(20);
            fillFournisseurSheet(sheet, fournisseurs);
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(12);
            style.setFont(font);
            XSSFRow headerRow = sheet.createRow(0);
            createCell(headerRow, 0,"IDENTIFIANT", style);
            createCell(headerRow, 1,"NOM", style);
            createCell(headerRow, 2,"PRENOM", style);
            createCell(headerRow, 3,"ADRESSE", style);
            createCell(headerRow, 4,"VILLE", style);            
            createCell(headerRow, 5,"TELEPHONE", style);
            workbook.write(new FileOutputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillFournisseurSheet(XSSFSheet sheet, List<Fournisseur> fournisseurs) {
        int rowNum = 0;
        for (Fournisseur fournisseur : fournisseurs) {
            XSSFRow row = sheet.createRow(++rowNum);
            row.createCell(0).setCellValue(fournisseur.getNumFournisseur());
            row.createCell(1).setCellValue(fournisseur.getNom());
            row.createCell(2).setCellValue(fournisseur.getPrenom());
            row.createCell(3).setCellValue(fournisseur.getAdresse());
            row.createCell(4).setCellValue(fournisseur.getVille());            
            row.createCell(5).setCellValue(fournisseur.getTelephone());
        }
    }
}
