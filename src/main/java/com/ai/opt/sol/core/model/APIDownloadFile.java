package com.ai.opt.sol.core.model;

import java.util.List;

import com.ai.runner.apicollector.vo.APIClassDoc;
import com.ai.runner.apicollector.vo.APIDoc;

public class APIDownloadFile {
    
    //API最新版本数据
    private List<APIDoc> apiVersionNews;
    
    //API的参数类数据
    private List<APIClassDoc> apiClassDetails;
    
    //API的模板数据
    private List<APITemplate> apiTemplates;

    public List<APIDoc> getApiVersionNews() {
        return apiVersionNews;
    }

    public void setApiVersionNews(List<APIDoc> apiVersionNews) {
        this.apiVersionNews = apiVersionNews;
    }

    public List<APIClassDoc> getApiClassDetails() {
        return apiClassDetails;
    }

    public void setApiClassDetails(List<APIClassDoc> apiClassDetails) {
        this.apiClassDetails = apiClassDetails;
    }

    public List<APITemplate> getApiTemplates() {
        return apiTemplates;
    }

    public void setApiTemplates(List<APITemplate> apiTemplates) {
        this.apiTemplates = apiTemplates;
    }
    
    

}
