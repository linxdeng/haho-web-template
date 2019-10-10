package cn.haho.template.common.http;

/**
 * @Description:
 * @Auther: linx.deng
 * @Date: 2019/9/3 20:06
 */
public class HttpResult {
    private int code;//状态码
    private String response;//数据

    public HttpResult(int code,String response){
        this.code=code;
        this.response=response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", response='" + response + '\'' +
                '}';
    }
}
