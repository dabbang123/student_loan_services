package com.sd.sls.notification.bs;

import java.util.List;
import java.util.Map;

import com.sd.sls.notification.model.Notification;

public interface INotificationBS {

	public void update(Map<String, Object> values);
	
	public List<Notification> checkNotifications();

}
