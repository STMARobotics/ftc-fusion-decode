package org.firstinspires.ftc.teamcode.opMode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.command.AutoDriveCommand;
import org.firstinspires.ftc.teamcode.globals.Constants;
import org.firstinspires.ftc.teamcode.globals.Robot;
import org.firstinspires.ftc.teamcode.subsystem.Drive;

@Autonomous(name = "AutoForward", preselectTeleOp = "SampleOpMode")
public class Auto extends CommandOpMode {

    TelemetryData telemetryData = new TelemetryData(telemetry);
    private final Robot robot = Robot.getInstance();

    @Override
    public void initialize() {
        // Must have for all opModes
        Constants.OP_MODE_TYPE = Constants.OpModeType.AUTO;

        // Resets the command scheduler
        super.reset();

        // Initialize the robot (which also registers subsystems, configures CommandScheduler, etc.)
        robot.init(this);
    }

    @Override
    public void run() {
        // DO NOT REMOVE ANY LINES BELOW! Runs the command scheduler and updates telemetry
        schedule(new AutoDriveCommand(Robot.getInstance().drive, 0.5));
        super.run();
        telemetryData.update();
    }
}