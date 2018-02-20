package org.usfirst.frc.team6026.robot.commands;

import org.usfirst.frc.team6026.robot.Robot;
import org.usfirst.frc.team6026.robot.SimplePID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

import java.lang.Math;

public class TestFollowWallCommand extends Command{

	private SimplePID m_RotatePID = new SimplePID(0.035, 0.001, 0, -1000, 1000);	// rotation PID
	private SimplePID m_MovePID = new SimplePID(0.003, 0.001, 0, -1000, 1000);	// move PID
	
	private int moveState = 0;
	
	public TestFollowWallCommand() {
		requires(Robot.kGyroSubsystem);
		requires(Robot.kDriveTrainSubsystem);
		requires(Robot.kRangeFinderSubsystem);
		Robot.kDriveTrainSubsystem.useBrakes(true);
	}
	
	protected void initialize() {
		Robot.kGyroSubsystem.gyro.reset();
		Robot.kGyroSubsystem.gyro.calibrate();
		
		Robot.kDriveTrainSubsystem.zeroMotorPositions();
		Robot.kRangeFinderSubsystem.clearDashboard();
		Robot.kDriveTrainSubsystem.useBrakes(true);
		
		Robot.kRangeFinderSubsystem.getLeftRange();
		Robot.kRangeFinderSubsystem.getRightRange();
		Robot.kRangeFinderSubsystem.getFrontRange();

		final char localSwitch = Robot.gameData.length() > 0 ? Robot.gameData.charAt(0) : '?';
		final char middleScale = Robot.gameData.length() > 1 ? Robot.gameData.charAt(1) : '?';
		final char remoteSwitch = Robot.gameData.length() > 2 ? Robot.gameData.charAt(2) : '?';
	
		moveState = 0;
		SmartDashboard.putNumber("AutoState", moveState);
	}
	
	private double move(double target) {
		double position = (Robot.kDriveTrainSubsystem.getLeftMotorPosition() + Robot.kDriveTrainSubsystem.getRightMotorPosition() ) / 2;
		//double target = 500;
		double output = m_MovePID.update(position, target);
		SmartDashboard.putNumber("target", target);
		SmartDashboard.putNumber("position", position);
		SmartDashboard.putNumber("output", output);
		Robot.kDriveTrainSubsystem.drive(output/1.5,0);
		return (target-position);
	}
	
	private double rotate(double target) {
		double position = Robot.kGyroSubsystem.gyro.getAngle();
		//double target = degrees;
		double output = m_RotatePID.update(position, target);
		SmartDashboard.putNumber("target", target);
		SmartDashboard.putNumber("position", position);
		SmartDashboard.putNumber("output", output);
		Robot.kDriveTrainSubsystem.drive(0, output);
		return (target-position);
	}
	
	protected void execute() {
		
		Robot.kDriveTrainSubsystem.updateDashboard();
		Robot.kGyroSubsystem.updateDashboard();
		
		switch( moveState ) {
		case 0:
			// Move 500mm
			if( Math.abs(move(500)) < 150 ) {
				moveState = 1;
				SmartDashboard.putNumber("AutoState", moveState);
				Robot.kDriveTrainSubsystem.zeroMotorPositions();
				Timer.delay(1.0);				
			}
			break;
		case 1:
			if( Math.abs(rotate(45)) < 2 ) {
				moveState = 2;
				SmartDashboard.putNumber("AutoState", moveState);
				Robot.kDriveTrainSubsystem.zeroMotorPositions();
				Timer.delay(1.0);
			}
			break;
		case 2:
			// Move 500mm
			if( Math.abs(move(500)) < 150 ) {
				moveState = 3;
				SmartDashboard.putNumber("AutoState", moveState);
				Robot.kDriveTrainSubsystem.zeroMotorPositions();
				Timer.delay(1.0);
			}
			break;
		case 3:
			// Rotate
			if( Math.abs(rotate(0)) < 2 ) {
				moveState = 4;
				Robot.kDriveTrainSubsystem.drive(0, 0);
				SmartDashboard.putNumber("AutoState", moveState);
				Robot.kDriveTrainSubsystem.zeroMotorPositions();
				Timer.delay(1.0);
			}
			break;
		case 4:
			// Move 500mm
			if( Math.abs(move(500)) < 150 ) {
				moveState = 5;
				Robot.kDriveTrainSubsystem.drive(0, 0);
				SmartDashboard.putNumber("AutoState", moveState);
				Robot.kDriveTrainSubsystem.zeroMotorPositions();
				Timer.delay(1.0);
			}
			break;
		default:
			Robot.kDriveTrainSubsystem.drive(0, 0);
		}
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
