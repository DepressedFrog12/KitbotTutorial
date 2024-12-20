// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Subsystems.Drivetrain.DrivetrainIOSim;
import frc.robot.Subsystems.Drivetrain.DrivetrainIOSpark;
import frc.robot.Subsystems.Drivetrain.DrivetrainSubsystem;
import frc.robot.Subsystems.Shooter.ShooterSubsystem;
import frc.robot.Subsystems.Shooter.ShooterIO;
import frc.robot.Subsystems.Shooter.Commands.Intake;
import frc.robot.Subsystems.Shooter.Commands.Shoot;

public class RobotContainer {
  final DrivetrainSubsystem drive;
  final CommandXboxController controller = new CommandXboxController(0);
  final ShooterSubsystem shooter = new ShooterSubsystem();
      
  public RobotContainer() {
        switch (Constants.currentMode) {
        case REAL:
        drive = new DrivetrainSubsystem(new DrivetrainIOSpark());
        configureBindings();
        break;

        case SIM:
        drive = new DrivetrainSubsystem(new DrivetrainIOSim());
        configureBindings();
        break;
        
        default:
        drive = new DrivetrainSubsystem(new DrivetrainIOSpark());
        configureBindings();
        break;
  }
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
