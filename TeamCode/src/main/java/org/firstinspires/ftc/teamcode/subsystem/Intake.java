package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.globals.Constants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;

import org.firstinspires.ftc.teamcode.globals.Robot;

public class Intake extends SubsystemBase {
    private final Robot robot = Robot.getInstance();
    private CRServoEx torque;
    public  CRServoEx speed;

    public static final int SPIN_SPEED = 10;
    public Intake (HardwareMap hwMap){
        this.torque = new CRServoEx(hwMap, TORQUE);
        this.speed = new CRServoEx(hwMap,SPEED);
    }
    public void spin(){

    }

}
