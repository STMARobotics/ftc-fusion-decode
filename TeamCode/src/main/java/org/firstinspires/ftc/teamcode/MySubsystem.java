package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.robocol.Command;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;

public class MySubsystem extends Subsystem {
    public static final MySubsystem INSTANCE = new MySubsystem();
    private MySubsystem() { }
    public MotorEx motor;
    public PIDFController controller = new PIDFController(0.005, 0.0, 0.0, new StaticFeedforward(0.0));
    public String name = "lift_motor";
    public Command toLow() {
        return new RunToPosition(motor, // MOTOR TO MOVE
                0.0, // TARGET POSITION, IN TICKS
                controller, // CONTROLLER TO IMPLEMENT
                this); // IMPLEMENTED SUBSYSTEM
    }
    public Command toMiddle() {
        return new RunToPosition(motor, // MOTOR TO MOVE
                500.0, // TARGET POSITION, IN TICKS
                controller, // CONTROLLER TO IMPLEMENT
                this); // IMPLEMENTED SUBSYSTEM
    }

    public Command toHigh() {
        return new RunToPosition(motor, // MOTOR TO MOVE
                1200.0, // TARGET POSITION, IN TICKS
                controller, // CONTROLLER TO IMPLEMENT
                this); // IMPLEMENTED SUBSYSTEM
    }
    @Override
    public void initialize() {
        motor = new MotorEx(name);
    }

}
