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
		if(str.equals("일반 경매 신청하는 법") || str.equals("실시간 경매 신청하는 법") || str.equals("경매 신청하는 법"))
		{
			content = 
					"<div class='other'>"
						+"<div class='chat-text'>"
						+ "<img src=\"/images/coin.png\" style=\"width: 30px; margin-top: 5px;\">"
							+"<b class='text'>경매 신청</b>"
							+"<p class='text'>신청 게시판에서 경매를 진행하고 싶은 물건에 대해 신청글을 적어주세요!</p>"
							+"<a href ='/board/application/list' class='text'>경매 신청하러 가기</a>"
						+"</div>"
					+"</div>"
					+ "<hr>";
			
		}else if(str.equals("일반 경매 주의사항")) {
			content = "<div class='other'>"
                    	+ "<div class='chat-text'>"
                    	+ "<img src=\"/images/coin.png\" style=\"width: 30px; margin-top: 5px;\">"
                    		+ "<b class='text'>일반 경매 시 주의사항</b>"
                    		+ "<p class='text'>1. 한번 입찰 시, 판매자 상품에 문제가 생기지 않는 이상 유찰시킬 수 없습니다.</p>"
                    		+ "<p class='text'>2. 상품의 하자는 판매자 책임입니다.</p>"
                    	+"</div>"
                    +"</div>"
                    + "<hr>";
			
		}else if(str.equals("일반 경매 하러가기")) {
			content =
					 "<div class='other'>"
						+"<div class='chat-text'>"
						+ "<img src=\"/images/coin.png\" style=\"width: 30px; margin-top: 5px;\">"
							+"<a href ='/product/list' class='text'>일반 경매하러 가기</a>"
						+"</div>"
					 +"</div>"
					 + "<hr>";
			
		}else if(str.equals("실시간 경매 주의사항")) {
			content = 
					"<div class='other'>"
						+"<div class='chat-text'>"
						+ "<img src=\"/images/coin.png\" style=\"width: 30px; margin-top: 5px;\">"
							+"<b class='text'>실시간 경매 시 주의사항</b>"
							+"<p class='text'>1. 경매 시작 전 입찰 금액 입력 시 반영이 되지 않습니다.</p>"
							+"<p class='text'>2. 경매 진행 중 욕설이나 타인을 비난하는 행위, 도배를 하는 행위는 강퇴를 당하실 수 있습니다.</p>"
							+"<p class='text'>3. 강퇴를 당하면 실시간 경매에 참여할 수 없습니다.</p>"
							+"<p class='text'>4. 입찰 금액 입력 시 자신의 포인트보다 높게 입력할 수 없습니다.</p>"
						+"</div>"
					+"</div>"
					+ "<hr>";
			
		}else if(str.equals("실시간 경매 하러가기")) {
			content =
					 "<div class='other'>"
						+"<div class='chat-text'>"
						+ "<img src=\"/images/coin.png\" style=\"width: 30px; margin-top: 5px;\">"
							+"<a href ='/liveAuction' class='text'>실시간 경매하러 가기</a>"
						+"</div>"
					 +"</div>"
					 + "<hr>";
			
		}else if(str.equals("경매 승인시 배송방법") || str.equals("배송 주소")) {
			content = 
					"<div class='other'>"
						+"<div class='chat-text'>"
						+"<img src=\"/images/coin.png\" style=\"width: 30px; margin-top: 5px;\">"
						+ "<b class='text'>배송 주소</b>"
							+"<p class='text'>배송지 주소는  서울특별시 금천구 가산디지털1로 151</p>"
							+"<p class='text'>(가산동, 세진 이노플렉스1차) 306호 구디아카데미 입니다.</p>"
						+"</div>"
					+"</div>"
					+ "<hr>";
			
		}else if(str.equals("일반 경매 진행 시스템")) {
			content = 
						"<div class='other'>"
							+ "<div class='chat-text'>"
							+ "<img src=\"/images/coin.png\" style=\"width: 30px; margin-top: 5px;\">"
								+ "<b class='text'>일반 경매 진행 시스템</b>"
								+ "<p class='text'>1. 신청 게시판에서 신청 게시글을 올립니다.</p>"
								+ "<p class='text'>2. 관리자가 게시글 승인 시 경매장에서 경매가 시작됩니다.</p>"
								+ "<p class='text'>3. 버튼을 통해 입찰할 수 있습니다.</p>"
								+ "<p class='text'>4. 종료시간 전에 가장 높은 금액을 입찰한 분이 낙찰하게 됩니다.</p>"
							+"</div>"
						+"</div>"
						+ "<hr>";
			
		}else if(str.equals("실시간 경매 진행 시스템")) {
			content = 
						"<div class='other'>"
							+"<div class='chat-text'>"
							+ "<img src=\"/images/coin.png\" style=\"width: 30px; margin-top: 5px;\">"
								+"<b class='text'>실시간 경매 진행 시스템</b>"
								+"<p class='text'>1. 신청 게시판에서 신청 게시글을 올립니다.</p>"
								+"<p class='text'>2. 관리자가 게시글 승인 시 경매장에서 경매가 시작됩니다.</p>"
								+"<p class='text'>3. 직접 금액을 입력하거나 단위가격을 통해 입찰할 수 있습니다.</p>"
								+"<p class='text'>4. 관리자가 종료하기 전에 가장 높은 금액을 입찰한 분이 낙찰하게 됩니다.</p>"
							+"</div>"
						+"</div>"
						+ "<hr>";
			
		}else if(str.equals("경매 승인 기간")) {
			content = 
					"<div class='other'>"
						+"<div class='chat-text'>"
						+ "<img src=\"/images/coin.png\" style=\"width: 30px; margin-top: 5px;\">"
							+"<span class='text'>경매 신청 승인은 관리자가 수시로 보겠지만 대략 3-5일 정도 걸리는 점 양해부탁드립니다!</span>"
						+"</div>"
                   +"</div>"
                   + "<hr>";
					
		}else if(str.equals("배송 기간")) {
			content = 
					"<div class='other'>"
						+"<div class='chat-text'>"
						+ "<img src=\"/images/coin.png\" style=\"width: 30px; margin-top: 5px;\">"
							+"<span class='text'>낙찰 뒤 배송 기간은 대략 1주일 정도 걸리는 점 양해부탁드립니다!</span>"
						+"</div>"
                    +"</div>"
                    + "<hr>";
			
		}else if(str.equals("배송 주소 잘못적으셨다면?")) {
			content =
					"<div class='other'>"
						+"<div class='chat-text'>"
						+ "<img src=\"/images/coin.png\" style=\"width: 30px; margin-top: 5px;\">"
							+"<span class='text'>배송 주소를 잘못 적으셨다면 마이페이지에서 수정해주시면 됩니다!</span>"
						+"</div>"
                   +"</div>"
                   + "<hr>";
			
		}else if(str.equals("포인트 충전")) {
			content = 
					"<div class='other'>"
						+"<div class='chat-text'>"
						+ "<img src=\"/images/coin.png\" style=\"width: 30px; margin-top: 5px;\">"
							+"<span class='text'>비드코인에서는 포인트를 충전해서 사용하실 수 있습니다.!</span>"
							+"<a href ='/liveAuction' class='text'>포인트 충전하러 가기</a> <br/>"
						+"</div>"
                   +"</div>"
                   + "<hr>";
			
		}else if(str.equals("포인트 사용 방법")) {
			content = 
					"<div class='other'>"
						+"<div class='chat-text'>"
						+ "<img src=\"/images/coin.png\" style=\"width: 30px; margin-top: 5px;\">"
						+"<span class='text'>일반 경매나 실시간 경매에서 포인트로 입찰하여 경매를 즐기실 수 있습니다.</span>"
                       +"</div>"
                   +"</div>"
                   + "<hr>";
		}

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
		}else if(str.equals("배송")) {
			content1 = "배송 기간";
			content2 = "배송 주소 잘못적으셨다면?";
			content3 = "배송 주소";
		}
		
		map.put("content1", content1);
		map.put("content2", content2);
		map.put("content3", content3);
		
		return map;
		
	}
	
	@RequestMapping(value = "/bid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> bid(@RequestParam String str)
	{
		ModelAndView mv = new ModelAndView();
		Map<String,String> map = new HashMap<>();
		String content1 ="";
		String content2 = "";
		String content3 = "";
		
		if(str.equals("경매")) {
			content1 = "일반 경매";
			content2 = "실시간 경매";
			content3 = "경매 신청하는 법";
		}
		
		map.put("content1", content1);
		map.put("content2", content2);
		map.put("content3", content3);
		
		return map;
		
	}
}
