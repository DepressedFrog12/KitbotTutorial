// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Subsystems.Drivetrain.DrivetrainIOSim;
import frc.robot.Subsystems.Drivetrain.DrivetrainIOSpark;
import frc.robot.Subsystems.Drivetrain.DrivetrainSubsystem;
import frc.robot.Subsystems.MontyShooter.MontySubsystem;
import frc.robot.Subsystems.Shooter.ShooterSubsystem;
import frc.robot.Constants;

public class RobotContainer {
  DrivetrainSubsystem drive = new DrivetrainSubsystem();
  final CommandXboxController controller = new CommandXboxController(0);
  final MontySubsystem monty = new MontySubsystem(new PneumaticHub(31));
  final ShooterSubsystem shooter;

      
  public RobotContainer() {
    configureBindings();
    shooter = new ShooterSubsystem();
  }

  private void configureBindings() {
    
    drive.setDefaultCommand(
    drive.setVoltagesArcadeCommand(
        () -> modifyJoystick(controller.getLeftY()),
        () -> modifyJoystick(controller.getRightX())));
    
  }

  private double modifyJoystick(double in) {
    if (Math.abs(in) < 0.05) {
      return 0.0;
    }
    return in * in * Math.signum(in);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
