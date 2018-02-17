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
		double jX = OI.driveJoystick.getRawAxis(4);
		double sY = OI.supportJoystick.getRawAxis(5);
		
		
		Robot.kLiftSubsystem.driveLiftMotor(sY/2);	// Drive lift up/down
		
		if( OI.supportJoystick.getRawAxis(3) > 0.1) {	// Eject
			Robot.kGripperSubsystem.driveGripperMotor((OI.supportJoystick.getRawAxis(3)*2)-1);
		}else if( OI.supportJoystick.getRawAxis(2) > 0.1) {	// Capture
			Robot.kGripperSubsystem.driveGripperMotor(-(OI.supportJoystick.getRawAxis(2)*2)-1);
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
		
		double speed1 = OI.driveJoystick.getRawAxis(2);
		double speed2 = OI.driveJoystick.getRawAxis(3);
		
		if(speed2 > 0.1) {
			Robot.kDriveTrainSubsystem.drive(speed2, jX);
		}else if(speed1 > 0.1) {
			Robot.kDriveTrainSubsystem.drive(-speed1, jX);
		}else {
			Robot.kDriveTrainSubsystem.drive(0, jX);
		}
		
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
