package com.goodee.finalproject.socialmember;

import java.security.Principal;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class MemberSocialService extends DefaultOAuth2UserService
{
	@Autowired
	public KakaoMapperIF kakaoMapperIF;

	// @Value("${kakao.Admin.key}")
	// private String adminKey;
	
	public KakaoDetailVO getKakaoDetail(KakaoVO kakaoVO) throws Exception{
		return kakaoMapperIF.getKakaoDetail(kakaoVO);
	}

	public int IdCheck(KakaoVO kakaoVO) throws Exception
	{
		return kakaoMapperIF.IdCheck(kakaoVO);
	}

	public int setKakaoDetail(KakaoDetailVO kakaoDetailVO) throws Exception
	{

		return kakaoMapperIF.setKakaoDetail(kakaoDetailVO);
	}

	public int setKakao1(KakaoVO kakaoVO) throws Exception
	{
		int rs = kakaoMapperIF.setKakao1(kakaoVO);
		log.info("------> service rs: {}", rs);
		if (rs == 1)
		{
			kakaoMapperIF.setKaRole(kakaoVO);
		}

		return rs;
	}

	// 카카오 소셜 로그인
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException
	{
		log.info("===== Social Login try =====");
		// // log.info("userRequest: {}", userRequest);
		// log.info("userRequest AccessToken: {}", userRequest.getAccessToken());
		// log.info("userRequest AdditionalParameters: {}", userRequest.getAdditionalParameters());
		// log.info("userRequest Registration: {}", userRequest.getClientRegistration());
		//
		// log.info("==================================");
		String social = userRequest.getClientRegistration().getRegistrationId();
		log.info(social);

		log.info("social kakao: {}", (social == "kakao"));
		log.info("social naver: {}", (social == "naver"));

		OAuth2User oAuth2User2 = null;

		if ((social.equals("kakao")))
		{
			oAuth2User2 = this.kakaoJoinCheck(userRequest);
		}
		else if ((social.equals("naver")))
		{
			oAuth2User2 = this.naverJoinCheck(userRequest);
		}

		log.info("oAuth Naver: {}", oAuth2User2);

		return oAuth2User2;
	}

	public OAuth2User kakaoJoinCheck(OAuth2UserRequest userRequest)
	{
		// 회원가입 유무
		OAuth2User oAuth2User = super.loadUser(userRequest);

		// log.info("===== user infomation =====");
		// log.info("oauth2user-Name: {}", oAuth2User.getName());
		// log.info("oauth2user-Attr: {}", oAuth2User.getAttributes());
		// log.info("oauth2user-Auth: {}", oAuth2User.getAuthorities());
		//
		// log.info("===================================");
		Map<String, Object> map = oAuth2User.getAttributes();

		Iterator<String> keys = map.keySet().iterator(); // key 꺼내기
		while (keys.hasNext()) // key가 있으면 true
		{
			String key = keys.next();
			// log.info("Key: {}", key);
			// map.get(key);
		}
		// log.info("oAuth-pro-ClassName: {}", oAuth2User.getAttribute("properties").getClass());
		LinkedHashMap<String, Object> lm = oAuth2User.getAttribute("properties");
		LinkedHashMap<String, Object> ka = oAuth2User.getAttribute("kakao_account");

		KakaoVO kakaoVO = new KakaoVO();
		kakaoVO.setKaNickName(oAuth2User.getName()); // id
		// memberVO.setPwd(null); // pwd가 없으므로, 비우거나 랜덤한 값으로 생성
		kakaoVO.setKaName((String) lm.get("nickname"));
		kakaoVO.setKaEmail(ka.get("email").toString());

		kakaoVO.setSocial(userRequest.getClientRegistration().getRegistrationId()); // 소셜 이름 가져옴 : kakao
		kakaoVO.setAttributes(oAuth2User.getAttributes());

		return kakaoVO;
	}

	public OAuth2User naverJoinCheck(OAuth2UserRequest userRequest)
	{
		// 회원가입 유무
		OAuth2User oAuth2User = super.loadUser(userRequest);

		// log.info("===== user infomation =====");
		// log.info("oauth2user-Name: {}", oAuth2User.getName());
		// log.info("oauth2user-Attr: {}", oAuth2User.getAttributes());
		// log.info("oauth2user-Auth: {}", oAuth2User.getAuthorities());
		//
		// log.info("===================================");
		Map<String, Object> map = oAuth2User.getAttributes();

		Iterator<String> keys = map.keySet().iterator(); // key 꺼내기
		while (keys.hasNext()) // key가 있으면 true
		{
			String key = keys.next();
			// log.info("Key: {}", key);
			// map.get(key);
		}
		// log.info("oAuth-pro-ClassName: {}", oAuth2User.getAttribute("properties").getClass());
		LinkedHashMap<String, Object> lm = oAuth2User.getAttribute("properties");
		log.info("naver properties: {}", lm);
		LinkedHashMap<String, Object> na = oAuth2User.getAttribute("profile");
		log.info("naver account: {}", na);

		NaverVO naverVO = new NaverVO();
		naverVO.setNaNickName((String) lm.get("name"));
		// naverVO.setNaEmail(na.get("email").toString());
		naverVO.setNaSocial(userRequest.getClientRegistration().getRegistrationId());
		naverVO.setAttriutes(oAuth2User.getAttributes());

		return naverVO;
	}
}
