package org.firstinspires.ftc.teamcode.Pedrio.Ralph.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;

import org.firstinspires.ftc.teamcode.Pedrio.Ralph.Hardware;

public class Deposit extends SubsystemBase {
    private final Hardware robot = Hardware.getInstance();
    private PIDController pid = new PIDController(0.005,0,0);

    private double SlidePose;

    public void setSlidePose(double SlidePos){
        this.SlidePose = SlidePos;
    }
    public double getSlidePose(){
        return robot.leftSlide.getCurrentPosition() ;
    }
    public void ArmToPose(double pose){
        pid.setSetPoint(pose);
        pose = pid.calculate(getSlidePose(),pose);
        robot.leftSlide.set(pose);
        robot.rightSlide.set(pose);
    }

    public boolean SlidesAtSetpoint(){
        return pid.atSetPoint();
    }
    public void Drop(){
        robot.depositServo.setPosition(0.75);
    }
    public void Box(){
        robot.depositServo.setPosition(0.55);
    }
    public double getServoPos(){
        return robot.depositServo.getPosition();
    }

    public void init(){
        pid.setTolerance(5);
    }


    @Override
    public void periodic() {
        ArmToPose(this.SlidePose * 2000);
    }
    public void resetEncoder(){
        robot.leftSlide.resetEncoder();
    }
}
