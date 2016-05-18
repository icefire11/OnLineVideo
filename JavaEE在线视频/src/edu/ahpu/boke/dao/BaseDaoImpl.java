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
	
	//注入Spring容器中的SessionFactory
	@Resource(name="sessionFactory")
	public void setSessionFactory4Spring(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void setEntityClass(Class<?> entityClass) {
		// TODO Auto-generated method stub
		this.entityClass=entityClass;
		
	}

	//直接调用Spring的Hibernate支持类的方法对实体对象做CRUD操作
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
	
	//使用注解压制类型安全警告
	@SuppressWarnings("unchecked")
	public T findById(Serializable id) {
		// TODO Auto-generated method stub
		//get(Class entityClass, Serializable id)：根据主键加载特定持久化类的实例
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
		// 为方便其他重载的方法调用此方法（提高代码复用性），设置了一个永远为真的条件。
		String hql="select o from "+entityClass.getSimpleName()+" o where 1=1 ";
		
		if(StringUtils.isNotBlank(whereHql)){
			hql=hql+whereHql;
			
		}
		//根据参数构造order by字符串
		String orderByStr=this.buildOrderBy(orderBy);
		
		hql=hql+orderByStr;
		
		final String _hql=hql;
		//excute方法的实参为匿名内部类的对象
		List<T> list=(List<T>)this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(_hql);//创建查询对象
				if(cacheable){//设置是否缓存查询结果
					 query.setCacheable(true);
					
				}
				setParams(query,params);//设置查询参数
				//调用query.list真正开始执行查询语句
				return query.list();
			}
		});
		
		
		 return list;
		 //使用Hibernate 查询数据库  返回的是实体类的
	}
	 protected void setParams(Query query, Object[] params) {
			// TODO Auto-generated method stub
		 if(params !=null &&params.length>0){
			 for(int i=0;i<params.length;i++){
				 query.setParameter(i, params[i]);
				 //这里params是数组长度  即如果 prams=object[] {userName} params.length=1
			 }
		 }
	 }
    //构造orderby字符串
	private String buildOrderBy(Map<String, String> orderBy) {
		// TODO Auto-generated method stub
		StringBuffer buf =new StringBuffer();
		if(orderBy!=null &&!orderBy.isEmpty()){
			buf.append(" order by ");
			//迭代排序条件
			for(Map.Entry<String, String> em :orderBy.entrySet()){
				buf.append(em.getKey()+" "+em.getValue()+",");
			}
			//去掉一个逗号
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
		//根据参数构造order by字符串
		String orderByStr=this.buildOrderBy(orderBy);
		
		hql=hql+orderByStr;
		
		final String _hql=hql;
		//excute方法的实参为匿名内部类的对象
		List<T> list=this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			@SuppressWarnings("unused")
			public Object doInHibernate(org.hibernate.Session session)throws HibernateException,
			                                                         SQLException{
				Query query=session.createQuery(_hql);//创建查询对象
				System.out.println("我在findByConditionWithPaging"+_hql);
			    
				query.setFirstResult(offset);//设置首条件返回结果的位置
				query.setMaxResults(length);//设置返回结果的最大数量
				setParams(query,params);//设置查询参数
				//调用query.list真正开始执行查询语句
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
		//excute方法的实参为匿名内部类的对象
		long count=this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(org.hibernate.Session session)throws HibernateException,
			                                                         SQLException{
				Query query=session.createQuery(_hql);//创建查询对象
				System.out.println("我在getRowCount"+_hql);
			    
				setParams(query,params);//设置查询参数
				//select count(*)的结果只有一个
				return query.uniqueResult();
			}
		});
		
	return  (int) count;
	}

}
