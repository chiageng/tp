package seedu.address.logic.messages;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command. \nPlease refer to the following URL for "
            + "the User Guide to obtain further assistance and information.\n"
            + "https://ay2324s2-cs2103t-w10-2.github.io/tp/UserGuide.html";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_FIELD_FORMAT = "Invalid field detected : %1$s \uD83D\uDC3E";
    public static final String MESSAGE_UNKNOWN_FIELD_FORMAT = "Unknown field detected : %1$s \uD83D\uDC3E";
    public static final String MESSAGE_MISSING_FIELD_FORMAT = "Missing field detected : %1$s \uD83D\uDC3E";
    public static final String FAILED_TO_ADD = "Failed to add Pooch Contact - \n";
    public static final String FAILED_TO_EDIT = "Failed to edit Pooch Contact - \n";
    public static final String FAILED_TO_EDIT_WITH_NAME = "Failed to edit Pooch Contact - \n"
            + "Editing Pooch Contact Name is not allowed.\n";
    public static final String FAILED_TO_ADD_NOTE = "Failed to add note to Pooch Contact - \n";
    public static final String FAILED_TO_SORT = "Failed to sort Pooch Contact - \n";
    public static final String FAILED_TO_PIN = "Failed to pin Pooch Contact - \n";
    public static final String FAILED_TO_UNPIN = "Failed to unpin Pooch Contact - \n";
    public static final String FAILED_TO_RATE = "Failed to rate Pooch Contact - \n";
    public static final String FAILED_TO_HELP = "Failed to display help window - \n";
    public static final String FAILED_TO_SEARCH = "Failed to search Pooch Contact - \n";
    public static final String FAILED_TO_DELETE = "Failed to delete Pooch Contact - \n";
    public static final String MESSAGE_COMMAND_FORMAT = "Follow this command format! \n%1$s";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_EMPTY_FIELDS =
            "No values specified for the following single-valued field(s): ";
    public static final String MESSAGE_EXTRA_FIELDS =
            "%1$s does not contain the following field(s): %2$s";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String formatPerson(Person person) {
        final StringBuilder builder = new StringBuilder();
        if (person instanceof Staff) {
            builder.append("Staff ");
        } else if (person instanceof Supplier) {
            builder.append("Supplier ");
        } else if (person instanceof Maintainer) {
            builder.append("Maintainer ");
        } else {
            builder.append("General Contact ");
        }
        builder.append(person.getName());
        return builder.toString();
    }

    /**
     * Returns an error message indicating the prefixes with no values.
     */
    public static String getErrorMessageForEmptyPrefixes(Prefix... emptyPrefixes) {
        assert emptyPrefixes.length > 0;

        Set<String> emptyFields =
                Stream.of(emptyPrefixes).map(Prefix::getTrimmedPrefix).collect(Collectors.toSet());

        return MESSAGE_EMPTY_FIELDS + "[" + String.join(", ", emptyFields) + "]";
    }
}
