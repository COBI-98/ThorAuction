package com.goodee.finalproject.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.member.MemberService;
import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.socialmember.KakaoDetailVO;
import com.goodee.finalproject.socialmember.MemberSocialService;
import com.google.gson.Gson;

@Service
@ServerEndpoint(value="/chatt")
public class WebSocketChat {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static Set<Session> clients = 
			Collections.synchronizedSet(new HashSet<Session>()); //채팅참여 session
	private static int value;
	private static Set<String> set = new HashSet<String>(); //채팅참여 name 
	private static Map<Session, String> list = new HashMap<>(); //채팅참여 session, name
	private static String winuser=""; //낙찰 유저
	private static String stop="false"; //얼리기
	private static String start="false"; //경매 시작
	private static String unit =""; //단위 가격
	private static String item =""; //경매 물품
	
	private static List<String> banlist = new ArrayList<String>(); //강퇴 list
	
	//입장시
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
		Object obj = parser.parse(msg);
		JSONObject jsonObj = (JSONObject) obj;
		System.out.println(msg);
		


		
		//경매 물품 설정
		if(msg.substring(2, 6).equals("item")) {
			item = String.valueOf(jsonObj.get("item"));
			sendMessage(msg,session);
		}
		
		//단위 경매 설정
		else if(msg.substring(2, 6).equals("unit")) {
			unit = String.valueOf(jsonObj.get("unit"));
			sendMessage(msg, session);
		}
		
		//경매 시작
		else if(msg.substring(2, 7).equals("start")) {
			start = String.valueOf(jsonObj.get("gogo"));
			sendMessage(msg, session);
		}
		
		//얼리기
		else if(msg.substring(2, 6).equals("stop")) {
			stop = String.valueOf(jsonObj.get("stop"));
			sendMessage(msg, session);
		}
		
		//단위가격 
		else if(msg.substring(2, 4).equals("id")) {
			String vv = String.valueOf(jsonObj.get("value"));
			int valu = Integer.parseInt(vv);
			setValue(valu);
			
			sendMessage(msg, session);
		}
		
		//강퇴
		else if(msg.substring(2, 5).equals("out")) {
			String outname = String.valueOf(jsonObj.get("out"));
			banlist.add(outname);
			Session ss = getKey(list,outname);
			sendOneMessage(msg, ss);
		}
		
		//경매 결과
		else if(msg.substring(2, 8).equals("amount")) {

			String amount = String.valueOf(jsonObj.get("amount")); //금액
			String winner = String.valueOf(jsonObj.get("winner")); //낙찰자
			
			String message = msg.replace("amount", "loginnum");
			
			value=Integer.parseInt(amount);
			winuser = winner;

			Session ss = getKey(list,winner);
			//DB에 저장할 예정 (금액, id, 경매 물품) 포인트 바로 빠지게 함

			

			
			
			//DB 저장
			
			
			//저장 후
			//item ="";
			sendMessage(msg,session);
			sendOneMessage(message, ss);

			
		//채팅 전송
		}else if(msg.substring(2, 5).equals("mid")) {
			
			String vv = String.valueOf(jsonObj.get("value"));
			String win = String.valueOf(jsonObj.get("win"));

			if(!(win.equals("false"))) {
				winuser = win;
			}else if(win.equals("false")) {
				msg = msg.replace(String.valueOf(jsonObj.get("win")),'"'+winuser+'"');
			}
			int valu = Integer.parseInt(vv);
			setValue(valu);
			
			sendMessage(msg, session);
			
		//입장시
		}else if(msg.substring(2, 10).equals("usercome")) {

			String name = String.valueOf(jsonObj.get("usercome")); //입장한 사람 name
			String come = String.valueOf(jsonObj.get("come"));
			
			set.add(come);
			list.put(session, come);
			
			StringBuilder sb = new StringBuilder();
			sb.append(set.toString());
			msg = msg.replace(name, sb.toString());
			msg = msg.replace(String.valueOf(jsonObj.get("ppp")), stop);
			msg = msg.replace(String.valueOf(jsonObj.get("gogo")), start);
			msg = msg.replace(String.valueOf(jsonObj.get("price")),unit);
			msg = msg.replace(String.valueOf(jsonObj.get("winner")), winuser);
			msg = msg.replace(String.valueOf(jsonObj.get("value")), String.valueOf(value));
			msg = msg.replace(String.valueOf(jsonObj.get("goods")), item);
						
			sendMessage(msg, session);
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
		
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("name",name);
		jsonObject.put("list", set);
		Gson gson = new Gson();
		String jsonString = gson.toJson(jsonObject);
		
		sendMessage(jsonString.toString(), s);
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int valu) {
		this.value = valu;
	}
	
	public String getWinuser() {
		return winuser;
	}
	public void setWinuser(String user) {
		this.winuser = user;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String str) {
		this.item = str;
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
	
	public List<String> getBanList() {
		return banlist;
	}
	
	@OnError
	public void handleError(Throwable t){
		t.printStackTrace();
	}

}
