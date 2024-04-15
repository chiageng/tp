package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COMMISSION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMPLOYMENT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PRODUCT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG;
import static seedu.address.logic.messages.EditMessages.FAILED_TO_EDIT;
import static seedu.address.logic.messages.Messages.MESSAGE_COMMAND_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_MISSING_FIELD_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_UNKNOWN_FIELD_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.messages.EditMessages;
import seedu.address.logic.messages.Messages;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonUtil;

//@@author yleeyilin
/**
 * Contains unit tests for {@code EditCommandParser}.
 */
public class EditCommandParserTest {
    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        String userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + " "
            + " " + PREFIX_FIELD + "{" + " }";
        assertParseFailure(parser, userInput, String.format(EditMessages.MESSAGE_EDIT_INVALID_NAME,
                Name.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_missingName_failure() {
        ArrayList<String> undetectedFields = new ArrayList<>();
        undetectedFields.add("name");
        String exception = FAILED_TO_EDIT + String.format(MESSAGE_MISSING_FIELD_FORMAT, undetectedFields);
        String expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        String userInput = EditCommand.COMMAND_WORD + " "
                + " " + PREFIX_FIELD + "{" + " }";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_missingField_failure() {
        ArrayList<String> undetectedFields = new ArrayList<>();
        undetectedFields.add("field");
        String exception = FAILED_TO_EDIT + String.format(MESSAGE_MISSING_FIELD_FORMAT, undetectedFields);
        String expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        String userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME;
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidSingleField_failure() {
        // specified invalid field (name)
        String userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + "Person1"
            + " " + PREFIX_FIELD + "{" + NAME_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        String exception = String.format(EditMessages.MESSAGE_MULTIPLE_NAME, EditCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, exception);

        // specified invalid field (product)
        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + "Person1"
            + " " + PREFIX_FIELD + "{" + PRODUCT_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        exception = FAILED_TO_EDIT + String.format(MESSAGE_UNKNOWN_FIELD_FORMAT, "[product]");
        exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, exception);

        // specified invalid field (price)
        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + "Person1"
            + " " + PREFIX_FIELD + "{" + PRICE_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        exception = FAILED_TO_EDIT + String.format(MESSAGE_UNKNOWN_FIELD_FORMAT, "[price]");
        exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, exception);

        // specified invalid field (employment)
        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + "Person1"
            + " " + PREFIX_FIELD + "{" + EMPLOYMENT_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        exception = FAILED_TO_EDIT + String.format(MESSAGE_UNKNOWN_FIELD_FORMAT, "[employment]");
        exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, exception);

        // specified invalid field (salary)
        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + "Person1"
            + " " + PREFIX_FIELD + "{" + SALARY_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        exception = FAILED_TO_EDIT + String.format(MESSAGE_UNKNOWN_FIELD_FORMAT, "[salary]");
        exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, exception);

        // specified invalid field (skill)
        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + "Person1"
            + " " + PREFIX_FIELD + "{" + SKILL_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        exception = FAILED_TO_EDIT + String.format(MESSAGE_UNKNOWN_FIELD_FORMAT, "[skill]");
        exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, exception);

        // specified invalid field (commission)
        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + "Person1"
            + " " + PREFIX_FIELD + "{" + COMMISSION_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        exception = FAILED_TO_EDIT + String.format(MESSAGE_UNKNOWN_FIELD_FORMAT, "[commission]");
        exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, exception);
    }

    @Test
    public void checkMultipleInvalidField() {
        // specified two invalid field (skill and commission)
        String userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + "Person1"
            + " " + PREFIX_FIELD + "{" + COMMISSION_DESC_AMY + SKILL_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        String exception = FAILED_TO_EDIT + String.format(MESSAGE_UNKNOWN_FIELD_FORMAT, "[commission, skill]");
        exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, exception);

        // specified three invalid field (name, skill and commission)
        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + "Person1"
            + " " + PREFIX_FIELD + "{" + COMMISSION_DESC_AMY + SKILL_DESC_AMY + NAME_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        exception = FAILED_TO_EDIT + String.format(MESSAGE_UNKNOWN_FIELD_FORMAT, "[commission, skill]");
        exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, exception);

        // specified three invalid field (salary, skill and commission)
        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + "Person1"
            + " " + PREFIX_FIELD + "{" + COMMISSION_DESC_AMY + SKILL_DESC_AMY + SALARY_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        exception = FAILED_TO_EDIT + String.format(MESSAGE_UNKNOWN_FIELD_FORMAT, "[commission, skill, salary]");
        exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, exception);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid name
        String userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + " "
            + " " + PREFIX_FIELD + "{" + PHONE_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        assertParseFailure(parser,
                userInput, String.format(EditMessages.MESSAGE_EDIT_INVALID_NAME, Name.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidValue_failure() {
        String userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + "Person1"
            + " " + PREFIX_FIELD + "{" + INVALID_PHONE_DESC + " }";
        assertParseFailure(parser, userInput,
                String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD, Phone.MESSAGE_CONSTRAINTS)); // invalid phone

        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + "Person1"
            + " " + PREFIX_FIELD + "{" + INVALID_EMAIL_DESC + " }";
        assertParseFailure(parser, userInput,
                String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD, Email.MESSAGE_CONSTRAINTS)); // invalid email

        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + "Person1"
            + " " + PREFIX_FIELD + "{" + INVALID_ADDRESS_DESC + " }";
        assertParseFailure(parser, userInput,
                EditMessages.MESSAGE_EDIT_EMPTY_FIELD); // invalid address

        // invalid phone followed by valid email
        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME + "Person1"
            + " " + PREFIX_FIELD + "{" + INVALID_PHONE_DESC + EMAIL_DESC_AMY + " }";
        assertParseFailure(parser, userInput,
                String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD, Phone.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName("Person1")
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG).build();
        EditCommand expectedCommand = new EditCommand(new Name("Person1"), descriptor);
        String userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME
            + "Person1" + " " + PREFIX_FIELD + "{ "
            + PersonUtil.getEditPersonDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG).build();
        EditCommand expectedCommand = new EditCommand(ALICE.getName(), descriptor);
        String userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME
            + ALICE.getName() + " " + PREFIX_FIELD + "{ "
            + PersonUtil.getEditPersonDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // phone
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_AMY).withTags(VALID_TAG).build();
        EditCommand expectedCommand = new EditCommand(CARL.getName(), descriptor);
        String userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{ "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        descriptor = new EditPersonDescriptorBuilder()
                .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG).build();
        expectedCommand = new EditCommand(CARL.getName(), descriptor);
        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{ "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG).build();
        expectedCommand = new EditCommand(CARL.getName(), descriptor);
        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{ "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // invalid followed by valid
        String userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{"
                + INVALID_PHONE_DESC + PHONE_DESC_BOB + " }";

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // valid followed by invalid
        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{"
                + PHONE_DESC_BOB + INVALID_PHONE_DESC + " }";

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple valid fields repeated
        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{"
                + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + PHONE_DESC_AMY
                + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));

        // multiple invalid values
        userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{"
                + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC
                + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC + " }";

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));
    }
}
