/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6026.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static int rangefinderLeftPort = 1;
	public static int rangefinderRightPort = 2;
	public static int rangefinderFrontPort = 3;
	
	public static int masterLeftMotor = 1;
	public static int masterRightMotor = 2;
	public static int slaveLeftMotor = 3;
	public static int slaveRightMotor = 4;
	public static int gripperLeftMotor = 5;
	public static int gripperRightMotor = 6;
	public static int liftMotor = 7;
	public static int gripperTiltMotor = 8;
}
