package frc.robot.Subsystems.Shooter;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.Subsystems.Shooter.ShooterIO.ShooterData;

public class ShooterSubsystem extends SubsystemBase {

    ShooterIO io;
    CommandXboxController controller = new CommandXboxController(0);
    ShooterData data = new ShooterData();

    public ShooterSubsystem() {
        switch (Constants.currentMode) {
            case SIM:
                io = new ShooterIOSim();
                break;

            case REAL:
                io = new ShooterIOSparkmax();
                break;

            default:
                break;
        }
    }

    public void setFeederOutput(double volts) {
        io.setFeederVolts(volts);
    }
    
    public void setShooterOutput(double volts){
        io.setShooterVolts(volts);
    }

    public void periodic() {
        io.getData(data);
        Logger.recordOutput("Shooter Speed: ", data.shooterVelocity);
        Logger.recordOutput("Feeder Speed: ", data.feederVelocity);
        Logger.recordOutput("Shooter Volts: ", data.shooterOutputVolts);
        Logger.recordOutput("Feeder Volts: ", data.feederOutputVolts);
    }
}