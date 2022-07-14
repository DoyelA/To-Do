package domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="task",schema = "public")
public class Tasks {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(allocationSize = 1,initialValue = 1,name="task_seq", schema = "public")
    @Column(name="task_id", nullable=false, unique=true)
    private Long id;

    @Column(name="task_description", nullable = false, length=200)
    private String description;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch=FetchType.EAGER)
    @JoinColumn(name="task_skills")
    private Set<Skill> taskSkills;

    @OneToOne(cascade = {CascadeType.MERGE},fetch=FetchType.EAGER)
    @JoinColumn(name="user_tasks")
    private User user;

    public Tasks() {
    }

    public Tasks(String description, Set<Skill> taskSkills, User user) {
        this.description = description;
        this.taskSkills = taskSkills;
        this.user = user;
    }

    public Tasks(Long id, String description, Set<Skill> taskSkills, User user) {
        this.id = id;
        this.description = description;
        this.taskSkills = taskSkills;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Skill> getTaskSkills() {
        return taskSkills;
    }

    public void setTaskSkills(Set<Skill> taskSkills) {
        this.taskSkills = taskSkills;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
