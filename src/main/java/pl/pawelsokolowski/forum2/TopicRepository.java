package pl.pawelsokolowski.forum2;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer> {

    @Query("select topic from Topic")
    List<String> findAllTopicNames();
}
