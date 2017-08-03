
package com.discussion.portal.model.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("remoteAddress")
    @Expose
    private String remoteAddress;
    @SerializedName("sessionId")
    @Expose
    private String sessionId;
    @SerializedName("tokenValue")
    @Expose
    private String tokenValue;
    @SerializedName("tokenType")
    @Expose
    private String tokenType;
    @SerializedName("decodedDetails")
    @Expose
    private Object decodedDetails;

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Object getDecodedDetails() {
        return decodedDetails;
    }

    public void setDecodedDetails(Object decodedDetails) {
        this.decodedDetails = decodedDetails;
    }

}
