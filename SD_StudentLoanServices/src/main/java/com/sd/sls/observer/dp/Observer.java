package com.sd.sls.observer.dp;
/*
 * @Author: Abhishek Vishwakarma
 */
import java.util.Map;

public interface Observer {
	//Update or Notify the observer
	public void update (Map<String, Object> values);
}
