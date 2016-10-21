package com.ai.opt.sol.web.controller.serviceDefine;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
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
	public ResponseData<String> save(HttpServletRequest request,HttpSession session,boolean isAdd){
		//当  新增
		
		//当  修改
		return new ResponseData<>(ResponseData.AJAX_STATUS_SUCCESS, "成功");
	}
	
	/**
	 * 进入页面调用-加载类目
	 */
	@RequestMapping("/list")
	public String list() {
		return "/serviceDefine/list";
	}

	/**
	 * 点击查询按钮调用方法-查询列表
	 * @return
	 */
	@RequestMapping("/getServiceList")
	@ResponseBody
	private ResponseData<PageInfoResponse<ServiceDefine>> queryNormProduct(HttpServletRequest request, String queryParams) {
		ResponseData<PageInfoResponse<ServiceDefine>> responseData = null;
		try {
			// TODO 查询服务
			

			PageInfoResponse<ServiceDefine> result = new PageInfoResponse<ServiceDefine>();
			result.setCount(5);
			result.setPageCount(1);
			ResponseHeader responseHeader = new ResponseHeader(true, ResponseData.AJAX_STATUS_SUCCESS, "");
			result.setResponseHeader(responseHeader );
			result.setPageNo(1);
			result.setPageSize(10);
			List<ServiceDefine> result1=new ArrayList<ServiceDefine>();
			for(int i=0;i<5;i++){
				ServiceDefine e=new ServiceDefine();
				e.setSrvApiId("test00"+i);
				e.setSrvApiName("测试"+i);
				e.setPrdlineCount(2);
				e.setVersionCount(2);
				e.setSrvCategoryValue("产品中心->查询");
				result1.add(e);
			}
			result.setResult(result1);

			responseData = new ResponseData<PageInfoResponse<ServiceDefine>>(ResponseData.AJAX_STATUS_SUCCESS,
					"查询成功", result);
		} catch (Exception e) {
			responseData = new ResponseData<PageInfoResponse<ServiceDefine>>(ResponseData.AJAX_STATUS_FAILURE,
					"获取信息异常");
		}
		return responseData;
	}

}
