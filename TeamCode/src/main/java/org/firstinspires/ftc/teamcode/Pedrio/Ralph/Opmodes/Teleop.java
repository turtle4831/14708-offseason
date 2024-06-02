package org.firstinspires.ftc.teamcode.Pedrio.Ralph.Opmodes;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Pedrio.Ralph.Commands.StartIntake;
import org.firstinspires.ftc.teamcode.Pedrio.Ralph.Commands.StopIntake;
import org.firstinspires.ftc.teamcode.Pedrio.Ralph.Hardware;
@TeleOp(name = "14708 is for cool kids only ")
public class Teleop extends OpMode {
    private Hardware robot = Hardware.getInstance();
    private StartIntake startIntake;
    private StopIntake stopIntake;
    GamepadEx driver;
    @Override
    public void init() {
        robot.Init(hardwareMap);
        gamepad1.setGamepadId(1);
        driver = new GamepadEx(gamepad1);
        robot.deposit.resetEncoder();
        robot.deposit.init();
        startIntake = new StartIntake(robot.intake);
        stopIntake = new StopIntake(robot.intake);
    }

    @Override
    public void loop() {
        this.robot = Hardware.getInstance();
        robot.DriveTrain.Drive(driver.getLeftX(), driver.getLeftY(), -driver.getRightX());

        robot.deposit.setSlidePose(driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER));

        Button score = (new GamepadButton(driver, GamepadKeys.Button.X)).whenHeld(
                new InstantCommand(robot.deposit::Drop)
        ).whenReleased(
                new InstantCommand(robot.deposit::Box)
        );

        Button intake = (new GamepadButton(driver, GamepadKeys.Button.A)).whenHeld(
                startIntake
        ).whenReleased(
                stopIntake
        );
        robot.deposit.periodic();
        CommandScheduler.getInstance().run();
        CommandScheduler.getInstance().registerSubsystem(robot.deposit);
    }
}
