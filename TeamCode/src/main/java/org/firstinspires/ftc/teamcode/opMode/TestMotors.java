package org.firstinspires.ftc.teamcode.opMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.controls.Bindings;
import org.firstinspires.ftc.teamcode.globals.Constants;
import org.firstinspires.ftc.teamcode.globals.Robot;

@TeleOp(name = "Test Motors")
public class TestMotors extends CommandOpMode {

    public GamepadEx driverGamepad;
    public GamepadEx operatorGamepad;

    TelemetryData telemetryData = new TelemetryData(telemetry);

    private final Robot robot = Robot.getInstance();

    MotorEx backRightMotor;
    MotorEx backLeftMotor;
    MotorEx frontRightMotor;
    MotorEx frontLeftMotor;

    @Override
    public void initialize() {
        // Must have for all opModes
        Constants.OP_MODE_TYPE = Constants.OpModeType.AUTO;
        robot.init(this);


        backRightMotor = robot.drive.getBackRightMotor();
        backLeftMotor = robot.drive.getBackLeftMotor();
        frontRightMotor = robot.drive.getFrontRightMotor();
        frontLeftMotor = robot.drive.getFrontLeftMotor();
        // Resets the command scheduler
        super.reset();

        Bindings.getDriverGamepad().getGamepadButton(GamepadKeys.Button.A).whenPressed(new InstantCommand(()-> {
            telemetry.addLine("\n\nA Button Pressed Running FRONT RIGHT");
            telemetry.addLine("\nMotor Direction: " + frontRightMotor.motor.getDirection());
            frontRightMotor.motor.setPower(.5);
        }));
        Bindings.getDriverGamepad().getGamepadButton(GamepadKeys.Button.A).whenReleased(new InstantCommand(()-> {
            frontRightMotor.motor.setPower(0);
        }));

        Bindings.getDriverGamepad().getGamepadButton(GamepadKeys.Button.B).whenPressed(new InstantCommand(()-> {
            telemetry.addLine("\n\nB Button Pressed Running BACK RIGHT");
            telemetry.addLine("\nMotor Direction: " + backRightMotor.motor.getDirection());
            backRightMotor.motor.setPower(.5);
        }));
        Bindings.getDriverGamepad().getGamepadButton(GamepadKeys.Button.B).whenReleased(new InstantCommand(()-> {
            backRightMotor.motor.setPower(0);
        }));

        Bindings.getDriverGamepad().getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(()-> {
            telemetry.addLine("\n\nX Button Pressed Running FRONT LEFT");
            telemetry.addLine("\nMotor Direction: " + frontLeftMotor.motor.getDirection());
            frontLeftMotor.motor.setPower(.5);
        }));
        Bindings.getDriverGamepad().getGamepadButton(GamepadKeys.Button.X).whenReleased(new InstantCommand(()-> {
            frontLeftMotor.motor.setPower(0);
        }));

        Bindings.getDriverGamepad().getGamepadButton(GamepadKeys.Button.Y).whenPressed(new InstantCommand(()-> {
            telemetry.addLine("\n\nY Button Pressed Running BACK LEFT");
            telemetry.addLine("\nMotor Direction: " + backLeftMotor.motor.getDirection());
            backLeftMotor.motor.setPower(.5);
        }));
        Bindings.getDriverGamepad().getGamepadButton(GamepadKeys.Button.Y).whenReleased(new InstantCommand(()-> {
            backLeftMotor.motor.setPower(0);
        }));
    }

    @Override
    public void run() {
        // DO NOT REMOVE ANY LINES BELOW! Runs the command scheduler and updates telemetry
        super.run();
        telemetryData.update();
    }
}
