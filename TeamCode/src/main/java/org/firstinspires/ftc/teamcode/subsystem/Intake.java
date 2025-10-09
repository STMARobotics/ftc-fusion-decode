package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.globals.Constants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;

import org.firstinspires.ftc.teamcode.globals.Robot;

public class Intake extends SubsystemBase {
    private final Robot robot = Robot.getInstance();
    private final CRServoEx torqueServo;
    public  CRServoEx speedServo;

    public static final int SPIN_SPEED = 10;
    public Intake (HardwareMap hwMap){
        this.torqueServo = new CRServoEx(hwMap, TORQUE);
        this.speedServo = new CRServoEx(hwMap,SPEED);

        this.torqueServo.setRunMode(CRServoEx.RunMode.RawPower);
        this.speedServo.setRunMode(CRServoEx.RunMode.RawPower);
    }
    public void spin(){
        speedServo.set(INTAKE_SPEED);
        torqueServo.set(INTAKE_SPEED);
    }

    public void stop() {
        speedServo.set(INTAKE_STOP);
        torqueServo.set(INTAKE_STOP);
    }
}
