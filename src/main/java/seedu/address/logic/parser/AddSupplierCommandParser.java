package seedu.address.logic.parser;

import static seedu.address.logic.messages.Messages.FAILED_TO_ADD;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddSupplierCommand;
import seedu.address.logic.messages.AddMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;
import seedu.address.model.person.Product;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Supplier;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddStaffCommand object
 */
public class AddSupplierCommandParser implements Parser<AddSupplierCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());
    /**
     * Parses the given {@code String} of arguments in the context of the AddStaffCommand
     * and returns an AddCommand object for execution. Parameter args cannot be null.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddSupplierCommand parse(String args) throws ParseException {
        assert (args != null) : "`argument` to pass for add supplier command is null";

        logger.log(Level.INFO, "Going to start parsing for supplier command.");

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_PRODUCT, PREFIX_PRICE, PREFIX_RATING);

        // Validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, AddSupplierCommand.MESSAGE_USAGE, "add-supplier",
                FAILED_TO_ADD, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_PRODUCT, PREFIX_PRICE, PREFIX_RATING);
        ParserUtil.verifyNoMissingField(argMultimap, AddSupplierCommand.MESSAGE_USAGE, "add-supplier",
                FAILED_TO_ADD, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_PRODUCT, PREFIX_PRICE);
        boolean isPreambleEmpty = argMultimap.isPreambleEmpty();
        if (!isPreambleEmpty) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSupplierCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_PRODUCT, PREFIX_PRICE);

        Supplier person = createSupplierContact(argMultimap);

        return new AddSupplierCommand(person);
    }

    /**
     * Creates a supplier contact based on the argument multimap.
     * @param argMultimap Contains the mappings of values to the specific prefixes.
     * @return A supplier contact.
     * @throws ParseException Thrown when invalid paramters are used.
     */
    private Supplier createSupplierContact(ArgumentMultimap argMultimap) throws ParseException {
        try {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElseThrow());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElseThrow());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElseThrow());
            Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElseThrow());
            String noteContent = argMultimap.getValue(PREFIX_NOTE).orElse("No note here");
            Note note = noteContent.equals("No note here") ? new Note(noteContent) : ParserUtil.parseNote(noteContent);
            Rating rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).orElse("0"));
            Tag tag = new Tag("supplier");
            Set<Tag> tags = new HashSet<>();
            tags.add(tag);
            Price price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).orElseThrow());
            Product product = ParserUtil.parseProduct(argMultimap.getValue(PREFIX_PRODUCT).orElseThrow());
            return new Supplier(name, phone, email, address, note, tags, product, price, rating);
        } catch (ParseException pe) {
            throw new ParseException(String.format(AddMessages.MESSAGE_ADD_INVALID_PARAMETERS, pe.getMessage()));
        }
    }
}
