package org.firstinspires.ftc.teamcode.Pedrio.Ralph.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Pedrio.Ralph.Hardware;

public class Intake extends SubsystemBase {
    private final Hardware robot = Hardware.getInstance();
    public enum Position{
        LOWER,
        RELEASE
    }

    double LowerLeftSide = 0.60;
    double LowerRightSide = 0.35;

    double LeftRelease = 0.93;
    double RightRelease = 0;

    public void SetIntakePos(Position position){
        switch (position){
            case LOWER:
                robot.leftIntakeServo.setPosition(LowerLeftSide);
                robot.rightIntakeServo.setPosition(LowerRightSide);
                break;

            case RELEASE:
                robot.leftIntakeServo.setPosition(LeftRelease);
                robot.rightIntakeServo.setPosition(RightRelease);
        }

    }

    public void StartIntake(){
        robot.Intake.setVelocity(2000);
        robot.Transfer.set(1.0);
    }
    public void StopIntake(){
        robot.Intake.set(0);
        robot.Transfer.set(0);
    }



    @Override
    public void periodic() {
        super.periodic();
    }
}
