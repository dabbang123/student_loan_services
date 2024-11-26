package com.sd.sls.interceptor.dp;

import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorConfig {
	
	public InterceptorConfig(InterceptorFramework framework, LoggingInterceptor loggingInterceptor) 
	{
		framework.registerInterceptor(loggingInterceptor);
	}
}
