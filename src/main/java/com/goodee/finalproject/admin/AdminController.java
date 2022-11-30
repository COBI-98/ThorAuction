package com.goodee.finalproject.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.member.RoleVO;
import com.goodee.finalproject.socialmember.KakaoRoleVO;
import com.goodee.finalproject.socialmember.KakaoVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/*")
public class AdminController
{
	@Autowired
	public AdminService adminService;

	@GetMapping("adminpage")
	public ModelAndView adminpage(MemberVO memberVO, KakaoVO kakaoVO, KakaoRoleVO kakaoRoleVO) throws Exception
	{
		log.info("--- get adminpage ---");
		ModelAndView modelAndView = new ModelAndView();

		List<MemberVO> memberVOs = adminService.getMemberTotal(memberVO);
		List<KakaoVO> kakaoVOs = adminService.getKakaoTotal(kakaoVO);

		log.info("adminService memberVO: {}", memberVOs);
		log.info("adminService kakaoVO: {}", kakaoVOs);

		modelAndView.addObject("member", memberVOs);
		modelAndView.addObject("kakao", kakaoVOs);
		modelAndView.setViewName("admin/adminpage");

		return modelAndView;
	}

	@PostMapping("adminpage")
	@ResponseBody
	public void adminpage(MemberVO memberVO, @RequestParam("roleName") String role) throws Exception
	{
		List<MemberVO> memberVOs = adminService.getMemberTotal(memberVO);
		log.info(role);

		log.info("array memberVOs2: {}", memberVOs);

		for (MemberVO memberVO2 : memberVOs)
		{
			log.info("for in memberVO: {}", memberVO2);

			if (role.equals("ROLE_MANGER"))
			{
				log.info("manager");
				log.info("update maneger: {}", adminService.roleSetManager(memberVO2));
				adminService.roleSetManager(memberVO2);

				return;
			}
			else if (role.equals("ROLE_USER"))
			{
				log.info("user");
				log.info("update USER: {}", adminService.roleSetUser(memberVO2));
				adminService.roleSetUser(memberVO2);

				return;
			}
			else if (role.equals("ROLE_BAN"))
			{
				log.info("ban");
				log.info("update BAN: {}", adminService.roleSetBan(memberVO2));
				adminService.roleSetBan(memberVO2);

				return;
			}
			else
			{
				log.info("error");
				return;
			}
		} // for end
	}
}
