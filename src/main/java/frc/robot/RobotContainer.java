// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.SnapToTarget;
import frc.robot.commands.TurnToAngle;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private DriveSubsystem m_DriveSubsystem = new DriveSubsystem();
  private Limelight lime = new Limelight();

  private final SendableChooser<Command> autoChooser = new SendableChooser<Command>();

  private Joystick driver_gamepad = new Joystick(Constants.DRIVER_GAMEPAD_ID);

  private CommandGenericHID driverController = new CommandGenericHID(Constants.DRIVER_GAMEPAD_ID);

  //private final Command m_autoCommand;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_DriveSubsystem.setDefaultCommand(
      new DefaultDrive(m_DriveSubsystem)
    );

    //autoChooser.setDefaultOption("DRIVE STRAIGHT", object);
  }

  private Trigger leftTrigger = new JoystickButton(driver_gamepad, 7);
  private Trigger leftBumper = new JoystickButton(driver_gamepad, 5);

  private void configureButtonBindings() {
     leftTrigger.whileTrue(new SnapToTarget(m_DriveSubsystem, lime));
     leftBumper.whileTrue(new TurnToAngle(m_DriveSubsystem, 0));
  }

  /*
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
  */
}
