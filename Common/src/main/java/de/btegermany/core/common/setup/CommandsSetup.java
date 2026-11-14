package de.btegermany.core.common.setup;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandManager;
import de.btegermany.core.common.completion.CompletionsRegistration;

public class CommandsSetup<M extends CommandManager<?, ?, ?, ?, ?, ?>> {

    private final M commandManager;
    private final CompletionsRegistration completionsRegistration;

    public CommandsSetup(M commandManager, Object... defaultCompletionObjects) {
        this.commandManager = commandManager;
        this.completionsRegistration = new CompletionsRegistration(commandManager);

        this.completions(defaultCompletionObjects);
    }

    public CommandsSetup<M> commands(BaseCommand... commands) {
        for (BaseCommand command : commands) {
            this.commandManager.registerCommand(command);
        }
        return this;
    }

    public CommandsSetup<M> completions(Object... completionObjects) {
        this.completionsRegistration.registerAll(completionObjects);
        return this;
    }

}
