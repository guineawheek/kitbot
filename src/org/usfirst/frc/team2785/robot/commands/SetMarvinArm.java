package org.usfirst.frc.team2785.robot.commands;

import org.usfirst.frc.team2785.robot.Robot;
import org.usfirst.frc.team2785.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls the robot's famous arm.
 * The angle is relative to the robot if savePosition is not used,
 * otherwise, it is relative to the floor.
 */
public class SetMarvinArm extends Command {
    private double target;
    private boolean _debug = false;

    public SetMarvinArm(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.marvinArm);
        target = angle;
    }

    public SetMarvinArm() {
        this(0);
        _debug = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (_debug) {
            target = SmartDashboard.getNumber("armTarget");
        }
        Robot.marvinArm.setAngle(-target);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // plus because gyro output is negative
        return Math.abs(Robot.marvinArm.returnPIDInput() + target) < RobotMap.ARM_TOLERANCE;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.marvinArm.stopPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
