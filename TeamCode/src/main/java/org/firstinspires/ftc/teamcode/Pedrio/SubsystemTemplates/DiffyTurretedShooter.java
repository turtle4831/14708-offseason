package org.firstinspires.ftc.teamcode.Pedrio.SubsystemTemplates;

import static com.arcrobotics.ftclib.purepursuit.PurePursuitUtil.angleWrap;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

public class DiffyTurretedShooter {

    //take these motors from a robot hardware class not from the constructor its only there to avoid bugs
    private MotorEx motorA;
    private MotorEx motorB;

    //keep all PIDF related things in the constructor
    private PIDFController turnPIDF;
    private PIDFController flywheelPIDF;
    private double currentAngleTicks;
    private double currentAngle;

    private static final double TICKS_PER_INCH = 1221;//fix all constants
    private static final double MAX_RPS_TICKS = 0;
    final private double ENCODER_TICKS_PER_REV = 537.7;
    final double MAX_RPM = 1150;
    double degrees = ENCODER_TICKS_PER_REV / 360;
    final private  double WHEEL_RADIUS = 3;
    final private double MIN_VELOCITY = 0;

    final private double MAX_VELOCITY = (MAX_RPM / 60) * 1 * (2 * Math.PI);
    private double rPower;


    public DiffyTurretedShooter(MotorEx motorA, MotorEx motorB, PIDFController turnPIDF, PIDFController flywheelPIDF) {
        //remember to take out motors
        this.motorA = motorA;
        this.motorB = motorB;
        this.turnPIDF = turnPIDF;
        this.flywheelPIDF = flywheelPIDF;

        this.turnPIDF.setTolerance(0.3);
        this.flywheelPIDF.setTolerance(0.01);

    }
    private double scale_number(double unscaled, double toMin,double toMax, double fromMin, double fromMax){
        return (toMax - toMin) * (unscaled - fromMin)/ (fromMax - fromMin) + toMin;
    }
    private double ticksToDegrees(double ticks){
        return ticks * degrees;
    }

    private double getMotor1Location(){
        return motorA.getCurrentPosition();

    }
    private double getMotor2Location(){
        return motorA.getCurrentPosition();
    }

    public double getTurretHeading(){
        currentAngleTicks = (getMotor1Location() + getMotor2Location()) / 2;
        currentAngle = ticksToDegrees(currentAngleTicks);
        currentAngle = angleWrap(currentAngle);

        return currentAngle;
    }
    public double getFlyWheelVelocity(double currentPosA, double currentPosB){
        double velocity =  ((currentPosA - getMotor1Location()) - (currentPosB - getMotor2Location())) / 2;
        return scale_number(velocity, 0.0,1.0,0,motorA.getMaxRPM());
    }
    public double getFlyWheelVelocityMotor(){
        return motorA.getVelocity() - motorB.getVelocity() / 2 ;
    }

    public boolean AimToPose(@NonNull Pose2d target, Pose2d turretCurrentPose, boolean fire) {
        //returns true if the turret is aimed correctly and is spinning at the correct velocity


        double x1 = target.getX();
        double y1 = target.getY();

        double x2 = target.getX();
        double y2 = target.getY();

        // double distance = Math.sqrt( //distance will be used along with a power look up table or kinematics model which ever one you need
        //   Math.pow((x2 - x1), 2) + Math.pow((x2 - x1), 2)
        //);

        Vector2d vector = new Vector2d((x2 - y2), (x1 - y2));

        double angle = vector.angle();
        double distance = vector.magnitude();//you can use this or distance this one is prob better if you use a scaling method

        double velocity = scale_number(vector.magnitude(),0.1,0.95,0,1.0);//tune these

        rPower = turnPIDF.calculate(getTurretHeading(), angle);
        velocity = flywheelPIDF.calculate(getFlyWheelVelocityMotor(),velocity);
        if (fire) {

            //the one is a scale factor
            motorA.setVelocity(((-velocity * 1) + rPower) * MAX_RPS_TICKS);
            motorB.setVelocity(((velocity * 1) + rPower) * MAX_RPS_TICKS);
        }else{
            motorA.setVelocity(((0) + rPower) * MAX_RPS_TICKS);
            motorB.setVelocity(((0) + rPower) * MAX_RPS_TICKS);
        }

        return turnPIDF.atSetPoint();
    }


}