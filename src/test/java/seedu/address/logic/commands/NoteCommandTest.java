package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.GEORGIAMAINTAINER;
import static seedu.address.testutil.TypicalPersons.GEORGIASTAFF;
import static seedu.address.testutil.TypicalPersons.GEORGIASUPPLIER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.NoteMessages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

//@@author jannaleong
/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code NoteCommand}.
 */
public class NoteCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Note validNote = new Note("get kibble today now");
    private final Note differentValidNote = new Note("get bones today");

    @Test
    public void execute_validNoteOther_addSuccess() throws CommandException {
        // set expected results
        Person toAddNotePerson = ALICE;
        Person expectedPerson = new Person(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(),
                validNote, toAddNotePerson.getTags(), toAddNotePerson.getRating());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);

        // execute note command
        NoteCommand noteCommand = new NoteCommand(toAddNotePerson.getName(), validNote);
        noteCommand.execute(model);

        assertEquals(model.getFilteredPersonList(), expectedModel.getFilteredPersonList());
        assertEquals(model.findPersonByName(toAddNotePerson.getName(),
                        NoteMessages.MESSAGE_INVALID_COMMAND_FORMAT).getNote(),
                expectedModel.findPersonByName(toAddNotePerson.getName(),
                        NoteMessages.MESSAGE_INVALID_COMMAND_FORMAT).getNote());
    }



    @Test
    public void execute_validNoteStaff_addSuccess() throws CommandException {
        Model modelStaff = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelStaff.addPerson(GEORGIASTAFF);

        // set expected results
        Staff toAddNotePerson = GEORGIASTAFF;
        Staff expectedPerson = new Staff(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(),
                validNote, toAddNotePerson.getTags(), toAddNotePerson.getSalary(), toAddNotePerson.getEmployment(),
                toAddNotePerson.getRating());
        Model expectedModel = new ModelManager(modelStaff.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(modelStaff.getFilteredPersonList().get(7), expectedPerson);

        // execute note command
        NoteCommand noteCommand = new NoteCommand(toAddNotePerson.getName(), validNote);
        noteCommand.execute(modelStaff);

        assertEquals(modelStaff.getFilteredPersonList(), expectedModel.getFilteredPersonList());
        assertEquals(modelStaff.getFilteredPersonList().get(7).getNote(),
                expectedModel.getFilteredPersonList().get(7).getNote());
    }

    @Test
    public void execute_validNoteSupplier_addSuccess() throws CommandException {
        Model modelSupplier = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelSupplier.addPerson(GEORGIASUPPLIER);

        // set expected results
        Supplier toAddNotePerson = GEORGIASUPPLIER;
        Supplier expectedPerson = new Supplier(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(),
                validNote, toAddNotePerson.getTags(), toAddNotePerson.getProduct(), toAddNotePerson.getPrice(),
                toAddNotePerson.getRating());
        Model expectedModel = new ModelManager(modelSupplier.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(modelSupplier.getFilteredPersonList().get(7), expectedPerson);

        // execute note command
        NoteCommand noteCommand = new NoteCommand(toAddNotePerson.getName(), validNote);
        noteCommand.execute(modelSupplier);

        assertEquals(modelSupplier.getFilteredPersonList(), expectedModel.getFilteredPersonList());
        assertEquals(modelSupplier.getFilteredPersonList().get(7).getNote(),
                expectedModel.getFilteredPersonList().get(7).getNote());
    }

    @Test
    public void execute_validNoteMaintainer_addSuccess() throws CommandException {
        Model modelMaintainer = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelMaintainer.addPerson(GEORGIAMAINTAINER);

        // set expected results
        Maintainer toAddNotePerson = GEORGIAMAINTAINER;
        Maintainer expectedPerson = new Maintainer(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(),
                validNote, toAddNotePerson.getTags(), toAddNotePerson.getSkill(), toAddNotePerson.getCommission(),
                toAddNotePerson.getRating());
        Model expectedModel = new ModelManager(modelMaintainer.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(modelMaintainer.getFilteredPersonList().get(7), expectedPerson);

        // execute note command
        NoteCommand noteCommand = new NoteCommand(toAddNotePerson.getName(), validNote);
        noteCommand.execute(modelMaintainer);

        assertEquals(modelMaintainer.getFilteredPersonList(), expectedModel.getFilteredPersonList());
        assertEquals(modelMaintainer.getFilteredPersonList().get(7).getNote(),
                expectedModel.getFilteredPersonList().get(7).getNote());
    }


    @Test
    public void execute_invalidName_throwsCommandException() {
        Name invalidName = new Name("patty");
        NoteCommand noteCommand = new NoteCommand(invalidName, validNote);
        assertCommandFailure(noteCommand, model, NoteMessages.MESSAGE_NOTE_NAME_NOT_FOUND);
    }

    @Test
    public void equals() {
        NoteCommand noteFirstCommand = new NoteCommand(ALICE.getName(), validNote);
        NoteCommand noteSecondCommand = new NoteCommand(BENSON.getName(), validNote);
        NoteCommand noteThirdCommand = new NoteCommand(ALICE.getName(), differentValidNote);

        // same object -> returns true
        assertEquals(noteFirstCommand, noteFirstCommand);

        // different names -> returns false
        assertNotEquals(noteFirstCommand, noteSecondCommand);

        // different notes -> returns false
        assertNotEquals(noteFirstCommand, noteThirdCommand);
    }

    @Test
    public void toStringMethod() {
        Name name = new Name("alice");
        Note note = new Note("alice likes dogs");
        NoteCommand noteCommand = new NoteCommand(name, note);
        String expected = NoteCommand.class.getCanonicalName() + "{name=" + name + ", " + "note=" + note + "}";
        assertEquals(expected, noteCommand.toString());
    }
}
