package com.ai.opt.sol.api.apisearch.param;

import java.io.Serializable;
import java.util.List;

public class APISearchResult implements Serializable {

    private static final long serialVersionUID = 1L;

    // 索引id
    private int id;

    // 服务编码
    private String apiCode;

    // 服务类名
    private String interfaceName;

    // 服务方法名
    private String methodName;

    // 服务提供者
    private String owner;

    // 提供者分类
    private String ownerType;

    // 服务坐标分组标识
    private String groupId;

    // 服务构件标识
    private String artifactId;

    // 简要说明
    private String briefComment;

    // 详细说明
    private String detailComment;

    // 自动发布时间
    private String publishDate;

    // 服务作者
    private String author;

    // 接口最新版本号
    private String version;

    // 服务异常
    private String exceptions;

    // 服务编码高亮值
    private String apiCodeHighlight;

    // 服务类名
    private String interfaceNameHighlight;

    // 服务方法名
    private String methodNameHighlight;

    // 服务提供者
    private String ownerHighlight;

    // 服务坐标分组标识
    private String groupIdHighlight;

    // 服务构件标识
    private String artifactIdHighlight;

    // 简要说明
    private String briefCommentHighlight;

    // 详细说明
    private String detailCommentHighlight;

    // 服务作者
    private String authorHighlight;

    // 是否支持REST访问
    private boolean restSupported;

    // REST地址相对路径
    private String restRelativeURL;

    // REST地址相对路径的高亮
    private String restRelativeURLHighlight;

    // REST服务的请求方式
    private String restMethod;

    /**
     * 是否设置过模板
     */
    private boolean isSetted;

    /**
     * API包含的版本信息
     */
    private List<com.ai.opt.sol.api.apisearch.param.APIVersion> apiVersions;

    // 是否允许删除服务
    private boolean isAllowDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getBriefComment() {
        return briefComment;
    }

    public void setBriefComment(String briefComment) {
        this.briefComment = briefComment;
    }

    public String getDetailComment() {
        return detailComment;
    }

    public void setDetailComment(String detailComment) {
        this.detailComment = detailComment;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getApiCodeHighlight() {
        return apiCodeHighlight;
    }

    public void setApiCodeHighlight(String apiCodeHighlight) {
        this.apiCodeHighlight = apiCodeHighlight;
    }

    public String getInterfaceNameHighlight() {
        return interfaceNameHighlight;
    }

    public void setInterfaceNameHighlight(String interfaceNameHighlight) {
        this.interfaceNameHighlight = interfaceNameHighlight;
    }

    public String getMethodNameHighlight() {
        return methodNameHighlight;
    }

    public void setMethodNameHighlight(String methodNameHighlight) {
        this.methodNameHighlight = methodNameHighlight;
    }

    public String getOwnerHighlight() {
        return ownerHighlight;
    }

    public void setOwnerHighlight(String ownerHighlight) {
        this.ownerHighlight = ownerHighlight;
    }

    public String getGroupIdHighlight() {
        return groupIdHighlight;
    }

    public void setGroupIdHighlight(String groupIdHighlight) {
        this.groupIdHighlight = groupIdHighlight;
    }

    public String getArtifactIdHighlight() {
        return artifactIdHighlight;
    }

    public void setArtifactIdHighlight(String artifactIdHighlight) {
        this.artifactIdHighlight = artifactIdHighlight;
    }

    public String getBriefCommentHighlight() {
        return briefCommentHighlight;
    }

    public void setBriefCommentHighlight(String briefCommentHighlight) {
        this.briefCommentHighlight = briefCommentHighlight;
    }

    public String getDetailCommentHighlight() {
        return detailCommentHighlight;
    }

    public void setDetailCommentHighlight(String detailCommentHighlight) {
        this.detailCommentHighlight = detailCommentHighlight;
    }

    public String getAuthorHighlight() {
        return authorHighlight;
    }

    public void setAuthorHighlight(String authorHighlight) {
        this.authorHighlight = authorHighlight;
    }

    public boolean isSetted() {
        return isSetted;
    }

    public void setSetted(boolean isSetted) {
        this.isSetted = isSetted;
    }

    public boolean isRestSupported() {
        return restSupported;
    }

    public void setRestSupported(boolean restSupported) {
        this.restSupported = restSupported;
    }

    public String getRestRelativeURL() {
        return restRelativeURL;
    }

    public void setRestRelativeURL(String restRelativeURL) {
        this.restRelativeURL = restRelativeURL;
    }

    public String getRestMethod() {
        return restMethod;
    }

    public void setRestMethod(String restMethod) {
        this.restMethod = restMethod;
    }

    public String getRestRelativeURLHighlight() {
        return restRelativeURLHighlight;
    }

    public void setRestRelativeURLHighlight(String restRelativeURLHighlight) {
        this.restRelativeURLHighlight = restRelativeURLHighlight;
    }

    public String getExceptions() {
        return exceptions;
    }

    public void setExceptions(String exceptions) {
        this.exceptions = exceptions;
    }

    public boolean isAllowDelete() {
        return isAllowDelete;
    }

    public void setAllowDelete(boolean isAllowDelete) {
        this.isAllowDelete = isAllowDelete;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<com.ai.opt.sol.api.apisearch.param.APIVersion> getApiVersions() {
        return apiVersions;
    }

    public void setApiVersions(List<APIVersion> apiVersions) {
        this.apiVersions = apiVersions;
    }

}
