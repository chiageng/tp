package seedu.address.logic.messages;

/**
 * Container for {@code PinCommand} visible messages.
 */
public class PinMessages extends Messages {
    public static final String PIN = "pin";
    public static final String FAILED_TO_PIN = "Failed to pin Pooch Contact - ";
    public static final String MESSAGE_PIN_PERSON_SUCCESS = "Woof! Pinned %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_PIN_INVALID_NAME = " %1$s \uD83D\uDC3E";
    public static final String MESSAGE_PIN_NAME_NOT_FOUND = FAILED_TO_PIN
            + "Name does not exist in our address book \uD83D\uDC3E";
}
