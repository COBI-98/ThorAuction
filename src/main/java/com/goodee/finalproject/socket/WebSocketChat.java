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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketSession;

import com.goodee.finalproject.member.MemberVO;
import com.google.gson.Gson;



@Service
@ServerEndpoint(value="/chatt")
public class WebSocketChat {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static Set<Session> clients = 
			Collections.synchronizedSet(new HashSet<Session>());
	private static int value;
	private static String username;
	private static Set<String> set = new HashSet<String>();
	private static Map<Session, String> list = new HashMap<>(); 
	
	@OnOpen
	public void onOpen(Session s,EndpointConfig config) throws Exception {

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

		JSONParser parser = new JSONParser();
		String count = String.valueOf(clients.size());
		
		//경매 결과
		if(msg.substring(2, 8).equals("amount")) {
			Object obj = parser.parse(msg);
			JSONObject jsonObj = (JSONObject) obj;
			String amount = String.valueOf(jsonObj.get("amount")); //금액
			String winner = String.valueOf(jsonObj.get("winner")); //낙찰자
			//DB에 저장할 예정

		}else if(msg.substring(2, 5).equals("mid")) {
			msg = msg.replace("tnuoc", count);
			//msg에 있는 value값 가져와서 value 에 저장
			int index = msg.lastIndexOf("value");
			System.out.println(index);
			String vv = msg.substring(index+7, msg.length()-1);
			System.out.println(vv);
			int valu = Integer.parseInt(vv);
			setValue(valu);
			
			sendMessage(msg, session);
			
		}else if(msg.substring(2, 10).equals("usercome")) {
			Object obj = parser.parse(msg);
			JSONObject jsonObj = (JSONObject) obj;
			String name = String.valueOf(jsonObj.get("usercome")); //입장한 사람 name
			set.add(name);
			list.put(session, name);
			Gson gson = new Gson();
			String jsonString = gson.toJson(set);
			
			System.out.println(jsonString);
			sendMessage(jsonString, session);
		}
		
		else {
			sendMessage(msg, session);

		}	
	}
	
	//메세지 전송
	public void sendMessage(String msg, Session session) throws IOException {
		System.out.println("receive message : " + msg);
		for(Session s : clients) {
			System.out.println("send data : " + msg);
			s.getBasicRemote().sendText(msg);
		}
	}
	
	@OnClose
	public void onClose(Session s) throws IOException {

		System.out.println("session close : " + s);
		clients.remove(s);
		System.out.println(clients.size());
		
		String name = list.get(s);
		set.remove(name);
		Gson gson = new Gson();
		String jsonString = gson.toJson(set);
		
		System.out.println(jsonString);
		sendMessage(jsonString, s);
		
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
	
	
	public String getname() {
		return username;
	}
	public void setname(String name) {
		this.username = name;
	}

}
