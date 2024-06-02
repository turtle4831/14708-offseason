package org.firstinspires.ftc.teamcode.Pedrio.Ralph.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Pedrio.Ralph.Hardware;
import org.firstinspires.ftc.teamcode.Pedrio.Ralph.Subsystems.Intake;

@TeleOp(name = "TestSlideEncoders")
public class Test extends OpMode {

    private Hardware robot = Hardware.getInstance();

    @Override
    public void init() {
        robot.Init(hardwareMap);
    }

    @Override
    public void loop() {
    robot = Hardware.getInstance();
    telemetry.addData("LeftSlide Encoder", robot.leftSlide.getCurrentPosition());
    if(gamepad1.a){
        robot.intake.SetIntakePos(Intake.Position.LOWER);
        robot.intake.StartIntake();
    }else{
        robot.intake.SetIntakePos(Intake.Position.RELEASE);
        robot.intake.StopIntake();
    }
    }
}
