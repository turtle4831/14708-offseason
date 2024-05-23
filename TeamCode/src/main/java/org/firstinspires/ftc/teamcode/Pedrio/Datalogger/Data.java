package org.firstinspires.ftc.teamcode.Pedrio.Datalogger;

import com.arcrobotics.ftclib.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Pedrio.Util.SubsystemType;
import org.slf4j.spi.MDCAdapter;

import java.util.ArrayList;

public class Data {
    private ArrayList<SubsystemType> subsystemTypes;
    private Pose2d DriveTrainPose;
    public Data(ArrayList<SubsystemType> subsystems, Pose2d StartingRobotPose){//constructor for init
        this.DriveTrainPose = StartingRobotPose;
        this.subsystemTypes = subsystems;
    }

    public Data(){

    }

    public ArrayList<Double> record(Pose2d pose,double RobotDetected,
                                    double GamePieceDetetected,double TeamObjectDetected,double HorizontalLinearSlideExtension,
                                    double VerticalLinearSlideExtension, double IntakeSpeed){
       ArrayList<Double> data = new ArrayList<>();
       data.add(pose.getX());
       data.add(pose.getY());
       data.add(pose.getHeading());
       data.add(RobotDetected);
       data.add(GamePieceDetetected);
       data.add(TeamObjectDetected);
       data.add(HorizontalLinearSlideExtension);
       data.add(VerticalLinearSlideExtension);
       data.add(IntakeSpeed);


       return data;

    }
}
