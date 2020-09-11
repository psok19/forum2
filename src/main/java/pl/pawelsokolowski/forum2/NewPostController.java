package pl.pawelsokolowski.forum2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewPostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired TopicRepository topicRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewPost(@RequestParam String topic, @RequestParam String text){ //todo issue1
        Post newPost = new Post();
        Topic newTopic = new Topic();
        newTopic.setTopic(topic);
        newPost.setTopic(newTopic);
        newPost.setText(text);
        topicRepository.save(newTopic);
        postRepository.save(newPost);
        return "Saved!";  //todo issue1
    }
}
