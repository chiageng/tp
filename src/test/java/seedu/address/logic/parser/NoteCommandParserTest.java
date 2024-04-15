package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.messages.Messages.MESSAGE_COMMAND_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_MISSING_FIELD_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_UNKNOWN_FIELD_FORMAT;
import static seedu.address.logic.messages.NoteMessages.FAILED_TO_ADD_NOTE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.NoteCommand;
import seedu.address.logic.messages.NoteMessages;
import seedu.address.model.person.DeadlineNote;
import seedu.address.model.person.Note;

//@@author jannaleong
public class NoteCommandParserTest {
    private final NoteCommandParser parser = new NoteCommandParser();
    private final Note validNote = new Note("Cancel shipment with bob");
    private final DeadlineNote validDeadlineNote = new DeadlineNote("Cancel shipment with bob", "2019-10-10");

    @Test
    public void parse_validArgs_returnsNoteCommand() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + NOTE_DESC_BOB,
                new NoteCommand(BOB.getName(), validNote));
    }

    @Test
    public void parse_validArgs_returnsDeadlineNoteCommand() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + NOTE_DESC_BOB
                + DEADLINE_DESC_BOB, new NoteCommand(BOB.getName(), validDeadlineNote));
    }

    @Test
    public void parse_invalidArgsDeadline_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + INVALID_DESC_BOB,
                String.format(NoteMessages.MESSAGE_NOTE_INVALID_PARAMETERS, Note.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_missingField_throwsParseException() {
        ArrayList<String> undetectedFields = new ArrayList<>();
        undetectedFields.add("note");

        String exception = FAILED_TO_ADD_NOTE + String.format(MESSAGE_MISSING_FIELD_FORMAT, undetectedFields);
        String expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + "/note ; name : alice",
                expectedMessage);
    }

    @Test
    public void parse_unknownField_throwsParseException() {
        String commandWithUnknownField = "/note ; unknown :";
        ArrayList<String> unknownFields = new ArrayList<>();
        unknownFields.add("unknown");

        String exception = FAILED_TO_ADD_NOTE + String.format(MESSAGE_UNKNOWN_FIELD_FORMAT, unknownFields);
        String expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + commandWithUnknownField,
                expectedMessage);
    }
}
