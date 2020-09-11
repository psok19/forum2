package pl.pawelsokolowski.forum2;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {

//    @Query("SELECT DISTINCT topic FROM Post")
//    List<String> findDistinctTopic();

}
