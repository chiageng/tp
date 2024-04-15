package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

//@@author jannaleong
public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }


    @Test
    public void constructor_invalidNote_throwsIllegalArgumentException() {
        String invalidNote = "";
        assertThrows(IllegalArgumentException.class, () -> new Note(invalidNote));
    }


    @Test
    public void isValidNote() {
        // null address
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null));

        // invalid addresses
        assertFalse(Note.isValidNote("")); // empty string
        assertFalse(Note.isValidNote(" ")); // spaces only

        // valid addresses
        assertTrue(Note.isValidNote("happy puppies")); //full phrase
        assertTrue(Note.isValidNote("-")); // one character
    }

    @Test
    public void isNoteContainingDeadline() {
        // no deadline prefix
        assertFalse(Note.isNoteContainingDeadline("no deadline field"));

        // contains deadline prefix
        assertTrue(Note.isNoteContainingDeadline("/note ; deadline :"));
    }

    @Test
    public void equals() {
        Note note1 = new Note("kind cats");
        Note note2 = new Note("kind cats");
        Note note3 = new Note("mean cats");

        // same values -> returns true
        assertTrue(note1.equals(note2));

        // same object -> returns true
        assertTrue(note1.equals(note1));

        // null -> returns false
        assertFalse(note1.equals(null));

        // different types -> returns false
        assertFalse(note1.equals(5));

        // different values -> returns false
        assertFalse(note1.equals(note3));
    }
}
