// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.MontyShooter.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.MontyShooter.MontySubsystem;

public class RunIntake extends Command {
  /** Creates a new RunIntake. */
  private final MontySubsystem montySubsystem;
  private final double speed;
  public RunIntake(MontySubsystem montySubsystem, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.montySubsystem = montySubsystem;
    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    montySubsystem.setIntakeVolts(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    montySubsystem.setIntakeVolts(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
