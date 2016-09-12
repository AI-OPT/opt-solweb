package com.ai.opt.sol.web.sandbox.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sol.api.sandbox.ISandBoxSV;
import com.ai.opt.sol.api.sandbox.param.APICallCase;
import com.ai.opt.sol.api.sandbox.param.APICallCaseQuery;
import com.ai.opt.sol.api.sandbox.param.APICallCaseReqParam;
import com.ai.opt.sol.api.sandbox.param.APICallSetting;
import com.ai.opt.sol.api.sandbox.param.APIRest;
import com.ai.opt.sol.api.sandbox.param.APIRestTestReq;
import com.ai.opt.sol.web.base.exception.SystemException;
import com.ai.opt.sol.web.base.model.ResponseData;
import com.ai.opt.sol.web.base.util.DubboConsumerFactory;
import com.ai.opt.sol.web.base.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/sandbox")
public class SandboxController {

    private static final Logger LOG = Logger.getLogger(SandboxController.class);

    @RequestMapping("/apisetting.html")
    public ModelAndView apisetting(HttpServletRequest request) {
        String indexId = request.getParameter("indexId");
        ISandBoxSV iSandBoxSV = DubboConsumerFactory.getService(ISandBoxSV.class);
        APICallSetting apiCallSetting = iSandBoxSV.getAPICallSettingFromES(indexId);
        request.setAttribute("apiCallSetting", apiCallSetting);
        ModelAndView view = new ModelAndView("sandbox/apisetting");
        return view;
    }

    @RequestMapping("/apireqparamset.html")
    public ModelAndView apireqparamset(HttpServletRequest request) {
        String indexId = request.getParameter("indexId");
        ISandBoxSV iSandBoxSV = DubboConsumerFactory.getService(ISandBoxSV.class);
        APICallSetting apiCallSetting = iSandBoxSV.getAPICallSettingFromES(indexId);
        request.setAttribute("apiCallSetting", apiCallSetting);
        ModelAndView view = new ModelAndView("sandbox/apireqparamset");
        return view;
    }

    @RequestMapping("/resttest.html")
    public ModelAndView toTestRest(HttpServletRequest request) {
        String indexId = request.getParameter("indexId");
        ISandBoxSV iSandBoxSV = DubboConsumerFactory.getService(ISandBoxSV.class);
        APIRest apiRest = iSandBoxSV.getAPIRest(indexId);
        request.setAttribute("apiRest", apiRest);
        ModelAndView view = new ModelAndView("sandbox/resttest");
        return view;
    }

    @RequestMapping("/restTest")
    public ResponseData<JSONObject> restTest(String data) {
        ResponseData<JSONObject> responseData = null;
        JSONObject json = new JSONObject();
        try {
            if (StringUtil.isBlank(data)) {
                throw new SystemException("没有传入待测试的数据");
            }
            /* 转换成被测试数据对象 */
            APIRestTestReq vo = JSON.parseObject(data, APIRestTestReq.class);
            if (vo == null) {
                throw new SystemException("没有传入待测试的数据");
            }
            String testResult = DubboConsumerFactory.getService(ISandBoxSV.class).testRest(vo);
            json.put("actualCode", "success");
            json.put("actualResult", testResult);
        } catch (Exception ex) {
            json.put("actualCode", "failure");
            json.put("actualResult", ex.getMessage());
        }
        responseData = new ResponseData<JSONObject>(ResponseData.AJAX_STATUS_SUCCESS, "服务测试成功",
                json);
        return responseData;
    }

    @RequestMapping("/saveAPICallSetting")
    public ResponseData<String> saveAPICallSetting(String data) {
        ResponseData<String> responseData = null;
        try {
            if (StringUtil.isBlank(data)) {
                throw new SystemException("没有传入数据包");
            }
            APICallSetting apiCallSetting = JSON.parseObject(data, APICallSetting.class);
            /* 转换成被测试数据对象 */
            if (apiCallSetting == null) {
                throw new SystemException("没有传入任何信息");
            }

            DubboConsumerFactory.getService(ISandBoxSV.class).saveAPICallSetting(apiCallSetting);
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "服务参数模板设置成功",
                    null);
        } catch (Exception e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "服务参数模板设置失败:"
                    + e.getMessage());
        }
        return responseData;
    }

    @RequestMapping("/checkRegistryAvailable")
    public ResponseData<String> checkRegistryAvailable(String registryURL) {
        ResponseData<String> responseData = null;
        try {
            if (StringUtil.isBlank(registryURL)) {
                throw new SystemException("没有传入注册中心地址");
            }
            boolean result = DubboConsumerFactory.getService(ISandBoxSV.class)
                    .checkRegistryAvailable(registryURL);
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,
                    "测试注册中心连通性成功", result ? "1" : "0");
        } catch (Exception e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,
                    "测试注册中心连通性失败,请检查");
        }
        return responseData;
    }

    @RequestMapping("/loadAPISetting")
    public ResponseData<APICallSetting> loadAPISetting(String indexId) {
        ResponseData<APICallSetting> responseData = null;
        try {
            APICallSetting data = DubboConsumerFactory.getService(ISandBoxSV.class)
                    .getAPICallSettingFromES(indexId);
            responseData = new ResponseData<APICallSetting>(ResponseData.AJAX_STATUS_SUCCESS,
                    "获取服务设置信息成功", data);
        } catch (Exception e) {
            LOG.error(e);
            responseData = new ResponseData<APICallSetting>(ResponseData.AJAX_STATUS_FAILURE,
                    "获取服务设置信息失败" + e.getMessage());
        }
        return responseData;

    }

    /**
     * 保存沙箱设置信息
     * 
     * @param data
     * @return
     * @author zhangchao
     */
    @RequestMapping("/setAPISandboxSetting")
    public ResponseData<String> setAPISandboxSetting(String data) {
        ResponseData<String> responseData = null;
        try {
            if (StringUtil.isBlank(data)) {
                throw new SystemException("没有传入数据包");
            }
            /* 转换成被测试数据对象 */
            APICallSetting vo = JSON.parseObject(data, APICallSetting.class);
            if (vo == null) {
                throw new SystemException("没有传入任何信息");
            }
            DubboConsumerFactory.getService(ISandBoxSV.class).setAPISandboxSetting(vo);
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "服务设置成功", "");
        } catch (Exception e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "服务设置失败:"
                    + e.getMessage());
        }
        return responseData;
    }

    @RequestMapping("/toMockTest.html")
    public ModelAndView toMockTest(HttpServletRequest request) {
        String indexId = request.getParameter("indexId");
        ISandBoxSV iSandBoxSV = DubboConsumerFactory.getService(ISandBoxSV.class);
        APICallSetting apiCallSetting = iSandBoxSV.getAPICallSettingFromES(indexId);
        request.setAttribute("apiCallSetting", apiCallSetting);
        ModelAndView view = new ModelAndView("sandbox/mocktest");
        return view;
    }

    @RequestMapping("/mockTest")
    public ResponseData<JSONObject> mockTest(String data, String registryURL) {
        ResponseData<JSONObject> responseData = null;
        JSONObject json = new JSONObject();
        try {
            if (StringUtil.isBlank(data)) {
                throw new SystemException("没有传入待测试的数据");
            }
            /* 转换成被测试数据对象 */
            APICallCase vo = JSON.parseObject(data, APICallCase.class);
            if (vo == null) {
                throw new SystemException("没有传入待测试的数据");
            }
            String testResult = DubboConsumerFactory.getService(ISandBoxSV.class).excuteTest(vo,
                    registryURL);
            json.put("actualCode", "success");
            json.put("actualResult", testResult);
        } catch (Exception ex) {
            json.put("actualCode", "failure");
            json.put("actualResult", ex.getMessage());
        }
        responseData = new ResponseData<JSONObject>(ResponseData.AJAX_STATUS_SUCCESS, "服务测试成功",
                json);
        return responseData;
    }

    @RequestMapping("/saveTestCase")
    public ResponseData<String> saveTestCase(String data) {
        ResponseData<String> responseData = null;
        try {
            if (StringUtil.isBlank(data)) {
                throw new SystemException("没有传入数据包");
            }
            /* 转换成被测试数据对象 */
            APICallCase vo = JSON.parseObject(data, APICallCase.class);
            if (vo == null) {
                throw new SystemException("没有传入任何信息");
            }
            DubboConsumerFactory.getService(ISandBoxSV.class).saveAPICallCase(vo);
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "服务测试用例保存成功",
                    "");
        } catch (Exception e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "服务测试用例保存失败:"
                    + e.getMessage());
        }
        return responseData;
    }

    @RequestMapping("/queryAPICases")
    public ResponseData<PageInfo<APICallCase>> queryAPICases(String queryCond) {
        ResponseData<PageInfo<APICallCase>> responseData = null;
        try {
            if (StringUtil.isBlank(queryCond)) {
                throw new SystemException("查询条件为空");
            }
            /* 转换成被测试数据对象 */
            APICallCaseQuery vo = JSON.parseObject(queryCond, APICallCaseQuery.class);
            if (vo == null) {
                throw new SystemException("查询条件为空");
            }
            PageInfo<APICallCase> result = DubboConsumerFactory.getService(ISandBoxSV.class)
                    .queryAPICallCases(vo);
            responseData = new ResponseData<PageInfo<APICallCase>>(
                    ResponseData.AJAX_STATUS_SUCCESS, "查询成功", result);
        } catch (Exception e) {
            responseData = new ResponseData<PageInfo<APICallCase>>(
                    ResponseData.AJAX_STATUS_FAILURE, "查询失败:" + e.getMessage());
        }
        return responseData;
    }

    @RequestMapping("/queryAPICaseReqParamByCaseId")
    public ResponseData<List<APICallCaseReqParam>> queryAPICaseReqParamByCaseId(String caseId) {
        ResponseData<List<APICallCaseReqParam>> responseData = null;
        try {
            if (StringUtil.isBlank(caseId)) {
                throw new SystemException("查询条件为空");
            }
            List<APICallCaseReqParam> list = DubboConsumerFactory.getService(ISandBoxSV.class)
                    .queryAPICallCaseReqParams(caseId);
            responseData = new ResponseData<List<APICallCaseReqParam>>(
                    ResponseData.AJAX_STATUS_SUCCESS, "查询成功", list);
        } catch (Exception e) {
            responseData = new ResponseData<List<APICallCaseReqParam>>(
                    ResponseData.AJAX_STATUS_FAILURE, "查询失败:" + e.getMessage());
        }
        return responseData;
    }

}
