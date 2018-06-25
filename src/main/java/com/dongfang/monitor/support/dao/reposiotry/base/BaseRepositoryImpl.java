package com.dongfang.monitor.support.dao.reposiotry.base;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * <br>
 *
 * @author Lucius
 * create by 2018/2/17
 * @Emial Lucius.li@ixiaoshuidi.com
 */
public class BaseRepositoryImpl<T, ID extends Serializable>
		extends SimpleJpaRepository<T, ID>
		implements BaseRepository<T, ID> {

	private final Class<T> domainClass;

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.domainClass = domainClass;
	}

	@Override
	public boolean support(String modelType) {
		return domainClass.getName().equals(modelType);
	}

}
