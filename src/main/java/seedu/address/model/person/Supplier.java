package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

//@@author chiageng
/**
 * Represents a Supplier in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Supplier extends Person {
    // Data fields
    private final Product product;
    private final Price price;

    /**
     * Every field must be present and not null.
     */
    public Supplier(Name name, Phone phone, Email email, Address address, Note note, Set<Tag> tags,
                    Product product, Price price, Rating rating) {
        super(name, phone, email, address, note, tags, rating);
        requireAllNonNull(product, price);
        this.product = product;
        this.price = price;
    }
    //@@author

    //@@author chiageng
    public Price getPrice() {
        return price;
    }
    //@@author

    //@@author chiageng
    public Product getProduct() {
        return product;
    }
    //@@author

    //@@author jannaleong
    /**
     * Returns a new instantiation of the current {@code Supplier} with the updated note,
     * which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    @Override
    public Supplier updateNote(Note note) {
        Supplier supplierToReturn = new Supplier(this.getName(), this.getPhone(), this.getEmail(),
                this.getAddress(), note, this.getTags(), this.product, this.price, this.getRating());
        supplierToReturn.setPinIfPinned(this);
        return supplierToReturn;
    }
    //@@author

    //@@author jamessinmaojun
    /**
     * Returns a new instantiation of the current {@code Supplier} with the updated rating,
     * which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    @Override
    public Supplier updateRating(Rating rating) {
        Supplier supplierToReturn = new Supplier(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(),
                this.getNote(), this.getTags(), this.product, this.price, rating);
        supplierToReturn.setPinIfPinned(this);
        return supplierToReturn;
    }
    //@@author

    //@@author yleeyilin
    /**
     * Returns a new instantiation of the current {@code Supplier} with the updated pin,
     * which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    @Override
    public Supplier updateToPinned() {
        Supplier supplierToReturn = new Supplier(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(),
                this.getNote(), this.getTags(), this.product, this.price, this.getRating());
        supplierToReturn.toPin();
        return supplierToReturn;
    }
    //@@author

    //@@author yleeyilin
    /**
     * Returns a new instantiation of the current {@code Supplier} with the updated unpin,
     * which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    @Override
    public Supplier updateToUnpinned() {
        Supplier supplierToReturn = new Supplier(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(),
                this.getNote(), this.getTags(), this.product, this.price, this.getRating());
        supplierToReturn.toUnpin();
        return supplierToReturn;
    }
    //@@author

    //@@author chiageng
    /**
     * Returns true if both staffs have the same identity and data fields.
     * This defines a stronger notion of equality between two supplier.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Supplier)) {
            return false;
        }

        Supplier otherPerson = (Supplier) other;
        return super.equals(otherPerson)
                && product.equals(otherPerson.product)
                && price.equals(otherPerson.price);
    }

    //@@author chiageng
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(super.hashCode(), product, price);
    }

    //@@author chiageng
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", getName())
                .add("phone", getPhone())
                .add("email", getEmail())
                .add("address", getAddress())
                .add("tags", getTags())
                .add("rating", getRating())
                .add("product", product)
                .add("price", price)
                .toString();
    }
}
