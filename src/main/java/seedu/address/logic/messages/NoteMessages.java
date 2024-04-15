package seedu.address.logic.messages;

//@@author jannaleong
/**
 * Container for {@code NoteCommand} visible messages.
 */
public class NoteMessages extends Messages {
    public static final String NOTE = "note";
    public static final String DEFAULT_NOTE = "No note here";
    public static final String FAILED_TO_ADD_NOTE = "Failed to add note to Pooch Contact - ";
    public static final String MESSAGE_ADD_NOTE_SUCCESS =
            "Woof! Added note to %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_NOTE_NAME_NOT_FOUND = FAILED_TO_ADD_NOTE
            + "Name does not exist in our address book. \uD83D\uDC3E";
    public static final String MESSAGE_DEADLINE_NOT_SPECIFIED = FAILED_TO_ADD_NOTE
            + "Deadline is not specified. \uD83D\uDC3E";
    public static final String MESSAGE_NOTE_INVALID_PARAMETERS = FAILED_TO_ADD_NOTE
            + "%1$s \uD83D\uDC3E";
}
