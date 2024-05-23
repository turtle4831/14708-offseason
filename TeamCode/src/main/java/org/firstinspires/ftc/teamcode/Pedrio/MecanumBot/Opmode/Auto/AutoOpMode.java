package org.firstinspires.ftc.teamcode.Pedrio.MecanumBot.Opmode.Auto;

import android.hardware.HardwareBuffer;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Pedrio.MecanumBot.Comms.Hardware;

@Autonomous(name = "PurePursuit test")
public class AutoOpMode extends OpMode {
    private Hardware robot = new Hardware().getInstance();



    @Override
    public void init() {

        robot.init(hardwareMap);

    }

    @Override
    public void loop() {
       robot = robot.getInstance();
    }
}
