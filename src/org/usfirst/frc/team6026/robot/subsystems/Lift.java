package org.usfirst.frc.team6026.robot.subsystems;

import org.usfirst.frc.team6026.robot.Robot;
import org.usfirst.frc.team6026.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift extends Subsystem{

	private WPI_TalonSRX m_liftMotor;
	double m_liftZeroPosition;
	
	public Lift() {
		m_liftMotor = new WPI_TalonSRX(RobotMap.liftMotor);
		// 10ms Sample, 100ms Timeout
		m_liftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 10, 100);
		// Setup Encoder
		m_liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		// Limit Switches
		m_liftMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
		m_liftMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);

		zeroMotorPositions();
	}
	
	public void driveLiftMotor(double d) {
		m_liftMotor.set(ControlMode.PercentOutput,0-d);
	}
	
	public double getLiftMotorPosition() {
		// 4096 counts = 1 revolution
		// 22 Teeth / revolution
		// 1 tooth / 0.25"
		// 4096 counts / rev * 1 rev/22 teeth * 4 teeth / inch * (2:1 ration for 2 stage lift)
		// 4096 counts = 11 inches =  mm 
		
		
		double encoder =  (m_liftZeroPosition-m_liftMotor.getSelectedSensorPosition(0));
		//return (encoder * (11.0 / 4096.0));	// inches
		return (encoder * (279.4 / 4096.0));	// mm
	}
	
	public void zeroMotorPositions() {
		m_liftZeroPosition = m_liftMotor.getSelectedSensorPosition(0);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}