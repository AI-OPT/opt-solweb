package com.ai.opt.sol.core.api.apisearch.impl;

import java.util.*;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sol.api.apisearch.IAPISearchSV;
import com.ai.opt.sol.api.apisearch.param.*;
import com.ai.opt.sol.core.mapper.bo.ApiCallSetting;
import com.ai.opt.sol.core.mapper.bo.ApiCallSettingReq;
import com.ai.opt.sol.core.mapper.bo.ApiEnvSettings;
import com.ai.opt.sol.core.mapper.bo.ApiEnvSettingsCriteria;
import com.ai.opt.sol.core.model.APIDownloadFile;
import com.ai.opt.sol.core.model.APIReqParamTemplate;
import com.ai.opt.sol.core.model.APITemplate;
import com.ai.opt.sol.core.service.atom.interfaces.IApiCallSettingAtomSV;
import com.ai.opt.sol.core.service.atom.interfaces.IApiCallSettingReqAtomSV;
import com.ai.opt.sol.core.service.atom.interfaces.IApiEnvSettingsAtomSV;
import com.ai.opt.sol.core.util.APIESProcessor;
import com.ai.opt.sol.core.util.APISearchUtil;
import com.ai.paas.ipaas.dbs.util.CollectionUtil;
import com.ai.runner.apicollector.util.ElasticSearchHandler;
import com.ai.runner.apicollector.vo.APIClassDoc;
import com.ai.runner.apicollector.vo.APIDoc;
import com.ai.runner.apicollector.vo.ElasticIndex;
import com.ai.runner.apicollector.vo.ElasticType;
import com.ai.runner.base.exception.CallerException;
import com.ai.runner.base.exception.SystemException;
import com.ai.runner.base.vo.PageInfo;
import com.ai.runner.utils.constants.ExceptCodeConstants;
import com.ai.runner.utils.util.BeanUtils;
import com.ai.runner.utils.util.StringUtil;
import com.ai.runner.utils.util.UUIDUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

//@Service
@Component
public class APISearchSVImpl implements IAPISearchSV {

    @Autowired
    IApiCallSettingAtomSV iApiCallSettingAtomSV;

    @Autowired
    IApiCallSettingReqAtomSV iApiCallSettingReqAtomSV;

    @Autowired
    IApiEnvSettingsAtomSV iApiEnvSettingsAtomSV;

    @Override
    public List<APIOwnerType> getAPIOwnerTypes() throws CallerException {
        List<APIOwnerType> list = APISearchUtil.getAPIOwnerTypes();
        if (!CollectionUtil.isEmpty(list)) {
            for (APIOwnerType ownerType : list) {
                List<APIOwnerStatistics> result = APISearchUtil.getAPIOwnerStatistics(ownerType
                        .getOwnerType());

                int serviceCount = 0;
                Set<String> ownerSet = new HashSet<String>();
                if (!CollectionUtil.isEmpty(result)) {
                    for (APIOwnerStatistics stat : result) {
                        ownerSet.add(stat.getOwner());
                        serviceCount += stat.getApiCount();
                    }
                }
                ownerType.setServiceCount(serviceCount);
                ownerType.setOwnerCount(ownerSet.size());
                ownerType.setOwnerStatistics(result);
            }
        }
        return list;
    }

    @Override
    public PageInfo<APISearchResult> searchAPIDocs(APISearchKey searchKey) throws CallerException {
        if (searchKey == null) {
            throw new SystemException("查询服务出错：参数为空");
        }
        PageInfo<APISearchResult> pageInfo = searchKey.getPageInfo();
        if (pageInfo == null) {
            throw new SystemException("查询服务出错：分页参数为空");
        }
        ElasticSearchHandler searcher = APIESProcessor.getElasticSearchHandler();
        SearchResponse response = APISearchUtil.searchAPIsHighLight(searchKey,
                pageInfo.getStartRowIndex() - 1, pageInfo.getEndRowIndex());
        SearchHits hits = response.getHits();
        // 获取总数
        long total = hits.getTotalHits();
        List<APISearchResult> list = new ArrayList<APISearchResult>();
        for (SearchHit hit : hits) {
            String sourceString = hit.getSourceAsString();
            APIDoc source = JSON.parseObject(sourceString, APIDoc.class);
            Map<String, HighlightField> hightLightFields = hit.getHighlightFields();
            APISearchResult bo = new APISearchResult();
            BeanUtils.copyProperties(bo, source);

            String apiCodeHighlight = searcher.getHightLightFieldValue(hightLightFields, "apiCode");
            String interfaceNameHighlight = searcher.getHightLightFieldValue(hightLightFields,
                    "interfaceName");
            String methodNameHighlight = searcher.getHightLightFieldValue(hightLightFields,
                    "methodName");
            String ownerHighlight = searcher.getHightLightFieldValue(hightLightFields, "owner");
            String groupIdHighlight = searcher.getHightLightFieldValue(hightLightFields, "groupId");
            String artifactIdHighlight = searcher.getHightLightFieldValue(hightLightFields,
                    "artifactId");
            String briefCommentHighlight = searcher.getHightLightFieldValue(hightLightFields,
                    "briefComment");
            String detailCommentHighlight = searcher.getHightLightFieldValue(hightLightFields,
                    "detailComment");
            String authorHighlight = searcher.getHightLightFieldValue(hightLightFields, "author");
            String restRelativeURLHighlight = searcher.getHightLightFieldValue(hightLightFields,
                    "restRelativeURL");

            bo.setRestRelativeURLHighlight(StringUtil.isBlank(restRelativeURLHighlight) ? bo
                    .getRestRelativeURL() : restRelativeURLHighlight);
            bo.setApiCodeHighlight(StringUtil.isBlank(apiCodeHighlight) ? bo.getApiCode()
                    : apiCodeHighlight);
            bo.setInterfaceNameHighlight(StringUtil.isBlank(interfaceNameHighlight) ? bo
                    .getInterfaceName() : interfaceNameHighlight);
            bo.setMethodNameHighlight(StringUtil.isBlank(methodNameHighlight) ? bo.getMethodName()
                    : methodNameHighlight);
            bo.setOwnerHighlight(StringUtil.isBlank(ownerHighlight) ? bo.getOwner()
                    : ownerHighlight);
            bo.setGroupIdHighlight(StringUtil.isBlank(groupIdHighlight) ? bo.getGroupId()
                    : groupIdHighlight);
            bo.setArtifactIdHighlight(StringUtil.isBlank(artifactIdHighlight) ? bo.getArtifactId()
                    : artifactIdHighlight);
            bo.setBriefCommentHighlight(StringUtil.isBlank(briefCommentHighlight) ? bo
                    .getBriefComment() : briefCommentHighlight);
            bo.setDetailCommentHighlight(StringUtil.isBlank(detailCommentHighlight) ? bo
                    .getDetailComment() : detailCommentHighlight);
            bo.setAuthorHighlight(StringUtil.isBlank(authorHighlight) ? bo.getAuthor()
                    : authorHighlight);
            boolean isSetted = checkAPISetting(bo.getOwner(), bo.getInterfaceName(),
                    bo.getMethodName());
            bo.setSetted(isSetted);
            // 获取API的所有版本信息
            List<APIVersion> versions = APISearchUtil.getAPIAllVersions(bo.getOwnerType(),
                    bo.getOwner(), bo.getInterfaceName(), bo.getMethodName());
            bo.setApiVersions(versions);
            list.add(bo);

        }
        pageInfo.setCount(Integer.parseInt(StringUtil.toString(total)));
        pageInfo.setPageNo(pageInfo.getPageNo());
        pageInfo.setPageSize(pageInfo.getPageSize());
        pageInfo.setPageCount(pageInfo.getPageCount());
        pageInfo.setResult(list);
        return pageInfo;
    }

    /**
     * 判断API是否已经设置过了
     * 
     * @param interfaceName
     * @param method
     * @return
     * @author zhangchao
     */
    private boolean checkAPISetting(String owner, String interfaceName, String method) {
        ApiCallSetting sets = iApiCallSettingAtomSV.getApiCallSetting(owner, interfaceName, method);
        return !(sets == null);
    }

    @Override
    public String getAPIVersionHistory(String apiDetailIndexId) throws CallerException {
        if (StringUtil.isBlank(apiDetailIndexId)) {
            throw new SystemException("获取API详细错误:缺少索引ID");
        }
        return APISearchUtil.getAPIVersionHistory(apiDetailIndexId);
    }

    @Override
    public String getAPIVersionNew(String apiDetailIndexId) throws CallerException {
        if (StringUtil.isBlank(apiDetailIndexId)) {
            throw new SystemException("获取API详细错误:缺少索引ID");
        }
        return APISearchUtil.getAPIVersionNew(apiDetailIndexId);
    }

    @Override
    public String getAPIClassDetail(String apiParamIndexId) throws CallerException {
        if (StringUtil.isBlank(apiParamIndexId)) {
            throw new SystemException("获取API参数类的属性列表错误:缺少参数类索引ID");
        }
        return APISearchUtil.getAPIClassDetail(apiParamIndexId);
    }

    @Override
    public String getAPIStatistics() throws CallerException {
        int ownerTypeCount = APISearchUtil.getAPIOwnerTypeCount();
        int ownerCount = APISearchUtil.getAPIOwnerCount();
        long apiCount = APISearchUtil.getAPICount();
        JSONObject data = new JSONObject();
        data.put("ownerTypeCount", ownerTypeCount);
        data.put("ownerCount", ownerCount);
        data.put("apiCount", apiCount);
        return data.toString();
    }

    @Override
    public void deleteAPINew(String indexId) throws CallerException {
        if (StringUtil.isBlank(indexId)) {
            throw new SystemException("作废服务失败：没有传入索引信息");
        }
        Client client = APIESProcessor.getElasticSearchHandler().getClient();
        client.prepareDelete(ElasticIndex.API.name().toLowerCase(),
                ElasticType.API_VERSION_NEW.name().toLowerCase(), indexId).execute().actionGet();
    }

    @Override
    public String downloadAPIs(String ownerType, String owner) throws CallerException {
        if (StringUtil.isBlank(ownerType)) {
            throw new SystemException("提供者类型不能为空");
        }
        if (StringUtil.isBlank(owner)) {
            throw new SystemException("提供者不能为空");
        }
        APIDownloadFile file = new APIDownloadFile();
        List<APIDoc> apiVersionNews = APISearchUtil.getAPIVersionNewDocs(ownerType, owner, null,
                null);
        List<APIClassDoc> apiClassDetails = APISearchUtil.getAPIsClassDocData(ownerType, owner,
                null);
        List<APITemplate> apiTemplates = this.getAPITemplates(owner);
        file.setApiClassDetails(apiClassDetails);
        file.setApiTemplates(apiTemplates);
        file.setApiVersionNews(apiVersionNews);
        return JSON.toJSONString(file);
    }

    @Override
    public String downloadAPI(String ownerType, String owner, String artifactId,
            String interfaceName, String method) throws CallerException {
        if (StringUtil.isBlank(ownerType)) {
            throw new SystemException("提供者类型不能为空");
        }
        if (StringUtil.isBlank(owner)) {
            throw new SystemException("提供者不能为空");
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
        APIDownloadFile file = new APIDownloadFile();
        List<APIDoc> apiVersionNews = APISearchUtil.getAPIVersionNewDocs(ownerType, owner,
                interfaceName, method);
        List<APIClassDoc> apiClassDetails = APISearchUtil.getAPIsClassDocData(ownerType, owner,
                artifactId);
        List<APITemplate> apiTemplates = this.getAPITemplates(owner, interfaceName, method);
        file.setApiClassDetails(apiClassDetails);
        file.setApiTemplates(apiTemplates);
        file.setApiVersionNews(apiVersionNews);
        return JSON.toJSONString(file);
    }

    private List<APITemplate> getAPITemplates(String owner) {
        List<ApiCallSetting> settings = iApiCallSettingAtomSV.getApiCallSettings(owner);
        List<APITemplate> templates = new ArrayList<APITemplate>();
        if (!CollectionUtil.isEmpty(settings)) {
            for (ApiCallSetting s : settings) {
                List<APIReqParamTemplate> reqParamTemplates = this.getAPIReqParamTemplates(
                        s.getInterfaceName(), s.getMethod());
                APITemplate t = new APITemplate();
                int id = (s.getInterfaceName() + "." + s.getMethod()).hashCode();
                t.setId(id + "");
                t.setInterfaceName(s.getInterfaceName());
                t.setMethod(s.getMethod());
                t.setOwner(owner);
                t.setReturnComment(s.getReturnComment());
                t.setReturnJavaType(s.getReturnJavaType());
                t.setReturnJson(s.getReturnJson());
                t.setReqParamTemplates(reqParamTemplates);
                templates.add(t);
            }
        }
        return templates;
    }

    private List<APITemplate> getAPITemplates(String owner, String interfaceName, String method) {
        ApiCallSetting s = iApiCallSettingAtomSV.getApiCallSetting(owner, interfaceName, method);
        List<APITemplate> templates = new ArrayList<APITemplate>();
        if (s != null) {
            List<APIReqParamTemplate> reqParamTemplates = this.getAPIReqParamTemplates(
                    s.getInterfaceName(), s.getMethod());
            APITemplate t = new APITemplate();
            t.setInterfaceName(s.getInterfaceName());
            t.setMethod(s.getMethod());
            t.setOwner(owner);
            t.setReturnComment(s.getReturnComment());
            t.setReturnJavaType(s.getReturnJavaType());
            t.setReturnJson(s.getReturnJson());
            t.setReqParamTemplates(reqParamTemplates);
            templates.add(t);
        }
        return templates;
    }

    private List<APIReqParamTemplate> getAPIReqParamTemplates(String interfaceName, String method) {
        List<ApiCallSettingReq> reqs = iApiCallSettingReqAtomSV.getApiCallSettingReqs(
                interfaceName, method);
        List<APIReqParamTemplate> ts = new ArrayList<APIReqParamTemplate>();
        if (!CollectionUtil.isEmpty(reqs)) {
            for (ApiCallSettingReq r : reqs) {
                APIReqParamTemplate t = new APIReqParamTemplate();
                t.setJavaType(r.getJavaType());
                t.setJson(r.getJson());
                t.setParamName(r.getParamName());
                ts.add(t);
            }
        }
        return ts;
    }

    @Override
    public void saveAPIEnvSettings(APIEnvSettings envSettings) throws CallerException {
        if (envSettings == null) {
            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "API环境信息不能为空");
        }
        if (StringUtil.isBlank(envSettings.getOwnertype())) {
            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "API提供者类型不能为空");
        }
        if (StringUtil.isBlank(envSettings.getOwner())) {
            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "API提供者不能为空");
        }
        if (StringUtil.isBlank(envSettings.getEnv())) {
            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "环境标识不能为空");
        }
        if (StringUtil.isBlank(envSettings.getZkcenter())) {
            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "注册中心地址不能为空");
        }
        ApiEnvSettings record = new ApiEnvSettings();
        record.setEnv(envSettings.getEnv());
        record.setOwner(envSettings.getOwner());
        record.setOwnertype(envSettings.getOwnertype());
        record.setResthttp(envSettings.getResthttp());
        record.setZkcenter(envSettings.getZkcenter());
        record.setMonitor(envSettings.getMonitor());
        if (StringUtil.isBlank(envSettings.getSettingsId())) {
            record.setSettingsId(UUIDUtil.genId32());
            iApiEnvSettingsAtomSV.insert(record);
        } else {
            record.setSettingsId(envSettings.getSettingsId());
            iApiEnvSettingsAtomSV.updateByPrimaryKeySelective(record);
        }

    }

    @Override
    public List<APIEnvSettings> getAPIEnvSettings(String ownerType, String owner)
            throws CallerException {
        if (StringUtil.isBlank(ownerType)) {
            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "提供者类型不能为空");
        }
        if (StringUtil.isBlank(owner)) {
            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "提供者不能为空");
        }
        ApiEnvSettingsCriteria sql = new ApiEnvSettingsCriteria();
        sql.or().andOwnerEqualTo(owner).andOwnertypeEqualTo(ownerType);
        List<ApiEnvSettings> list = iApiEnvSettingsAtomSV.selectByExample(sql);
        List<APIEnvSettings> l = new ArrayList<APIEnvSettings>();
        if (!CollectionUtil.isEmpty(list)) {
            for (ApiEnvSettings o : list) {
                APIEnvSettings b = new APIEnvSettings();
                b.setEnv(o.getEnv());
                b.setOwner(o.getOwner());
                b.setOwnertype(o.getOwnertype());
                b.setResthttp(o.getResthttp());
                b.setSettingsId(o.getSettingsId());
                b.setZkcenter(o.getZkcenter());
                b.setMonitor(o.getMonitor());
                l.add(b);
            }
        }
        return l;
    }

    @Override
    public List<APIEnvSettings> getAllAPIEnvSettings() throws CallerException {
        ApiEnvSettingsCriteria sql = new ApiEnvSettingsCriteria();
        List<ApiEnvSettings> list = iApiEnvSettingsAtomSV.selectByExample(sql);
        List<APIEnvSettings> l = new ArrayList<APIEnvSettings>();
        if (!CollectionUtil.isEmpty(list)) {
            for (ApiEnvSettings o : list) {
                APIEnvSettings b = new APIEnvSettings();
                b.setEnv(o.getEnv());
                b.setOwner(o.getOwner());
                b.setOwnertype(o.getOwnertype());
                b.setResthttp(o.getResthttp());
                b.setSettingsId(o.getSettingsId());
                b.setZkcenter(o.getZkcenter());
                b.setMonitor(o.getMonitor());
                l.add(b);
            }
        }
        return l;
    }

    @Override
    public APIEnvSettings getAPIEnvSetting(String settingId) throws CallerException {
        if (StringUtil.isBlank(settingId)) {
            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "设置ID不能为空");
        }
        ApiEnvSettingsCriteria sql = new ApiEnvSettingsCriteria();
        sql.or().andSettingsIdEqualTo(settingId);
        List<ApiEnvSettings> list = iApiEnvSettingsAtomSV.selectByExample(sql);
        List<APIEnvSettings> l = new ArrayList<APIEnvSettings>();
        if (!CollectionUtil.isEmpty(list)) {
            for (ApiEnvSettings o : list) {
                APIEnvSettings b = new APIEnvSettings();
                b.setEnv(o.getEnv());
                b.setOwner(o.getOwner());
                b.setOwnertype(o.getOwnertype());
                b.setResthttp(o.getResthttp());
                b.setSettingsId(o.getSettingsId());
                b.setZkcenter(o.getZkcenter());
                b.setMonitor(o.getMonitor());
                l.add(b);
            }
        }
        return l.size() == 0 ? null : l.get(0);
    }

}
