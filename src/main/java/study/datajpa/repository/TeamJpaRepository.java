package study.datajpa.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import study.datajpa.domain.Team;

@Repository
@RequiredArgsConstructor
public class TeamJpaRepository {

	private final EntityManager em;
	
	public Team save(Team team) {
		em.persist(team);
		
		return team;
	}
	
	public void delect(Team team) {
		em.remove(team);
	}
	
	public List<Team> findAll() {
		return em.createQuery("select t from Team t",Team.class).getResultList();
	}
	
	public Optional<Team> findById(Long id) {
		Team team = em.find(Team.class, id);
		
		return Optional.ofNullable(team);
	}
	
	public Long count() {
		return em.createQuery("select count(t) from Team",Long.class).getSingleResult();
	}
}
