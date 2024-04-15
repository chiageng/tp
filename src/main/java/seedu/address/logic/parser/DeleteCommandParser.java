package seedu.address.logic.parser;

import static seedu.address.logic.messages.DeleteMessages.DELETE;
import static seedu.address.logic.messages.DeleteMessages.FAILED_TO_DELETE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.messages.DeleteMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

//@@author jamessinmaojun
/**
 * Parses input arguments and creates a new DeleteCommand object.
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {
    public static final String MESSAGE_NULL_ARGUMENTS = "argument to pass for delete command is null";
    public static final String MESSAGE_COMMENCE_PARSING = "Going to start parsing for delete command.";
    public static final String MESSAGE_PARSE_EXCEPTION = "Parsing error while parsing for delete command.";

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution. Parameter {@code args} cannot be null.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public DeleteCommand parse(String args) throws ParseException {
        assert (args != null) : MESSAGE_NULL_ARGUMENTS;

        logger.log(Level.INFO, MESSAGE_COMMENCE_PARSING);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, DeleteCommand.MESSAGE_USAGE, DELETE,
                FAILED_TO_DELETE, PREFIX_NAME);
        ParserUtil.verifyNoMissingField(argMultimap, DeleteCommand.MESSAGE_USAGE, DELETE,
                FAILED_TO_DELETE, PREFIX_NAME);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        boolean isPreambleEmpty = argMultimap.getPreamble().isEmpty();
        if (!isPreambleEmpty) {
            logger.log(Level.WARNING, MESSAGE_PARSE_EXCEPTION);
            throw new ParseException(String.format(DeleteMessages.MESSAGE_DELETE_MISSING_NAME,
                    DeleteCommand.MESSAGE_USAGE));
        }

        try {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElseThrow());
            return new DeleteCommand(name);
        } catch (ParseException pe) {
            throw new ParseException(String.format(DeleteMessages.MESSAGE_DELETE_INVALID_PARAMETERS, pe.getMessage()));
        }
    }
}
