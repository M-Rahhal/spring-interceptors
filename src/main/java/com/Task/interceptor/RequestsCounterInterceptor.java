package com.Task.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@ConfigurationProperties(prefix = "interceptor")
public class RequestsCounterInterceptor implements HandlerInterceptor {

    private static int numOfRequests;
    private static long maxThrottlingPeriod;
    private static long delayDuration;
    private static int maxNumOfRequests;



    static {
        Runnable timer = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(maxThrottlingPeriod);
                        resetNumOfRequests();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Thread thread = new Thread(timer);
        thread.start();
    }



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        incrementNumOfRequests();
        if (getNumOfRequests()>=maxNumOfRequests){
            log.info("more delay is added on this request to achieve throttling");
            Thread.sleep(delayDuration);
        }
        return true;
    }

    private static synchronized int getNumOfRequests(){
        return numOfRequests;
    }

    private static synchronized void incrementNumOfRequests(){
        numOfRequests++;
    }
    private static synchronized void resetNumOfRequests(){
        numOfRequests = 0;
    }


    public long getMaxThrottlingPeriod() {
        return maxThrottlingPeriod;
    }

    public void setMaxThrottlingPeriod(int maxThrottlingPeriod) {
        this.maxThrottlingPeriod = maxThrottlingPeriod;
    }

    public long getDelayDuration() {
        return delayDuration;
    }

    public void setDelayDuration(int delayDuration) {
        this.delayDuration = delayDuration;
    }

    public int getMaxNumOfRequests() {
        return maxNumOfRequests;
    }

    public void setMaxNumOfRequests(int maxNumOfRequests) {
        this.maxNumOfRequests = maxNumOfRequests;
    }



}
