package Simulation;


public class Caretaker {
    private Simulation.Memento lastState;
    private Simulation simulation;


    public Caretaker(Simulation simulation){
        this.simulation = simulation;
    }

    public void save(){
        lastState = simulation.snapshot();
    }

    public void load(){
        simulation.restore(lastState);
    }
}
