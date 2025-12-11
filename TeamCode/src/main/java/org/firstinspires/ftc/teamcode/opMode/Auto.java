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
                .addPath(new BezierLine(new Pose(14, 126,  Math.toRadians(90)),  new Pose(56,48, Math.toRadians(90))))
//                .addPath(new BezierLine(new Pose(51, 93, Math.toRadians(140)), new Pose(37, 34, Math.toRadians(180))))
//                .addPath(new BezierLine(new Pose(37, 34, Math.toRadians(180)), new Pose(12, 35, Math.toRadians(180))))
//                .addPath(new BezierLine(new Pose(12, 35, Math.toRadians(180)), new Pose(51, 94, Math.toRadians(180))))
//                .addPath(new BezierLine(new Pose(51, 94, Math.toRadians(270)), new Pose(37, 60, Math.toRadians(140))))
//                .addPath(new BezierLine(new Pose(37, 60, Math.toRadians(270)), new Pose(10, 59, Math.toRadians(140))))
//                .addPath(new BezierLine(new Pose(10, 59, Math.toRadians(270)), new Pose(51, 94, Math.toRadians(140))))
//                .addPath(new BezierLine(new Pose(51, 94, Math.toRadians(270)), new Pose(37, 84, Math.toRadians(140))))
//                .addPath(new BezierLine(new Pose(37, 84, Math.toRadians(270)), new Pose(12, 84, Math.toRadians(140))))
//                .addPath(new BezierLine(new Pose(12, 84, Math.toRadians(270)), new Pose(51, 94, Math.toRadians(140))))
//                .addPath(new BezierLine(new Pose(51, 94, Math.toRadians(270)), new Pose(24, 5, Math.toRadians(140))))







                .build();

        Follower follower = robot.drive.getFollower();
//        follower.setStartingPose(startPose);
        schedule(
                new RunCommand(()-> follower.update()),
                new FollowPathCommand(follower, myPath, false, .5).setGlobalMaxPower(.5)
                );
    }

    @Override
    public void run() {
        telemetry.addData("OTOT POSITION: ", robot.otos.getPosition());
        telemetry.update();
        super.run();
    }
}