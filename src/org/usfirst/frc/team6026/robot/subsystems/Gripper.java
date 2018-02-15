package org.usfirst.frc.team6026.robot.subsystems;

import org.usfirst.frc.team6026.robot.Robot;
import org.usfirst.frc.team6026.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gripper extends Subsystem{

	private WPI_TalonSRX m_leftGripperMotor;
	private WPI_TalonSRX m_rightGripperMotor;
	private WPI_TalonSRX m_gripperTiltMotor;
	
	public Gripper() {
		m_leftGripperMotor = new WPI_TalonSRX(RobotMap.gripperLeftMotor);
		m_rightGripperMotor = new WPI_TalonSRX(RobotMap.gripperRightMotor);
		m_gripperTiltMotor = new WPI_TalonSRX(RobotMap.gripperTiltMotor);
	}
	
	public void driveGripperMotor(double d) {
		m_leftGripperMotor.set(ControlMode.PercentOutput,-d);
		m_rightGripperMotor.set(ControlMode.PercentOutput,d);
	}
	
	public void driveTiltMotor(double d) {
		m_gripperTiltMotor.set(ControlMode.PercentOutput, d);
	}

	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}