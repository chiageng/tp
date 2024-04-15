package seedu.address.logic.parser;

import static seedu.address.logic.messages.RateMessages.FAILED_TO_RATE;
import static seedu.address.logic.messages.RateMessages.RATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.RateCommand;
import seedu.address.logic.messages.RateMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Rating;

//@@author jamessinmaojun
/**
 * Parses input arguments and creates a new RateCommand object.
 */
public class RateCommandParser implements Parser<RateCommand> {
    public static final String MESSAGE_NULL_ARGUMENTS = "argument to pass for rate command is null";
    public static final String MESSAGE_COMMENCE_PARSING = "Going to start parsing for rate command.";

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the RateCommand.
     * and returns a RateCommand object for execution. Parameter {@code args} cannot be null.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public RateCommand parse(String args) throws ParseException {
        assert (args != null) : MESSAGE_NULL_ARGUMENTS;

        logger.log(Level.INFO, MESSAGE_COMMENCE_PARSING);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_RATING);

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, RateCommand.MESSAGE_USAGE, RATE,
                FAILED_TO_RATE,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_NOTE, PREFIX_RATING);
        ParserUtil.verifyNoMissingField(argMultimap, RateCommand.MESSAGE_USAGE, RATE,
                FAILED_TO_RATE,
                PREFIX_NAME, PREFIX_RATING);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        try {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElseThrow());
            Rating rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).orElseThrow());
            return new RateCommand(name, rating);
        } catch (ParseException pe) {
            throw new ParseException(String.format(RateMessages.MESSAGE_RATE_INVALID_PARAMETERS, pe.getMessage()));
        }
    }
}
