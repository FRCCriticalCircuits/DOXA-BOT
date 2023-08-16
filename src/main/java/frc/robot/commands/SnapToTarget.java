package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Limelight;

public class SnapToTarget extends CommandBase {
  private DriveSubsystem drive;
  private Limelight lime;

  private PIDController controller = new PIDController(0.0325, 0.038, 0.01);

  public SnapToTarget(DriveSubsystem drive, Limelight lime) {
    this.drive = drive;
    this.lime = lime;

    addRequirements(drive);
  } 

  @Override
  public void initialize(){
    drive.drive.setSafetyEnabled(false);
    //controller.setIntegratorRange(-4, 4);
  }

  @Override
  public void execute() {
    controller.setIntegratorRange(-2, 2);
    //if(drive.GAMEPAD_DRIVER.getRawButton(7)){
      drive.arcadeDrive(0, -controller.calculate(lime.getX(), 0));
      drive.drive.feed();
    //}

    SmartDashboard.putNumber("LIME X", lime.getX());

    SmartDashboard.putData(controller);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    if(controller.atSetpoint() || lime.getX() == 0){
      return true;
    }else {
      return false;
    }
  }
}
