package frc.team2767.command.auton;

import com.moandjiezana.toml.Toml;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2767.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwitchSameCube1Deliver extends CommandGroup {
  private static final double START_POSITION_YAW = 0d;

  private static final Logger logger = LoggerFactory.getLogger(SwitchSameCube1Deliver.class);
  private final int kDistance;
  private final String settings;

  public SwitchSameCube1Deliver(StartPosition startPosition) {
    settings = startPosition == StartPosition.RIGHT ? "R_SW_S_C1D" : "L_SW_S_C1D";
    Toml toml = Robot.INJECTOR.settings().getAutonSettings(settings);
    String path = toml.getString("path");
    kDistance = toml.getLong("distance").intValue();

    PathCommand pathCommand = new PathCommand(path, startPosition.getPathAngle(START_POSITION_YAW));
  }

  @Override
  public String toString() {
    return "SwitchSameCube1Deliver{" + "settings='" + settings + '\'' + '}';
  }
}
