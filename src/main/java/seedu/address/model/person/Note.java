package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

//@@author jannaleong
/**
 * Represents a Person's Note in the address book.
 */
public class Note {
    public static final String MESSAGE_CONSTRAINTS = "Note can take any values, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private String value;

    /**
     * Constructs a {@code Note}.
     *
     * @param note A note value.
     */
    public Note(String note) {
        requireNonNull(note);
        checkArgument(isValidNote(note), MESSAGE_CONSTRAINTS);
        value = note;
    }


    /**
     * Returns true if a given string is a valid note.
     *
     * @param test Note to test.
     * @return Boolean indicating whether the string is a valid note.
     */
    public static boolean isValidNote(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string contains the deadline prefix.
     *
     * @param test String to test.
     * @return Boolean indicating whether the string contains the deadline prefix.
     */
    public static boolean isNoteContainingDeadline(String test) {
        String trimmedDeadlinePrefix = "; deadline :";
        return test.contains(trimmedDeadlinePrefix);
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return value.equals(otherNote.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
