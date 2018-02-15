package org.usfirst.frc.team6026.robot.subsystems;

import org.usfirst.frc.team6026.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem{

	private WPI_TalonSRX m_leftMotor;
	private WPI_TalonSRX m_rightMotor;
	private WPI_TalonSRX m_leftSlaveMotor;
	private WPI_TalonSRX m_rightSlaveMotor;
	double m_leftZeroPosition;
	double m_rightZeroPosition;
	private DifferentialDrive diffDrive;

	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	public DriveTrain() {
		m_leftMotor = new WPI_TalonSRX(RobotMap.masterLeftMotor);
		m_rightMotor = new WPI_TalonSRX(RobotMap.masterRightMotor);
		m_leftSlaveMotor = new WPI_TalonSRX(RobotMap.slaveLeftMotor);
		m_rightSlaveMotor = new WPI_TalonSRX(RobotMap.slaveRightMotor);

		// set up leftSlave/rightSlave in follower mode
		m_leftSlaveMotor.set(ControlMode.Follower, RobotMap.masterLeftMotor);
		m_rightSlaveMotor.set(ControlMode.Follower, RobotMap.masterRightMotor);
		
		// 10ms Sample, 100ms Timeout
		m_leftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 10, 100);
		m_rightMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 10, 100);
		
		// Setup Encoder
		m_leftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		m_rightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		
		diffDrive = new DifferentialDrive(
				m_leftMotor,
				m_rightMotor
			);
		
		zeroMotorPositions();
	}
	
	public void updateDashboard() {
		SmartDashboard.putNumber("LMotor Current (A)", m_leftMotor.getOutputCurrent());
		SmartDashboard.putNumber("RMotor Current (A)", m_rightMotor.getOutputCurrent());
		SmartDashboard.putNumber("LMotor Velocity",  m_leftMotor.getActiveTrajectoryVelocity());
		SmartDashboard.putNumber("RMotor Velocity",  m_rightMotor.getActiveTrajectoryVelocity());
		SmartDashboard.putNumber("LMotor Encoder", m_leftMotor.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("RMotor Encoder", 0-m_rightMotor.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("LMotor Encoder Abs", getLeftMotorPosition() );
		SmartDashboard.putNumber("RMotor Encoder Abs", getRightMotorPosition() );
	}
	
	public void zeroMotorPositions() {
		m_leftZeroPosition = m_leftMotor.getSelectedSensorPosition(0);
		m_rightZeroPosition = m_rightMotor.getSelectedSensorPosition(0);
	}
	
	public double getLeftMotorPosition() {
		// Wheel Diameter 7.72" diameter => 24.25" Circumference
		// Encoder yields 4096 pulses per revolution.
		// 4096 counts = 24.25" of travel or 615.95mm
		
		double encoder =  (m_leftZeroPosition-m_leftMotor.getSelectedSensorPosition(0));
		//return (encoder * (24.25 / 4096.0));	// inches
		return (encoder * (615.95 / 4096.0));	// mm
	}
	
	public double getRightMotorPosition() {
		double encoder = 0-(m_rightZeroPosition-m_rightMotor.getSelectedSensorPosition(0));
		//return (encoder * (24.25 / 4096.0));	// inches
		return (encoder * (615.95 / 4096.0));	// mm
	}
	
	public void driveLeftMotor(double d) {
		m_leftMotor.set(ControlMode.PercentOutput,0-d);		
	}
	
	public void driveRightMotor(double d) {
		m_rightMotor.set(ControlMode.PercentOutput,d);
	}
	
	
	public void drive (double speed, double rotation) {
		diffDrive.arcadeDrive(speed/1.5, rotation);
		updateDashboard();
	}
	
	public void drive2 (double speed, double rotation) { 
		double d = 22;	// distance between L & R wheels
		
		double left = speed - ((d*rotation)/2);
		double right = speed + ((d*rotation)/2);
		
		SmartDashboard.putNumber("Left",left);
		SmartDashboard.putNumber("Right", right);
		
		driveLeftMotor(left);
		driveRightMotor(right);

		//driveLeftMotor(0);
		//driveRightMotor(0);
		
		updateDashboard();
	}

}
