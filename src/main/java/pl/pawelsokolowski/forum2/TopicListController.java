package pl.pawelsokolowski.forum2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
public class TopicListController {
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping(value={"/", "/topic_list"})
    public String getTopicList() {
        return "topic_list";
    }

    @ModelAttribute("topics")
    private Iterable<Topic> addTopicsModelAttribute() {
        return topicRepository.findAll();
    }


}
