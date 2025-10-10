package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.globals.Constants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.globals.Robot;

public class Drive extends SubsystemBase {

    private final Robot robot = Robot.getInstance();
    public final MecanumDrive mecanumDrive;
    private final MotorEx frontRightMotor;
    private final MotorEx frontLeftMotor;
    private final MotorEx backLeftMotor;
    private final MotorEx backRightMotor;

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
        // input motors exactly as shown below
        this.frontRightMotor = new MotorEx(hwMap, FRONT_RIGHT_MOTOR);
        this.frontLeftMotor = new MotorEx(hwMap, FRONT_LEFT_MOTOR);
        this.backLeftMotor = new MotorEx(hwMap, BACK_LEFT_MOTOR);
        this.backRightMotor = new MotorEx(hwMap, BACK_RIGHT_MOTOR);
        mecanumDrive = new MecanumDrive(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
    }

    public void driveFieldCentric(double forward, double strafe, double turn) {
        this.forward = forward;
        this.strafe = strafe;
        this.turn = turn;
        this.mecanumDrive.driveFieldCentric(forward, strafe, turn, getHeading(), false);
    }
    public void driveRobotCentric(double forward, double strafe, double turn){
        this.forward = forward;
        this.strafe = strafe;
        this.turn = turn;

        robot.telemetryData.addData("Heading", this.getHeading());
        robot.telemetryData.addData("Drive - Forward", this.getForward());
        robot.telemetryData.addData("Drive - Strafe", this.getStrafe());
        robot.telemetryData.addData("Drive - Turn", this.getTurn());

        this.mecanumDrive.driveRobotCentric(this.strafe, this.forward, this.turn);

    }

    public double getHeading() {
        return robot.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    @Override
    public void periodic() {

    }

    public void stop() {
        this.driveRobotCentric(0,0,0);
    }
}
