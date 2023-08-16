package dev.thewlabs.schoolar.domain.events.enums;

public enum EventStatus {
    DRAFT,
    PENDING,
    APPROVED,
    REJECTED,
    CANCELLED, // The event is cancelled
    DELAYED, // The event is delayed to a later time on the same day
    POSTPONED, // The event is planned to take place at a later date but not yet confirmed
    RESCHEDULED, // The event is planned to take place at a later date and confirmed
    ONGOING, // The event is currently taking place
    SUSPENDED, // The event has been suspended due to unforeseen circumstances
    ABANDONED, // The event has been abandoned due to unforeseen circumstances
    COMPLETED, // The event has been completed successfully
    ARCHIVED
}
