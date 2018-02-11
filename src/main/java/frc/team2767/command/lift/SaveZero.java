package frc.team2767.command.lift;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2767.Robot;
import frc.team2767.subsystem.LiftSubsystem;

public class SaveZero extends InstantCommand {

  private final LiftSubsystem liftSubsystem;

  public SaveZero() {
    liftSubsystem = Robot.INJECTOR.liftSubsystem();
    requires(liftSubsystem);
  }

  @Override
  protected void initialize() {
    liftSubsystem.saveAbsoluteZeroPosition();
  }
}
