package org.usfirst.frc.team6026.robot.commands;

import org.usfirst.frc.team6026.robot.Robot;
import org.usfirst.frc.team6026.robot.SimplePID;

import edu.wpi.first.wpilibj.command.Command;

public class TestFollowWallCommand extends Command{

	private SimplePID m_MovePID = new SimplePID(0.0025, 0, 0, 0, 0);	// move PID
	private SimplePID m_WallPID = new SimplePID(0.0025, 0, 0, 0, 0);	// wall PID
	
	public TestFollowWallCommand() {
		requires(Robot.kGyroSubsystem);
		requires(Robot.kDriveTrainSubsystem);
		requires(Robot.kRangeFinderSubsystem);
	}
	
	protected void initialize() {
		Robot.kGyroSubsystem.gyro.reset();
		Robot.kDriveTrainSubsystem.zeroMotorPositions();
		Robot.kRangeFinderSubsystem.clearDashboard();
		
		Robot.kRangeFinderSubsystem.getLeftRange();
		Robot.kRangeFinderSubsystem.getRightRange();
		Robot.kRangeFinderSubsystem.getFrontRange();
	}
	
	protected void execute() {
		double forward = 0.0;
		double rotate = 0.0;
		
		final char localSwitch = Robot.gameData.length() > 0 ? Robot.gameData.charAt(0) : '?';
		final char middleScale = Robot.gameData.length() > 1 ? Robot.gameData.charAt(1) : '?';
		final char remoteSwitch = Robot.gameData.length() > 2 ? Robot.gameData.charAt(2) : '?';
		
		// TODO: Drive Forward Until Reach Target (Front Object < 500mm)
		
		
		// TODO: Maintain 500mm distance from wall (Left or Right based on GameData)
		
		
		Robot.kDriveTrainSubsystem.drive(forward,rotate);
	}
	
	protected void end() {
		
	}
	
	protected void interrupt() {
		
	}
	
	@Override
	protected boolean isFinished() {
		// TO DO Auto-generated method stub
		return false;
	}

}
