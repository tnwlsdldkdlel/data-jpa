package study.datajpa.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class JpaBaseEntity {
	
	@Column(updatable = false)
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	@PrePersist
	public void prePersist() {
		LocalDateTime now = LocalDateTime.now();
		this.createdDate = now;
		this.updatedDate = now;
	}
	
	@PreUpdate
	public void preUpdate() {
		this.updatedDate = LocalDateTime.now();
	}

}
