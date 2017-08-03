
package com.discussion.portal.model.auth;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAuthentication {

    @SerializedName("authorities")
    @Expose
    private List<Authority_> authorities = null;
    @SerializedName("details")
    @Expose
    private Details_ details;
    @SerializedName("authenticated")
    @Expose
    private Boolean authenticated;
    @SerializedName("principal")
    @Expose
    private String principal;
    @SerializedName("credentials")
    @Expose
    private String credentials;
    @SerializedName("name")
    @Expose
    private String name;

    public List<Authority_> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority_> authorities) {
        this.authorities = authorities;
    }

    public Details_ getDetails() {
        return details;
    }

    public void setDetails(Details_ details) {
        this.details = details;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
