package org.usfirst.frc.team2785.robot.subsystems;

import org.usfirst.frc.team2785.robot.RobotMap;
import org.usfirst.frc.team2785.robot.commands.TeleopCamera;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 *
 */
public class CameraMount extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static Servo horizontalServo;
	private static Servo verticalServo;
	private static USBCamera camera;
	private static final double speed = 4.0d; // 1 deg / 20 milliseconds
	public CameraMount(Servo vertical, Servo horizontal, USBCamera cam) {
		horizontalServo = horizontal;
		verticalServo = vertical;
		camera = cam;
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TeleopCamera());
    }
    public void startServer() {
    	CameraServer server = CameraServer.getInstance();
    	server.setQuality(RobotMap.cameraQuality);
    	server.startAutomaticCapture(camera);
    }
    public void stopServer() {
    	CameraServer server = CameraServer.getInstance();
    	server.startAutomaticCapture(camera);
    }
    public void pushData() {
    	SmartDashboard.putNumber("cvert", verticalServo.getAngle());
    	SmartDashboard.putNumber("choriz", horizontalServo.getAngle());
    }
    public void resetPosition() {
    	verticalServo.setAngle(100);
    	horizontalServo.setAngle(32);
    }
    public void moveCamera(double vertical, double horizontal) {
    	//TODO: fix, duh
    	if (Math.abs(vertical) > 0.1) {
    		verticalServo.setAngle(verticalServo.getAngle() + vertical * speed);
    	}
    	if (Math.abs(horizontal) > 0.1) {
    		horizontalServo.setAngle(horizontalServo.getAngle() + horizontal * speed);
    	}
    }
}

