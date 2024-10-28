package com.gudee.urban.map;

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


//@RequiredArgsConstructor // 초기화 되지 않은 모든 final 필드 & @NonNull 마크가 있는 필드를 초기화하는 생성자 생성
@RestController
@Log4j2
public class MapController {
	
	private final MapService mapService;
	
	public MapController(MapService mapService) {
		this.mapService = mapService;
	}
	
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
	public void getBeerObject() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
	    // 부동산 실거래 정보 url
	    String url = "https://data.gm.go.kr/openapi/Apttradedelngdetail?KEY=fd588635f78f47f0b90354625482c963&Type=json&pIndex=1&pSize=1";
	    
	    Object responseBody = restTemplate.getForObject(url, String.class);
	    log.info(responseBody.toString());
	    
	    
	    
	    
	    ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode rootNode = objectMapper.readTree(responseBody.toString());
//        JsonNode aptTradeDetail = rootNode.path("Apttradedelngdetail");
//        
//        for (JsonNode detail : aptTradeDetail) {
//            JsonNode rows = detail.path("row");
//            for (JsonNode row : rows) {
//                String legalDongNm = row.path("LEGALDONG_NM").asText();
//                System.out.println("LEGALDONG_NM: " + legalDongNm);
//                System.out.println("@@");
//            }
//        }
	    
	    Map<String, Object> map = objectMapper.readValue(responseBody.toString(), Map.class);
	    
	    
	 
	    
	   
	}
}
