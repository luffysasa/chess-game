package assignment1;

public class SetMode extends ThreeMusketeers{
    private AgentStrategy agentStrategy;

    public SetMode(AgentStrategy agentStrategy) {
        this.agentStrategy = agentStrategy;
    }


    public Agent excutesetmode(){
        return this.agentStrategy.agentStragety();
    }
}
