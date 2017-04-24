package com.haifeiWu.test;

import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class TestApp {

	// {"code":200,"data":{"failFileList":[],"sucFileList":[]},"errMsg":"Operate success."}

	public void getFile(String result) {
		Map<String, Object> map = (Map<String, Object>) JSON.parse(result);
		Map<String, Object> data = (Map<String, Object>) JSON
				.parse((String) map.get("data"));
		JSONArray sucArray = JSON.parseArray((String) data.get("sucFileList"));
		for (int i = 0; i < sucArray.size(); i++) {
			System.out.println(i + "---------------------" + sucArray.get(i));
		}
		JSONArray failArray = JSON
				.parseArray((String) data.get("failFileList"));
		for (int i = 0; i < failArray.size(); i++) {
			System.out.println(i + "---------------------" + failArray.get(i));
		}

		// {"code":200,"data":{"failFileList":[],"sucFileList":[]},"errMsg":"Operate success."};

	}

	@Test
	public void test12() {
		DateTimeFormatter format = DateTimeFormat
				.forPattern("yyyy-MM-dd HH:mm");
		DateTime enter = DateTime.parse("2017-03-17 08:44", format);
		DateTime leave = DateTime.parse("2017-04-01 10:20", format);
		int hours = Hours.hoursBetween(enter, leave).getHours();
		System.out.println(hours + "小时");
	}

	// @Test
	// public void test01() {
	// String str = "1961, 8, 12";
	// StringBuilder sb = new StringBuilder();
	// String[] arr = str.split(", ");
	// for (int i = 0; i < arr.length; i++) {
	// sb.append(arr[i] + "-");
	// }
	// String date = sb.toString().substring(0, sb.toString().length() - 1);
	// System.out.println(date);
	// }
	//
	// @Test
	// public void test02() {
	// String date = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
	// System.out.println(date);
	// }
	//
	// @Test
	// public void test03() {
	// DateTimeFormatter format = DateTimeFormat
	// .forPattern("yyyy-MM-dd HH:mm:ss");
	// DateTime dateTime2 = DateTime.parse(
	// PropertiesReadUtils.getString("time"), format);
	//
	// String string_u = dateTime2.toString("yyyy-MM-dd HH:mm:ss");
	// System.out.println(string_u);
	// }
	//
	// @Test
	// public void test04() {
	// String string_c = new DateTime().toString("yyyy年MM月dd日 HH:mm:ss EE");
	//
	// System.out.println(string_c);
	// }
	//
	// public String getWebRootPath() throws Exception {
	// ActionContext actionContext = ActionContext.getContext();
	// ServletContext servletContext = (ServletContext) actionContext
	// .get(ServletActionContext.SERVLET_CONTEXT);
	// String rootPath = servletContext.getRealPath("/");
	// return rootPath;
	// }
	//
	// @Test
	// public void test06() throws Exception {
	// }
	//
	// @Test
	// public void test05() {
	// CopyFile.copyFile("D:\\CVRDLL\\zp.bmp", "data/zp2.bmp");
	// // 复制文件
	// // int byteread = 0; // 读取的字节数
	// // InputStream in = null;
	// // OutputStream out = null;
	// //
	// // try {
	// // in = new FileInputStream(new File("D:\\CVRDLL\\zp.bmp"));
	// // out = new FileOutputStream(new File("data/zp2.bmp"));
	// // byte[] buffer = new byte[1024];
	// //
	// // while ((byteread = in.read(buffer)) != -1) {
	// // out.write(buffer, 0, byteread);
	// // }
	// // } catch (FileNotFoundException e) {
	// // throw new RuntimeException();
	// // } catch (IOException e) {
	// // throw new RuntimeException();
	// // } finally {
	// // try {
	// // if (out != null)
	// // out.close();
	// // if (in != null)
	// // in.close();
	// // } catch (IOException e) {
	// // e.printStackTrace();
	// // }
	// // }
	// }

	/*
	 * @Autowired private LineDao lineDao;
	 * 
	 * @Test public void test07(){ lineDao.findAllInfor(); }
	 */

	@Test
	public void test() {
		// RoomService rooms = new RoomServiceImple();
		// PHCSMP_Room room=rooms.findByRoomID(1);
		// System.out.println("roomId: "+room.getRoom_ID());

		// SuspectDao sus = new SuspectDaoImple();
		// System.out.println(sus.findByBandId(1).getAddress());

	}

}
