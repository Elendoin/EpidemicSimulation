package Simulation;
import Movement.SpecimenMovement2D;
import Specimen.Specimen;
import java.util.*;
import Vectors.*;
import States.*;

public class Simulation {
    private Dictionary<Specimen, Vector2D> dictionary;
    private List<Specimen> specimenList;
    private final Random random = new Random();
    private final SpecimenMovement2D movement = new SpecimenMovement2D();
    private int frameX, frameY;
    private boolean pause = false;


    public void start(int frameX, int frameY) {
        Scanner scanner = new Scanner(System.in);
        specimenList = new ArrayList<Specimen>();
        dictionary = new Hashtable<Specimen, Vector2D>();
        this.frameX = frameX;
        this.frameY = frameY;
        System.out.println("Enter the starting amount of specimens: ");
        int startNumber = scanner.nextInt();

        if (startNumber <= 0) {
            return;
        }

        for (int i = 0; i < startNumber; i++) {
            Vector2D position = Vector2DOperations.randomEdgePoint(frameX, frameY);

            int roll = random.nextInt(100);
            if (roll < 20) {
                addNewSpecimen(position, "Immune");
            } else {
                addNewSpecimen(position, "Vulnerable");
            }

        }
    }

    public void update() {
        if (pause){
            return;
        }
        List<Specimen> removable = new ArrayList<Specimen>();
        int roll;
        for(Specimen specimen: specimenList){
            movement.setSpecimen(specimen);
            Vector2D direction = dictionary.get(specimen);

            //EDGE BLOCK
            Vector2D newPosition = (Vector2D)movement.move(direction);
            if(Vector2DOperations.checkForEdge(newPosition, frameX, frameY)){
                roll = random.nextInt(100);
                if(roll<50){
                    dictionary.put(specimen, Vector2DOperations.middleDirection((Vector2D)specimen.getPosition(), frameX, frameY));
                }
                else{
                    removable.add(specimen);
                    continue;
                }
            }

            //INFECTION BLOCK
            boolean found = false;
            if(specimen.getActiveFlag()){
                for(Specimen search : specimenList){
                    double distance = Vector2DOperations.distance((Vector2D)specimen.getPosition(), (Vector2D)search.getPosition());
                    if((search.getState() instanceof SickSymptoms || search.getState() instanceof SickNoSymptoms) &&
                            distance < 75){
                        specimen.setSickVicinity(specimen.getSickVicinity()+1);

                        if(specimen.getSickVicinity()>=75){
                            if(search.getState() instanceof SickNoSymptoms){
                                roll = random.nextInt(100);
                                if(roll>50){
                                    specimen.setState(new SickNoSymptoms(specimen));
                                    specimen.getState().setColor();
                                    specimen.getState().setFlag();
                                }
                            }
                            else if(search.getState() instanceof SickSymptoms){
                                specimen.setState(new SickSymptoms(specimen));
                                specimen.getState().setColor();
                                specimen.getState().setFlag();
                            }
                        }
                        found = true;
                        break;
                    }
                }
            }
            if(!found){
                specimen.setSickVicinity(0);
            }
            if(specimen.getState() instanceof SickSymptoms || specimen.getState() instanceof SickNoSymptoms){
                specimen.setSickTime(specimen.getSickTime() + 1);
                if(specimen.getSickTime() >= 500){
                    specimen.setState(new Immune(specimen));
                    specimen.getState().setColor();
                    specimen.getState().setFlag();
                }
            }



            //VECTOR BLOCK
            specimen.setPosition(newPosition);
            roll = random.nextInt(1000);
            //RANDOM DIRECTION
            if (roll<2){
                dictionary.put(specimen, Vector2DOperations.randomDirectionVector(2, -2));
            }
            //RANDOM SPEED
            else if(roll<10){
                dictionary.put(specimen, Vector2DOperations.randomSpeed(dictionary.get(specimen), 3, 0.5));
            }
        }

        //REMOVE SPECIMENS
        for(Specimen remove : removable){
            specimenList.remove(remove);
            dictionary.remove(remove);
        }

        //NEW SPECIMEN
        roll = random.nextInt(1000);
        if(roll<50){
            roll = random.nextInt(100);
            String stateString;
            if(roll<10){
                stateString = "Immune";
            }
            else if(roll<75){
                stateString = "Vulnerable";
            }
            else if(roll<90){
                stateString = "SickNoSymptoms";
            }
            else{
                stateString = "SickSymptoms";
            }
            Vector2D position = Vector2DOperations.randomEdgePoint(frameX, frameY);
            addNewSpecimen(position, stateString);
        }
    }

    public List<Specimen> getSpecimenList(){
        return this.specimenList;
    }

    private void addNewSpecimen(Vector2D position, String stateString){
        SpecimenState state;
        Specimen specimen = new Specimen(position);
        specimenList.add(specimen);
        state = switch (stateString) {
            case "Immune" -> new Immune(specimen);
            case "SickNoSymptoms" -> new SickNoSymptoms(specimen);
            case "SickSymptoms" -> new SickSymptoms(specimen);
            default -> new Vulnerable(specimen);
        };
        specimen.setState(state);
        state.setFlag();
        state.setColor();
        dictionary.put(specimen, Vector2DOperations.middleDirection(position, frameX, frameY));
    }

    public Memento snapshot(){
        pause = true;
        List<Specimen> newList = new ArrayList<Specimen>();
        Dictionary<Specimen, Vector2D> newDictionary = new Hashtable<Specimen, Vector2D>();

        int i = 0;
        for(Specimen specimen : specimenList){
            newList.add(specimen.deepCopy2D());
            double[] components = new double[2];
            components[0] = dictionary.get(specimen).getComponents()[0];
            components[1] = dictionary.get(specimen).getComponents()[1];
            newDictionary.put(newList.get(i), new Vector2D(components));
            i++;
        }

        pause = false;
        return new Memento(newList, newDictionary);
    }

    public void restore(Memento memento){
        pause = true;
        List<Specimen> newList = new ArrayList<Specimen>();
        Dictionary<Specimen, Vector2D> newDictionary = new Hashtable<Specimen, Vector2D>();

        int i = 0;
        for(Specimen specimen : memento.getSavedList()){
            newList.add(specimen.deepCopy2D());
            double[] components = new double[2];
            components[0] = memento.getSavedDictionary().get(specimen).getComponents()[0];
            components[1] = memento.getSavedDictionary().get(specimen).getComponents()[1];
            newDictionary.put(newList.get(i), new Vector2D(components));
            i++;
        }

        this.specimenList = newList;
        this.dictionary = newDictionary;
        pause = false;
    }

    public static class Memento{
        private Dictionary<Specimen, Vector2D> mementoDictionary = new Hashtable<Specimen, Vector2D>();
        private List<Specimen> mementoList = new ArrayList<Specimen>();

        private Memento(List<Specimen> list, Dictionary<Specimen, Vector2D> newDictionary){
            this.mementoList = list;
            this.mementoDictionary = newDictionary;
        }

        private List<Specimen> getSavedList(){
            return mementoList;
        }

        private Dictionary<Specimen, Vector2D> getSavedDictionary(){
            return mementoDictionary;
        }
    }
}
