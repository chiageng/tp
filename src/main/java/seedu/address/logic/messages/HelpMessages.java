package seedu.address.logic.messages;

//@@author jannaleong
/**
 * Container for {@code HelpCommand} visible messages.
 */
public class HelpMessages extends Messages {
    public static final String HELP = "help";
    public static final String FAILED_TO_HELP = "Failed to display help window - ";
    public static final String MESSAGES_SHOWING_HELP_MESSAGE = "Opened help window.";
    public static final String MESSAGES_SHOWING_DELETE_HELP_MESSAGE = "Opened help window for delete command.";
    public static final String MESSAGES_SHOWING_EDIT_HELP_MESSAGE = "Opened help window for edit command.";
    public static final String MESSAGES_SHOWING_ADD_HELP_MESSAGE =
            "Opened help window for add command.";
    public static final String MESSAGES_SHOWING_SEARCH_HELP_MESSAGE =
            "Opened help window for search command.";
    public static final String MESSAGES_SHOWING_EXIT_HELP_MESSAGE =
            "Opened help window for exit command.";
    public static final String MESSAGES_SHOWING_LIST_HELP_MESSAGE =
            "Opened help window for list command.";
    public static final String MESSAGES_SHOWING_NOTE_HELP_MESSAGE =
            "Opened help window for note command.";
    public static final String MESSAGES_SHOWING_PIN_HELP_MESSAGE =
            "Opened help window for pin command.";
    public static final String MESSAGES_SHOWING_UNPIN_HELP_MESSAGE =
            "Opened help window for unpin command.";
    public static final String MESSAGES_SHOWING_RATE_HELP_MESSAGE =
            "Opened help window for rate command.";
    public static final String MESSAGES_SHOWING_REDO_HELP_MESSAGE =
            "Opened help window for redo command.";
    public static final String MESSAGES_SHOWING_UNDO_HELP_MESSAGE =
            "Opened help window for undo command.";
    public static final String MESSAGES_SHOWING_REMIND_HELP_MESSAGE =
            "Opened help window for remind command.";
    public static final String MESSAGES_SHOWING_SORT_HELP_MESSAGE =
            "Opened help window for sort command.";
    public static final String MESSAGES_SHOWING_CLEAR_HELP_MESSAGE =
            "Opened help window for clear command.";
    public static final String MESSAGES_INVALID_COMMAND_TYPE = "Invalid command type given.";
    public static final String MESSAGE_HELP_MISSING_COMMAND = "Failed to give help - "
            + "Help requires a command type field. %1$s\uD83D\uDC3E";
    public static final String MESSAGE_HELP_INVALID_PARAMETERS = FAILED_TO_HELP
            + "%1$s \uD83D\uDC3E";

    public static final String USERGUIDE_URL = "https://ay2324s2-cs2103t-w10-2.github.io/tp/UserGuide.html";

    public static final String DISPLAYED_DELETE_MESSAGE = "Deletes the specified contact."
            + "\n" + "" + "\n"
            + "Format: /delete ; name : [name] "
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_ADD_MESSAGE = "Adds a other/maintainer/supplier/staff person."
            + "\n" + "" + "\n"
            + "Format:" + "\n"
            + "/add-person ; name : [name] ; phone : [phone] ; address : [address] ; email : [email]"
            + "\n"
            + "/add-maintainer ; name : [name] ; phone : [phone] ; address : [address] ;"
            + " email : [email] ; skill : [skill] ; commission : [commission/hr]" + "\n"
            + "/add-supplier ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ;"
            + " product : [product] ; price : [price/(quantity)]" + "\n"
            + "/add-staff ; name : [name] ; phone : [phone] ; address : [address] ;"
            + " email : [email] ; salary : [salary/hr] ; employment : [part-time/full-time]" + "\n"
            + "\n" + ""
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_EDIT_MESSAGE = "Edit the fields of the specified contact"
            + "\n" + "" + "\n"
            + "Format:" + "\n"
            + "/edit-person ; name : [name] ; field : { [field] : [value] }" + "\n"
            + "/edit-staff ; name : [name] ; field : { [field] : [value] }" + "\n"
            + "/edit-supplier ; name : [name] ; field : { [field] : [value] }" + "\n"
            + "/edit-maintainer ; name : [name] ; field : { [field] : [value] }" + "\n"
            + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;


    public static final String DISPLAYED_SEARCH_MESSAGE = "Searches for a contact using"
            + " specified fields and keyword."
            + "\n" + "" + "\n"
            + "Format:" + "\n"
            + "/search ; [target-field] : [value]" + "\n"
            + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_EXIT_MESSAGE = "Exits the program."
            + "\n" + "" + "\n"
            + "Format: " + "/exit"
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_LIST_MESSAGE = "List all contacts."
            + "\n" + "" + "\n"
            + "Format: " + "/list"
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_NOTE_MESSAGE = "Adds a note to a contact."
            + "\n" + "" + "\n"
            + "Format:" + "\n"
            + "/note ; name : [name] ; note : [note message]" + "\n"
            + "OR" + "\n"
            + "/note ; name : [name] ; note : [note message] ; deadline : [deadline date]"
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_PIN_MESSAGE = "Pin specified contact at the top of the planner."
            + "\n" + "" + "\n"
            + "Format: " + "/pin ; name : [name]"
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_UNPIN_MESSAGE = "Unpin specified contact at the top of the planner."
            + "\n" + "" + "\n"
            + "Format: " + "/unpin ; name : [name]"
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_RATE_MESSAGE = "Adds a rating to a specified contact from 1-5."
            + "\n" + "" + "\n"
            + "Format: " + "/rate ; name : [name] ; rating : [rating value from 1-5]"
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_REDO_MESSAGE = "Redoes your previous command."
            + "\n" + "" + "\n"
            + "Format: " + "/redo"
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_UNDO_MESSAGE = "Undoes your previous command."
            + "\n" + "" + "\n"
            + "Format: " + "/undo"
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_REMIND_MESSAGE = "Displays all contacts with note deadlines"
            + " from today onwards."
            + "\n" + "" + "\n"
            + "Format: " + "/remind"
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_SORT_MESSAGE = "Sorts contacts based on specified field."
            + "\n" + "" + "\n"
            + "Format: " + "/sort ; [target-field] : [value]"
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_CLEAR_MESSAGE = "Clear all contacts."
            + "\n" + "" + "\n"
            + "Format: " + "/clear"
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;
}
