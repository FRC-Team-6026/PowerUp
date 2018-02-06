package org.usfirst.frc.team6026.robot.subsystems;

import org.usfirst.frc.team6026.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;

public class RangeFinder extends Subsystem{

	private AnalogInput leftRangeSensor = new AnalogInput(RobotMap.rangefinderLeftPort);
	private AnalogInput rightRangeSensor = new AnalogInput(RobotMap.rangefinderRightPort);
	private AnalogInput frontRangeSensor = new AnalogInput(RobotMap.rangefinderFrontPort);
	
	public RangeFinder() {
		
	}
	
	public void clearDashboard() {
		SmartDashboard.putNumber("Left Range(mm)", 0);
		SmartDashboard.putNumber("Right Range(mm)", 0);
		SmartDashboard.putNumber("Front Range(mm)", 0);
	}
	
	public double getLeftRange() {
		double volts = leftRangeSensor.getVoltage();
		double range = 5.0 * (volts/0.004883);
		SmartDashboard.putNumber("Left Range(mm)", range);
		return range;
	}
	
	public double getRightRange() {
		double volts = rightRangeSensor.getVoltage();
		double range = 5.0 * (volts/0.004883);
		SmartDashboard.putNumber("Right Range(mm)", range);
		return range;
	}
	
	public double getFrontRange() {
		double volts = frontRangeSensor.getVoltage();
		double range = 5.0 * (volts/0.004883);
		SmartDashboard.putNumber("Front Range(mm)", range);
		return range;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
