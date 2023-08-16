package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class TurnToAngle extends CommandBase{
    private DriveSubsystem drive;
    private double angle;
    private PIDController pid;

    public TurnToAngle(DriveSubsystem drive, double angle) {
        this.drive = drive;
        this.angle = angle;

        addRequirements(drive);
    }

    @Override
    public void initialize(){
        pid = new PIDController(1, 0, 0);
    }

    @Override
    public void execute() {
        SmartDashboard.putData(pid);
        pid.enableContinuousInput(0, 360);
        //pid.setIntegratorRange(-1, 1);
        drive.arcadeDrive(0, -pid.calculate(drive.getAngle(), angle));
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
