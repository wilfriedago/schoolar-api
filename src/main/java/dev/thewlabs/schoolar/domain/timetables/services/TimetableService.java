package dev.thewlabs.schoolar.domain.timetables.services;

import dev.thewlabs.schoolar.domain.events.Event;
import dev.thewlabs.schoolar.domain.events.EventMapper;
import dev.thewlabs.schoolar.domain.events.dtos.EventDetailsDto;
import dev.thewlabs.schoolar.domain.timetables.entities.ClassroomTimetable;
import dev.thewlabs.schoolar.domain.timetables.entities.GroupTimetable;
import dev.thewlabs.schoolar.domain.timetables.repositories.ClassroomTimetableRepository;
import dev.thewlabs.schoolar.domain.timetables.repositories.GroupTimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TimetableService {
    private final GroupTimetableRepository groupTimetableRepository;
    private final EventMapper eventMapper;
    private final ClassroomTimetableRepository classroomTimetableRepository;

    @Autowired
    public TimetableService(GroupTimetableRepository groupTimetableRepository, EventMapper eventMapper, ClassroomTimetableRepository classroomTimetableRepository) {
        this.eventMapper = eventMapper;
        this.groupTimetableRepository = groupTimetableRepository;
        this.classroomTimetableRepository = classroomTimetableRepository;
    }

    public List<Event> getGroupTimetableEvents(UUID groupTimetableId) {
        GroupTimetable groupTimetable = groupTimetableRepository.findGroupTimetableById(groupTimetableId);

        return groupTimetable.getEvents();
    }

    public List<EventDetailsDto> getGroupTimetableEvents(UUID groupId, LocalDate startDate, LocalDate endDate) {
        return groupTimetableRepository
                .findGroupTimetableByGroupIdAndStartDateAndEndDate(groupId, startDate, endDate)
                .map(groupTimetable -> groupTimetable.getEvents().stream()
                        .map(eventMapper::entityToDto)
                        .toList())
                .orElse(List.of());
    }


    public List<Event> getClassroomTimetableEvents(UUID classroomTimetableId) {
        ClassroomTimetable classroomTImetable = classroomTimetableRepository.findClassroomTimetableById(classroomTimetableId);

        return classroomTImetable.getEvents();
    }
}
