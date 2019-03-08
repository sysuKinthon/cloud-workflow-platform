package org.sysu.bpmprocessenginesportal.requestcontext;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.FutureTask;

//存放使用restTemplate执行completeTask和startProcess需要的参数信息
//维护一个futureTask，用于获取执行的结果
public class ExecuteRequestContext extends AbstractRequestContext {
    private String rtl;

//    请求到达的时间
    private long startTime;

//    期望的执行时间(由)IRule计算
    private long expectExecuteTime;

    public ExecuteRequestContext(RequestMethod method, String rtl, String url, MultiValueMap<String, Object> variables, RestTemplate restTemplate) {
        this.method = method;
        this.rtl = rtl;
        this.url = url;
        this.variables= variables;
        this.restTemplate = restTemplate;
        this.futureTask = new FutureTask<>(new Task(method, url, variables, restTemplate));
        this.startTime = System.currentTimeMillis();
    }

    public ExecuteRequestContext(String rtl, String url, MultiValueMap<String, Object> variables, RestTemplate restTemplate,
                                 FutureTask futureTask) {
        this.rtl = rtl;
        this.url = url;
        this.variables= variables;
        this.restTemplate = restTemplate;
        this.futureTask = futureTask;
        this.startTime = System.currentTimeMillis();
    }

    public ExecuteRequestContext(FlowLimitationRequestContext flowLimitationRequestContext) {
        this.method = flowLimitationRequestContext.getMethod();
        this.url = flowLimitationRequestContext.getUrl();
        this.variables = flowLimitationRequestContext.getVariables();
        this.restTemplate = flowLimitationRequestContext.getRestTemplate();
        this.futureTask = flowLimitationRequestContext.getFutureTask();
        this.startTime = System.currentTimeMillis();
    }

    public long getExpectExecuteTime() {
        return expectExecuteTime;
    }

    public void setExpectExecuteTime(long expectExecuteTime) {
        this.expectExecuteTime = expectExecuteTime;
    }

    public String getRtl() {
        return rtl;
    }

    public void setRtl(String rtl) {
        this.rtl = rtl;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

}