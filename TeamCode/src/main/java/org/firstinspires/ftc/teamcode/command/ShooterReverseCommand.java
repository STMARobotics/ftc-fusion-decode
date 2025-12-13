package org.firstinspires.ftc.teamcode.command;

import static org.firstinspires.ftc.teamcode.globals.Constants.SHOOTER_SPEED_POWER;
import static org.firstinspires.ftc.teamcode.globals.Constants.SHOOTER_TIME;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.globals.Robot;
import org.firstinspires.ftc.teamcode.subsystem.Shooter;

public class ShooterReverseCommand extends CommandBase {
    private final Shooter shooter;
    private boolean shouldFinish;
    private long startTimeMillis;

    public ShooterReverseCommand(Shooter shooter) {
        System.out.println("MOLLIE: Creating Shooter Command");
        this.shooter = shooter;
        addRequirements(this.shooter);
    }

    @Override
    public void initialize() {
        startTimeMillis = System.currentTimeMillis() + SHOOTER_TIME;
        System.out.println("MOLLIE: Initializing setting start time too " + startTimeMillis + " Current Time: " + System.currentTimeMillis());
        shouldFinish = false;
    }

    @Override
    public void execute(){
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("MOLLIE: Current Time: " + currentTimeMillis + " Time To Start Servo: " + startTimeMillis);

        shooter.setSpeed(-SHOOTER_SPEED_POWER);
        shooter.shoot();
        Robot.getInstance().telemetryData.addData("Shooter Speed " +
                "Velocity", shooter.getShooterMotor().getVelocity());
    }


    @Override
    public boolean isFinished(){
        return shouldFinish;
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stop();
    }

    public void stop() {
        System.out.println("MOLLIE: STOPPING");
        this.shouldFinish = true;
    }
}
