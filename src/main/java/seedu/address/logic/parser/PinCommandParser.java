package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.messages.PinMessages.FAILED_TO_PIN;
import static seedu.address.logic.messages.PinMessages.PIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.PinCommand;
import seedu.address.logic.messages.PinMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

//@@author yleeyilin
/**
 * Parses input arguments and creates a new PinCommand object.
 */
public class PinCommandParser implements Parser<PinCommand> {
    public static final String MESSAGE_NULL_ARGUMENTS = "Arguments to pass into pin command is null.";
    public static final String MESSAGE_COMMENCE_PARSING = "Going to start parsing for pin command.";

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the PinCommand.
     * and returns a PinCommand object for execution. Parameter {@code args} cannot be null.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public PinCommand parse(String args) throws ParseException {
        requireNonNull(args);
        assert (args != null) : MESSAGE_NULL_ARGUMENTS;

        logger.log(Level.INFO, MESSAGE_COMMENCE_PARSING);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, PinCommand.MESSAGE_USAGE, PIN,
                FAILED_TO_PIN,
                PREFIX_NAME);
        ParserUtil.verifyNoMissingField(argMultimap, PinCommand.MESSAGE_USAGE, PIN,
                FAILED_TO_PIN,
                PREFIX_NAME);

        Name name = ParserUtil.mapName(argMultimap, PinMessages.MESSAGE_PIN_INVALID_NAME);

        return new PinCommand(name);
    }
}
