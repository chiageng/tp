package seedu.address.logic.parser;

import static seedu.address.logic.messages.Messages.MESSAGE_COMMAND_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_MISSING_FIELD_FORMAT;
import static seedu.address.logic.messages.PinMessages.FAILED_TO_PIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PinCommand;
import seedu.address.model.person.Name;

//@@author yleeyilin
/**
 * Contains unit tests for {@code PinCommandParser}.
 */
public class PinCommandParserTest {
    private final PinCommandParser parser = new PinCommandParser();

    @Test
    public void parse_missingNamePrefix_failure() {
        // no field specified
        ArrayList<String> undetectedFields = new ArrayList<>();
        undetectedFields.add("name");
        String exception = FAILED_TO_PIN + String.format(MESSAGE_MISSING_FIELD_FORMAT, undetectedFields);
        String expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, PinCommand.MESSAGE_USAGE);
        String userInput = PinCommand.COMMAND_WORD + " "
                + "Alice Pauline";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_validArgs_returnsPinCommand() {
        String userInput = PinCommand.COMMAND_WORD + " " + PREFIX_NAME
                + "Alice Pauline";
        assertParseSuccess(parser, userInput,
                new PinCommand(new Name("Alice Pauline")));
    }
}
