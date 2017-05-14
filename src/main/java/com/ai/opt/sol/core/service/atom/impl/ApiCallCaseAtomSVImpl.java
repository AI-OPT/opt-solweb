package com.ai.opt.sol.core.service.atom.impl;

import java.util.List;

import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sol.core.mapper.interfaces.ApiCallCaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sol.api.sandbox.param.APICallCaseQuery;
import com.ai.opt.sol.core.mapper.bo.ApiCallCase;
import com.ai.opt.sol.core.mapper.bo.ApiCallCaseCriteria;
import com.ai.opt.sol.core.mapper.bo.ApiCallCaseCriteria.Criteria;
import com.ai.opt.sol.core.service.atom.interfaces.IApiCallCaseAtomSV;

@Component
public class ApiCallCaseAtomSVImpl implements IApiCallCaseAtomSV {

    @Autowired
    ApiCallCaseMapper apiCallCaseMapper;
    @Override
    public void add(ApiCallCase record) {
        apiCallCaseMapper.insert(record);
    }

    @Override
    public void update(ApiCallCase record) {
        apiCallCaseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ApiCallCase getApiCallCase(String caseId) {
        return apiCallCaseMapper.selectByPrimaryKey(caseId);
    }

    @Override
    public List<ApiCallCase> getApiCallCases(String interfaceName, String method) {
        ApiCallCaseCriteria sql = new ApiCallCaseCriteria();
        sql.setOrderByClause(" test_time desc");
        sql.or().andInterfaceNameEqualTo(interfaceName).andMethodEqualTo(method);
        
        return apiCallCaseMapper.selectByExample(sql);
    }

    @Override
    public int getCount(APICallCaseQuery query) {
        ApiCallCaseCriteria sql = new ApiCallCaseCriteria(); 
        Criteria criteria =sql.or();
        if(!StringUtil.isBlank(query.getInterfaceName())){
            criteria.andInterfaceNameEqualTo(query.getInterfaceName());
        }
        if(!StringUtil.isBlank(query.getMethod())){
            criteria.andMethodEqualTo(query.getMethod());
        }
        if(!StringUtil.isBlank(query.getTester())){
            criteria.andTesterLike("%"+query.getTester()+"%");
        }
        if(!StringUtil.isBlank(query.getCaseTag())){
            criteria.andCaseTagLike("%"+query.getCaseTag()+"%");
        }
        return apiCallCaseMapper.countByExample(sql);
    }

    @Override
    public List<ApiCallCase> getApiCallCases(APICallCaseQuery query) {
        ApiCallCaseCriteria sql = new ApiCallCaseCriteria();
        sql.setOrderByClause(" test_time desc");
        Criteria criteria =sql.or();
        if(!StringUtil.isBlank(query.getInterfaceName())){
            criteria.andInterfaceNameEqualTo(query.getInterfaceName());
        }
        if(!StringUtil.isBlank(query.getMethod())){
            criteria.andMethodEqualTo(query.getMethod());
        }
        if(!StringUtil.isBlank(query.getTester())){
            criteria.andTesterLike("%"+query.getTester()+"%");
        }
        if(!StringUtil.isBlank(query.getCaseTag())){
            criteria.andCaseTagLike("%"+query.getCaseTag()+"%");
        }
        sql.setLimitStart(query.getPageInfo().getStartRowIndex()-1);
        sql.setLimitEnd(query.getPageInfo().getPageSize());
        return apiCallCaseMapper.selectByExample(sql);
    }

}
