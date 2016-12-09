package com.haifeiWu.test;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import com.haifeiWu.utils.PropertiesReadUtils;

public class TestApp {
	@Test
	public void test01() {
		String str = "1961, 8, 12";
		StringBuilder sb = new StringBuilder();
		String[] arr = str.split(", ");
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i] + "-");
		}
		String date = sb.toString().substring(0, sb.toString().length() - 1);
		System.out.println(date);
	}

	@Test
	public void test02() {
		String date = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
		System.out.println(date);
	}

	@Test
	public void test03() {
		DateTimeFormatter format = DateTimeFormat
				.forPattern("yyyy-MM-dd HH:mm:ss");
		DateTime dateTime2 = DateTime.parse(
				PropertiesReadUtils.getString("time"), format);

		String string_u = dateTime2.toString("yyyy-MM-dd HH:mm:ss");
		System.out.println(string_u);
	}

	@Test
	public void test04() {
		String string_c = new DateTime().toString("yyyy年MM月dd日 HH:mm:ss EE");

		System.out.println(string_c);
	}
}
