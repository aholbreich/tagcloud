package n2ru.tagcloud.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="pligg_tag_cache")
public class Tag {

	@Id
	@Column(name = "tag_words")
	private String words;
	private Integer count;
	
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
