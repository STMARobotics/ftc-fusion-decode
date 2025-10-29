package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.Intake;

public class IntakeSpinOutCommand extends CommandBase {
    private final Intake intake;

    public IntakeSpinOutCommand(Intake intake) {
        this.intake = intake;
        addRequirements(this.intake);
    }


    @Override
    public void execute() {
        intake.spinEject();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
