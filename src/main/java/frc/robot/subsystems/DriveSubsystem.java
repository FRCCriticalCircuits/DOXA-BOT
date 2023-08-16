// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.swing.GrayFilter;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  // --------------------------
  // ASSIGN MOTOR CONTROLLERS
  // --------------------------

  private WPI_VictorSPX FRONT_LEFT = new WPI_VictorSPX(Constants.FRONT_LEFT_ID);
  private WPI_VictorSPX FRONT_RIGHT = new WPI_VictorSPX(Constants.FRONT_RIGHT_ID);

  private WPI_VictorSPX FOLLOWER_LEFT = new WPI_VictorSPX(Constants.REAR_LEFT_ID);
  private WPI_VictorSPX FOLLOWER_RIGHT = new WPI_VictorSPX(Constants.REAR_RIGHT_ID);

  // --------------------------

  private MotorControllerGroup left = new MotorControllerGroup(FRONT_LEFT, FOLLOWER_LEFT);
  private MotorControllerGroup right = new MotorControllerGroup(FRONT_RIGHT, FOLLOWER_RIGHT);
  
  private ADXRS450_Gyro  gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

  public DifferentialDrive drive;

  public Joystick GAMEPAD_DRIVER;

  public DriveSubsystem() {
    GAMEPAD_DRIVER = new Joystick(Constants.DRIVER_GAMEPAD_ID);
    drive = new DifferentialDrive(left, right);
    gyro.calibrate();
    setup();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("ANGLE", gyro.getAngle());
  }

  private void setup() {
    left.setInverted(true);

    FRONT_LEFT.setNeutralMode(NeutralMode.Brake);
    FOLLOWER_LEFT.setNeutralMode(NeutralMode.Coast);

    FRONT_RIGHT.setNeutralMode(NeutralMode.Brake);
    FOLLOWER_RIGHT.setNeutralMode(NeutralMode.Coast);
  }

  public void setBrake() {
    FRONT_LEFT.setNeutralMode(NeutralMode.Brake);
    FOLLOWER_LEFT.setNeutralMode(NeutralMode.Brake);
    FRONT_RIGHT.setNeutralMode(NeutralMode.Brake);
    FOLLOWER_RIGHT.setNeutralMode(NeutralMode.Brake);
  }

  public void stopMotors() {
    FRONT_LEFT.stopMotor();
    FRONT_RIGHT.stopMotor();
  }

  public void arcadeDrive(double forward, double turn) {
    drive.arcadeDrive(forward, turn);
  }

  public void arcadeDriveVolts(double forward, double turn) {
    FRONT_LEFT.setVoltage(forward);
    FRONT_RIGHT.setVoltage(turn);
    drive.feed();
  }

  public double getAngle() {
    return gyro.getAngle();
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
