package States;
import Specimen.Specimen;
import java.awt.*;

public class Immune extends SpecimenState {
    public Immune(Specimen specimen){
        super(specimen);
    }

    @Override
    public void setColor() {
        specimen.setColor(Color.BLUE);
    }
    @Override
    public void setFlag(){
        specimen.setActiveFlag(false);
    }
    @Override
    public SpecimenState deepCopy(Specimen specimen) {
        return new Immune(specimen);
    }
}
