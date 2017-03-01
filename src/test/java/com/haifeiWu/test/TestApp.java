package com.haifeiWu.test;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.ActivityRecordDao;
import com.haifeiWu.dao.LineDao;
import com.haifeiWu.dao.SuspectDao;
import com.haifeiWu.daoImple.ActivityRecordDaoImple;
import com.haifeiWu.daoImple.LineDaoImple;
import com.haifeiWu.daoImple.SuspectDaoImple;
import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.entity.PHCSMP_Line;
import com.haifeiWu.utils.CopyFile;
import com.haifeiWu.utils.PropertiesReadUtils;
import com.haifeiWu.utils.Video;
import com.opensymphony.xwork2.ActionContext;


public class TestApp {
	
//	@Test
//	public void test01() {
//		String str = "1961, 8, 12";
//		StringBuilder sb = new StringBuilder();
//		String[] arr = str.split(", ");
//		for (int i = 0; i < arr.length; i++) {
//			sb.append(arr[i] + "-");
//		}
//		String date = sb.toString().substring(0, sb.toString().length() - 1);
//		System.out.println(date);
//	}
//
//	@Test
//	public void test02() {
//		String date = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
//		System.out.println(date);
//	}
//
//	@Test
//	public void test03() {
//		DateTimeFormatter format = DateTimeFormat
//				.forPattern("yyyy-MM-dd HH:mm:ss");
//		DateTime dateTime2 = DateTime.parse(
//				PropertiesReadUtils.getString("time"), format);
//
//		String string_u = dateTime2.toString("yyyy-MM-dd HH:mm:ss");
//		System.out.println(string_u);
//	}
//
//	@Test
//	public void test04() {
//		String string_c = new DateTime().toString("yyyy年MM月dd日 HH:mm:ss EE");
//
//		System.out.println(string_c);
//	}
//
//	public String getWebRootPath() throws Exception {
//		ActionContext actionContext = ActionContext.getContext();
//		ServletContext servletContext = (ServletContext) actionContext
//				.get(ServletActionContext.SERVLET_CONTEXT);
//		String rootPath = servletContext.getRealPath("/");
//		return rootPath;
//	}
//
//	@Test
//	public void test06() throws Exception {
//	}
//
//	@Test
//	public void test05() {
//		CopyFile.copyFile("D:\\CVRDLL\\zp.bmp", "data/zp2.bmp");
//		// 复制文件
//		// int byteread = 0; // 读取的字节数
//		// InputStream in = null;
//		// OutputStream out = null;
//		//
//		// try {
//		// in = new FileInputStream(new File("D:\\CVRDLL\\zp.bmp"));
//		// out = new FileOutputStream(new File("data/zp2.bmp"));
//		// byte[] buffer = new byte[1024];
//		//
//		// while ((byteread = in.read(buffer)) != -1) {
//		// out.write(buffer, 0, byteread);
//		// }
//		// } catch (FileNotFoundException e) {
//		// throw new RuntimeException();
//		// } catch (IOException e) {
//		// throw new RuntimeException();
//		// } finally {
//		// try {
//		// if (out != null)
//		// out.close();
//		// if (in != null)
//		// in.close();
//		// } catch (IOException e) {
//		// e.printStackTrace();
//		// }
//		// }
//	}
	
	/*@Autowired 
	private LineDao lineDao;
	@Test
	public void test07(){
		lineDao.findAllInfor();
	}*/

}
