package org.usfirst.frc.team6026.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

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
		Robot.kDriveTrainSubsystem.drive(OI.driveJoystick.getY()/1.5, (OI.driveJoystick.getX())/1.5);
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
