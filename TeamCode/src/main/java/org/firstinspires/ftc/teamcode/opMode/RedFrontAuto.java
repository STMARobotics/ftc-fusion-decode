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

@Autonomous(name = "RED Front Auto", preselectTeleOp = "Driver Controlled")
public class RedFrontAuto extends CommandOpMode {

    TelemetryData telemetryData = new TelemetryData(telemetry);
    private final Robot robot = Robot.getInstance();

    int PAUSE_TO_START_SHOOTER = 2500;
    int PAUSE_TO_STOP_SHOOTER = 10000;
    int PAUSE_TO_START_INTAKE = 7000;
    int PAUSE_TO_STOP_INTAKE = 5000;

    int startingY = 90;
    int startingX = 10;
    int startingAngle = 90;
    int shootingY = 88;
    int shootingX = 85;
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
                                new WaitCommand(PAUSE_TO_START_SHOOTER),
                                shooterFireCommand
                        ),
                        new SequentialCommandGroup(
                                new WaitCommand(PAUSE_TO_STOP_SHOOTER),
                                new InstantCommand(shooterFireCommand::stop)
                        ),
                        new SequentialCommandGroup(
                                new WaitCommand(PAUSE_TO_START_INTAKE),
                                intakeSpinInCommand,
                                new WaitCommand(PAUSE_TO_STOP_INTAKE),
                                new IntakeStopCommand(robot.intake)
                        )
                )
        );
        schedule(
                new RunCommand(follower::update),
                new FollowPathCommand(startingPose, BlueShootLine, robot.drive).withGlobalMaxPower(.7),
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
