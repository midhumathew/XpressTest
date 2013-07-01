package com.xprsapp.xpress.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Boolean;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import android.content.Context;
import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Toast;

import com.jayway.android.robotium.solo.Solo;

public class TestCaseSignUp extends ActivityInstrumentationTestCase2 {
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.xprsapp.xpress.HomeActivity";
	private Solo solo;
	Context context = getInstrumentation().getContext();
	Workbook wb;
	WritableWorkbook copy;
	WritableSheet sheet;
	boolean FuntionResult = false;
	Cell rowData[] = null;
	int rowCount = '0';
	WorkbookSettings ws = null;
	Workbook workbook = null;
	Sheet s = null;
	Boolean status;

	String[] vFirstValue, vSecondValue, vExpectedValue, vExecute;
	private static Class<?> launcherActivityClass;
	static {
		try {
			launcherActivityClass = Class
					.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public TestCaseSignUp() throws ClassNotFoundException {
		super(launcherActivityClass);
	}

	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());		
		getTestData();
	}

	public void getTestData() throws Exception {	
		 readExcelFile();
		FileInputStream fis = null;
		File sdCard = Environment.getExternalStorageDirectory();
		File dir = new File(sdCard.getAbsolutePath() + "/TestDataInput.xlsx");
		
		
		
		try {
			fis = new FileInputStream(dir);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Get the object of DataInputStream
		readTestData(fis);
		DataInputStream in = new DataInputStream(fis);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			Log.w("FileUtils", "File data: " + strLine);
		}
		in.close();	
		String ret = "";
		
		 try {
	            InputStream dbInputStream = context.getAssets().open("TestDataInput.xls", Context.MODE_WORLD_READABLE);
	            int cols = 9;
	            Cell[] row;
	            Cell cell;
	            Workbook w;
	            w = Workbook.getWorkbook(dbInputStream);
	            Sheet sheet = w.getSheet(0);
	            for (int r = 1; r < sheet.getRows(); r++) {
	               
	                row = sheet.getRow(r);
	                if (row != null) {
	                    for (int c = 0; c < cols; c++) {
	                        cell = sheet.getCell(c, r);
	                       
	                    }
	                  
	                }
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (BiffException e) {
	            e.printStackTrace();
	        }		
		
		
		
		
		try {
			InputStream inputStream = context.openFileInput("URL.txt");

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null) {
					stringBuilder.append(receiveString);
				}

				inputStream.close();
				ret = stringBuilder.toString();
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

	}

	 private  void readExcelFile() { 
		 
		 //Context context = getInstrumentation().getContext();
		 String filename="TestDataInput.xlsx";
	        try{
	            // Creating Input Stream 
	            File file = new File(context.getExternalFilesDir(null), filename); 
	            FileInputStream myInput = new FileInputStream(file);
	 
	            // Create a POIFSFileSystem object 
	            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
	 
	            // Create a workbook using the File System 
	            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
	 
	            // Get the first sheet from workbook 
	            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
	 
	            /** We now need something to iterate through the cells.**/
	            Iterator rowIter = mySheet.rowIterator();
	 
	            while(rowIter.hasNext()){
	                HSSFRow myRow = (HSSFRow) rowIter.next();
	                Iterator cellIter = myRow.cellIterator();
	                while(cellIter.hasNext()){
	                    HSSFCell myCell = (HSSFCell) cellIter.next();
	                  
	                }
	            }
	        }catch (Exception e){e.printStackTrace(); }
	 
	        return;
	    } 

	public void readTestData(InputStream fileInputStream) throws Exception {
		ws = null;
		workbook = null;
		s = null;
		int columnCount = 0;
		int totalSheet = 0;
		String firstSheet = "Sheet1";

		try {
			ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));
			workbook = Workbook.getWorkbook(fileInputStream, ws);

			totalSheet = workbook.getNumberOfSheets();
			if (totalSheet > 0) {

				if (!workbook.getSheet(0).getName().equals(firstSheet)) {
					System.out.println("contents are not fine");
				}

			}

			else
				System.out.println("There is not any sheet available.");

			s = workbook.getSheet(1);

			rowCount = s.getRows();
			vFirstValue = new String[rowCount];
			vSecondValue = new String[rowCount];
			vExpectedValue = new String[rowCount];
			vExecute = new String[rowCount];

			columnCount = s.getColumns();
			rowData = s.getRow(0);

			if (rowData[0].getContents().length() != 0) {
				for (int i = 1; i < rowCount; i++) {

					rowData = s.getRow(i);
					if (rowData[0].getContents().equals("TC1")) {

						if (rowData[5].getContents().equals("Yes")) {
							System.out.println("Will Execute: "
									+ rowData[1].getContents());
							vFirstValue[i] = rowData[2].getContents()
									.toString();
							vSecondValue[i] = rowData[3].getContents()
									.toString();
							vExpectedValue[i] = rowData[4].getContents()
									.toString();
							vExecute[i] = rowData[5].getContents().toString();
						} else {
							System.out.println("We will skip "
									+ rowData[1].getContents());
						}
					}
					// System.out.println("Read Data is "+vFirstValue[i]+" "+vSecondValue[i]+" "+vExpectedValue[i]+" "+vExecute[i]);
				}

				System.out.println("Success");

			}
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	public void testCanOpenSettings() throws Exception {

		readExcelFile();
		getTestData();
		solo.clickOnButton("Sign Up");
		solo.sleep(10000);
		solo.enterText(0, "midhu");
		solo.enterText(1, "midhu123");
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();

	}
}
