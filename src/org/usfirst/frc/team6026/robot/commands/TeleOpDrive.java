package org.usfirst.frc.team6026.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6026.robot.OI;
import org.usfirst.frc.team6026.robot.Robot;

public class TeleOpDrive extends Command {

	public TeleOpDrive() {
		requires(Robot.kLiftSubsystem);
		requires(Robot.kDriveTrainSubsystem);
		requires(Robot.kGripperSubsystem);
	}
	
	protected void initialize() {
		Robot.kDriveTrainSubsystem.zeroMotorPositions();
	}
	
	protected void execute() {
		double jY = OI.driveJoystick.getY();
		double jX = OI.driveJoystick.getX();
		
		if( OI.driveJoystick.getRawButton(1)) {
			Robot.kLiftSubsystem.driveLiftMotor(0.5);	// Down Bottom Limit
		}else if( OI.driveJoystick.getRawButton(2)) {
			Robot.kLiftSubsystem.driveLiftMotor(-0.5);	// Up (Top Limit)
		}else{
			Robot.kLiftSubsystem.driveLiftMotor(0);
		}
		
		if( OI.driveJoystick.getRawAxis(3) > 0.1) {	// Eject
			Robot.kGripperSubsystem.driveGripperMotor((OI.driveJoystick.getRawAxis(3)*2)-1);
		}else if( OI.driveJoystick.getRawAxis(2) > 0.1) {	// Capture
			Robot.kGripperSubsystem.driveGripperMotor(-(OI.driveJoystick.getRawAxis(2)*2)-1);
		}else {
			Robot.kGripperSubsystem.driveGripperMotor(0.0);
		}
		
		if(OI.driveJoystick.getRawButton(3)) {
			Robot.kGripperSubsystem.driveTiltMotor(0.5);
		} else if(OI.driveJoystick.getRawButton(4)) {
			Robot.kGripperSubsystem.driveTiltMotor(-0.5);
		}
			
		SmartDashboard.putNumber("JoyX", jX);
		SmartDashboard.putNumber("JoyY", jY);
		
		Robot.kDriveTrainSubsystem.drive(-jY, jX);
		
		SmartDashboard.putNumber("Lift (mm)", Robot.kLiftSubsystem.getLiftMotorPosition());
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
