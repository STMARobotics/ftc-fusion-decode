package org.firstinspires.ftc.teamcode.command;

import static org.firstinspires.ftc.teamcode.globals.Constants.SHOOTER_TIME;

import com.seattlesolvers.solverslib.command.CommandBase;


import org.firstinspires.ftc.teamcode.subsystem.*;

public class ShooterFireCommand extends CommandBase {
    private final Shooter shooter;
    private boolean shouldRun;
    private long startTimeMillis;

    public ShooterFireCommand(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(this.shooter);
    }

    @Override
    public void initialize() {
        startTimeMillis = System.currentTimeMillis() + SHOOTER_TIME;
        shouldRun = true;
    }

    @Override
    public void execute(){
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis >= startTimeMillis) {
            shooter.spinServo();
        }
        shooter.shoot();
    }


    @Override
    public boolean isFinished(){
        return shouldRun;
    }

    public void stop() {
        this.shouldRun = false;
    }
}
