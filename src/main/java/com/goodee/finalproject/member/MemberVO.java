package com.goodee.finalproject.member;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class MemberVO {
	
	@NotBlank(message = "필수 항목입니다")
	@Pattern(regexp = "[a-z0-9]{3,15}$", message = "3~15자의 영문 소문자, 숫자만 사용해 주세요")
	private String id;
	
	@NotBlank(message = "필수 항목입니다")
	@Pattern(regexp = "[a-zA-Z0-9]{8,16}$", message = "8~16자 영문 대ㆍ소문자, 숫자만 사용해 주세요")
	private String pw;
	
	@NotBlank(message = "필수 항목입니다")
	@Pattern(regexp = "[가-힣]{2,5}$", message = "이름을 다시 확인해 주세요")
	private String name;
	
	@NotBlank(message = "필수 항목입니다")
	@Pattern(regexp = "[0-9]{6}$", message = "생년월일 6자를 정확하게 입력해 주세요 ex)000101")
	private String birth;
	
	@NotBlank(message = "필수 항목입니다")
	@Pattern(regexp = "^([0-9a-zA-Z_\\.-]+)@([0-9a-zA-Z_-]+)(\\.[0-9a-zA-Z_-]+){1,2}$", message = "이메일 주소를 다시 확인해 주세요")
	private String email;
	private String post;
	private String addr;
	private String addr2;	
	
	@NotBlank(message = "필수 항목입니다")
	@Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "전화번호를 정확히 입력해 주세요")
	private String phone;
	private Long point;
	
	private List<RoleVO> roleVOs;
}
