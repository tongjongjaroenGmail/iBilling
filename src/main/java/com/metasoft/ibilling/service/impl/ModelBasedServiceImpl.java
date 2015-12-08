/**
 * 
 */
package com.metasoft.ibilling.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDao;
import com.metasoft.ibilling.model.BaseModel;
import com.metasoft.ibilling.service.ModelBasedService;

/**
 * @author 
 * 
 */
public abstract class ModelBasedServiceImpl<T, E extends BaseModel, I extends Serializable> extends BaseServiceImpl implements ModelBasedService<T, E, I>
{
    protected AbstractDao<E, I> abstractDao;

    protected ModelBasedServiceImpl(AbstractDao<E, I> abstractDao)
    {
        this.abstractDao = abstractDao;
    }

    public E findById(I id)
    {
        return abstractDao.findById(id);
    }

    public List<E> findByIds(I[] ids)
    {
        return abstractDao.findByIds(ids);
    }

    public List<E> findAll()
    {
        return abstractDao.findAll();
    }

    public I save(E e)
    {
        return abstractDao.save(e);
    }

    public I saveOrUpdate(E e)
    {
        return abstractDao.saveOrUpdate(e);
    }

    public void delete(E e)
    {
        abstractDao.delete(e);
    }

    public boolean deleteById(I id)
    {
        return abstractDao.deleteById(id);
    }
}
