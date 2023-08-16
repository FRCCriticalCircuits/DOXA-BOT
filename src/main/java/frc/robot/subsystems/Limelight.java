// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  public NetworkTable table;

  public Limelight() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    setLEDMode(2);
  }

  
  public void setLEDMode(int ledMode){ // 0 for default. 1 for on, 2 for flash and 3 for off
    table.getEntry("ledmode").setNumber(ledMode);
}

  public double getX() {
    return table.getEntry("tx").getDouble(0);
  }

  public double getY() {
    return table.getEntry("ty").getDouble(0);
  }

  public double getTargetDistance(){
    double distance, ll_height = 18.9, target_height = 15.4;
    
    distance = (target_height - ll_height) / Math.tan(Math.toRadians(getY()));

    return distance;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("TARGET DISTANCE", getTargetDistance());
    SmartDashboard.putNumber("X OFFSET", getX());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
