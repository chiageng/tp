package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a Person's Note with Deadline in the address book.
 */
public class DeadlineNote extends Note {

    public static final String MESSAGE_INVALID_DATE = "Date must be in the format yyyy-mm-dd (e.g., 2019-10-15)";
    private String deadline;

    /**
     * Constructs an {@code DeadlineNote}.
     *
     * @param note A valid note.
     * @param note A valid deadline.
     */
    public DeadlineNote(String note, String deadline) {
        super(note);
        requireNonNull(note);
        checkArgument(isValidNote(note), MESSAGE_CONSTRAINTS);
        this.deadline = convertDate(deadline);
    }

    /**
     * Returns true if date is in valid format, else
     * returns false.
     *
     * @return Boolean indicating if it is in a
     *         correct format.
     */
    public static Boolean isValidDate(String test) {
        if (test.equals("")) {
            return false;
        }
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            // don't change the date in this case
            // as it is not a valid date, return false.
        }
        return false;
    }

    /**
     * Returns date converted into a more
     * readable format.
     */
    public String convertDate(String deadline) {
        assert (isValidDate(deadline)) : "deadline to convert is not valid";

        LocalDate originalDate = LocalDate.parse(deadline);
        return originalDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }


    @Override
    public String toString() {
        return super.toString() + " by: " + this.deadline;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeadlineNote)) {
            return false;
        }

        DeadlineNote otherNote = (DeadlineNote) other;
        boolean isDeadlineEqual = deadline.equals(otherNote.deadline);
        return super.equals(otherNote) && isDeadlineEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deadline);
    }
}
