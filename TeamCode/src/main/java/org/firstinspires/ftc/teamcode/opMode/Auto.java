package org.firstinspires.ftc.teamcode.opMode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.pedroCommand.HoldPointCommand;
import com.seattlesolvers.solverslib.pedroCommand.TurnCommand;
import com.seattlesolvers.solverslib.pedroCommand.TurnToCommand;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.command.AutoDriveCommand;
//import org.firstinspires.ftc.teamcode.command.FollowPathCommand;
import org.firstinspires.ftc.teamcode.command.FollowPathCommand;
import org.firstinspires.ftc.teamcode.globals.Constants;
import org.firstinspires.ftc.teamcode.globals.Robot;
import org.firstinspires.ftc.teamcode.subsystem.Drive;

@Autonomous(name = "AutoForward", preselectTeleOp = "SampleOpMode")
public class Auto extends CommandOpMode {

    TelemetryData telemetryData = new TelemetryData(telemetry);
    private final Robot robot = Robot.getInstance();
    private final Pose startPose = new Pose(0, 0, Math.toRadians(0));
    private final Pose endPose = new Pose(12, 0, Math.toRadians(90));

    private PathChain myPath;
    @Override
    public void initialize() {
        // Must have for all opModes\
        Constants.OP_MODE_TYPE = Constants.OpModeType.AUTO;

        // Resets the command scheduler
        super.reset();

        // Initialize the robot (which also registers subsystems, configures CommandScheduler, etc.)
        robot.init(this);

        myPath = robot.drive.pathBuilder()
                .addPath(new BezierLine(startPose, endPose))
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(90))
                .build();
        Follower follower = robot.drive.getFollower();
        follower.setStartingPose(startPose);
        schedule(
                new RunCommand(()-> follower.update()),
                new HoldPointCommand(follower, new Pose(0,4,0),true)
//                new HoldPointCommand(follower,new Pose(12, 4, Math.toRadians(90)), false)
//                new TurnCommand(follower, Math.PI /2 , false),
//                new TurnCommand(follower, 90.0, true, AngleUnit.DEGREES),
//                new TurnToCommand(follower, Math.PI /2),
//                new TurnToCommand(follower, 90.0, AngleUnit.DEGREES)
                );
    }

    @Override
    public void run() {
        super.run();
    }
}