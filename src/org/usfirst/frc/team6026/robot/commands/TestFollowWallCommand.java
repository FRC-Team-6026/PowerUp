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
	}
	
	protected void execute() {
		double forward = 0.0;
		double rotate = 0.0;
		
		// TODO: Drive Forward Until Reach Target (Front Object < 500mm)
		
		// TODO: Maintain 500mm distance from wall (Left or Right based on GameData)
		if( Robot.gameData.charAt(0) == 'L') {
			// wall on left
		}else {
			// wall on right
		}
		
		Robot.kDriveTrainSubsystem.drive(forward,rotate);
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
