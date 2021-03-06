package RobotCode2014.commands.drivetrain;

import RobotCode2014.OI;
import RobotCode2014.subsystems.Drivetrain;
import RobotCode2014.Constants;
import RobotCode2014.commands.CommandBase;

/**
 *
 * @author Brian Chan
 */
public class RestoreManualDriveCommand extends CommandBase {
    
    private boolean hasReset;
    
    public RestoreManualDriveCommand() {
    }
    
    public void start() {
        super.start();
        hasReset = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	if(checkAxesMinLessThan(Constants.DrivetrainConst.manualOverrideResetThreshold))
            hasReset = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return !drivetrain.isBusy() || (hasReset && checkAxesMaxGreaterThan(Constants.DrivetrainConst.manualOverrideThreshold));
    }

    // Called once after isFinished returns true
    protected void end() {
	drivetrain.setBusy(false);
	OI.manualDrive.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private boolean checkAxesMaxGreaterThan(double limit) {
        return (Math.abs(RobotCode2014.OI.gamepad1.getLeftX()) > limit || Math.abs(RobotCode2014.OI.gamepad1.getLeftY()) > limit || 
		Math.abs(RobotCode2014.OI.gamepad1.getRightX()) > limit || Math.abs(RobotCode2014.OI.gamepad1.getRightY()) > limit);
    }
    
    private boolean checkAxesMinLessThan(double limit) {
        return (Math.abs(RobotCode2014.OI.gamepad1.getLeftX()) < limit || Math.abs(RobotCode2014.OI.gamepad1.getLeftY()) < limit || 
		Math.abs(RobotCode2014.OI.gamepad1.getRightX()) < limit || Math.abs(RobotCode2014.OI.gamepad1.getRightY()) < limit);
    }
}
