package frc.robot.Subsystems.MontyShooter;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.MontyShooter.MontyShooterIO.MontyData;

public class MontySubsystem extends SubsystemBase {
    MontyShooterIO montyIO;
    MontyData montyData = new MontyData();

    public MontySubsystem(PneumaticHub hub) {
        montyIO = new MontyShooter(hub);
    }

    
    public void setLauncherVolts(double volts) {
        montyIO.setLauncherVolts(volts);
    }
    
    public void setFeederVolts(double volts) {
        montyIO.setFeederVolts(volts);
    }
    
    public void setIntakeVolts(double volts) {
        montyIO.setIntakeVolts(volts);
    }
    
    public void setHoodOpen(boolean open) {
        montyIO.setHoodOpen(open);
    }
    
    public void setIntakeOpen(boolean open) {
        montyIO.setIntakeOpen(open);
    }
    
    public boolean getIntakeOpen() {
        return montyIO.getIntakeOpen();
    }
    
    public boolean getHoodOpen() {
        return montyIO.getHoodOpen();
    }
    
    public void getData(MontyData data) {
        montyIO.getData(data);
    }
    
    @Override
    public void periodic() {
        montyIO.getData(montyData);

        Logger.recordOutput("Shooter Volts: ", montyData.shooterVolts);
        Logger.recordOutput("Feeder Volts: ", montyData.feederVolts);
        Logger.recordOutput("Intake Volts: ", montyData.intakeVolts);
        Logger.recordOutput("Shooter Amps: ", montyData.shooterAmps);
        Logger.recordOutput("Feeder Amps: ", montyData.feederAmps);
        Logger.recordOutput("Intake Amps: ", montyData.intakeAmps);
        Logger.recordOutput("Hood Open: ", montyData.hoodOpen);
        Logger.recordOutput("Intake Open: ", montyData.intakeOpen);
    }
}