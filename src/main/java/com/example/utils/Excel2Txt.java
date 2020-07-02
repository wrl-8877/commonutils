package com.example.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 *
 * @author 中科软科技
 * @since 2020/6/19
 */
public class Excel2Txt {

    //读取excel指定sheet中的各行数据，存入二维数组，包括首行
    public static String[][] getSheetData(XSSFSheet sheet) throws IOException {
        String[][] testArray = new String[sheet.getPhysicalNumberOfRows()][];
        for(int rowId =0;rowId<sheet.getPhysicalNumberOfRows();rowId++){
            XSSFRow row = sheet.getRow(rowId);
            List<String> testSetList = new ArrayList<String>();
            for(int column=0;column<row.getPhysicalNumberOfCells();column++){
                row.getCell(column).setCellType(Cell.CELL_TYPE_STRING);
                testSetList.add(row.getCell(column).getStringCellValue());
            }
            testArray[rowId] = (String[])testSetList.
                    toArray(new String[testSetList.size()]);
        }
        return testArray;
    }

    //打印二维数组
    public static void printDoubleArray(String[][] testArray) throws IOException{
        for(int i =0; i<testArray.length;i++ )
        {
            for (int j=0; j<testArray[i].length;j++)
            {
                System.out.println(testArray[i][j]+" ||");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) throws IOException {
        // TODO 自动生成的方法存根

        File file = new File("D:\\test.xlsx");
        FileInputStream fis = new FileInputStream(file);
        @SuppressWarnings("resource")
        XSSFWorkbook wb = new XSSFWorkbook(new BufferedInputStream(fis));
        printDoubleArray(getSheetData(wb.getSheetAt(0)));

    }

}
