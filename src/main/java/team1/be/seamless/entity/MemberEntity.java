package team1.be.seamless.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "memberss")
public class MemberEntity extends BaseEntity {

    public MemberEntity() {

    }

    public MemberEntity(ProjectEntity project) {
        this.projectEntity = project;
    }

    public MemberEntity(String name, String role, String email, String imageURL,
                        ProjectEntity projectEntity) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.imageURL = imageURL;
        this.projectEntity = projectEntity;
        this.isDelete = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guest_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "is_delete")
    private Boolean isDelete;

    @Column(name = "role")
    private String role;

    @Column(name = "name")
    private String name;

    @Column(name = "imageURL")
    private String imageURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectEntity projectEntity;

    @OneToMany(mappedBy = "owner")
    private List<TaskEntity> taskEntities = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public List<TaskEntity> getTaskEntities() {
        return taskEntities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
