package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.gamepad.TriggerReader;

import java.util.function.DoubleSupplier;

public class Bindings {

    GamepadEx driverGamepad;
    GamepadEx operatorGamepad;

    private static Bindings INSTANCE;

    public static void init(Gamepad driverGamepad, Gamepad operatorGamepad) {
        if (INSTANCE == null) {
            INSTANCE = new Bindings(new GamepadEx(driverGamepad), new GamepadEx(operatorGamepad));
        }
    }
    private Bindings(GamepadEx driverGamepad, GamepadEx operatorGamepad) {
        this.driverGamepad = driverGamepad;
        this.operatorGamepad = operatorGamepad;
    }

    public static GamepadButton getDriverOptionKey() {
        return INSTANCE.driverGamepad.getGamepadButton(GamepadKeys.Button.OPTIONS);
    }

    public static DoubleSupplier getDriverLeftX() {
        return INSTANCE.driverGamepad::getLeftX;
    }

    public static DoubleSupplier getDriverLeftY() {
        return INSTANCE.driverGamepad::getLeftY;
    }

    public static DoubleSupplier getDriverRightX() {
        return INSTANCE.driverGamepad::getRightX;
    }

    public static DoubleSupplier getDriverRightTrigger() {
        return () -> INSTANCE.driverGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
    }

    public static GamepadButton getOperatorButtonA(){
       return INSTANCE.operatorGamepad.getGamepadButton(GamepadKeys.Button.A);
    }

    public static Trigger getOperatorLeftTrigger(){
        return new Trigger(() -> {
            double value = INSTANCE.operatorGamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
            return value > .01 || value < -.01;
        });
    }

    public static GamepadEx getDriverGamepad() {
        return INSTANCE.driverGamepad;
    }

    public static GamepadEx getOperatorGamepad() {
        return INSTANCE.operatorGamepad;
    }
}
