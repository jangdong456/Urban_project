package com.gudee.urban.map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


//@RequiredArgsConstructor // 초기화 되지 않은 모든 final 필드 & @NonNull 마크가 있는 필드를 초기화하는 생성자 생성
@RestController
public class MapController {
	
	private final MapService mapService;
	
	public MapController(MapService mapService) {
		this.mapService = mapService;
	}
	
	@GetMapping("test")
	public String getList(Model model) throws Exception {
		MapVO list = mapService.getList();
		model.addAttribute("list", list);
		
		return "test";
	}
}
