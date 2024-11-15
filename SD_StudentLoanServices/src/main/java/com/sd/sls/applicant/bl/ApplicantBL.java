package com.sd.sls.applicant.bl;

/*
 * @Author: Abhishek Vishwakarma
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.sls.applicant.constants.ApplicantConstants;
import com.sd.sls.applicant.dao.IApplicantDAO;
import com.sd.sls.applicant.model.Applicant;
import com.sd.sls.user.bl.IUserBusinessLogic;
import com.sd.sls.user.dao.IUserDAO;
import com.sd.sls.user.model.User;

@Service
public class ApplicantBL implements IApplicantBL {
	
	@Autowired
	private IApplicantDAO applicantDAO;
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IUserBusinessLogic userBusinessLogic;
	
	@Override
	public Map<String, Boolean> registerApplicant(Map<String, Object> userValues)
	{
		Map<String, Boolean> returnMap = new HashMap<>();
		Applicant applicant = createApplicant(userValues);
		if(checkIfApplicantAlreadyExists(applicant))
		{
			returnMap.put(ApplicantConstants.APPLICANT_ALREADY_REGISTERED, true);
			return returnMap;
		}
		
		if (applicant.getUser() == null)
		{
			if (userValues.containsKey("email") && userValues.containsKey("password") && userValues.containsKey("phoneNumber"))
			{
				User user = userBusinessLogic.createUser(userValues);
				user.setUserName(applicant.getFirstName() + applicant.getLastName());
				if (userDAO.registerUser(user) == 1)
				{
					user = userDAO.findUserByEmail(Objects.toString(userValues.get("email")));
					applicant.setUser(user);
				}
			}
			else
			{
				returnMap.put(ApplicantConstants.NO_USER_FOUND, true);
				return returnMap;
			}
		}
		
		if (applicantDAO.registerApplicant(applicant) == 1)
		{
			returnMap.put(ApplicantConstants.APPLICANT_REGISTERED, true);
		}
		
		return returnMap;
	}

	private Applicant createApplicant (Map<String, Object> userValues)
	{
		Applicant applicant = new Applicant();
		User user = userDAO.findUserByEmail(Objects.toString(userValues.get("email")));
		applicant.setUser(user == null ? null : user);
		applicant.setFirstName(Objects.toString(userValues.get("firstName")));
		applicant.setLastName(Objects.toString(userValues.get("lastName")));
		String dateString = Objects.toString(userValues.get("dateOfBirth"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        java.sql.Date sqlDate = null;
        try 
        {
            Date utilDate = formatter.parse(dateString);
            sqlDate = new java.sql.Date(utilDate.getTime());
        }
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
		applicant.setDateOfBirth(sqlDate);
		applicant.setAddress(Objects.toString(userValues.get("address")));
		applicant.setEducationDetails(Objects.toString(userValues.get("educationDetails")));
		applicant.setMembershipType(Objects.toString(userValues.get("membershipType")));
		applicant.setEmail(Objects.toString(userValues.get("email")));
		return applicant;
	}
	
	private boolean checkIfApplicantAlreadyExists(Applicant applicant)
	{
		return applicantDAO.checkIfApplicantAlreadyExists(applicant);
	}
}
