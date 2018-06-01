package sample;


import org.hl7.fhir.dstu3.model.Patient;

import java.util.Date;
public class myPatient {
    private String name;
    private String id;
    private Date birthdate;
    private String gender;

    public myPatient() {

    }

    public myPatient(Patient p) {
        //if (p.getName().get(0).getNameAsSingleString() != null) {
         //   this.name = WordUtils.capitalizeFully(p.getName().get(0).getNameAsSingleString()); // + "\t" + p.getName().get(0).getText();
        this.name = "noname";
       // }
        if (p.getId() != null) {
            this.id = p.getId().split("/")[p.getId().split("/").length - 1];
        }
        if (p.getBirthDate() != null) {
            this.birthdate = p.getBirthDate();
        }
        if (p.getGender() != null) {
            this.gender = p.getGender().toString();
        }
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public Date getBirthdate() { return birthdate; }
    public String getGender() { return gender; }

    /*
    public String makeName(Patient patient){
        String name = "";

        //for (int i = 0; i < patient.getName().size(); i++) {
        //    if (patient.getName().get(i).getFamily() !=null)
        //        name = name + " " + patient.getName().get(i).getFamily();
        //}

        for (int i = 0; i < patient.getName().size(); i++) {
            if (patient.getName().get(i).getGivenAsSingleString()!=null)
                name = name + " " + patient.getName().get(i).getFamilyAsSingleString();


        }
        return name;
    } */
}
