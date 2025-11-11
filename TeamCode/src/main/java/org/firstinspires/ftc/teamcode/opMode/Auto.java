package org.firstinspires.ftc.teamcode.opMode;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.command.AutoDriveCommand;
import org.firstinspires.ftc.teamcode.command.FollowPathCommand;
import org.firstinspires.ftc.teamcode.globals.Constants;
import org.firstinspires.ftc.teamcode.globals.Robot;
import org.firstinspires.ftc.teamcode.subsystem.Drive;

@Autonomous(name = "AutoForward", preselectTeleOp = "SampleOpMode")
public class Auto extends CommandOpMode {

    TelemetryData telemetryData = new TelemetryData(telemetry);
    private final Robot robot = Robot.getInstance();
    private final Pose startPose = new Pose(12, 0, Math.toRadians(0));
    private final Pose endPose = new Pose(0, 0, Math.toRadians(0));

    private PathChain myPath;
    @Override
    public void initialize() {
        // Must have for all opModes
        Constants.OP_MODE_TYPE = Constants.OpModeType.AUTO;

        // Resets the command scheduler
        super.reset();

        // Initialize the robot (which also registers subsystems, configures CommandScheduler, etc.)
        robot.init(this);

        myPath = robot.drive.pathBuilder()
                .addPath(new BezierLine(startPose, endPose))
                .build();
    }

    @Override
    public void run() {
        // DO NOT REMOVE ANY LINES BELOW! Runs the command scheduler and updates telemetry
        schedule(new FollowPathCommand(startPose, myPath, robot.drive));
        super.run();
        telemetryData.update();
    }
}