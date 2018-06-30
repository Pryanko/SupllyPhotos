package ru.supplyphotos.presentation.mvp;

/**
 * @author Grigoriy Pryamov.
 */
public interface KeepAliveProvider {
    boolean keepAlive(boolean parentKeepAlive);
}
