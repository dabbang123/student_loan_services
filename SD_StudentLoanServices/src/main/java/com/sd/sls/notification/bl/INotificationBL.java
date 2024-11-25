package com.sd.sls.notification.bl;

import java.util.List;
import java.util.Map;

import com.sd.sls.notification.model.Notification;

public interface INotificationBL {
	public void update(Map<String, Object> values);
	
	public List<Notification> checkNotifications();

}
