package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_UNPIN_ALICE_MAINTAINER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_UNPIN_ALICE_PERSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_UNPIN_ALICE_STAFF;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_UNPIN_ALICE_SUPPLIER;
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
 * Contains integration tests (interaction with the Model) and unit tests for {@code UnpinCommand}.
 */
public class UnpinCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validUnpinOther_unpinSuccess() throws CommandException {
        Person personToUnpin = new PersonBuilder(ALICE).withName(VALID_NAME_UNPIN_ALICE_PERSON)
                .withAddress(VALID_ADDRESS_BOB).build();
        personToUnpin.toPin();
        Person expectedPerson = new PersonBuilder(ALICE).withName(VALID_NAME_UNPIN_ALICE_PERSON)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedPerson.toPin();
        expectedPerson.toUnpin();

        // expected model result
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedPerson);
        expectedModel.updatePinnedPersonList();

        // execute method for model
        model.addPerson(personToUnpin);
        UnpinCommand unpinCommand = new UnpinCommand(expectedPerson.getName());

        unpinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validUnpinSupplier_unpinSuccess() throws CommandException {
        Supplier supplierToUnpin = new SupplierBuilder(ALICESUPPLIER).withName(VALID_NAME_UNPIN_ALICE_SUPPLIER)
                .withAddress(VALID_ADDRESS_BOB).build();
        supplierToUnpin.toPin();
        Supplier expectedSupplier = new SupplierBuilder(ALICESUPPLIER).withName(VALID_NAME_UNPIN_ALICE_SUPPLIER)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedSupplier.toPin();
        expectedSupplier.toUnpin();

        // expected model result
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedSupplier);
        expectedModel.updatePinnedPersonList();

        // execute method for model
        model.addPerson(supplierToUnpin);
        UnpinCommand unpinCommand = new UnpinCommand(expectedSupplier.getName());

        unpinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validUnpinMaintainer_unpinSuccess() throws CommandException {
        Maintainer maintainerToUnpin = new MaintainerBuilder(ALICEMAINTAINER)
                .withName(VALID_NAME_UNPIN_ALICE_MAINTAINER).withAddress(VALID_ADDRESS_BOB).build();
        maintainerToUnpin.toPin();
        Maintainer expectedMaintainer = new MaintainerBuilder(ALICEMAINTAINER)
                .withName(VALID_NAME_UNPIN_ALICE_MAINTAINER).withAddress(VALID_ADDRESS_BOB).build();
        expectedMaintainer.toPin();
        expectedMaintainer.toUnpin();

        // expected model result
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedMaintainer);
        expectedModel.updatePinnedPersonList();

        // execute method for model
        model.addPerson(maintainerToUnpin);
        UnpinCommand unpinCommand = new UnpinCommand(expectedMaintainer.getName());

        unpinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validUnpinStaff_unpinSuccess() throws CommandException {
        Staff staffToUnpin = new StaffBuilder(ALICESTAFF).withName(VALID_NAME_UNPIN_ALICE_STAFF)
                .withAddress(VALID_ADDRESS_BOB).build();
        staffToUnpin.toPin();
        Staff expectedStaff = new StaffBuilder(ALICESTAFF).withName(VALID_NAME_UNPIN_ALICE_STAFF)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedStaff.toPin();
        expectedStaff.toUnpin();

        // expected model result
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedStaff);
        expectedModel.updatePinnedPersonList();

        // execute method for model
        model.addPerson(staffToUnpin);
        UnpinCommand unpinCommand = new UnpinCommand(expectedStaff.getName());

        unpinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }
}
