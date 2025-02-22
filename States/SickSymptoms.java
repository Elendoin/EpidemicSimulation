package States;
import Specimen.Specimen;
import java.awt.*;

public class SickSymptoms extends SpecimenState{
    public SickSymptoms(Specimen specimen){
        super(specimen);
    }

    @Override
    public void setColor(){
        specimen.setColor(Color.RED);
    }
    @Override
    public void setFlag(){
        specimen.setActiveFlag(false);
    }
    @Override
    public SpecimenState deepCopy(Specimen specimen) {
        return new SickSymptoms(specimen);
    }
}
