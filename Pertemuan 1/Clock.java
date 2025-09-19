import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Clock{
    private JFrame frame;
    private JLabel label;
    private JLabel dateLabel;
    private JLabel temperatureLabel;
    private ClockDisplay clock;
    private CalendarDisplay date;
    private TemperatureDisplay temperature;
    private boolean clockRunning = false;
    private TimerThread timerThread;
    
    public Clock(){
        clock = new ClockDisplay();
        date = new CalendarDisplay();
        temperature = new TemperatureDisplay();
        
        makeFrame();
    }
    
    public Clock(int hour, int minute, int second){
        clock = new ClockDisplay(hour, minute, second);
        date = new CalendarDisplay();
        temperature = new TemperatureDisplay();
        
        makeFrame();
    }

    
    private void start(){
        clockRunning = true;
        timerThread = new TimerThread();
        timerThread.start();
    }
    
    private void stop(){
        clockRunning = false;
    }
    
    private void step(){
        clock.timeTick();
        label.setText(clock.getTime());
    }
       
    private void quit(){
        System.exit(0);
    }
    
    private void makeFrame(){
        frame = new JFrame("Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(1, 60, 1, 60));
        makeMenuBar(frame);
        
        contentPane.setLayout(new BorderLayout(12, 12));
        
        label = new JLabel("00:00:00", SwingConstants.CENTER);
        Font displayFont = label.getFont().deriveFont(64.0f);
        label.setFont(displayFont);
        contentPane.add(label, BorderLayout.CENTER);
    
        dateLabel = new JLabel(date.getTodayDate(), SwingConstants.CENTER);
        dateLabel.setFont(dateLabel.getFont().deriveFont(18.0f));
        contentPane.add(dateLabel, BorderLayout.NORTH);
    
        temperatureLabel = new JLabel(temperature.getTemperature(), SwingConstants.CENTER);
        temperatureLabel.setFont(temperatureLabel.getFont().deriveFont(18.0f));
        contentPane.add(temperatureLabel, BorderLayout.EAST);
    
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(1, 0));
        
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> start());
        toolbar.add(startButton);
        
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> stop());
        toolbar.add(stopButton);
        
        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(e -> step());
        toolbar.add(stepButton);
        
        JPanel flow = new JPanel();
        flow.add(toolbar);
        contentPane.add(flow, BorderLayout.SOUTH);
        
        frame.pack();
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2-frame.getWidth()/2, d.height/2-frame.getHeight()/2);
        frame.setVisible(true);
    }


    private void makeMenuBar(JFrame frame){
        final int SHORTCUT_MASK = 
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar= new JMenuBar();

        frame.setJMenuBar(menubar);

        JMenu menu;
        JMenuItem item;
    
        menu= new JMenu("File");
        menubar.add(menu);
        
        item = new JMenuItem("About Clock...");
            item.addActionListener(e -> showAbout());
        menu.add(item);
        
        menu.addSeparator();
        
        item = new JMenuItem("Quit");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            item.addActionListener(e -> quit());
        menu.add(item);
    }
    
    class TimerThread extends Thread{
        public void run(){
            while(clockRunning){
                step();
                pause();
            }
        }
        
        private void pause(){
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException exc){
                
            }
        }
    } 
}
