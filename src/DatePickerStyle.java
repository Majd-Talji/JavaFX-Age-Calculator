
import java.time.LocalDate;
import java.time.Period;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class DatePickerStyle extends VBox {

    Label birthDateLabel, resultLabel;
    CheckBox deathDateCheckBox;
    DatePicker birthDatePicker, deathDatePicker;
    Button calculateAgeButton;

    int d1, d2, m1, m2, y1, y2;

    LocalDate startDate, endDate;

    long daysCounter, monthsCounter, yearsCounter;

    public void toggleDeathDatePicker() {

        if (deathDateCheckBox.isSelected()) {
            deathDatePicker.setDisable(false);
        } else {
            deathDatePicker.setDisable(true);
        }

    }

    public void displayAge() {
        String text = "";

        y1 = birthDatePicker.getValue().getYear();
        m1 = birthDatePicker.getValue().getMonthValue();
        d1 = birthDatePicker.getValue().getDayOfMonth();

        if (deathDateCheckBox.isSelected()) {
            y2 = deathDatePicker.getValue().getYear();
            m2 = deathDatePicker.getValue().getMonthValue();
            d2 = deathDatePicker.getValue().getDayOfMonth();
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

    public DatePickerStyle() {

        birthDateLabel = new Label("Birth Date");
        deathDateCheckBox = new CheckBox("Death Date");
        resultLabel = new Label();
        calculateAgeButton = new Button("Calculate Age");
        birthDatePicker = new DatePicker(LocalDate.of(2000, 1, 1));
        deathDatePicker = new DatePicker(LocalDate.now());

        birthDateLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
        deathDateCheckBox.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
        calculateAgeButton.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
        resultLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
        birthDatePicker.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
        deathDatePicker.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");

        birthDateLabel.setPrefSize(200, 30);
        deathDateCheckBox.setPrefSize(200, 30);
        birthDatePicker.setPrefSize(200, 30);
        deathDatePicker.setPrefSize(200, 30);
        calculateAgeButton.setPrefSize(200, 30);

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20));

        this.getChildren().add(birthDateLabel);
        this.getChildren().add(birthDatePicker);
        this.getChildren().add(deathDateCheckBox);
        this.getChildren().add(deathDatePicker);
        this.getChildren().add(calculateAgeButton);
        this.getChildren().add(resultLabel);

        VBox.setMargin(birthDatePicker, new Insets(10, 0, 35, 0));
        VBox.setMargin(deathDateCheckBox, new Insets(10, 0, 0, 0));
        VBox.setMargin(deathDatePicker, new Insets(10, 0, 35, 0));
        VBox.setMargin(calculateAgeButton, new Insets(10, 0, 25, 0));
        VBox.setMargin(resultLabel, new Insets(10, 0, 0, 0));

        calculateAgeButton.setOnAction((ActionEvent event) -> {
            displayAge();
        });

        deathDateCheckBox.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    toggleDeathDatePicker();
                });

        toggleDeathDatePicker();

        birthDatePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

                deathDatePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {

                    @Override
                    public DateCell call(DatePicker param) {

                        DateCell dateCell = new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item.isBefore(newValue)) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        };

                        return dateCell;
                    }

                });

            }
        });

    }

}
