package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    private static final String OUTPUT_MESSAGE = "Output:\n";

    @FXML
    private TextArea resultDisplay;

    /**
     * Constructs a new ResultDisplay object.
     * This constructor initializes the ResultDisplay with the FXML file specified,
     * and sets the text of the resultDisplay to the provided OUTPUT_MESSAGE.
     */
    public ResultDisplay() {
        super(FXML);
        resultDisplay.setText(OUTPUT_MESSAGE);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(OUTPUT_MESSAGE + feedbackToUser);
    }

}
