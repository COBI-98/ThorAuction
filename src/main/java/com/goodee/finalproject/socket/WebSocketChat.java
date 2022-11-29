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
			Collections.synchronizedSet(new HashSet<Session>()); //채팅참여 session
	private static int value;
	private static String username;
	private static Set<String> set = new HashSet<String>(); //채팅참여 name 
	private static Map<Session, String> list = new HashMap<>(); //채팅참여 session, name
	
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
		Object obj = parser.parse(msg);
		JSONObject jsonObj = (JSONObject) obj;
		
		if(msg.substring(2, 5).equals("out")) {
			String outname = String.valueOf(jsonObj.get("out"));
			Session ss = getKey(list,outname);
			System.out.println(ss);
			sendOneMessage(msg, ss);
		}
		
		//경매 결과
		else if(msg.substring(2, 8).equals("amount")) {

			String amount = String.valueOf(jsonObj.get("amount")); //금액
			String winner = String.valueOf(jsonObj.get("winner")); //낙찰자
			//DB에 저장할 예정

		}else if(msg.substring(2, 5).equals("mid")) {
			
			String vv = String.valueOf(jsonObj.get("value"));
			System.out.println(vv);
			int valu = Integer.parseInt(vv);
			setValue(valu);
			
			sendMessage(msg, session);
			
		}else if(msg.substring(2, 10).equals("usercome")) {

			String name = String.valueOf(jsonObj.get("usercome")); //입장한 사람 name
			set.add(name);
			list.put(session, name);
			Gson gson = new Gson();
			String jsonString = gson.toJson(set);
			
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
	
	//한명한테만 메세지 전송
	public void sendOneMessage(String msg, Session session) throws IOException{
		System.out.println("receive message : " + msg);
			System.out.println("send data : " + msg);
			session.getBasicRemote().sendText(msg);
		
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
	
	public static <K, V> K getKey(Map<K, V> map, V value) {
	       // 찾을 hashmap 과 주어진 단서 value
	       for (K key : map.keySet()) {
	           if (value.equals(map.get(key))) {
	               return key;
	           }
	       }
	       return null;
	}

}
