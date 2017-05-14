package com.ai.opt.sol.core.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class ApiEnvSettingsCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ApiEnvSettingsCriteria() {
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

        public Criteria andSettingsIdIsNull() {
            addCriterion("settings_id is null");
            return (Criteria) this;
        }

        public Criteria andSettingsIdIsNotNull() {
            addCriterion("settings_id is not null");
            return (Criteria) this;
        }

        public Criteria andSettingsIdEqualTo(String value) {
            addCriterion("settings_id =", value, "settingsId");
            return (Criteria) this;
        }

        public Criteria andSettingsIdNotEqualTo(String value) {
            addCriterion("settings_id <>", value, "settingsId");
            return (Criteria) this;
        }

        public Criteria andSettingsIdGreaterThan(String value) {
            addCriterion("settings_id >", value, "settingsId");
            return (Criteria) this;
        }

        public Criteria andSettingsIdGreaterThanOrEqualTo(String value) {
            addCriterion("settings_id >=", value, "settingsId");
            return (Criteria) this;
        }

        public Criteria andSettingsIdLessThan(String value) {
            addCriterion("settings_id <", value, "settingsId");
            return (Criteria) this;
        }

        public Criteria andSettingsIdLessThanOrEqualTo(String value) {
            addCriterion("settings_id <=", value, "settingsId");
            return (Criteria) this;
        }

        public Criteria andSettingsIdLike(String value) {
            addCriterion("settings_id like", value, "settingsId");
            return (Criteria) this;
        }

        public Criteria andSettingsIdNotLike(String value) {
            addCriterion("settings_id not like", value, "settingsId");
            return (Criteria) this;
        }

        public Criteria andSettingsIdIn(List<String> values) {
            addCriterion("settings_id in", values, "settingsId");
            return (Criteria) this;
        }

        public Criteria andSettingsIdNotIn(List<String> values) {
            addCriterion("settings_id not in", values, "settingsId");
            return (Criteria) this;
        }

        public Criteria andSettingsIdBetween(String value1, String value2) {
            addCriterion("settings_id between", value1, value2, "settingsId");
            return (Criteria) this;
        }

        public Criteria andSettingsIdNotBetween(String value1, String value2) {
            addCriterion("settings_id not between", value1, value2, "settingsId");
            return (Criteria) this;
        }

        public Criteria andOwnertypeIsNull() {
            addCriterion("ownerType is null");
            return (Criteria) this;
        }

        public Criteria andOwnertypeIsNotNull() {
            addCriterion("ownerType is not null");
            return (Criteria) this;
        }

        public Criteria andOwnertypeEqualTo(String value) {
            addCriterion("ownerType =", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeNotEqualTo(String value) {
            addCriterion("ownerType <>", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeGreaterThan(String value) {
            addCriterion("ownerType >", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeGreaterThanOrEqualTo(String value) {
            addCriterion("ownerType >=", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeLessThan(String value) {
            addCriterion("ownerType <", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeLessThanOrEqualTo(String value) {
            addCriterion("ownerType <=", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeLike(String value) {
            addCriterion("ownerType like", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeNotLike(String value) {
            addCriterion("ownerType not like", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeIn(List<String> values) {
            addCriterion("ownerType in", values, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeNotIn(List<String> values) {
            addCriterion("ownerType not in", values, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeBetween(String value1, String value2) {
            addCriterion("ownerType between", value1, value2, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeNotBetween(String value1, String value2) {
            addCriterion("ownerType not between", value1, value2, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNull() {
            addCriterion("owner is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNotNull() {
            addCriterion("owner is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerEqualTo(String value) {
            addCriterion("owner =", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotEqualTo(String value) {
            addCriterion("owner <>", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThan(String value) {
            addCriterion("owner >", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("owner >=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThan(String value) {
            addCriterion("owner <", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThanOrEqualTo(String value) {
            addCriterion("owner <=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLike(String value) {
            addCriterion("owner like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotLike(String value) {
            addCriterion("owner not like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerIn(List<String> values) {
            addCriterion("owner in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotIn(List<String> values) {
            addCriterion("owner not in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerBetween(String value1, String value2) {
            addCriterion("owner between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotBetween(String value1, String value2) {
            addCriterion("owner not between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andEnvIsNull() {
            addCriterion("env is null");
            return (Criteria) this;
        }

        public Criteria andEnvIsNotNull() {
            addCriterion("env is not null");
            return (Criteria) this;
        }

        public Criteria andEnvEqualTo(String value) {
            addCriterion("env =", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvNotEqualTo(String value) {
            addCriterion("env <>", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvGreaterThan(String value) {
            addCriterion("env >", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvGreaterThanOrEqualTo(String value) {
            addCriterion("env >=", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvLessThan(String value) {
            addCriterion("env <", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvLessThanOrEqualTo(String value) {
            addCriterion("env <=", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvLike(String value) {
            addCriterion("env like", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvNotLike(String value) {
            addCriterion("env not like", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvIn(List<String> values) {
            addCriterion("env in", values, "env");
            return (Criteria) this;
        }

        public Criteria andEnvNotIn(List<String> values) {
            addCriterion("env not in", values, "env");
            return (Criteria) this;
        }

        public Criteria andEnvBetween(String value1, String value2) {
            addCriterion("env between", value1, value2, "env");
            return (Criteria) this;
        }

        public Criteria andEnvNotBetween(String value1, String value2) {
            addCriterion("env not between", value1, value2, "env");
            return (Criteria) this;
        }

        public Criteria andZkcenterIsNull() {
            addCriterion("zkcenter is null");
            return (Criteria) this;
        }

        public Criteria andZkcenterIsNotNull() {
            addCriterion("zkcenter is not null");
            return (Criteria) this;
        }

        public Criteria andZkcenterEqualTo(String value) {
            addCriterion("zkcenter =", value, "zkcenter");
            return (Criteria) this;
        }

        public Criteria andZkcenterNotEqualTo(String value) {
            addCriterion("zkcenter <>", value, "zkcenter");
            return (Criteria) this;
        }

        public Criteria andZkcenterGreaterThan(String value) {
            addCriterion("zkcenter >", value, "zkcenter");
            return (Criteria) this;
        }

        public Criteria andZkcenterGreaterThanOrEqualTo(String value) {
            addCriterion("zkcenter >=", value, "zkcenter");
            return (Criteria) this;
        }

        public Criteria andZkcenterLessThan(String value) {
            addCriterion("zkcenter <", value, "zkcenter");
            return (Criteria) this;
        }

        public Criteria andZkcenterLessThanOrEqualTo(String value) {
            addCriterion("zkcenter <=", value, "zkcenter");
            return (Criteria) this;
        }

        public Criteria andZkcenterLike(String value) {
            addCriterion("zkcenter like", value, "zkcenter");
            return (Criteria) this;
        }

        public Criteria andZkcenterNotLike(String value) {
            addCriterion("zkcenter not like", value, "zkcenter");
            return (Criteria) this;
        }

        public Criteria andZkcenterIn(List<String> values) {
            addCriterion("zkcenter in", values, "zkcenter");
            return (Criteria) this;
        }

        public Criteria andZkcenterNotIn(List<String> values) {
            addCriterion("zkcenter not in", values, "zkcenter");
            return (Criteria) this;
        }

        public Criteria andZkcenterBetween(String value1, String value2) {
            addCriterion("zkcenter between", value1, value2, "zkcenter");
            return (Criteria) this;
        }

        public Criteria andZkcenterNotBetween(String value1, String value2) {
            addCriterion("zkcenter not between", value1, value2, "zkcenter");
            return (Criteria) this;
        }

        public Criteria andResthttpIsNull() {
            addCriterion("resthttp is null");
            return (Criteria) this;
        }

        public Criteria andResthttpIsNotNull() {
            addCriterion("resthttp is not null");
            return (Criteria) this;
        }

        public Criteria andResthttpEqualTo(String value) {
            addCriterion("resthttp =", value, "resthttp");
            return (Criteria) this;
        }

        public Criteria andResthttpNotEqualTo(String value) {
            addCriterion("resthttp <>", value, "resthttp");
            return (Criteria) this;
        }

        public Criteria andResthttpGreaterThan(String value) {
            addCriterion("resthttp >", value, "resthttp");
            return (Criteria) this;
        }

        public Criteria andResthttpGreaterThanOrEqualTo(String value) {
            addCriterion("resthttp >=", value, "resthttp");
            return (Criteria) this;
        }

        public Criteria andResthttpLessThan(String value) {
            addCriterion("resthttp <", value, "resthttp");
            return (Criteria) this;
        }

        public Criteria andResthttpLessThanOrEqualTo(String value) {
            addCriterion("resthttp <=", value, "resthttp");
            return (Criteria) this;
        }

        public Criteria andResthttpLike(String value) {
            addCriterion("resthttp like", value, "resthttp");
            return (Criteria) this;
        }

        public Criteria andResthttpNotLike(String value) {
            addCriterion("resthttp not like", value, "resthttp");
            return (Criteria) this;
        }

        public Criteria andResthttpIn(List<String> values) {
            addCriterion("resthttp in", values, "resthttp");
            return (Criteria) this;
        }

        public Criteria andResthttpNotIn(List<String> values) {
            addCriterion("resthttp not in", values, "resthttp");
            return (Criteria) this;
        }

        public Criteria andResthttpBetween(String value1, String value2) {
            addCriterion("resthttp between", value1, value2, "resthttp");
            return (Criteria) this;
        }

        public Criteria andResthttpNotBetween(String value1, String value2) {
            addCriterion("resthttp not between", value1, value2, "resthttp");
            return (Criteria) this;
        }

        public Criteria andMonitorIsNull() {
            addCriterion("monitor is null");
            return (Criteria) this;
        }

        public Criteria andMonitorIsNotNull() {
            addCriterion("monitor is not null");
            return (Criteria) this;
        }

        public Criteria andMonitorEqualTo(String value) {
            addCriterion("monitor =", value, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorNotEqualTo(String value) {
            addCriterion("monitor <>", value, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorGreaterThan(String value) {
            addCriterion("monitor >", value, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorGreaterThanOrEqualTo(String value) {
            addCriterion("monitor >=", value, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorLessThan(String value) {
            addCriterion("monitor <", value, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorLessThanOrEqualTo(String value) {
            addCriterion("monitor <=", value, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorLike(String value) {
            addCriterion("monitor like", value, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorNotLike(String value) {
            addCriterion("monitor not like", value, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorIn(List<String> values) {
            addCriterion("monitor in", values, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorNotIn(List<String> values) {
            addCriterion("monitor not in", values, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorBetween(String value1, String value2) {
            addCriterion("monitor between", value1, value2, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorNotBetween(String value1, String value2) {
            addCriterion("monitor not between", value1, value2, "monitor");
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