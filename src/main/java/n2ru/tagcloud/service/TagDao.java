package n2ru.tagcloud.service;

import java.util.List;

import n2ru.tagcloud.domain.Tag;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TagDao extends CrudRepository<Tag, Long> {

	// http://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.repositories
	//@Query(value = "SELECT * from pligg_tag_cache limit 60", nativeQuery = true)
	
	//select tag_words, count(DISTINCT link_id) as count 
	//FROM pligg_tags , pligg_links .  
	//WHERE tag_lang='ru' and link_id = tag_link_id and (link_status='published' OR link_status='queued')  order by count desc limit
	@Query(value = "SELECT tag_words, count(DISTINCT link_id) as count "
			+ "FROM pligg_tags , pligg_links "
			+ "WHERE tag_lang='ru' and link_id = tag_link_id and (link_status='published' OR link_status='queued') "
			+ "GROUP BY tag_words "
			+ "ORDER BY count desc limit 60", nativeQuery = true)
	public List<Tag> getTops();

}