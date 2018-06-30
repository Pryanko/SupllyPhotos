package ru.supplyphotos.data.model.error;

/**
 * @author Grigoriy Pryamov.
 */
public interface UserError {

    UserError NO_ERROR = () -> "";

    /**
     * Возвращает текст сообщения
     */
    String getMessage();
}
