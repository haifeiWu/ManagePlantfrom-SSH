package com.haifeiWu.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haifeiWu.utils.PropertiesReadUtils;
import com.haifeiWu.utils.PropertiesWriteUtils;

@Controller
@Scope("prototype")
@RequestMapping("/properties")
public class InitPropertiesAction {

	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request) {
		return "WEB-INF/jsp/home/initConfigs";
	}

	@RequestMapping(value="/getName")
	public String getName(HttpServletRequest request,HttpServletResponse response){
		try {
			String title = PropertiesReadUtils.getTitleString("title");
			String name = PropertiesReadUtils.getTitleString("name");
			String flag=title.substring(0, 1);
			if(flag!=null&&flag.equals("\\")){
				title = PropertiesWriteUtils.ascii2Native(title);
				name = PropertiesWriteUtils.ascii2Native(name);
				System.out.println(title+"...."+name);
			}
			request.setAttribute("name", name);
			request.setAttribute("title", title);
			response.setContentType("text/plain; charset=utf-8");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jsp/login";
	}
	
	@RequestMapping("/init")
	public String init(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			// 派出所系统信息初始化的Map
			String title = request.getParameter("title");
			String name = request.getParameter("name");
			// System.out.println("====从前台获得的title======"+title);
			Map<String, String> policeTitleMap = new HashMap<String, String>();
			policeTitleMap.put("title", PropertiesWriteUtils.native2Ascii(title));
			policeTitleMap.put("name", PropertiesWriteUtils.native2Ascii(name));
			PropertiesWriteUtils.updateProperties("title.properties",
					policeTitleMap);
			title = PropertiesReadUtils.getTitleString("title");
			name = PropertiesReadUtils.getTitleString("name");
			// PDF信息初始化
			String serverIp = request.getParameter("serverIp");
			String serverPort=request.getParameter("serverPort");
			String toolPath = request.getParameter("toolPath");
			String sourcePath = "http://"+serverIp+":"+serverPort+"/ManagePlantfrom-SSH/GR_loadInfor.action?suspectID=";
			String relatePath = request.getParameter("relatePath");
			System.out.println("====从前台获得的PDF:toolPath======" + toolPath);
			Map<String, String> pdfMap = new HashMap<String, String>();
			pdfMap.put("toolPath", toolPath);
			pdfMap.put("sourcePath", sourcePath);
			pdfMap.put("relatePath", relatePath);
			// 获取服务器根目录
			String classPath = Thread.currentThread().getContextClassLoader()
					.getResource("").getPath();
			String rootPath = "";
			if ("\\".equals(File.separator)) {
				String path = classPath.substring(1,
						classPath.indexOf("/WEB-INF/classes"));
				rootPath = path.substring(0, path.lastIndexOf("/"));
				rootPath = rootPath.replace("/", "\\");
			}
			String serverPath=rootPath;
			pdfMap.put("serverPath", serverPath);	
			PropertiesWriteUtils.updateProperties("pdf.properties", pdfMap);
			// 录播系统信息初始化
			String remoteServerIP = request.getParameter("recordIp");
			String remoteServerPort = request.getParameter("recordPort");

			System.out.println("====前台传来的录播器IP:" + remoteServerIP);
			Map<String, String> RecordDeviceMap = new HashMap<String, String>();
			RecordDeviceMap.put("remoteServerIP", remoteServerIP);
			RecordDeviceMap.put("remoteServerPort", remoteServerPort);
			RecordDeviceMap.put(
					"StartRecording",
					"http://"
							+ PropertiesReadUtils
									.getRecordConfString("remoteServerIP")
							+ ":"
							+ PropertiesReadUtils
									.getRecordConfString("remoteServerPort")
							+ "/SxStartRecording.psp");
			RecordDeviceMap.put(
					"StopRecording",
					"http://"
							+ PropertiesReadUtils
									.getRecordConfString("remoteServerIP")
							+ ":"
							+ PropertiesReadUtils
									.getRecordConfString("remoteServerPort")
							+ "/SxStopRecording.psp");
			RecordDeviceMap.put(
					"PauseRecording",
					"http://"
							+ PropertiesReadUtils
									.getRecordConfString("remoteServerIP")
							+ ":"
							+ PropertiesReadUtils
									.getRecordConfString("remoteServerPort")
							+ "/SxPauseRecording.psp");
			RecordDeviceMap.put(
					"RestartRecording",
					"http://"
							+ PropertiesReadUtils
									.getRecordConfString("remoteServerIP")
							+ ":"
							+ PropertiesReadUtils
									.getRecordConfString("remoteServerPort")
							+ "/SxRestartRecording.psp");
			RecordDeviceMap.put(
					"SwitchRecording",
					"http://"
							+ PropertiesReadUtils
									.getRecordConfString("remoteServerIP")
							+ ":"
							+ PropertiesReadUtils
									.getRecordConfString("remoteServerPort")
							+ "/SxSwitchRecordingSrc.psp");

			PropertiesWriteUtils.updateProperties("recordConf.properties",
					RecordDeviceMap);

			remoteServerIP = PropertiesReadUtils
					.getRecordConfString("remoteServerIP");
			System.out.println("=====写入了录播器remoteServerIP:" + remoteServerIP);
			/*
			 * //WebSocket信息初始化 String
			 * webSocket=(String)request.getParameter("webSocket");
			 * 
			 * Map<String, String> map3=new HashMap<String,String>();
			 * map3.put("webSocket", webSocket);
			 * PropertiesWriteUtils.updateProperties("recordConf.properties",
			 * map3);
			 */
			// 服务器信息初始化
			String ftpPort=request.getParameter("ftpPort");
			String uploadDir=request.getParameter("uploadDir");
			String userName=request.getParameter("userName");
			String passWord=request.getParameter("passWord");
			Map<String, String> ftpMap = new HashMap<String, String>();
			ftpMap.put("serverIp", serverIp);
			ftpMap.put("ftpPort", ftpPort);
			ftpMap.put("uploadDir", uploadDir);
			ftpMap.put("userName", userName);
			ftpMap.put("passWord", passWord);
			PropertiesWriteUtils.updateProperties("recordConf.properties",ftpMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/properties/load";
		}
		return "WEB-INF/jsp/home/index";
	}

}
