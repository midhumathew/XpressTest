package com.xprsapp.xpress.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import jxl.Cell;
import jxl.CellType;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.jayway.android.robotium.solo.Solo;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

@SuppressWarnings("rawtypes")
public class TestCase extends ActivityInstrumentationTestCase2
{
	
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.xprsapp.xpress.HomeActivity";
	private static Class<?> launcherActivityClass;
	private Solo solo;
	static {
		try {
			launcherActivityClass = Class
					.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	@SuppressWarnings("unchecked")
	public TestCase()
	{
		super(launcherActivityClass);

	}
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		read();
		 readExcelFile();
	}
	private  void readExcelFile() { 
		 

		Context context = getInstrumentation().getContext();
		 String filename="TestData.csv";
		 
	        try{
	            File file = new File(context.getExternalFilesDir(null), filename); 
	            FileInputStream myInput = new FileInputStream(file);
	            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
	            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
	            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
	            Iterator rowIter = mySheet.rowIterator();
	 
	            while(rowIter.hasNext()){
	                HSSFRow myRow = (HSSFRow) rowIter.next();
	                Iterator cellIter = myRow.cellIterator();
	                while(cellIter.hasNext()){
	                    HSSFCell myCell = (HSSFCell) cellIter.next();
	                  
	                }
	            }
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        } 
	       
	        return;
	    } 
	public void testMethod()  {
		
		solo.clickOnButton("Sign Up");
		solo.sleep(10000);
		solo.enterText(0, "midhu");
		solo.enterText(1, "midhu123");
	}
	public void read() throws IOException  {
		
		String inputFile="/mnt/sdcard/Android/data/com.xprsapp.xpress.test/files/TestData.csv";
        File inputWorkbook = new File(inputFile);
        File parent_dir = inputWorkbook.getParentFile();
        Workbook w;
        try {
            System.out.println("Parent dir"+parent_dir);
            if(parent_dir.exists() == true){
                System.out.println("Pardent_dir failed"+"1");
            }
            else
            {
                System.out.println("Pardent _ dir not failed"+"2");
            }
             if(inputWorkbook.exists()== true)
            {
                System.out.println("File Exists");
            }
            else
            {
                System.out.println("File NOt Exists");
                System.out.println("Path "+inputWorkbook.getAbsoluteFile());
            }
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = (Sheet) w.getSheet(0);
            // Loop over first 10 column and lines

            for (int j = 0; j < ((jxl.Sheet) sheet).getColumns(); j++) {
                for (int i = 0; i < ((jxl.Sheet) sheet).getRows(); i++) {
                    Cell cell = ((jxl.Sheet) sheet).getCell(j, i);
                    CellType type = cell.getType();
                    if (cell.getType() == CellType.LABEL) {
                        System.out.println("I got a label "
                                + cell.getContents());
                    }

                    if (cell.getType() == CellType.NUMBER) {
                        System.out.println("I got a number "
                                + cell.getContents());
                    }

                }
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

}
