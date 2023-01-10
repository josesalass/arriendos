package com.example.arriendos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.arriendos.model.Residencia;
import com.example.arriendos.model.Usuario;

public class ResidenciaExcelExporter {
    
    private XSSFWorkbook workbook;

    private XSSFSheet sheet;

    private Usuario user;
    private List<Residencia> listResidencias;

    //Constructor
    public ResidenciaExcelExporter(List<Residencia> listResidencias) {
        this.listResidencias = listResidencias;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "Rut", style);      
        createCell(row, 1, "Id_Residencia", style);       
        createCell(row, 2, "Direccion", style);    
        createCell(row, 3, "Descripcion", style);
        createCell(row, 4, "Piezas", style);
        createCell(row, 5, "Solo hombres", style);
        createCell(row, 6, "Solo mujeres", style);
        createCell(row, 7, "Servicios básicos compartidos", style);

        
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }


    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Residencia residencia : listResidencias) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            String reestriccion1 = "No";
            String reestriccion2 = "No";
            String reestriccion3 = "No";

            if(residencia.getReestriccion1()){
                reestriccion1 = "Si";
            }
            if(residencia.getReestriccion2()){
                reestriccion2 = "Si";
            }
            if(residencia.getReestriccion3()){
                reestriccion3 = "Si";
            }
             
            createCell(row, columnCount++, residencia.getUsuario().getId(), style);
            createCell(row, columnCount++, residencia.getId(), style);
            createCell(row, columnCount++, residencia.getDireccion(), style);
            createCell(row, columnCount++, residencia.getDescripcion(), style);
            createCell(row, columnCount++, residencia.getPiezas().size(), style);
            createCell(row, columnCount++, reestriccion1, style);
            createCell(row, columnCount++, reestriccion2, style);
            createCell(row, columnCount++, reestriccion3, style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();         
    }


    
}
