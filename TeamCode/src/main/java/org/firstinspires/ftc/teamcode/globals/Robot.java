package org.firstinspires.ftc.teamcode.globals;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.command.DriverControlCommand;
import org.firstinspires.ftc.teamcode.command.IntakeSpinCommand;
import org.firstinspires.ftc.teamcode.command.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.command.ShooterFireCommand;
import org.firstinspires.ftc.teamcode.command.ShooterStopCommand;
import org.firstinspires.ftc.teamcode.controls.Bindings;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Shooter;

public class Robot extends com.seattlesolvers.solverslib.command.Robot {

    // Components
    public IMU imu;

    private static final Robot instance = new Robot();
    public TelemetryData telemetryData;

    // Subsystems
    public Drive drive;
    public Intake intake;
    public Shooter shooter;

    public static Robot getInstance() {
        return instance;
    }

    public void init(CommandOpMode opMode) {
        reset();
        this.telemetryData = new TelemetryData(opMode.telemetry);
        HardwareMap hwMap = opMode.hardwareMap;
        Bindings.init(opMode.gamepad1, opMode.gamepad2);
        imu = hwMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD)));
        this.drive = new Drive(hwMap);
        this.intake = new Intake(hwMap);
        this.shooter = new Shooter(hwMap);

        register(drive, intake, shooter);

        if (Constants.OP_MODE_TYPE == Constants.OpModeType.TELEOP) {
            bindCommands();
        }

    }


    public void bindCommands() {
        Bindings.getDriverOptionKey().whenPressed(
                new InstantCommand(() -> {
                    imu.resetYaw();
                }));

        DriverControlCommand dcc = new DriverControlCommand(drive,
                Bindings.getDriverLeftY(),
                Bindings.getDriverLeftX(),
                Bindings.getDriverRightX(),
                Bindings.getDriverRightTrigger()

        );

//        this.schedule(dcc);
        this.drive.setDefaultCommand(dcc);
//        CommandScheduler.getInstance().setDefaultCommand(this.drive, dcc);

        Bindings.getOperatorLeftTrigger().whenActive(new IntakeSpinCommand(intake));
        Bindings.getOperatorLeftTrigger().whenInactive(new IntakeStopCommand(intake));
        Bindings.getOperatorRightTrigger().whenActive(new ShooterFireCommand(shooter));
        Bindings.getOperatorRightTrigger().whenInactive(new ShooterStopCommand(shooter));


    }
}
