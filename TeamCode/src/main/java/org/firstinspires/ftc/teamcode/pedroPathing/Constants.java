package org.firstinspires.ftc.teamcode.pedroPathing;

import static org.firstinspires.ftc.teamcode.globals.Constants.*;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.DriveEncoderConstants;
import com.pedropathing.ftc.localization.constants.OTOSConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static OTOSConstants localizerConstants;

    static {
        localizerConstants = new OTOSConstants()
                .hardwareMapName("Spark")
                .linearUnit(DistanceUnit.INCH)
                .angleUnit(AngleUnit.RADIANS)
                .offset(new SparkFunOTOS.Pose2D(1, -5, Math.toRadians(180)))
                .linearScalar(97.44084292682993)
                .angularScalar(-0.9842570182);

    }

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName(FRONT_RIGHT_MOTOR)
            .rightRearMotorName(BACK_RIGHT_MOTOR)
            .leftRearMotorName(BACK_LEFT_MOTOR)
            .leftFrontMotorName(FRONT_LEFT_MOTOR)
            .leftFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorSimple.Direction.REVERSE);


    public static FollowerConstants followerConstants = new FollowerConstants()
//            .mass(5) // must be kg
//            .forwardZeroPowerAcceleration(-58.742)
//            .lateralZeroPowerAcceleration(-82.934)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.2, 0, 0.0, 0))
            .headingPIDFCoefficients(new PIDFCoefficients(0.0, 0, 0.0, 0));


    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
//.driveEncoderLocalizer(localizerConstants)
                .OTOSLocalizer(localizerConstants)
                .build();
    }

}