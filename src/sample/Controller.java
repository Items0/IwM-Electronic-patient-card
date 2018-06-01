package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.hl7.fhir.dstu3.model.Bundle;

import java.util.ArrayList;

public class Controller {

    //GUI elements
    public Button search;
    public ListView patientList;
    public TextField patientName;


    public ArrayList<myPatient> patientArrayList;
    public FhirtestServer connection;
    public myPatient helpPatient;

    public void handleSearch(){
        helpPatient = new myPatient();
        patientArrayList= new ArrayList<>();
        patientList.getItems().clear();
        Bundle myBundle;
        ObservableList<String> patients = FXCollections.observableArrayList ();

        if(patientName.getText().length()==0) {
        
            myBundle = connection.getAllName();

            patientArrayList = connection.getPatientName(myBundle, helpPatient);

        } else {
            myBundle= connection.getOneName(patientName.getText());
            patientArrayList = connection.getPatientName(myBundle, helpPatient);

        }
        for (myPatient patient : patientArrayList) {
            patients.add(patient.getId() + " " + patient.getName());
        }
        patientList.setItems(patients);
    }
    @FXML
    public void initialize() {
        connection=new FhirtestServer();
    }

}
