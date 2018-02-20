package org.usfirst.frc.team6026.robot.subsystems;

import org.usfirst.frc.team6026.robot.Robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gyro extends Subsystem{

	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	public Gyro() {
		gyro.reset();
		gyro.calibrate();
	}
	
	public void updateDashboard() {
		SmartDashboard.putString("Game Data", Robot.gameData);
		SmartDashboard.putNumber("Gyro Angle (degree)", gyro.getAngle());
		SmartDashboard.putNumber("Gyro Angle (rate)", gyro.getRate());
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
