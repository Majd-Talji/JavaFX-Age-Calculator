
import java.time.LocalDate;
import java.time.Period;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class ComboBoxStyle extends Group {

    Label birthDateLabel, resultLabel;
    CheckBox deathDateCheckBox;
    ComboBox dayOfBirth, monthOfBirth, yearOfBirth, dayOfDeath, monthOfDeath, yearOfDeath;
    Button calculateAgeButton;

    int d1, d2, m1, m2, y1, y2;

    LocalDate startDate, endDate;

    long daysCounter, monthsCounter, yearsCounter;

    public void toggleDeathDatePicker() {

        if (deathDateCheckBox.isSelected()) {
            dayOfDeath.setDisable(false);
            monthOfDeath.setDisable(false);
            yearOfDeath.setDisable(false);
        } else {
            dayOfDeath.setDisable(true);
            monthOfDeath.setDisable(true);
            yearOfDeath.setDisable(true);
        }

    }

    public void displayAge() {
        String text = "";

        y1 = Integer.parseInt(yearOfBirth.getSelectionModel().getSelectedItem().toString());
        m1 = monthOfBirth.getSelectionModel().getSelectedIndex() + 1;
        d1 = Integer.parseInt(dayOfBirth.getSelectionModel().getSelectedItem().toString());

        if (deathDateCheckBox.isSelected()) {
            y2 = Integer.parseInt(yearOfDeath.getSelectionModel().getSelectedItem().toString());
            m2 = monthOfDeath.getSelectionModel().getSelectedIndex() + 1;
            d2 = Integer.parseInt(dayOfDeath.getSelectionModel().getSelectedItem().toString());
        } else {
            y2 = LocalDate.now().getYear();
            m2 = LocalDate.now().getMonthValue();
            d2 = LocalDate.now().getDayOfMonth();
        }

        startDate = LocalDate.of(y1, m1, d1);
        endDate = LocalDate.of(y2, m2, d2);

        yearsCounter = Period.between(startDate, endDate).getYears();
        monthsCounter = Period.between(startDate, endDate).getMonths();
        daysCounter = Period.between(startDate, endDate).getDays();

        if (yearsCounter == 0 && monthsCounter == 0 && daysCounter == 0) {
            resultLabel.setTextFill(Color.RED);
            resultLabel.setText("Cannot compare same date");
        } else if (!Period.between(startDate, endDate).isNegative()) {

            if (yearsCounter == 1) {
                text += yearsCounter + " year ";
            } else if (yearsCounter > 1) {
                text += yearsCounter + " years ";
            }

            if (monthsCounter == 1) {
                if (yearsCounter > 0 && daysCounter > 0) {
                    text += ", " + monthsCounter + " month ";
                } else if (yearsCounter > 0 && daysCounter == 0) {
                    text += "and " + monthsCounter + " month ";
                } else {
                    text += monthsCounter + " month ";
                }
            }

            if (monthsCounter > 1) {
                if (yearsCounter > 0 && daysCounter > 0) {
                    text += ", " + monthsCounter + "months ";
                } else if (yearsCounter > 0 && daysCounter == 0) {
                    text += "and " + monthsCounter + "months ";
                } else {
                    text += monthsCounter + " months ";
                }
            }

            if (daysCounter == 1) {
                if (yearsCounter == 0 && monthsCounter == 0) {
                    text += daysCounter + " day";
                } else {
                    text += "and " + daysCounter + " day";
                }
            }

            if (daysCounter > 1) {
                if (yearsCounter == 0 && monthsCounter == 0) {
                    text += daysCounter + " days";
                } else {
                    text += "and " + daysCounter + " days";
                }
            }

            resultLabel.setTextFill(Color.BLACK);
            resultLabel.setText(text);
        } else {
            resultLabel.setTextFill(Color.RED);
            resultLabel.setText("Logic order of Dates is wrong");
        }

    }

    public ComboBoxStyle() {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May",
            "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        Integer[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};

        int currentYear = LocalDate.now().getYear();

        Integer[] years = new Integer[currentYear];

        for (int i = 0; i < currentYear; i++) {
            years[currentYear - i - 1] = i + 1;
        }

        birthDateLabel = new Label("Birth Date");
        deathDateCheckBox = new CheckBox("Death Date");
        resultLabel = new Label();
        calculateAgeButton = new Button("Calculate Age");
        dayOfBirth = new ComboBox(FXCollections.observableArrayList(days));
        monthOfBirth = new ComboBox(FXCollections.observableArrayList(months));
        yearOfBirth = new ComboBox(FXCollections.observableArrayList(years));
        dayOfDeath = new ComboBox(FXCollections.observableArrayList(days));
        monthOfDeath = new ComboBox(FXCollections.observableArrayList(months));
        yearOfDeath = new ComboBox(FXCollections.observableArrayList(years));

        birthDateLabel.setPrefSize(150, 30);
        deathDateCheckBox.setPrefSize(150, 30);
        dayOfBirth.setPrefSize(65, 30);
        monthOfBirth.setPrefSize(65, 30);
        yearOfBirth.setPrefSize(70, 30);
        dayOfDeath.setPrefSize(65, 30);
        monthOfDeath.setPrefSize(65, 30);
        yearOfDeath.setPrefSize(70, 30);
        calculateAgeButton.setPrefSize(215, 60);
        resultLabel.setPrefSize(430, 30);

        birthDateLabel.setTranslateX(174);
        birthDateLabel.setTranslateY(40);
        dayOfBirth.setTranslateX(110);
        dayOfBirth.setTranslateY(80);
        monthOfBirth.setTranslateX(182.5);
        monthOfBirth.setTranslateY(80);
        yearOfBirth.setTranslateX(255);
        yearOfBirth.setTranslateY(80);
        deathDateCheckBox.setTranslateX(150);
        deathDateCheckBox.setTranslateY(150);
        dayOfDeath.setTranslateX(110);
        dayOfDeath.setTranslateY(190);
        monthOfDeath.setTranslateX(182.5);
        monthOfDeath.setTranslateY(190);
        yearOfDeath.setTranslateX(255);
        yearOfDeath.setTranslateY(190);
        calculateAgeButton.setTranslateX(110);
        calculateAgeButton.setTranslateY(260);
        resultLabel.setTranslateX(0);
        resultLabel.setTranslateY(340);

        resultLabel.setAlignment(Pos.CENTER);

        dayOfBirth.getSelectionModel().selectFirst();
        monthOfBirth.getSelectionModel().selectFirst();
        yearOfBirth.getSelectionModel().select(0);

        dayOfDeath.getSelectionModel().selectFirst();
        monthOfDeath.getSelectionModel().selectFirst();
        yearOfDeath.getSelectionModel().select(0);

        birthDateLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
        deathDateCheckBox.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
        calculateAgeButton.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
        resultLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");

        this.getChildren().add(birthDateLabel);
        this.getChildren().add(deathDateCheckBox);
        this.getChildren().add(resultLabel);
        this.getChildren().add(calculateAgeButton);
        this.getChildren().add(dayOfBirth);
        this.getChildren().add(monthOfBirth);
        this.getChildren().add(yearOfBirth);
        this.getChildren().add(dayOfDeath);
        this.getChildren().add(monthOfDeath);
        this.getChildren().add(yearOfDeath);

        calculateAgeButton.setOnAction((ActionEvent event) -> {
            displayAge();
        });

        deathDateCheckBox.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    toggleDeathDatePicker();
                });

        toggleDeathDatePicker();
    }

}
