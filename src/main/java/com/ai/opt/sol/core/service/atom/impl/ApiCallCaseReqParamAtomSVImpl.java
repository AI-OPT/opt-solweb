package com.ai.opt.sol.core.service.atom.impl;

import java.util.List;

import com.ai.opt.sol.core.mapper.interfaces.ApiCallCaseReqParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sol.core.mapper.bo.ApiCallCaseReqParam;
import com.ai.opt.sol.core.mapper.bo.ApiCallCaseReqParamCriteria;
import com.ai.opt.sol.core.mapper.factory.MapperFactory;
import com.ai.opt.sol.core.service.atom.interfaces.IApiCallCaseReqParamAtomSV;

@Component
public class ApiCallCaseReqParamAtomSVImpl implements IApiCallCaseReqParamAtomSV {

    @Autowired
    ApiCallCaseReqParamMapper apiCallCaseReqParamMapper;

    @Override
    public void add(ApiCallCaseReqParam record) {
        apiCallCaseReqParamMapper.insert(record);
    }

    @Override
    public List<ApiCallCaseReqParam> getApiCallCaseReqParams(String caseId) {
        ApiCallCaseReqParamCriteria sql = new ApiCallCaseReqParamCriteria();
        sql.setOrderByClause(" sort asc");
        sql.or().andCaseIdEqualTo(caseId);
        return apiCallCaseReqParamMapper.selectByExample(sql);
    }

}
