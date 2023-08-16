package dev.thewlabs.schoolar.common.mail.models;

import org.thymeleaf.context.Context;

public interface Mail {
    String getDestination();

    String getSubject();

    String getTemplate();

    Context getContext();
}
