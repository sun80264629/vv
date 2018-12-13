package com.vv.demo.util.excel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * Hello world!
 * 
 */
public class ExcelUtil {
	private List<String> titlesList;
	
	public static void main(String[] args) {
		Workbook wb;
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy",Locale.US);
		//fmt.setTimeZone(TimeZone.getTimeZone("U.S"));
		Date d = new Date();
		System.out.println(fmt.format(d));
		//fmt.parse("31-012");
	}
}
