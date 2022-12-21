package com.goodee.finalproject.error;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController
{
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request)
	{
		log.info("===== get error page =====");

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null)
		{
			int statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.FORBIDDEN.value())
			{
				return "error/error-403";
			}
			if (statusCode == HttpStatus.NOT_FOUND.value())
			{
				return "error/error-404";
			}
			if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value())
			{
				return "error/error-405";
			}
			if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
			{
				return "error/error-500";
			}
			else
			{
				return "error/error";
			}
		}

		return "error/error";
	}
}
