package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICEMAINTAINER;
import static seedu.address.testutil.TypicalPersons.ALICESTAFF;
import static seedu.address.testutil.TypicalPersons.ALICESUPPLIER;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.BOBMAINTAINER;
import static seedu.address.testutil.TypicalPersons.BOBSTAFF;
import static seedu.address.testutil.TypicalPersons.BOBSUPPLIER;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.AddressBook;
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

//@@author Joshy837
/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code SortCommand}.
 */
public class SortCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validSortByName_sortSuccess() throws CommandException {

        // expected
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // actual
        model.deletePerson(ALICE);
        model.addPerson(ALICE);
        SortCommand sortCommand = new SortCommand(new Prefix("name"));
        sortCommand.execute(model);

        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validSortByPhone_sortSuccess() throws CommandException {

        // expected
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(ELLE);
        expectedModel.addPerson(FIONA);
        expectedModel.addPerson(GEORGE);
        expectedModel.addPerson(DANIEL);
        expectedModel.addPerson(ALICE);
        expectedModel.addPerson(CARL);
        expectedModel.addPerson(BENSON);

        // actual
        SortCommand sortCommand = new SortCommand(new Prefix("phone"));
        sortCommand.execute(model);

        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validSortByEmail_sortSuccess() throws CommandException {

        // expected
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(ALICE);
        expectedModel.addPerson(GEORGE);
        expectedModel.addPerson(DANIEL);
        expectedModel.addPerson(CARL);
        expectedModel.addPerson(BENSON);
        expectedModel.addPerson(FIONA);
        expectedModel.addPerson(ELLE);

        // actual
        SortCommand sortCommand = new SortCommand(new Prefix("email"));
        sortCommand.execute(model);

        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validSortByAddress_sortSuccess() throws CommandException {

        // expected
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(DANIEL);
        expectedModel.addPerson(ALICE);
        expectedModel.addPerson(BENSON);
        expectedModel.addPerson(GEORGE);
        expectedModel.addPerson(FIONA);
        expectedModel.addPerson(ELLE);
        expectedModel.addPerson(CARL);

        // actual
        SortCommand sortCommand = new SortCommand(new Prefix("address"));
        sortCommand.execute(model);

        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validSortByNote_sortSuccess() throws CommandException {

        // expected
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(ALICE);
        expectedModel.addPerson(BENSON);
        expectedModel.addPerson(CARL);
        expectedModel.addPerson(DANIEL);
        expectedModel.addPerson(ELLE);
        expectedModel.addPerson(FIONA);
        expectedModel.addPerson(GEORGE);

        // actual
        SortCommand sortCommand = new SortCommand(new Prefix("note"));
        sortCommand.execute(model);

        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validSortByRating_sortSuccess() throws CommandException {
        Person personWithSmallerRating = new PersonBuilder(ALICE).withRating("3").build();
        Person personWithLargerRating = new PersonBuilder(BOB).withRating("5").build();
        Person personWithNoRating = new PersonBuilder(CARL).withRating("").build();

        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs());
        actualModel.addPerson(personWithNoRating);
        actualModel.addPerson(personWithSmallerRating);
        actualModel.addPerson(personWithLargerRating);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(personWithLargerRating);
        expectedModel.addPerson(personWithSmallerRating);
        expectedModel.addPerson(personWithNoRating);

        SortCommand sortCommand = new SortCommand(new Prefix("rating"));
        sortCommand.execute(actualModel);

        assertEquals(expectedModel.getFilteredPersonList(), actualModel.getFilteredPersonList());
    }

    @Test
    public void execute_validSortByPin_sortSuccess() throws CommandException {
        Person personWithPin = new PersonBuilder(ALICE).build();
        personWithPin.toPin();
        Person personWithNoPin = BOB;

        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs());
        actualModel.addPerson(personWithNoPin);
        actualModel.addPerson(personWithPin);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(personWithPin);
        expectedModel.addPerson(personWithNoPin);

        SortCommand sortCommand = new SortCommand(new Prefix("pin"));
        sortCommand.execute(actualModel);

        assertEquals(expectedModel.getFilteredPersonList(), actualModel.getFilteredPersonList());
    }

    @Test
    public void execute_validSortBySalary_sortSuccess() throws CommandException {
        Staff staffWithSmallerSalary = new StaffBuilder(ALICESTAFF).withSalary("$50/hr").build();
        Staff staffWithLargerSalary = new StaffBuilder(BOBSTAFF).withSalary("$100/hr").build();
        Person nonStaff = CARL;

        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs());
        actualModel.addPerson(nonStaff);
        actualModel.addPerson(staffWithSmallerSalary);
        actualModel.addPerson(staffWithLargerSalary);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(staffWithSmallerSalary);
        expectedModel.addPerson(staffWithLargerSalary);
        expectedModel.addPerson(nonStaff);

        SortCommand sortCommand = new SortCommand(new Prefix("salary"));
        sortCommand.execute(actualModel);

        assertEquals(expectedModel.getFilteredPersonList(), actualModel.getFilteredPersonList());
    }

    @Test
    public void execute_validSortByEmployment_sortSuccess() throws CommandException {
        Staff staffWithLexicographicallyEarlierEmployment =
                new StaffBuilder(ALICESTAFF).withEmployment("full-time").build();
        Staff staffWithLexicographicallyLaterEmployment =
                new StaffBuilder(BOBSTAFF).withEmployment("part-time").build();
        Person nonStaff = CARL;

        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs());
        actualModel.addPerson(nonStaff);
        actualModel.addPerson(staffWithLexicographicallyLaterEmployment);
        actualModel.addPerson(staffWithLexicographicallyEarlierEmployment);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(staffWithLexicographicallyEarlierEmployment);
        expectedModel.addPerson(staffWithLexicographicallyLaterEmployment);
        expectedModel.addPerson(nonStaff);

        SortCommand sortCommand = new SortCommand(new Prefix("employment"));
        sortCommand.execute(actualModel);

        assertEquals(expectedModel.getFilteredPersonList(), actualModel.getFilteredPersonList());
    }

    @Test
    public void execute_validSortByProduct_sortSuccess() throws CommandException {
        Supplier supplierWithLexicographicallyEarlierProduct =
                new SupplierBuilder(ALICESUPPLIER).withProduct("dog food").build();
        Supplier supplierWithLexicographicallyLaterProduct =
                new SupplierBuilder(BOBSUPPLIER).withProduct("raw beans").build();
        Person nonSupplier = CARL;

        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs());
        actualModel.addPerson(nonSupplier);
        actualModel.addPerson(supplierWithLexicographicallyLaterProduct);
        actualModel.addPerson(supplierWithLexicographicallyEarlierProduct);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(supplierWithLexicographicallyEarlierProduct);
        expectedModel.addPerson(supplierWithLexicographicallyLaterProduct);
        expectedModel.addPerson(nonSupplier);

        SortCommand sortCommand = new SortCommand(new Prefix("product"));
        sortCommand.execute(actualModel);

        assertEquals(expectedModel.getFilteredPersonList(), actualModel.getFilteredPersonList());
    }

    @Test
    public void execute_validSortByPrice_sortSuccess() throws CommandException {
        Supplier supplierWithSmallerPrice =
                new SupplierBuilder(ALICESUPPLIER).withPrice("$10/bag").build();
        Supplier supplierWithLargerPrice =
                new SupplierBuilder(BOBSUPPLIER).withPrice("$50/bag").build();
        Person nonSupplier = CARL;

        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs());
        actualModel.addPerson(nonSupplier);
        actualModel.addPerson(supplierWithLargerPrice);
        actualModel.addPerson(supplierWithSmallerPrice);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(supplierWithSmallerPrice);
        expectedModel.addPerson(supplierWithLargerPrice);
        expectedModel.addPerson(nonSupplier);

        SortCommand sortCommand = new SortCommand(new Prefix("price"));
        sortCommand.execute(actualModel);

        assertEquals(expectedModel.getFilteredPersonList(), actualModel.getFilteredPersonList());
    }

    @Test
    public void execute_validSortByCommission_sortSuccess() throws CommandException {
        Maintainer maintainerWithSmallerCommission =
                new MaintainerBuilder(ALICEMAINTAINER).withCommission("$10/hr").build();
        Maintainer maintainerWithLargerCommission =
                new MaintainerBuilder(BOBMAINTAINER).withCommission("$50/hr").build();
        Person nonMaintainer = CARL;

        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs());
        actualModel.addPerson(nonMaintainer);
        actualModel.addPerson(maintainerWithLargerCommission);
        actualModel.addPerson(maintainerWithSmallerCommission);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(maintainerWithSmallerCommission);
        expectedModel.addPerson(maintainerWithLargerCommission);
        expectedModel.addPerson(nonMaintainer);

        SortCommand sortCommand = new SortCommand(new Prefix("commission"));
        sortCommand.execute(actualModel);

        assertEquals(expectedModel.getFilteredPersonList(), actualModel.getFilteredPersonList());
    }

    @Test
    public void execute_validSortBySkill_sortSuccess() throws CommandException {
        Maintainer maintainerWithLexicographicallyEarlierSkill =
                new MaintainerBuilder(ALICEMAINTAINER).withSkill("dog trainer").build();
        Maintainer maintainerWithLexicographicallyLaterSkill =
                new MaintainerBuilder(BOBMAINTAINER).withSkill("vet").build();
        Person nonMaintainer = CARL;

        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs());
        actualModel.addPerson(nonMaintainer);
        actualModel.addPerson(maintainerWithLexicographicallyLaterSkill);
        actualModel.addPerson(maintainerWithLexicographicallyEarlierSkill);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(maintainerWithLexicographicallyEarlierSkill);
        expectedModel.addPerson(maintainerWithLexicographicallyLaterSkill);
        expectedModel.addPerson(nonMaintainer);

        SortCommand sortCommand = new SortCommand(new Prefix("skill"));
        sortCommand.execute(actualModel);

        assertEquals(expectedModel.getFilteredPersonList(), actualModel.getFilteredPersonList());
    }
}
