package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * ☺ Hi! This is the perfect teleop code for December 16, 2017! ☺
 */
@TeleOp(name = "♪ ♥ Drive Mode 1 (nonlinear bumpers) ♥  ♪", group = "Our Teleop")
//@Disabled
public class FinalPerfectTeleopWithBumpers extends LinearOpMode {

    /* This says use MasterHardwareClass */
    MasterHardwareClass robot = new MasterHardwareClass();

    /*These values are used for the drive*/
    double frontLeft;
    double frontRight;
    double backLeft;
    double backRight;
    double verticalMax = 0;
    double verticalMin = 0;
    double clawMax = 0;
    double clawMin = 0;

    boolean trigger;

    @Override
    public void runOpMode() {
        // get a reference to our compass
//        robot.sideRangeSensor.initialize();
//        robot.frontRangeSensor.initialize();

        /* The init() method of the hardware class does all the work here*/
        robot.init(hardwareMap);

        /* Initialize the vertical arm encoder */
        robot.verticalArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sleep(100);
        robot.verticalArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Wait for the start button
        telemetry.addLine("!☻ Ready to Run ☻!");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {

            // Display the current value
            telemetry.addLine("Hi~♪");
            telemetry.addData("Claw Opening Controls", "X is Close, B is Open");
            telemetry.addData("Claw Moving Controls", "Use the D-Pad ↑ & ↓ buttons!");

            telemetry.addData("Encoder Mode:", robot.verticalArmMotor.getMode());
            telemetry.addData("Encoder Data:", robot.verticalArmMotor.getCurrentPosition());
            telemetry.addLine("Front RS Data");
            telemetry.addData("cm", "%.2f cm", robot.frontRangeSensor.cmUltrasonic());
            telemetry.addLine("Wheel power");
            telemetry.addData("Front Left:", robot.frontLeftMotor.getPower());
            telemetry.addData("Front Right:", robot.frontRightMotor.getPower());
            telemetry.addData("Back Left:", robot.backLeftMotor.getPower());
            telemetry.addData("Back Right:", robot.backRightMotor.getPower());
            telemetry.update();

        /* Put the servo arm up */
           robot.gemServo.setPosition(robot.xPosUp);

        /* Claw Motor Control */
            if (gamepad2.x && robot.clawMotor.getCurrentPosition() > clawMin) {
                if (robot.ClawPower != robot.clawClose) {
                    robot.clawMotor.setPower(robot.clawClose);
                    robot.ClawPower = robot.clawClose;
                }
            }
            else{
                if(gamepad2.x && robot.clawMotor.getCurrentPosition() <= clawMin){
                    if (robot.ClawPower != robot.clawStill) {
                        robot.clawMotor.setPower(robot.clawStill);
                        robot.ClawPower = robot.clawStill;
                    }
                }
                else{
                    if (robot.ClawPower != robot.clawStill) {
                        robot.clawMotor.setPower(robot.clawStill);
                        robot.ClawPower = robot.clawStill;
                    }
                }
            }

            if (gamepad2.b && robot.clawMotor.getCurrentPosition() < clawMax) {
                if (robot.ClawPower != robot.clawOpen) {
                    robot.clawMotor.setPower(robot.clawOpen);
                    robot.ClawPower = robot.clawOpen;
                }
            }
            else{
                if(gamepad2.b && robot.clawMotor.getCurrentPosition() >= clawMax){
                    if (robot.ClawPower != robot.clawStill) {
                        robot.clawMotor.setPower(robot.clawStill);
                        robot.ClawPower = robot.clawStill;
                    }
                }
                else{
                    if (robot.ClawPower != robot.clawStill) {
                        robot.clawMotor.setPower(robot.clawStill);
                        robot.ClawPower = robot.clawStill;
                    }
                }
            }

            if (!gamepad2.b && !gamepad2.x) {
                if (robot.ClawPower != robot.clawStill) {
                    robot.clawMotor.setPower(robot.clawStill);
                    robot.ClawPower = robot.clawStill;
                }
            }

        /* Vertical Arm Motor */
            if (gamepad2.dpad_up && robot.verticalArmMotor.getCurrentPosition() < verticalMax) {
                if (robot.VerticalArmPower != 1) {
                    robot.verticalArmMotor.setPower(1);
                    robot.VerticalArmPower = 1;
                }
            } else {
                if (gamepad2.dpad_up && robot.verticalArmMotor.getCurrentPosition() >= verticalMax) {
                    robot.verticalArmMotor.setPower(0);
                    robot.VerticalArmPower = 0;
                }
                else{
                    robot.verticalArmMotor.setPower(0);
                    robot.VerticalArmPower = 0;
                }
            }

            if (gamepad2.dpad_up && robot.verticalArmMotor.getCurrentPosition() < verticalMax) {
                if (robot.VerticalArmPower != -1) {
                    robot.verticalArmMotor.setPower(-1);
                    robot.VerticalArmPower = -1;
                }
            } else {
                if (gamepad2.dpad_up && robot.verticalArmMotor.getCurrentPosition() >= verticalMax) {
                    robot.verticalArmMotor.setPower(0);
                    robot.VerticalArmPower = 0;
                }
                else{
                    robot.verticalArmMotor.setPower(0);
                    robot.VerticalArmPower = 0;
                }
            }


    /* Range Trigger Linear Drive */
            if (gamepad1.right_trigger <= .5 && gamepad1.right_trigger != 0) {
                setWheelPower(-.5, -.5, .5, .5);
                trigger = true;
            } else {
                if (gamepad1.right_trigger <= .25 && gamepad1.right_trigger != 0) {
                    setWheelPower(-.25, -.25, .25, .25);
                    trigger = true;
                }
                if (gamepad1.right_trigger <= 1 && gamepad1.right_trigger != 0) {
                    setWheelPower(-1, -1, 1, 1);
                    trigger = true;
                }
            }


            if (gamepad1.left_trigger <= .5 && gamepad1.left_trigger != 0) {
                setWheelPower(.5, .5, -.5, -.5);
                trigger = true;
            } else {
                if (gamepad1.left_trigger <= .25 && gamepad1.left_trigger != 0) {
                    setWheelPower(.25, .25, -.25, -.25);
                    trigger = true;
                }
                if (gamepad1.left_trigger <= 1 && gamepad1.left_trigger != 0) {
                    setWheelPower(1, 1, -1, -1);
                    trigger = true;
                }
            }
//            if (gamepad1.left_trigger != 0) {
//                setWheelPower(1, 1, -1, -1);
//                trigger = true;
//            }
//
//            if (gamepad1.right_trigger != 0) {
//                setWheelPower(-1, -1, 1, 1);
//                trigger = true;
//            }

                if(gamepad1.right_trigger == 0 && gamepad1.left_trigger == 0){
                trigger = false;
            }

            if (!trigger) {
        /* Rotational Drive Control */
                if (gamepad1.left_bumper && gamepad1.right_stick_x < 0 || gamepad1.left_bumper && gamepad1.right_stick_x > 0) {

                    double GRX = gamepad1.right_stick_x / robot.bumperSlowest;

                    final double v1 = +GRX;
                    final double v2 = -GRX;
                    final double v3 = +GRX;
                    final double v4 = -GRX;

                    frontLeft = -v1;
                    frontRight = v2;
                    backLeft = -v3;
                    backRight = v4;

                    setWheelPower(frontLeft, frontRight, backLeft, backRight);
                } else {

                    if (gamepad1.right_bumper && gamepad1.right_stick_x < 0 || gamepad1.right_bumper && gamepad1.right_stick_x > 0) {

                        double GRX = gamepad1.right_stick_x / robot.bumperFastest;

                        final double v1 = +GRX;
                        final double v2 = -GRX;
                        final double v3 = +GRX;
                        final double v4 = -GRX;

                        frontLeft = -v1;
                        frontRight = v2;
                        backLeft = -v3;
                        backRight = v4;

                        setWheelPower(frontLeft, frontRight, backLeft, backRight);

                    } else {

                        double GRX = gamepad1.right_stick_x / robot.nobumper;

                        final double v1 = +GRX;
                        final double v2 = -GRX;
                        final double v3 = +GRX;
                        final double v4 = -GRX;

                        frontLeft = -v1;
                        frontRight = v2;
                        backLeft = -v3;
                        backRight = v4;

                        setWheelPower(frontLeft, frontRight, backLeft, backRight);
                    }

        /* Drive Control */
                    if (gamepad1.left_bumper) {
                        double GLY = -gamepad1.left_stick_y / robot.bumperSlowest;
                        double GRX = gamepad1.right_stick_x / robot.bumperSlowest;
                        double GLX = gamepad1.left_stick_x / robot.bumperSlowest;

                        final double v1 = GLY + GRX + GLX;
                        final double v2 = GLY - GRX - GLX;
                        final double v3 = GLY + GRX - GLX;
                        final double v4 = GLY - GRX + GLX;

                        frontLeft = -v1;
                        frontRight = v2;
                        backLeft = -v3;
                        backRight = v4;

                        setWheelPower(frontLeft, frontRight, backLeft, backRight);
                    } else {
                        if (gamepad1.right_bumper) {
                            double GLY = -gamepad1.left_stick_y / robot.bumperFastest;
                            double GRX = gamepad1.right_stick_x / robot.bumperFastest;
                            double GLX = gamepad1.left_stick_x / robot.bumperFastest;

                            final double v1 = GLY + GRX + GLX;
                            final double v2 = GLY - GRX - GLX;
                            final double v3 = GLY + GRX - GLX;
                            final double v4 = GLY - GRX + GLX;

                            frontLeft = -v1;
                            frontRight = v2;
                            backLeft = -v3;
                            backRight = v4;

                            setWheelPower(frontLeft, frontRight, backLeft, backRight);

                        } else {
                            double GLY = -gamepad1.left_stick_y / robot.nobumper;
                            double GRX = gamepad1.right_stick_x / robot.nobumper;
                            double GLX = gamepad1.left_stick_x / robot.nobumper;

                            final double v1 = GLY + GRX + GLX;
                            final double v2 = GLY - GRX - GLX;
                            final double v3 = GLY + GRX - GLX;
                            final double v4 = GLY - GRX + GLX;

                            frontLeft = -v1;
                            frontRight = v2;
                            backLeft = -v3;
                            backRight = v4;

                            setWheelPower(frontLeft, frontRight, backLeft, backRight);

                        }
                    }
                }
            }
        }
    }
    /***********************************************************************************************
     * These are all of the methods used in the Teleop*
     ***********************************************************************************************/

//    /* This method powers the claw */
//    public void setClawPower (String XorB){
//    boolean button;
//    double power;
//    double encoderMax, encoderMin;
//
//
//        switch(XorB){
//            case"X":
//            button = gamepad2.x; power = robot.clawClose; encoderMin = clawMin; encoderMax = clawMax;
//
//            case"B":
//            button = gamepad2.b; power = robot.clawOpen; encoderMin =
//        }
//
//        if (gamepad2.x && robot.clawMotor.getCurrentPosition() > clawMin) {
//            if (robot.ClawPower != robot.clawClose) {
//                robot.clawMotor.setPower(robot.clawClose);
//                robot.ClawPower = robot.clawClose;
//            }
//        }
//        else{
//            if(gamepad2.x && robot.clawMotor.getCurrentPosition() <= clawMin){
//                if (robot.ClawPower != robot.clawStill) {
//                    robot.clawMotor.setPower(robot.clawStill);
//                    robot.ClawPower = robot.clawStill;
//                }
//            }
//            else{
//                if (robot.ClawPower != robot.clawStill) {
//                    robot.clawMotor.setPower(robot.clawStill);
//                    robot.ClawPower = robot.clawStill;
//                }
//            }
//        }
//
//        if (gamepad2.b && robot.clawMotor.getCurrentPosition() < clawMax) {
//            if (robot.ClawPower != robot.clawOpen) {
//                robot.clawMotor.setPower(robot.clawOpen);
//                robot.ClawPower = robot.clawOpen;
//            }
//        }
//        else{
//            if(gamepad2.b && robot.clawMotor.getCurrentPosition() >= clawMax){
//                if (robot.ClawPower != robot.clawStill) {
//                    robot.clawMotor.setPower(robot.clawStill);
//                    robot.ClawPower = robot.clawStill;
//                }
//            }
//            else{
//                if (robot.ClawPower != robot.clawStill) {
//                    robot.clawMotor.setPower(robot.clawStill);
//                    robot.ClawPower = robot.clawStill;
//                }
//            }
//        }
//
//        if (!gamepad2.b && !gamepad2.x) {
//            if (robot.ClawPower != robot.clawStill) {
//                robot.clawMotor.setPower(robot.clawStill);
//                robot.ClawPower = robot.clawStill;
//            }
//        }
//    }


    /* This method powers each wheel to whichever power is desired */
    public void setWheelPower(double fl, double fr, double bl, double br) {

        /* Create power variables */
        double frontLeft;
        double frontRight;
        double backLeft;
        double backRight;

        /* Initialize the powers with the values input whenever this method is called */
        frontLeft = fl;
        frontRight = fr;
        backLeft = bl;
        backRight = br;

        /* set each wheel to the power indicated whenever this method is called */
        if (robot.FrontLeftPower != frontLeft) {
            robot.frontLeftMotor.setPower(fl);
            robot.FrontLeftPower = frontLeft;
        }
        if (robot.FrontRightPower != frontRight) {
            robot.frontRightMotor.setPower(fr);
            robot.FrontRightPower = frontRight;
        }
        if (robot.BackLeftPower != backLeft) {
            robot.backLeftMotor.setPower(bl);
            robot.BackLeftPower = backLeft;
        }
        if (robot.BackRightPower != backRight)
            robot.backRightMotor.setPower(br);
        robot.BackRightPower = backRight;
    }


    /* This method does all of the math that calculates the power to set on the wheels*/
    public void moveYAxis(double y) {

        double frontLeft;
        double frontRight;
        double backLeft;
        double backRight;
        double max;

        /*calculate the power for each wheel*/
        frontLeft = -y;
        frontRight = +y;
        backLeft = -y;
        backRight = +y;

        /*Set power on each wheel*/
        if (robot.FrontLeftPower != frontLeft) {
            robot.frontLeftMotor.setPower(frontLeft);
            robot.FrontLeftPower = frontLeft;
        }
        if (robot.FrontRightPower != frontRight) {
            robot.frontRightMotor.setPower(frontRight);
            robot.FrontRightPower = frontRight;
        }
        if (robot.BackLeftPower != backLeft) {
            robot.backLeftMotor.setPower(backLeft);
            robot.BackLeftPower = backLeft;
        }
        if (robot.BackRightPower != backRight) {
            robot.backRightMotor.setPower(backRight);
            robot.BackRightPower = backRight;
        }
    }

    public void moveXAxis(double x) {

        double frontLeft;
        double frontRight;
        double backLeft;
        double backRight;

                /*calculate the power for each wheel*/
        frontLeft = -x;
        frontRight = -x;
        backLeft = -x;
        backRight = -x;

                /*Set power on each wheel*/
        if (robot.FrontLeftPower != frontLeft) {
            robot.frontLeftMotor.setPower(frontLeft);
            robot.FrontLeftPower = frontLeft;
        }
        if (robot.FrontRightPower != frontRight) {
            robot.frontRightMotor.setPower(frontRight);
            robot.FrontRightPower = frontRight;
        }
        if (robot.BackLeftPower != backLeft) {
            robot.backLeftMotor.setPower(backLeft);
            robot.BackLeftPower = backLeft;
        }
        if (robot.BackRightPower != backRight) {
            robot.backRightMotor.setPower(backRight);
            robot.BackRightPower = backRight;
        }
    }
}

