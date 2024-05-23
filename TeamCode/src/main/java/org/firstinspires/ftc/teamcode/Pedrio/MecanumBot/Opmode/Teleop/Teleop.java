package org.firstinspires.ftc.teamcode.Pedrio.MecanumBot.Opmode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Pedrio.MecanumBot.Comms.Hardware;

@TeleOp(name = "pedrio tele-op test || robot: num-num")
public class Teleop extends OpMode {
    private Hardware robot = new Hardware().getInstance();

    @Override
    public void init() {

        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot = robot.getInstance();
        robot.drivetrain.driveFieldCentric(
            gamepad1.left_stick_x,
            gamepad1.left_stick_y,
            gamepad1.right_stick_x,
            robot.drivetrain.getOdoHeadingDegrees()
    );

    }
}
