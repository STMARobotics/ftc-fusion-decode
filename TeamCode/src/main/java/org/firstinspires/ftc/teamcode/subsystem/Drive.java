package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.pedroPathing.Constants.*;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathBuilder;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.globals.Robot;

public class Drive extends SubsystemBase {

    private final Robot robot = Robot.getInstance();
    private Follower follower;
    private Pose currentPose = new Pose();

    public double getForward() {
        return forward;
    }

    public double getStrafe() {
        return strafe;
    }

    public double getTurn() {
        return turn;
    }

    private double forward;
    private double strafe;
    private double turn;

    public Drive(HardwareMap hwMap){
        follower = createFollower(hwMap);
    }

    public void driveFieldCentric(double forward, double strafe, double turn) {
        this.forward = forward;
        this.strafe = strafe;
        this.turn = turn;
        this.follower.setTeleOpDrive(this.forward, this.strafe, this.turn, false);
    }

    public void driveRobotCentric(double forward, double strafe, double turn){
        this.forward = forward;
        this.strafe = strafe;
        this.turn = -turn;

        robot.telemetryData.addData("Heading", this.getHeading());
        robot.telemetryData.addData("Drive - Forward", this.getForward());
        robot.telemetryData.addData("Drive - Strafe", this.getStrafe());
        robot.telemetryData.addData("Drive - Turn", this.getTurn());
        this.follower.setTeleOpDrive(this.forward, this.strafe, this.turn, true);
        follower.update();
        currentPose = follower.getPose();
    }

    public PathBuilder pathBuilder() {
        return follower.pathBuilder();
    }

    public void resetLocalization() {
        Pose resetPose = new Pose();
        follower.setStartingPose(resetPose);
        follower.setPose(resetPose);
    }

    public double getHeading() {
        return robot.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    @Override
    public void periodic() {
        follower.update();
        currentPose = follower.getPose();
    }

    public Follower getFollower() {
        return follower;
    }

    public void telemetry(Telemetry telemetry) {
        // Log the position to the telemetry
        telemetry.addData("X coordinate (meters)", currentPose.getX());
        telemetry.addData("Y coordinate (meters)", currentPose.getY());
        telemetry.addData("Heading angle (radians)", currentPose.getHeading());
    }

    public void stop() {
        this.driveRobotCentric(0,0,0);
    }

    public Pose getCurrentPose() {
        return currentPose;
    }

    public void setCurrentPose(Pose currentPose) {
        this.currentPose = currentPose;
    }
}
