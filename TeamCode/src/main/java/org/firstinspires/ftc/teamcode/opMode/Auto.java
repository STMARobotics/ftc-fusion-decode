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
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import com.seattlesolvers.solverslib.pedroCommand.HoldPointCommand;
import com.seattlesolvers.solverslib.pedroCommand.TurnCommand;
import com.seattlesolvers.solverslib.pedroCommand.TurnToCommand;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.command.AutoDriveCommand;
import org.firstinspires.ftc.teamcode.globals.Constants;
import org.firstinspires.ftc.teamcode.globals.Robot;
import org.firstinspires.ftc.teamcode.subsystem.Drive;

@Autonomous(name = "AutoForward", preselectTeleOp = "SampleOpMode")
public class Auto extends CommandOpMode {

    TelemetryData telemetryData = new TelemetryData(telemetry);
    private final Robot robot = Robot.getInstance();
    private final Pose startPose = new Pose(0, 0, Math.toRadians(0));
    private final Pose endPose = new Pose(12, 0, Math.toRadians(0));
    private final Pose turnPose = new Pose(12, 0, Math.toRadians(90));

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
                .addPath(new BezierLine(new Pose(55.3846, 88.61, Math.toRadians(90)), new Pose(53.30, 88.61, Math.toRadians(140))))
                .addPath(new BezierLine(new Pose(53.30, 88.61, Math.toRadians(140)), new Pose(38.38, 33.49, Math.toRadians(180))))
                .addPath(new BezierLine(new Pose(38.38, 33.49, Math.toRadians(270)), new Pose(4.38, 33.49, Math.toRadians(180))))
                .addPath(new BezierLine(new Pose(4.38, 33.49, Math.toRadians(180)), new Pose(38.38, 33.49, Math.toRadians(180))))
                .addPath(new BezierLine(new Pose(38.38, 33.49, Math.toRadians(270)), new Pose(53.32, 88.1, Math.toRadians(140))))

                .build();

        Follower follower = robot.drive.getFollower();
        follower.setStartingPose(startPose);
        schedule(
                new RunCommand(()-> follower.update()),
                new FollowPathCommand(follower, myPath).setGlobalMaxPower(.5)
//                new FollowPathCommand(follower, myPath2)
//                new HoldPointCommand(follower, new Pose(4, 0, 0), false)
//                new HoldPointCommand(follower, new Pose(0,4,0),true),
//                new HoldPointCommand(follower,new Pose(12, 4, Math.toRadians(90)), false),
//                new TurnCommand(follower, Math.PI /2 , false),
//                new TurnCommand(follower, 90.0, true, AngleUnit.DEGREES).
//                new TurnToCommand(follower, Math.PI /2),
//                new TurnToCommand(follower, 90.0, AngleUnit.DEGREES)
                );
    }

    @Override
    public void run() {
        telemetry.addData("OTOT POSITION: ", robot.otos.getPosition());
        telemetry.update();
        super.run();
    }
}