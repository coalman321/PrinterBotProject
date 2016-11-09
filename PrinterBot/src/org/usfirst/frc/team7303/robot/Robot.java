
package org.usfirst.frc.team7303.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
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
    public Spark rightDrive;
    public Spark leftDrive;
    public Spark Shoulder;
    public Spark Elbow;
    public Spark Wrist;
    public Gyro gyrox;
    public BNO055 sensor;
    public ServoLogic Sh;
    public ServoLogic El;
    public ServoLogic Wr;
    public Encoder Sho;
    public Encoder Elb;
    private int shTarg;
    private int elTarg;
	
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
    	Sh = new ServoLogic(5);
    	El = new ServoLogic(1);
    	Wr = new ServoLogic(1);
	sho = new Encoder(0,1);
	Elb = new Encoder(2,3);
	    
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
	elTarg = 0;
        shTarg = 0;
    	
    }
	
    public void teleopPeriodic() {
	Sh.toTarget(shTarg, Sho.get());
	El.toTarget(elTarg, Elb.get());
        Wr.toTarget(0, (int)this.angle());
	
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
