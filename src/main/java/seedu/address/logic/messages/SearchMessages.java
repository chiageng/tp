package seedu.address.logic.messages;

//@@author Joshy837
/**
 * Container for {@code SearchCommand} visible messages.
 */
public class SearchMessages extends Messages {
    public static final String SEARCH = "search";
    public static final String FAILED_TO_SEARCH = "Failed to search Pooch Contact - ";
    public static final String MESSAGE_SEARCH_PERSON_SUCCESS = "Woof! %1$s contact(s) found! \uD83D\uDC36";
    public static final String MESSAGE_SEARCH_MISSING_FIELD = FAILED_TO_SEARCH
            + "Search requires a name / phone / address / email / product / employment field. \uD83D\uDC3E";
    public static final String MESSAGE_SEARCH_INVALID_FIELD = FAILED_TO_SEARCH
            + "PoochPlanner doesn't recognise the field \uD83D\uDC3E";
}
