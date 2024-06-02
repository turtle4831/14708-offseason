package org.firstinspires.ftc.teamcode.Pedrio.Ralph.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Pedrio.Ralph.Subsystems.Intake;

public class StartIntake extends CommandBase {
    Intake intake;
    public StartIntake(Intake intake){
        this.intake = intake;
        addRequirements(intake);
    }
    @Override
    public void initialize() {
        intake.SetIntakePos(Intake.Position.LOWER);
        intake.StartIntake();
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
