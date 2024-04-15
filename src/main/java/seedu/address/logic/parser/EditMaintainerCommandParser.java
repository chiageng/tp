package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.messages.AddMessages.MAINTAINER_TYPE;
import static seedu.address.logic.messages.EditMessages.EDIT_MAINTAINER;
import static seedu.address.logic.messages.EditMessages.FAILED_TO_EDIT;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.EditMaintainerCommand;
import seedu.address.logic.commands.EditMaintainerCommand.EditMaintainerDescriptor;
import seedu.address.logic.messages.EditMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

//@@author yleeyilin
/**
 * Parses input arguments and creates a new EditMaintainerCommand object.
 */
public class EditMaintainerCommandParser implements Parser<EditMaintainerCommand> {
    public static final String MESSAGE_NULL_ARGUMENTS = "Arguments to pass into edit maintainer command is null.";
    public static final String MESSAGE_COMMENCE_PARSING = "Going to start parsing for edit maintainer command.";

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the EditMaintainerCommand.
     * and returns an EditMaintainerCommand object for execution. Parameter {@code args} cannot be null.
     * @throws ParseException If the user input does not conform to the expected format
     */
    public EditMaintainerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        assert (args != null) : MESSAGE_NULL_ARGUMENTS;

        logger.log(Level.INFO, MESSAGE_COMMENCE_PARSING);

        String parsedArgs = ParserUtil.parseArg(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(parsedArgs, PREFIX_NAME, PREFIX_FIELD);

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(parsedArgs, EditMaintainerCommand.MESSAGE_USAGE, EDIT_MAINTAINER,
                FAILED_TO_EDIT,
                PREFIX_NAME, PREFIX_FIELD, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_SKILL, PREFIX_COMMISSION);
        ParserUtil.verifyNoMissingField(argMultimap, EditMaintainerCommand.MESSAGE_USAGE, EDIT_MAINTAINER,
                FAILED_TO_EDIT,
                PREFIX_NAME, PREFIX_FIELD);
        boolean isNamePrefixDuplicated = argMultimap.hasDuplicateNamePrefix();
        if (isNamePrefixDuplicated) {
            throw new ParseException(String.format(EditMessages.MESSAGE_MULTIPLE_NAME,
                    EditMaintainerCommand.MESSAGE_USAGE));
        }

        // maps user commands to name and field
        Name name = ParserUtil.mapName(argMultimap, EditMessages.MESSAGE_EDIT_INVALID_NAME);
        String fieldArgs = ParserUtil.mapFields(argMultimap, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditMaintainerCommand.MESSAGE_USAGE));

        // maps fields to edit to their values
        ArgumentMultimap fieldArgMultimap =
                ArgumentTokenizer.tokenize(fieldArgs, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_SKILL, PREFIX_COMMISSION);

        fieldArgMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_SKILL, PREFIX_COMMISSION);

        EditMaintainerDescriptor editMaintainerDescriptor = editMaintainerDescription(fieldArgMultimap);

        boolean isNoFieldEdited = !editMaintainerDescriptor.isAnyFieldEdited();
        if (isNoFieldEdited) {
            throw new ParseException(EditMessages.MESSAGE_EDIT_EMPTY_FIELD);
        }

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(MAINTAINER_TYPE));
        editMaintainerDescriptor.setTags(tags);

        return new EditMaintainerCommand(name, editMaintainerDescriptor);
    }

    /**
     * Edits the description of a {@code Maintainer}.
     *
     * @param fieldArgMultimap The mapping of field arguments into different specific fields.
     * @return EditMaintainerDescriptor that contains the new values from the user.
     * @throws ParseException If the user enters invalid parameters.
     */
    private EditMaintainerDescriptor editMaintainerDescription(
                ArgumentMultimap fieldArgMultimap) throws ParseException {
        try {
            EditMaintainerDescriptor editMaintainerDescriptor = new EditMaintainerDescriptor();

            if (fieldArgMultimap.getValue(PREFIX_PHONE).isPresent()) {
                editMaintainerDescriptor.setPhone(ParserUtil.parsePhone(fieldArgMultimap.getValue(PREFIX_PHONE).get()));
            }
            if (fieldArgMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                editMaintainerDescriptor.setEmail(ParserUtil.parseEmail(fieldArgMultimap.getValue(PREFIX_EMAIL).get()));
            }
            if (fieldArgMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
                editMaintainerDescriptor.setAddress(ParserUtil.parseAddress(
                        fieldArgMultimap.getValue(PREFIX_ADDRESS).get()));
            }
            if (fieldArgMultimap.getValue(PREFIX_SKILL).isPresent()) {
                editMaintainerDescriptor.setSkill(ParserUtil.parseSkill(fieldArgMultimap.getValue(PREFIX_SKILL).get()));
            }
            if (fieldArgMultimap.getValue(PREFIX_COMMISSION).isPresent()) {
                editMaintainerDescriptor.setCommission(ParserUtil.parseCommission(
                        fieldArgMultimap.getValue(PREFIX_COMMISSION).get()));
            }

            return editMaintainerDescriptor;
        } catch (ParseException pe) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD, pe.getMessage()));
        }
    }
}
