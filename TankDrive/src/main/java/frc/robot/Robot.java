package frc.robot;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {

    // Motor controllers for the left side
    CANSparkMax m_leftLeader = new CANSparkMax(3, CANSparkMax.MotorType.kBrushless);
    CANSparkMax m_leftFollower = new CANSparkMax(4, CANSparkMax.MotorType.kBrushless);

    // Motor controllers for the right side
    CANSparkMax m_rightLeader = new CANSparkMax(1, CANSparkMax.MotorType.kBrushless);
    CANSparkMax m_rightFollower = new CANSparkMax(2, CANSparkMax.MotorType.kBrushless);

    // Xbox controller for driver input
    XboxController m_driverJoy = new XboxController(0);

    // DifferentialDrive object for controlling the robot's drive
    DifferentialDrive m_drive = new DifferentialDrive(m_leftLeader, m_rightLeader);

    @Override
    public void robotInit() {
        // Set up followers to follow the leaders
        m_leftFollower.follow(m_leftLeader);
        m_rightFollower.follow(m_rightLeader);

        // Invert the right side motors if needed
        m_rightLeader.setInverted(true);
    }

    @Override
    public void teleopPeriodic() {
        // Get joystick input for left and right sides
        // Invert the values if needed based on your robot's orientation
        double leftSpeed = -m_driverJoy.getRawAxis(1);
        double rightSpeed = -m_driverJoy.getRawAxis(5); // Assuming axis 5 for the right joystick

        // Drive the robot using tank drive
        m_drive.tankDrive(leftSpeed, rightSpeed);
    }
}
