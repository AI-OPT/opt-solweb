package com.ai.opt.sol.web.controller.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/test1")
public class TestDomePage {

	@RequestMapping("/demo1")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("/test/demo1");
        return view;
    }

}
