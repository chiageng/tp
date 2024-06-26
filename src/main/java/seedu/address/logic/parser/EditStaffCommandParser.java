package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.messages.Messages.FAILED_TO_EDIT;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.EditStaffCommand;
import seedu.address.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.address.logic.messages.EditMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditStaffCommand object
 */
public class EditStaffCommandParser implements Parser<EditStaffCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the EditStaffCommand
     * and returns an EditStaffCommand object for execution. Parameter args cannot be null.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditStaffCommand parse(String args) throws ParseException {
        requireNonNull(args);
        assert (args != null) : "argument to pass for edit staff command is null";

        logger.log(Level.INFO, "Going to start parsing for edit staff command.");

        String parsedArgs = ParserUtil.parseArg(args);
        Name name;
        String fieldArgs;
        EditStaffDescriptor editStaffDescriptor;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(parsedArgs, PREFIX_NAME, PREFIX_FIELD);

        // Validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, EditStaffCommand.MESSAGE_USAGE, "edit-staff",
                FAILED_TO_EDIT, PREFIX_NAME,
                PREFIX_FIELD, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_EMPLOYMENT, PREFIX_SALARY);
        ParserUtil.verifyNoMissingField(argMultimap, EditStaffCommand.MESSAGE_USAGE, "edit-staff",
                FAILED_TO_EDIT,
                PREFIX_NAME, PREFIX_FIELD);
        boolean isNamePrefixDuplicated = argMultimap.hasDuplicateNamePrefix();
        if (isNamePrefixDuplicated) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDITING_NAME,
                    EditStaffCommand.MESSAGE_USAGE));
        }

        // Maps user commands to name, field
        name = ParserUtil.mapName(argMultimap, EditMessages.MESSAGE_EDIT_INVALID_NAME);
        fieldArgs = ParserUtil.mapFields(argMultimap, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditStaffCommand.MESSAGE_USAGE));

        // Maps fields to edit to their values
        ArgumentMultimap fieldArgMultimap =
                ArgumentTokenizer.tokenize(fieldArgs, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_EMPLOYMENT, PREFIX_SALARY);

        fieldArgMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_EMPLOYMENT, PREFIX_SALARY);

        editStaffDescriptor = editStaffDescription(fieldArgMultimap);

        boolean isNoFieldEdited = !editStaffDescriptor.isAnyFieldEdited();
        if (isNoFieldEdited) {
            throw new ParseException(EditMessages.MESSAGE_EDIT_EMPTY_FIELD);
        }

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("staff"));
        editStaffDescriptor.setTags(tags);

        return new EditStaffCommand(name, editStaffDescriptor);
    }

    /**
     * Edits the description of a Staff.
     *
     * @param fieldArgMultimap The mapping of field arguments into different specific fields.
     * @return EditStaffDescriptor that contains the new values from the user.
     * @throws ParseException Indicates the invalid format that users might have entered.
     */
    private EditStaffDescriptor editStaffDescription(ArgumentMultimap fieldArgMultimap) throws ParseException {
        try {
            EditStaffDescriptor editStaffDescriptor = new EditStaffDescriptor();

            if (fieldArgMultimap.getValue(PREFIX_PHONE).isPresent()) {
                editStaffDescriptor.setPhone(ParserUtil.parsePhone(fieldArgMultimap.getValue(PREFIX_PHONE).get()));
            }
            if (fieldArgMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                editStaffDescriptor.setEmail(ParserUtil.parseEmail(fieldArgMultimap.getValue(PREFIX_EMAIL).get()));
            }
            if (fieldArgMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
                editStaffDescriptor.setAddress(ParserUtil.parseAddress(
                    fieldArgMultimap.getValue(PREFIX_ADDRESS).get()));
            }
            if (fieldArgMultimap.getValue(PREFIX_SALARY).isPresent()) {
                editStaffDescriptor.setSalary(ParserUtil.parseSalary(fieldArgMultimap.getValue(PREFIX_SALARY).get()));
            }
            if (fieldArgMultimap.getValue(PREFIX_EMPLOYMENT).isPresent()) {
                editStaffDescriptor.setEmployment(ParserUtil.parseEmployment(
                        fieldArgMultimap.getValue(PREFIX_EMPLOYMENT).get()));
            }

            return editStaffDescriptor;
        } catch (ParseException pe) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD, pe.getMessage()));
        }
    }
}
