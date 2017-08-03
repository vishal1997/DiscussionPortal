
package com.discussion.portal.model.auth;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthenticationModel {

    @SerializedName("authorities")
    @Expose
    private List<Authority> authorities = null;
    @SerializedName("details")
    @Expose
    private Details details;
    @SerializedName("authenticated")
    @Expose
    private Boolean authenticated;
    @SerializedName("userAuthentication")
    @Expose
    private UserAuthentication userAuthentication;
    @SerializedName("clientOnly")
    @Expose
    private Boolean clientOnly;
    @SerializedName("credentials")
    @Expose
    private String credentials;
    @SerializedName("principal")
    @Expose
    private String principal;
    @SerializedName("oauth2Request")
    @Expose
    private Oauth2Request oauth2Request;
    @SerializedName("name")
    @Expose
    private String name;

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public UserAuthentication getUserAuthentication() {
        return userAuthentication;
    }

    public void setUserAuthentication(UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    public Boolean getClientOnly() {
        return clientOnly;
    }

    public void setClientOnly(Boolean clientOnly) {
        this.clientOnly = clientOnly;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Oauth2Request getOauth2Request() {
        return oauth2Request;
    }

    public void setOauth2Request(Oauth2Request oauth2Request) {
        this.oauth2Request = oauth2Request;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
