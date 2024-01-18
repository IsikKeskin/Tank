// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
 // Motor controllers for the left side
  CANSparkMax m_leftLeader = new CANSparkMax(3, CANSparkMax.MotorType.kBrushed);
  CANSparkMax m_leftFollower = new CANSparkMax(4, CANSparkMax.MotorType.kBrushed);

  // Motor controllers for the right side
  CANSparkMax m_rightLeader = new CANSparkMax(1, CANSparkMax.MotorType.kBrushed);
  CANSparkMax m_rightFollower = new CANSparkMax(2, CANSparkMax.MotorType.kBrushed);

  // DifferentialDrive object for controlling the robot's drive
  DifferentialDrive m_drive = new DifferentialDrive(m_leftLeader, m_rightLeader);

  /** Creates a new ExampleSubsystem. */
  public DriveTrain() {
  // Set up followers to follow the leaders
  m_leftFollower.follow(m_leftLeader);
  m_rightFollower.follow(m_rightLeader);

  // Invert the right side motors if needed
  m_rightLeader.setInverted(true);
}
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand(DoubleSupplier leftStick, DoubleSupplier rightStick) {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return run(
        () -> {
          double leftSpeed = -leftStick.getAsDouble();
          double rightSpeed = -rightStick.getAsDouble();
          m_drive.tankDrive(leftSpeed, rightSpeed);
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}