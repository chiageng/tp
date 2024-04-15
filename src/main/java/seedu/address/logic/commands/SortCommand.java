package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.SortMessages;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

//@@author Joshy837
/**
 * Format full help instructions for every command for display.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "/sort";
    public static final String MESSAGE_USAGE =
            "Sorts the address book based on specified parameters.\n"
            + "Example: /sort ; field : name";
    public static final String MESSAGE_CONSTRAINTS =
            "Only accepts name, phone, email, address, "
            + "salary, employment, price, product, "
            + "skill, commission, tag, note, pin "
            + "and rating as valid command type inputs.";
    private static final Logger logger = LogsCenter.getLogger(SortCommand.class);

    private final Prefix prefix;

    /**
     * Creates a SortCommand object.
     * @param prefix The contact field that the user wants to sort the address book by.
     */
    public SortCommand(Prefix prefix) {
        requireNonNull(prefix);
        this.prefix = prefix;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateSortedPersonList(prefix);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        if (prefix.getPrefix().equalsIgnoreCase("phone")) {
            logger.fine(String.format(SortMessages.MESSAGE_SORT_PERSON_SUCCESS, "phone number"));
            return new CommandResult(String.format(SortMessages.MESSAGE_SORT_PERSON_SUCCESS, "phone number"));
        } else {
            logger.fine(String.format(SortMessages.MESSAGE_SORT_PERSON_SUCCESS, prefix.getPrefix()));
            return new CommandResult(String.format(SortMessages.MESSAGE_SORT_PERSON_SUCCESS, prefix.getPrefix()));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherSearchCommand = (SortCommand) other;
        return prefix.equals(otherSearchCommand.prefix);
    }
}
