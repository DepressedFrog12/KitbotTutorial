package frc.robot.Subsystems.MontyShooter;

public interface MontyShooterIO {

    public static class MontyData {
        public double shooterVolts;
        public double feederVolts;
        public double intakeVolts;

        public double shooterAmps;
        public double feederAmps;
        public double intakeAmps;

        public boolean hoodOpen;
        public boolean intakeOpen;
    }

    public abstract void setLauncherVolts(double volts);
    public abstract void setFeederVolts(double volts);
    public abstract void setIntakeVolts(double volts);
    public abstract void setHoodOpen(boolean open);
    public abstract void setIntakeOpen(boolean open);
    public abstract boolean getIntakeOpen();
    public abstract boolean getHoodOpen();
    public abstract void getData(MontyData data);
}
