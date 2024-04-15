package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    //@@author yleeyilin
    /**
     * Sorts the list based on the isPinned field of contacts.
     * Tie-breaks using the lexographical order of the contact names.
     */
    public void sortByPinnedStatus() {
        internalList.sort(Comparator.comparing(Person::isPinned).reversed()
                .thenComparing(p -> p.getName().toString()));
    }
    //@@author

    //@@author Joshy837
    /**
     * Sorts the list based on the specified field.
     *
     * @param prefix The prefix representing the field to sort by.
     */
    public void sortBy(Prefix prefix) {
        String field = prefix.getPrefix();

        switch (field.toLowerCase()) {
        case "name":
            sortByFieldName();
            break;
        case "phone":
            sortByFieldPhone();
            break;
        case "email":
            sortByFieldEmail();
            break;
        case "address":
            sortByFieldAddress();
            break;
        case "tag":
            sortByFieldTag();
            break;
        case "salary":
            sortByFieldSalary();
            break;
        case "employment":
            sortByFieldEmployment();
            break;
        case "product":
            sortByFieldProduct();
            break;
        case "price":
            sortByFieldPrice();
            break;
        case "commission":
            sortByFieldCommission();
            break;
        case "skill":
            sortByFieldSkill();
            break;
        case "note":
            sortByFieldNote();
            break;
        case "pin":
            sortByFieldPin();
            break;
        case "rating":
            sortByFieldRating();
            break;
        default:
            sortByFieldName(); // Default sorting by name
            break;
        }

        sortByFieldPin();
    }

    /**
     * Sorts the list by name.
     */
    private void sortByFieldName() {
        internalList.sort(Comparator.comparing(p -> p.getName().toString()));
    }

    /**
     * Sorts the list by phone number.
     */
    private void sortByFieldPhone() {
        internalList.sort(Comparator.comparing(p -> Integer.parseInt(p.getPhone().toString())));
    }

    /**
     * Sorts the list by email address.
     */
    private void sortByFieldEmail() {
        internalList.sort(Comparator.comparing(p -> p.getEmail().toString()));
    }

    /**
     * Sorts the list by address.
     */
    private void sortByFieldAddress() {
        internalList.sort(Comparator.comparing(p -> p.getAddress().toString()));
    }

    /**
     * Sorts the list by tag.
     */
    private void sortByFieldTag() {
        internalList.sort(Comparator.comparing(p -> p.getTags().toString()));
    }

    /**
     * Sorts the list by salary.
     */
    private void sortByFieldSalary() {
        internalList.sort(Comparator.comparing(p -> {
            if (p instanceof Staff) {
                Staff staff = (Staff) p;
                String salary = staff.getSalary().toString();
                return "0" + salary.length() + salary;
            }
            return "1" + p.getName().toString();
        }));
    }

    /**
     * Sorts the list by employment status.
     */
    private void sortByFieldEmployment() {
        internalList.sort(Comparator.comparing(p -> {
            if (p instanceof Staff) {
                Staff staff = (Staff) p;
                return "0" + staff.getEmployment().toString();
            }
            return "1" + p.getName().toString();
        }));
    }

    /**
     * Sorts the list by product.
     */
    private void sortByFieldProduct() {
        internalList.sort(Comparator.comparing(p -> {
            if (p instanceof Supplier) {
                Supplier supplier = (Supplier) p;
                String product = supplier.getProduct().toString();
                return "0" + product.length() + product;
            }
            return "1" + p.getName().toString();
        }));
    }

    /**
     * Sorts the list by price.
     */
    private void sortByFieldPrice() {
        internalList.sort(Comparator.comparing(p -> {
            if (p instanceof Supplier) {
                Supplier supplier = (Supplier) p;
                return "0" + supplier.getPrice().toString();
            }
            return "1" + p.getName().toString();
        }));
    }

    /**
     * Sorts the list by commission.
     */
    private void sortByFieldCommission() {
        internalList.sort(Comparator.comparing(p -> {
            if (p instanceof Maintainer) {
                Maintainer maintainer = (Maintainer) p;
                String commission = maintainer.getCommission().toString();
                return "0" + commission.length() + commission;
            }
            return "1" + p.getName().toString();
        }));
    }

    /**
     * Sorts the list by skill.
     */
    private void sortByFieldSkill() {
        internalList.sort(Comparator.comparing(p -> {
            if (p instanceof Maintainer) {
                Maintainer maintainer = (Maintainer) p;
                return "0" + maintainer.getSkill().toString();
            }
            return "1" + p.getName().toString();
        }));
    }

    /**
     * Sorts the list by note.
     */
    private void sortByFieldNote() {
        internalList.sort(Comparator.comparing((Person p) -> {
            String note = p.getNote().toString();
            if (note.equals("No note here")) {
                return "1" + p.getName().toString();
            } else {
                return "0" + p.getNote().toString();
            }
        }));
    }

    /**
     * Sorts the list by PIN.
     */
    private void sortByFieldPin() {
        internalList.sort(Comparator.comparing((Person p) -> p.getPin().toString()).reversed());
    }

    /**
     * Sorts the list by rating.
     */
    private void sortByFieldRating() {
        internalList.sort(Comparator.comparing((Person p) -> p.getRating().toString()).reversed());
    }
    //@@author

    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniquePersonList)) {
            return false;
        }

        UniquePersonList otherUniquePersonList = (UniquePersonList) other;
        return internalList.equals(otherUniquePersonList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Person> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSamePerson(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
