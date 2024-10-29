package com.gudee.urban.map;


import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.resource.HttpResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gudee.urban.dao.MapMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service
@Log4j2
public class MapService {
	
	private final MapMapper mapMapper;
	
	
	public MapVO getList() throws Exception {
		return mapMapper.getList();
	}
	
	public void getJsonList() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
	    // 부동산 실거래 정보 url
	    String url = "https://data.gm.go.kr/openapi/Apttradedelngdetail?KEY=fd588635f78f47f0b90354625482c963&Type=json&pIndex=1&pSize=1";
	    
	    String responseBody = restTemplate.getForObject(url, String.class);

	    // 방법1 : jsondate-> {"a"["b"{"c":"asd"},{"d":123}]} 형식
	    ObjectMapper objectMapper = new ObjectMapper();
	    
	    Map<String, Object> map = objectMapper.readValue(responseBody, Map.class);
	    List<Map<String, Object>> list = (List<Map<String,Object>>)map.get("Apttradedelngdetail");
	    Map<String,Object> rowindex = list.get(1);
		List<Map<String, Object>> rowdata = (List<Map<String, Object>>)rowindex.get("row"); 
	    log.info(rowdata);
	    
		MapVO mapvo = new MapVO();
	    
	    //향상된 for문 ([데이터타입][변수명]:[data])
	    for(Map<String,Object>realdata : rowdata) {
	    	mapvo.setApt_name((String)realdata.get("APT_NM"));
	    	mapvo.setTransaction_price((Integer.parseInt(realdata.get("DELNG_AMT").toString())));
	    	mapvo.setTransaction_yy((String)(realdata.get("YY")));
	    	mapvo.setTransaction_mm((String)(realdata.get("MT")));
	    	mapvo.setTransaction_dd((String)(realdata.get("DE")));
	    	mapvo.setRode_name((String)realdata.get("LEGALDONG_NM"));
	    	mapvo.setStreet_number((String)realdata.get("LOTNO"));
	    	mapvo.setBuild_yy((String)realdata.get("BUILD_YY"));
	    	mapvo.setFloor((Integer.parseInt(realdata.get("FLOOR_CNT").toString())));
	    	mapvo.setArea(Double.parseDouble(realdata.get("PRVTUSE_AR").toString()));   	
	    	
	    	kakaoAdress(mapvo);
	    	log.info(mapvo);
	    }
	    
	    mapMapper.addList(mapvo);
	    
	}
	
    public void kakaoAdress(MapVO mapVO) throws Exception {
    	RestTemplate restTemplate = new RestTemplate();
    	HttpHeaders headers = new HttpHeaders();
    	// KEY=fd588635f78f47f0b90354625482c963
    	String key = "KakaoAK 05b37aaff2282416af8e225e012e0a44";
    	headers.set("Authorization", key);
    	
    	HttpEntity<String> entity = new HttpEntity<String>("parameters",headers);
    	String encode = mapVO.getRode_name() + " " +mapVO.getStreet_number();
    	String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + encode;
    	
    	ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    	
    
    	if(res.getStatusCode().is2xxSuccessful() && res.getBody() != null) {
    		
    		ObjectMapper objectMapper = new ObjectMapper();
    		Map<String, Object> map =  objectMapper.readValue(res.getBody(), Map.class);
    		List<Map<String,Object>> list = (List<Map<String,Object>>)map.get("documents");
    		
    		mapVO.setX_coordinate((Double.parseDouble(list.get(0).get("x").toString())));
    		mapVO.setY_coordinate((Double.parseDouble(list.get(0).get("y").toString())));
    	}

    }
}
