package com.ai.opt.sol.web.controller.prdline;

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

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sol.api.apisol.param.APISolIndustry;
import com.ai.opt.sol.api.apisol.param.APISolPrdlineVersion;
import com.ai.opt.sol.api.apisol.param.APISolPrdlineVersionResult;
import com.ai.opt.sol.web.base.model.ResponseData;
import com.ai.opt.sol.web.controller.serviceDefine.ServiceDefine;

@Controller
@RequestMapping("/prdline")
public class PrdLineController {
	
	@RequestMapping("/add")
	public ModelAndView addView(){
		ModelAndView view = new ModelAndView("/prdline/save");
		
		//TODO 查询行业类型列表初始化呢行业类型下来表使用  调用 ISolIndustrySV.queryIndustry
		
		
		//测试数据
		List<APISolIndustry> solIndustryList = new ArrayList<APISolIndustry>();
		for(int i=0;i<4;i++){
			APISolIndustry solIndustry = new APISolIndustry();
			solIndustry.setIndustryCode(""+i);
			solIndustry.setIndustryName("类型"+i);
			solIndustryList.add(solIndustry);
		}
		
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("solIndustryList", solIndustryList);
		SolPrdline solPrdline = new SolPrdline();
		modelMap.put("solPrdline", solPrdline);
		view.addAllObjects(modelMap);
        return view;
	}
	
	@RequestMapping("/edit")
	public ModelAndView editView(String prdlineId){
		ModelAndView view = new ModelAndView("/prdline/save");
		
		//TODO 通过PrdlineId 查询产品线信息 ISolPrdlineSV.querySolPrdlineId
		
		//测试数据
		SolPrdline solPrdline = new SolPrdline();
		solPrdline.setCreateTime("2016-10-25");
		solPrdline.setIndustryCode("1");
		solPrdline.setIndustryName("互联网");
		solPrdline.setPrdlineCode("test001");
		solPrdline.setPrdlineId("001");
		solPrdline.setPrdlineManager("项目经理");
		solPrdline.setPrdlineName("测试1");
		solPrdline.setPrdlineRemark("测试数据");
		
		//TODO 查询行业类型列表 ISolIndustrySV.queryIndustry
		
		//测试数据
		List<APISolIndustry> solIndustryList = new ArrayList<APISolIndustry>();
		for(int i=0;i<4;i++){
			APISolIndustry solIndustry = new APISolIndustry();
			solIndustry.setIndustryCode(""+i);
			solIndustry.setIndustryName("类型"+i);
			solIndustryList.add(solIndustry);
		}
		
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("solPrdline", solPrdline);
		modelMap.put("solIndustryList", solIndustryList);
		view.addAllObjects(modelMap);
		
        return view;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public ResponseData<String> save(HttpServletRequest request,HttpSession session){
		//TODO 保存服务
		//当prdlineId为空时  新增 ISolPrdlineSV.createSolPrdline
		
		//当prdlineId非空时  修改 ISolPrdlineSV.modifySolPrdline
		return new ResponseData<>(ResponseData.AJAX_STATUS_SUCCESS, "成功");
	}
	
	@RequestMapping("/list")
	public String list() {
		return "/prdline/list";
	}
	
	/**
	 * 点击查询按钮调用方法-查询列表
	 * 
	 * @return
	 */
	@RequestMapping("/getPrdlineList")
	@ResponseBody
	private ResponseData<PageInfoResponse<SolPrdline>> queryNormProduct(HttpServletRequest request,
			String queryParams) {
		ResponseData<PageInfoResponse<SolPrdline>> responseData = null;
		try {
			// TODO 查询服务  调用ISolPrdlineSV.querySolPrdlineNameCode (未实现分页查询)

			PageInfoResponse<SolPrdline> result = new PageInfoResponse<SolPrdline>();
			result.setCount(5);
			result.setPageCount(1);
			ResponseHeader responseHeader = new ResponseHeader(true, ResponseData.AJAX_STATUS_SUCCESS, "");
			result.setResponseHeader(responseHeader);
			result.setPageNo(1);
			result.setPageSize(10);
			List<SolPrdline> result1 = new ArrayList<SolPrdline>();
			for (int i = 0; i < 5; i++) {
				SolPrdline e = new SolPrdline();
				e.setPrdlineCode("test00" + i);
				e.setPrdlineName("测试" + i);
				e.setCreateTime("2016-10-25");
				e.setIndustryCode("01");
				e.setIndustryName("互联网");
				e.setPrdlineId("001");
				e.setPrdlineManager("项目经理");
				e.setPrdlineRemark("测试数据");
				result1.add(e);
			}
			result.setResult(result1);

			responseData = new ResponseData<PageInfoResponse<SolPrdline>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功",
					result);
		} catch (Exception e) {
			responseData = new ResponseData<PageInfoResponse<SolPrdline>>(ResponseData.AJAX_STATUS_FAILURE,
					"获取信息异常");
		}
		return responseData;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResponseData<String> delete(HttpSession session, String prdlineId){
		//TODO 删除服务 ISolPrdlineSV.delSolPrdline
		
		return new ResponseData<>(ResponseData.AJAX_STATUS_SUCCESS, "成功");
	}
	
	/**
	 * 通过服务id获得对应的服务版本列表
	 * @param request
	 * @param srvApiId
	 * @return
	 */
	@RequestMapping("/getVersionList")
	@ResponseBody
	private ResponseData<PageInfoResponse<APISolPrdlineVersionResult>> getVersionList(HttpServletRequest request,
			String srvApiId) {
		ResponseData<PageInfoResponse<APISolPrdlineVersionResult>> responseData = null;
		try {
			// TODO 通过服务id获得对应的服务版本 ISolServiceVersionSV.querySolServiceVersion

			// 假数据 后续删除
			PageInfoResponse<APISolPrdlineVersionResult> result = new PageInfoResponse<APISolPrdlineVersionResult>();
			result.setCount(5);
			result.setPageCount(1);
			ResponseHeader responseHeader = new ResponseHeader(true, ResponseData.AJAX_STATUS_SUCCESS, "");
			result.setResponseHeader(responseHeader);
			result.setPageNo(1);
			result.setPageSize(10);
			List<APISolPrdlineVersionResult> result1 = new ArrayList<APISolPrdlineVersionResult>();
			for (int i = 0; i < 3; i++) {
				APISolPrdlineVersionResult e = new APISolPrdlineVersionResult();
				e.setCreateTime("2016-10-2" + i + " 14:00:00");
				e.setPrdlineName(i + ".0");
				e.setPrdlineVersion(i+"");
				result1.add(e);
			}
			result.setResult(result1);

			responseData = new ResponseData<PageInfoResponse<APISolPrdlineVersionResult>>(ResponseData.AJAX_STATUS_SUCCESS,
					"查询成功", result);
		} catch (Exception e) {
			responseData = new ResponseData<PageInfoResponse<APISolPrdlineVersionResult>>(ResponseData.AJAX_STATUS_FAILURE,
					"获取信息异常");
		}
		return responseData;
	}

	/**
	 * 新增服务版本
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/addVersion")
	@ResponseBody
	public ResponseData<String> addVersion(HttpServletRequest request, HttpSession session, APISolPrdlineVersion solPrdlineVersion) {
		// TODO 新增版本 ISolPrdlineVersionSV.createSolPrdlineVersion

		return new ResponseData<>(ResponseData.AJAX_STATUS_SUCCESS, "成功");
	}
	
	/**
	 * 获取服务列表
	 * @param prdlineId
	 * @return
	 */
	@RequestMapping("/serviceList")
	public ModelAndView serviceList(String prdlineId) {
		ModelAndView view = new ModelAndView("/prdline/serviceList");
		view.addObject("prdlineId", prdlineId);
		return view;
	}
	
	/**
	 *查询服务列表
	 * @return
	 */
	@RequestMapping("/getServiceList")
	@ResponseBody
	private ResponseData<PageInfoResponse<ServiceDefine>> getServiceList(HttpServletRequest request,
			String queryParams, String srvCategoryId, String prdlineId) {
		ResponseData<PageInfoResponse<ServiceDefine>> responseData = null;
		try {
			// TODO 查询产品线下相关服务  ISolPrdlineVersionSV.querySolPrdlineVersion

			PageInfoResponse<ServiceDefine> result = new PageInfoResponse<ServiceDefine>();
			result.setCount(5);
			result.setPageCount(5);
			ResponseHeader responseHeader = new ResponseHeader(true, ResponseData.AJAX_STATUS_SUCCESS, "");
			result.setResponseHeader(responseHeader);
			result.setPageNo(1);
			result.setPageSize(10);
			List<ServiceDefine> result1 = new ArrayList<ServiceDefine>();
			for (int i = 0; i < 5; i++) {
				ServiceDefine e = new ServiceDefine();
				e.setSrvApiId("test00" + i);
				e.setSrvApiName("测试" + i);
				e.setPrdlineCount(5);
				e.setVersionCount(3);
				e.setSrvCategoryValue("产品中心->查询");
				result1.add(e);
			}
			result.setResult(result1);

			responseData = new ResponseData<PageInfoResponse<ServiceDefine>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功",
					result);
		} catch (Exception e) {
			responseData = new ResponseData<PageInfoResponse<ServiceDefine>>(ResponseData.AJAX_STATUS_FAILURE,
					"获取信息异常");
		}
		return responseData;
	}
}
