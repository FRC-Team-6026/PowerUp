/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6026.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6026.robot.commands.TestMoveCommand;
import org.usfirst.frc.team6026.robot.commands.Test360Command;
import org.usfirst.frc.team6026.robot.commands.CenterSwitch;
import org.usfirst.frc.team6026.robot.commands.CenterTossSwitch;
import org.usfirst.frc.team6026.robot.commands.LeftSideSwitch;
import org.usfirst.frc.team6026.robot.commands.RightSideSwitch;
import org.usfirst.frc.team6026.robot.commands.TeleOpDrive;

import org.usfirst.frc.team6026.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6026.robot.subsystems.Gripper;
import org.usfirst.frc.team6026.robot.subsystems.Gyro;
import org.usfirst.frc.team6026.robot.subsystems.Lift;
import org.usfirst.frc.team6026.robot.subsystems.RangeFinder;

import edu.wpi.first.wpilibj.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final DriveTrain kDriveTrainSubsystem = new DriveTrain();
	public static final Gyro kGyroSubsystem = new Gyro();
	public static final RangeFinder kRangeFinderSubsystem = new RangeFinder();
	public static final Lift kLiftSubsystem = new Lift();
	public static final Gripper kGripperSubsystem = new Gripper();
	
	public static final Command teleopDrive = new TeleOpDrive();
	
	public static OI m_oi;
	public static String gameData;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		// Get Game Data
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if( gameData.length() > 0 ) {
			if( gameData.charAt(0) == 'L') {
			
			}
		}
		
		m_oi = new OI();
		//m_chooser.addDefault("Auto Rotate", new Test360Command());
		//m_chooser.addObject("Auto Move", new TestMoveCommand());
		m_chooser.addObject("Center Switch", new CenterSwitch());
		m_chooser.addObject("Center Switch Toss", new CenterTossSwitch());
		m_chooser.addObject("Left Side Switch", new LeftSideSwitch());
		m_chooser.addObject("Right Side Switch", new RightSideSwitch());
		
		SmartDashboard.putData("Auto Mode", m_chooser);
		
		CameraServer.getInstance().startAutomaticCapture();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		/*
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        if (gameData==null) { gameData = ""; }
        int retries = 100;
        while (gameData.length() < 2 && retries > 0) {
            DriverStation.reportError("Gamedata is " + gameData + " retrying " + retries, false);
            try {
                Thread.sleep(5);
                gameData = DriverStation.getInstance().getGameSpecificMessage();
                if (gameData==null) { gameData = ""; }
            } catch (Exception e) {
            }
            retries--;
        }
        SmartDashboard.putString("Auto/gameData", gameData);
        DriverStation.reportError("gameData before parse: " + gameData, false);
		*/
		m_autonomousCommand = m_chooser.getSelected();

		if( teleopDrive != null ) {
			teleopDrive.cancel();
		}
		
		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		
		teleopDrive.start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
