package study.datajpa.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Item implements Persistable<String> {
	
	@Id
	private String id;
	
	@CreatedDate
	private LocalDateTime createDate;

	@Override
	public boolean isNew() {
		return createDate == null;
	}

	@Override
	public String getId() {
		return this.id;
	}

}
