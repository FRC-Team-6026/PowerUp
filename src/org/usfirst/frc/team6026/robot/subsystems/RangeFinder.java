package org.usfirst.frc.team6026.robot.subsystems;

import org.usfirst.frc.team6026.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;

public class RangeFinder extends Subsystem{

	private AnalogInput leftRangeSensor = new AnalogInput(RobotMap.rangefinderLeftPort);
	private AnalogInput rightRangeSensor = new AnalogInput(RobotMap.rangefinderRightPort);
	private AnalogInput frontRangeSensor = new AnalogInput(RobotMap.rangefinderFrontPort);
	
	private DigitalOutput leftRangeEnable = new DigitalOutput(RobotMap.rangefinderLeftPort);
	private DigitalOutput rightRangeEnable = new DigitalOutput(RobotMap.rangefinderRightPort);
	private DigitalOutput frontRangeEnable = new DigitalOutput(RobotMap.rangefinderFrontPort);
	
	public RangeFinder() {
		
	}
	
	public void clearDashboard() {
		SmartDashboard.putNumber("Left Range(mm)", 0);
		SmartDashboard.putNumber("Right Range(mm)", 0);
		SmartDashboard.putNumber("Front Range(mm)", 0);
	}
	
	public void rangeSelect(int n) {
		switch(n) {
		default:
		case 0:	// Front
			leftRangeEnable.set(false);
			rightRangeEnable.set(false);
			frontRangeEnable.set(true);
			break;
		case 1:	// Right
			leftRangeEnable.set(false);
			rightRangeEnable.set(true);
			frontRangeEnable.set(false);
			break;
		case 2:	// Left
			leftRangeEnable.set(true);
			rightRangeEnable.set(false);
			frontRangeEnable.set(false);
			break;
		}
	}
	
	public double getLeftRange() {
		rangeSelect(2);
		double volts = leftRangeSensor.getVoltage();
		double range = 5.0 * (volts/0.004883);
		SmartDashboard.putNumber("Left Range(mm)", range);
		return range;
	}
	
	public double getRightRange() {
		rangeSelect(1);
		double volts = rightRangeSensor.getVoltage();
		double range = 5.0 * (volts/0.004883);
		SmartDashboard.putNumber("Right Range(mm)", range);
		return range;
	}
	
	public double getFrontRange() {
		rangeSelect(0);
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
