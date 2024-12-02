package frc.robot.Subsystems.MontyShooter.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.MontyShooter.MontySubsystem;

public class ToggleHood extends Command {
    public final MontySubsystem montySubsystem;

    public ToggleHood(MontySubsystem montySubsystem) {
            this.montySubsystem = montySubsystem;
    }

    public void initialize() {
        montySubsystem.setHoodOpen(!montySubsystem.getHoodOpen());
    }
    public boolean isFinished() {
        return true;
    }
}
