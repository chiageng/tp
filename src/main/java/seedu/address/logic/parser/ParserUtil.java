package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.messages.Messages.MESSAGE_COMMAND_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_MISSING_FIELD_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_UNKNOWN_FIELD_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_COLLECTION;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.messages.NoteMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Commission;
import seedu.address.model.person.DeadlineNote;
import seedu.address.model.person.Email;
import seedu.address.model.person.Employment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;
import seedu.address.model.person.Product;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Salary;
import seedu.address.model.person.Skill;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    private static final Logger logger = LogsCenter.getLogger("ParselUtil");

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException If the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();

        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String arg} into a {@code arg}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code name} is invalid.
     */
    public static String parseArg(String arg) throws ParseException {
        requireNonNull(arg);
        String parserArg = arg.replaceAll("field : \\{ ", "field : { ; ");
        return parserArg;
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    //@@author chiageng
    /**
     * Parses a {@code String employment} into an {@code Employment}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code employment} is invalid.
     */
    public static Employment parseEmployment(String employment) throws ParseException {
        requireNonNull(employment);
        String trimmedEmployment = employment.trim();
        if (!Employment.isValidEmployment(trimmedEmployment)) {
            throw new ParseException(Employment.MESSAGE_CONSTRAINTS);
        }
        return new Employment(trimmedEmployment);
    }
    //@@author

    //@@author chiageng
    /**
     * Parses a {@code String salary} into an {@code Salary}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code salary} is invalid.
     */
    public static Salary parseSalary(String salary) throws ParseException {
        requireNonNull(salary);
        String trimmedSalary = salary.trim();
        if (!Salary.isValidSalary(trimmedSalary)) {
            throw new ParseException(Salary.MESSAGE_CONSTRAINTS);
        }
        return new Salary(trimmedSalary);
    }
    //@@author

    //@@author chiageng
    /**
     * Parses a {@code String product} into an {@code Product}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code product} is invalid.
     */
    public static Product parseProduct(String product) throws ParseException {
        requireNonNull(product);
        String trimmedProduct = product.trim();
        if (!Product.isValidProduct(trimmedProduct)) {
            throw new ParseException(Product.MESSAGE_CONSTRAINTS);
        }
        return new Product(trimmedProduct);
    }
    //@@author

    //@@author chiageng
    /**
     * Parses a {@code String price} into an {@code Price}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException If the given {@code price} is invalid.
     */
    public static Price parsePrice(String price) throws ParseException {
        requireNonNull(price);
        String trimmedPrice = price.trim();
        if (!Price.isValidPrice(trimmedPrice)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }
        return new Price(trimmedPrice);
    }
    //@@author

    //@@author chiageng
    /**
     * Parses a {@code String skill} into an {@code Skill}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code skill} is invalid.
     */
    public static Skill parseSkill(String skill) throws ParseException {
        requireNonNull(skill);
        String trimmedSkill = skill.trim();
        if (!Skill.isValidSkill(trimmedSkill)) {
            throw new ParseException(Skill.MESSAGE_CONSTRAINTS);
        }
        return new Skill(trimmedSkill);
    }
    //@@author

    //@@author chiageng
    /**
     * Parses a {@code String commission} into an {@code Commission}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code commission} is invalid.
     */
    public static Commission parseCommission(String commission) throws ParseException {
        requireNonNull(commission);
        String trimmedCommission = commission.trim();
        if (!Commission.isValidCommission(trimmedCommission)) {
            throw new ParseException(Commission.MESSAGE_CONSTRAINTS);
        }
        return new Commission(trimmedCommission);
    }
    //@@author

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    //@@author yleeyilin
    /**
     * Parses a {@code String args} into an {@code String args}.
     * Leading and trailing whitespaces and curly brackets will be trimmed.
     */
    public static String parseField(String args) throws ParseException {
        requireNonNull(args);
        String trimmedFields = args.replaceAll("[{}]", "").trim();
        return trimmedFields;
    }
    //@@author

    //@@author jannaleong
    /**
     * Parses a {@code String note} into a {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code note} is invalid.
     */
    public static Note parseNote(String note) throws ParseException {
        requireNonNull(note);
        String trimmedNote = note.trim();

        if (Note.isNoteContainingDeadline(trimmedNote)) {
            throw new ParseException(NoteMessages.MESSAGE_DEADLINE_NOT_SPECIFIED);
        }
        if (!Note.isValidNote(trimmedNote)) {
            throw new ParseException(Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(trimmedNote);
    }
    //@@author

    //@@author jamessinmaojun
    /**
     * Parses a {@code String rating} into a {@code Rating}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code rating} is invalid.
     */
    public static Rating parseRating(String rating) throws ParseException {
        requireNonNull(rating);
        String trimmedRating = rating.trim();
        if (!Rating.isValidRating(trimmedRating)) {
            throw new ParseException(Rating.MESSAGE_CONSTRAINTS);
        }
        return new Rating(trimmedRating);
    }
    //@@author

    //@@author jannaleong
    /**
     * Parses a {@code String note, String deadline} into a {@code DeadlineNote}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code note, deadline} is invalid.
     */
    public static DeadlineNote parseDeadlineNote(String note, String deadline) throws ParseException {
        requireNonNull(note, deadline);
        String trimmedNote = note.trim();
        String trimmedDeadline = deadline.trim();

        if (!DeadlineNote.isValidNote(trimmedNote)) {
            throw new ParseException(Note.MESSAGE_CONSTRAINTS);
        }
        if (!DeadlineNote.isValidDate(deadline)) {
            throw new ParseException(DeadlineNote.MESSAGE_INVALID_DATE);
        }
        return new DeadlineNote(trimmedNote, trimmedDeadline);
    }
    //@@author

    //@@author jannaleong
    /**
     * Parses a {@code String commandType}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException If the given {@code commandType} is invalid.
     */
    public static String parseHelp(String commandType) throws ParseException {
        requireNonNull(commandType);
        String trimmedCommand = commandType.trim();

        if (!HelpCommand.isValidCommandType(commandType)) {
            throw new ParseException(HelpCommand.MESSAGE_CONSTRAINTS);
        }
        return trimmedCommand;
    }
    //@@author

    //@@author Joshy837
    /**
     * Parses a {@code String sort field}.
     * Leading "; " and trailing " : " will be trimmed
     * @throws ParseException If the given {@code commandType} is invalid.
     */
    public static String parseSortField(String sortField) throws ParseException {

        requireNonNull(sortField);

        // Remove "; " using replace()
        String removedSemicolon = sortField.replace("; ", "");

        // Remove " : " using replaceAll()
        String trimmedSortField = removedSemicolon.replaceAll(" : ", "");

        return trimmedSortField;
    }
    //@@author

    //@@author yleeyilin
    /**
     * Standardises all prefixes input by users to lower case.
     * @param argsString The input string to parse.
     * @return The formatted input string to fix.
     */
    public static String normalisePrefixes(String argsString) {
        String[] parts = argsString.split(";");
        StringBuilder result = new StringBuilder(parts[0]);

        for (int i = 1; i < parts.length; i++) {
            String part = parts[i];
            int colonIndex = part.indexOf(":");
            if (colonIndex != -1) {
                String prefix = part.substring(0, colonIndex).trim();
                String value = part.substring(colonIndex + 1).trim();
                result.append(" ; ").append(prefix.toLowerCase()).append(" : ").append(value);
            } else {
                result.append(" ; ").append(part.trim());
            }
        }
        return result.toString();
    }
    //@@author

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    //@@author yleeyilin
    /**
     * Returns name value using PREFIX.
     * @param argMultimap Object that contains mapping of prefix to value.
     * @param message Error message to throw if parse exception.
     * @return Returns object representing name.
     * @throws ParseException If command is in invalid format.
     */
    public static Name mapName(ArgumentMultimap argMultimap, String message) throws ParseException {
        try {
            return ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(message, pe.getMessage()));
        }
    }
    //@@author

    //@@author yleeyilin
    /**
     * Returns field values using PREFIX.
     * @param argMultimap Object that contains mapping of prefix to value.
     * @param message Error message to throw if parse exception.
     * @return Returns object representing the respective fields.
     * @throws ParseException If command is in invalid format.
     */
    public static String mapFields(ArgumentMultimap argMultimap, String message) throws ParseException {
        try {
            return " " + ParserUtil.parseField(argMultimap.getValue(PREFIX_FIELD).get());
        } catch (ParseException pe) {
            throw new ParseException(message, pe);
        }
    }
    //@@author

    //@@author Joshy837
    /**
     * Returns sort field values using PREFIX.
     * @param argMultimap Object that contains mapping of prefix to value.
     * @param message Error message to throw if parse exception.
     * @return Returns object representing the respective fields.
     * @throws ParseException Thrown when command is in invalid format.
     */
    public static Prefix mapSortFields(ArgumentMultimap argMultimap, String message) throws ParseException {
        try {
            String value = argMultimap.getValue(PREFIX_FIELD).get();

            for (Prefix prefix : PREFIX_SORT_COLLECTION) {
                String keyword = ParserUtil.parseSortField(prefix.getPrefix());
                if (keyword.equalsIgnoreCase(value)) {
                    return new Prefix(value);
                }
            }

            throw new ParseException(String.format(message));

        } catch (ParseException pe) {
            throw new ParseException(String.format(message, pe.getMessage()));
        }
    }
    //@@author

    //@@author chiageng
    /**
     * Verifies that there are no invalid prefixes.
     * @param args Arguments.
     * @param message Error messages to be displayed.
     * @param commandType Command Type.
     * @param headerMessage Header of error messages.
     * @param prefixes Required prefixes in the command.
     * @throws ParseException If there are invalid prefixes.
     */
    public static void verifyNoUnknownPrefix(String args, String message, String commandType,
                                             String headerMessage, Prefix... prefixes)
            throws ParseException {
        ArrayList<String> unknownPrefixes = ArgumentTokenizer.checkUnknownPrefix(args,
                prefixes);
        logger.log(Level.WARNING, "Parsing error while parsing for " + commandType + " command.");
        if (unknownPrefixes.size() > 0) {
            String exception = headerMessage + String.format(MESSAGE_UNKNOWN_FIELD_FORMAT, unknownPrefixes);
            exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, message);
            throw new ParseException(exception);
        }
    }
    //@@author

    //@@author chiageng
    /**
     * Verifies that there are no missing prefixes.
     * @param argMultimap Arguments.
     * @param message Error messages to be displayed.
     * @param commandType Command Type.
     * @param headerMessage Header of error messages.
     * @param prefixes Required prefixes in the command.
     * @throws ParseException If there are missing prefixes.
     */
    public static void verifyNoMissingField(ArgumentMultimap argMultimap, String message, String commandType,
                                            String headerMessage, Prefix... prefixes) throws
            ParseException {
        if (!arePrefixesPresent(argMultimap, prefixes)) {
            logger.log(Level.WARNING, "Parsing error while parsing for " + commandType + " command.");
            ArrayList<String> missingFields = ArgumentTokenizer.checkUndetectedPrefix(argMultimap,
                    prefixes);
            String exception = headerMessage + String.format(MESSAGE_MISSING_FIELD_FORMAT, missingFields);
            exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, message);
            throw new ParseException(exception);
        }
    }
    //@@author
}
