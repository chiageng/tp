package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.HelpMessages;
import seedu.address.model.Model;

//@@author jannaleong
/**
 * Returns help details for each command.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "/help";
    public static final String MESSAGE_USAGE = "Shows program usage instructions.\n"
            + "Example: /help ; command : exit";
    public static final String MESSAGE_CONSTRAINTS = "PoochPlanner only accepts general, add, delete, edit,"
            + " exit, search, list, note, pin, unpin, note, rate, redo, undo, remind, sort and clear as"
            + " valid command type inputs.";
    public static final String MESSAGE_NULL_COMMAND = "Specified command type to give help for is null.";
    public static final String LOGGER_EXECUTE_HELP_MESSAGE = "Started executing the help command.";

    private final String commandType;
    private final Logger logger = LogsCenter.getLogger(getClass());

    private enum CommandTypes {
        GENERAL,
        ADD,
        DELETE,
        EDIT,
        EXIT,
        SEARCH,
        LIST,
        NOTE,
        PIN,
        UNPIN,
        RATE,
        REDO,
        UNDO,
        REMIND,
        SORT,
        CLEAR
    }

    /**
     * Constructs a HelpCommand object.
     * @param commandType The command type to give help for.
     */
    public HelpCommand(String commandType) {
        requireAllNonNull(commandType);
        this.commandType = commandType;
    }

    /**
     * Checks if command provided is a valid command.
     * Parameter commandType cannot be null.
     *
     * @param commandType The command type to give help for.
     * @return Boolean showing whether the command type is valid.
     * */
    public static boolean isValidCommandType(String commandType) {
        assert (commandType != null) : MESSAGE_NULL_COMMAND;

        for (CommandTypes c : CommandTypes.values()) {
            String lowercaseName = c.name().toLowerCase();
            String lowercaseCommandType = commandType.toLowerCase();
            if (lowercaseName.equals(lowercaseCommandType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Executes the command and returns the help message.
     * Field commandType cannot be null.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert (commandType != null) : MESSAGE_NULL_COMMAND;
        logger.info(LOGGER_EXECUTE_HELP_MESSAGE);

        // return the correct help message based on command type
        String capitalizedCommandType = commandType.toUpperCase();
        if (capitalizedCommandType.equals(CommandTypes.GENERAL.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.ADD.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_ADD_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.DELETE.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_DELETE_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.EDIT.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_EDIT_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.SEARCH.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_SEARCH_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.EXIT.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_EXIT_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.LIST.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_LIST_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.NOTE.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_NOTE_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.PIN.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_PIN_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.UNPIN.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_UNPIN_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.RATE.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_RATE_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.REDO.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_REDO_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.UNDO.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_UNDO_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.REMIND.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_REMIND_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.SORT.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_SORT_HELP_MESSAGE, true, false);
        } else if (capitalizedCommandType.equals(CommandTypes.CLEAR.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_CLEAR_HELP_MESSAGE, true, false);
        } else {
            throw new CommandException(HelpMessages.MESSAGES_INVALID_COMMAND_TYPE);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HelpCommand)) {
            return false;
        }

        HelpCommand otherHelpCommand = (HelpCommand) other;
        return commandType.equals(otherHelpCommand.commandType);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("command type", commandType)
                .toString();
    }
}
