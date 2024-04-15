package seedu.address.model.person;

//@@author yleeyilin
/**
 * Represents a Person's Pin in the address book.
 */
public class Pin {

    private Boolean isPin;

    /**
     * Constructs a {@code Pin}.
     */
    public Pin() {
        isPin = false;
    }

    public void setPin() {
        this.isPin = true;
    }

    public void setUnpin() {
        this.isPin = false;
    }

    public boolean getIsPinned() {
        return this.isPin;
    }

    @Override
    public String toString() {
        return isPin ? "true" : "false";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Note)) {
            return false;
        }

        Pin otherPin = (Pin) other;
        return isPin.equals(otherPin.isPin);
    }

    @Override
    public int hashCode() {
        return isPin.hashCode();
    }
}
