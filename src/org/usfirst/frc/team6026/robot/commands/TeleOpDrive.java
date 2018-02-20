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
		requires(Robot.kRangeFinderSubsystem);
	}
	
	protected void initialize() {
		Robot.kDriveTrainSubsystem.zeroMotorPositions();
		Robot.kDriveTrainSubsystem.useBrakes(false);
	}
	
	protected void execute() {
		double jY = OI.driveJoystick.getY();
		double jX = OI.driveJoystick.getRawAxis(4);
		double sY = OI.supportJoystick.getRawAxis(5);
		//double rF = Robot.kRangeFinderSubsystem.getFrontRange();
		double rR = Robot.kRangeFinderSubsystem.getRightRange();
		//double rL = Robot.kRangeFinderSubsystem.getLeftRange();
		double lTrigger = OI.driveJoystick.getRawAxis(2);
		double rTrigger = OI.driveJoystick.getRawAxis(3);
		
		Robot.kDriveTrainSubsystem.updateDashboard();
		
		// Lift
		Robot.kLiftSubsystem.driveLiftMotor(sY/2);
		
		// Gripper
		if( OI.supportJoystick.getRawAxis(3) > 0.01) {	// Capture
			Robot.kGripperSubsystem.driveGripperMotor(((OI.supportJoystick.getRawAxis(3)/2)+0.5));
		}else if( OI.supportJoystick.getRawAxis(2) > 0.01) {	// Eject
			Robot.kGripperSubsystem.driveGripperMotor(-((OI.supportJoystick.getRawAxis(2)/2)+0.5));
		}else if( OI.supportJoystick.getRawButton(5)) {	// Slow Eject
			Robot.kGripperSubsystem.driveGripperMotor(-0.25);
		}else {
			Robot.kGripperSubsystem.driveGripperMotor(0.0);
		}
		
		// Tilt
		if(OI.driveJoystick.getRawButton(3)) {
			Robot.kGripperSubsystem.driveTiltMotor(0.5);
		} else if(OI.driveJoystick.getRawButton(4)) {
			Robot.kGripperSubsystem.driveTiltMotor(-0.5);
		}
		
		// Drive
		if(rTrigger > 0.1) {
			Robot.kDriveTrainSubsystem.drive(rTrigger, jX);
		}else if(lTrigger > 0.1) {
			Robot.kDriveTrainSubsystem.drive(-lTrigger, jX);
		}else {
			Robot.kDriveTrainSubsystem.drive(0, jX);
		}
		
		// LIFT ENCODER REMOVED
		// SmartDashboard.putNumber("Lift (mm)", Robot.kLiftSubsystem.getLiftMotorPosition());
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
