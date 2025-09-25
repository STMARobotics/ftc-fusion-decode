package org.firstinspires.ftc.teamcode.globals;


import static org.firstinspires.ftc.teamcode.globals.Constants.FRONT_RIGHT_MOTOR;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.Robot;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.subsystem.Drive;

public class FusionRobot extends Robot {

    // Components
    public MotorEx frontRightMotor;
    public MotorEx frontLeftMotor;
    public MotorEx backLeftMotor;
    public MotorEx backRightMotor;
    public IMU imu;

    private static final FusionRobot instance = new FusionRobot();
    public TelemetryData telemetryData;

    // Subsystems
    public Drive drive;

    public static FusionRobot getInstance() {
        return instance;
    }

    public void init(HardwareMap hwMap, TelemetryData telemetryData) {
        this.telemetryData = telemetryData;
        frontRightMotor = new MotorEx(hwMap, FRONT_RIGHT_MOTOR);
        frontLeftMotor = new MotorEx(hwMap, FRONT_RIGHT_MOTOR);
        backLeftMotor = new MotorEx(hwMap, FRONT_RIGHT_MOTOR);
        backRightMotor = new MotorEx(hwMap, FRONT_RIGHT_MOTOR);

        imu = hwMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD)));

        drive = new Drive();
    }

}
