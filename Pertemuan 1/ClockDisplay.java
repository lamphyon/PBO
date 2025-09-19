public class ClockDisplay{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private NumberDisplay seconds;
    private String displayString;
    
    public ClockDisplay(){ // Konstruktor untuk ngambil info di kelas NumberDisplay
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        updateDisplay();
    }
    
    public ClockDisplay(int hour, int minute, int second){ // Sama, tapi ada inputan di BlueJ
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        setTime(hour, minute, second);
    }
    
    public void timeTick(){ // Untuk meng-increment waktu, menggunakan fungsi "increment" di NumberDisplay
        seconds.increment();
        if(seconds.getValue() == 0){
            minutes.increment();
            if(minutes.getValue() == 0){
                hours.increment();
            }
        }
        updateDisplay();
    }

    
    public void setTime(int hour, int minute, int second){ // untuk mengeset waktunya
        hours.setValue(hour);
        minutes.setValue(minute);
        seconds.setValue(second);
        updateDisplay();
    }
   
    public String getTime(){ // mengembalikan waktu dalam format hh:mm:ss
        return displayString;
    }
    
    private void updateDisplay(){ // untuk meng-update waktu dan direturn dalam tipe data string
        displayString = hours.getDisplayValue() + ":" +
        minutes.getDisplayValue()  + ":" +
        seconds.getDisplayValue();
    }
}
