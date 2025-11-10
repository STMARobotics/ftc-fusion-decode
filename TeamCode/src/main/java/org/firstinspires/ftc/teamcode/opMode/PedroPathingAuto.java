package org.firstinspires.ftc.teamcode.opMode;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import  com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.globals.Robot;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.sql.SQLOutput;

@Autonomous(name = "PedroPathing Auto", group = "Examples")
public class PedroPathingAuto extends CommandOpMode {
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState;

    TelemetryData telemetryData = new TelemetryData(telemetry);
    private final Robot robot = Robot.getInstance();

    private final Pose startPose = new Pose(12, 0, Math.toRadians(0));
    private final Pose endPose = new Pose(0, 0, 0);

    private PathChain myPath;

    @Override
    public void initialize() {
        // Must have for all opModes
        org.firstinspires.ftc.teamcode.globals.Constants.OP_MODE_TYPE = org.firstinspires.ftc.teamcode.globals.Constants.OpModeType.AUTO;

        // Resets the command scheduler
        super.reset();

        // Initialize the robot (which also registers subsystems, configures CommandScheduler, etc.)
        robot.init(this);

        follower = Constants.createFollower(hardwareMap);

        myPath = follower.pathBuilder()
                .addPath(new BezierLine(startPose, endPose))
                .build();

    }

    @Override
    public void run() {
        System.out.println("MOLLIE: CREATING PATH follower is " + follower.isBusy());
        follower.setStartingPose(startPose);
        follower.setPose(startPose);
        follower.setMaxPower(1);
        follower.followPath(myPath, true);

        System.out.println("MOLLIE: GOING IN FOLLOWER LOOP follower is " + follower.isBusy());

        while (! follower.isBusy()) {
//            System.out.println("MOLLIE: IN LOOP FOLLOWER IS " + follower.isBusy());
        }

        System.out.println("MOLLIE: I AM DONE");

        // DO NOT REMOVE ANY LINES BELOW! Runs the command scheduler and updates telemetry
        super.run();

    }

//    @Override
//    public void Loop(){
//        follower.update();
//        autonomousPathUpdate();
//
//        telemetry.addData("path state", pathState);
//        telemetry.addData("x", follower.getPose().getX());
//        telemetry.addData("y", follower.getPose().getY());
//        telemetry.addData("heading",follower.getPose().getHeading());
//        telemetry.update();
//    }


    }






