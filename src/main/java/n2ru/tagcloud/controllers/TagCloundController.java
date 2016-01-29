package n2ru.tagcloud.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import n2ru.tagcloud.Application;
import n2ru.tagcloud.domain.Tag;

@Controller
public class TagCloundController {

	private static final int TAG_MAX_FONT_SIZE = 36;


	public static final Logger LOG = LoggerFactory.getLogger(Application.class);

	
	
	@Resource
	private EntityManager em;
	
	@RequestMapping("/")
	public String getTagCloud(Model model) {
		LOG.info("Todo app");
		List<Tag> tags = em.createNativeQuery("SELECT * from pligg_tag_cache limit 100", Tag.class).getResultList();
		model.addAttribute("name", "testname");
		model.addAttribute("tags_max_pts",TAG_MAX_FONT_SIZE);
		model.addAttribute("tags",tags );
		return "cloud";
	}
}
