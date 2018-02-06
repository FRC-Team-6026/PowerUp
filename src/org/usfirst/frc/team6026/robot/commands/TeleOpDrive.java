package org.usfirst.frc.team6026.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6026.robot.OI;
import org.usfirst.frc.team6026.robot.subsystems.*;

public class TeleOpDrive extends Command {
	
	public static DriveTrain driveTrain = new DriveTrain();

	public TeleOpDrive() {
		requires(driveTrain);
	}
	
	protected void initialize() {
		driveTrain.zeroMotorPositions();
	}
	
	protected void execute() {
		driveTrain.drive(OI.joystick.getY()/1.5, (OI.joystick.getX())/1.5);
	}
	
	protected void end() {
		
	}
	
	protected void interrupt() {
		
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
