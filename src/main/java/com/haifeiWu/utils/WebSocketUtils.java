package com.haifeiWu.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


/**
 * websocket的服务器端
 * @author WXY
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 客户端可以通过这个URL来连接到WebSocket服务器端
 */

@ServerEndpoint("/websocket")
public class WebSocketUtils {
	
	/**
	 * 建立连接之后，如果RFID_ReadAction获取成功，则将信息传递到这里，进行处理之后，传递给客户端
	 * 
	 * 嫌疑人表中有当前手环信息，流程和房间号，房间表中有IP
	 * webSocket应在连接上的时候查出房间号，用房间号标识每个连接
	 */

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    //private static CopyOnWriteArraySet<WebSocketUtils> webSocketSet = new CopyOnWriteArraySet<WebSocketUtils>();
	private static Map<String, WebSocketUtils> map=new HashMap<String, WebSocketUtils>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //private HttpSession httpSession;
    /**
	 * 树莓派传递过来的手环ID和读卡器 
	 * 该方法会调用WebSocket的onmessage方法，将对应页面传递到客户端，客户端发出相应的请求
	 */
	public void flushPage(String bandID,String cardReader_ID){
		
	}
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        
        
        
        
        
        
//        this.httpSession = (HttpSession) config.getUserProperties()
//                .get(HttpSession.class.getName());
//        System.out.println("websocket获取的ip:  "+httpSession.getAttribute("ip"));
        
        //config.getUserProperties();
        
        //httpSession.
//        System.out.println("URI:"+this.session.getRequestURI());
//        
//        System.out.println("异步远程"+this.session.getAsyncRemote());
        
//        Map<String,List<String>> map=this.session.getRequestParameterMap();
//        Iterator iter=map.keySet().iterator();
//        while(iter.hasNext()){
//        	System.out.println(iter.next());
//        }
        
//        System.out.println("OpenSessions:"+this.session.getOpenSessions());
//        	Set<Session> set=this.session.getOpenSessions();
//        	Iterator iter=set.iterator();
//        	while(iter.hasNext()){
//        		System.out.println("opensession:   "+iter.next());
//        	}
//        System.out.println("PathParameters:"+this.session.getPathParameters());
//        
//        Map<String, String> map=this.session.getPathParameters();
//    	Iterator mapiter=set.iterator();
//    	while(mapiter.hasNext()){
//    		System.out.println("PathParameters:   "+mapiter.next());
//    	}
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
    	//根据当前对象去获取key，然后删除
        //map.remove(this);  //从set中删除     //在线数减1
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //向客户端群发消息
        
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

}
