package frc.robot.Subsystems.Shooter.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Shooter.ShooterSubsystem;

public class Shoot extends Command {
    private ShooterSubsystem shooter;
    private double voltMod;

    public Shoot(double voltMod, ShooterSubsystem shooter) {
        this.shooter = shooter;
        this.voltMod = voltMod;
    }

    public void execute() {
        shooter.setShooterOutput(voltMod);
    }

    public void end(boolean interrupted) {
        shooter.setShooterOutput(0);
    }

    public boolean isFinished() {
        return false;
    }
}
