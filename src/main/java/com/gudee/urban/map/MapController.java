package com.gudee.urban.map;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@RequiredArgsConstructor // 초기화 되지 않은 모든 final 필드 & @NonNull 마크가 있는 필드를 초기화하는 생성자 생성
@RestController
@Log4j2
public class MapController {
	
	private final MapService mapService;
	
//	@GetMapping("/test")
//	public String getList(Model model) throws Exception {
//		
//		MapVO list = mapService.getList();
//		model.addAttribute("list",list);
//		
////		modelAndView.setViewName("test");
////		modelAndView.addObject(list);
//		return "test";
//	}
	
	@GetMapping("/test")
	public void getJsonList() throws Exception {
		
		mapService.getJsonList();
	}
}
