package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICEMAINTAINER;
import static seedu.address.testutil.TypicalPersons.ALICESTAFF;
import static seedu.address.testutil.TypicalPersons.ALICESUPPLIER;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.BOBMAINTAINER;
import static seedu.address.testutil.TypicalPersons.BOBSTAFF;
import static seedu.address.testutil.TypicalPersons.BOBSUPPLIER;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.MaintainerBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.SupplierBuilder;

public class UniquePersonListTest {

    private final UniquePersonList uniquePersonList = new UniquePersonList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniquePersonList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePersonList.add(ALICE);
        assertTrue(uniquePersonList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniquePersonList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniquePersonList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniquePersonList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.setPerson(ALICE, ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(ALICE);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniquePersonList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniquePersonList.setPerson(ALICE, editedAlice);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(editedAlice);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.setPerson(ALICE, BOB);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPerson(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePersonList.add(ALICE);
        uniquePersonList.remove(ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((UniquePersonList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePersonList.add(ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        uniquePersonList.setPersons(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((List<Person>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePersonList.add(ALICE);
        List<Person> personList = Collections.singletonList(BOB);
        uniquePersonList.setPersons(personList);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sortInternalListByPin() {
        Person personToPin = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        personToPin.toPin();
        uniquePersonList.add(personToPin);
        uniquePersonList.sortByPinnedStatus();
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(personToPin);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    //@@author Joshy837
    @Test
    public void sortInternalListByName() {
        uniquePersonList.add(BOB);
        uniquePersonList.add(ALICE);
        uniquePersonList.sortBy(new Prefix("name"));

        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(ALICE);
        expectedUniquePersonList.add(BOB);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sortInternalListByPhone() {
        Person personWithLargerPhoneNo = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        Person personWithSmallerPhoneNo = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY).build();

        uniquePersonList.add(personWithLargerPhoneNo);
        uniquePersonList.add(personWithSmallerPhoneNo);
        uniquePersonList.sortBy(new Prefix("phone"));

        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(personWithSmallerPhoneNo);
        expectedUniquePersonList.add(personWithLargerPhoneNo);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sortInternalListByEmail() {
        Person personWithLexicographicallyLaterEmail =
                new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        Person personWithLexicographicallyEarlierEmail =
                new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY).build();

        uniquePersonList.add(personWithLexicographicallyLaterEmail);
        uniquePersonList.add(personWithLexicographicallyEarlierEmail);
        uniquePersonList.sortBy(new Prefix("email"));

        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(personWithLexicographicallyEarlierEmail);
        expectedUniquePersonList.add(personWithLexicographicallyLaterEmail);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sortInternalListByAddress() {
        Person personWithLexicographicallyLaterAddress =
                new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_AMY).build();
        Person personWithLexicographicallyEarlierAddress =
                new PersonBuilder(BOB).withAddress(VALID_ADDRESS_BOB).build();

        uniquePersonList.add(personWithLexicographicallyLaterAddress);
        uniquePersonList.add(personWithLexicographicallyEarlierAddress);
        uniquePersonList.sortBy(new Prefix("address"));

        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(personWithLexicographicallyEarlierAddress);
        expectedUniquePersonList.add(personWithLexicographicallyLaterAddress);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sortInternalListByTag() {
        uniquePersonList.add(ALICESTAFF);
        uniquePersonList.add(BOBMAINTAINER);
        uniquePersonList.sortBy(new Prefix("tag"));

        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOBMAINTAINER);
        expectedUniquePersonList.add(ALICESTAFF);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sortInternalListBySalary() {
        Staff staffWithSmallerSalary = new StaffBuilder(ALICESTAFF).withSalary("$50/hr").build();
        Staff staffWithLargerSalary = new StaffBuilder(BOBSTAFF).withSalary("$100/hr").build();
        Person nonStaff = CARL;

        uniquePersonList.add(nonStaff);
        uniquePersonList.add(staffWithSmallerSalary);
        uniquePersonList.add(staffWithLargerSalary);
        uniquePersonList.sortBy(new Prefix("salary"));

        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(staffWithSmallerSalary);
        expectedUniquePersonList.add(staffWithLargerSalary);
        expectedUniquePersonList.add(nonStaff);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sortInternalListByEmployment() {
        Staff staffWithLexicographicallyEarlierEmployment =
                new StaffBuilder(ALICESTAFF).withEmployment("full-time").build();
        Staff staffWithLexicographicallyLaterEmployment =
                new StaffBuilder(BOBSTAFF).withEmployment("part-time").build();
        Person nonStaff = CARL;

        uniquePersonList.add(nonStaff);
        uniquePersonList.add(staffWithLexicographicallyLaterEmployment);
        uniquePersonList.add(staffWithLexicographicallyEarlierEmployment);
        uniquePersonList.sortBy(new Prefix("employment"));

        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(staffWithLexicographicallyEarlierEmployment);
        expectedUniquePersonList.add(staffWithLexicographicallyLaterEmployment);
        expectedUniquePersonList.add(nonStaff);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sortInternalListByProduct() {
        Supplier supplierWithLexicographicallyEarlierProduct =
                new SupplierBuilder(ALICESUPPLIER).withProduct("dog food").build();
        Supplier supplierWithLexicographicallyLaterProduct =
                new SupplierBuilder(BOBSUPPLIER).withProduct("raw beans").build();
        Person nonSupplier = CARL;

        uniquePersonList.add(nonSupplier);
        uniquePersonList.add(supplierWithLexicographicallyLaterProduct);
        uniquePersonList.add(supplierWithLexicographicallyEarlierProduct);
        uniquePersonList.sortBy(new Prefix("product"));

        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(supplierWithLexicographicallyEarlierProduct);
        expectedUniquePersonList.add(supplierWithLexicographicallyLaterProduct);
        expectedUniquePersonList.add(nonSupplier);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sortInternalListByPrice() {
        Supplier supplierWithSmallerPrice =
                new SupplierBuilder(ALICESUPPLIER).withPrice("$10/bag").build();
        Supplier supplierWithLargerPrice =
                new SupplierBuilder(BOBSUPPLIER).withPrice("$50/bag").build();
        Person nonSupplier = CARL;

        uniquePersonList.add(nonSupplier);
        uniquePersonList.add(supplierWithLargerPrice);
        uniquePersonList.add(supplierWithSmallerPrice);
        uniquePersonList.sortBy(new Prefix("price"));

        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(supplierWithSmallerPrice);
        expectedUniquePersonList.add(supplierWithLargerPrice);
        expectedUniquePersonList.add(nonSupplier);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sortInternalListByCommission() {
        Maintainer maintainerWithSmallerCommission =
                new MaintainerBuilder(ALICEMAINTAINER).withCommission("$10/hr").build();
        Maintainer maintainerWithLargerCommission =
                new MaintainerBuilder(BOBMAINTAINER).withCommission("$50/hr").build();
        Person nonMaintainer = CARL;

        uniquePersonList.add(nonMaintainer);
        uniquePersonList.add(maintainerWithLargerCommission);
        uniquePersonList.add(maintainerWithSmallerCommission);
        uniquePersonList.sortBy(new Prefix("commission"));

        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(maintainerWithSmallerCommission);
        expectedUniquePersonList.add(maintainerWithLargerCommission);
        expectedUniquePersonList.add(nonMaintainer);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sortInternalListBySkill() {
        Maintainer maintainerWithLexicographicallyEarlierSkill =
                new MaintainerBuilder(ALICEMAINTAINER).withSkill("dog trainer").build();
        Maintainer maintainerWithLexicographicallyLaterSkill =
                new MaintainerBuilder(BOBMAINTAINER).withSkill("vet").build();
        Person nonMaintainer = CARL;

        uniquePersonList.add(nonMaintainer);
        uniquePersonList.add(maintainerWithLexicographicallyLaterSkill);
        uniquePersonList.add(maintainerWithLexicographicallyEarlierSkill);
        uniquePersonList.sortBy(new Prefix("skill"));

        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(maintainerWithLexicographicallyEarlierSkill);
        expectedUniquePersonList.add(maintainerWithLexicographicallyLaterSkill);
        expectedUniquePersonList.add(nonMaintainer);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sortInternalListByNote() {
        Person personWithLexicographicallyEarlierNote =
                new PersonBuilder(BOB).withNote("buy food").build();
        Person personWithLexicographicallyLaterNote =
                new PersonBuilder(ALICE).withNote("get kibble").build();

        uniquePersonList.add(personWithLexicographicallyLaterNote);
        uniquePersonList.add(personWithLexicographicallyEarlierNote);
        uniquePersonList.sortBy(new Prefix("note"));

        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(personWithLexicographicallyEarlierNote);
        expectedUniquePersonList.add(personWithLexicographicallyLaterNote);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sortInternalListByRating() {
        Person personWithSmallerRating = new PersonBuilder(ALICE).withRating("3").build();
        Person personWithLargerRating = new PersonBuilder(BOB).withRating("5").build();
        Person personWithNoRating = new PersonBuilder(CARL).withRating("").build();

        uniquePersonList.add(personWithNoRating);
        uniquePersonList.add(personWithSmallerRating);
        uniquePersonList.add(personWithLargerRating);
        uniquePersonList.sortBy(new Prefix("rating"));

        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(personWithLargerRating);
        expectedUniquePersonList.add(personWithSmallerRating);
        expectedUniquePersonList.add(personWithNoRating);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sortInternalListBySortPin() {
        Person personWithPin = new PersonBuilder(ALICE).build();
        personWithPin.toPin();
        Person personWithNoPin = BOB;

        uniquePersonList.add(personWithNoPin);
        uniquePersonList.add(personWithPin);
        uniquePersonList.sortBy(new Prefix("pin"));

        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(personWithPin);
        expectedUniquePersonList.add(personWithNoPin);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }
    //@@author

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Person> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPersons(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePersonList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniquePersonList.asUnmodifiableObservableList().toString(), uniquePersonList.toString());
    }
}
