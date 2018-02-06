package org.usfirst.frc.team6026.robot.subsystems;

import org.usfirst.frc.team6026.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem{

	private WPI_TalonSRX m_leftMotor;
	private WPI_TalonSRX m_rightMotor;
	double m_leftZeroPosition;
	double m_rightZeroPosition;

	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	public DriveTrain() {
		m_leftMotor = new WPI_TalonSRX(1);
		m_rightMotor = new WPI_TalonSRX(2);
		
		// 10ms Sample, 100ms Timeout
		m_leftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 10, 100);
		m_rightMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 10, 100);
		
		// Setup Encoder
		m_leftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		m_rightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		
		zeroMotorPositions();
	}
	
	public void zeroMotorPositions() {
		m_leftZeroPosition = m_leftMotor.getSelectedSensorPosition(0);
		m_rightZeroPosition = m_rightMotor.getSelectedSensorPosition(0);
	}
	
	public double getLeftMotorPosition() {
		return 0-(m_leftZeroPosition-m_leftMotor.getSelectedSensorPosition(0));
	}
	
	public double getRightMotorPosition() {
		return m_rightZeroPosition-(m_rightMotor.getSelectedSensorPosition(0));
	}
	
	public void driveLeftMotor(double d) {
		m_leftMotor.set(ControlMode.PercentOutput,0-d);		
	}
	
	public void driveRightMotor(double d) {
		m_rightMotor.set(ControlMode.PercentOutput,d);
	}
	
	public void drive (double speed, double rotation) { 
		double d = 22;	// distance between L & R wheels
		
		driveLeftMotor(speed - ((d*rotation)/2));
		driveRightMotor(speed + ((d*rotation)/2));
	}

}
