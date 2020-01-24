package tracktimer;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class myFrame extends JFrame {
    private JTextField speedField;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private JLabel startSpeedLabel;
    private JLabel currentSpeedLabel;
    private JLabel intervallLabel;
    private JLabel timeLabel;
    private JPanel rootPanel;

    Timer timer;
    double length = 0;
    double speed = 0;
    double elapsedTimeCounter = 0;
    double elapsedTime = 0;
    int meterCounter = 0;

    myFrame(){
        add(rootPanel);

            startButton.addActionListener(e->{
                buttonHandler();
                try {
                    speed = Double.parseDouble(speedField.getText()); //get speed input
                    startSpeedLabel.setText(speedField.getText()); //set the label text
                }catch (Exception ex1)
                {
                    ex1.printStackTrace();
                }

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
            });

            stopButton.addActionListener(e->{
                timer.cancel();
                buttonHandler();
            });
    }

    void calcSpeed(double speed)
    {

        length =Math.round(72/speed * 100); //rounding, 20m*3.6m/s = 72
        length = length/100; //rounding
        intervallLabel.setText(Double.toString(length));

    }
    void timeHandler()
    {
        if(meterCounter == 10) //reached 200m
        {
            elapsedTimeCounter =0;
            meterCounter = 0;
            speed += 0.5; //increase Speed
            currentSpeedLabel.setText(Double.toString(speed));
        }else{
            meterCounter++;
            elapsedTimeCounter =0;

            System.out.println(meterCounter); //DEBUGGING
            /*
            * TODO: FIND OUT WHAT IS SUPPOSED TO HAPPEN HERE*/
        }
    }
    void buttonHandler() //To make the App less Bug-prone
    {
        startButton.setEnabled(!startButton.isEnabled());
        stopButton.setEnabled(!stopButton.isEnabled());
        speedField.setEnabled(!speedField.isEnabled());
    }
}
