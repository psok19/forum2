package pl.pawelsokolowski.forum2;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {

    @Query(value = "SELECT * FROM post WHERE topic_id = ?1", nativeQuery = true)
    Iterable<Post> findAllPostsByTopicId(int topicId);

//    @Query("SELECT DISTINCT topic FROM Post")
//    List<String> findDistinctTopic();

}
