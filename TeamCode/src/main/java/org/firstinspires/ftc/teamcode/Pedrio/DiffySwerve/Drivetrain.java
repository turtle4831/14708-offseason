package org.firstinspires.ftc.teamcode.Pedrio.DiffySwerve;

import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.ChassisSpeeds;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.SwerveDriveKinematics;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.SwerveModuleState;

public class Drivetrain {
    private Hardware robot = new Hardware().getInstance();

    public Module leftModule = new Module(robot.leftModuleMotorA, robot.leftModuleMotorB);
    public Module rightModule = new Module(robot.rightModuleMotorA, robot.rightModuleMotorB);

    private SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
            new Translation2d(-0.33,0), new Translation2d(0.33, 0)
    );

    private ChassisSpeeds speeds;
    private double newAngle;
    private double leftModuleAngle;
    private double rightModuleAngle;
    private SwerveModuleState leftModuleState,rightModuleState;

    public void drive(double x,double y, double z){
        //calculate the vector for modules to point at
        Vector2d vector = new Vector2d(x,y);
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(ChassisSpeeds.fromFieldRelativeSpeeds(x,y,z, Rotation2d.fromDegrees(robot.getHeading())));

        leftModuleState = states[0];
        rightModuleState = states[1];
        //calculate the needed wheel angle and vector stuff

        //newAngle = vector.angle() - robot.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);//for field centric
        leftModuleAngle  = leftModuleState.angle.getDegrees();
        rightModuleAngle = rightModuleState.angle.getDegrees();

        leftModule.moveTo(leftModuleState.speedMetersPerSecond, leftModuleAngle);
        rightModule.moveTo(leftModuleState.speedMetersPerSecond, rightModuleAngle);


    }
    //auto stuff








}
