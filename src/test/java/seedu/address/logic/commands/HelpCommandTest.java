package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.isValidCommandType;

import org.junit.jupiter.api.Test;

import seedu.address.logic.messages.HelpMessages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

//@@author jannaleong
/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code HelpCommand}.
 */
public class HelpCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Test
    public void execute_generalHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("general"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_deleteHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_DELETE_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("delete"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_searchHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_SEARCH_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("search"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_editHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_EDIT_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("edit"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_addHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_ADD_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("add"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_exitHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_EXIT_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("exit"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_LIST_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("list"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_noteHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_NOTE_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("note"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_pinHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_PIN_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("pin"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_unpinHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_UNPIN_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("unpin"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_rateHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_RATE_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("rate"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_redoHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_REDO_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("redo"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_undoHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_UNDO_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("undo"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_remindHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_REMIND_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("remind"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_sortHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_SORT_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("sort"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_clearHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_CLEAR_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("clear"), model, expectedCommandResult, expectedModel);
    }


    @Test
    public void execute_invalidCommandType_throwsCommandException() {
        String invalidCommand = "poodles";
        HelpCommand helpCommand = new HelpCommand(invalidCommand);
        assertCommandFailure(helpCommand, model, HelpMessages.MESSAGES_INVALID_COMMAND_TYPE);
    }

    @Test
    public void isInvalidCommandTypeTest() {
        String invalidCommand = "hamster";
        boolean isValidCommand = isValidCommandType(invalidCommand);
        assertEquals(false, isValidCommand);
    }

    @Test
    public void isValidCommandTypeTest() {
        String validCommand = "add";
        boolean isValidCommand = isValidCommandType(validCommand);
        assertEquals(true, isValidCommand);
    }



    @Test
    public void equals() {
        HelpCommand helpFirstCommand = new HelpCommand("delete");
        HelpCommand helpSecondCommand = new HelpCommand("add");

        // same object -> returns true
        assertTrue(helpFirstCommand.equals(helpFirstCommand));

        // different command types -> returns false
        assertFalse(helpFirstCommand.equals(helpSecondCommand));
    }

    @Test
    public void toStringMethod() {
        String validCommandType = "add";
        HelpCommand helpCommand = new HelpCommand(validCommandType);
        String expected = HelpCommand.class.getCanonicalName() + "{command type=" + validCommandType + "}";
        assertEquals(expected, helpCommand.toString());
    }
}
