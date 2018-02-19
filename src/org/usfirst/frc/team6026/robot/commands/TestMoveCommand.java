package org.usfirst.frc.team6026.robot.commands;

import org.usfirst.frc.team6026.robot.Robot;
import org.usfirst.frc.team6026.robot.SimplePID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestMoveCommand extends Command {

	private SimplePID m_MovePID = new SimplePID(0.003, 0, 0, 0, 0);	// move PID
	
	public TestMoveCommand() {
		requires(Robot.kGyroSubsystem);
		requires(Robot.kDriveTrainSubsystem);
	}
	
	protected void initialize() {
		Robot.kGyroSubsystem.gyro.reset();
		Robot.kDriveTrainSubsystem.zeroMotorPositions();
	}
	
	protected void execute() {
		
		// Move 1000 Counts (1000mm)
		double position = (Robot.kDriveTrainSubsystem.getLeftMotorPosition() + Robot.kDriveTrainSubsystem.getRightMotorPosition() ) / 2;
		double target = 2616;
		double output = - m_MovePID.update(position, target);
		
		SmartDashboard.putNumber("position", position);
		SmartDashboard.putNumber("output", output);
		
		// TODO: Use Gyro To Maintain Heading
		
		Robot.kDriveTrainSubsystem.drive(output/4.0,0);
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
