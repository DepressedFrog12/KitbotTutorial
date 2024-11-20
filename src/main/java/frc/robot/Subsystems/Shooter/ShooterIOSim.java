package frc.robot.Subsystems.Shooter;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import static frc.robot.Constants.*;


public class ShooterIOSim implements ShooterIO{

    TalonFX shooterMotor;
    TalonFX feederMotor;

    VoltageOut shooterVolts;
    VoltageOut feederVolts;

    public ShooterIOSim() {

        shooterMotor = new TalonFX(shooterID);
        feederMotor = new TalonFX(feederID);

        shooterMotor.setInverted(true);
        feederMotor.setInverted(true);

        shooterVolts = new VoltageOut(0.0);
        feederVolts = new VoltageOut(0.0); 
    }

public void setFeederVolts(double volts){
    feederMotor.setControl(feederVolts.withOutput(volts));
}

public void setShooterVolts(double volts){
    shooterMotor.setControl(shooterVolts.withOutput(volts));
}

public void getData(ShooterData data){
    var shooterSimState = shooterMotor.getSimState();
    var feederSimState = feederMotor.getSimState();

    shooterSimState.setSupplyVoltage(RoboRioSim.getVInVoltage());
    feederSimState.setSupplyVoltage(RoboRioSim.getVInVoltage());

    data.shooterOutputVolts = shooterSimState.getMotorVoltage();
    data.feederOutputVolts = feederSimState.getMotorVoltage();
    
    data.shooterAmps = shooterSimState.getTorqueCurrent();
    data.feederAmps = feederSimState.getTorqueCurrent();

    data.shooterTemp = 0.0;
    data.feederTemp = 0.0;
    data.shooterVelocity = 0.0;
    data.feederVelocity = 0.0;
}
}