package frc.team2767.control;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2767.Settings;
import frc.team2767.command.LogCommand;
import frc.team2767.command.climber.ClimbCommand;
import frc.team2767.command.climber.HoldCommand;
import frc.team2767.command.drive.ZeroGyroYawCommand;
import frc.team2767.command.flipper.FlipperLaunchCommand;
import frc.team2767.command.lift.Down;
import frc.team2767.command.lift.LoadParameters;
import frc.team2767.command.lift.Position;
import frc.team2767.command.lift.SaveZero;
import frc.team2767.command.lift.Stop;
import frc.team2767.command.lift.Up;
import frc.team2767.command.lift.Zero;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Accesses driver control input. */
@Singleton
public class Controls {

  private static final Logger logger = LoggerFactory.getLogger(Controls.class);

  private static final int DRIVER_RIGHT_X_AXIS = 0;
  private static final int DRIVER_RIGHT_Y_AXIS = 1;
  private static final int DRIVER_LEFT_Y_AXIS = 2;
  private static final int DRIVER_TUNER_AXIS = 6;
  private static final int DRIVER_LEFT_X_AXIS = 5;

  private static final int DRIVER_LEFT_BUTTON = 1;
  private static final int DRIVER_RIGHT_SHOULDER_BUTTON = 2;
  private static final int DRIVER_RESET_BUTTON = 3;
  private static final int DRIVER_LEFT_SHOULDER_DOWN_BUTTON = 4;
  private static final int DRIVER_LEFT_SHOULDER_UP_BUTTON = 5;

  private static final int DRIVER_LEFT_TRIM_X_POS = 7;
  private static final int DRIVER_LEFT_TRIM_X_NEG = 6;
  private static final int DRIVER_RIGHT_TRIM_X_NEG = 13;
  private static final int DRIVER_RIGHT_TRIM_X_POS = 12;

  private static final int DRIVER_LEFT_TRIM_Y_POS = 8;
  private static final int DRIVER_LEFT_TRIM_Y_NEG = 9;
  private static final int DRIVER_RIGHT_TRIM_Y_POS = 10;
  private static final int DRIVER_RIGHT_RIM_Y_NEG = 11;

  private static final int GAME_A_BUTTON = 1;
  private static final int GAME_B_BUTTON = 2;
  private static final int GAME_X_BUTTON = 3;
  private static final int GAME_Y_BUTTON = 4;
  private static final int GAME_LEFT_SHOULDER_BUTTON = 5;
  private static final int GAME_RIGHT_SHOULDER_BUTTON = 6;
  private static final int GAME_BACK_BUTTON = 7;
  private static final int GAME_START_BUTTON = 8;
  private static final int GAME_LEFT_STICK_BUTTON = 9;
  private static final int GAME_RIGHT_STICK_BUTTON = 10;

  private static final int POWERUP_INTAKE_PORTAL = 0;
  private static final int BOARD_BUTTON_1 = 1;
  private static final int BOARD_BUTTON_2 = 2;
  private static final int BOARD_BUTTON_3 = 3;
  private static final int BOARD_BUTTON_4 = 4;
  private static final int BOARD_BUTTON_5 = 5;
  private static final int BOARD_BUTTON_6 = 6;

  private static final int POWERUP_SHOULDER_DOWN_1 = 1;
  private static final int POWERUP_SHOULDER_UP_2 = 2;
  private static final int POWERUP_ELEVATOR_DOWN_3 = 3;
  private static final int POWERUP_ELEVATOR_UP_4 = 4;
  private static final int POWERUP_CLIMB_BUTTON_5 = 5;
  private static final int POWERUP_TRANSFORM_6 = 6;
  private static final int POWERUP_MID_SCALE_7 = 7;
  private static final int POWERUP_PORTAL_INTAKE_8 = 8;
  private static final int POWERUP_INTAKE_OUT_9 = 9;
  private static final int POWERUP_GROUND_INTAKE_POS_10 = 10;
  private static final int POWERUP_INTAKE_IN_11 = 11;
  private static final int POWERUP_EXCHANGE_POS_12 = 12;

  private static final int POWERUP_BOARD_BUTTON_Y_AXIS = 1;
  private static final int POWERUP_BOARD_BUTTON_X_AXIS = 0;

  private static final int LEFT = 1;
  private static final int CENTER_LEFT = 2;
  private static final int CENTER_RIGHT = 3;
  private static final int CENTER_RIGHT_EXCHANGE = 4;

  private final List<DigitalInput> digitalInputs = new ArrayList<>();

  private final Joystick gameController = new Joystick(0);
  private final Joystick driverController = new Joystick(1);
  private final Joystick buttonBoard = new Joystick(3);
  private final Joystick powerupButtonBoard = new Joystick(2);

  private final Button powerupShoulderDownButton =
      new JoystickButton(powerupButtonBoard, POWERUP_SHOULDER_DOWN_1);
  private final Button powerupShoulderUpButton =
      new JoystickButton(powerupButtonBoard, POWERUP_SHOULDER_UP_2);
  private final Button powerupElevatorDownButton =
      new JoystickButton(powerupButtonBoard, POWERUP_ELEVATOR_DOWN_3);
  private final Button powerupElevatorUpButton =
      new JoystickButton(powerupButtonBoard, POWERUP_ELEVATOR_UP_4);
  private final Button powerupClimbButton =
      new JoystickButton(powerupButtonBoard, POWERUP_CLIMB_BUTTON_5);
  //  private final Trigger powerupSwitchPosButton;
  //  private final Trigger powerupScaleHighButton;
  private final Button powerupScaleMidButton =
      new JoystickButton(powerupButtonBoard, POWERUP_MID_SCALE_7);
  private final Button powerupPortalIntakeButton =
      new JoystickButton(powerupButtonBoard, POWERUP_PORTAL_INTAKE_8);
  private final Button powerupIntakeOutButton =
      new JoystickButton(powerupButtonBoard, POWERUP_INTAKE_OUT_9);
  private final Button powerupGroundIntakePosButton =
      new JoystickButton(powerupButtonBoard, POWERUP_GROUND_INTAKE_POS_10);
  private final Button powerupIntakeInButton =
      new JoystickButton(powerupButtonBoard, POWERUP_INTAKE_IN_11);
  //  private final Trigger powerupStowButton;
  private final Button powerupExchangePosButton =
      new JoystickButton(powerupButtonBoard, POWERUP_EXCHANGE_POS_12);
  private final Button powerupTransformerButton =
      new JoystickButton(powerupButtonBoard, POWERUP_TRANSFORM_6);

  private final Button zeroGyroButton = new JoystickButton(driverController, DRIVER_RESET_BUTTON);
  private final Button button1 = new JoystickButton(buttonBoard, BOARD_BUTTON_1);
  private final Button button2 = new JoystickButton(buttonBoard, BOARD_BUTTON_2);
  private final Button button3 = new JoystickButton(buttonBoard, BOARD_BUTTON_3);
  private final Button button4 = new JoystickButton(buttonBoard, BOARD_BUTTON_4);
  private final Button button5 = new JoystickButton(buttonBoard, BOARD_BUTTON_5);
  private final Button button6 = new JoystickButton(buttonBoard, BOARD_BUTTON_6);

  private final Button autonButton = new JoystickButton(buttonBoard, BOARD_BUTTON_1);
  private final Button testButton = new JoystickButton(buttonBoard, BOARD_BUTTON_2);
  private final Button azimuthTestButton = new JoystickButton(buttonBoard, BOARD_BUTTON_3);
  private final Button flipper =
      new JoystickButton(driverController, DRIVER_LEFT_SHOULDER_UP_BUTTON);

  //  private final Button autonButton = new JoystickButton(buttonBoard, BOARD_BUTTON_1);
  //  private final Button testButton = new JoystickButton(buttonBoard, BOARD_BUTTON_2);
  //  private final Button azimuthTestButton = new JoystickButton(buttonBoard, BOARD_BUTTON_3);
  private final Button liftUpButton = new JoystickButton(gameController, GAME_Y_BUTTON);
  private final Button liftDownButton = new JoystickButton(gameController, GAME_A_BUTTON);

  List<Button> buttons = new ArrayList<>();

  @Inject
  Controls(Settings settings) {
    for (int i = 0; i < 6; i++) {
      digitalInputs.add(i, new DigitalInput(i));
    }

    assignSmartDashboardButtons();

    if (settings.isIsolatedTestMode()) {
      logger.info("initializing controls in isolated test mode");
      return;
    }

    //    powerupScaleHighButton = new ButtonBoardAxisTriggerYNeg(this);
    //    powerupScaleHighButton.whenActive(new LogCommand("Button powerup scale high is active"));
    powerupScaleMidButton.whenActive(new LogCommand("scale mid"));

    //    powerupStowButton = new ButtonBoardAxisTriggerXPos(this);
    //    powerupStowButton.whenActive(new LogCommand("Powerup stow is active"));
    //
    //    powerupSwitchPosButton = new ButtonBoardAxisTriggerXNeg(this);
    //    powerupSwitchPosButton.whenActive(new LogCommand("Powerup switch pos is active"));

    powerupElevatorUpButton.whenActive(new LogCommand("elevator up"));
    powerupElevatorDownButton.whenActive(new LogCommand("elevator down"));

    powerupShoulderUpButton.whenActive(new LogCommand("shoulder up"));
    powerupShoulderDownButton.whenActive(new LogCommand("shoulder down"));

    powerupExchangePosButton.whenActive(new LogCommand("exchange Pos"));

    powerupPortalIntakeButton.whenActive(new LogCommand("portal intake button"));

    powerupGroundIntakePosButton.whenActive(new LogCommand("ground intake pos"));

    powerupIntakeInButton.whenActive(new LogCommand("intake in"));
    powerupIntakeOutButton.whenActive(new LogCommand("intake out"));

    powerupClimbButton.whenActive(new LogCommand("climb"));

    powerupTransformerButton.whenActive(new LogCommand("transformers!!!!!!"));

    logger.info("initializing robot controls");
    flipper.whenPressed(new FlipperLaunchCommand());
    zeroGyroButton.whenPressed(new ZeroGyroYawCommand());
    zeroGyroButton.whenPressed(new ZeroGyroYawCommand());
    liftUpButton.whenPressed(new ClimbCommand());
    liftUpButton.whenReleased(new HoldCommand());
    //    button3.whenPressed(new PathCommand(LEFT));
    //    button4.whenPressed(new PathCommand(CENTER_LEFT));
    //    button5.whenPressed(new PathCommand(CENTER_RIGHT));
    //    button6.whenPressed(new PathCommand(CENTER_RIGHT_EXCHANGE));

  }

  /**
   * Read the selected autonomous mode from the binary-code hexadecimal switch. Don't be fooled by
   * hex numbers when debugging, for example switch position 24 (hex) = 36 (dec).
   *
   * <p>The switch wiring labelled 0-5 are connected to corresponding DIO ports 0-5.
   *
   * @return the switch position
   */
  public int getAutonomousSwitchPosition() {
    int val = 0;
    for (int i = 6; i-- > 0; ) {
      val = val << 1;
      val = (val & 0xFE) | (digitalInputs.get(i).get() ? 0 : 1);
    }
    return val;
  }

  private void assignSmartDashboardButtons() {
    SmartDashboard.putData("Lift/LoadParametersCommand", new LoadParameters());
    SmartDashboard.putData("Lift/PositionCommand", new Position());
    SmartDashboard.putData("Lift/UpCommand", new Up());
    SmartDashboard.putData("Lift/DownCommand", new Down());
    SmartDashboard.putData("Lift/StopCommand", new Stop());
    SmartDashboard.putData("Lift/SaveZero", new SaveZero());
    SmartDashboard.putData("Lift/Zero", new Zero());

    SmartDashboard.putData("Shoulder/Up", new frc.team2767.command.shoulder.Up());
    SmartDashboard.putData("Shoulder/Down", new frc.team2767.command.shoulder.Down());
    SmartDashboard.putData("Shoulder/Stop", new frc.team2767.command.shoulder.Stop());
  }

  /**
   * Return the driver controller left stick Y-axis position.
   *
   * @return the position, range is -1.0 (full reverse) to 1.0 (full forward)
   */
  public double getForward() {
    return -driverController.getRawAxis(DRIVER_LEFT_Y_AXIS);
  }

  public boolean getLeftButton() {
    return driverController.getRawButton(DRIVER_LEFT_BUTTON);
  }

  public boolean getRightShoulder() {
    return driverController.getRawButton(DRIVER_RIGHT_SHOULDER_BUTTON);
  }

  public boolean getLeftShoulderUp() {
    return driverController.getRawButton(DRIVER_LEFT_SHOULDER_UP_BUTTON);
  }

  public boolean getLeftShoulderDown() {
    return driverController.getRawButton(DRIVER_LEFT_SHOULDER_DOWN_BUTTON);
  }

  public double getDriverRightY() {
    return -driverController.getRawAxis(DRIVER_RIGHT_Y_AXIS);
  }

  public boolean getDriverLeftTrimXPos() {
    return driverController.getRawButton(DRIVER_LEFT_TRIM_X_POS);
  }

  public boolean getDriverLeftTrimXNeg() {
    return driverController.getRawButton(DRIVER_LEFT_TRIM_X_NEG);
  }

  public boolean getDriverRightTrimXNeg() {
    return driverController.getRawButton(DRIVER_RIGHT_TRIM_X_NEG);
  }

  public boolean getDriverRightTrimXPos() {
    return driverController.getRawButton(DRIVER_RIGHT_TRIM_X_POS);
  }

  public boolean getDriverLeftTrimYPos() {
    return driverController.getRawButton(DRIVER_LEFT_TRIM_Y_POS);
  }

  public boolean getDriverLeftTrimYNeg() {
    return driverController.getRawButton(DRIVER_LEFT_TRIM_Y_NEG);
  }

  public boolean getDriverRightTrimYPos() {
    return driverController.getRawButton(DRIVER_RIGHT_TRIM_Y_POS);
  }

  public boolean getDriverRightTrimYNeg() {
    return driverController.getRawButton(DRIVER_RIGHT_RIM_Y_NEG);
  }

  /**
   * Return the driver controller left stick X-axis position.
   *
   * @return the position, range is -1.0 (full left) to 1.0 (full right)
   */
  public double getStrafe() {
    return driverController.getRawAxis(DRIVER_LEFT_X_AXIS);
  }

  /**
   * Return the driver controller right stick X-axis position.
   *
   * @return the position, range is -1.0 (full left) to 1.0 (full right)
   */
  public double getAzimuth() {
    return driverController.getRawAxis(DRIVER_RIGHT_X_AXIS);
  }

  /**
   * Return the "Ch 6. Flaps Gain" knob value.
   *
   * @return the knob position, range is -1.0 (full left) to 1.0 (full right)
   */
  public double getTuner() {
    return driverController.getRawAxis(DRIVER_TUNER_AXIS);
  }

  public double getButtonBoardYNeg() {
    return powerupButtonBoard.getRawAxis(POWERUP_BOARD_BUTTON_Y_AXIS);
  }

  public double getButtonBoardXNeg() {
    return powerupButtonBoard.getRawAxis(POWERUP_BOARD_BUTTON_X_AXIS);
  }

  public double getButtonBoardXPos() {
    return powerupButtonBoard.getRawAxis(POWERUP_BOARD_BUTTON_X_AXIS);
  }

  /**
   * Return the "Reset" button on flight sim controller.
   *
   * @return true if the button is pressed
   */
  public boolean getResetButton() {
    return driverController.getRawButton(DRIVER_RESET_BUTTON);
  }

  /**
   * Return the gamepad "A" button
   *
   * @return true if the button is pressed
   */
  public boolean getGamepadAButton() {
    return gameController.getRawButton(GAME_A_BUTTON);
  }

  /**
   * Return the gamepad "B" button
   *
   * @return true if the button is pressed
   */
  public boolean getGamepadBButton() {
    return gameController.getRawButton(GAME_B_BUTTON);
  }

  /**
   * Return the gamepad "X" button
   *
   * @return true if the button is pressed
   */
  public boolean getGamepadXButton() {
    return gameController.getRawButton(GAME_X_BUTTON);
  }

  /**
   * Return the gamepad "Y" button
   *
   * @return true if the button is pressed
   */
  public boolean getGamepadYButton() {
    return gameController.getRawButton(GAME_Y_BUTTON);
  }

  /**
   * Return the gamepad "back" button
   *
   * @return true if the button is pressed
   */
  public boolean getGamepadBackButton() {
    return gameController.getRawButton(GAME_BACK_BUTTON);
  }

  /**
   * Return the gamepad "start" button
   *
   * @return true if the button is pressed
   */
  public boolean getGamepadStartButton() {
    return gameController.getRawButton(GAME_START_BUTTON);
  }
}
