package com.sd.sls.applicant.bs;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.sls.applicant.bl.IApplicantBL;
import com.sd.sls.observer.dp.Observer;
import com.sd.sls.observer.dp.Subject;

@Service
public class ApplicantBS implements IApplicantBS, Subject {
	
	private final List<Observer> observers = new ArrayList<>();
	
	@Autowired
	private IApplicantBL applicantBL;

	@Override
	public Map<String, Boolean> registerApplicant(Map<String, Object> userValues) 
	{
		Map<String, Boolean> registerApplicantMap = applicantBL.registerApplicant(userValues);
		notifyObserver(userValues);
		return registerApplicantMap;
	}

	@Override
	public void addObserver(Observer observer) 
	{
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) 
	{
		observers.remove(observer);
	}

	@Override
	public void notifyObserver(Map<String, Object> values) 
	{
		for (Observer observer : observers) 
		{
			observer.update(values);
		}
	}
}
