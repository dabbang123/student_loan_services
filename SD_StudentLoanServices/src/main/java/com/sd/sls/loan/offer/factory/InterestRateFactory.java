package com.sd.sls.loan.offer.factory;

/*
 * Author: Rushabh Botadra
 */
import com.sd.sls.loan.offer.decorator.GoldMemberShipDecorator;
import com.sd.sls.loan.offer.decorator.IInterestRate;
import com.sd.sls.loan.offer.decorator.NormalInterestRate;
import com.sd.sls.loan.offer.decorator.SilverMemberShipDecorator;
import com.sd.sls.membershiptype.MembershipType;

public class InterestRateFactory {
	
	public static IInterestRate getInterestRateFactory(String membershipType)
	{
		if(membershipType.equals(MembershipType.GOLD.getMembershipType()))
		{
			return new GoldMemberShipDecorator(new NormalInterestRate());
		}
		else if(membershipType.equals(MembershipType.SILVER.getMembershipType()))
		{
			return new SilverMemberShipDecorator(new NormalInterestRate());
		}
		else if(membershipType.equals(MembershipType.REGULAR.getMembershipType()))
		{
			return new NormalInterestRate();
		}
		else
		{
			throw new IllegalArgumentException("Unexpected value: " + membershipType);
		}
	}

}
