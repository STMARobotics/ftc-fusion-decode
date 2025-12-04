package org.firstinspires.ftc.teamcode.command;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.Drive;

public class FollowPathCommand extends CommandBase{
   private Follower follower;
   private PathChain pathChain;
   private Pose startPose;
   private Drive drive;

   private boolean holdEnd = false;
   private double maxPower = 1.5;

   public FollowPathCommand(
           Pose startPose,
           PathChain pathChain,
           Drive drive){
       this.follower = drive.getFollower();
       this.pathChain = pathChain;
       this.startPose = startPose;
       this.drive = drive;

       addRequirements(drive);

   }
   public FollowPathCommand withGlobalMaxPower(double globalMaxPower){
      maxPower = globalMaxPower;
      return this;
    }
    public FollowPathCommand withHoldEnd(boolean holdEnd){
       this.holdEnd = holdEnd;
       return this;
    }

    @Override
    public void initialize(){
       follower.setStartingPose(startPose);
       follower.setPose(startPose);
       follower.setMaxPower(maxPower);
       if (maxPower != 1.0){
           follower.followPath(pathChain, maxPower, true);
       } else {
           follower.followPath(pathChain, true);
       }
    }
    @Override
    public boolean isFinished() {
        return !follower.isBusy();
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }
}

