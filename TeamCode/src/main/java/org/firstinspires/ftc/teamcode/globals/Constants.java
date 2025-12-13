package org.firstinspires.ftc.teamcode.globals;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class Constants {
    public enum OpModeType {
        AUTO,
        TELEOP
    }
    public enum AllianceColor {
        RED,
        BLUE
    }

    public static OpModeType OP_MODE_TYPE;
    public static AllianceColor ALLIANCE_COLOR = AllianceColor.BLUE;

    public static final double MINIMUM_SPEED = 0.5;
    public static final double INTAKE_SPEED = 1;
    public static final double INTAKE_STOP = 0;
    public static double MAX_VELOCITY = -2184;
    public static double SHOOTER_SPEED_POWER = -0.92;
    public static double SHOOTER_SPEED_VELOCITY = -2100;
    public static final double SHOOTER_STOP = 0;
    public static long SHOOTER_TIME = 250;
    public static double MIN_VELOCITY_PERCENTAGE = .95;
    public static int SHOOTING_VELOCITY = -2184;
    public static double MIN_VELOCITY = SHOOTING_VELOCITY * MIN_VELOCITY_PERCENTAGE;

    // Hardware Names
    public static final String FRONT_RIGHT_MOTOR = "FrontRightMotor";
    public static final String FRONT_LEFT_MOTOR = "FrontLeftMotor";
    public static final String BACK_LEFT_MOTOR = "BackLeftMotor";
    public static final String BACK_RIGHT_MOTOR = "BackRightMotor";

    public static final String TORQUE = "torque";
    public static final String  SPEED = "speed";

    public static final String SHOOTER_MOTOR = "shooterMotor";
    public static final String SHOOTER_SERVO = "shooterServo";

}
