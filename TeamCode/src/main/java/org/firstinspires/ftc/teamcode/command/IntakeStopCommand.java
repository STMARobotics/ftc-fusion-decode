package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.Intake;

public class IntakeStopCommand extends CommandBase {
    private final Intake intake;

    public IntakeStopCommand(Intake intake) {
        this.intake = intake;
        addRequirements(this.intake);
    }


    @Override
    public void execute() {
        intake.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
