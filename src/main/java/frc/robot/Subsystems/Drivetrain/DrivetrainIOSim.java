// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain;

import com.ctre.phoenix6.controls.VoltageOut;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;
import frc.robot.Constants;

public class DrivetrainIOSim implements DrivetrainIO {

// TalonFX leftFalcon = new TalonFX(Constants.drivetrainLeftFalconID);
// TalonFX rightFalcon = new TalonFX(Constants.drivetrainRightFalconID);
  
VoltageOut leftVoltage = new VoltageOut(0);
VoltageOut rightVoltage = new VoltageOut(0);

private double leftAppliedVolts = 0.0;
private double rightAppliedVolts = 0.0;

private boolean isClosedLoop = false;
private PIDController leftPID = new PIDController(Constants.kPSim, Constants.kISim, Constants.kDSim);
private PIDController rightPID = new PIDController(Constants.kPSim, Constants.kISim, Constants.kDSim);

DifferentialDrivetrainSim physicsSim = DifferentialDrivetrainSim.createKitbotSim(
    KitbotMotor.kDoubleNEOPerSide,
    KitbotGearing.k8p45,
    KitbotWheelSize.kSixInch,
    null);
@Override
public void updateInputs(DrivetrainIOInputs inputs) {
    physicsSim.update(0.020);

    physicsSim.setInputs(leftAppliedVolts, rightAppliedVolts);
    inputs.leftOutputVolts = 0.0;
    inputs.rightOutputVolts = 0.0;

    inputs.leftVelocityMetersPerSecond = 0.0;
    inputs.rightVelocityMetersPerSecond = 0.0;

    inputs.leftPositionMeters = 0.0;
    inputs.rightPositionMeters = 0.0;

    inputs.leftVelocityMetersPerSecond = physicsSim.getLeftVelocityMetersPerSecond();
    inputs.rightVelocityMetersPerSecond = physicsSim.getRightVelocityMetersPerSecond();

    inputs.leftPositionMeters = physicsSim.getLeftPositionMeters();
    inputs.rightPositionMeters = physicsSim.getRightPositionMeters();

    inputs.leftCurrentAmps = new double[0];
    inputs.leftTempCelcius = new double[0];
    inputs.rightCurrentAmps = new double[0];
    inputs.rightTempCelcius = new double[0];

    inputs.leftCurrentAmps = new double[] {physicsSim.getLeftCurrentDrawAmps()};
    inputs.leftTempCelcius = new double[0];
    inputs.rightCurrentAmps = new double[] {physicsSim.getRightCurrentDrawAmps()};
    inputs.rightTempCelcius = new double[0];

    inputs.leftAppliedVolts = leftAppliedVolts;
    inputs.rightAppliedVolts = rightAppliedVolts;


}

    @Override
    public void setVolts(double left, double right) {
       leftAppliedVolts = -left;
       rightAppliedVolts = -right;
        isClosedLoop = false;
    }
    @Override
      public void setMetersPerSecond(double left, double right) {
        isClosedLoop = true;
        leftPID.setSetpoint(Units.radiansPerSecondToRotationsPerMinute(left * Constants.gearRatio));
        rightPID.setSetpoint(Units.radiansPerSecondToRotationsPerMinute(right * Constants.gearRatio));
}
}