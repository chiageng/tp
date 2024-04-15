package seedu.address.logic.messages;

/**
 * Container for {@code UnpinCommand} visible messages.
 */
public class UnpinMessages extends Messages {
    public static final String UNPIN = "unpin";
    public static final String FAILED_TO_UNPIN = "Failed to unpin Pooch Contact - ";
    public static final String MESSAGE_UNPIN_PERSON_SUCCESS = "Woof! Unpinned %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_UNPIN_INVALID_NAME = "Failed to unpin Pooch Contact - %1$s \uD83D\uDC3E";
    public static final String MESSAGE_UNPIN_NAME_NOT_FOUND = FAILED_TO_UNPIN
            + "Name does not exist in your address book \uD83D\uDC3E";
}
