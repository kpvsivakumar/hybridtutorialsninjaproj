package com.tutorialninja.qa.utilites;

import java.util.Arrays;

public class practise {
	public static void main(String[] args) {
		Object[][] data = Utiles.excelReader("Sheet1");
		System.out.println(Arrays.deepToString(data));
		String timeStamp = Utiles.getTimeStamp();
		System.out.println(timeStamp);

	}
}
