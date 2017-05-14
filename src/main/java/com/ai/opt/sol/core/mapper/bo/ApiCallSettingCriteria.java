package com.ai.opt.sol.core.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class ApiCallSettingCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ApiCallSettingCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSettingIdIsNull() {
            addCriterion("setting_id is null");
            return (Criteria) this;
        }

        public Criteria andSettingIdIsNotNull() {
            addCriterion("setting_id is not null");
            return (Criteria) this;
        }

        public Criteria andSettingIdEqualTo(String value) {
            addCriterion("setting_id =", value, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdNotEqualTo(String value) {
            addCriterion("setting_id <>", value, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdGreaterThan(String value) {
            addCriterion("setting_id >", value, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdGreaterThanOrEqualTo(String value) {
            addCriterion("setting_id >=", value, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdLessThan(String value) {
            addCriterion("setting_id <", value, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdLessThanOrEqualTo(String value) {
            addCriterion("setting_id <=", value, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdLike(String value) {
            addCriterion("setting_id like", value, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdNotLike(String value) {
            addCriterion("setting_id not like", value, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdIn(List<String> values) {
            addCriterion("setting_id in", values, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdNotIn(List<String> values) {
            addCriterion("setting_id not in", values, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdBetween(String value1, String value2) {
            addCriterion("setting_id between", value1, value2, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdNotBetween(String value1, String value2) {
            addCriterion("setting_id not between", value1, value2, "settingId");
            return (Criteria) this;
        }

        public Criteria andApiCodeIsNull() {
            addCriterion("api_code is null");
            return (Criteria) this;
        }

        public Criteria andApiCodeIsNotNull() {
            addCriterion("api_code is not null");
            return (Criteria) this;
        }

        public Criteria andApiCodeEqualTo(String value) {
            addCriterion("api_code =", value, "apiCode");
            return (Criteria) this;
        }

        public Criteria andApiCodeNotEqualTo(String value) {
            addCriterion("api_code <>", value, "apiCode");
            return (Criteria) this;
        }

        public Criteria andApiCodeGreaterThan(String value) {
            addCriterion("api_code >", value, "apiCode");
            return (Criteria) this;
        }

        public Criteria andApiCodeGreaterThanOrEqualTo(String value) {
            addCriterion("api_code >=", value, "apiCode");
            return (Criteria) this;
        }

        public Criteria andApiCodeLessThan(String value) {
            addCriterion("api_code <", value, "apiCode");
            return (Criteria) this;
        }

        public Criteria andApiCodeLessThanOrEqualTo(String value) {
            addCriterion("api_code <=", value, "apiCode");
            return (Criteria) this;
        }

        public Criteria andApiCodeLike(String value) {
            addCriterion("api_code like", value, "apiCode");
            return (Criteria) this;
        }

        public Criteria andApiCodeNotLike(String value) {
            addCriterion("api_code not like", value, "apiCode");
            return (Criteria) this;
        }

        public Criteria andApiCodeIn(List<String> values) {
            addCriterion("api_code in", values, "apiCode");
            return (Criteria) this;
        }

        public Criteria andApiCodeNotIn(List<String> values) {
            addCriterion("api_code not in", values, "apiCode");
            return (Criteria) this;
        }

        public Criteria andApiCodeBetween(String value1, String value2) {
            addCriterion("api_code between", value1, value2, "apiCode");
            return (Criteria) this;
        }

        public Criteria andApiCodeNotBetween(String value1, String value2) {
            addCriterion("api_code not between", value1, value2, "apiCode");
            return (Criteria) this;
        }

        public Criteria andAppNameIsNull() {
            addCriterion("app_name is null");
            return (Criteria) this;
        }

        public Criteria andAppNameIsNotNull() {
            addCriterion("app_name is not null");
            return (Criteria) this;
        }

        public Criteria andAppNameEqualTo(String value) {
            addCriterion("app_name =", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotEqualTo(String value) {
            addCriterion("app_name <>", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameGreaterThan(String value) {
            addCriterion("app_name >", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameGreaterThanOrEqualTo(String value) {
            addCriterion("app_name >=", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameLessThan(String value) {
            addCriterion("app_name <", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameLessThanOrEqualTo(String value) {
            addCriterion("app_name <=", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameLike(String value) {
            addCriterion("app_name like", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotLike(String value) {
            addCriterion("app_name not like", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameIn(List<String> values) {
            addCriterion("app_name in", values, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotIn(List<String> values) {
            addCriterion("app_name not in", values, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameBetween(String value1, String value2) {
            addCriterion("app_name between", value1, value2, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotBetween(String value1, String value2) {
            addCriterion("app_name not between", value1, value2, "appName");
            return (Criteria) this;
        }

        public Criteria andInterfaceNameIsNull() {
            addCriterion("interface_name is null");
            return (Criteria) this;
        }

        public Criteria andInterfaceNameIsNotNull() {
            addCriterion("interface_name is not null");
            return (Criteria) this;
        }

        public Criteria andInterfaceNameEqualTo(String value) {
            addCriterion("interface_name =", value, "interfaceName");
            return (Criteria) this;
        }

        public Criteria andInterfaceNameNotEqualTo(String value) {
            addCriterion("interface_name <>", value, "interfaceName");
            return (Criteria) this;
        }

        public Criteria andInterfaceNameGreaterThan(String value) {
            addCriterion("interface_name >", value, "interfaceName");
            return (Criteria) this;
        }

        public Criteria andInterfaceNameGreaterThanOrEqualTo(String value) {
            addCriterion("interface_name >=", value, "interfaceName");
            return (Criteria) this;
        }

        public Criteria andInterfaceNameLessThan(String value) {
            addCriterion("interface_name <", value, "interfaceName");
            return (Criteria) this;
        }

        public Criteria andInterfaceNameLessThanOrEqualTo(String value) {
            addCriterion("interface_name <=", value, "interfaceName");
            return (Criteria) this;
        }

        public Criteria andInterfaceNameLike(String value) {
            addCriterion("interface_name like", value, "interfaceName");
            return (Criteria) this;
        }

        public Criteria andInterfaceNameNotLike(String value) {
            addCriterion("interface_name not like", value, "interfaceName");
            return (Criteria) this;
        }

        public Criteria andInterfaceNameIn(List<String> values) {
            addCriterion("interface_name in", values, "interfaceName");
            return (Criteria) this;
        }

        public Criteria andInterfaceNameNotIn(List<String> values) {
            addCriterion("interface_name not in", values, "interfaceName");
            return (Criteria) this;
        }

        public Criteria andInterfaceNameBetween(String value1, String value2) {
            addCriterion("interface_name between", value1, value2, "interfaceName");
            return (Criteria) this;
        }

        public Criteria andInterfaceNameNotBetween(String value1, String value2) {
            addCriterion("interface_name not between", value1, value2, "interfaceName");
            return (Criteria) this;
        }

        public Criteria andMethodIsNull() {
            addCriterion("method is null");
            return (Criteria) this;
        }

        public Criteria andMethodIsNotNull() {
            addCriterion("method is not null");
            return (Criteria) this;
        }

        public Criteria andMethodEqualTo(String value) {
            addCriterion("method =", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotEqualTo(String value) {
            addCriterion("method <>", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThan(String value) {
            addCriterion("method >", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThanOrEqualTo(String value) {
            addCriterion("method >=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThan(String value) {
            addCriterion("method <", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThanOrEqualTo(String value) {
            addCriterion("method <=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLike(String value) {
            addCriterion("method like", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotLike(String value) {
            addCriterion("method not like", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodIn(List<String> values) {
            addCriterion("method in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotIn(List<String> values) {
            addCriterion("method not in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodBetween(String value1, String value2) {
            addCriterion("method between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotBetween(String value1, String value2) {
            addCriterion("method not between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andReturnJavaTypeIsNull() {
            addCriterion("return_java_type is null");
            return (Criteria) this;
        }

        public Criteria andReturnJavaTypeIsNotNull() {
            addCriterion("return_java_type is not null");
            return (Criteria) this;
        }

        public Criteria andReturnJavaTypeEqualTo(String value) {
            addCriterion("return_java_type =", value, "returnJavaType");
            return (Criteria) this;
        }

        public Criteria andReturnJavaTypeNotEqualTo(String value) {
            addCriterion("return_java_type <>", value, "returnJavaType");
            return (Criteria) this;
        }

        public Criteria andReturnJavaTypeGreaterThan(String value) {
            addCriterion("return_java_type >", value, "returnJavaType");
            return (Criteria) this;
        }

        public Criteria andReturnJavaTypeGreaterThanOrEqualTo(String value) {
            addCriterion("return_java_type >=", value, "returnJavaType");
            return (Criteria) this;
        }

        public Criteria andReturnJavaTypeLessThan(String value) {
            addCriterion("return_java_type <", value, "returnJavaType");
            return (Criteria) this;
        }

        public Criteria andReturnJavaTypeLessThanOrEqualTo(String value) {
            addCriterion("return_java_type <=", value, "returnJavaType");
            return (Criteria) this;
        }

        public Criteria andReturnJavaTypeLike(String value) {
            addCriterion("return_java_type like", value, "returnJavaType");
            return (Criteria) this;
        }

        public Criteria andReturnJavaTypeNotLike(String value) {
            addCriterion("return_java_type not like", value, "returnJavaType");
            return (Criteria) this;
        }

        public Criteria andReturnJavaTypeIn(List<String> values) {
            addCriterion("return_java_type in", values, "returnJavaType");
            return (Criteria) this;
        }

        public Criteria andReturnJavaTypeNotIn(List<String> values) {
            addCriterion("return_java_type not in", values, "returnJavaType");
            return (Criteria) this;
        }

        public Criteria andReturnJavaTypeBetween(String value1, String value2) {
            addCriterion("return_java_type between", value1, value2, "returnJavaType");
            return (Criteria) this;
        }

        public Criteria andReturnJavaTypeNotBetween(String value1, String value2) {
            addCriterion("return_java_type not between", value1, value2, "returnJavaType");
            return (Criteria) this;
        }

        public Criteria andReturnCommentIsNull() {
            addCriterion("return_comment is null");
            return (Criteria) this;
        }

        public Criteria andReturnCommentIsNotNull() {
            addCriterion("return_comment is not null");
            return (Criteria) this;
        }

        public Criteria andReturnCommentEqualTo(String value) {
            addCriterion("return_comment =", value, "returnComment");
            return (Criteria) this;
        }

        public Criteria andReturnCommentNotEqualTo(String value) {
            addCriterion("return_comment <>", value, "returnComment");
            return (Criteria) this;
        }

        public Criteria andReturnCommentGreaterThan(String value) {
            addCriterion("return_comment >", value, "returnComment");
            return (Criteria) this;
        }

        public Criteria andReturnCommentGreaterThanOrEqualTo(String value) {
            addCriterion("return_comment >=", value, "returnComment");
            return (Criteria) this;
        }

        public Criteria andReturnCommentLessThan(String value) {
            addCriterion("return_comment <", value, "returnComment");
            return (Criteria) this;
        }

        public Criteria andReturnCommentLessThanOrEqualTo(String value) {
            addCriterion("return_comment <=", value, "returnComment");
            return (Criteria) this;
        }

        public Criteria andReturnCommentLike(String value) {
            addCriterion("return_comment like", value, "returnComment");
            return (Criteria) this;
        }

        public Criteria andReturnCommentNotLike(String value) {
            addCriterion("return_comment not like", value, "returnComment");
            return (Criteria) this;
        }

        public Criteria andReturnCommentIn(List<String> values) {
            addCriterion("return_comment in", values, "returnComment");
            return (Criteria) this;
        }

        public Criteria andReturnCommentNotIn(List<String> values) {
            addCriterion("return_comment not in", values, "returnComment");
            return (Criteria) this;
        }

        public Criteria andReturnCommentBetween(String value1, String value2) {
            addCriterion("return_comment between", value1, value2, "returnComment");
            return (Criteria) this;
        }

        public Criteria andReturnCommentNotBetween(String value1, String value2) {
            addCriterion("return_comment not between", value1, value2, "returnComment");
            return (Criteria) this;
        }

        public Criteria andReturnJsonIsNull() {
            addCriterion("return_json is null");
            return (Criteria) this;
        }

        public Criteria andReturnJsonIsNotNull() {
            addCriterion("return_json is not null");
            return (Criteria) this;
        }

        public Criteria andReturnJsonEqualTo(String value) {
            addCriterion("return_json =", value, "returnJson");
            return (Criteria) this;
        }

        public Criteria andReturnJsonNotEqualTo(String value) {
            addCriterion("return_json <>", value, "returnJson");
            return (Criteria) this;
        }

        public Criteria andReturnJsonGreaterThan(String value) {
            addCriterion("return_json >", value, "returnJson");
            return (Criteria) this;
        }

        public Criteria andReturnJsonGreaterThanOrEqualTo(String value) {
            addCriterion("return_json >=", value, "returnJson");
            return (Criteria) this;
        }

        public Criteria andReturnJsonLessThan(String value) {
            addCriterion("return_json <", value, "returnJson");
            return (Criteria) this;
        }

        public Criteria andReturnJsonLessThanOrEqualTo(String value) {
            addCriterion("return_json <=", value, "returnJson");
            return (Criteria) this;
        }

        public Criteria andReturnJsonLike(String value) {
            addCriterion("return_json like", value, "returnJson");
            return (Criteria) this;
        }

        public Criteria andReturnJsonNotLike(String value) {
            addCriterion("return_json not like", value, "returnJson");
            return (Criteria) this;
        }

        public Criteria andReturnJsonIn(List<String> values) {
            addCriterion("return_json in", values, "returnJson");
            return (Criteria) this;
        }

        public Criteria andReturnJsonNotIn(List<String> values) {
            addCriterion("return_json not in", values, "returnJson");
            return (Criteria) this;
        }

        public Criteria andReturnJsonBetween(String value1, String value2) {
            addCriterion("return_json between", value1, value2, "returnJson");
            return (Criteria) this;
        }

        public Criteria andReturnJsonNotBetween(String value1, String value2) {
            addCriterion("return_json not between", value1, value2, "returnJson");
            return (Criteria) this;
        }

        public Criteria andCallTypeIsNull() {
            addCriterion("call_type is null");
            return (Criteria) this;
        }

        public Criteria andCallTypeIsNotNull() {
            addCriterion("call_type is not null");
            return (Criteria) this;
        }

        public Criteria andCallTypeEqualTo(String value) {
            addCriterion("call_type =", value, "callType");
            return (Criteria) this;
        }

        public Criteria andCallTypeNotEqualTo(String value) {
            addCriterion("call_type <>", value, "callType");
            return (Criteria) this;
        }

        public Criteria andCallTypeGreaterThan(String value) {
            addCriterion("call_type >", value, "callType");
            return (Criteria) this;
        }

        public Criteria andCallTypeGreaterThanOrEqualTo(String value) {
            addCriterion("call_type >=", value, "callType");
            return (Criteria) this;
        }

        public Criteria andCallTypeLessThan(String value) {
            addCriterion("call_type <", value, "callType");
            return (Criteria) this;
        }

        public Criteria andCallTypeLessThanOrEqualTo(String value) {
            addCriterion("call_type <=", value, "callType");
            return (Criteria) this;
        }

        public Criteria andCallTypeLike(String value) {
            addCriterion("call_type like", value, "callType");
            return (Criteria) this;
        }

        public Criteria andCallTypeNotLike(String value) {
            addCriterion("call_type not like", value, "callType");
            return (Criteria) this;
        }

        public Criteria andCallTypeIn(List<String> values) {
            addCriterion("call_type in", values, "callType");
            return (Criteria) this;
        }

        public Criteria andCallTypeNotIn(List<String> values) {
            addCriterion("call_type not in", values, "callType");
            return (Criteria) this;
        }

        public Criteria andCallTypeBetween(String value1, String value2) {
            addCriterion("call_type between", value1, value2, "callType");
            return (Criteria) this;
        }

        public Criteria andCallTypeNotBetween(String value1, String value2) {
            addCriterion("call_type not between", value1, value2, "callType");
            return (Criteria) this;
        }

        public Criteria andSandboxRespIsNull() {
            addCriterion("sandbox_resp is null");
            return (Criteria) this;
        }

        public Criteria andSandboxRespIsNotNull() {
            addCriterion("sandbox_resp is not null");
            return (Criteria) this;
        }

        public Criteria andSandboxRespEqualTo(String value) {
            addCriterion("sandbox_resp =", value, "sandboxResp");
            return (Criteria) this;
        }

        public Criteria andSandboxRespNotEqualTo(String value) {
            addCriterion("sandbox_resp <>", value, "sandboxResp");
            return (Criteria) this;
        }

        public Criteria andSandboxRespGreaterThan(String value) {
            addCriterion("sandbox_resp >", value, "sandboxResp");
            return (Criteria) this;
        }

        public Criteria andSandboxRespGreaterThanOrEqualTo(String value) {
            addCriterion("sandbox_resp >=", value, "sandboxResp");
            return (Criteria) this;
        }

        public Criteria andSandboxRespLessThan(String value) {
            addCriterion("sandbox_resp <", value, "sandboxResp");
            return (Criteria) this;
        }

        public Criteria andSandboxRespLessThanOrEqualTo(String value) {
            addCriterion("sandbox_resp <=", value, "sandboxResp");
            return (Criteria) this;
        }

        public Criteria andSandboxRespLike(String value) {
            addCriterion("sandbox_resp like", value, "sandboxResp");
            return (Criteria) this;
        }

        public Criteria andSandboxRespNotLike(String value) {
            addCriterion("sandbox_resp not like", value, "sandboxResp");
            return (Criteria) this;
        }

        public Criteria andSandboxRespIn(List<String> values) {
            addCriterion("sandbox_resp in", values, "sandboxResp");
            return (Criteria) this;
        }

        public Criteria andSandboxRespNotIn(List<String> values) {
            addCriterion("sandbox_resp not in", values, "sandboxResp");
            return (Criteria) this;
        }

        public Criteria andSandboxRespBetween(String value1, String value2) {
            addCriterion("sandbox_resp between", value1, value2, "sandboxResp");
            return (Criteria) this;
        }

        public Criteria andSandboxRespNotBetween(String value1, String value2) {
            addCriterion("sandbox_resp not between", value1, value2, "sandboxResp");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}