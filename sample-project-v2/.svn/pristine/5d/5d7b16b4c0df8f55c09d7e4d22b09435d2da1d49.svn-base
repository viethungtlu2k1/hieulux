package com.globits.sample.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import com.globits.sample.domain.Student;
import com.globits.sample.dto.StudentDto;

public class ImportExcel {
	
	static Map<String, Integer> hashColumnSinhVien = new HashMap<>();
	
	public static List<StudentDto> importStudent(InputStream is){
		hashColumnSinhVien.put("name", 0);
		hashColumnSinhVien.put("code", 2);
		hashColumnSinhVien.put("location",3);
		hashColumnSinhVien.put("birthdate", 1);
		try {
			List<StudentDto> ret = new ArrayList<>();
			Workbook workbook = new XSSFWorkbook(is);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			int rowIndex = 1;
			int num = datatypeSheet.getLastRowNum();
			while(rowIndex <= num) {
				Row currentRow = datatypeSheet.getRow(rowIndex);
				Cell currentCell;
				
				if(currentRow != null) {
					StudentDto svDto = new StudentDto();
					Integer index = hashColumnSinhVien.get("name");
					if(index != null) {
						currentCell = currentRow.getCell(index); // name
						if(currentCell != null) {
							String name = null;
							if (currentCell.getCellTypeEnum() == CellType.STRING && StringUtils.hasText(currentCell.getStringCellValue())) {
								name = currentCell.getStringCellValue();
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								name = String.valueOf(Double.valueOf(currentCell.getNumericCellValue()).longValue());
							}
							svDto.setLastName(name);
						}
					}
					
					index = hashColumnSinhVien.get("location");
					if(index != null) {
						currentCell = currentRow.getCell(index); // address
						if (currentCell != null) {
							String address = null;
							if (currentCell.getCellTypeEnum() == CellType.STRING && StringUtils.hasText(currentCell.getStringCellValue())) {
								address = currentCell.getStringCellValue();
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								address = String.valueOf(Double.valueOf(currentCell.getNumericCellValue()).longValue());
							}
							svDto.setLocation(address);
						}
					}
					
					index = hashColumnSinhVien.get("code");
					if(index != null) {
						currentCell = currentRow.getCell(index); // address
						if (currentCell != null) {
							String code = null;
							if (currentCell.getCellTypeEnum() == CellType.STRING && StringUtils.hasText(currentCell.getStringCellValue())) {
								code = currentCell.getStringCellValue();
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								code = String.valueOf(Double.valueOf(currentCell.getNumericCellValue()).longValue());
							}
							svDto.setCode(code);
						}
					}
					index = hashColumnSinhVien.get("birthdate");
					if(index != null) {
						currentCell = currentRow.getCell(index); // address
						if(currentCell.getCellTypeEnum() == CellType.NUMERIC||currentCell.getCellTypeEnum() == CellType.FORMULA)
						   {
							String cellValue=String.valueOf(currentCell.getNumericCellValue());
						     if(HSSFDateUtil.isCellDateFormatted(currentCell))
						      {
						          Date date = currentCell.getDateCellValue();
						          svDto.setBirthDate(date);
						          //cellValue = df.format(date);
						          //System.out.println(cellValue);
						       }						       
						    }
						 else if (currentCell.getCellTypeEnum() == CellType.STRING && StringUtils.hasText(currentCell.getStringCellValue())) {
							String cellValue=currentCell.getStringCellValue();  
							DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					        Date date = df.parse(cellValue);
					        svDto.setBirthDate(date);
						}
					}
					ret.add(svDto);
				}
				
				rowIndex++;
			}
			
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	
	public static void main (String[] args) throws FileNotFoundException {
//		Student s = new Student();
//		s.setFirstName("Nguyen Ngoc Khanh");
//		StudentDto d=new StudentDto(s);
//		System.out.println(d.getFirstName());
		FileInputStream f = new FileInputStream("D:\\Projects\\Training\\globits-v2\\sample-project-v2\\sample-project-v2-app\\Student.xlsx");//Doi duong dan o day
		List<StudentDto> list = importStudent(f);
		System.out.println(list.size());
	}
}
