package com.sd.sls.loan.offer.decorator;

/*
 * Author: Rushabh Botadra
 */
public class NormalInterestRate implements IInterestRate{

	@Override
	public double getRate() 
	{
		return 10.0;
	}
}
