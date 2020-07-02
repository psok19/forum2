package pl.pawelsokolowski.forum2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TopicListController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String getTopicList() {
        return "topic_list";
    }

    @ModelAttribute("topics")
    private List<String> getRawTopics() {

        return postRepository.findDistinctTopic();
    }

}
