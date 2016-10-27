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
import com.ai.opt.sol.api.apisol.param.APIPrdFlag;
import com.ai.opt.sol.api.apisol.param.APISolPrdlineVersion;
import com.ai.opt.sol.api.apisol.param.APISolServiceDefine;
import com.ai.opt.sol.api.apisol.param.APISolServiceVersion;
import com.ai.opt.sol.web.base.model.ResponseData;
import com.ai.paas.ipaas.util.StringUtil;

@Controller
@RequestMapping("/serviceDefine")
public class ServiceDefineController {

	@RequestMapping("/add")
	public ModelAndView addView() {
		ModelAndView view = new ModelAndView("/serviceDefine/save");
		return view;
	}

	/**
	 * 保存服务定义（新增 修改）
	 * @param request
	 * @param session
	 * @param isAdd
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ResponseData<String> save(HttpServletRequest request, HttpSession session,APISolServiceDefine solServiceDefine) {
		if(StringUtil.isBlank(solServiceDefine.getSrvApiId())){
			// TODO 当 新增 ISolServiceDefineSV.createSolService
			
		}else{
			// TODO 当 修改 ISolServiceDefineSV.modifySolService
			
		}
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
	 * 
	 * @return
	 */
	@RequestMapping("/getServiceList")
	@ResponseBody
	private ResponseData<PageInfoResponse<ServiceDefine>> queryNormProduct(HttpServletRequest request,
			String queryParams, String srvCategoryId) {
		ResponseData<PageInfoResponse<ServiceDefine>> responseData = null;
		try {
			// TODO 查询服务 ISolServiceDefineSV.querySolService(未实现分页查询)

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

	/**
	 * 通过服务id获得对应的产品线列表
	 * @param request
	 * @param srvApiId
	 * @return
	 */
	@RequestMapping("/getPrdlineList")
	@ResponseBody
	private ResponseData<PageInfoResponse<APIPrdFlag>> getPrdlineList(HttpServletRequest request, String srvApiId) {
		ResponseData<PageInfoResponse<APIPrdFlag>> responseData = null;
		try {
			// TODO 通过服务id获得对应的产品线 ISolServicePrdlineRelSV.querySolServicePrdlineRel(未实现分页)

			// 假数据 后续删除
			PageInfoResponse<APIPrdFlag> result = new PageInfoResponse<APIPrdFlag>();
			result.setCount(5);
			//result.setPageCount(1);
			ResponseHeader responseHeader = new ResponseHeader(true, ResponseData.AJAX_STATUS_SUCCESS, "");
			result.setResponseHeader(responseHeader);
			result.setPageNo(1);
			result.setPageSize(10);
			List<APIPrdFlag> result1 = new ArrayList<APIPrdFlag>();
			for (int i = 0; i < 5; i++) {
				APIPrdFlag e = new APIPrdFlag();
				e.setPrdlineCode("TEST-" + i);
				e.setPrdlineManager("项目经理");
				e.setPrdlineName("测试" + i);
				e.setPrdlineVersion("1.0");
				e.setServiceVersion("1.0-SNAPSHOT");
				result1.add(e);
			}
			result.setResult(result1);

			responseData = new ResponseData<PageInfoResponse<APIPrdFlag>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功",
					result);
		} catch (Exception e) {
			responseData = new ResponseData<PageInfoResponse<APIPrdFlag>>(ResponseData.AJAX_STATUS_FAILURE, "获取信息异常");
		}
		return responseData;
	}

	/**
	 * 通过产品线id获得对应的产品线信息
	 * @param request
	 * @param srvPrdlineId
	 * @return
	 */
	@RequestMapping("/getPrdline")
	@ResponseBody
	private ResponseData<APIPrdFlag> getPrdline(HttpServletRequest request, String srvPrdlineId) {
		ResponseData<APIPrdFlag> responseData = null;
		try {
			// TODO 通过产品线id获得对应的产品线 ISolPrdlineSV.querySolPrdlineId

			// 假数据 后续删除
			APIPrdFlag e = new APIPrdFlag();
			e.setPrdlineCode("TEST-" + 1);
			e.setPrdlineManager("项目经理");
			e.setPrdlineName("测试" + 1);
			e.setPrdlineVersion("1.0");
			e.setPrdlineVersionId("1");
			e.setServiceVersion("1.0-SNAPSHOT");
			e.setSrvVersionId("1");

			responseData = new ResponseData<APIPrdFlag>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", e);
		} catch (Exception e) {
			responseData = new ResponseData<APIPrdFlag>(ResponseData.AJAX_STATUS_FAILURE, "获取信息异常");
		}
		return responseData;
	}
	
	/**
	 * 加载产品线版本下拉框数据
	 * @param request
	 * @param srvApiId
	 * @return
	 */
	@RequestMapping("/getSelectPrdlineVersionList")
	@ResponseBody
	private ResponseData<List<APISolPrdlineVersion>> getSelectPrdlineVersionList(HttpServletRequest request,
			String srvPrdlineId) {
		ResponseData<List<APISolPrdlineVersion>> responseData = null;
		try {
			// TODO 通过产品线id获得对应的产品线版本 ISolPrdlineVersionSV.querySolPrdlineVersionId

			// 假数据 后续删除
			List<APISolPrdlineVersion> result = new ArrayList<APISolPrdlineVersion>();
			for (int i = 0; i < 5; i++) {
				APISolPrdlineVersion e = new APISolPrdlineVersion();
				e.setPrdlineVersion(i + ".0");
				e.setPrdlineVersionId(i+"");
				result.add(e);
			}
			responseData = new ResponseData<List<APISolPrdlineVersion>>(ResponseData.AJAX_STATUS_SUCCESS,
					"查询成功", result);
		} catch (Exception e) {
			responseData = new ResponseData<List<APISolPrdlineVersion>>(ResponseData.AJAX_STATUS_FAILURE,
					"获取信息异常");
		}
		return responseData;
	}
	
	/**
	 * 加载服务版本下拉框数据
	 * @param request
	 * @param srvApiId
	 * @return
	 */
	@RequestMapping("/getSelectServiceVersionList")
	@ResponseBody
	private ResponseData<List<APISolServiceVersion>> getSelectServiceVersionList(HttpServletRequest request,
			String srvApiId) {
		ResponseData<List<APISolServiceVersion>> responseData = null;
		try {
			// TODO 通过服务id获得对应的服务版本 ISolServiceVersionSV.querySolServiceVersionId

			// 假数据 后续删除
			List<APISolServiceVersion> result = new ArrayList<APISolServiceVersion>();
			for (int i = 0; i < 5; i++) {
				APISolServiceVersion e = new APISolServiceVersion();
				e.setSrvVersion(i + ".0-SNAPSHOT");
				e.setSrvVersionId(i+"");
				result.add(e);
			}
			responseData = new ResponseData<List<APISolServiceVersion>>(ResponseData.AJAX_STATUS_SUCCESS,
					"查询成功", result);
		} catch (Exception e) {
			responseData = new ResponseData<List<APISolServiceVersion>>(ResponseData.AJAX_STATUS_FAILURE,
					"获取信息异常");
		}
		return responseData;
	}

	/**
	 * 新增产品线
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/addPrdline")
	@ResponseBody
	public ResponseData<String> addPrdline(HttpServletRequest request, HttpSession session) {
		// TODO 新增服务关联产品线 ISolServicePrdlineRelSV.createSolServicePrdlineRel

		return new ResponseData<>(ResponseData.AJAX_STATUS_SUCCESS, "成功");
	}
	
	/**
	 * 修改产品线
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/modifyPrdline")
	@ResponseBody
	public ResponseData<String> modifyPrdline(HttpServletRequest request, HttpSession session) {
		// TODO 修改产品线 ISolServicePrdlineRelSV.modifySolServicePrdlineRel

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
	private ResponseData<PageInfoResponse<APISolServiceVersion>> getVersionList(HttpServletRequest request,
			String srvApiId) {
		ResponseData<PageInfoResponse<APISolServiceVersion>> responseData = null;
		try {
			// TODO 通过服务id获得对应的服务版本ISolServiceVersionSV.querySolServiceVersionId

			// 假数据 后续删除
			PageInfoResponse<APISolServiceVersion> result = new PageInfoResponse<APISolServiceVersion>();
			result.setCount(5);
			result.setPageCount(1);
			ResponseHeader responseHeader = new ResponseHeader(true, ResponseData.AJAX_STATUS_SUCCESS, "");
			result.setResponseHeader(responseHeader);
			result.setPageNo(1);
			result.setPageSize(10);
			List<APISolServiceVersion> result1 = new ArrayList<APISolServiceVersion>();
			for (int i = 0; i < 3; i++) {
				APISolServiceVersion e = new APISolServiceVersion();
				e.setCreateTime("2016-10-2" + i + " 14:00:00");
				e.setSrvVersion(i + ".0-SNAPSHOT");
				e.setVersionRemark("测试数据");
				result1.add(e);
			}
			result.setResult(result1);

			responseData = new ResponseData<PageInfoResponse<APISolServiceVersion>>(ResponseData.AJAX_STATUS_SUCCESS,
					"查询成功", result);
		} catch (Exception e) {
			responseData = new ResponseData<PageInfoResponse<APISolServiceVersion>>(ResponseData.AJAX_STATUS_FAILURE,
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
	public ResponseData<String> addVersion(HttpServletRequest request, HttpSession session) {
		// TODO 新增版本 ISolServiceVersionSV.createSolServiceVersion

		return new ResponseData<>(ResponseData.AJAX_STATUS_SUCCESS, "成功");
	}

}
