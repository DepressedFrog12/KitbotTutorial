package frc.robot.Subsystems.Shooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import static frc.robot.Constants.*;

public class ShooterIOSparkmax implements ShooterIO{
    CANSparkMax shooter;
    CANSparkMax feeder;

    RelativeEncoder shooterEncoder;
    RelativeEncoder feederEncoder;

    public ShooterIOSparkmax(){
        shooter = new CANSparkMax(shooterID, MotorType.kBrushless);
        feeder = new CANSparkMax(feederID, MotorType.kBrushless);

        shooterEncoder = shooter.getEncoder();
        feederEncoder = feeder.getEncoder();

        shooter.restoreFactoryDefaults();
        feeder.restoreFactoryDefaults();

        shooter.setInverted(true);
        feeder.setInverted(true);

        shooter.burnFlash();
        feeder.burnFlash();
    }
    @Override
    public void setFeederVolts(double volts) {
        feeder.setVoltage(volts);
    }
    @Override
    public void setShooterVolts(double volts) {
        shooter.setVoltage(volts);
    }
    @Override
    public void getData(ShooterData data) {
        data.shooterOutputVolts = shooter.getAppliedOutput();
        data.feederOutputVolts = feeder.getAppliedOutput();

        data.shooterAmps = shooter.getOutputCurrent();
        data.feederAmps = feeder.getOutputCurrent();

        data.shooterTemp = shooter.getMotorTemperature();
        data.feederTemp = feeder.getMotorTemperature();

        data.shooterVelocity = shooterEncoder.getVelocity();
        data.feederVelocity = feederEncoder.getVelocity();
    }
}
