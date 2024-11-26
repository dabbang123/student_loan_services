package com.sd.sls.interceptor.dp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class InterceptorDispatcher {
	private final List<Interceptor> interceptors = new ArrayList<>();
	
	public void attach(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public void dispatchEvent(Context context) {
        for (Interceptor interceptor : interceptors) {
            interceptor.eventCallBack(context);
        }
    }
}
