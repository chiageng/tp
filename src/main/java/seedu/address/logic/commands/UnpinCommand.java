package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.Messages;
import seedu.address.logic.messages.UnpinMessages;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

//@@author yleeyilin
/**
 * Unpins a contact in PoochPlanner.
 */
public class UnpinCommand extends Command {
    public static final String COMMAND_WORD = "/unpin";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unpins a contact from the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe other ";
    public static final String MESSAGE_NULL_NAME = "Specified name to unpin contact is null.";
    public static final String LOGGER_EXECUTE_UNPIN_MESSAGE = "Started executing the unpin command.";

    private final Name name;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Constructs an UnpinCommand object.
     * @param name Name of the person in PoochPlanner to unpin.
     */
    public UnpinCommand(Name name) {
        requireNonNull(name);
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert (name != null) : MESSAGE_NULL_NAME;
        logger.info(LOGGER_EXECUTE_UNPIN_MESSAGE);
        requireNonNull(model);

        Person personToUnpin = model.findByName(name, UnpinMessages.MESSAGE_UNPIN_NAME_NOT_FOUND);
        Person unpinnedPerson = personToUnpin.updateToUnpinned();

        model.setPerson(personToUnpin, unpinnedPerson);
        model.updatePinnedPersonList();

        return new CommandResult(String.format(UnpinMessages.MESSAGE_UNPIN_PERSON_SUCCESS,
                Messages.formatPerson(personToUnpin)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnpinCommand)) {
            return false;
        }

        UnpinCommand otherCommand = (UnpinCommand) other;
        return this.name.equals(otherCommand.name);
    }
}
