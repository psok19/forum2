package pl.pawelsokolowski.forum2;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="topic_id")
    private Integer topicId;

    private String topic;

    @OneToMany(mappedBy = "topic")
    private Set<Post> posts = new HashSet<>();

    public Integer getId() {
        return topicId;
    }

    public void setId(Integer id) {
        this.topicId = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
