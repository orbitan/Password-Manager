package passwordManager;

@Deprecated
public abstract class Mode {
    
    protected Safe safe;
    
    public Mode(){
        this.safe = new Safe();
    }
    
    public static Mode get(String[] mode){
        if(mode.length < 1) return new ConsoleMode();
        if(mode[0].contains("gui")) return new GuiMode();
        return new ConsoleMode();
    }
    public abstract void run();
}
