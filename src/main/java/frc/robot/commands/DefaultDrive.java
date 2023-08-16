package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DefaultDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem drive;

  public DefaultDrive(DriveSubsystem drive) {
    this.drive = drive;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
      drive.arcadeDrive(drive.GAMEPAD_DRIVER.getRawAxis(1), drive.GAMEPAD_DRIVER.getRawAxis(2));
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
