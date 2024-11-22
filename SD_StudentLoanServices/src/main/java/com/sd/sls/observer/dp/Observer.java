package com.sd.sls.observer.dp;
/*
 * @Author: Abhishek Vishwakarma
 */
import java.util.Map;

public interface Observer {
	public void update (Map<String, Object> values);
}
