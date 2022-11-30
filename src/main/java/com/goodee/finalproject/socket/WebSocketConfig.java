package com.goodee.finalproject.socket;


import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.goodee.finalproject.member.MemberVO;

@Component
public class WebSocketConfig extends Configurator {

    @Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		// TODO Auto-generated method stub
    	MemberVO mem = (MemberVO)((HttpSession) request.getHttpSession()).getAttribute("member");
		
		if(mem != null) {
			sec.getUserProperties().put("member", mem);
		}
	}

	@Bean
    ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}