# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an FTC (FIRST Tech Challenge) robotics project for the DECODE (2025-2026) competition season. It uses the NextFTC framework built on top of the standard FTC SDK for command-based robot programming with the SolversLib library integration.

## Build System and Commands

This project uses Gradle with Android Studio for building:

### Essential Build Commands
```bash
# Build the project
./gradlew build

# Build and install to connected device
./gradlew installDebug

# Clean build artifacts
./gradlew clean
```

### Project Structure
- **FtcRobotController/**: Standard FTC SDK module containing the robot controller app
- **TeamCode/**: Team-specific robot code module where all custom development happens
- **build.gradle**: Root build configuration
- **build.common.gradle**: Shared Android configuration for FTC projects
- **build.dependencies.gradle**: Dependency management

## Architecture

### Framework Stack
1. **FTC SDK**: Base Android framework for FTC robotics
2. **NextFTC**: Command-based framework layered on FTC SDK
3. **SolversLib**: Additional robotics utilities (version 0.3.2)

### Key Components
- **NextFTCOpMode**: Base class for OpModes using the NextFTC framework
- **Robot**: Base robot class (extended by FusionRobot)
- **Subsystems**: Modular robot components (Claw, Lift, etc.)
- **Commands**: Actions that can be scheduled and run on subsystems

### Code Organization
```
TeamCode/src/main/java/org/firstinspires/ftc/teamcode/
├── command/
│   └── FusionRobot.java          # Main robot class
├── subsystem/
│   ├── Claw.java                 # Claw subsystem with servo control
│   └── Lift.java                 # Lift subsystem
├── TeleOpProgram.java            # Teleop control program
└── AutonomousProgram.java        # Autonomous program
```

### Programming Patterns
- **Singleton Pattern**: Subsystems use static INSTANCE fields
- **Command Pattern**: Actions are encapsulated as Command objects
- **Component System**: OpModes use component-based architecture
- **Fluent API**: Hardware configuration uses method chaining

### Hardware Integration
- **MotorEx**: Enhanced motor control with built-in direction reversal
- **ServoEx**: Enhanced servo control 
- **MecanumDriverControlled**: Pre-built mecanum drive control
- **Gamepads**: Unified gamepad input handling

### Sample Implementation Pattern
```java
@TeleOp(name = "My OpMode")
public class MyOpMode extends NextFTCOpMode {
    public MyOpMode() {
        addComponents(
            new SubsystemComponent(MySubsystem.INSTANCE),
            BulkReadComponent.INSTANCE,
            BindingsComponent.INSTANCE
        );
    }
    
    @Override
    public void onStartButtonPressed() {
        // Schedule commands here
    }
}
```

## Development Notes

### Dependencies
- The project uses SolversLib version 0.3.2 for additional robotics functionality
- NextFTC framework is the primary development framework
- Standard FTC SDK 11.0 provides the base Android robotics platform

### Build Configuration
- Minimum SDK version: 24
- Target SDK version: 28 (intentionally older for FTC compatibility)
- Java version: 1.8
- NDK version: 21.3.6528147
- Supports both debug and release builds with proper signing

### Hardware Configuration
- Robot uses mecanum drive with 4 motors (front_left, front_right, back_left, back_right)
- Subsystems include Claw (servo-based) and Lift
- Gamepad controls include speed reduction via right bumper (50% speed)