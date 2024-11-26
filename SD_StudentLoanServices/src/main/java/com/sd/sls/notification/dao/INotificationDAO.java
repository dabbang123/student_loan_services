package com.sd.sls.notification.dao;

import java.util.List;

import com.sd.sls.notification.model.Notification;

public interface INotificationDAO {
	public void update(Notification notification);
	
	public List<Notification> checkNotifications();
}
