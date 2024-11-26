package com.sd.sls.interceptor.dp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class InterceptorFramework {
	private final List<Interceptor> interceptors = new ArrayList<>();
	
	public void registerInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public void triggerInterceptors(Context context) {
        for (Interceptor interceptor : interceptors) {
            interceptor.eventCallBack(context);
        }
    }
	
}
