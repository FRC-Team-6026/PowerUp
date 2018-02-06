package org.usfirst.frc.team6026.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SimplePID {
	double dState;
	double iState;
	double iMax, iMin;
	double iGain, pGain, dGain;
	
	public double update (double position, double target){ //im @ 3, headed to 5
		double error = target - position;
		double p = pGain * error;
		
		iState += error; 
		if (iState > iMax) iState = iMax;
		else if (iState < iMin) iState = iMin;
		double i = iGain * iState;
		double d = dGain * (dState - position);
		dState = position;
		
		SmartDashboard.putNumber("iState", iState);
		SmartDashboard.putNumber("pidOut",  p+i+d);
		return (p+i+d);
	}
		
		
	public SimplePID(double pG, double iG, double dG, double diMin, double diMax){
		iState = iMin + (iMax - iMin/2);
		pGain = pG;
		iGain = iG;
		dGain = dG;
		iMax = diMax;
		iMin = diMin;
	}
	
}
