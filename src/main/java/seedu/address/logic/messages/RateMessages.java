package seedu.address.logic.messages;

//@@author jamessinmaojun
/**
 * Container for {@code RateCommand} visible messages.
 */
public class RateMessages extends Messages {
    public static final String RATE = "rate";
    public static final String DEFAULT_RATING = "0";
    public static final String FAILED_TO_RATE = "Failed to rate Pooch Contact - ";
    public static final String MESSAGE_RATE_PERSON_SUCCESS = "Woof! Rated %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_RATE_NAME_NOT_FOUND = FAILED_TO_RATE
            + "Name does not exist in our address book.\uD83D\uDC3E";
    public static final String MESSAGE_RATE_MISSING_NAME = FAILED_TO_RATE
            + "Rate requires a name field. %1$s\uD83D\uDC3E";
    public static final String MESSAGE_RATE_MISSING_RATING = FAILED_TO_RATE
            + "Please enter a rating. %1$s\uD83D\uDC3E";
    public static final String MESSAGE_RATE_INVALID_PARAMETERS = FAILED_TO_RATE
            + "%1$s \uD83D\uDC3E";
}
