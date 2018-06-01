package sample;


import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Patient;

import java.util.ArrayList;

public class FhirtestServer {
    public FhirContext ctx;
    public static String serverBase= "http://hapi.fhir.org/baseDstu3";
    public IGenericClient client;

    public FhirtestServer(){
        ctx = FhirContext.forDstu3();
        client = ctx.newRestfulGenericClient(serverBase);
    }
    public Bundle getAllName(){
        Bundle allPatient = this.getClient()
                .search()
                .forResource(Patient.class)
                .returnBundle(Bundle.class)
                .execute();
        return allPatient;
    }
    public Bundle getOneName(String Name){
        Bundle oneName = this.getClient().search()
                .forResource(Patient.class)
                .where(Patient.NAME.matches().value(Name))
                .returnBundle(Bundle.class)
                .execute();
        return oneName;
    }

    public ArrayList getPatientName(Bundle allPatient, myPatient helpPatient){
        ArrayList<myPatient> patientArrayList = new ArrayList<>();
        for (int i=0 ; i < allPatient.getEntry().size(); i++){
            Patient thePatient = (Patient) allPatient.getEntry().get(i).getResource();
            /*if (thePatient.getName().get(0).getFamily().isEmpty()) {
                System.out.print("blabla\t" + thePatient.getName().get(0).getFamily());
            }
            else
            {

            }*/
            patientArrayList.add(new myPatient(thePatient));
            //String checkName=helpPatient.makeName(thePatient);
            //if(checkName.length()>2) {
            //
           // }
        }
        return patientArrayList;
    }


    public FhirContext getCtx() {
        return ctx;
    }

    public IGenericClient getClient() {
        return client;
    }
}
