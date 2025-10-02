package org.firstinspires.ftc.teamcode.globals;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.command.DriverControlCommand;
import org.firstinspires.ftc.teamcode.command.IntakeSpinCommand;
import org.firstinspires.ftc.teamcode.command.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.controls.Bindings;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Intake;

public class Robot extends com.seattlesolvers.solverslib.command.Robot {

    // Components
    public IMU imu;

    private static final Robot instance = new Robot();
    public TelemetryData telemetryData;

    // Subsystems
    public Drive drive;
    public Intake intake;

    public static Robot getInstance() {
        return instance;
    }

    public void init(CommandOpMode opMode) {
        reset();
        this.telemetryData =new TelemetryData(opMode.telemetry);
        HardwareMap hwMap = opMode.hardwareMap;
        Bindings.init(opMode.gamepad1, opMode.gamepad2);
        imu = hwMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD)));
        drive = new Drive(hwMap);

        if (Constants.OP_MODE_TYPE == Constants.OpModeType.TELEOP) {
            bindCommands();
        }

        register(drive, intake);
    }


    public void bindCommands() {
        Bindings.getDriverOptionKey().whenPressed(
                new InstantCommand(() -> {
                    imu.resetYaw();
                }));

        drive.setDefaultCommand(new DriverControlCommand(drive,
                Bindings.getDriverLeftX() ,
                Bindings.getDriverLeftY() ,
                Bindings.getDriverRightX(),
                Bindings.getDriverRightTrigger()
        ));

        Bindings.getOperatorLeftTrigger().whenActive(new IntakeSpinCommand(intake));
        Bindings.getOperatorLeftTrigger().whenInactive(new IntakeStopCommand(intake));

    }
}
