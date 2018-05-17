package com.ssxt.dao;

 
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.ssxt.common.tools.Page;

 
  
 
/**
 * 定义了几种比较常用的方法
 * @author Administrator
 *
 */
public interface BaseDao<T> {
	/**
	 * 保存或更新实体，如果实体有ID则更新记录，否则将保存新的记录
	 * 
	 * @param entity
	 */
//	public void saveOrUpdate(T entity);

	/**
	 * 保存实体，这将在数据库中保存新的记录
	 * 
	 * @param entity
	 */
	//public void save(T entity);

	/**
	 * 删除实体
	 * 
	 * @param entity
	 */
//	public void delete(T entity);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	//public void update(T entity);

	/**
	 * 根据实体的类和ID获取实体
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	public T get(Class<T> clazz,int id);

	/**
	 * 根据HQL获取实体列表
	 * 
	 * @param hql
	 * @return 返回是一个ArrayList<T>的列表
	 */
	public List<T> getByHql(String hql);

	/**
	 * 分布获取实体
	 * 
	 * @param page
	 * @param countHql
	 * @param selHql
	 * @return 返回是一个分页实体，值存在于List<T> result中
	 */
	public Page<T> getByPage(Page<T> page,String countHql,String selHql);

	/**
	 * 根据实体类型和ID删除实体
	 * 
	 * @param clazz
	 * @param id
	 */
	//public void deleteById(Class<T> clazz,int id);

	/**
	 * 根据hql语句拿到对应的字符串字段
	 * @param hql
	 * @return
	 */
	public String getStringByHql(String hql);

	/**
	 * 根据hql语句拿到对应的整形字段
	 * @param hql
	 * @return
	 */
	public Integer getIntegerByHql(String hql);

	/**
	 * 根据实体类型和ID集合删除
	 * @param clazz
	 * @param ids
	 */
	//public void deleteByIds(Class<T> clazz,Collection<Integer> ids);

	/**
	 * 根据hql统计一些记录
	 * @param hql
	 * @return
	 */
	public long countRecord(String hql);

	/**
	 * 根据hql拿到一个List<Object[]>放在Page里面
	 * 如果countHql为空，则不统计记录数
	 * @param page
	 * @param countHql
	 * @param selHql
	 * @return 返回是一个分页实体，值存在于List result中
	 */
	@SuppressWarnings("unchecked")
	public Page getByObjectPage(Page page,String countHql,String selHql);
	
	/**
	 * 根据hql查询持久化对象
	 * @param hql
	 * @return
	 */
	public Object getObjectByHql(String hql);

	/**
	 * 根据hql查询返回一列对象列表
	 * @param hql
	 * @return 返回是一个ArrayList，根据集合常规方法取出其中的对象
	 */
	@SuppressWarnings("unchecked")
	public List getListByHql(String hql);

	/**
	 * 根据hql更新实体
	 * @param hql
	 */
	//public void updateByHql(String hql);

	/**
	 * 根据hql和多个参数读取一个列表
	 * @param hql
	 * @param paramNames
	 * @param paramValues
	 * @return 返回是一个ArrayList，根据集合常规方法取出其中的对象
	 */
	@SuppressWarnings("unchecked")
	public List getByParams(String hql, String[] paramNames,Object[] paramValues);
	
	/**用SQL实现分页
	 * 
	 * @param page
	 * @param countSql
	 * @param selSql
	 * @return
	 */
	public Page getPageBySQL(Page page,String countSql,String selSql);
	
	/**
	 * 用于使用SQL查询，返回具体的字段集
	 * @param sql
	 * @return 返回是一个ArrayList，根据集合常规方法取出其中的对象
	 */
	public List getListBySQL(String sql);
	
	/**根据SQL获取对象
	 * 
	 * @param sql
	 * @return
	 */
	public Object getObjectBySQL(String sql);
	
	/**用于SQL查询记录数
	 * 
	 * @param sql
	 * @return
	 */
	public long countSQLRecord(String sql);

	/**
	 * 拿到指定大小的list
	 * @param selHql
	 * @param size
	 * @return 返回是一个ArrayList，根据集合常规方法取出其中的对象
	 */
	public List<Object> getListLimitSize(String selHql , int size);
	
	
	
	
//	============================= 2012-09-17添加(RSun) ======================================
	
	/**
	 * 创建SqlQuery对象
	 * @param sql
	 * @return Query
	 */
	public SQLQuery createSqlQuery(String sql);
	
	
	/**
	 * 创建Query对象
	 * @param hql
	 * @return Query
	 */
	public Query createQuery(String hql);
	
	
	/**
	 * 添加实体对象
	 * @param T 实体对象
	 * @return  实体序列化id
	 */
	//public Serializable addEntity(T entity);
	
	
	/**
	 * 查询实体对象(get)
	 * @param clazz  实体类型
	 * @param id     实体id
	 * @return       实体对象
	 */
	public T getEntity(Class<T> clazz, Serializable id);

	
	/**
	 * 根据条件获取实体 <br/>
	 * 只返回一条记录，如果有多条则报错
	 * @param <T>
	 * @param clazz
	 * @param where 例如：where 1=1
	 * @return
	 * @throws DAOException
	 */
	public T getEntity(Class<T> clazz, String where);
	
	
	/**
	 * 执行hql返回一个想要的对象（允许自己new对象）<br>
	 * 例如： new com.cooguo.douwan.action.www.vo.SysMessageBean(tumr.id, tumr.addTime) <br>
	 * 多个请使用，queryAll(String hql, List<Object> list)
	 * @param hql
	 * @param list
	 * @return
	 */
	public Object getEntity(String hql, List<Object> list);
	
	
	/**
	 * 获取实体列表
	 * @param clazz 实体类型
	 * @return 实体列表 返回是一个ArrayList，根据集合常规方法取出其中的对象
	 */
	public List<T> queryAll(Class<T> clazz);

	
	/**
	 * 根据条件获取实体列表
	 * @param clazz 实体类型
	 * @param where 例如：where 1=1
	 * @return 实体列表 返回是一个ArrayList，根据集合常规方法取出其中的对象
	 */
	public List<T> queryAll(Class<T> clazz, String where);
	
	
	/**
	 * 获取结果集(允许自己new对象) <br>
	 * 例如： new com.cooguo.douwan.action.www.vo.SysMessageBean(tumr.id, tumr.addTime) <br>
	 * 1条结果集，请使用getEntity(String hql, List<Object> list)
	 * @param hql
	 * @param list 条件参数
	 * @return 返回是一个ArrayList，根据集合常规方法取出其中的对象
	 */
	public List<Object> queryAll(String hql, List<Object> list);
	
	
	/**
	 * 根据调节获取总记录数
	 * @param hql 数据库语句
	 * @param list 参数集(HQL拼接的？参数)
	 * @return int
	 */
	public int getTotalCounts(String hql, List<Object> list);
	
	
	/**
	 * 根据条件分页操作
	 * @param hql           数据库语句
	 * @param startPageNum  开始条数
	 * @param pageSize      每页显示条数
	 * @param list 			参数集(HQL拼接的？参数)
	 * @return              一页的数据 list
	 */
	public List<T> list(String hql, int startPageNum, int pageSize, List<Object> list);
	
	
	/**
	 * 根据条件动态删除数据
	 * @param clazz	 实体类
	 * @param whereName 条件的字段名
	 * @param whereValue 条件值
	 * @throws DAOException
	 */
	//public boolean delEntity(Class<T> clazz, Object[] whereName, Object[] whereValue);
	
	
	/**
	 * 动态修改单表字段值
	 * @param fieldName 要改的字段名(不允许null)
	 * @param fieldValue 要改字段的新值
	 * @throws DAOException
	 */
//	public boolean updateEntity(Class<T> clazz, Object[] fieldName, Object[] fieldValue);
	
	
	/**
	 * 动态修改单表字段值
	 * @param clazz	 实体类
	 * @param fieldName 要改的字段名(不允许null)
	 * @param fieldValue 要改字段的新值
	 * @param whereName 条件的字段名
	 * @param whereValue 条件值
	 * @throws DAOException
	 */
//	public boolean updateEntity(Class<T> clazz, Object[] fieldName,
		//	Object[] fieldValue, Object[] whereName, Object[] whereValue);
	
	public List<Object[]> getByJdbcSQL(String sql, String orderSql, int start, int pageSize, int length);
	
}
