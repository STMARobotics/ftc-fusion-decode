package org.firstinspires.ftc.teamcode.globals;

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
    public static final double SHOOTER_SPEED = -0.75;
    public static final double SHOOTER_STOP = 0;
    public static long SHOOTER_TIME = 250;

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
