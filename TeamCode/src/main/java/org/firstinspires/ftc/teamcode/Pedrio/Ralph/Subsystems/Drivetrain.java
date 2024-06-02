package org.firstinspires.ftc.teamcode.Pedrio.Ralph.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.Pedrio.Ralph.Hardware;

public class Drivetrain extends SubsystemBase {
    private Hardware hardware = Hardware.getInstance();
    private MecanumDrive drive = new MecanumDrive(
            hardware.FlMotor, hardware.FrMotor, hardware.BlMotor, hardware.BrMotor
    );
    public void Drive(double x, double y, double z){
        drive.driveRobotCentric(x,y,z);
    }

}
