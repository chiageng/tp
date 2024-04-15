package seedu.address.logic.parser;

import static seedu.address.logic.messages.HelpMessages.FAILED_TO_HELP;
import static seedu.address.logic.messages.HelpMessages.HELP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HELP;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.messages.HelpMessages;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author jannaleong
/**
 * Parses input arguments and creates a new HelpCommand object.
 */
public class HelpCommandParser implements Parser<HelpCommand> {
    public static final String MESSAGE_NULL_ARGUMENTS = "Argument to pass into help command is null.";
    public static final String MESSAGE_COMMENCE_PARSING = "Going to start parsing for help command.";
    public static final String MESSAGE_PARSE_EXCEPTION = "Parsing error while parsing for help command.";

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand and
     * returns a HelpCommand object for execution. Parameter {@code args} cannot be null.
     *
     * @param args Argument to parse.
     * @return HelpCommand object with the parsed command type value.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public HelpCommand parse(String args) throws ParseException {
        assert (args != null) : MESSAGE_NULL_ARGUMENTS;
        logger.log(Level.INFO, MESSAGE_COMMENCE_PARSING);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_HELP);

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, HelpCommand.MESSAGE_USAGE, HELP,
                FAILED_TO_HELP, PREFIX_HELP);
        ParserUtil.verifyNoMissingField(argMultimap, HelpCommand.MESSAGE_USAGE, HELP,
                FAILED_TO_HELP, PREFIX_HELP);
        boolean isPreambleEmpty = argMultimap.isPreambleEmpty();
        if (!isPreambleEmpty) {
            logger.log(Level.WARNING, MESSAGE_PARSE_EXCEPTION);
            throw new ParseException(String.format(HelpMessages.MESSAGE_HELP_MISSING_COMMAND,
                    HelpCommand.MESSAGE_USAGE));
        }

        try {
            String commandType = ParserUtil.parseHelp(argMultimap.getValue(PREFIX_HELP).orElseThrow());
            return new HelpCommand(commandType);
        } catch (ParseException pe) {
            throw new ParseException(String.format(HelpMessages.MESSAGE_HELP_INVALID_PARAMETERS, pe.getMessage()));
        }
    }
}
