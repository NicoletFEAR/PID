
package org.usfirst.frc.team4786.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    private CANTalon talon; 
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	talon = new CANTalon(1);
    	talon.changeControlMode(TalonControlMode.Position);
    	final int rawCodesPerRev = 7;
    	/** Gearbox Ratios makes every 71 turns one turn for the input.
    	 * It also makes the torque 71 times greater.
    	 * */
    	final int gearboxRatio = 71;
    	talon.configEncoderCodesPerRev(rawCodesPerRev * gearboxRatio);
    	talon.setPID(10, 0, 0);
    }
	
	public void disabledPeriodic() {
	}

    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    }

    public void teleopInit() {

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
