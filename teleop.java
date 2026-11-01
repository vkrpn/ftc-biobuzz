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

    CRServo servo;
    
    @Override
    public void runOpMode() throws InterruptedException { 
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        
        servo = hardwareMap.get(CRServo.class, "Josh");
        
        DcMotor[] motors = {backLeft, backRight, frontLeft, frontRight};
        
        drivetrain drivetrain = new drivetrain(motors[0], motors[1], motors[2],motors[3]);
        
        waitForStart();
        
        while (opModeIsActive()) {
            servo.setPower(1.0);
            telemetry.addData("LStick x-axis", gamepad2.left_stick_x);
            telemetry.update();
            
            if (Math.abs(gamepad2.left_stick_x) > Math.abs(gamepad2.left_stick_y)) {
                drivetrain.strafe(gamepad2.left_stick_x);
            } else if (Math.abs(gamepad2.left_stick_y) > Math.abs(gamepad2.left_stick_x)) {
                drivetrain.driveForward(gamepad2.left_stick_y);
            //} else if (Math.abs(gamepad2.left_stick_x) > 1.0 && Math.abs(gamepad2.left_stick_y) > 1.0) {
            //    
            } else if (Math.abs(gamepad2.right_stick_x) > 0) {
                drivetrain.turn(gamepad2.right_stick_x);
            } else {
                drivetrain.setPowerAll(0.0);
            }
        }
    }
}

