package com.haifeiWu.test;

import org.joda.time.DateTime;
import org.junit.Test;

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
}
