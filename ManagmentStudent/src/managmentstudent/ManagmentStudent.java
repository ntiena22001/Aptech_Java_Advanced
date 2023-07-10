/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package managmentstudent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ManagmentStudent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Batch batch = new Batch();
        batch.listStudent.add(new Student("A001", "Nguyen Van An", "16/04/2004", 8.5f));
        batch.listStudent.add(new Student("A002", "Nguyen Van Be", "05/10/2004", 7.5f));
        batch.listStudent.add(new Student("A003", "Nguyen Van Ngoan", "09/05/2004", 8.5f));
        batch.listStudent.add(new Student("A004", "Thai Thanh Liem", "15/11/2004", 6.5f));
        batch.listStudent.add(new Student("A005", "Cao Van Ut", "04/03/2004", 7.5f));
        batch.exportToExcel();
       

        
    }
}
