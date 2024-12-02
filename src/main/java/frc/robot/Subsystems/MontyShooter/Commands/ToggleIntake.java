// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.MontyShooter.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.MontyShooter.MontySubsystem;

public class ToggleIntake extends Command {
  private final MontySubsystem montySubsystem;
  /** Creates a new toggleIntake. */
  public ToggleIntake(MontySubsystem montySubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.montySubsystem = montySubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    montySubsystem.setIntakeOpen(!montySubsystem.getIntakeOpen());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
