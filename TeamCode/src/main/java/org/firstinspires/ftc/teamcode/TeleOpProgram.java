package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Claw;
import org.firstinspires.ftc.teamcode.subsystem.Lift;

import dev.nextftc.bindings.Range;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;
import java.util.function.Supplier;

@TeleOp(name = "NextFTC TeleOp Program Java")
public class TeleOpProgram extends NextFTCOpMode {
    public TeleOpProgram() {
        addComponents(
                new SubsystemComponent(Lift.INSTANCE, Claw.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    // change the names and directions to suit your robot
    private final MotorEx frontLeftMotor = new MotorEx("front_left").reversed();
    private final MotorEx frontRightMotor = new MotorEx("front_right");
    private final MotorEx backLeftMotor = new MotorEx("back_left").reversed();
    private final MotorEx backRightMotor = new MotorEx("back_right");

    @Override
    public void onStartButtonPressed() {

        Command driverControlled = new MecanumDriverControlled(
                frontLeftMotor,
                frontRightMotor,
                backLeftMotor,
                backRightMotor,
                getDoubleSupplier(Gamepads.gamepad1().leftStickY()),
                getDoubleSupplier(Gamepads.gamepad1().leftStickX()),
                getDoubleSupplier(Gamepads.gamepad1().rightStickX())
        );
        driverControlled.schedule();

        Gamepads.gamepad2().dpadUp()
                .whenBecomesTrue(Lift.INSTANCE.toHigh)
                .whenBecomesFalse(Claw.INSTANCE.open);

        Gamepads.gamepad2().rightTrigger().greaterThan(0.2)
                .whenBecomesTrue(
                        Claw.INSTANCE.close.then(Lift.INSTANCE.toHigh)
                );

        Gamepads.gamepad2().leftBumper().whenBecomesTrue(
                Claw.INSTANCE.open.and(Lift.INSTANCE.toLow)
        );

        Gamepads.gamepad1().rightBumper().whenBecomesTrue(
                driverControlled
        );
    }

    @NonNull
    private static Supplier<Double> getDoubleSupplier(Range range) {
        return () -> {
            if (Gamepads.gamepad1().rightBumper().get()) {
                return range.get() * .5;
            } else {
                return range.get();
            }
        };
    }
}
