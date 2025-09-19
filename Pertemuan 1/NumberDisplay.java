public class NumberDisplay{
    private int limit; // atribut berisi batas angka
    private int value; // atribut untuk mendapatkan angka saat ini
    
    public NumberDisplay(int rollOverLimit){ // Konstruktor yang menyimpan limit dan value
        limit = rollOverLimit;
        value = 0;
    }
    
    public int getValue(){ // mereturn value
        return value;
    }
    
    public void setValue(int replacementValue){ // untuk mengganti value
        if((replacementValue >= 0) && (replacementValue < limit)){
            value = replacementValue;
        }
    }
    
    public String getDisplayValue(){ // untuk memberi "0" ke angka satuan
        if(value < 10){
            return "0" + value;
        }
        else {
            return "" + value;
        }
    }
    
    public void increment(){ // untuk meng-increment
        value = (value + 1) % limit;
    }
}
