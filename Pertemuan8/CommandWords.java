public class CommandWords {
    private static final String[] validCommands = {
        "go", "quit", "help", "look"
    };

    public boolean isCommand(String aString) {
        for (String command : validCommands) {
            if (command.equals(aString)) {
                return true;
            }
        }
        return false;
    }
}