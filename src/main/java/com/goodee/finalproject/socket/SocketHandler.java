package com.goodee.finalproject.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.goodee.finalproject.member.MemberVO;

@Component
public class SocketHandler extends TextWebSocketHandler {

//	ArrayList<WebSocketSession> sessions = new ArrayList<>();
	HashMap<String, WebSocketSession> sessions = new HashMap<>();
	
//	@Autowired
//	WebSocketChat socket;

	// client에서 메시지가 서버로 전송댈때 실행되는 함수.
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		String payload = message.getPayload();

		try {
				// 접속된 모든 세션에 메시지 전송
				for (String key : sessions.keySet()) {
					WebSocketSession ss = sessions.get(key);
					ss.sendMessage(new TextMessage(payload));
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 세션이 생성될때 시작되는 함수
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		sessions.put(session.getId(), session);
//		Map<String, Object> map = session.getAttributes();
//		MemberVO mem = (MemberVO) map.get("member");
//		String id = mem.getId();
//		
//		if(sessions.get(id) != null) {
//			sessions.replace(id, session);
//		}else {
//			sessions.put(id, session);
//		}
		
	}

	// 세션이 끝날때 실행되는 함수
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		sessions.remove(session.getId());
		super.afterConnectionClosed(session, status);

	}
	
}