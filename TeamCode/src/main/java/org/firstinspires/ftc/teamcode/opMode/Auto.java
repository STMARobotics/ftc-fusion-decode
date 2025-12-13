package org.firstinspires.ftc.teamcode.opMode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.command.FollowPathCommand;
import org.firstinspires.ftc.teamcode.command.IntakeSpinInCommand;
import org.firstinspires.ftc.teamcode.command.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.command.ShooterFireCommand;
import org.firstinspires.ftc.teamcode.globals.Constants;
import org.firstinspires.ftc.teamcode.globals.Robot;

@Autonomous(name = "Back Auto", preselectTeleOp = "SampleOpMode")
public class Auto extends CommandOpMode {

    TelemetryData telemetryData = new TelemetryData(telemetry);
    private final Robot robot = Robot.getInstance();

    int startingX = 24;
    int startingY = 127;
    int startingAngle = 140;
    int xChange = -startingX;
    int yChange = -startingY;
    int angleChange = -startingAngle;

    private PathChain BlueShootLine;
    @Override
    public void initialize() {
        // Must have for all opModes\
        Constants.OP_MODE_TYPE = Constants.OpModeType.AUTO;

        // Resets the command scheduler
        super.reset();

        // Initialize the robot (which also registers subsystems, configures CommandScheduler, etc.)
        robot.init(this);
        Pose startingPose = createPose(startingX, startingY, startingAngle);
//        Pose shootingPose = createPose(58, 93, startingAngle);
        Pose shootingPose = createPose(startingX, startingY - 40, startingAngle);
        BlueShootLine = robot.drive.pathBuilder()
//                .addPath(new BezierLine(new Pose(0, 0,  Math.toRadians(-40)),  new Pose(61 + xChange,96 + yChange, Math.toRadians(-40))))
                .addPath(new BezierLine(startingPose,  shootingPose))
                .setLinearHeadingInterpolation(startingPose.getHeading(), shootingPose.getHeading(), .8)

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

        BlueShootLine = robot.drive.pathBuilder()
//                .addPath(new BezierLine(new Pose(0, 0,  Math.toRadians(-40)),  new Pose(61 + xChange,96 + yChange, Math.toRadians(-40))))
                .addPath(new BezierLine(startingPose, shootingPose))
                .setLinearHeadingInterpolation(startingPose.getHeading(), shootingPose.getHeading(), .8)

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
        ShooterFireCommand shooterFireCommand = new ShooterFireCommand(robot.shooter);
        IntakeSpinInCommand intakeSpinInCommand = new IntakeSpinInCommand(robot.intake);
        Command shoot = new ParallelCommandGroup(
                new ParallelCommandGroup(
                    new SequentialCommandGroup(
                        new WaitCommand(1500),
                        shooterFireCommand
                    ),
                    new SequentialCommandGroup(
                        new WaitCommand(8000),
                        new InstantCommand(shooterFireCommand::stop)
                    ),
                    new SequentialCommandGroup(
                        new WaitCommand(5000),
                        intakeSpinInCommand,
                        new WaitCommand(2000),
                        new IntakeStopCommand(robot.intake)
                    )
                )
        );
        schedule(
                new RunCommand(follower::update),
                new FollowPathCommand(startingPose, BlueShootLine, robot.drive).withGlobalMaxPower(.5),
                shoot
                );
    }

    @Override
    public void run() {
        telemetry.addData("OTOS POSITION: ", robot.otos.getPosition());
        System.out.println("MOLLIE: OTOS VALUE: " + robot.otos.getPosition() + " Heading Degrees: " + Math.toDegrees(robot.otos.getPosition().h));
        System.out.println("MOLLIE: POS: " + robot.drive.getFollower().getPose());
        telemetry.update();
        super.run();
    }

    private Pose createPose (int x, int y, int degrees) {
        return new Pose(x - xChange, y - yChange, Math.toRadians(degrees - angleChange));
    }
}
