package frc.robot.Subsystems.Shooter.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Shooter.ShooterSubsystem;

public class Intake extends Command{
    private ShooterSubsystem intake;
    private double voltMod;
    public Intake(double voltMod, ShooterSubsystem intake) {
    
        this.intake = intake;
        this.voltMod = voltMod;
    }

    public void execute() {
        intake.setShooterOutput(voltMod);
    }

    public void end(boolean interrupted) {
        intake.setShooterOutput(0);
    }

    public boolean isFinished() {
        return false;
    }
}
