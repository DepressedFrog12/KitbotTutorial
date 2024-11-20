package frc.robot;

import edu.wpi.first.math.util.Units;

public class Constants {
    public static final int drivetrainLeftFalconID = 0;
    public static final int drivetrainRightFalconID = 1;


    public static final int drivetrainRightMainSparkID = 3;
    public static final int drivetrainLeftFollowSparkID = 2;
    public static final int drivetrainLeftMainSparkID = 1;
    public static final int drivetrainRightFollowSparkID = 4;

    public static final int shooterID = 0;
    public static final int feederID = 1;

    public static final double kPReal = 0.2;
    public static final double kDReal = 0.0;

    public static double kS = 0.0;
    public static double kV = 0.0;

    public static double kPSim = 0.2;
    public static double kDSim = 0.0;
    public static double kISim = 0.0;;

    public static double gearRatio = 8.46;
    public static double wheelRadius = Units.inchesToMeters(2);

    public static final Mode currentMode = Mode.SIM;

    public static enum Mode {
        REAL,
        SIM,
        REPLAY
    }
}
