package org.firstinspires.ftc.teamcode.Pedrio.Ralph.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;

import org.firstinspires.ftc.teamcode.Pedrio.Ralph.Hardware;

public class Deposit extends SubsystemBase {
    public enum SlideState{
        PowerControlled,
        PIDControlled
    }
    private final Hardware robot = Hardware.getInstance();
    private PIDController pid = new PIDController(0.005,0,0);
    private double SlidePose = 0;
    private double power = 0;
    private SlideState slideState;

    public void setPower(double power) {
        this.power = power;
    }


    public SlideState getSlideState() {
        return slideState;
    }

    public void setSlideStatePID(){
        slideState = SlideState.PIDControlled;
    }
    public void setSlideStatePower(){
        slideState = SlideState.PowerControlled;
    }


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
    public void setSlidePower(double power){
        robot.leftSlide.set(power);
        robot.rightSlide.set(power);
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
        slideState = SlideState.PIDControlled;
        pid.setTolerance(5);

    }


    @Override
    public void periodic() {
        switch (slideState){
            case PIDControlled:
                ArmToPose(this.SlidePose * 2000);
                break;
            case PowerControlled:
                setSlidePower(power);
                break;

        }

    }
    public void resetEncoder(){
        robot.leftSlide.resetEncoder();
    }
}
