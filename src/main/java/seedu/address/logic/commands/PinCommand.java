package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.Messages;
import seedu.address.logic.messages.PinMessages;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Pins a contact on the address book.
 */
public class PinCommand extends Command {
    public static final String COMMAND_WORD = "/pin";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Pins a contact to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe other ";

    private final Name name;

    /**
     * @param name of the person in the person list to pin.
     */
    public PinCommand(Name name) {
        requireNonNull(name);
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToPin = model.findByName(name, PinMessages.MESSAGE_PIN_NAME_NOT_FOUND);
        Person pinnedPerson = personToPin.updateToPinned();

        model.setPerson(personToPin, pinnedPerson);
        model.updatePinnedPersonList();

        return new CommandResult(String.format(PinMessages.MESSAGE_PIN_PERSON_SUCCESS,
                Messages.formatPerson(personToPin)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PinCommand)) {
            return false;
        }

        PinCommand otherCommand = (PinCommand) other;
        return this.name.equals(otherCommand.name);
    }
}
