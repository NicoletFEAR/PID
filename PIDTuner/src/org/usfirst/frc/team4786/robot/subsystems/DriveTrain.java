package org.usfirst.frc.team4786.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class DriveTrain extends Subsystem 
{	
	
	
	
	public CANTalon backright = new CANTalon(0); //backright
	public CANTalon frontright = new CANTalon(1); //frontright
	public CANTalon frontleft = new CANTalon(14); //frontleft
	public CANTalon backleft = new CANTalon(15); //backleft
	
	public RobotDrive drive;
	
	public static final double WHEEL_RADIUS = 3;
	public static final int TESTBENCH_CODES_PER_REV = 7;
	public static final int TESTBENCH_GEARBOX_RATIO = 71;
	public static final int OPTION8_CODES_PER_REV = 7;
	public static final double OPTION8_GEARBOX_RATIO = 8.45;
	public DriveTrain() 
	{
	    backright.changeControlMode(CANTalon.TalonControlMode.Position);
	    backleft.changeControlMode(CANTalon.TalonControlMode.Position);
	    
	    frontright.changeControlMode(CANTalon.TalonControlMode.Follower);
	    frontright.set(backright.getDeviceID());
	    frontleft.changeControlMode(CANTalon.TalonControlMode.Follower);
	    frontleft.set(backleft.getDeviceID());
	    
	    frontright.enable();
	    frontleft.enable();
	    backright.enable();
	    backleft.enable();
	
		final int rawCodesPerRev = OPTION8_CODES_PER_REV;
		final double gearboxRatio = OPTION8_GEARBOX_RATIO;
    	frontright.configEncoderCodesPerRev((int) (rawCodesPerRev * gearboxRatio)); //Sets encoder revolutions
    	frontleft.configEncoderCodesPerRev((int) (rawCodesPerRev * gearboxRatio));
    	backleft.configEncoderCodesPerRev((int) (rawCodesPerRev * gearboxRatio));
    	backright.configEncoderCodesPerRev((int) (rawCodesPerRev * gearboxRatio));
    	
    	backright.setEncPosition(0);
    	backleft.setEncPosition(0);
    	
    	//int Error = (int) (rawCodesPerRev * gearboxRatio / 5); //Sets the tolerance of the talons
    	int Error = 0;
    	backright.setAllowableClosedLoopErr(Error);
    	backleft.setAllowableClosedLoopErr(Error);	

    	//backleft.setInverted(true);
    	//backright.setInverted(false);
    	drive = new RobotDrive(backleft, backright);
	}

	@Override
	protected void initDefaultCommand() {
		
	}	
	
	public void driveWheelsTank(double leftValue, double rightValue)
	{
		drive.tankDrive(leftValue, rightValue, true);
	}
	
	public void autonomousDrive(double x)
	{
		
		backright.set(-x);
		backleft.set(x);
	}
	
	public void driveAutonomousDistance(double feet)
	{
		//set CANTalon to PID position mode
		//Parameter for position in feet
		//Convert revolutions to feet
		//reset encoder position to 0 each time used, then set to position
		backright.changeControlMode(TalonControlMode.Position);
		backleft.changeControlMode(TalonControlMode.Position);
		backright.setEncPosition(0);
		backleft.setEncPosition(0);
		double revs = feetToRevs(feet);
		backright.set(-revs);
		backleft.set(revs);
	}
	
	public double feetToRevs(double feet)
	{
		double circumference = 2 * Math.PI * WHEEL_RADIUS;
		return 12 * feet / circumference;
	}
}