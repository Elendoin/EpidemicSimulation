package States;
import Specimen.Specimen;
import java.awt.*;

public class SickNoSymptoms extends SpecimenState{
    public SickNoSymptoms(Specimen specimen){
        super(specimen);
    }

    @Override
    public void setColor(){
        specimen.setColor(Color.ORANGE);
    }
    @Override
    public void setFlag(){
        specimen.setActiveFlag(false);
    }
    @Override
    public SpecimenState deepCopy(Specimen specimen) {
        return new SickNoSymptoms(specimen);
    }
}
