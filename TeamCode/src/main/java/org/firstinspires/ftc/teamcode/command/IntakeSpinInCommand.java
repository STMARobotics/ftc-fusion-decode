package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.Intake;

public class IntakeSpinInCommand extends CommandBase {
    private final Intake intake;

    public IntakeSpinInCommand(Intake intake) {
        this.intake = intake;
        addRequirements(this.intake);
    }


    @Override
    public void execute() {
        intake.spinIntake();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
