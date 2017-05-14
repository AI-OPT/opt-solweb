package com.ai.opt.sol.core.util;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.ai.opt.sol.api.apisearch.param.APIOwnerStatistics;
import com.ai.opt.sol.api.apisearch.param.APIOwnerType;
import com.ai.opt.sol.api.apisearch.param.APISearchKey;
import com.ai.opt.sol.api.apisearch.param.APIVersion;
import com.ai.paas.ipaas.dbs.util.CollectionUtil;
import com.ai.runner.apicollector.vo.*;
import com.ai.runner.base.exception.CallerException;
import com.ai.runner.base.exception.SystemException;
import com.alibaba.fastjson.JSONObject;

/**
 * DUBBO-API查询类<br>
 * Date: 2015年10月14日 <br>
 * Copyright (c) 2015 asiainfo.com <br>
 * 
 * @author zhangchao
 */
public class APISearchUtil {

    /**
     * 获取总的服务提供者分类数量
     * 
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static int getAPIOwnerTypeCount() {
        List<APIOwnerType> list = getAPIOwnerTypes();
        return list.size();
    }

    /**
     * 获取服务提供者的数量
     * 
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static int getAPIOwnerCount() {
        List<APIOwnerDoc> ownerDoclist = getAPIOwnerDocs(null);
        Map<String, String> ownerMap = new HashMap<String, String>();
        for (APIOwnerDoc doc : ownerDoclist) {
            ownerMap.put(doc.getOwner(), doc.getOwner());
        }
        return ownerMap.size();
    }

    /**
     * 获取提供的服务总数
     * 
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static long getAPICount() {
        Client client = APIESProcessor.getElasticSearchHandler().getClient();
        CountResponse countResponse = client.prepareCount(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.API_VERSION_NEW.name().toLowerCase()).execute().actionGet();
        long count = countResponse.getCount();
        return count;
    }

    /**
     * 从搜索引擎获取所有的服务归属类型
     * 
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static List<APIOwnerType> getAPIOwnerTypes() {
        List<APIOwnerDoc> ownerDoclist = getAPIOwnerDocs(null);
        Map<String, String> ownerTypeMap = new HashMap<String, String>();
        for (APIOwnerDoc doc : ownerDoclist) {
            ownerTypeMap.put(doc.getOwnerType(), doc.getOwnerType());
        }
        List<APIOwnerType> list = new ArrayList<APIOwnerType>();
        for (String ownerType : ownerTypeMap.keySet()) {
            APIOwnerType t = new APIOwnerType();
            t.setOwnerType(ownerType);
            t.setOwnerTypeName(ownerType);
            list.add(t);
        }
        return list;
    }

    /**
     * 按照服务提供者类型统计其下面各个提供者包含的服务数量
     * 
     * @param ownerType
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static List<APIOwnerStatistics> getAPIOwnerStatistics(String ownerType) {
        String[] colors = new String[] { "bg-aqua", "bg-green", "bg-yellow", "bg-red", "bg-purple",
                "bg-blue", "bg-light-blue", "bg-navy", "bg-teal", "bg-olive", "bg-lime",
                "bg-orange", "bg-maroon" };
        List<APIOwnerStatistics> list = new ArrayList<APIOwnerStatistics>();
        List<APIOwnerDoc> ownerDoclist = getAPIOwnerDocs(ownerType);
        int i = 0;
        for (APIOwnerDoc ownerDoc : ownerDoclist) {
            String owner = ownerDoc.getOwner();
            List<APIDoc> apiList = getAPIVersionNewDocs(ownerType, owner, null, null);
            int apiCount = apiList.size();
            APIOwnerStatistics bo = new APIOwnerStatistics();
            bo.setApiCount(apiCount);
            bo.setOwner(owner);
            bo.setOwnerName(owner);
            bo.setOwnerType(ownerType);
            if (i < 13) {
                bo.setColor(colors[i]);
                i++;
            } else {
                bo.setColor(colors[3]);
                i = 0;
            }
            list.add(bo);
        }
        return list;
    }

    /**
     * 服务综合搜索
     *
     * @param searchKey
     * @param start
     * @param end
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static SearchResponse searchAPIsHighLight(APISearchKey searchKey, int start, int end) {
        Client client = APIESProcessor.getElasticSearchHandler().getClient();
        BoolQueryBuilder builder = boolQuery();
        String ownerType = searchKey.getOwnerType();
        String owner = searchKey.getOwner();
        String keywords = searchKey.getKeywords();
        String limitException = searchKey.getLimitException();
        if (!StringUtils.isBlank(ownerType)) {
            builder.must(termQuery("ownerType", ownerType.toLowerCase()));
        }
        if (!StringUtils.isBlank(owner)) {
            builder.must(termQuery("owner", owner.toLowerCase()));
        }
        if (!StringUtils.isBlank(keywords)) {
            builder.must(queryStringQuery(keywords));
        }
        if (!StringUtils.isBlank(limitException)) {
            if ("1".equals(limitException)) {
                builder.mustNot(QueryBuilders.matchQuery("exceptions",
                        CallerException.class.getName()));
            } else if ("0".equals(limitException)) {
                builder.must(QueryBuilders.matchQuery("exceptions", CallerException.class.getName()));
            }
        }
        SearchResponse response = client.prepareSearch(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.API_VERSION_NEW.name().toLowerCase()).setQuery(builder)
                .addHighlightedField("apiCode").addHighlightedField("interfaceName")
                .addHighlightedField("methodName").addHighlightedField("owner")
                .addHighlightedField("groupId").addHighlightedField("artifactId")
                .addHighlightedField("briefComment").addHighlightedField("detailComment")
                .addHighlightedField("author").addHighlightedField("restRelativeURL")
                .setHighlighterPreTags("<span style=\"color:red\"><b>")
                .setHighlighterPostTags("</b></span>").setFrom(start).setSize(end - start)
                .execute().actionGet();
        return response;
    }

    /**
     * 获取接口的版本列表
     * 
     * @param ownerType
     * @param owner
     * @param className
     * @param methodName
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static List<APIVersion> getAPIAllVersions(String ownerType, String owner,
            String className, String methodName) {
        List<APIDoc> doclist = APISearchUtil.getAPIVersionHistoryDocs(ownerType, owner, className,
                methodName);
        List<APIVersion> list = new ArrayList<APIVersion>();
        for (APIDoc doc : doclist) {
            APIVersion bo = new APIVersion();
            bo.setApiDetailIndexId(objToString(doc.getId()));
            bo.setVersion(doc.getVersion());
            list.add(bo);
        }
        return list;
    }

    /**
     * 获取API详细信息
     * 
     * @param apiDetailIndexId
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static String getAPIVersionHistory(String apiDetailIndexId) {
        Client client = APIESProcessor.getElasticSearchHandler().getClient();
        SearchResponse response = client.prepareSearch(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.API_VERSION_HISTORY.name().toLowerCase())
                .setQuery(termQuery("_id", apiDetailIndexId)).execute().actionGet();
        if (response.getHits().getTotalHits() == 0) {
            return null;
        }
        return response.getHits().getHits()[0].getSourceAsString();
    }

    /**
     * 获取API详细信息
     * 
     * @param apiDetailIndexId
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static String getAPIVersionNew(String apiDetailIndexId) {
        Client client = APIESProcessor.getElasticSearchHandler().getClient();
        SearchResponse response = client.prepareSearch(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.API_VERSION_NEW.name().toLowerCase())
                .setQuery(termQuery("_id", apiDetailIndexId)).execute().actionGet();
        if (response.getHits().getTotalHits() == 0) {
            return null;
        }
        return response.getHits().getHits()[0].getSourceAsString();
    }

    /**
     * 获取参数类的详细信息
     * 
     * @param apiParamIndexId
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static String getAPIClassDetail(String apiParamIndexId) {
        Client client = APIESProcessor.getElasticSearchHandler().getClient();
        SearchResponse response = client.prepareSearch(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.CLASS_DETAIL.name().toLowerCase())
                .setQuery(termQuery("_id", apiParamIndexId)).execute().actionGet();
        if (response.getHits().getTotalHits() == 0) {
            return null;
        }
        return response.getHits().getHits()[0].getSourceAsString();
    }

    /**
     * 获取指定服务提供者类型的索引信息
     * 
     * @param ownerType
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static List<APIOwnerDoc> getAPIOwnerDocs(String ownerType) {
        Client client = APIESProcessor.getElasticSearchHandler().getClient();
        BoolQueryBuilder builder = boolQuery();
        if (!StringUtils.isBlank(ownerType)) {
            builder.must(termQuery("ownerType", ownerType.toLowerCase()));
        }
        CountResponse countResponse = client.prepareCount(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.API_OWNER.name().toLowerCase()).setQuery(builder).execute()
                .actionGet();
        long count = countResponse.getCount();
        /* 2.获取所有的API_OWNER信息 */
        SearchResponse response = client.prepareSearch(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.API_OWNER.name().toLowerCase()).setFrom(0)
                .setSize(Integer.parseInt(objToString(count))).setQuery(builder).execute()
                .actionGet();
        List<APIOwnerDoc> list = new ArrayList<APIOwnerDoc>();
        for (SearchHit hit : response.getHits()) {
            APIOwnerDoc doc = JSONObject.parseObject(hit.getSourceAsString(), APIOwnerDoc.class);
            list.add(doc);
        }
        return list;
    }

    /**
     * 获取API_VERSION_HISTORY信息
     * 
     * @param ownerType
     * @param owner
     * @param interfaceName
     * @param methodName
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    private static List<APIDoc> getAPIVersionHistoryDocs(String ownerType, String owner,
            String interfaceName, String methodName) {
        Client client = APIESProcessor.getElasticSearchHandler().getClient();
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if (!StringUtils.isBlank(ownerType)) {
            builder.must(QueryBuilders.termQuery("ownerType", ownerType.toLowerCase()));
        }
        if (!StringUtils.isBlank(owner)) {
            builder.must(QueryBuilders.termQuery("owner", owner.toLowerCase()));
        }
        if (!StringUtils.isBlank(interfaceName)) {
            builder.must(QueryBuilders.termQuery("interfaceName", interfaceName.toLowerCase()));
        }
        if (!StringUtils.isBlank(methodName)) {
            builder.must(QueryBuilders.termQuery("methodName", methodName.toLowerCase()));
        }
        CountResponse countResponse = client.prepareCount(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.API_VERSION_HISTORY.name().toLowerCase()).setQuery(builder)
                .execute().actionGet();
        long count = countResponse.getCount();
        /* 2.获取所有的API_SUMMARY_VERSION信息 */
        SearchResponse response = client.prepareSearch(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.API_VERSION_HISTORY.name().toLowerCase()).setFrom(0)
                .setSize(Integer.parseInt(objToString(count))).setQuery(builder).execute()
                .actionGet();
        List<APIDoc> list = new ArrayList<APIDoc>();
        for (SearchHit hit : response.getHits()) {
            APIDoc doc = JSONObject.parseObject(hit.getSourceAsString(), APIDoc.class);
            list.add(doc);
        }
        return list;
    }

    public static List<APIDoc> getAPIVersionNewDocs(String ownerType, String owner,
            String interfaceName, String methodName) {
        Client client = APIESProcessor.getElasticSearchHandler().getClient();
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if (!StringUtils.isBlank(ownerType)) {
            builder.must(QueryBuilders.termQuery("ownerType", ownerType.toLowerCase()));
        }
        if (!StringUtils.isBlank(owner)) {
            builder.must(QueryBuilders.termQuery("owner", owner.toLowerCase()));
        }
        if (!StringUtils.isBlank(interfaceName)) {
            builder.must(QueryBuilders.termQuery("interfaceName", interfaceName.toLowerCase()));
        }
        if (!StringUtils.isBlank(methodName)) {
            builder.must(QueryBuilders.termQuery("methodName", methodName.toLowerCase()));
        }
        CountResponse countResponse = client.prepareCount(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.API_VERSION_NEW.name().toLowerCase()).setQuery(builder)
                .execute().actionGet();
        long count = countResponse.getCount();
        /* 2.获取所有的API_SUMMARY_VERSION信息 */
        SearchResponse response = client.prepareSearch(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.API_VERSION_NEW.name().toLowerCase()).setFrom(0)
                .setSize(Integer.parseInt(objToString(count))).setQuery(builder).execute()
                .actionGet();
        List<APIDoc> list = new ArrayList<APIDoc>();
        for (SearchHit hit : response.getHits()) {
            APIDoc doc = JSONObject.parseObject(hit.getSourceAsString(), APIDoc.class);
            list.add(doc);
        }
        return list;
    }

    /**
     * 获取API总数
     * 
     * @param ownerType
     * @param owner
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static long getAPICount(String ownerType, String owner) {
        Client client = APIESProcessor.getElasticSearchHandler().getClient();
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if (!StringUtils.isBlank(ownerType)) {
            builder.must(QueryBuilders.termQuery("ownerType", ownerType.toLowerCase()));
        }
        if (!StringUtils.isBlank(owner)) {
            builder.must(QueryBuilders.termQuery("owner", owner.toLowerCase()));
        }
        CountResponse countResponse = client.prepareCount(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.API_VERSION_NEW.name().toLowerCase()).setQuery(builder)
                .execute().actionGet();
        long count = countResponse.getCount();
        return count;
    }

    /**
     * 获取没有声明异常的API数量
     * 
     * @param ownerType
     * @param owner
     * @return
     * @author zhangchao
     */
    public static long getAPICountUnThrowsCallerException(String ownerType, String owner) {
        Client client = APIESProcessor.getElasticSearchHandler().getClient();
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if (!StringUtils.isBlank(ownerType)) {
            builder.must(QueryBuilders.termQuery("ownerType", ownerType.toLowerCase()));
        }
        if (!StringUtils.isBlank(owner)) {
            builder.must(QueryBuilders.termQuery("owner", owner.toLowerCase()));
        }
        builder.mustNot(QueryBuilders.matchQuery("exceptions", CallerException.class.getName()));
        CountResponse countResponse = client.prepareCount(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.API_VERSION_NEW.name().toLowerCase()).setQuery(builder)
                .execute().actionGet();
        long count = countResponse.getCount();
        return count;
    }

    /**
     * 获取最新版本的入参列表
     * 
     * @param ownerType
     * @param owner
     * @param interfaceName
     * @param methodName
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static APIParamDoc[] getAPIInAPIParamDocs(String ownerType, String owner,
            String interfaceName, String methodName) {
        List<APIDoc> list = getAPIVersionNewDocs(ownerType, owner, interfaceName, methodName);
        if (CollectionUtil.isEmpty(list)) {
            throw new SystemException("[" + interfaceName + "." + methodName + "]版本索引信息不存在");
        }
        APIDoc apiDoc = list.get(0);
        return apiDoc.getInAPIParamDocs();
    }

    /**
     * 获取一个参数索引信息
     * 
     * @param ownerType
     * @param owner
     * @param interfaceName
     * @param methodName
     * @param paramName
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static APIParamDoc getAPIInAPIParamDoc(String ownerType, String owner,
            String interfaceName, String methodName, String paramName) {
        APIParamDoc[] paramDocs = getAPIInAPIParamDocs(ownerType, owner, interfaceName, methodName);
        if (CollectionUtil.isEmpty(paramDocs)) {
            throw new SystemException("[" + interfaceName + "." + methodName + "]版本索引信息不包含任何参数");
        }
        APIParamDoc pDoc = null;
        for (APIParamDoc p : paramDocs) {
            if (p.getName().equals(paramName)) {
                pDoc = p;
                break;
            }
        }
        if (pDoc == null) {
            throw new SystemException("[" + interfaceName + "." + methodName + "]版本索引信息不包含参数["
                    + paramName + "]");
        }
        return pDoc;
    }

    /**
     * 获取最新版本的返回列表
     * 
     * @param ownerType
     * @param owner
     * @param interfaceName
     * @param methodName
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static APIParamDoc getAPIReturnAPIParamDocs(String ownerType, String owner,
            String interfaceName, String methodName) {
        List<APIDoc> list = getAPIVersionNewDocs(ownerType, owner, interfaceName, methodName);
        if (CollectionUtil.isEmpty(list)) {
            throw new SystemException("[" + interfaceName + "." + methodName + "]版本索引信息不存在");
        }
        APIDoc apiDoc = list.get(0);
        return apiDoc.getReturnAPIParamDoc();
    }

    public static List<APIClassDoc> getAPIsClassDocData(String ownerType, String owner,
            String artifactId) {
        Client client = APIESProcessor.getElasticSearchHandler().getClient();
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if (!StringUtils.isBlank(ownerType)) {
            builder.must(QueryBuilders.termQuery("ownerType", ownerType.toLowerCase()));
        }
        if (!StringUtils.isBlank(owner)) {
            builder.must(QueryBuilders.termQuery("owner", owner.toLowerCase()));
        }
        if (!StringUtils.isBlank(artifactId)) {
            builder.must(QueryBuilders.termQuery("artifactId", artifactId.toLowerCase()));
        }
        CountResponse countResponse = client.prepareCount(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.CLASS_DETAIL.name().toLowerCase()).setQuery(builder)
                .execute().actionGet();
        long count = countResponse.getCount();
        SearchResponse response = client.prepareSearch(ElasticIndex.API.name().toLowerCase())
                .setTypes(ElasticType.CLASS_DETAIL.name().toLowerCase()).setFrom(0)
                .setSize(Integer.parseInt(objToString(count))).setQuery(builder).execute()
                .actionGet();
        List<APIClassDoc> list = new ArrayList<APIClassDoc>();
        for (SearchHit hit : response.getHits()) {
            APIClassDoc doc = JSONObject.parseObject(hit.getSourceAsString(), APIClassDoc.class);
            list.add(doc);
        }
        return list;
    }

    private static String objToString(Object obj){
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }
}
