package com.stitchcentral.stitchcentralservices.util;

public class CommonResponse {

    private boolean success;
    private String message;

    public CommonResponse() {
    }

    public CommonResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{ \"success\": " + success + ", \"message\": \"" + message + "\" }";
    }
}

