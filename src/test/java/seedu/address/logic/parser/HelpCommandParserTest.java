package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.messages.HelpMessages.FAILED_TO_HELP;
import static seedu.address.logic.messages.Messages.MESSAGE_COMMAND_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_MISSING_FIELD_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_UNKNOWN_FIELD_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HELP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.messages.HelpMessages;

//@@author jannaleong
public class HelpCommandParserTest {
    private final HelpCommandParser parser = new HelpCommandParser();
    private final String validCommand = "delete";
    private final String invalidCommand = "poodle";
    public final String validHelpDesc = " " + PREFIX_HELP + validCommand;
    public final String invalidCommandHelpDesc = " " + PREFIX_HELP + invalidCommand;

    @Test
    public void parse_validArgs_returnsHelpCommand() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + validHelpDesc,
                new HelpCommand(validCommand));
    }

    @Test
    public void parse_invalidCommand_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + invalidCommandHelpDesc,
                String.format(HelpMessages.MESSAGE_HELP_INVALID_PARAMETERS, HelpCommand.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_missingField_throwsParseException() {
        ArrayList<String> undetectedFields = new ArrayList<>();
        undetectedFields.add("command");

        String exception = FAILED_TO_HELP + String.format(MESSAGE_MISSING_FIELD_FORMAT, undetectedFields);
        String expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + "/help ;",
                expectedMessage);
    }

    @Test
    public void parse_unknownField_throwsParseException() {
        String commandWithUnknownField = "/help ; unknown :";
        ArrayList<String> unknownFields = new ArrayList<>();
        unknownFields.add("unknown");

        String exception = FAILED_TO_HELP + String.format(MESSAGE_UNKNOWN_FIELD_FORMAT, unknownFields);
        String expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + commandWithUnknownField,
                expectedMessage);
    }
}
