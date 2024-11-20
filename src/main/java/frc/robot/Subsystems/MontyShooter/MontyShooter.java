package frc.robot.Subsystems.MontyShooter;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.Solenoid;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class MontyShooter implements MontyShooterIO{
    TalonFX leftLauncher;
    TalonFX rightLauncher;
    
    TalonSRX feederMotor;

    TalonSRX leftIntake;
    TalonSRX rightintake;
    TalonSRX mainIntake;
    
    Solenoid hood;
    Solenoid intake;

    public MontyShooter(PneumaticHub hub) {
        leftLauncher = new TalonFX(leftLauncherID);
        rightLauncher = new TalonFX(rightLauncherID);


    }
    @Override
    public void setFeederVolts(double volts) {
        feederMotor.set(ControlMode.PercentOutput, volts * feederStrength);

    }

    @Override
    public void setIntakeVolts(double volts) {
        mainIntake.set(ControlMode.PercentOutput, volts * feederStrength);
    }

    @Override
    public void setHoodOpen(boolean open) {
        hood.set(open);
    }

    @Override
    public void setIntakeOpen(boolean open) {
        intake.set(open);
    }

    @Override
    public boolean getIntakeOpen() {
        return intake.get();
    }

    @Override
    public boolean getHoodOpen() {
        return hood.get();
    }

    @Override
    public void getData(MontyData data) {
        data.feederVolts = feederMotor.getMotorOutputVoltage();
        data.intakeVolts = mainIntake.getMotorOutputVoltage();
        data.shooterVolts = leftLauncher.getMotorVoltage().getValueAsDouble();

        data.feederAmps = feederMotor.getSupplyCurrent();
        data.intakeAmps = mainIntake.getSupplyCurrent();
        data.shooterAmps = leftLauncher.getTorqueCurrent().getValueAsDouble();

        data.hoodOpen = hood.get();
        data.intakeOpen = intake.get();
    }

    @Override
    public void setLauncherVolts(double volts) {
        leftLauncher.set(volts * launcherStrength);
        rightLauncher.set(volts * launcherStrength);
    }

}
