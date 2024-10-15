// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain;

import org.littletonrobotics.junction.AutoLog;

/** Add your docs here. */
public interface DrivetrainIO {
    @AutoLog
    public static class DrivetrainIOInputs {
        public double leftOutputVolts = 0.0;
        public double rightOutputVolts = 0.0;

        public double leftVelocityMetersPerSecond = 0.0;
        public double rightVelocityMetersPerSecond = 0.0;

        public double leftPositionMeters = 0.0;
        public double rightPositionMeters = 0.0;

        public double leftRotationsRad = 0.0;
        public double rightRotationsRad = 0.0;

        public double leftVelocityRadsPerSecond = 0.0;
        public double rightVelocityRadsPerSecond = 0.0;

        public double leftVelocityGoalMetersPerSecond = 0.0;
        public double rightVelocityGoalMetersPerSecond = 0.0;

        public double leftAppliedVolts = 0.0;
        public double rightAppliedVolts = 0.0;

        public double[] leftCurrentAmps = new double[0];
        public double[] leftTempCelcius = new double[0];
        public double[] rightCurrentAmps = new double[0];
        public double[] rightTempCelcius = new double[0];

        public boolean isClosedLoop = false;
    }
    
    public abstract void updateInputs(DrivetrainIOInputs inputs);

    public abstract void setVolts(double left, double right);
}