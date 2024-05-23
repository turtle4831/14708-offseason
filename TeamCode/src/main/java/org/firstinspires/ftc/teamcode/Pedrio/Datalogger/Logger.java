package org.firstinspires.ftc.teamcode.Pedrio.Datalogger;

import com.arcrobotics.ftclib.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Pedrio.Util.SubsystemType;

import java.util.ArrayList;

public class Logger {
    ArrayList<SubsystemType> subsystems =  new ArrayList<>();
    Pose2d pose;
    private Data data;
    public Logger(ArrayList<SubsystemType> subsystems, Pose2d pose){
        this.pose = pose;
        this.subsystems = subsystems;
        data = new Data(this.subsystems,this.pose);
    }
    public Logger(Pose2d pose){
        this.pose = new Pose2d(pose.getTranslation(),pose.getRotation());
    }

    public void update(){
        //updates the data by adding subsystem info to a list

    }


    public void write(){
        //used at the end of an opmode to write data to a sd card connected to a control hub
    }
}
