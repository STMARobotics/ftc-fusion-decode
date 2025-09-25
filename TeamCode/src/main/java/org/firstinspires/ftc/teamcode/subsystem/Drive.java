package org.firstinspires.ftc.teamcode.subsystem;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.globals.FusionRobot;

public class Drive extends SubsystemBase {

    private final FusionRobot robot = FusionRobot.getInstance();
    public final MecanumDrive mecanumDrive;

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

    public Drive(){
        // input motors exactly as shown below
        mecanumDrive = new MecanumDrive(robot.frontLeftMotor, robot.frontRightMotor, robot.backLeftMotor, robot.backRightMotor);
    }

    public void driveFieldCentric(double forward, double strafe, double turn) {
        this.forward = forward;
        this.strafe = strafe;
        this.turn = turn;
        this.mecanumDrive.driveFieldCentric(forward, strafe, turn, getHeading(), false);
    }

    public double getHeading() {
        return robot.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    @Override
    public void periodic() {
        robot.telemetryData.addData("Heading", this.getHeading());
        robot.telemetryData.addData("Drive - Forward", this.getForward());
        robot.telemetryData.addData("Drive - Strafe", this.getStrafe());
        robot.telemetryData.addData("Drive - Turn", this.getTurn());
    }
}
