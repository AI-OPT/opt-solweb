package com.ai.opt.sol.api.sandbox;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.sol.api.sandbox.param.*;
import com.ai.runner.base.exception.CallerException;
import com.ai.runner.base.vo.PageInfo;

//@Path("SandBox")
//@Consumes({ MediaType.APPLICATION_JSON })
//@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ISandBoxSV {

    boolean checkRegistryAvailable(String registryURL) throws CallerException;

    /**
     * 根据索引ID从搜索引擎读取配置
     * 
     * @param indexId
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    APICallSetting getAPICallSettingFromES(String indexId) throws CallerException;

    /**
     * 保存API的设置信息
     * 
     * @param setting
     * @author zhangchao
     */
    void saveAPICallSetting(APICallSetting setting) throws CallerException;

    /**
     * 获取这个方法已经保存了的测试用例
     * 
     * @param interfaceName
     * @param method
     * @return
     * @author zhangchao
     */
    List<APICallCase> getAPICallCases(String interfaceName, String method) throws CallerException;

    /**
     * 保存新的测试用例
     * 
     * @param callCase
     * @author zhangchao
     */
    void saveAPICallCase(APICallCase callCase) throws CallerException;

    /**
     * 发起DUBBO的MOCK测试
     * 
     * @param callCase
     * @param registryURL
     * @return
     * @author zhangchao
     */
    String excuteTest(APICallCase callCase, String registryURL) throws CallerException;

    /**
     * 保存沙箱设置信息
     * 
     * @param setting
     * @author zhangchao
     */
    void setAPISandboxSetting(APICallSetting setting) throws CallerException;

    /**
     * 按照条件查询测试用例
     * 
     * @param query
     * @return
     * @author zhangchao
     */
    PageInfo<APICallCase> queryAPICallCases(APICallCaseQuery query) throws CallerException;

    /**
     * 获取用例参数模板
     * 
     * @param caseId
     * @return
     * @author zhangchao
     */
    List<APICallCaseReqParam> queryAPICallCaseReqParams(String caseId) throws CallerException;

    /**
     * 根据索引获取API-REST信息
     * 
     * @param indexId
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    APIRest getAPIRest(String indexId) throws CallerException;

    /**
     * 测试REST服务
     * 
     * @param restTestReq
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    String testRest(APIRestTestReq restTestReq) throws CallerException;

}
