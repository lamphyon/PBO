public class Command {
    private String commandWord;
    private String secondWord;

    public Command(String first, String second) {
        this.commandWord = first;
        this.secondWord = second;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public boolean isUnknown() {
        return (commandWord == null);
    }

    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}