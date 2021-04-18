package com.gzeic.smartcity01.bean;

public class LoginBean {

    /**
     * msg : 操作成功
     * code : 200
     * token : eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6ImMwYzAzNWJmLTM3MTgtNDE3MC1hZWQwLTA1NDIzZGI2ZTVmYyJ9.AbIAOtVXy_Spx-eJhn4vWmlJAF_3brSX2mYUEZeQ_D3Jo-W8cUf0R5WZTocohbn_zQz_1e0Pw6dtUYrdzOlf0Q
     */

    private String msg;
    private int code;
    private String token;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
