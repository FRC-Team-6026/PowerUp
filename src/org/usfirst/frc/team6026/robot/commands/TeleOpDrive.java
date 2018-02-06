package org.usfirst.frc.team6026.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6026.robot.OI;
import org.usfirst.frc.team6026.robot.Robot;

public class TeleOpDrive extends Command {

	public TeleOpDrive() {
		requires(Robot.kDriveTrainSubsystem);
	}
	
	protected void initialize() {
		Robot.kDriveTrainSubsystem.zeroMotorPositions();
	}
	
	protected void execute() {
		double jX = OI.driveJoystick.getY();
		double jY = OI.driveJoystick.getX();
		SmartDashboard.putNumber("JoyX", jX);
		SmartDashboard.putNumber("JoyY", jY);
		Robot.kDriveTrainSubsystem.drive(jX, jY);
	}
	
	protected void end() {
		
	}
	
	protected void interrupt() {
		
	}
	
	@Override
	protected boolean isFinished() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		
		return false;
	}

}
