package team1.be.seamless.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity(name = "optionss")
public class OptionEntity extends BaseEntity {

    public OptionEntity() {

    }

    public OptionEntity(String name, String description, String optionType) {
        this.name = name;
        this.description = description;
        this.optionType = optionType;
        this.isDeleted = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "option_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "option_type")
    private String optionType;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "optionEntity", cascade = CascadeType.ALL)
    private List<ProjectOptionEntity> options;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOptionType() {
        return optionType;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public List<ProjectOptionEntity> getOptions() {
        return options;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setOptions(List<ProjectOptionEntity> options) {
        this.options = options;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public OptionEntity Update(String name, String description, String optionType) {
        this.name = name;
        this.description = description;
        this.optionType = optionType;
        return this;
    }

}
