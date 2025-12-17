public class Game {
    private Parser parser;
    private Room currentRoom;

    public Game() {
        setupRooms();
        parser = new Parser();
    }

    private void setupRooms() {
        Room outside, theatre, pub, lab, office;
      
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);
        pub.setExit("east", outside);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        office.setExit("west", lab);

        currentRoom = outside;
    }

    public void play() {            
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Goodbye.");
    }

    private void printWelcome() {
        System.out.println("\nWelcome to Adventure!");
        System.out.println("Type 'help' if you need assistance.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String action = command.getCommandWord();
        if (action.equals("help")) {
            printHelp();
        } else if (action.equals("go")) {
            goRoom(command);
        } else if (action.equals("look")) {
            System.out.println(currentRoom.getLongDescription());
        } else if (action.equals("quit")) {
            return quit(command);
        }
        return false;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone.");
        System.out.println("Your command words are: go, quit, help, look");
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        return true;
    }
}