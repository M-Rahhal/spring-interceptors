package com.Task.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class RequestsCounterInterceptor implements HandlerInterceptor {

    private static int numOfRequests;
    private static long delayTime;


    static {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                while (delayTime == 0) {
                    try {
                        Thread.sleep(60000);
                        resetNumOfRequests();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String uri = request.getRequestURI();
        if (uri.contains("third"))
            return true;


        incrementNumOfRequests();
        log.info("[number of requests within a minuit -> "+numOfRequests+"] / [delay duration -> "+delayTime+"]");
        if (getNumOfRequests()>=10)
            setDelayTime(10000);

        Thread.sleep(delayTime);
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
    private static synchronized void setDelayTime(long delayTime){
        RequestsCounterInterceptor.delayTime = delayTime;
    }
}
