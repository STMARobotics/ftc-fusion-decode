package org.firstinspires.ftc.teamcode;

public class SubsystemGroupDontUse {
    public SubsystemGroupDontUse(Object, Object o1) {
    }

    public class MySubsystemGroup extends SubsystemGroupDontUse {
        public static final MySubsystemGroup INSTANCE = new MySubsystemGroup();
        private static final Object MyFirstSubsystem = ;
        private static final Object MySecondSubsystem = ;

        private MySubsystemGroup() {
            super(
                    MyFirstSubsystem.INSTANCE,
                    MySecondSubsystem.INSTANCE
                    public MyOpMode() {
                addComponents(
                        SubsystemComponent(MySubsystemGroup.INSTANCE)
                );
            }
            );
        }

        private Object SubsystemComponent(MySubsystemGroup instance) {
        }

        private void MyOpMode() {
        }
    }
    public class MyRobot extends SubsystemGroupDontUse {
        public static final MyRobot INSTANCE = new MyRobot();

        private MyRobot() {
            super(
                    Claw.INSTANCE,
                    Lift.INSTANCE
            );
        }
    }
    public final Command score = new SequentialGroup(
            Lift.INSTANCE.toHigh,
            Claw.INSTANCE.open
    ).named("Score");
}
