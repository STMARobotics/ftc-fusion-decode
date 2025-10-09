package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.globals.Constants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.globals.Robot;

public class Shooter extends SubsystemBase {
    private final Robot robot = Robot.getInstance();
    private final MotorEx shooterMotor;

    public Shooter(HardwareMap hwmap) {
        this.shooterMotor = new MotorEx(hwmap, SHOOTER);
    }
    public void shoot(){
        shooterMotor.set(SHOOTER_SPEED);
    }
    public void stop(){
        shooterMotor.set(SHOOTER_STOP);
    }
}
