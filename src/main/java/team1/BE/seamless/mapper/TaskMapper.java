package team1.BE.seamless.mapper;

import org.springframework.stereotype.Component;
import team1.BE.seamless.DTO.TaskDTO.TaskCreate;
import team1.BE.seamless.DTO.TaskDTO.TaskDetail;
import team1.BE.seamless.DTO.TaskDTO.TaskUpdate;
import team1.BE.seamless.entity.MemberEntity;
import team1.BE.seamless.entity.ProjectEntity;
import team1.BE.seamless.entity.TaskEntity;
import team1.BE.seamless.util.Util;

@Component
public class TaskMapper {

    public TaskEntity toEntity(ProjectEntity project, MemberEntity member, TaskCreate taskCreate) {
        TaskEntity.Priority priorityEnum;

        try {
            priorityEnum = TaskEntity.Priority.valueOf(taskCreate.getPriority().name());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid priority value: " + taskCreate.getPriority());
        }

        return new TaskEntity(
            taskCreate.getName(),
            taskCreate.getDescription(),
            priorityEnum,
            project,
            member,
            taskCreate.getStartDate(),
            taskCreate.getEndDate(),
            taskCreate.getProgress());
    }

    public TaskEntity toUpdate(TaskEntity task, TaskUpdate update) {
        task.setName(Util.isNull(update.getName()) ? task.getName() : update.getName());
        task.setDescription(Util.isNull(update.getDescription()) ? task.getDescription() : update.getDescription());
        task.setProgress(Util.isNull(update.getProgress().toString()) ? task.getProgress()
            : update.getProgress());
        task.setStartDate(Util.isNull(update.getStartDate().toString()) ? task.getStartDate()
            : update.getStartDate());
        task.setEndDate(
            Util.isNull(update.getEndDate().toString()) ? task.getEndDate() : update.getEndDate());

        return task;
    }


    public TaskDetail toDetail(TaskEntity task) {
        return new TaskDetail(task.getId(), task.getName(), task.getDescription(), task.getOwner().getId(), task.getProgress(), task.getStartDate(), task.getEndDate());
    }
}
