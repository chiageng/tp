package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.Messages;
import seedu.address.logic.messages.UnpinMessages;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Pins a contact on the address book.
 */
public class UnpinCommand extends Command {
    public static final String COMMAND_WORD = "/unpin";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unpin a contact from the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe other ";

    private final Name name;

    /**
     * @param name of the person in the person list to unpin.
     */
    public UnpinCommand(Name name) {
        requireNonNull(name);
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
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
