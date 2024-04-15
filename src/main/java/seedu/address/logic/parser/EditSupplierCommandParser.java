package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.messages.AddMessages.SUPPLIER_TYPE;
import static seedu.address.logic.messages.EditMessages.EDIT_SUPPLIER;
import static seedu.address.logic.messages.EditMessages.FAILED_TO_EDIT;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.EditSupplierCommand;
import seedu.address.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import seedu.address.logic.messages.EditMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

//@@author yleeyilin
/**
 * Parses input arguments and creates a new EditSupplierCommand object.
 */
public class EditSupplierCommandParser implements Parser<EditSupplierCommand> {
    public static final String MESSAGE_NULL_ARGUMENTS = "Arguments to pass into edit supplier command is null.";
    public static final String MESSAGE_COMMENCE_PARSING = "Going to start parsing for edit supplier command.";

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the EditSupplierCommand
     * and returns an EditSupplierCommand object for execution. Parameter {@code args} cannot be null.
     * @throws ParseException If the user input does not conform to the expected format
     */
    public EditSupplierCommand parse(String args) throws ParseException {
        requireNonNull(args);
        assert (args != null) : MESSAGE_NULL_ARGUMENTS;

        logger.log(Level.INFO, MESSAGE_COMMENCE_PARSING);

        String parsedArgs = ParserUtil.parseArg(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(parsedArgs, PREFIX_NAME, PREFIX_FIELD);

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(parsedArgs, EditSupplierCommand.MESSAGE_USAGE, EDIT_SUPPLIER,
                FAILED_TO_EDIT,
                PREFIX_NAME, PREFIX_FIELD, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_NAME, PREFIX_PRODUCT, PREFIX_PRICE);
        ParserUtil.verifyNoMissingField(argMultimap, EditSupplierCommand.MESSAGE_USAGE, EDIT_SUPPLIER,
                FAILED_TO_EDIT,
                PREFIX_NAME, PREFIX_FIELD);
        boolean isNamePrefixDuplicated = argMultimap.hasDuplicateNamePrefix();
        if (isNamePrefixDuplicated) {
            throw new ParseException(String.format(EditMessages.MESSAGE_MULTIPLE_NAME,
                    EditSupplierCommand.MESSAGE_USAGE));
        }

        // maps user commands to name and field
        Name name = ParserUtil.mapName(argMultimap, EditMessages.MESSAGE_EDIT_INVALID_NAME);
        String fieldArgs = ParserUtil.mapFields(argMultimap, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditSupplierCommand.MESSAGE_USAGE));

        // maps fields to edit to their values
        ArgumentMultimap fieldArgMultimap =
                ArgumentTokenizer.tokenize(fieldArgs, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_NAME, PREFIX_PRODUCT, PREFIX_PRICE);

        fieldArgMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        EditSupplierDescriptor editSupplierDescriptor = editSupplierDescription(fieldArgMultimap);

        boolean isNoFieldEdited = !editSupplierDescriptor.isAnyFieldEdited();
        if (isNoFieldEdited) {
            throw new ParseException(EditMessages.MESSAGE_EDIT_EMPTY_FIELD);
        }

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(SUPPLIER_TYPE));
        editSupplierDescriptor.setTags(tags);

        return new EditSupplierCommand(name, editSupplierDescriptor);
    }

    /**
     * Edits the description of a {@code Supplier}.
     *
     * @param fieldArgMultimap The mapping of field arguments into different specific fields.
     * @return EditSupplierDescriptor that contains the new values from the user.
     * @throws ParseException If the user enters invalid parameters.
     */
    private EditSupplierDescriptor editSupplierDescription(ArgumentMultimap fieldArgMultimap) throws ParseException {
        try {
            EditSupplierDescriptor editSupplierDescriptor = new EditSupplierDescriptor();

            if (fieldArgMultimap.getValue(PREFIX_PHONE).isPresent()) {
                editSupplierDescriptor.setPhone(ParserUtil.parsePhone(fieldArgMultimap.getValue(PREFIX_PHONE).get()));
            }
            if (fieldArgMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                editSupplierDescriptor.setEmail(ParserUtil.parseEmail(fieldArgMultimap.getValue(PREFIX_EMAIL).get()));
            }
            if (fieldArgMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
                editSupplierDescriptor.setAddress(ParserUtil.parseAddress(
                        fieldArgMultimap.getValue(PREFIX_ADDRESS).get()));
            }
            if (fieldArgMultimap.getValue(PREFIX_PRODUCT).isPresent()) {
                editSupplierDescriptor.setProduct(ParserUtil.parseProduct(
                        fieldArgMultimap.getValue(PREFIX_PRODUCT).get()));
            }
            if (fieldArgMultimap.getValue(PREFIX_PRICE).isPresent()) {
                editSupplierDescriptor.setPrice(ParserUtil.parsePrice(fieldArgMultimap.getValue(PREFIX_PRICE).get()));
            }

            return editSupplierDescriptor;
        } catch (ParseException pe) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD, pe.getMessage()));
        }
    }
}
