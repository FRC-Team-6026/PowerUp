package org.usfirst.frc.team6026.robot.commands;

import org.usfirst.frc.team6026.robot.OI;
import org.usfirst.frc.team6026.robot.Robot;
import org.usfirst.frc.team6026.robot.SimplePID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Test360Command extends Command {

	//private SimplePID m_RotatePID = new SimplePID(0.02, 0.0006, 0, -10000, 10000);  // Best so far
	private SimplePID m_RotatePID = new SimplePID(0.035, 0.001, 0, -1000, 1000);	// rotation PID
	
	public Test360Command() {
		requires(Robot.kGyroSubsystem);
		requires(Robot.kDriveTrainSubsystem);
	}
	
	protected void initialize() {
		Robot.kGyroSubsystem.gyro.reset();
		Robot.kDriveTrainSubsystem.zeroMotorPositions();
		Robot.kDriveTrainSubsystem.useBrakes(false);
	}
	
	protected void execute() {

		// Rotate
		double position = Robot.kGyroSubsystem.gyro.getAngle();
		double target = -45;
		double output = m_RotatePID.update(position, target);

		Robot.kDriveTrainSubsystem.updateDashboard();
		Robot.kGyroSubsystem.updateDashboard();
		
		SmartDashboard.putNumber("Rot Pos", position);
		SmartDashboard.putNumber("Rot Out", output);
		
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
