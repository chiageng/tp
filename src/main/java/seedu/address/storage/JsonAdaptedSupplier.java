package seedu.address.storage;

import seedu.address.model.person.Person;
import seedu.address.model.person.Supplier;

//@@author chiageng
/**
 * Jackson-friendly version of {@link Supplier}.
 */
class JsonAdaptedSupplier extends JsonAdaptedPerson {
    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedSupplier(Person source) {
        super(source);
        Supplier supplier = (Supplier) source;

        setProduct(supplier.getProduct().value);
        setPrice(supplier.getPrice().value);
    }
}
