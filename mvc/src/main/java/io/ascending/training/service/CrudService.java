
package io.ascending.training.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Optional;

public abstract class CrudService<T, ID extends Serializable> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext 
	protected EntityManager em; 

	protected abstract CrudRepository<T, ID> getCrudRepository();

	protected String getLogName() {
		return getClass().getSimpleName();
	}

	@Transactional
	public <S extends T> S save(S entity) {
		return getCrudRepository().save(entity);
	}

	@Transactional
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		return getCrudRepository().saveAll(entities);
	}

	@Transactional(readOnly = true)
	public Optional<T> findById(ID id) {
		return getCrudRepository().findById(id);
	}

	@Transactional(readOnly = true)
	public boolean exists(ID id) {
		return getCrudRepository().existsById(id);
	}

	@Transactional(readOnly = true)
	public Iterable<T> findAll() {
		return getCrudRepository().findAll();
	}

	@Transactional(readOnly = true)
	public long count() {
		return getCrudRepository().count();
	}

	@Transactional
	public void delete(ID id) {
		getCrudRepository().deleteById(id);
	}

	@Transactional
	public void delete(T entity) {
		getCrudRepository().delete(entity);
	}

	@Transactional
	public void delete(Iterable<? extends T> entities) {
		getCrudRepository().deleteAll(entities);
	}

	@Transactional
	public void deleteAll() {
		getCrudRepository().deleteAll();
	}
        
        public void flush() {
            em.flush();
        }
}
