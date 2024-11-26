package com.sd.sls.interceptor.dp;

import org.springframework.stereotype.Component;

@Component
public class InterceptorDispatcher {
	private final InterceptorFramework framework;
	
	private Context context;
	
	public InterceptorDispatcher(InterceptorFramework framework) {
		this.framework = framework;
	}
	
	public void setContext(Context context)
	{
		this.context = context;
	}

	public void dispatchEvent(Context context) {
		if (context == null)
		{
			throw new IllegalArgumentException("Context Not Found");
		}
		framework.triggerInterceptors(context);
	}
}
