import Simulation.SimulationVisual;

class Main {
    public static void main(String[] args) {
        SimulationVisual panel = new SimulationVisual();
        panel.loop(SimulationVisual.createFrame(), 40);
    }
}
