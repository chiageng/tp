package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.messages.AddMessages.OTHER_TYPE;
import static seedu.address.logic.messages.EditMessages.EDIT;
import static seedu.address.logic.messages.EditMessages.FAILED_TO_EDIT;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.messages.EditMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

//@@author yleeyilin
/**
 * Parses input arguments and creates a new EditCommand object.
 */
public class EditCommandParser implements Parser<EditCommand> {
    public static final String MESSAGE_NULL_ARGUMENTS = "Arguments to pass into edit command is null.";
    public static final String MESSAGE_COMMENCE_PARSING = "Going to start parsing for edit command.";

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand.
     * and returns an EditCommand object for execution. Parameter {@code args} cannot be null.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        assert (args != null) : MESSAGE_NULL_ARGUMENTS;

        logger.log(Level.INFO, MESSAGE_COMMENCE_PARSING);

        String parsedArgs = ParserUtil.parseArg(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(parsedArgs, PREFIX_NAME, PREFIX_FIELD);

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(parsedArgs, EditCommand.MESSAGE_USAGE, EDIT,
                FAILED_TO_EDIT,
                PREFIX_NAME, PREFIX_FIELD, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        ParserUtil.verifyNoMissingField(argMultimap, EditCommand.MESSAGE_USAGE, EDIT,
                FAILED_TO_EDIT,
                PREFIX_NAME, PREFIX_FIELD);
        boolean isNamePrefixDuplicated = argMultimap.hasDuplicateNamePrefix();
        if (isNamePrefixDuplicated) {
            throw new ParseException(String.format(EditMessages.MESSAGE_MULTIPLE_NAME,
                    EditCommand.MESSAGE_USAGE));
        }

        // maps user commands to name and field
        Name name = ParserUtil.mapName(argMultimap, EditMessages.MESSAGE_EDIT_INVALID_NAME);
        String fieldArgs = ParserUtil.mapFields(argMultimap, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE));

        // maps fields to edit to their values
        ArgumentMultimap fieldArgMultimap =
                ArgumentTokenizer.tokenize(fieldArgs, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        fieldArgMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        EditPersonDescriptor editPersonDescriptor = editPersonDescription(fieldArgMultimap);

        boolean isNoFieldEdited = !editPersonDescriptor.isAnyFieldEdited();
        if (isNoFieldEdited) {
            throw new ParseException(EditMessages.MESSAGE_EDIT_EMPTY_FIELD);
        }

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(OTHER_TYPE));
        editPersonDescriptor.setTags(tags);
        return new EditCommand(name, editPersonDescriptor);
    }

    /**
     * Edits the description of a {@code Person}.
     *
     * @param fieldArgMultimap The mapping of field arguments into different specific fields.
     * @return EditPersonDescriptor that contains the new values from the user.
     * @throws ParseException If the user enters invalid parameters.
     */
    private EditPersonDescriptor editPersonDescription(ArgumentMultimap fieldArgMultimap) throws ParseException {
        try {
            EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

            if (fieldArgMultimap.getValue(PREFIX_PHONE).isPresent()) {
                editPersonDescriptor.setPhone(ParserUtil.parsePhone(fieldArgMultimap.getValue(PREFIX_PHONE).get()));
            }
            if (fieldArgMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                editPersonDescriptor.setEmail(ParserUtil.parseEmail(fieldArgMultimap.getValue(PREFIX_EMAIL).get()));
            }
            if (fieldArgMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
                editPersonDescriptor.setAddress(ParserUtil.parseAddress(
                    fieldArgMultimap.getValue(PREFIX_ADDRESS).get()));
            }

            return editPersonDescriptor;
        } catch (ParseException pe) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD, pe.getMessage()));
        }
    }
}
