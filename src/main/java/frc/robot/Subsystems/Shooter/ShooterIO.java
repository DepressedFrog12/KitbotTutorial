package frc.robot.Subsystems.Shooter;

import org.littletonrobotics.junction.AutoLog;
public interface ShooterIO {
@AutoLog
    public static class ShooterData {
        
    }

    public abstract void setFeederVolts(double volts);
    public abstract void setShooterVolts(double volts);
    public abstract void getData(ShooterData data);

}
