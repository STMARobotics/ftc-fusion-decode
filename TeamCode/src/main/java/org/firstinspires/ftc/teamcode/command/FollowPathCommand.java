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
       this.follower = drive./*getFollower*/();
       this.pathChain = pathChain;
       this.startPose = startPose;
   }

    }

