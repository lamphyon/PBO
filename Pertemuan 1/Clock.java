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
    
    public Clock(){ // Konstruktor untuk mengambil informasi yang dibutuhkan dari kelas lain
        clock = new ClockDisplay();
        date = new CalendarDisplay();
        temperature = new TemperatureDisplay();
        
        makeFrame();
    }
    
    public Clock(int hour, int minute, int second){ // Sama saja, tapi yang ini bisa diinput di BlueJ
        clock = new ClockDisplay(hour, minute, second);
        date = new CalendarDisplay();
        temperature = new TemperatureDisplay();
        
        makeFrame();
    }

    
    private void start(){ // Apabila user memencet tombol start, akan mulai jalan clocknya
        clockRunning = true;
        timerThread = new TimerThread();
        timerThread.start();
    }
    
    private void stop(){ // Apabila user memencet tombol stop, akan mulai berhenti clocknya
        clockRunning = false;
    }
    
    private void step(){ // Apabila user memencet tombol step, akan menjalankan "timeTick" dan increment satu detik
        clock.timeTick();
        label.setText(clock.getTime());
    }
       
    private void quit(){ // Apabila user memencet tombol Quit di kiri atas, program berhenti
        System.exit(0);
    }
    
    private void makeFrame(){ // layout dari interface
        frame = new JFrame("Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;

        //contentPane yang berisi tanggal ,temperatur, dan waktu dari kelas lain
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
        
        // toolbar untuk tombol start, stop, dll
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

        // Panel flow untuk bordernya/wadahnya
        JPanel flow = new JPanel();
        flow.add(toolbar);
        contentPane.add(flow, BorderLayout.SOUTH);
        
        frame.pack();
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2-frame.getWidth()/2, d.height/2-frame.getHeight()/2);
        frame.setVisible(true);
    }


    private void makeMenuBar(JFrame frame){ // Ini untuk tombol "File" di kiri atas, akan memunculkan pilihan "About" dan "Quit"
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
    
    class TimerThread extends Thread{ // ini while(1) untuk memilih tombol toolbar
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
