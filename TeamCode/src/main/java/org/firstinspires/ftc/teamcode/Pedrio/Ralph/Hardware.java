package org.firstinspires.ftc.teamcode.Pedrio.Ralph;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Pedrio.Ralph.Subsystems.Deposit;
import org.firstinspires.ftc.teamcode.Pedrio.Ralph.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Pedrio.Ralph.Subsystems.Intake;

public class Hardware {
    private static Hardware instance = null;

    public MotorEx FlMotor;
    public MotorEx FrMotor;
    public MotorEx BlMotor;
    public MotorEx BrMotor;

    public MotorEx leftSlide;
    public MotorEx rightSlide;
    public MotorEx Intake;
    public MotorEx Transfer;
    public ServoEx leftIntakeServo;
    public ServoEx rightIntakeServo;
    public ServoEx depositServo;
    public HardwareMap hmap;
    public Drivetrain DriveTrain;
    public Deposit deposit;
    public org.firstinspires.ftc.teamcode.Pedrio.Ralph.Subsystems.Intake intake;
    public static Hardware getInstance(){
        if(instance == null){
            instance = new Hardware();
        }
       return instance;
    }

    public void Init(final HardwareMap hmap){
        this.hmap = hmap;
        this.FlMotor = new MotorEx(hmap,"FrontLeft");
        this.FrMotor = new MotorEx(hmap,"FrontRight");
        this.BlMotor = new MotorEx(hmap,"BackLeft");
        this.BrMotor = new MotorEx(hmap,"BackRight");

        this.leftSlide = new MotorEx(hmap,"Slide");
        this.rightSlide = new MotorEx(hmap,"Slide2");
        this.leftSlide.setInverted(true);
        this.rightSlide.setInverted(false);

        this.Intake = new MotorEx(hmap, "Intake");
        this.Transfer = new MotorEx(hmap, "Fingers");
        this.Transfer.setInverted(true);

        this.leftIntakeServo = new SimpleServo(hmap,"LeftServo",0,90, AngleUnit.DEGREES);
        this.rightIntakeServo = new SimpleServo(hmap,"RightServo", 0 ,-90,AngleUnit.DEGREES);


        this.depositServo = new SimpleServo(hmap,"BoxServo",0,90,AngleUnit.DEGREES);

        this.DriveTrain = new Drivetrain();
        this.deposit = new Deposit();
        this.intake = new Intake();
    }
    public void Loop(){

    }

}
