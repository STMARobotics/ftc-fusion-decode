package org.firstinspires.ftc.teamcode;

public class Subsystem {
    public class Claw implements Subsystem {
        public static final Claw INSTANCE = new Claw();
        private Claw() { }

        private ServoExDontUse servo = new ServoExDontUse("claw_servo");

        public Command open = new SetPositionDontUse(servo, 0.1).requires(this);
        public Command close = new SetPositionDontUse(servo, 0.2).requires(this);
    }
}
