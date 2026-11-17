package de.btegermany.core.common.completion;

import co.aikar.commands.CommandManager;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.*;
import java.util.Collection;

@RequiredArgsConstructor
public class CompletionsRegistration {
    private final CommandManager<?, ?, ?, ?, ?, ?> commandManager;

    public void registerAll(Object... completionObjects) {
        for (Object completionObject : completionObjects) {
            this.register(completionObject);
        }
    }

    public void register(Object completionObject) {
        for (Method method : completionObject.getClass().getMethods()) {
            if (!this.isAnnotationPresent(method)) {
                continue;
            }
            String completionId = method.getAnnotation(AsyncCompletion.class).value();

            if (!this.isCorrectReturnType(method)) {
                throw new InvalidCompletionException(completionId);
            }

            this.registerAsyncCompletion(completionObject, method, completionId);
        }
    }

    private void registerAsyncCompletion(Object completionObject, Method method, String completionId) {
        this.commandManager.getCommandCompletions().registerAsyncCompletion(
                completionId,
                context -> {
                    try {
                        Object result = method.invoke(completionObject, context);
                        return this.castInvokedResult(result);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new InvalidCompletionException(completionId, e);
                    }
                }
        );
    }

    private boolean isAnnotationPresent(Method method) {
        return method.isAnnotationPresent(AsyncCompletion.class);
    }

    private boolean isCorrectReturnType(Method method) {
        AnnotatedType annotatedType = method.getAnnotatedReturnType();

        if (!(annotatedType instanceof AnnotatedParameterizedType apt)) {
            return false;
        }

        return apt.getType().toString().equals("java.util.Collection<java.lang.String>");
    }

    @SuppressWarnings("unchecked")
    private Collection<String> castInvokedResult(Object result) {
        return (Collection<String>) result;
    }

    private static class InvalidCompletionException extends RuntimeException {
        public InvalidCompletionException(String completionId, Throwable cause) {
            super("Can't call method or return type doesn't match Collection<String> for completionId '%s'".formatted(completionId), cause);
        }
        public InvalidCompletionException(String completionId) {
            this(completionId, null);
        }
    }

}
