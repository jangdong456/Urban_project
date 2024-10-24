package com.gudee.urban.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UrbanViewController {

	@Autowired
	private UrbanService urbanService;
	
    @GetMapping(value = {"/", "/index"})
    public ModelAndView index() throws Exception {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("message", "Hello, Urban!");
        
        urbanService.getList();
        return modelAndView;
    }
    

}
