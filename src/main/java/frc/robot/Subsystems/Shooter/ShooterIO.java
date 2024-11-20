package frc.robot.Subsystems.Shooter;

import org.littletonrobotics.junction.AutoLog;
public interface ShooterIO {
@AutoLog
    public static class ShooterData {
        public double shooterVelocity = 0.0;
        public double feederVelocity = 0.0;

        public double shooterTemp = 0.0;
        public double feederTemp = 0.0;

        public double shooterAmps = 0.0;
        public double feederAmps = 0.0;

        public double shooterOutputVolts = 0.0;
        public double feederOutputVolts = 0.0;
    }

    public abstract void setFeederVolts(double volts);
    public abstract void setShooterVolts(double volts);
    public abstract void getData(ShooterData data);

}
