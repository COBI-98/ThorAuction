package com.goodee.finalproject.socialMember;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class SocialMemberService extends DefaultOAuth2UserService
{
	@Autowired
	public KakaoMapperIF kakaoMapperIF;

	// @Value("${kakao.Admin.key}")
	// private String adminKey;

	public int IdCheck(KakaoVO kakaoVO) throws Exception
	{
		return kakaoMapperIF.IdCheck(kakaoVO);
	}

	public int setKakaoDetail(KakaoDetailVO kakaoDetailVO) throws Exception
	{
		return kakaoMapperIF.setKakaoDetail(kakaoDetailVO);
	}

	public int setKakao1(KakaoVO object) throws Exception
	{
		log.info("===== kakao service =====");

		int rs = kakaoMapperIF.setKakao1(object);

		log.info("memberservice set kakao : {}", rs);

		return rs;
	}

	// 카카오 소셜 로그인7
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException
	{
		// log.info("===== Social Login try =====");
		// // log.info("userRequest: {}", userRequest);
		// log.info("userRequest AccessToken: {}", userRequest.getAccessToken());
		// log.info("userRequest AdditionalParameters: {}", userRequest.getAdditionalParameters());
		// log.info("userRequest Registration: {}", userRequest.getClientRegistration());
		//
		// log.info("==================================");
		String social = userRequest.getClientRegistration().getRegistrationId();
		log.info(social);

		OAuth2User oAuth2User2 = this.socialJoinCheck(userRequest);

		return oAuth2User2;
	}

	public OAuth2User socialJoinCheck(OAuth2UserRequest userRequest)
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
}
