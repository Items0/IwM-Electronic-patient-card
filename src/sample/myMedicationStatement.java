package sample;

import org.hl7.fhir.dstu3.model.MedicationStatement;
import org.hl7.fhir.exceptions.FHIRException;

import java.util.Date;

public class myMedicationStatement {
    private String name;
    private String taken;
    private String dosage;
    private Date startDate;
    private Date endDate;


    public myMedicationStatement(MedicationStatement ms)  {
        try{
            this.name = ms.getMedicationCodeableConcept().getCodingFirstRep().getDisplay();
            this.taken =  ms.getTaken().getDisplay();
            this.dosage=  ms.getDosageFirstRep().getDoseSimpleQuantity().getValue().toString() + ms.getDosageFirstRep().getDoseSimpleQuantity().getUnit();
            this.startDate = ms.getEffectivePeriod().getStart();
            this.endDate =  ms.getEffectivePeriod().getEnd();

        } catch (FHIRException e) {
            e.printStackTrace();
        }catch (NullPointerException n){

        }
    }

    @Override
    public String toString() {
        return "Name:"+name+"\nTaken:"+taken+"\nDosage:"+dosage +"\n"+endDate;
    }

    public Date getStartDate() {
        return startDate;
    }
}
