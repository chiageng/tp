package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PIN_ALICE_MAINTAINER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PIN_ALICE_PERSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PIN_ALICE_STAFF;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PIN_ALICE_SUPPLIER;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICEMAINTAINER;
import static seedu.address.testutil.TypicalPersons.ALICESTAFF;
import static seedu.address.testutil.TypicalPersons.ALICESUPPLIER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;
import seedu.address.testutil.MaintainerBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.SupplierBuilder;

//@@author yleeyilin
/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code PinCommand}.
 */
public class PinCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validPinOther_pinSuccess() throws CommandException {
        Person personToPin = new PersonBuilder(ALICE).withName(VALID_NAME_PIN_ALICE_PERSON)
                .withAddress(VALID_ADDRESS_BOB).build();
        Person expectedPerson = new PersonBuilder(ALICE).withName(VALID_NAME_PIN_ALICE_PERSON)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedPerson.toPin();

        // expected model result
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedPerson);
        expectedModel.updatePinnedPersonList();

        // execute method for model
        model.addPerson(personToPin);
        PinCommand pinCommand = new PinCommand(personToPin.getName());

        pinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validPinStaff_pinSuccess() throws CommandException {
        Staff staffToPin = new StaffBuilder(ALICESTAFF).withName(VALID_NAME_PIN_ALICE_STAFF)
                .withAddress(VALID_ADDRESS_BOB).build();
        Staff expectedStaff = new StaffBuilder(ALICESTAFF).withName(VALID_NAME_PIN_ALICE_STAFF)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedStaff.toPin();

        // expected model result
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedStaff);
        expectedModel.updatePinnedPersonList();

        // execute method for model
        model.addPerson(staffToPin);
        PinCommand pinCommand = new PinCommand(staffToPin.getName());

        pinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validPinMaintainer_pinSuccess() throws CommandException {
        Maintainer maintainerToPin = new MaintainerBuilder(ALICEMAINTAINER)
                .withName(VALID_NAME_PIN_ALICE_MAINTAINER).withAddress(VALID_ADDRESS_BOB).build();
        Maintainer expectedMaintainer = new MaintainerBuilder(ALICEMAINTAINER)
                .withName(VALID_NAME_PIN_ALICE_MAINTAINER).withAddress(VALID_ADDRESS_BOB).build();
        expectedMaintainer.toPin();

        // expected model result
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedMaintainer);
        expectedModel.updatePinnedPersonList();

        // execute method for model
        model.addPerson(maintainerToPin);
        PinCommand pinCommand = new PinCommand(maintainerToPin.getName());

        pinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validPinSupplier_pinSuccess() throws CommandException {
        Supplier supplierToPin = new SupplierBuilder(ALICESUPPLIER).withName(VALID_NAME_PIN_ALICE_SUPPLIER)
                .withAddress(VALID_ADDRESS_BOB).build();
        Supplier expectedSupplier = new SupplierBuilder(ALICESUPPLIER).withName(VALID_NAME_PIN_ALICE_SUPPLIER)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedSupplier.toPin();

        // expected model result
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedSupplier);
        expectedModel.updatePinnedPersonList();

        // execute method for model
        model.addPerson(supplierToPin);
        PinCommand pinCommand = new PinCommand(supplierToPin.getName());

        pinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }
}
