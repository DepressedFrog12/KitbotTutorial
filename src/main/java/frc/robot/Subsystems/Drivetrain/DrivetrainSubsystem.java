// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import java.util.function.DoubleSupplier;
import java.util.function.BooleanSupplier;
import org.littletonrobotics.junction.Logger;

public class DrivetrainSubsystem extends SubsystemBase {
  public final DrivetrainIO io;
  DrivetrainIOInputsAutoLogged inputs = new DrivetrainIOInputsAutoLogged();

  DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(new Rotation2d(), 0, 0);

  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(24));

  SimpleMotorFeedforward ff = new SimpleMotorFeedforward(Constants.kS, Constants.kV);

  private void setVoltages(double left, double right) {
    io.setVolts(left, right);
  }

  public Command setVoltagesCommand(DoubleSupplier left, DoubleSupplier right) {
    return new RunCommand(() -> this.setVoltages(left.getAsDouble(), right.getAsDouble()), this);
  }

  public Command setVoltagesArcadeCommand(DoubleSupplier drive, DoubleSupplier steer) {
    return new RunCommand(() -> {
      var speeds = DifferentialDrive.arcadeDriveIK(drive.getAsDouble(), steer.getAsDouble(), false);
      this.setVoltages(speeds.left * 12, speeds.right * 12);
    }, this);
  }

  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem(DrivetrainIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    io.updateInputs(inputs);
    Logger.processInputs("Drivetrain", inputs);

    odometry.update(
        odometry.getPoseMeters().getRotation()
            .plus(Rotation2d.fromRadians((inputs.leftVelocityMetersPerSecond - inputs.rightVelocityMetersPerSecond)
                * 0.020 / Units.inchesToMeters(26))),
        inputs.leftPositionMeters, inputs.rightPositionMeters);

    Logger.recordOutput("Drivebase Pose", odometry.getPoseMeters());
  }

  public void drive(double speed, double angle, boolean isClosedLoop) {
    var wheelSpeeds = DifferentialDrive.arcadeDriveIK(speed, angle, false);
    if (isClosedLoop) {
      io.setMetersPerSecond(wheelSpeeds.left, wheelSpeeds.right);
    } else {
      io.setVolts(wheelSpeeds.left * 12.0, wheelSpeeds.right * 12.0);
    }
  }
  public Command driveCommand(DoubleSupplier speed, DoubleSupplier angle, BooleanSupplier isClosedLoop){
    return new RunCommand(() -> drive(speed.getAsDouble(), angle.getAsDouble(), isClosedLoop.getAsBoolean()), this);
  }
}
