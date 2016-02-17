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

	private static final int TAG_MAX_FONT_SIZE = 72;
	private static final int TAG_MIN_FONT_SIZE = 11;


	public static final Logger LOG = LoggerFactory.getLogger(Application.class);

	 //TODO cache.
	
	@Resource
	private EntityManager em;
	
	@RequestMapping("/")
	public String getTagCloud(Model model) {
		LOG.info("Todo app");
		List<Tag> tags = em.createNativeQuery("SELECT * from pligg_tag_cache limit 50", Tag.class).getResultList();

		Tag mostused= tags.stream().max((t1, t2) -> Integer.compare( t1.getCount(), t2.getCount())).get(); 
		//Tag lessused= tags.stream().min((t1, t2) -> Integer.compare( t1.getCount(), t2.getCount())).get(); 
		int gap = TAG_MAX_FONT_SIZE - TAG_MIN_FONT_SIZE;
		LOG.info("gap {}, most {}", gap, mostused.getCount());
		for(Tag tag :tags){
			double sizePlus = (((double)tag.getCount()/(double)mostused.getCount() )*(double)gap);
			LOG.info("coeff {} , Size plus {}", (tag.getCount()/mostused.getCount() ), sizePlus);
			tag.setFontsize(TAG_MIN_FONT_SIZE+(int)sizePlus);
		}
		
		model.addAttribute("name", "testname");
		model.addAttribute("tags_max_pts",36);
		model.addAttribute("tags",tags );
		return "cloud";
	}


}
