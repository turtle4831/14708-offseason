package org.firstinspires.ftc.teamcode.Pedrio.Ralph.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Pedrio.Ralph.Subsystems.Intake;

public class StopIntake extends CommandBase {
    Intake intake;
    public StopIntake(Intake intake){
        this.intake = intake;
        addRequirements(intake);
    }
    @Override
    public void initialize() {
        intake.SetIntakePos(Intake.Position.RELEASE);
        intake.StopIntake();
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
