package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
        assertTrue(Note.isValidNote("happy puppies"));
        assertTrue(Note.isValidNote("-")); // one character
    }

    @Test
    public void isNoteContainingDeadline() {
        // no deadline prefix
        assertFalse(Note.isNoteContainingDeadline("no deadline field")); // empty string

        // contains deadline prefix
        assertTrue(Note.isNoteContainingDeadline("/note ; deadline :"));
    }

    @Test
    public void equals() {
        Note note = new Note("kind cats");

        // same values -> returns true
        assertTrue(note.equals(new Note("kind cats")));

        // same object -> returns true
        assertTrue(note.equals(note));

        // null -> returns false
        assertFalse(note.equals(null));

        // different types -> returns false
        assertFalse(note.equals(5.0f));

        // different values -> returns false
        assertFalse(note.equals(new Note("mean cats")));
    }
}
