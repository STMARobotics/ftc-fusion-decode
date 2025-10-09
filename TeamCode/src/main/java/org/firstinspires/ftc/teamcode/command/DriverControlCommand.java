package org.firstinspires.ftc.teamcode.command;

import com.qualcomm.robotcore.robocol.Command;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.globals.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Intake;

import java.util.function.DoubleSupplier;

public class DriverControlCommand extends CommandBase {

    private final Drive drive;
    private final DoubleSupplier forward;
    private final DoubleSupplier strafe;
    private final DoubleSupplier turn;
    private final DoubleSupplier speedModifier;



    public DriverControlCommand(Drive subsystem, DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier turn, DoubleSupplier speedModifier) {
        this.drive = subsystem;
        this.forward = forward;
        this.strafe = strafe;
        this.turn = turn;
        this.speedModifier = speedModifier;
        addRequirements(this.drive);
    }

    @Override
    public void execute() {
        double multiplier = Constants.MINIMUM_SPEED + (1 - Constants.MINIMUM_SPEED) * speedModifier.getAsDouble();
        this.drive.driveRobotCentric(- forward.getAsDouble() * multiplier, strafe.getAsDouble() * multiplier, - turn.getAsDouble() * multiplier);

    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
