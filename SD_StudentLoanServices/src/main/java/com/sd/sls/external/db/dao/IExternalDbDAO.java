package com.sd.sls.external.db.dao;

import java.util.List;

/*
 * Author: Rushabh Botadra
 */

import com.sd.sls.external.db.model.ExternalDb;

public interface IExternalDbDAO {
	public boolean checkItrForBusiness(String uinNo);
	
	public boolean checkItrForSalaried(String uinNo);
}
