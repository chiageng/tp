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
 * Container for general visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command. \nPlease refer to the following URL for "
            + "the User Guide to obtain further assistance and information.\n"
            + "https://ay2324s2-cs2103t-w10-2.github.io/tp/UserGuide.html";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_UNKNOWN_FIELD_FORMAT = "Unknown field detected : %1$s \uD83D\uDC3E";
    public static final String MESSAGE_MISSING_FIELD_FORMAT = "Missing field detected : %1$s \uD83D\uDC3E";
    public static final String MESSAGE_COMMAND_FORMAT = "Follow this command format! \n%1$s";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_EMPTY_FIELDS =
            "No values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    //@@author chiageng
    /**
     * Formats the {@code Person} including basic person fields for display to the user.
     * @param person The person to be formatted.
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
    //@@author

    //@@author chiageng
    /**
     * Formats the {@code Person} for display to the user.
     * @param person The person to be formatted.
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
    //@@author

    //@@author Joshy837
    /**
     * Returns an error message indicating the prefixes with no values.
     */
    public static String getErrorMessageForEmptyPrefixes(Prefix... emptyPrefixes) {
        assert emptyPrefixes.length > 0;

        Set<String> emptyFields =
                Stream.of(emptyPrefixes).map(Prefix::getTrimmedPrefix).collect(Collectors.toSet());

        return MESSAGE_EMPTY_FIELDS + "[" + String.join(", ", emptyFields) + "]";
    }
    //@@author
}
