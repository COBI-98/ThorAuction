package com.goodee.finalproject.chatbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class BotController {
	
	
	
	@RequestMapping(value = "/chatbot", method = RequestMethod.GET)
	public ModelAndView keyboard()
	{
		KeyboardVO keyboard=new KeyboardVO(new String[] {"사진", "라벨", "에코메세지"});
		List<String> ar = new ArrayList<>();
		ar.add("일반 경매");
		ar.add("실시간 경매");
		ar.add("경매 진행 시스템");
		ModelAndView mv = new ModelAndView();
		mv.addObject("ar", ar);
		mv.setViewName("/liveAuction/chatbot");
		return mv;
	}
	
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> message(@RequestParam String str)
	{
		ModelAndView mv = new ModelAndView();
		Map<String,String> map = new HashMap<>();
		String content ="";
		String url ="";
		System.out.println("gggg");
		if(str.equals("일반 경매 신청하는 법"))
		{
			url = "/board/application/list";
			content = "신청 게시판에서 신청먼저 하세욤";
		}else if(str.equals("일반 경매 주의사항")) {
			content = "주의사항 입니다";
		}else if(str.equals("일반 경매 하러가기")) {
			content = "/liveAuction";
		}else if(str.equals("실시간 경매 신청하는 법")) {
			url = "/board/application/list";
			content = "신청 게시판에서 신청먼저 하세욤";
		}else if(str.equals("실시간 경매 주의사항")) {
			content = "주의사항 입니다.";
		}else if(str.equals("실시간 경매 하러가기")) {
			content = "/liveAuction";
		}else if(str.equals("경매 승인시 배송방법")) {
			content = "배송지 주소는 구디아카데미 입니다.";
		}else if(str.equals("일반 경매 진행 시스템")) {
			content = "일반 시스템";
		}else if(str.equals("실시간 경매 진행 시스템")) {
			content = "실시간 시스템";
		}

		map.put("url", url);
		map.put("content", content);

		return map;
		
	}
	
	@RequestMapping(value = "/click", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> click(@RequestParam String str)
	{
		ModelAndView mv = new ModelAndView();
		Map<String,String> map = new HashMap<>();
		String content1 ="";
		String content2 = "";
		String content3 = "";
		if(str.equals("일반 경매")) {
		
			content1 = "일반 경매 신청하는 법";
			content2 = "일반 경매 주의사항";
			content3 = "일반 경매 하러가기";
			
		}else if(str.equals("실시간 경매")) {
			
			content1 = "실시간 경매 신청하는 법";
			content2 = "실시간 경매 주의사항";
			content3 = "실시간 경매 하러가기";
			
		}else if(str.equals("경매 진행 시스템")) {
			
			content1 = "경매 승인시 배송방법";
			content2 = "일반 경매 진행 시스템";
			content3 = "실시간 경매 진행 시스템";
		}
		
		map.put("content1", content1);
		map.put("content2", content2);
		map.put("content3", content3);
		
		return map;
		
	}

}
