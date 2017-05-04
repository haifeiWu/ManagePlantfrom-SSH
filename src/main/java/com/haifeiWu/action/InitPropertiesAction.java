package com.haifeiWu.action;

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
public class InitPropertiesAction{
	
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request){
		return "WEB-INF/jsp/home/initConfigs";
	}
	
	
	@RequestMapping("/init")
	public String init(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			//派出所系统信息初始化的Map
			String title= request.getParameter("title");
			String name= request.getParameter("name");
			System.out.println("====从前台获得的title======"+title);
			Map<String, String> policeTitleMap=new HashMap<String,String>();
			policeTitleMap.put("title", title);
			policeTitleMap.put("name", name);
			PropertiesWriteUtils.updateProperties("title.properties", policeTitleMap);

			title=new String(PropertiesReadUtils.getTitleString(
					"title").getBytes("ISO-8859-1"),"utf-8");
			name=new String(PropertiesReadUtils.getTitleString(
					"name").getBytes("ISO-8859-1"),"utf-8");
	    	System.out.println("=====写入了title:"+title);
	    	System.out.println("=====写入了name:"+name);
			//PDF信息初始化
			String toolPath=request.getParameter("toolPath");
			String sourcePath=request.getParameter("sourcePath");
			String serverPath=request.getParameter("serverPath");
			System.out.println("====从前台获得的PDF:toolPath======"+toolPath);
			Map<String, String> pdfMap=new HashMap<String,String>();
			pdfMap.put("toolPath", toolPath);
			pdfMap.put("sourcePath", sourcePath);
			pdfMap.put("serverPath", serverPath);
			PropertiesWriteUtils.updateProperties("pdf.properties", pdfMap);
			
			toolPath=new String(PropertiesReadUtils.getPDFString("toolPath").getBytes("ISO-8859-1"),"utf-8");
			System.out.println("=====写入了toolPath:"+toolPath);
			//录播系统信息初始化
			String remoteServerIP=request.getParameter("recordIp");
			String remoteServerPort=request.getParameter("recordPort");
			
			System.out.println("====前台传来的录播器IP:"+remoteServerIP);
			Map<String, String> RecordDeviceMap=new HashMap<String,String>();
			RecordDeviceMap.put("remoteServerIP", remoteServerIP);
			RecordDeviceMap.put("remoteServerPort", remoteServerPort);
			RecordDeviceMap.put("StartRecording","http://"+PropertiesReadUtils.getRecordConfString("remoteServerIP")+":"+
	    			PropertiesReadUtils.getRecordConfString("remoteServerPort")+"/SxStartRecording.psp");
			RecordDeviceMap.put("StopRecording","http://"+PropertiesReadUtils.getRecordConfString("remoteServerIP")+":"+
	    			PropertiesReadUtils.getRecordConfString("remoteServerPort")+"/SxStopRecording.psp");
			RecordDeviceMap.put("PauseRecording","http://"+PropertiesReadUtils.getRecordConfString("remoteServerIP")+":"+
	    			PropertiesReadUtils.getRecordConfString("remoteServerPort")+"/SxPauseRecording.psp");
			RecordDeviceMap.put("RestartRecording","http://"+PropertiesReadUtils.getRecordConfString("remoteServerIP")+":"+
	    			PropertiesReadUtils.getRecordConfString("remoteServerPort")+"/SxRestartRecording.psp");
			RecordDeviceMap.put("SwitchRecording","http://"+PropertiesReadUtils.getRecordConfString("remoteServerIP")+":"+
	    			PropertiesReadUtils.getRecordConfString("remoteServerPort")+"/SxSwitchRecordingSrc.psp");
			
			PropertiesWriteUtils.updateProperties("recordConf.properties", RecordDeviceMap);
			
			remoteServerIP=new String(PropertiesReadUtils.getRecordConfString("remoteServerIP").getBytes("ISO-8859-1"),"utf-8");
			System.out.println("=====写入了录播器remoteServerIP:"+remoteServerIP);
			/*//WebSocket信息初始化
			String webSocket=(String)request.getParameter("webSocket");
			
			Map<String, String> map3=new HashMap<String,String>();
			map3.put("webSocket", webSocket);
			PropertiesWriteUtils.updateProperties("recordConf.properties", map3);
			*/
			//服务器信息初始化
			String serverPort=request.getParameter("serverPort");
			String url=request.getParameter("url");
			
			Map<String, String> serverMap=new HashMap<String,String>();
			serverMap.put("serverPort", serverPort);
			serverMap.put("url", url);
			PropertiesWriteUtils.updateProperties("recordConf.properties", serverMap);
			
		}catch(Exception e){
			e.printStackTrace();
			/*response.getWriter().write("<script type='text/javascript'> alert('初始化失败'); </script>");*/
			return "redirect:/properties/load";
		}
		return "WEB-INF/jsp/home/index";
	}
	
}
