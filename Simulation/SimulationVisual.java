package Simulation;
import Specimen.Specimen;
import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SimulationVisual extends JPanel{
    private List<Specimen> specimenList;

    public SimulationVisual() {
        setBackground(Color.BLACK);
    }


    public void loop(JFrame frame, int deltaT){
        Simulation simulation = new Simulation();
        simulation.start(800, 800);
        specimenList = simulation.getSpecimenList();


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        Caretaker caretaker = new Caretaker(simulation);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caretaker.save();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caretaker.load();
            }
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);


        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.add(this, BorderLayout.CENTER);
        while(frame.isVisible())
        {
            try {
                specimenList = simulation.getSpecimenList();
                simulation.update();
                repaint();
                Thread.sleep(deltaT);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public static JFrame createFrame(){
        JFrame frame=new JFrame("Simulation");
        frame.setSize(800,800);
        frame.getContentPane().setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Specimen specimen : specimenList){
            double[] pos = new double[2];
            pos = specimen.getPosition().getComponents();
            g.setColor(specimen.getColor());
            g.fillOval((int)pos[0], (int)pos[1], 20, 20);
        }

    }
}
