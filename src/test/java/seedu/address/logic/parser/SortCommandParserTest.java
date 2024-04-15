package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.messages.SortMessages;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author Joshy837
public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", SortMessages.MESSAGE_SORT_MISSING_FIELD);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ; field : nam", SortMessages.MESSAGE_SORT_INVALID_FIELD);
    }

    @Test
    public void parse_validArgs_returnsSortCommand() throws ParseException {
        String keyword = " ; field : name";
        ArgumentMultimap token = ArgumentTokenizer.tokenize(keyword, PREFIX_FIELD);

        // no leading and trailing whitespaces
        SortCommand expectedSortCommand =
                new SortCommand(ParserUtil.mapSortFields(token, SortMessages.MESSAGE_SORT_INVALID_FIELD));
        assertParseSuccess(parser, keyword, expectedSortCommand);
    }
}
