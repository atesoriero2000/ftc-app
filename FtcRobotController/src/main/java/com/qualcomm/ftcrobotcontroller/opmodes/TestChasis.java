package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

/**
 * Created by Tony_Air on 10/1/15.
 */

public class TestChasis extends OpMode {

    DcMotor LEFT, RIGHT, LIFT_TILT, LIFT;

    int steps2vertical = 2880;
    boolean tiltInPositionMode = false;

    boolean liftInPositionMode = false;

    float leftSpeed, rightSpeed;

    float speedLimit = .50f;
    float rotationLimit = .50f;//rotation reduction
    float tiltSpeedLimit = .20f;//tilt motor reduction
    float liftSpeedLimit = 1f;//lift motor reduction


    float reductionMin = .5f;//slow speed
    float reduction = 1f;//full speed

    int reverse = -1;//start backwards

    public void init() {

        RIGHT = hardwareMap.dcMotor.get("motor1");
        LEFT = hardwareMap.dcMotor.get("motor2");


//        RIGHT.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
//        LEFT.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        LIFT_TILT = hardwareMap.dcMotor.get("motor3");
        LIFT = hardwareMap.dcMotor.get("motor4");


//        LIFT.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
//        LIFT.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
//
//        LIFT_TILT.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
//        LIFT_TILT.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
//
//        LIFT_TILT.setDirection(DcMotor.Direction.FORWARD);
//        LIFT_TILT.setTargetPosition(steps2vertical);
//        LIFT_TILT.setPower(1);
//        tiltInPositionMode = true;


    }


    public void loop() {

//        if (LIFT_TILT.getCurrentPosition() == LIFT_TILT.getTargetPosition() && tiltInPositionMode) {
//
//            LIFT_TILT.setPower(0);
//            LIFT_TILT.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
//            LIFT_TILT.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
//
//        }
//
//        if (LIFT.getCurrentPosition() == LIFT.getTargetPosition() && liftInPositionMode) {
//
//            LIFT.setPower(0);
//            LIFT.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
//            LIFT.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
//
//        }
//
//        if(gamepad2.a) {//sets tilt to upright
//
//            LIFT_TILT.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
//            LIFT_TILT.setTargetPosition(0);
//            LIFT_TILT.setPower(1);
//            //set direction
//            tiltInPositionMode = true;
//        }
//
//        if(gamepad2.b){//sets tilt to level
//
//            LIFT.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
//            LIFT.setTargetPosition(0);
//            LIFT.setPower(1);
//            //set direction
//            liftInPositionMode = true;
//
//        }
//
//
//
//        if(!tiltInPositionMode){
//            LIFT_TILT.setPower(gamepad2.left_stick_x);
//        }
//
//        if(!liftInPositionMode){
//            LIFT.setPower(gamepad2.right_stick_x);
//        }

        LIFT_TILT.setPower(-gamepad2.left_stick_y * tiltSpeedLimit);//y axis is inverted
        LIFT.setPower(-gamepad2.right_stick_y * liftSpeedLimit);


        if (gamepad1.a) {// reverses driving for pushing blocks
            reverse *= -1;
        }

        float speed = gamepad1.left_stick_y * speedLimit * reverse;
        float rotation = gamepad1.left_stick_x * rotationLimit;//rotation reduction


        if (gamepad1.b) {//makes slower driving mode

            if (reduction == 1f) {
                reduction = reductionMin;

            } else if (reduction == reductionMin) {
                reduction = 1f;

            }
        }
//        if (speed>0) {
//            if (rotation > 0) {// instead of +rotation and -rotation to maximize forward speed
//                rightSpeed = speed - rotation;
//                leftSpeed = speed;
//
//            } else if (rotation <= 0) {
//                rightSpeed = speed;
//                leftSpeed = speed + rotation;
//            }
//        }else if(speed<=0){
//            if (rotation > 0) {// instead of +rotation and -rotation to maximize forward speed
//                rightSpeed = speed + rotation;
//                leftSpeed = speed;
//
//            } else if (rotation <= 0) {
//                rightSpeed = speed;
//                leftSpeed = speed - rotation;
//            }
//
//        }

        rightSpeed = speed + rotation;
        leftSpeed = speed - rotation;

        leftSpeed *= -reduction;// to reverse motor
        rightSpeed *= reduction;


        LEFT.setPower(leftSpeed);
        RIGHT.setPower(rightSpeed);

        telemetry.addData("01:", "Left Motor: " + leftSpeed);
        telemetry.addData("02:", "Right Motor: " + rightSpeed);

        telemetry.addData("03:", "Reverse: " + reverse);

        telemetry.addData("04:", "Tilt Motor: " + gamepad2.left_stick_y * tiltSpeedLimit);
        telemetry.addData("05:", "Lift Motor: " + gamepad2.right_stick_y * liftSpeedLimit);

    }


}
