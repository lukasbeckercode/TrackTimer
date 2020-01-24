/*
This is where everything happens!
You enter a starting speed, then every 200m this speed is increased by .5 km/h and the Software beeps
The software also beeps every 20m.
This is in Analysis
TODO: Add Beeps
TODO: Add Error Message if the speedField is left empty
This is Beeping
The rounding process is a bit weired here:
eg: 1.12345 should be rounded to 2 decimal points:
Math.round(1.12345*100)=112
112/100=1.12
*/
package tracktimer;
//Imports handled by IntelliJ
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class myFrame extends JFrame {
    //Variables that represent UI Elements
    private JTextField speedField;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private JLabel startSpeedLabel;
    private JLabel currentSpeedLabel;
    private JLabel intervallLabel;
    private JLabel timeLabel;
    private JPanel rootPanel;

    //Public Variables
    Timer timer;
    double length = 0; //represents the time a runner has to run 20m in his dictated speed
    double speed = 0;//represents the current speed in the test
    double elapsedTimeCounter = 0; //timer in ms
    double elapsedTime = 0; //rounded Timer
    int meterCounter = 0; //counts, how often a runner has passed 20m

    myFrame(){
        add(rootPanel);

            startButton.addActionListener(e->{
                buttonHandler();
                try {
                    speed = Double.parseDouble(speedField.getText()); //get speed input
                    startSpeedLabel.setText(speedField.getText()); //set the label text
                    currentSpeedLabel.setText(Double.toString(speed));
                    timer = new java.util.Timer(); //create a new Timer(not swing Timer)
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            calcSpeed(speed);
                            elapsedTimeCounter +=1; //count in ms
                            elapsedTime = Math.round(elapsedTimeCounter*10); //round it to only a few decimal points
                            elapsedTime = elapsedTime/10000; //rounding
                            timeLabel.setText(Double.toString(elapsedTime));
                            if(elapsedTime ==length)
                            {
                                timeHandler();
                            }
                        }
                    },0,1); //Timer without delay and 1ms period
                }catch (Exception ex1)
                {
                    ex1.printStackTrace();
                    //TODO: ADD ERROR MESSAGE

                    //Keep This

                }


            });

            stopButton.addActionListener(e->{ //Stop the Timer
                timer.cancel();
                buttonHandler();
            });

            resetButton.addActionListener(e->{ //Reset all the values
                length= 0;
                meterCounter = 0;
                speed = 0;
                elapsedTime = 0;
                elapsedTimeCounter = 0;
                speedField.setText("");
                currentSpeedLabel.setText("0");
                startSpeedLabel.setText("0");
                intervallLabel.setText("0");
                timeLabel.setText("0");
            });
    }

    void calcSpeed(double speed)
    {
        //This method calculates, how many seconds elapse when someone runs 20m with "speed" speed
        length =Math.round(72/speed * 100); //rounding, 20m*3.6m/s = 72
        length = length/100; //rounding
        intervallLabel.setText(Double.toString(length));

    }
    void timeHandler()
    {
        if(meterCounter == 10) //reached 200m
        {
            elapsedTimeCounter =0; //Reset Variables
            meterCounter = 0;
            speed += 0.5; //increase Speed
            currentSpeedLabel.setText(Double.toString(speed));
            //TODO: ADD BEEP
        }else{
            //reached 20 more meters
            meterCounter++;
            elapsedTimeCounter =0; // reset Timer

            System.out.println(meterCounter); //DEBUGGING
            //TODO: ADD BEEP
        }
    }
    void buttonHandler() //To make the App less Bug-prone
    {
        startButton.setEnabled(!startButton.isEnabled());
        stopButton.setEnabled(!stopButton.isEnabled());
        resetButton.setEnabled(!resetButton.isEnabled());
        speedField.setEnabled(!speedField.isEnabled());
    }
}
