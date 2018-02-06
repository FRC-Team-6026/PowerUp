package org.usfirst.frc.team6026.robot.commands;

import org.usfirst.frc.team6026.robot.OI;
import org.usfirst.frc.team6026.robot.Robot;
import org.usfirst.frc.team6026.robot.SimplePID;

import edu.wpi.first.wpilibj.command.Command;

public class Test360Command extends Command {

	//private SimplePID m_RotatePID = new SimplePID(0.003, 0.0003, 0, 0, 0.1);  // best so far
	private SimplePID m_RotatePID = new SimplePID(0.0025, 0.0001, 0, 0, 1000);	// rotation PID
	
	public Test360Command() {
		requires(Robot.kGyroSubsystem);
		requires(Robot.kDriveTrainSubsystem);
	}
	
	protected void initialize() {
		Robot.kGyroSubsystem.gyro.reset();
		Robot.kDriveTrainSubsystem.zeroMotorPositions();
	}
	
	protected void execute() {
		Robot.kDriveTrainSubsystem.drive(OI.driveJoystick.getY()/1.5, (OI.driveJoystick.getX())/1.5);
		
		// Rotate 360
		double position = Robot.kGyroSubsystem.gyro.getAngle();
		double target = 360;
		double output = m_RotatePID.update(position, target);
		
		Robot.kDriveTrainSubsystem.drive(0, output);
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
