package States;

import Specimen.Specimen;

import java.awt.*;

public class Vulnerable extends SpecimenState{
    public Vulnerable(Specimen specimen){
        super(specimen);
    }

    @Override
    public void setColor() {
        specimen.setColor(Color.GREEN);
    }
    @Override
    public void setFlag(){
        specimen.setActiveFlag(true);
    }
    @Override
    public SpecimenState deepCopy(Specimen specimen) {
        return new Vulnerable(specimen);
    }
}
