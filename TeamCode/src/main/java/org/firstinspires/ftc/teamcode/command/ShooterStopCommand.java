package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.Shooter;

public class ShooterStopCommand extends CommandBase {
    private final Shooter shooter;

    public ShooterStopCommand(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(this.shooter);
    }
    @Override
    public void execute(){
        shooter.stop();
    }
    @Override
    public boolean isFinished(){
        return true;
    }
}
