package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * This is a test of Automated Controls.
 * @author Ozaner Hansha
 */
public class TestChasis extends OpMode
{
    /**
     * Left/Right Motor Objects.
     */
    DcMotor LEFT, RIGHT;

    /**
     * The speed factor of the motors.
     */
    float speedMultiplier = 1;

    /**
     * Initilization Method. Runs before {@link loop()} method.
     */
    @Override
    public void init()
    {
      LEFT =  hardwareMap.dcMotor.get("motor1");
      RIGHT = hardwareMap.dcMotor.get("motor2");
    }

    /**
     * Calculates the speed of the motors based on the stick input.
     * @return The speed the motor should be set to.
     */
    public static float getMotorSpeed()
    {
      return (gamepad1.left_stick_y + gamepad1.left_stick_x) * speedMultiplier;
    }

    /**
     * Sets the power of the motors.
     * @param left - The power of the left motor.
     * @param right - The power of the right motor.
     */
    public static void setMotors(float left, float right)
    {
      LEFT.setPower(left);
      RIGHT.setPower(right);
    }

    /**
     * Prints the motor speed to the console.
     */
    public static void printInput()
    {
      telemetry.addData("01:", "Left Motor: " + leftSpeed);
      telemetry.addData("02:", "Right Motor: " + rightSpeed);
    }

    /**
     * Checks if left stick button its being held down and if so,
     * set the {@link speedMultiplier} to the given float value.
     * @param multiplier - The modifier to be multiplied by the speed.
     */
    public static void checkGearShift(float multiplier)
    {
      if(gamepad1.left_stick_button)
         speedMultiplier = multiplier;
      else
        speedMultiplier = 1;
    }

    /**
     * Program loop. Continously ran.
     */
    @Override
    public void loop()
    {
      checkGearShift(0.5); //Checks if the gear should shift.
      setMotors(-getMotorSpeed(), getMotorSpeed());
      printInput(); //For Debigging.
    }
}
