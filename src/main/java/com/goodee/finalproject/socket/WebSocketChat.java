package com.goodee.finalproject.socket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
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



@Service
@ServerEndpoint(value="/chatt")
public class WebSocketChat {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static Set<Session> clients = 
			Collections.synchronizedSet(new HashSet<Session>());

	
	@OnOpen
	public void onOpen(Session s) throws Exception {
		ModelAndView mv = new ModelAndView();
		System.out.println("open session : " + s.toString());
		if(!clients.contains(s)) {
			clients.add(s);
			System.out.println("session open : " + s);
		}else {
			System.out.println("이미 연결된 session 임!!!");
		}System.out.println(clients.size());
	}
	
	
	@OnMessage
	public void onMessage(String msg, Session session) throws Exception{
		System.out.println(msg.toString());
		String count = String.valueOf(clients.size());
		msg = msg.replace("tnuoc", count);
		
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
