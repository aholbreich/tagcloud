package n2ru.tagcloud.service;

import java.time.LocalDateTime;
import java.util.List;

import n2ru.tagcloud.domain.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagCachedLoader {

	public static final Logger LOG = LoggerFactory.getLogger(TagCachedLoader.class);
	
	@Autowired
	private TagDao tagDao;

	private LocalDateTime lastLoad = LocalDateTime.now().minusDays(10);

	private List<Tag> cached = null;

	public List<Tag> getTagList() {

		if (LocalDateTime.now().minusMinutes(1).isAfter(lastLoad)) {
			cached = tagDao.getTops();
			lastLoad = LocalDateTime.now();
			LOG.debug("Reload form DB");
		}
		return cached;
	}
}
