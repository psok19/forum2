package pl.pawelsokolowski.forum2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class TopicViewController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/topic/{id}")
    public String showConversation() {
        return "topic_view";
    }

    @ModelAttribute("posts")
    private Iterable<Post> findAllPostsByTopicId(@PathVariable("id") int id) {
        return postRepository.findAllPostsByTopicId(id);
    }

    @PostMapping("/topic/{id}/add_post")
    public ModelAndView addNewPost(@PathVariable("id") int topicId, @RequestParam("new_post") String newPostText) {
        Optional<Topic> topicOptional = topicRepository.findById(topicId);
        Topic topic = topicOptional.get();
        Post post = new Post();
        post.setTopic(topic);
        post.setText(newPostText);
        postRepository.save(post);

        return new ModelAndView("redirect:/topic/" + topicId);
    }


}
