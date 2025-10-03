# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an FTC (FIRST Tech Challenge) robotics project for the DECODE (2025-2026) competition season, built on the FTC SDK. The project uses a command-based architecture with the Solverslib framework and implements a mecanum drive system with subsystem-based organization.

## Build System and Development Commands

### Building the Project
- **Build debug APK**: `./gradlew assembleDebug`
- **Build release APK**: `./gradlew assembleRelease`
- **Install to device**: `./gradlew installDebug`
- **Clean build**: `./gradlew clean`

### Android Studio Setup
- Requires Android Studio Ladybug (2024.2) or later
- Import project using "Import project (Eclipse ADT, Gradle, etc.)"
- Uses Gradle with Android plugin 8.13.0
- Target SDK: 28, Min SDK: 24, Compile SDK: 30

### Gradle Configuration
- Main build file: `build.gradle` (rarely modified)
- Team customizations go in `TeamCode/build.gradle`
- Common build logic in `build.common.gradle`
- Dependencies managed in `build.dependencies.gradle`

## Code Architecture

### Project Structure
- **FtcRobotController/**: Core FTC SDK module with sample OpModes
- **TeamCode/**: Custom team code following command-based architecture
  - `globals/`: Robot singleton and constants
  - `subsystem/`: Hardware subsystems (Drive, Intake)
  - `command/`: Command implementations
  - `controls/`: Gamepad bindings and input handling
  - `opMode/`: OpMode implementations

### Core Architecture Patterns

#### Command-Based Framework
- Uses **Solverslib** command framework (`com.seattlesolvers.solverslib`)
- `Robot.java` is singleton pattern extending `com.seattlesolvers.solverslib.command.Robot`
- Subsystems extend `SubsystemBase`
- Commands extend `CommandBase`
- OpModes extend `CommandOpMode`

#### Robot Initialization Pattern
```java
Constants.OP_MODE_TYPE = Constants.OpModeType.TELEOP; // or AUTO
super.reset(); // Reset command scheduler
robot.init(this); // Initialize robot singleton
```

#### Subsystem Registration
All subsystems must be registered in `Robot.init()`:
```java
register(drive, intake);
```

### Key Components

#### Robot Singleton (`globals/Robot.java`)
- Central hardware and subsystem management
- IMU initialization with proper orientation
- Command binding for TeleOp mode
- Telemetry data management

#### Drive Subsystem (`subsystem/Drive.java`)
- Mecanum drive implementation using Solverslib
- Field-centric and robot-centric drive modes
- Motor configuration: FL, FR, BL, BR pattern
- IMU-based heading for field-centric driving

#### Controls System (`controls/Bindings.java`)
- Singleton pattern for gamepad management
- Wraps gamepads with `GamepadEx` from Solverslib
- Static methods for accessing controls throughout codebase
- Trigger-based input handling

#### Constants Management (`globals/Constants.java`)
- Centralized configuration values
- OpMode type tracking (AUTO/TELEOP)
- Hardware device names
- Speed and behavior constants

### Hardware Configuration Requirements
- IMU must be named "imu" in hardware configuration
- Motors: "FrontRightMotor", "FrontLeftMotor", "BackLeftMotor", "BackRightMotor"
- Servos: "torque", "speed" (for intake system)

### Dependencies
- **FtcRobotController**: Core FTC SDK dependency
- **Solverslib**: `org.solverslib:core:SNAPSHOT-7cdcc66` for command framework
- **PedroPathing**: Available but commented out in dependencies

### Development Notes
- Command binding only occurs in TELEOP mode
- Default commands must be set for subsystems requiring continuous control
- Periodic telemetry updates handled automatically via `TelemetryData`
- Field-centric drive uses IMU yaw for orientation