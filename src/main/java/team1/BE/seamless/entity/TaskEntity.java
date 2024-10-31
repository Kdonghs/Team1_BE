package team1.BE.seamless.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity(name = "taskss")
public class TaskEntity {

    public TaskEntity(String name, String description, Priority priorityEnum, ProjectEntity project, MemberEntity member, LocalDateTime startDate, LocalDateTime endDate, Integer progress) {
        this.name = name;
        this.description = description;
        this.progress = progress;
        this.status = 0;
        this.priority = priorityEnum;
        this.isDeleted = false;
        this.projectEntity = project;
        this.owner = member;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    public TaskEntity() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "progress")
    private Integer progress = 0;

    @Column(name = "status")
    private Integer status = 0;

    @Column(name = "priority")
    private Priority priority;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private ProjectEntity projectEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity owner;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getProgress() {
        return progress;
    }

    public Integer getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public ProjectEntity getProject() {
        return projectEntity;
    }

    public MemberEntity getOwner() {
        return owner;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setOwner(MemberEntity owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
