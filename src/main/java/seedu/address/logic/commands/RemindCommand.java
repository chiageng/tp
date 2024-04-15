package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.messages.RemindMessages;
import seedu.address.model.Model;
import seedu.address.model.person.RemindPredicate;

//@@author jannaleong
/**
 * Displays all persons with notes that have deadlines from
 * the current day onwards.
 */
public class RemindCommand extends Command {
    public static final String COMMAND_WORD = "/remind";
    public static final String LOGGER_EXECUTE_REMIND_MESSAGE = "Started executing the remind command.";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @Override
    public CommandResult execute(Model model) {
        logger.info(LOGGER_EXECUTE_REMIND_MESSAGE);
        requireNonNull(model);

        model.updateFilteredPersonList(new RemindPredicate());

        return new CommandResult(
                String.format(RemindMessages.MESSAGE_REMIND_PERSON_SUCCESS, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        return other instanceof RemindCommand;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
