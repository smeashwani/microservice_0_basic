package com.training.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.product.model.ServerDetails;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
	
	@Autowired
	private MessageSource messageSource;
	
	
	
	@Value("${server.port}")
	private String port;
	
	@Value("${spring.application.name}")
	private String applicationName;
	
	@GetMapping("/")
	public ServerDetails getHome(HttpServletRequest req) {
		String msg = messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
		String ipaddress = req.getRemoteAddr().replace(".", "_");
		return ServerDetails.builder()
				.systemname(ipaddress)
				.port(port)
				.name(applicationName)
				.i18nMessage(msg)
				.build();
	}
}
