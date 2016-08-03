package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Tony_Air on 12/11/15.
 */
public class ServoDebug extends OpMode {

    Servo servo1, servo2, servo3, servo4;

    int servo = 1;
    int servoPosition = 0;

    @Override
    public void init() {

        servo1 = hardwareMap.servo.get("servo1");
        servo2 = hardwareMap.servo.get("servo2");
        servo3 = hardwareMap.servo.get("servo3");
        servo4 = hardwareMap.servo.get("servo4");



    }

    @Override
    public void loop() {

        int x = 1;

        if(gamepad1.a) {
            x++;
        }
        if(x == 5){
            x=1;
        }

        while(gamepad1.dpad_down && servoPosition >= 0){
            servoPosition--;
        }
        while(gamepad1.dpad_up && servoPosition <= 255){
            servoPosition++;
        }

        
        switch (servo){
            case 1:
                servo1.setPosition(servoPosition);
                break;

            case 2:
                servo1.setPosition(servoPosition);
                break;

            case 3:
                servo1.setPosition(servoPosition);
                break;

            case 4:
                servo1.setPosition(servoPosition);
                break;


        }

        telemetry.addData("01:", "Position: " + servoPosition);


    }

}
