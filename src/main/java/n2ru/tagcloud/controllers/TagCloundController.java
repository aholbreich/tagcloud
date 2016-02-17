package n2ru.tagcloud.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import n2ru.tagcloud.Application;
import n2ru.tagcloud.domain.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TagCloundController {

	private static final int TAG_MAX_FONT_SIZE = 36;
	private static final int TAG_MIN_FONT_SIZE = 10;


	public static final Logger LOG = LoggerFactory.getLogger(Application.class);

	 //TODO cache.
	
	@Resource
	private EntityManager em;
	
	@RequestMapping("/")
	public String getTagCloud(Model model) {
		LOG.info("Todo app");
		List<Tag> tags = em.createNativeQuery("SELECT * from pligg_tag_cache limit 100", Tag.class).getResultList();

		Tag mostused= tags.stream().max((t1, t2) -> Integer.compare( t1.getCount(), t2.getCount())).get(); 
		//Tag lessused= tags.stream().min((t1, t2) -> Integer.compare( t1.getCount(), t2.getCount())).get(); 
		int gap = TAG_MAX_FONT_SIZE - TAG_MIN_FONT_SIZE;
		
		for(Tag tag :tags){
			tag.setFontsize(TAG_MIN_FONT_SIZE + tag.getCount()/mostused.getCount()*gap);
		}
		
		model.addAttribute("name", "testname");
		model.addAttribute("tags_max_pts",TAG_MAX_FONT_SIZE);
		model.addAttribute("tags",tags );
		return "cloud";
	}


}
