package edu.ahpu.boke.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.ahpu.boke.util.GenericClass;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class<?> entityClass=GenericClass.getGenericClass(this.getClass());
	
	//ע��Spring�����е�SessionFactory
	@Resource(name="sessionFactory")
	public void setSessionFactory4Spring(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void setEntityClass(Class<?> entityClass) {
		// TODO Auto-generated method stub
		this.entityClass=entityClass;
		
	}

	//ֱ�ӵ���Spring��Hibernate֧����ķ�����ʵ�������CRUD����
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(entity);
		
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(entity);
		
	}
	
	//ʹ��ע��ѹ�����Ͱ�ȫ����
	@SuppressWarnings("unchecked")
	public T findById(Serializable id) {
		// TODO Auto-generated method stub
		//get(Class entityClass, Serializable id)���������������ض��־û����ʵ��
		return (T)this.getHibernateTemplate().get(entityClass, id);
	}


	public void deleteByIds(Serializable... ids) {
		// TODO Auto-generated method stub
		if(ids!=null&&ids.length>0){
			for(Serializable id:ids){
				Object entity=this.getHibernateTemplate().get(entityClass, id);
				if(entity!=null){
					this.getHibernateTemplate().delete(entity);
				}
			}
			
		}
		
	}

	
	public void deleteAll(Collection<T> entities) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().deleteAll(entities);
	}

	@SuppressWarnings({"rawtypes","unchecked"})
	public List<T> findByCondition(String whereHql, final Object[] params,
			Map<String, String> orderBy, final boolean cacheable) {
		// Ϊ�����������صķ������ô˷�������ߴ��븴���ԣ���������һ����ԶΪ���������
		String hql="select o from "+entityClass.getSimpleName()+" o where 1=1 ";
		
		if(StringUtils.isNotBlank(whereHql)){
			hql=hql+whereHql;
			
		}
		//���ݲ�������order by�ַ���
		String orderByStr=this.buildOrderBy(orderBy);
		
		hql=hql+orderByStr;
		
		final String _hql=hql;
		//excute������ʵ��Ϊ�����ڲ���Ķ���
		List<T> list=(List<T>)this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(_hql);//������ѯ����
				if(cacheable){//�����Ƿ񻺴��ѯ���
					 query.setCacheable(true);
					
				}
				setParams(query,params);//���ò�ѯ����
				//����query.list������ʼִ�в�ѯ���
				return query.list();
			}
		});
		
		
		 return list;
		 //ʹ��Hibernate ��ѯ���ݿ�  ���ص���ʵ�����
	}
	 protected void setParams(Query query, Object[] params) {
			// TODO Auto-generated method stub
		 if(params !=null &&params.length>0){
			 for(int i=0;i<params.length;i++){
				 query.setParameter(i, params[i]);
				 //����params�����鳤��  ����� prams=object[] {userName} params.length=1
			 }
		 }
	 }
    //����orderby�ַ���
	private String buildOrderBy(Map<String, String> orderBy) {
		// TODO Auto-generated method stub
		StringBuffer buf =new StringBuffer();
		if(orderBy!=null &&!orderBy.isEmpty()){
			buf.append(" order by ");
			//������������
			for(Map.Entry<String, String> em :orderBy.entrySet()){
				buf.append(em.getKey()+" "+em.getValue()+",");
			}
			//ȥ��һ������
			buf.deleteCharAt(buf.length()-1);
		}
		return buf.toString();
	}
	

	@Override
	public List<T> findByCondition(String whereHql, Object[] params,
			boolean cacheable) {
		// TODO Auto-generated method stub
		return this.findByCondition(whereHql, params,null, cacheable);
	}

	@Override
	public List<T> findByCondition(Map<String, String> orderBy,
			boolean cacheable) {
		// TODO Auto-generated method stub
		return this.findByCondition(null, null, orderBy,cacheable);
	}

	@Override
	public List<T> findAll(boolean cacheable) {
		// TODO Auto-generated method stub
		return this.findByCondition(null, null, null ,cacheable);
	}


	@Override
	public T findFirstByCondition(String whereHql, Object[] params,
			boolean cacheable) {
		// TODO Auto-generated method stub
		List<T> list=this.findByCondition(whereHql, params, cacheable);
		if(list !=null &&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings({"rawtypes","unchecked"})
	public List<T> findByConditionWithPaging(String whereHql, final Object[] params,
			Map<String, String> orderBy, final int offset, final int length) {
		// TODO Auto-generated method stub
		String hql="select o from "+entityClass.getSimpleName()+" o where 1=1 ";
		if(StringUtils.isNotBlank(whereHql)){
			hql=hql+whereHql;
			
		}
		//���ݲ�������order by�ַ���
		String orderByStr=this.buildOrderBy(orderBy);
		
		hql=hql+orderByStr;
		
		final String _hql=hql;
		//excute������ʵ��Ϊ�����ڲ���Ķ���
		List<T> list=this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			@SuppressWarnings("unused")
			public Object doInHibernate(org.hibernate.Session session)throws HibernateException,
			                                                         SQLException{
				Query query=session.createQuery(_hql);//������ѯ����
				System.out.println("����findByConditionWithPaging"+_hql);
			    
				query.setFirstResult(offset);//�������������ؽ����λ��
				query.setMaxResults(length);//���÷��ؽ�����������
				setParams(query,params);//���ò�ѯ����
				//����query.list������ʼִ�в�ѯ���
				return query.list();
			}
		});
		
		
		 return list;
}

	@SuppressWarnings({"rawtypes","unchecked"})
	public int getRowCount(String whereHql, final Object[] params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+entityClass.getSimpleName()+" o where 1=1 ";
		if(StringUtils.isNotBlank(whereHql)){
			hql=hql+whereHql;
			
		}	
		final String _hql=hql;
		//excute������ʵ��Ϊ�����ڲ���Ķ���
		long count=this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(org.hibernate.Session session)throws HibernateException,
			                                                         SQLException{
				Query query=session.createQuery(_hql);//������ѯ����
				System.out.println("����getRowCount"+_hql);
			    
				setParams(query,params);//���ò�ѯ����
				//select count(*)�Ľ��ֻ��һ��
				return query.uniqueResult();
			}
		});
		
	return  (int) count;
	}

}
