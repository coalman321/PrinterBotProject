
package org.usfirst.frc.team7303.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    public RobotDrive myBot;
    public Joystick stick1;
    public Spark rightDrive,leftDrive,Shoulder,Elbow,Wrist;
    public Gyro gyrox; public BNO055 sensor;
    public ServoLogic Sh, El, Wr;
    public Encoder Sho , Elb;
    private int shTarg, elTarg;
    public DigitalInput shSw,elSw;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	sensor = BNO055.getInstance(I2C.Port.kMXP);
    	leftDrive = new Spark(0);
    	rightDrive = new Spark(1);
    	Shoulder = new Spark(2);
    	Elbow = new Spark(3);
    	Wrist = new Spark(4);
    	myBot = new RobotDrive(0, 1);
    	stick1 = new Joystick(1);
    	Sh = new ServoLogic(10,5);
    	El = new ServoLogic(1,1);
    	Wr = new ServoLogic(1,1);
    	Sho = new Encoder(0,1);
    	Elb = new Encoder(2,3);
    	shSw = new DigitalInput(9);
    	elSw = new DigitalInput(8);
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopInit(){
    	int Elmax = 120;
		elTarg = 0;
    	shTarg = 132600;
    	while(!elSw.get()){
    		Elbow.set(1.0);
    	}
		Elbow.set(0.0);
		Elbow.set(El.toTarget(Elmax,Elb.get()));
		while(Elb.get() != Elmax){
	    	Timer.delay(5);
		}
		Elbow.set(0.0);
		while(!shSw.get()){
			Shoulder.set(0.5);
		}
		Shoulder.set(0.0);
		Shoulder.set(Sh.toTarget(shTarg,Sho.get()));
		while(Sho.get() != shTarg){
	    	Timer.delay(5);
		}
		Shoulder.set(0.0);
    }
	
    public void teleopPeriodic() {
    	Shoulder.set(Sh.toTarget(shTarg, Sho.get()));
    	Elbow.set(El.toTarget(elTarg, Elb.get()));
    	Wrist.set(Wr.toTarget(0, (int)this.angle()));
    	while(elTarg != Elb.get()||shTarg != Sho.get()||0 != this.angle()){
	    Timer.delay(5);
	}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    public Gyro getGyroX() {
       return sensor.createGyroX();
    }
    public double angle(){
    	return sensor.getRoll();
    }
    
}
