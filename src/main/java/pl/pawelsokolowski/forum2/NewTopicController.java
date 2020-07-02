package pl.pawelsokolowski.forum2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewTopicController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private PostRepository postRepository;

    @PostMapping("/topic/add")
    public @ResponseBody String createNewTopic(@RequestParam String topic, @RequestParam("first_post") String post){
        Topic newTopic = new Topic();
        newTopic.setTopic(topic);
        Post newPost = new Post();
        newPost.setTopic(topic);
        newPost.setText(post);
        topicRepository.save(newTopic);
        postRepository.save(newPost);
        return "Created!";
    }

}
