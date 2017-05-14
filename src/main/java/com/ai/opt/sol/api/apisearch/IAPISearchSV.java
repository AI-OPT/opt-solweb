package com.ai.opt.sol.api.apisearch;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.sol.api.apisearch.param.APIEnvSettings;
import com.ai.opt.sol.api.apisearch.param.APIOwnerType;
import com.ai.opt.sol.api.apisearch.param.APISearchKey;
import com.ai.opt.sol.api.apisearch.param.APISearchResult;
import com.ai.runner.base.exception.CallerException;
import com.ai.runner.base.vo.PageInfo;

//@Path("APISearch")
//@Consumes({ MediaType.APPLICATION_JSON })
//@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IAPISearchSV {

    /**
     * 获取在线网站API的信息
     * 
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    String getAPIStatistics() throws CallerException;

    /**
     * 获取所有服务的归属类型列表
     * 
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    List<APIOwnerType> getAPIOwnerTypes() throws CallerException;

    /**
     * 服务API综合查询
     * 
     * @param searchKey
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    PageInfo<APISearchResult> searchAPIDocs(APISearchKey searchKey) throws CallerException;

    /**
     * 获取服务版本历史
     * 
     * @param apiDetailIndexId
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    String getAPIVersionHistory(String apiDetailIndexId) throws CallerException;

    String getAPIVersionNew(String apiDetailIndexId) throws CallerException;

    /**
     * 根据参数类索引ID获取API参数类的属性列表
     * 
     * @param apiParamIndexId
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    String getAPIClassDetail(String apiParamIndexId) throws CallerException;

    /**
     * 根据索引删除API信息
     * 
     * @param indexId
     * @throws CallerException
     * @author zhangchao
     * @ApiDocMethod
     */
    void deleteAPINew(String indexId) throws CallerException;

    /**
     * 下载某提供者的API数据
     * 
     * @param ownerType
     * @param owner
     * @return
     * @throws CallerException
     * @author zhangchao
     * @ApiDocMethod
     */
    String downloadAPIs(String ownerType, String owner) throws CallerException;

    /**
     * 下载单个接口的数据
     * 
     * @param ownerType
     * @param owner
     * @param artifactId
     * @param interfaceName
     * @param method
     * @return
     * @throws CallerException
     * @author zhangchao
     * @ApiDocMethod
     */
    String downloadAPI(String ownerType, String owner, String artifactId, String interfaceName,
                       String method) throws CallerException;

    /**
     * 保存API调用者设置
     * 
     * @param envSettings
     * @throws CallerException
     * @author zhangchao
     * @ApiDocMethod
     */
    void saveAPIEnvSettings(APIEnvSettings envSettings) throws CallerException;

    /**
     * 获取指定的服务提供者的环境设置
     * 
     * @param ownerType
     * @param owner
     * @return
     * @throws Exception
     * @author zhangchao
     * @ApiDocMethod
     */
    List<APIEnvSettings> getAPIEnvSettings(String ownerType, String owner) throws CallerException;

    /**
     * 获取单个环境设置信息
     * 
     * @param settingId
     * @return
     * @throws CallerException
     * @author zhangchao
     * @ApiDocMethod
     */
    APIEnvSettings getAPIEnvSetting(String settingId) throws CallerException;

    /**
     * 获取所有服务提供者环境设置列表
     * 
     * @return
     * @throws CallerException
     * @author zhangchao
     * @ApiDocMethod
     */
    List<APIEnvSettings> getAllAPIEnvSettings() throws CallerException;

}
