
package com.discussion.portal.model.auth;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Oauth2Request {

    @SerializedName("clientId")
    @Expose
    private String clientId;
    @SerializedName("scope")
    @Expose
    private List<Object> scope = null;
    @SerializedName("requestParameters")
    @Expose
    private RequestParameters requestParameters;
    @SerializedName("resourceIds")
    @Expose
    private List<Object> resourceIds = null;
    @SerializedName("authorities")
    @Expose
    private List<Object> authorities = null;
    @SerializedName("approved")
    @Expose
    private Boolean approved;
    @SerializedName("refresh")
    @Expose
    private Boolean refresh;
    @SerializedName("redirectUri")
    @Expose
    private Object redirectUri;
    @SerializedName("responseTypes")
    @Expose
    private List<Object> responseTypes = null;
    @SerializedName("extensions")
    @Expose
    private Extensions extensions;
    @SerializedName("grantType")
    @Expose
    private Object grantType;
    @SerializedName("refreshTokenRequest")
    @Expose
    private Object refreshTokenRequest;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<Object> getScope() {
        return scope;
    }

    public void setScope(List<Object> scope) {
        this.scope = scope;
    }

    public RequestParameters getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(RequestParameters requestParameters) {
        this.requestParameters = requestParameters;
    }

    public List<Object> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Object> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public List<Object> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Object> authorities) {
        this.authorities = authorities;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getRefresh() {
        return refresh;
    }

    public void setRefresh(Boolean refresh) {
        this.refresh = refresh;
    }

    public Object getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(Object redirectUri) {
        this.redirectUri = redirectUri;
    }

    public List<Object> getResponseTypes() {
        return responseTypes;
    }

    public void setResponseTypes(List<Object> responseTypes) {
        this.responseTypes = responseTypes;
    }

    public Extensions getExtensions() {
        return extensions;
    }

    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }

    public Object getGrantType() {
        return grantType;
    }

    public void setGrantType(Object grantType) {
        this.grantType = grantType;
    }

    public Object getRefreshTokenRequest() {
        return refreshTokenRequest;
    }

    public void setRefreshTokenRequest(Object refreshTokenRequest) {
        this.refreshTokenRequest = refreshTokenRequest;
    }

}
