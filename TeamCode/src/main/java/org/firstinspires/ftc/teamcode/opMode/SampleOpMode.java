package org.firstinspires.ftc.teamcode.opMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;

import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.command.DriverControlCommand;
import org.firstinspires.ftc.teamcode.globals.Constants;
import org.firstinspires.ftc.teamcode.globals.FusionRobot;
import org.firstinspires.ftc.teamcode.subsystem.Drive;

@TeleOp(name = "Driver Controlled")
public class SampleOpMode extends CommandOpMode {

    public GamepadEx driverGamepad;
    public GamepadEx operatorGamepad;

    TelemetryData telemetryData = new TelemetryData(telemetry);

    private final FusionRobot robot = FusionRobot.getInstance();

    @Override
    public void initialize() {
        // Must have for all opModes
        Constants.OP_MODE_TYPE = Constants.OpModeType.TELEOP;

        // Resets the command scheduler
        super.reset();

        // Initialize the robot (which also registers subsystems, configures CommandScheduler, etc.)
        robot.init(hardwareMap, telemetryData);

        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        driverGamepad.getGamepadButton(GamepadKeys.Button.OPTIONS).whenPressed(
                new InstantCommand(() -> {
                    robot.imu.resetYaw();
                }));

        robot.drive.setDefaultCommand(new DriverControlCommand(robot.drive,
                () -> driverGamepad.getLeftX() ,
                () -> driverGamepad.getLeftY() ,
                () -> driverGamepad.getRightX(),
                () -> driverGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)
        ));

        register(robot.drive);
    }

    @Override
    public void run() {

        // DO NOT REMOVE ANY LINES BELOW! Runs the command scheduler and updates telemetry
        super.run();
        telemetryData.update();
    }
}
