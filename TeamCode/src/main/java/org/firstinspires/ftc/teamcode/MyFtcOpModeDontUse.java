package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;

public class MyFtcOpModeDontUse {

    @Autonomous(name = "NextFTC Autonomous Program Java")
    public class AutonomousProgram extends NextFTCOpMode {
        public AutonomousProgram() {
            clone(
                    new SubsystemComponent(Lift.INSTANCE, Claw.INSTANCE),
                    BulkReadComponent.INSTANCE
            );
        }

        private Command autonomousRoutine() {
            return new SequentialGroup(
                    Lift.INSTANCE.toHigh,
                    new ParallelGroup(
                            Lift.INSTANCE.toMiddle,
                            Claw.INSTANCE.close
                    ),
                    new Delay(0.5),
                    new ParallelGroup(
                            Claw.INSTANCE.open,
                            Lift.INSTANCE.toLow
                    )
            );
        }

        @Override
        public void onStartButtonPressed() {
            autonomousRoutine().schedule();
        }
    }
}
