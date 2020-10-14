package pl.pawelsokolowski.forum2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class TopicViewController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/topic/{id}")
    @ModelAttribute
    public String showConversation(@PathVariable int id, Model model){
        Iterable<Post> posts = postRepository.findAllPostsByTopicId(id);
        model.addAttribute("posts", posts);
        return "topic_view";
    }

//    @ModelAttribute("posts")
//    private Iterable<Post> findAllPostsByTopicId(int topicId){
//        return postRepository.findAllPostsByTopicId(topicId);
//    }
//
//    @ModelAttribute("topic")
//    private Topic findTopicById(int topicId){
//        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
//        return optionalTopic.isPresent() ? optionalTopic.get() : new Topic();
//    }

//    @ModelAttribute("topic_id")
//    private int setModelAttributeTopicId(){
//        return topicId;
//    }



//    @PostMapping("/topic/{id}/add_post")


}
