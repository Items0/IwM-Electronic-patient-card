package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.hl7.fhir.dstu3.model.Bundle;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {

    //GUI elements
    public ListView details;
    public Button search;
    public ListView patientList;
    public TextField patientName;
    public Button showDetails;
    public TextArea medicationShow;
    public ListView timeList;
    public DatePicker timeStart;
    public DatePicker timeEnd;

    public ArrayList<myPatient> patientArrayList;
    public FhirtestServer connection;
    public myPatient helpPatient;

    public void handleChoosePatient(MouseEvent arg0) {
        myPatient choosenPatient= (myPatient)patientList.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Details about selected Patient");
        alert.setHeaderText(choosenPatient.toString());
        alert.setContentText(choosenPatient.showDetails());

        alert.showAndWait();
        System.out.println("clicked on " + patientList.getSelectionModel().getSelectedItem());
    }
    public void handleShowDetails() {
       /* */
        List<Bundle.BundleEntryComponent> everythingFromChoosenPatient;
        //myPatient choosenPatient =(myPatient)patientList.getSelectionModel().getSelectedItem();
        if(timeStart.getValue()==null){
            everythingFromChoosenPatient= connection.getEveything("1212131");

        }else{
            everythingFromChoosenPatient= connection.getEveythingFromPeriod("1212131",
                    Date.from(timeStart.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()) ,Date.from(timeEnd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        //myPatient choosenPatient =(myPatient)patientList.getSelectionModel().getSelectedItem();


        ObservableList<Object> observationsList = FXCollections.observableArrayList ();
        ObservableList<String> timeObservableList = FXCollections.observableArrayList ();

        List<Object> observations= connection.getObservationAndMedicationStatement(everythingFromChoosenPatient);
        List<myMedication> myMedicationsList=connection.getMedication(everythingFromChoosenPatient);

        for (Object o : observations) {
            observationsList.add(o);
            if(o instanceof myMedicationStatement){
                myMedicationStatement ms = (myMedicationStatement)o;
                timeObservableList.add(ms.getStartDate().toString()+"\n ");

            }else {
                myObservation mo = (myObservation)o;
                timeObservableList.add(mo.getStartDate().toString()+"\n ");}

        }
        for (Object o: myMedicationsList){
            medicationShow.appendText(o.toString());

        }

        System.out.println("Observations size:"+observations.size());
        details.setItems(observationsList);
        timeList.setItems(timeObservableList);
    }
    public void handleSearch(){
        System.out.println("Button 'Szukaj' clicked");
        helpPatient = new myPatient();
        patientArrayList= new ArrayList<>();
        patientList.getItems().clear();
        List<Bundle.BundleEntryComponent> myBundle;
        ObservableList<myPatient> patients = FXCollections.observableArrayList();

        if(patientName.getText() == "") {
            myBundle = connection.getAllName();
            patientArrayList = connection.getPatientName(myBundle, helpPatient);
        } else {
            myBundle= connection.getOneName(patientName.getText());
            patientArrayList = connection.getPatientName(myBundle, helpPatient);
        }
        for (myPatient patient : patientArrayList) {
            patients.add(patient);
        }
        patientList.setItems(patients);
        System.out.println("Button 'Szukaj' clicked EXIT");
    }
    @FXML
    public void initialize() {
        connection=new FhirtestServer();
    }

}
