package com.example.ocr.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeCtrl {
	
	@RequestMapping("/")
	public String home(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
}
