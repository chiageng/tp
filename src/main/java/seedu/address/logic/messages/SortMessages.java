package seedu.address.logic.messages;

//@@author Joshy837
/**
 * Container for {@code SortCommand} visible messages.
 */
public class SortMessages extends Messages {
    public static final String SORT = "sort";
    public static final String FAILED_TO_SORT = "Failed to sort Pooch Contact - ";
    public static final String MESSAGE_SORT_PERSON_SUCCESS =
            "Woof! Sorted PoochPlanner by %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_SORT_INVALID_FIELD =
            FAILED_TO_SORT + "Please input a valid target field! \uD83D\uDC3E";
    public static final String MESSAGE_SORT_MISSING_FIELD =
            FAILED_TO_SORT + "Missing field detected : [field] \uD83D\uDC3E\n"
                    + "Follow this command format! \n"
                    + "Sorts the address book based on specified parameters.\n"
                    + "Example: /sort ; field : name";
}
