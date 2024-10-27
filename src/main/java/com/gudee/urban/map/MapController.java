package com.gudee.urban.map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


//@RequiredArgsConstructor // 초기화 되지 않은 모든 final 필드 & @NonNull 마크가 있는 필드를 초기화하는 생성자 생성
@RestController
public class MapController {
	
	private final MapService mapService;
	
	public MapController(MapService mapService) {
		this.mapService = mapService;
	}
	
	@GetMapping("test")
	public ModelAndView getList(Model model) throws Exception {
		var modelAndView = new ModelAndView();
		MapVO list = mapService.getList();
		
		
		modelAndView.setViewName("test");
		modelAndView.addObject(list);
		return modelAndView;
	}
}
