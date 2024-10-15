// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.util.Units;
import frc.robot.Constants;

/** Add your docs here. */
public class DrivetrainIOSpark implements DrivetrainIO {
    private final CANSparkMax leftMain = new CANSparkMax(Constants.drivetrainLeftMainSparkID, MotorType.kBrushless);
    private final CANSparkMax leftFollow = new CANSparkMax(Constants.drivetrainLeftFollowSparkID, MotorType.kBrushless);
    private final RelativeEncoder leftEncoder = leftMain.getEncoder();

    private final CANSparkMax rightMain = new CANSparkMax(Constants.drivetrainRightMainSparkID, MotorType.kBrushless);
    private final CANSparkMax rightFollow = new CANSparkMax(Constants.drivetrainRightFollowSparkID, MotorType.kBrushless);
    private final RelativeEncoder rightEncoder = rightMain.getEncoder();

    private boolean isClosedLoop = false;
    private final SparkPIDController leftPID = leftMain.getPIDController();
    private final SparkPIDController rightPID = rightMain.getPIDController();

    public DrivetrainIOSpark() {
        leftMain.restoreFactoryDefaults();
        leftFollow.restoreFactoryDefaults();
        rightMain.restoreFactoryDefaults();
        rightFollow.restoreFactoryDefaults();

        leftMain.setCANTimeout(250);
        leftFollow.setCANTimeout(250);
        rightMain.setCANTimeout(250);
        rightFollow.setCANTimeout(250);

        leftMain.setInverted(true);
        rightMain.setInverted(false);

        leftFollow.follow(leftMain);
        rightFollow.follow(rightMain);

        leftMain.enableVoltageCompensation(12.0);
        rightMain.enableVoltageCompensation(12.0);

        leftMain.setSmartCurrentLimit(40);
        rightMain.setSmartCurrentLimit(40);

        leftPID.setP(Constants.kPReal);
        leftPID.setD(Constants.kDReal);
        rightPID.setP(Constants.kPReal);
        rightPID.setD(Constants.kDReal);

        leftMain.burnFlash();
        leftFollow.burnFlash();
        rightMain.burnFlash();
        rightFollow.burnFlash();
    }

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.leftRotationsRad = Units.rotationsToRadians(leftEncoder.getPosition() / Constants.gearRatio);
        inputs.rightRotationsRad = Units.rotationsToRadians(rightEncoder.getPosition() / Constants.gearRatio);

        inputs.leftPositionMeters = (leftEncoder.getPosition() / Constants.gearRatio) * 2 * Math.PI * Constants.wheelRadius;
        inputs.rightPositionMeters = (rightEncoder.getPosition() / Constants.gearRatio) * 2 * Math.PI * Constants.wheelRadius;

        inputs.leftVelocityRadsPerSecond = Units.rotationsPerMinuteToRadiansPerSecond(leftEncoder.getVelocity() / Constants.gearRatio);
        inputs.rightVelocityRadsPerSecond = Units.rotationsPerMinuteToRadiansPerSecond(rightEncoder.getVelocity() / Constants.gearRatio);


    }

    @Override
    public void setVolts(double left, double right) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVolts'");
    }

    
}
