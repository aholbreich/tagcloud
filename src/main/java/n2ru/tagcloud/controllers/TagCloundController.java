package n2ru.tagcloud.controllers;

import java.util.List;

import n2ru.tagcloud.domain.Tag;
import n2ru.tagcloud.service.TagCachedLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TagCloundController {

	private static final int TAG_MAX_FONT_SIZE = 72;
	private static final int TAG_MIN_FONT_SIZE = 11;

	public static final Logger LOG = LoggerFactory.getLogger(TagCloundController.class);


    @Autowired
	private TagCachedLoader loader;
	
	@RequestMapping("/")
	public String getTagCloud(Model model) {
		
		List<Tag> tags = loader.getTagList();

		if(tags==null || tags.isEmpty()){
			LOG.warn("Loaded list was null, using dummy tag");
			tags.add(new Tag("Росссия", 10, 10)); //falback
		}
	
		Tag mostused= tags.stream().max((t1, t2) -> Integer.compare( t1.getCount(), t2.getCount())).get(); 
		
		int gap = TAG_MAX_FONT_SIZE - TAG_MIN_FONT_SIZE;
		LOG.debug("gap {}, most {}", gap, mostused.getCount());
		for(Tag tag :tags){
			double sizePlus = (((double)tag.getCount()/(double)mostused.getCount() )*(double)gap);
//			LOG.debug("coeff {} , Size plus {}", (tag.getCount()/mostused.getCount() ), sizePlus);
			tag.setFontsize(TAG_MIN_FONT_SIZE+(int)sizePlus);
		}
		
		model.addAttribute("name", "testname");
		model.addAttribute("tags_max_pts",36);
		model.addAttribute("tags",tags );
		return "cloud";
	}


}
