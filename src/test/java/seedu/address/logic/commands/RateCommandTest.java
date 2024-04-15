package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.GEORGIAMAINTAINER;
import static seedu.address.testutil.TypicalPersons.GEORGIASTAFF;
import static seedu.address.testutil.TypicalPersons.GEORGIASUPPLIER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.RateMessages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

//@@author jamessinmaojun
/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code RateCommand}.
 */
public class RateCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Rating validRating = new Rating("1");

    @Test
    public void execute_validRatingOther_addSuccess() throws CommandException {
        // set expected results
        Person toAddRatingPerson = ALICE;
        Person expectedPerson = new Person(toAddRatingPerson.getName(), toAddRatingPerson.getPhone(),
                toAddRatingPerson.getEmail(), toAddRatingPerson.getAddress(),
                toAddRatingPerson.getNote(), toAddRatingPerson.getTags(), validRating);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);

        // execute rate command
        RateCommand rateCommand = new RateCommand(toAddRatingPerson.getName(), validRating);
        rateCommand.execute(model);

        assertEquals(model.getFilteredPersonList(), expectedModel.getFilteredPersonList());
        assertEquals(model.findPersonByName(toAddRatingPerson.getName(),
                        RateMessages.MESSAGE_RATE_NAME_NOT_FOUND).getNote(),
                expectedModel.findPersonByName(toAddRatingPerson.getName(),
                        RateMessages.MESSAGE_RATE_NAME_NOT_FOUND).getNote());
    }

    @Test
    public void execute_validRateStaff_addSuccess() throws CommandException {
        Model modelStaff = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelStaff.addPerson(GEORGIASTAFF);

        // set expected results
        Staff toAddRatingStaff = GEORGIASTAFF;
        Staff expectedStaff = new Staff(toAddRatingStaff.getName(), toAddRatingStaff.getPhone(),
                toAddRatingStaff.getEmail(), toAddRatingStaff.getAddress(),
                toAddRatingStaff.getNote(), toAddRatingStaff.getTags(), toAddRatingStaff.getSalary(),
                toAddRatingStaff.getEmployment(),
                validRating);
        Model expectedModel = new ModelManager(modelStaff.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(modelStaff.getFilteredPersonList().get(7), expectedStaff);

        // execute rate command
        RateCommand rateCommand = new RateCommand(toAddRatingStaff.getName(), validRating);
        rateCommand.execute(modelStaff);

        assertEquals(modelStaff.getFilteredPersonList(), expectedModel.getFilteredPersonList());
        assertEquals(modelStaff.getFilteredPersonList().get(7).getNote(),
                expectedModel.getFilteredPersonList().get(7).getNote());
    }

    @Test
    public void execute_validRateSupplier_addSuccess() throws CommandException {
        Model modelSupplier = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelSupplier.addPerson(GEORGIASUPPLIER);

        // set expected results
        Supplier toAddRatingSupplier = GEORGIASUPPLIER;
        Supplier expectedPerson = new Supplier(toAddRatingSupplier.getName(), toAddRatingSupplier.getPhone(),
                toAddRatingSupplier.getEmail(), toAddRatingSupplier.getAddress(),
                toAddRatingSupplier.getNote(), toAddRatingSupplier.getTags(), toAddRatingSupplier.getProduct(),
                toAddRatingSupplier.getPrice(),
                validRating);
        Model expectedModel = new ModelManager(modelSupplier.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(modelSupplier.getFilteredPersonList().get(7), expectedPerson);

        // execute rate command
        RateCommand rateCommand = new RateCommand(toAddRatingSupplier.getName(), validRating);
        rateCommand.execute(modelSupplier);

        assertEquals(modelSupplier.getFilteredPersonList(), expectedModel.getFilteredPersonList());
        assertEquals(modelSupplier.getFilteredPersonList().get(7).getNote(),
                expectedModel.getFilteredPersonList().get(7).getNote());
    }

    @Test
    public void execute_validRateMaintainer_addSuccess() throws CommandException {
        Model modelMaintainer = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelMaintainer.addPerson(GEORGIAMAINTAINER);

        // set expected results
        Maintainer toAddRatingMaintainer = GEORGIAMAINTAINER;
        Maintainer expectedMaintainer = new Maintainer(toAddRatingMaintainer.getName(),
                toAddRatingMaintainer.getPhone(), toAddRatingMaintainer.getEmail(), toAddRatingMaintainer.getAddress(),
                toAddRatingMaintainer.getNote(), toAddRatingMaintainer.getTags(), toAddRatingMaintainer.getSkill(),
                toAddRatingMaintainer.getCommission(),
                validRating);
        Model expectedModel = new ModelManager(modelMaintainer.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(modelMaintainer.getFilteredPersonList().get(7), expectedMaintainer);

        // execute rate command
        RateCommand rateCommand = new RateCommand(toAddRatingMaintainer.getName(), validRating);
        rateCommand.execute(modelMaintainer);

        assertEquals(modelMaintainer.getFilteredPersonList(), expectedModel.getFilteredPersonList());
        assertEquals(modelMaintainer.getFilteredPersonList().get(7).getNote(),
                expectedModel.getFilteredPersonList().get(7).getNote());
    }

    @Test
    public void equals() {
        RateCommand rateFirstCommand = new RateCommand(ALICE.getName(), validRating);
        RateCommand rateSecondCommand = new RateCommand(BENSON.getName(), validRating);

        // same object -> returns true
        assertEquals(rateFirstCommand, rateFirstCommand);

        // different names -> returns false
        assertNotEquals(rateFirstCommand, rateSecondCommand);
    }
}
