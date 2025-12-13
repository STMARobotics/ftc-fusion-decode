package org.firstinspires.ftc.teamcode.opMode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
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

@Autonomous(name = "Front Auto", preselectTeleOp = "SampleOpMode")
public class FrontAuto extends CommandOpMode {

    TelemetryData telemetryData = new TelemetryData(telemetry);
    private final Robot robot = Robot.getInstance();

    int startingX = 90;
    int startingY = 10;
    int startingAngle = 90;
    int shootingX = 88;
    int shootingY = 115;
    int shootingAngle = 37;

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
        Pose shootingPose = createPose(shootingX, shootingY, shootingAngle);
        BlueShootLine = robot.drive.pathBuilder()
                .addPath(new BezierLine(startingPose, shootingPose))
                .setLinearHeadingInterpolation(startingPose.getHeading(), shootingPose.getHeading(), .8)
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
