package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.Messages;
import seedu.address.logic.messages.NoteMessages;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;

//@@author jannaleong
/**
 * Adds a note to an existing person in PoochPlanner.
 * A non-empty note and name must be specified.
 */
public class NoteCommand extends Command {
    public static final String COMMAND_WORD = "/note";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds note to a contact in PoochPlanner.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME"
            + PREFIX_NOTE + "NOTE"
            + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME
            + " Moochie" + " " + PREFIX_NOTE + "Meet at 6pm Tuesday";
    public static final String MESSAGE_NULL_NAME = "Specified name to add to contact is null.";
    public static final String MESSAGE_NULL_NOTE = "Specified note to add to contact is null.";
    public static final String LOGGER_EXECUTE_NOTE_MESSAGE = "Started executing the note command.";

    private final Name name;
    private final Note note;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Constructs a NoteCommand object.
     * @param name Name of the person in PoochPlanner to add note to.
     * @param note Note to add to the specified person in PoochPlanner.
     */
    public NoteCommand(Name name, Note note) {
        requireAllNonNull(name, note);
        this.name = name;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert (note != null) : MESSAGE_NULL_NOTE;
        assert (name != null) : MESSAGE_NULL_NAME;
        logger.info(LOGGER_EXECUTE_NOTE_MESSAGE);
        requireNonNull(model);

        Person personToEdit = model.findByName(name, NoteMessages.MESSAGE_NOTE_NAME_NOT_FOUND);
        Person editedPerson = personToEdit.updateNote(note);
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonListWithCommit(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(NoteMessages.MESSAGE_ADD_NOTE_SUCCESS,
                Messages.formatPerson(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteCommand)) {
            return false;
        }

        NoteCommand e = (NoteCommand) other;
        return name.equals(e.name)
                && note.equals(e.note);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("note", note)
                .toString();
    }
}
