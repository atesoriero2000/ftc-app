package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelController;

/**
 * Created by tonytesoriero on 9/11/16.
 */
public class DigitalIO extends OpMode {

    DigitalChannel IO1;
    boolean IO1_State = false;

    @Override
    public void init() {

        IO1 = hardwareMap.digitalChannel.get("IO1");
        IO1.setMode(DigitalChannelController.Mode.OUTPUT);
        IO1.setState(false);

    }

    @Override
    public void loop() {


        if(gamepad1.a) {
            IO1_State = true;
        }else{
            IO1_State = false;
        }

        IO1.setState(IO1_State);

        telemetry.addData("01:", "IO1: " + IO1_State);

    }
}
