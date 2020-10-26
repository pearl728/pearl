package com.ssh.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ssh.dao.BaseDao;
import com.ssh.domain.LinkMan;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class clazz;

	public BaseDaoImpl() {
		//����:��һ��,��Ҫ��ȡ�� Class
		Class clazz = this.getClass();//���ڱ����õ��Ǹ���� class(����)
		
		//�鿴 JKD �� API
		Type type = clazz.getGenericSuperclass();//��ȡ����������
		
		//�õ������ type ����һ������������,�� type ǿתΪ����������
		ParameterizedType pType = (ParameterizedType) type;
		
		//ͨ�����������ͻ��ʵ�����Ͳ���:�õ�һ��ʵ�����Ͳ���������?Map<String,Integer>
		Type[] types = pType.getActualTypeArguments();
		
		//ֻ��õ�һ��ʵ�����Ͳ�������
		this.clazz = (Class) types[0];
	}
	
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}
	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}
	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}
	
	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}
	
	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	@Override
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
	}

	

}