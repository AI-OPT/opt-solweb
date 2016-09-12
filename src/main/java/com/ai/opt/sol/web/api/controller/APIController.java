package com.ai.opt.sol.web.api.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sol.api.apisearch.IAPISearchSV;
import com.ai.opt.sol.api.apisearch.param.APIEnvSettings;
import com.ai.opt.sol.api.apisearch.param.APIOwnerType;
import com.ai.opt.sol.api.apisearch.param.APISearchKey;
import com.ai.opt.sol.api.apisearch.param.APISearchResult;
import com.ai.opt.sol.web.base.exception.SystemException;
import com.ai.opt.sol.web.base.model.ResponseData;
import com.ai.opt.sol.web.base.util.DubboConsumerFactory;
import com.ai.opt.sol.web.base.util.StringUtil;
import com.ai.opt.sol.web.sandbox.controller.SandboxController;
import com.ai.runner.apicollector.util.JavaDocletUtil;
import com.ai.runner.apicollector.vo.APIClassDoc;
import com.ai.runner.apicollector.vo.APIClassFieldDoc;
import com.ai.runner.apicollector.vo.APIDoc;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/api")
public class APIController {

    private static final Logger LOG = Logger.getLogger(SandboxController.class);

    @RequestMapping("/index.html")
    public ModelAndView index(HttpServletRequest request) {
        List<APIOwnerType> apiOwnerTypes = DubboConsumerFactory.getService(IAPISearchSV.class)
                .getAPIOwnerTypes();
        request.setAttribute("apiOwnerTypes", apiOwnerTypes);
        ModelAndView view = new ModelAndView("api/index");
        return view;
    }

    /**
     * 获取在线网站的服务统计信息
     * 
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    @RequestMapping("/getAPIStatistics")
    public ResponseData<JSONObject> getAPIStatistics() {
        ResponseData<JSONObject> responseData = null;
        try {
            String data = DubboConsumerFactory.getService(IAPISearchSV.class).getAPIStatistics();
            responseData = new ResponseData<JSONObject>(ResponseData.AJAX_STATUS_SUCCESS,
                    "API在线统计成功", JSONObject.parseObject(data));
        } catch (Exception e) {
            LOG.error(e);
            responseData = new ResponseData<JSONObject>(ResponseData.AJAX_STATUS_FAILURE,
                    "API在线统计失败:" + e.getMessage());
        }
        return responseData;
    }

    @RequestMapping("/tosearch.html")
    public ModelAndView tosearch(HttpServletRequest request) {
        String owner = request.getParameter("owner");
        String ownerType = request.getParameter("ownerType");
        String keywords = request.getParameter("keywords");
        String limitException = request.getParameter("limitException");
        String allowDelete = request.getParameter("allowDelete");
        request.setAttribute("owner", owner);
        request.setAttribute("ownerType", ownerType);
        request.setAttribute("keywords", keywords);
        request.setAttribute("limitException", limitException);
        request.setAttribute("allowDelete", allowDelete);
        ModelAndView view = new ModelAndView("api/search");
        return view;
    }

    @RequestMapping("/search")
    public ResponseData<PageInfo<APISearchResult>> search(String queryCond) {
        ResponseData<PageInfo<APISearchResult>> responseData = null;
        try {
            if (StringUtil.isBlank(queryCond)) {
                throw new SystemException("查询条件为空");
            }
            /* 转换成被测试数据对象 */
            APISearchKey vo = JSON.parseObject(queryCond, APISearchKey.class);
            if (vo == null) {
                throw new SystemException("查询条件为空");
            }
            PageInfo<APISearchResult> result = DubboConsumerFactory.getService(IAPISearchSV.class)
                    .searchAPIDocs(vo);
            responseData = new ResponseData<PageInfo<APISearchResult>>(
                    ResponseData.AJAX_STATUS_SUCCESS, "查询成功", result);
        } catch (Exception e) {
            responseData = new ResponseData<PageInfo<APISearchResult>>(
                    ResponseData.AJAX_STATUS_FAILURE, "查询失败:" + e.getMessage());
        }
        return responseData;
    }

    @RequestMapping("/apidetail.html")
    public ModelAndView apidetail(HttpServletRequest request) {
        String owner = request.getParameter("owner");
        String interfaceName = request.getParameter("interfaceName");
        String methodName = request.getParameter("methodName");
        String version = request.getParameter("version");
        if (StringUtil.isBlank(owner)) {
            throw new SystemException("API提供者不能为空");
        }
        if (StringUtil.isBlank(interfaceName)) {
            throw new SystemException("API接口类不能为空");
        }
        if (StringUtil.isBlank(methodName)) {
            throw new SystemException("API方法不能为空");
        }
        if (StringUtil.isBlank(version)) {
            throw new SystemException("API版本不能为空");
        }
        int id = JavaDocletUtil.getAPIHashCode(interfaceName, methodName, version);
        String data = DubboConsumerFactory.getService(IAPISearchSV.class).getAPIVersionHistory(
                StringUtil.toString(id));
        if (StringUtil.isBlank(data)) {
            throw new SystemException("API的这个版本没有发布，请联系服务负责人先发布");
        }
        APIDoc apiDoc = JSONObject.parseObject(data, APIDoc.class);
        request.setAttribute("apiDoc", apiDoc);
        ModelAndView view = new ModelAndView("api/apidetail");
        return view;
    }

    @RequestMapping("/getAPIVersionNewDetail.html")
    public ModelAndView getAPIVersionNewDetail(HttpServletRequest request) {
        String indexId = request.getParameter("indexId");
        if (StringUtil.isBlank(indexId)) {
            throw new SystemException("缺少API的索引信息");
        }
        String data = DubboConsumerFactory.getService(IAPISearchSV.class).getAPIVersionNew(indexId);
        if (StringUtil.isBlank(data)) {
            throw new SystemException("API的这个版本没有发布，请联系服务负责人先发布");
        }
        APIDoc apiDoc = JSONObject.parseObject(data, APIDoc.class);
        request.setAttribute("apiDoc", apiDoc);
        ModelAndView view = new ModelAndView("api/apidetail");
        return view;
    }

    @RequestMapping("/getSubClassFields")
    public ResponseData<List<APIClassFieldDoc>> getSubClassFields(String pIndexId) {
        ResponseData<List<APIClassFieldDoc>> responseData = null;
        try {
            if (StringUtil.isBlank(pIndexId)) {
                throw new SystemException("获取子属性错误，缺少上级属性索引ID");
            }
            String data = DubboConsumerFactory.getService(IAPISearchSV.class).getAPIClassDetail(
                    pIndexId);
            if (StringUtil.isBlank(data)) {
                throw new SystemException("获取子属性错误，缺少索引数据");
            }
            APIClassDoc classDoc = JSONObject.parseObject(data, APIClassDoc.class);
            responseData = new ResponseData<List<APIClassFieldDoc>>(
                    ResponseData.AJAX_STATUS_SUCCESS, "查询成功", classDoc.getClassFields());
        } catch (Exception e) {
            responseData = new ResponseData<List<APIClassFieldDoc>>(
                    ResponseData.AJAX_STATUS_FAILURE, "查询失败:" + e.getMessage());
        }
        return responseData;
    }

    @RequestMapping("/deleteAPI")
    public ResponseData<String> deleteAPI(String indexId) {
        ResponseData<String> responseData = null;
        try {
            if (StringUtil.isBlank(indexId)) {
                throw new SystemException("缺少索引标识");
            }
            DubboConsumerFactory.getService(IAPISearchSV.class).deleteAPINew(indexId);
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "服务作废成功",
                    null);
        } catch (Exception e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "服务作废失败:"
                    + e.getMessage());
        }
        return responseData;
    }

    @RequestMapping("/downloadAPIs")
    public void downloadOwnerAPIs(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String ownerType = request.getParameter("ownerType");
        String owner = request.getParameter("owner");
        if (StringUtil.isBlank(ownerType)) {
            throw new SystemException("服务提供者类型不能为空");
        }
        if (StringUtil.isBlank(owner)) {
            throw new SystemException("服务提供者不能为空");
        }
        String data = DubboConsumerFactory.getService(IAPISearchSV.class).downloadAPIs(ownerType,
                owner);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.setHeader("Content-disposition", "attachment;filename=" + owner
                + "_apis_data.json");
        PrintWriter out = response.getWriter();
        out.write(data);
        out.flush();
        out.close();
    }

    @RequestMapping("/downloadAPI")
    public void downloadAPI(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String ownerType = request.getParameter("ownerType");
        String owner = request.getParameter("owner");
        String artifactId = request.getParameter("artifactId");
        String interfaceName = request.getParameter("interfaceName");
        String method = request.getParameter("method");
        if (StringUtil.isBlank(ownerType)) {
            throw new SystemException("服务提供者类型不能为空");
        }
        if (StringUtil.isBlank(owner)) {
            throw new SystemException("服务提供者不能为空");
        }
        if (StringUtil.isBlank(artifactId)) {
            throw new SystemException("服务构件名称不能为空");
        }
        if (StringUtil.isBlank(interfaceName)) {
            throw new SystemException("服务接口类名不能为空");
        }
        if (StringUtil.isBlank(method)) {
            throw new SystemException("服务接口方法不能为空");
        }
        String data = DubboConsumerFactory.getService(IAPISearchSV.class).downloadAPI(ownerType,
                owner, artifactId, interfaceName, method);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.setHeader("Content-disposition", "attachment;filename=" + interfaceName + "_"
                + method + "_api_data.json");
        PrintWriter out = response.getWriter();
        out.write(data);
        out.flush();
        out.close();
    }

    @RequestMapping("/toenvsetting.html")
    public ModelAndView toenvsetting(HttpServletRequest request) {
        String owner = request.getParameter("owner");
        String ownerType = request.getParameter("ownerType");

        if (StringUtil.isBlank(owner)) {
            throw new SystemException("API提供者不能为空");
        }
        if (StringUtil.isBlank(ownerType)) {
            throw new SystemException("API提供者类型不能为空");
        }
        request.setAttribute("owner", owner);
        request.setAttribute("ownerType", ownerType);
        ModelAndView view = new ModelAndView("api/envsetting");
        return view;
    }

    @RequestMapping("/saveEnvSetting")
    public ResponseData<String> saveEnvSetting(String envSetting) {
        ResponseData<String> responseData = null;
        try {
            if (StringUtil.isBlank(envSetting)) {
                throw new SystemException("没有提交数据");
            }
            APIEnvSettings vo = JSON.parseObject(envSetting, APIEnvSettings.class);
            if (vo == null) {
                throw new SystemException("没有提交数据");
            }
            DubboConsumerFactory.getService(IAPISearchSV.class).saveAPIEnvSettings(vo);
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "保存成功", "");
        } catch (Exception e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "保存失败:"
                    + e.getMessage());
        }
        return responseData;
    }

    @RequestMapping("/getEnvSetting")
    public ResponseData<APIEnvSettings> getEnvSetting(String settingId) {
        ResponseData<APIEnvSettings> responseData = null;
        try {
            if (StringUtil.isBlank(settingId)) {
                throw new SystemException("设置标识为空");
            }
            APIEnvSettings vo = DubboConsumerFactory.getService(IAPISearchSV.class)
                    .getAPIEnvSetting(settingId);
            if (vo == null) {
                throw new SystemException("设置信息不存在");
            }
            responseData = new ResponseData<APIEnvSettings>(ResponseData.AJAX_STATUS_SUCCESS,
                    "查询成功", vo);
        } catch (Exception e) {
            responseData = new ResponseData<APIEnvSettings>(ResponseData.AJAX_STATUS_FAILURE,
                    "查询失败:" + e.getMessage());
        }
        return responseData;
    }

    @RequestMapping("/getEnvSettings")
    public ResponseData<List<APIEnvSettings>> getEnvSettings(String ownerType, String owner) {
        ResponseData<List<APIEnvSettings>> responseData = null;
        try {
            if (StringUtil.isBlank(owner)) {
                throw new SystemException("API提供者不能为空");
            }
            if (StringUtil.isBlank(ownerType)) {
                throw new SystemException("API提供者类型不能为空");
            }
            List<APIEnvSettings> list = DubboConsumerFactory.getService(IAPISearchSV.class)
                    .getAPIEnvSettings(ownerType, owner);
            responseData = new ResponseData<List<APIEnvSettings>>(ResponseData.AJAX_STATUS_SUCCESS,
                    "查询成功", list);
        } catch (Exception e) {
            responseData = new ResponseData<List<APIEnvSettings>>(ResponseData.AJAX_STATUS_FAILURE,
                    "查询失败:" + e.getMessage());
        }
        return responseData;
    }

}
