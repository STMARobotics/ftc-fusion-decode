package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.Intake;

public class IntakeSpinCommand extends CommandBase {
    private final Intake intake;

    public IntakeSpinCommand(Intake intake) {
        this.intake = intake;
        addRequirements(this.intake);
    }


    @Override
    public void execute() {
        intake.spin();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
