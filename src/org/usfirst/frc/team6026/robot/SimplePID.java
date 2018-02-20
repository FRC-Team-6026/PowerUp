package org.usfirst.frc.team6026.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SimplePID {
	double dState;
	double iState;
	double iMax, iMin;
	double iGain, pGain, dGain;
	
	public void display(double p, double i, double d, double is, double ds, double err) {
		SmartDashboard.putNumber("p", p);
		SmartDashboard.putNumber("i", i);
		SmartDashboard.putNumber("d", d);
		SmartDashboard.putNumber("is", is);
		SmartDashboard.putNumber("ds",  ds);
		SmartDashboard.putNumber("e", err);
		
		double outs[] = {p,i,d,is,ds,err};
		SmartDashboard.putNumberArray("pid", outs);
	}
	
	public double update (double position, double target){ //im @ 3, headed to 5
		double error = target - position;
		double p = pGain * error;
		
		iState += error; 
		if (iState > iMax) iState = iMax;
		else if (iState < iMin) iState = iMin;
		double i = iGain * iState;
		double d = dGain * (dState - position);
		dState = position;
		
		display(p, i, d, iState, dState, error);
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
