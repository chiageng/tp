package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.HelpMessages;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private static final String TITLE = "Pooch Planner";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;

    private HelpWindow helpWindow;

    @FXML
    private StackPane titlePlaceholder;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     *
     * @param commandResult Message received from execute method which indicates
     *                     the type of command to give help for.
     */
    @FXML
    public void handleAllHelp(CommandResult commandResult) {
        String userFeedback = commandResult.getFeedbackToUser();
        if (!commandResult.isShowHelp()) {
            return;
        }

        // to determine which command user needs help for.
        Boolean isHelpGeneralCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_HELP_MESSAGE);
        Boolean isDeleteHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_DELETE_HELP_MESSAGE);
        Boolean isEditHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_EDIT_HELP_MESSAGE);
        Boolean isSearchHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_SEARCH_HELP_MESSAGE);
        Boolean isAddHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_ADD_HELP_MESSAGE);
        Boolean isExitHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_EXIT_HELP_MESSAGE);
        Boolean isListHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_LIST_HELP_MESSAGE);
        Boolean isNoteHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_NOTE_HELP_MESSAGE);
        Boolean isPinHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_PIN_HELP_MESSAGE);
        Boolean isUnpinHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_UNPIN_HELP_MESSAGE);
        Boolean isRateHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_RATE_HELP_MESSAGE);
        Boolean isRedoHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_REDO_HELP_MESSAGE);
        Boolean isUndoHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_UNDO_HELP_MESSAGE);
        Boolean isRemindHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_REMIND_HELP_MESSAGE);
        Boolean isSortHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_SORT_HELP_MESSAGE);
        Boolean isClearHelpCommand = userFeedback.equals(HelpMessages.MESSAGES_SHOWING_CLEAR_HELP_MESSAGE);

        // set different help messages based on type of command to get help for.
        if (isHelpGeneralCommand) {
            handleHelp();
        } else if (isDeleteHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_DELETE_MESSAGE);
        } else if (isEditHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_EDIT_MESSAGE);
        } else if (isSearchHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_SEARCH_MESSAGE);
        } else if (isAddHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_ADD_MESSAGE);
        } else if (isExitHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_EXIT_MESSAGE);
        } else if (isListHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_LIST_MESSAGE);
        } else if (isNoteHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_NOTE_MESSAGE);
        } else if (isPinHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_PIN_MESSAGE);
        } else if (isUnpinHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_UNPIN_MESSAGE);
        } else if (isRateHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_RATE_MESSAGE);
        } else if (isRedoHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_REDO_MESSAGE);
        } else if (isUndoHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_UNDO_MESSAGE);
        } else if (isRemindHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_REMIND_MESSAGE);
        } else if (isSortHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_SORT_MESSAGE);
        } else if (isClearHelpCommand) {
            handleOtherHelp(HelpMessages.DISPLAYED_CLEAR_MESSAGE);
        } else {
            // As particular command cannot be identified, display general help message
            // to still provide user with some help.
            handleHelp();
        }
    }

    /**
     * Opens the help window for a specific command or focuses on it if it's already opened.
     *
     * @param displayedMessage Message to be displayed to users.
     */
    @FXML
    public void handleOtherHelp(String displayedMessage) {
        helpWindow.setHelpMessage(displayedMessage);
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }


    /**
     * Opens the general help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        String generalHelpMessage =
                "Refer to the user guide: https://ay2324s2-cs2103t-w10-2.github.io/tp/UserGuide.html";
        helpWindow.setHelpMessage(generalHelpMessage);
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleAllHelp(commandResult);
            }
            if (commandResult.isExit()) {
                handleExit();
            }
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
