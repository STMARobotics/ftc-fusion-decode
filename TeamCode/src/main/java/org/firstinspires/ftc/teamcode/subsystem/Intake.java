package org.firstinspires.ftc.teamcode.subsystem;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.CRServoEx;
import dev.nextftc.hardware.powerable.SetPower;

public class Intake implements Subsystem {
    public static final Intake INSTANCE = new Intake();
    private Intake() { }

    private final CRServoEx TorqueServo = new CRServoEx ("torque_servo");
    private final CRServoEx SpeedServo = new CRServoEx("speed_servo");


    public Command intakeSpeed = new SetPower(SpeedServo, 1 ).requires(this);
    public Command intakeTorque = new SetPower(TorqueServo, 1).requires(this);
}
