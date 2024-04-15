package seedu.address.logic.messages;

//@@author jamessinmaojun
/**
 * Container for {@code DeleteCommand} visible messages.
 */
public class DeleteMessages extends Messages {
    public static final String DELETE = "delete";
    public static final String FAILED_TO_DELETE = "Failed to delete Pooch Contact - ";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Woof! Deleted %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_DELETE_NAME_NOT_FOUND = FAILED_TO_DELETE
            + "Name does not exist in your address book.\uD83D\uDC3E";
    public static final String MESSAGE_DELETE_MISSING_NAME = FAILED_TO_DELETE
            + "Delete requires a name field. %1$s\uD83D\uDC3E";
    public static final String MESSAGE_DELETE_INVALID_PARAMETERS = FAILED_TO_DELETE
            + "%1$s \uD83D\uDC3E";
}
