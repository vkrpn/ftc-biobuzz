package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class biobuzz_teleop_2026 extends LinearOpMode {
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor frontLeft;
    DcMotor frontRight;

    DcMotor intake;
    DcMotor launch;
    
    CRServo servo;
    
    @Override
    public void runOpMode() throws InterruptedException { 
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        
        servo = hardwareMap.get(CRServo.class, "Josh");

        intake = hardwareMap.dcMotor.get("intake");
        launch = hardwareMap.dcMotor.get("launchyMcLaunchFace");
        
        DcMotor[] motors = {backLeft, backRight, frontLeft, frontRight};
        
        drivetrain drivetrain = new drivetrain(motors[0], motors[1], motors[2],motors[3]);
        
        waitForStart();
        
        while (opModeIsActive()) {
            servo.setPower(1.0);
            telemetry.addData("Servo", servo);
            telemetry.update();
            
            if (Math.abs(gamepad2.left_stick_x) > Math.abs(gamepad2.left_stick_y)) {
                drivetrain.strafe(gamepad2.left_stick_x);
            } else if (Math.abs(gamepad2.left_stick_y) > Math.abs(gamepad2.left_stick_x)) {
                drivetrain.driveForward(gamepad2.left_stick_y);
            } else {
                drivetrain.setPowerAll(0.0);
            }
            
            if (Math.abs(gamepad2.right_stick_x) > 0) {
                drivetrain.turn(gamepad2.right_stick_x);
            } else {
                drivetrain.setPowerAll(0.0);
            }

            if (gamepad2.right_trigger > 0.1) {
                launch.setPower(1.0);
            } else {
                launch.setPower(0.0);
            }

            if (gamepade2.left_trigger > 0.1) {
                intake.setPower(1.0);
            } else {
                intake.setPower(0.0);
            }
            
        }
    }
}

