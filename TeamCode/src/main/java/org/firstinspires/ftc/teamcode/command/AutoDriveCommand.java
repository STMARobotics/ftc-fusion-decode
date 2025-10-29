package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.Drive;

public class AutoDriveCommand extends CommandBase {
    private final Double driveTime;
    private final Drive drive;
    private long startTimeMillis;

    public AutoDriveCommand(Drive drive, Double driveTime) {
        this.drive = drive;
        this.driveTime = driveTime;

        addRequirements(this.drive);
    }

    @Override
    public void initialize() {
        startTimeMillis = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        this.drive.driveRobotCentric(0.25,0,0);

    }

    @Override
    public void end(boolean interrupted) {
        this.drive.stop();
    }

    @Override
    public boolean isFinished() {
        long currentTimeMillis = System.currentTimeMillis();
        double endTime = startTimeMillis + (driveTime *1000);
        return currentTimeMillis >= endTime;
    }
}
