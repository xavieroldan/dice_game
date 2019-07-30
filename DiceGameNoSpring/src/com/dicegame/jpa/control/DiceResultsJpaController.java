
package com.dicegame.jpa.control;

import com.dicegame.jpa.exceptions.NonexistentEntityException;
import com.dicegame.jpa.exceptions.PreexistingEntityException;
import com.dicegame.models.DiceResults;
import com.dicegame.models.DiceResultsPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.dicegame.models.DiceRolls;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class DiceResultsJpaController implements Serializable {
    public DiceResultsJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(DiceResults diceResults) throws PreexistingEntityException, Exception
    {
        if (diceResults.getDiceResultsPK() == null)
        {
            diceResults.setDiceResultsPK(new DiceResultsPK());
        }
        diceResults.getDiceResultsPK().setDiceRollsIdRoll(diceResults.getDiceRolls().getIdRoll());
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            DiceRolls diceRolls = diceResults.getDiceRolls();
            if (diceRolls != null)
            {
                diceRolls = em.getReference(diceRolls.getClass(), diceRolls.getIdRoll());
                diceResults.setDiceRolls(diceRolls);
            }
            em.persist(diceResults);
            if (diceRolls != null)
            {
                diceRolls.getDiceResultsList().add(diceResults);
                diceRolls = em.merge(diceRolls);
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            if (findDiceResults(diceResults.getDiceResultsPK()) != null)
            {
                throw new PreexistingEntityException("DiceResults " + diceResults + " already exists.", ex);
            }
            throw ex;
        }
        finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public void edit(DiceResults diceResults) throws NonexistentEntityException, Exception
    {
        diceResults.getDiceResultsPK().setDiceRollsIdRoll(diceResults.getDiceRolls().getIdRoll());
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            DiceResults persistentDiceResults = em.find(DiceResults.class, diceResults.getDiceResultsPK());
            DiceRolls diceRollsOld = persistentDiceResults.getDiceRolls();
            DiceRolls diceRollsNew = diceResults.getDiceRolls();
            if (diceRollsNew != null)
            {
                diceRollsNew = em.getReference(diceRollsNew.getClass(), diceRollsNew.getIdRoll());
                diceResults.setDiceRolls(diceRollsNew);
            }
            diceResults = em.merge(diceResults);
            if (diceRollsOld != null && !diceRollsOld.equals(diceRollsNew))
            {
                diceRollsOld.getDiceResultsList().remove(diceResults);
                diceRollsOld = em.merge(diceRollsOld);
            }
            if (diceRollsNew != null && !diceRollsNew.equals(diceRollsOld))
            {
                diceRollsNew.getDiceResultsList().add(diceResults);
                diceRollsNew = em.merge(diceRollsNew);
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0)
            {
                DiceResultsPK id = diceResults.getDiceResultsPK();
                if (findDiceResults(id) == null)
                {
                    throw new NonexistentEntityException("The diceResults with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
        finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public void destroy(DiceResultsPK id) throws NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            DiceResults diceResults;
            try
            {
                diceResults = em.getReference(DiceResults.class, id);
                diceResults.getDiceResultsPK();
            }
            catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The diceResults with id " + id + " no longer exists.", enfe);
            }
            DiceRolls diceRolls = diceResults.getDiceRolls();
            if (diceRolls != null)
            {
                diceRolls.getDiceResultsList().remove(diceResults);
                diceRolls = em.merge(diceRolls);
            }
            em.remove(diceResults);
            em.getTransaction().commit();
        }
        finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public List<DiceResults> findDiceResultsEntities()
    {
        return findDiceResultsEntities(true, -1, -1);
    }

    public List<DiceResults> findDiceResultsEntities(int maxResults, int firstResult)
    {
        return findDiceResultsEntities(false, maxResults, firstResult);
    }

    private List<DiceResults> findDiceResultsEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DiceResults.class));
            Query q = em.createQuery(cq);
            if (!all)
            {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        }
        finally
        {
            em.close();
        }
    }

    public DiceResults findDiceResults(DiceResultsPK id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(DiceResults.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getDiceResultsCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DiceResults> rt = cq.from(DiceResults.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        }
        finally
        {
            em.close();
        }
    }

}
