package org.example.mst_medical_app.model;

import javafx.beans.property.*;

public class Patient {
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final StringProperty gender = new SimpleStringProperty();
    private final StringProperty condition = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    public Patient(String name, int age, String gender, String condition, String status) {
        this.name.set(name);
        this.age.set(age);
        this.gender.set(gender);
        this.condition.set(condition);
        this.status.set(status);
    }

    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }

    public int getAge() { return age.get(); }
    public IntegerProperty ageProperty() { return age; }

    public String getGender() { return gender.get(); }
    public StringProperty genderProperty() { return gender; }

    public String getCondition() { return condition.get(); }
    public StringProperty conditionProperty() { return condition; }

    public String getStatus() { return status.get(); }
    public StringProperty statusProperty() { return status; }
}
