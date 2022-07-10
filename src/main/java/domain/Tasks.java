package domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="task",schema = "public")
public class Tasks {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(allocationSize = 1,initialValue = 1,name="taask_seq", schema = "public")
    @Column(name="task_id", nullable=false, unique=true)
    private Long id;
    @Column(name="task_description", nullable = false, length=200)
    private String description;
    @OneToOne(cascade = {CascadeType.MERGE},fetch=FetchType.EAGER)
    @JoinColumn(name="user_tasks")
    private User user;
}
