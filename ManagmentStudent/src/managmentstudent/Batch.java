/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managmentstudent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Batch {

    ArrayList<Student> listStudent;

    public Batch() {
        this.listStudent = new ArrayList<Student>();
    }

    public ArrayList<Student> getListStudent() {
        return listStudent;
    }

    public void setListStudent(ArrayList<Student> listStudent) {
        this.listStudent = listStudent;
    }

    @Override
    public String toString() {
        return "Batch{" + "listStudent=" + listStudent + '}';
    }

    public void exportToExcel() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Student Data");
        Row headerRow = sheet.createRow(0);

        Cell idHeaderCell = headerRow.createCell(0);
        idHeaderCell.setCellValue("ID");

        Cell nameHeaderCell = headerRow.createCell(1);
        nameHeaderCell.setCellValue("Name");

        Cell birthdayHeaderCell = headerRow.createCell(2);
        birthdayHeaderCell.setCellValue("Birthday");

        Cell markHeaderCell = headerRow.createCell(3);
        markHeaderCell.setCellValue("Mark");

        int rowNum = 1;
        for (Student student : listStudent) {
            Row row = sheet.createRow(rowNum++);

            Cell idCell = row.createCell(0);
            idCell.setCellValue(student.getId());

            Cell nameCell = row.createCell(1);
            nameCell.setCellValue(student.getName());

            Cell birthdayCell = row.createCell(2);
            birthdayCell.setCellValue(student.getBirthday().toString());

            Cell markCell = row.createCell(3);
            markCell.setCellValue(student.getMean_of_marks());
        }

        try {
            FileOutputStream outputStream = new FileOutputStream("studentlist.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
