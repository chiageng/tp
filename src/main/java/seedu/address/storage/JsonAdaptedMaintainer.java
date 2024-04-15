package seedu.address.storage;

import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;

//@@author chiageng
/**
 * Jackson-friendly version of {@link Maintainer}.
 */
class JsonAdaptedMaintainer extends JsonAdaptedPerson {
    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedMaintainer(Person source) {
        super(source);
        Maintainer supplier = (Maintainer) source;

        setSkill(supplier.getSkill().value);
        setCommission(supplier.getCommission().value);
    }
}
