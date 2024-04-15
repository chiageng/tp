package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_RATING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_BOB;
import static seedu.address.logic.messages.Messages.MESSAGE_COMMAND_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_MISSING_FIELD_FORMAT;
import static seedu.address.logic.messages.RateMessages.FAILED_TO_RATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RateCommand;
import seedu.address.logic.messages.RateMessages;
import seedu.address.model.person.Rating;

//@@author jamessinmaojun
public class RateCommandParserTest {
    private final RateCommandParser parser = new RateCommandParser();
    private final Rating validRating = new Rating("0");

    @Test
    public void parse_validArgs_returnsRateCommand() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + RATING_DESC_BOB,
                new RateCommand(BOB.getName(), validRating));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + INVALID_RATING_DESC,
                String.format(RateMessages.MESSAGE_RATE_INVALID_PARAMETERS, Rating.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_nameMissing_throwsParseException() {
        ArrayList<String> undetectedFields = new ArrayList<>();
        undetectedFields.add("name");
        String exception = String.format(MESSAGE_MISSING_FIELD_FORMAT, undetectedFields);
        String expectedMessage = FAILED_TO_RATE + exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, RateCommand.MESSAGE_USAGE);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + RATING_DESC_BOB,
                expectedMessage);
    }

    @Test
    public void parse_ratingMissing_throwsParseException() {
        // no rating specified
        ArrayList<String> undetectedFields = new ArrayList<>();
        undetectedFields.add("rating");
        String exception = String.format(MESSAGE_MISSING_FIELD_FORMAT, undetectedFields);
        String expectedMessage = FAILED_TO_RATE + exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, RateCommand.MESSAGE_USAGE);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB,
                expectedMessage);
    }
}
