package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

//@@author jannaleong
public class RemindPredicateTest {

    @Test
    public void equals() {
        RemindPredicate firstPredicate = new RemindPredicate();
        RemindPredicate secondPredicate = new RemindPredicate();

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // different object, equal predicate -> returns true
        assertTrue(firstPredicate.equals(secondPredicate));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));
    }

    @Test
    public void test_detectsValidDeadlines() {
        RemindPredicate predicate = new RemindPredicate();

        // valid note after todays date -> true
        assertTrue(predicate.test(new PersonBuilder().withNote("get dogs by: Oct 10 2025").build()));

        // valid note before todays date -> false
        assertFalse(predicate.test(new PersonBuilder().withNote("get dogs by: Oct 10 2020").build()));

        //invalid note where date is in incorrect format -> false
        assertFalse(predicate.test(new PersonBuilder().withNote("get dogs by: 10-9-2025").build()));
    }
}
