package n2ru.tagcloud.service;

import java.util.List;

import n2ru.tagcloud.domain.Tag;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TagDao extends CrudRepository<Tag, Long> {

	// http://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.repositories
	@Query(value = "SELECT * from pligg_tag_cache limit 50", nativeQuery = true)
	public List<Tag> getTops();

}