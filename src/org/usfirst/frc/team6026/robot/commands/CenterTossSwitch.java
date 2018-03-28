package org.usfirst.frc.team6026.robot.commands;

import org.usfirst.frc.team6026.robot.Robot;
import org.usfirst.frc.team6026.robot.SimplePID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

import java.lang.Math;

public class CenterTossSwitch extends Command{

	private SimplePID m_RotatePID = new SimplePID(0.035, 0.001, 0, -1000, 1000);	// rotation PID
	private SimplePID m_MovePID = new SimplePID(0.003, 0.001, 0, -1000, 1000);	// move PID
	
	private int moveState = 0;
	private Timer stateTimer = new Timer();
	
	private char localSwitch = '?';
	
	public CenterTossSwitch() {
		requires(Robot.kGyroSubsystem);
		requires(Robot.kDriveTrainSubsystem);
		requires(Robot.kRangeFinderSubsystem);
	}
	
	protected void initialize() {		
		Robot.kDriveTrainSubsystem.zeroMotorPositions();
		Robot.kRangeFinderSubsystem.clearDashboard();
		Robot.kDriveTrainSubsystem.useBrakes(true);
		Robot.kDriveTrainSubsystem.setRamp(1.0);
		
		Robot.kRangeFinderSubsystem.getLeftRange();
		Robot.kRangeFinderSubsystem.getRightRange();
		Robot.kRangeFinderSubsystem.getFrontRange();

		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if( gameData.length() > 0 ) {
			localSwitch = gameData.charAt(0);
		}
		
		moveState = -3;
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
		return Math.abs(target-position);
	}
	
	private double rotate(double target) {
		double position = Robot.kGyroSubsystem.gyro.getAngle();
		//double target = degrees;
		double output = m_RotatePID.update(position, target);
		SmartDashboard.putNumber("target", target);
		SmartDashboard.putNumber("position", position);
		SmartDashboard.putNumber("output", output);
		Robot.kDriveTrainSubsystem.drive(0, output);
		return Math.abs(target-position);
	}
	
	private double approach(double target) {
		double position = Robot.kRangeFinderSubsystem.getFrontRange();
		double output = m_MovePID.update(position, target);
		SmartDashboard.putNumber("target", target);
		SmartDashboard.putNumber("position", position);
		SmartDashboard.putNumber("output", output);
		Robot.kDriveTrainSubsystem.drive(output/1.5,0);
		return Math.abs(target-position);	
	}
	
	protected void execute() {
		
		SmartDashboard.putNumber("AutoState", moveState);
		Robot.kDriveTrainSubsystem.updateDashboard();
		Robot.kGyroSubsystem.updateDashboard();
		SmartDashboard.putNumber("stateTimer", stateTimer.get());
		
		switch( moveState ) {
		case -3:
			// Start
			stateTimer.reset();
			stateTimer.start();
			Robot.kDriveTrainSubsystem.drive(0, 0);
			moveState++;
		case -2:
			// Lift
			if( stateTimer.get() > 1.0 ) {
				moveState++;
			}
			Robot.kDriveTrainSubsystem.drive(0, 0);
			Robot.kLiftSubsystem.driveLiftMotor(-0.35);
			break;
		case -1:
			// Release
			if( stateTimer.get() > 1.5 ) {
				moveState++;
				Robot.kDriveTrainSubsystem.drive(0, 0);
				Robot.kLiftSubsystem.driveLiftMotor(0);
			}
			Robot.kDriveTrainSubsystem.drive(0, 0);
			Robot.kLiftSubsystem.driveLiftMotor(0.35);
			break;
		case 0:
			// Move 1200mm
			if( (move(1200)) < 150 ) {
				moveState++;
				SmartDashboard.putNumber("AutoState", moveState);
				Robot.kDriveTrainSubsystem.zeroMotorPositions();
				Robot.kGripperSubsystem.driveGripperMotor(0.5);
				Timer.delay(0.25);
				stateTimer.reset();
			}
			break;
		case 1:
			// Square on Wall
			if( stateTimer.get() > 2.0 ) {
				moveState++;
			}
			Robot.kDriveTrainSubsystem.drive(0.25, 0);
			Robot.kLiftSubsystem.driveLiftMotor(-0.05);
			break;
		case 2:
			// Back up a bit
			if( stateTimer.get() > 2.0 ) {
				moveState++;
			}
			Robot.kDriveTrainSubsystem.drive(-0.25, 0);
			Robot.kLiftSubsystem.driveLiftMotor(-0.05);
			break;
		case 3:
			// this should be 45 for right hand switch, and -45 for left hand switch
			if( localSwitch == 'L' || localSwitch == 'l' ) {
				if( (rotate(-60)) < 2 ) {
					moveState++;
					SmartDashboard.putNumber("AutoState", moveState);
					Robot.kDriveTrainSubsystem.zeroMotorPositions();
					Robot.kGripperSubsystem.driveGripperMotor(0.0);
					Timer.delay(0.25);
					stateTimer.reset();
				}
			}else {
				if( (rotate(60)) < 2 ) {
					moveState++;
					SmartDashboard.putNumber("AutoState", moveState);
					Robot.kDriveTrainSubsystem.zeroMotorPositions();
					Robot.kGripperSubsystem.driveGripperMotor(0.0);
					Timer.delay(0.25);
					stateTimer.reset();
				}	
			}
			break;
		case 4:
			// Lift Package
			if( stateTimer.get() > 4.0 ) {
				moveState++;
			}
			Robot.kDriveTrainSubsystem.drive(0, 0);
			Robot.kLiftSubsystem.driveLiftMotor(-0.5);
			break;
		case 5:
			// Eject Package
			if( stateTimer.get() > 2.0 ) {
				moveState++;
			}
			Robot.kDriveTrainSubsystem.drive(0, 0);
			Robot.kLiftSubsystem.driveLiftMotor(-0.05);
			Robot.kGripperSubsystem.driveGripperMotor(-1.0);
			break;			
		default:
			Robot.kDriveTrainSubsystem.drive(0, 0);
			Robot.kLiftSubsystem.driveLiftMotor(-0.05);
			Robot.kGripperSubsystem.driveGripperMotor(0.0);
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
