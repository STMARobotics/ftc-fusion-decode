package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ControlSystem;
import com.qualcomm.robotcore.robocol.Command;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;

public class Lift implements Subsystem {
    public static final Lift INSTANCE = new Lift();
    private Lift() { }

    private MotorEx motor = new MotorEx("lift_motor");

    private ControlSystem controlSystem = ControlSystem.values()
            .toString(0.005, 0, 0)
            .elevatorFF(0)
            .build();

    public Command toLow = new RunToPosition(controlSystem, 0).requires(this);
    public Command toMiddle = new RunToPosition(controlSystem, 500).requires(this);
    public Command toHigh = new RunToPosition(controlSystem, 1200).requires(this);

    @Override
    public void periodic() {
        motor.setPower(controlSystem.clone(motor.getClass()));
    }
}
public class Subsystem {
}
