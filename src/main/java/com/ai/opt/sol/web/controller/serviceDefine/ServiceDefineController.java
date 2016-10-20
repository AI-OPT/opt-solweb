package com.ai.opt.sol.web.controller.serviceDefine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.opt.sol.web.base.model.ResponseData;

@Controller
@RequestMapping("/serviceDefine")
public class ServiceDefineController {
	
	@RequestMapping("/add")
	public ModelAndView addView(){
		ModelAndView view = new ModelAndView("/serviceDefine/save");
        return view;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public ResponseData<String> save(HttpServletRequest request,HttpSession session){
		//当prdlineId为空时  新增
		
		//当prdlineId非空时  修改
		return new ResponseData<>(ResponseData.AJAX_STATUS_SUCCESS, "成功");
	}
}
