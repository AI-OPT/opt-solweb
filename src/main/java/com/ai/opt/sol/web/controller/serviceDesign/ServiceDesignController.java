package com.ai.opt.sol.web.controller.serviceDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sol.api.apisol.param.APISolServiceDesignInput;
import com.ai.opt.sol.api.apisol.param.APISolServiceDesignOutput;
import com.ai.opt.sol.web.base.model.ResponseData;

@Controller
@RequestMapping("/serviceDesign")
public class ServiceDesignController {
	
	@RequestMapping("/design")
	public ModelAndView design(String srvApiId){
		if(StringUtil.isBlank(srvApiId)){
			return addView();
		}else{
			return editView(srvApiId);
		}
	}

	@RequestMapping("/add")
	public ModelAndView addView() {
		ModelAndView view = new ModelAndView("/serviceDesign/save");
		view.addObject("isAdd", true);
		return view;
	}

	@RequestMapping("/edit")
	public ModelAndView editView(String srvApiId) {
		ModelAndView modelAndView = new ModelAndView("/serviceDesign/save");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("isAdd", false);
		// TODO 根据srvApiId查询服务设计对象（入参出参） ISolServiceParamSV.designServiceQuery

		// 测试数据
		modelMap.put("srvApiId", "1001");
		modelMap.put("srvApiName", "测试数据");
		List<APISolServiceDesignInput> inputParamList = new ArrayList<APISolServiceDesignInput>();
		APISolServiceDesignInput inputParams = new APISolServiceDesignInput();
		inputParams.setInputId("0001");
		inputParams.setInputName("入参1");
		inputParams.setIsRequired("Y");
		inputParams.setParentInputName("入参对象");
		inputParams.setSrvApiId("1001");
		inputParamList.add(inputParams);
		modelMap.put("inputParamList", inputParamList);

		List<APISolServiceDesignOutput> outputParamList = new ArrayList<APISolServiceDesignOutput>();
		APISolServiceDesignOutput outputParams = new APISolServiceDesignOutput();
		outputParams.setOutputId("2001");
		outputParams.setOutputName("出参1");
		outputParams.setParentOutputName("出参对象");
		outputParams.setSrvApiId("1001");
		outputParamList.add(outputParams);
		modelMap.put("outputParamList", outputParamList);

		modelAndView.addAllObjects(modelMap);
		return modelAndView;
	}

	@RequestMapping("/save")
	@ResponseBody
	public ResponseData<String> save(HttpServletRequest request, HttpSession session) {
		String srvApiId = request.getParameter("srvApiId");
		String inputModifyArray = request.getParameter("inputModifyArray");
		String inputDeleteArray = request.getParameter("inputDeleteArray");
		String inputAddArray = request.getParameter("inputAddArray");
		String outputModifyArray = request.getParameter("outputModifyArray");
		String outputDeleteArray = request.getParameter("outputDeleteArray");
		String outputAddArray = request.getParameter("outputAddArray");
		// 修改入参数据
		if (!StringUtil.isBlank(inputModifyArray)) {
			// TODO 调用入参修改 ISolServiceParamSV.modifyInputServiceParam
		}
		// 新增入参数据
		if (!StringUtil.isBlank(inputAddArray)) {
			// TODO 调用入参新增 ISolServiceParamSV.createServiceInput
		}
		// 删除入参数据
		if (!StringUtil.isBlank(inputDeleteArray)) {
			// TODO 调用入参删除 ISolServiceParamSV.delInputServiceParam
		}

		// 修改出参数据
		if (!StringUtil.isBlank(outputModifyArray)) {
			// TODO 调用出参修改 ISolServiceParamSV.modifyOutputServiceParam
		}
		// 新增出参数据
		if (!StringUtil.isBlank(outputAddArray)) {
			// TODO 调用出参新增 ISolServiceParamSV.createServiceOutput
		}
		// 删除出参数据
		if (!StringUtil.isBlank(outputDeleteArray)) {
			// TODO 调用出参删除 ISolServiceParamSV.delOutputServiceParam
		}

		return new ResponseData<>(ResponseData.AJAX_STATUS_SUCCESS, "成功");
	}

	// @RequestMapping("inputParams/modify")
	// @ResponseBody
	// public ResponseData<String> inputParamsModify(HttpServletRequest request,
	// HttpSession session, String inputId) {
	//
	// return new ResponseData<>(ResponseData.AJAX_STATUS_SUCCESS, "成功");
	// }
	//
	// @RequestMapping("inputParams/delete")
	// @ResponseBody
	// public ResponseData<String> inputParamsDelete(HttpServletRequest request,
	// HttpSession session, String inputId) {
	//
	// return new ResponseData<>(ResponseData.AJAX_STATUS_SUCCESS, "成功");
	// }
	//
	// @RequestMapping("outputParams/modify")
	// @ResponseBody
	// public ResponseData<String> outputParamsModify(HttpServletRequest
	// request, HttpSession session, String outputId) {
	//
	// return new ResponseData<>(ResponseData.AJAX_STATUS_SUCCESS, "成功");
	// }
	//
	// @RequestMapping("outputParams/delete")
	// @ResponseBody
	// public ResponseData<String> outputParamsDelete(HttpServletRequest
	// request, HttpSession session, String outputId) {
	//
	// return new ResponseData<>(ResponseData.AJAX_STATUS_SUCCESS, "成功");
	// }

}
