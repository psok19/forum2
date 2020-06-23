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

    @PostMapping(path="/add")
    public @ResponseBody String addNewPost(@RequestParam String topic, @RequestParam String text){
        Post newPost = new Post();
        newPost.setTopic(topic);
        newPost.setText(text);

        postRepository.save(newPost);
        return "Saved!";
    }
}
