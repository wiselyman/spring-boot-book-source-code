package top.wisely;

public class GreetingService {
    private String user;
    private String greetings;

    public GreetingService(String user, String greetings) {
        this.user = user;
        this.greetings = greetings;
    }

    public String greeting(){
        return "Hi " + this.user + "," + this.greetings;
    }
}
