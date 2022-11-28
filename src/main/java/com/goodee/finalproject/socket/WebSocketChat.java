package com.goodee.finalproject.socket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketSession;

import com.goodee.finalproject.member.MemberVO;



@Service
@ServerEndpoint(value="/chatt")
public class WebSocketChat {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static Set<Session> clients = 
			Collections.synchronizedSet(new HashSet<Session>());
	private static int value;
	//private static final Map<Session, WebSocketSession> sessionMap = new HashMap<Session, WebSocketSession>();
	
	//ChatController chatController = new ChatController();

	
	@OnOpen
	public void onOpen(Session s,EndpointConfig config) throws Exception {
		//HttpSession httpSession = (HttpSession) config.getUserProperties().get("member");
		//MemberVO mem = (MemberVO)request.getSession().getAttribute("member");
		ModelAndView mv = new ModelAndView();
		System.out.println("open session : " + s.toString());
		if(!clients.contains(s)) {
			clients.add(s);
			System.out.println("session open : " + s);
		}else {
			System.out.println("이미 연결된 session 임!!!");
		}System.out.println(clients.size());
//		logger.info("WebSocket Session created : " + s.getId());
//		logger.info("Http Session is : " + httpSession.getId());
		System.out.println(clients);
//		
//		sessionMap.put(s, httpSession);
	}
	
	
	@OnMessage
	public void onMessage(String msg, Session session) throws Exception{
		//HttpSession mem = sessionMap.get(session);
		//MemberVO mem = (MemberVO)request.getSession().getAttribute("member");
		
		System.out.println(msg.toString());
		String count = String.valueOf(clients.size());
		msg = msg.replace("tnuoc", count);
		//msg에 있는 value값 가져와서 value 에 저장
		int index = msg.lastIndexOf("value");
		System.out.println(index);
		String vv = msg.substring(index+7, msg.length()-1);
		System.out.println(vv);
		int valu = Integer.parseInt(vv);
		setValue(valu);
//		String value = String.valueOf(ChatController.getValue());
//		msg = msg.replace("eulav", value);
		
//		JSONParser jsonParser = new JSONParser();
//		JSONObject jsonObject = (JSONObject) jsonParser.parse(msg);
//		System.out.println(jsonObject.get("count"));
		
		System.out.println("receive message : " + msg);
		for(Session s : clients) {
			System.out.println("send data : " + msg);
			s.getBasicRemote().sendText(msg);
		}
	}
	
	@OnClose
	public void onClose(Session s) {
		//HttpSession httpSession = sessionMap.get(s);
		//MemberVO mem = (MemberVO)request.getSession().getAttribute("member");

		System.out.println("session close : " + s);
		clients.remove(s);
		System.out.println(clients.size());
	}
	
	//채팅방 인원수
	public void userCount(Session s) throws IOException, EncodeException {
		s.getBasicRemote().sendObject(clients.size());
		
	}
	
	public int getCount() {
		return clients.size();
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int valu) {
		this.value = valu;
	}
//	public Session getSession() {
//		return clients;
//	}
	
//	//메시지 전체 전송
//	public void broadCast(String text) throws IOException {
//		logger.info("전달 대상: " + clients.size());
//		Set<Session> keys = clients;
//		for(Session s : clients) {
//			s.getBasicRemote().sendText(text);
//
//		}
//		
//	}
}
