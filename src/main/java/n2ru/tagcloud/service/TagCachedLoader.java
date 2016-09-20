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
	
	private static final int CACHE_MINUTES = 10;
	
	@Autowired
	private TagDao tagDao;

	private LocalDateTime lastLoad = LocalDateTime.now().minusMinutes(CACHE_MINUTES);

	private List<Tag> cached = null;

	public List<Tag> getTagList() {
		LOG.debug("Getting tags...");
		if (cached ==null || cached.isEmpty()|| LocalDateTime.now().minusMinutes(CACHE_MINUTES).isAfter(lastLoad)) {
			LOG.info("Chache not presend or expired. Loading form DB");
			cached = tagDao.getTops();
			lastLoad = LocalDateTime.now();
			LOG.info("Fetched from DB, elements {}", cached!=null?cached.size():0);
		}
		return cached;
	}
}
