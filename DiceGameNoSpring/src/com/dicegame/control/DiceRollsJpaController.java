package com.dicegame.control;

import com.dicegame.exceptions.IllegalOrphanException;
import com.dicegame.exceptions.NonexistentEntityException;
import com.dicegame.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.dicegame.models.Players;
import com.dicegame.models.DiceResults;
import com.dicegame.models.DiceRolls;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class DiceRollsJpaController implements Serializable
{
    public DiceRollsJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(DiceRolls diceRolls) throws PreexistingEntityException, Exception
    {
        if (diceRolls.getDiceResultsList() == null)
        {
            diceRolls.setDiceResultsList(new ArrayList<DiceResults>());
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Players playersIdplayers = diceRolls.getPlayersIdplayers();
            if (playersIdplayers != null)
            {
                playersIdplayers = em.getReference(playersIdplayers.getClass(), playersIdplayers.getIdplayers());
                diceRolls.setPlayersIdplayers(playersIdplayers);
            }
            List<DiceResults> attachedDiceResultsList = new ArrayList<DiceResults>();
            for (DiceResults diceResultsListDiceResultsToAttach : diceRolls.getDiceResultsList())
            {
                diceResultsListDiceResultsToAttach = em.getReference(diceResultsListDiceResultsToAttach.getClass(), diceResultsListDiceResultsToAttach.getDiceResultsPK());
                attachedDiceResultsList.add(diceResultsListDiceResultsToAttach);
            }
            diceRolls.setDiceResultsList(attachedDiceResultsList);
            em.persist(diceRolls);
            if (playersIdplayers != null)
            {
                playersIdplayers.getDiceRollsList().add(diceRolls);
                playersIdplayers = em.merge(playersIdplayers);
            }
            for (DiceResults diceResultsListDiceResults : diceRolls.getDiceResultsList())
            {
                DiceRolls oldDiceRollsOfDiceResultsListDiceResults = diceResultsListDiceResults.getDiceRolls();
                diceResultsListDiceResults.setDiceRolls(diceRolls);
                diceResultsListDiceResults = em.merge(diceResultsListDiceResults);
                if (oldDiceRollsOfDiceResultsListDiceResults != null)
                {
                    oldDiceRollsOfDiceResultsListDiceResults.getDiceResultsList().remove(diceResultsListDiceResults);
                    oldDiceRollsOfDiceResultsListDiceResults = em.merge(oldDiceRollsOfDiceResultsListDiceResults);
                }
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            if (findDiceRolls(diceRolls.getIdRoll()) != null)
            {
                throw new PreexistingEntityException("DiceRolls " + diceRolls + " already exists.", ex);
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

    public void edit(DiceRolls diceRolls) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            DiceRolls persistentDiceRolls = em.find(DiceRolls.class, diceRolls.getIdRoll());
            Players playersIdplayersOld = persistentDiceRolls.getPlayersIdplayers();
            Players playersIdplayersNew = diceRolls.getPlayersIdplayers();
            List<DiceResults> diceResultsListOld = persistentDiceRolls.getDiceResultsList();
            List<DiceResults> diceResultsListNew = diceRolls.getDiceResultsList();
            List<String> illegalOrphanMessages = null;
            for (DiceResults diceResultsListOldDiceResults : diceResultsListOld)
            {
                if (!diceResultsListNew.contains(diceResultsListOldDiceResults))
                {
                    if (illegalOrphanMessages == null)
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DiceResults " + diceResultsListOldDiceResults + " since its diceRolls field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (playersIdplayersNew != null)
            {
                playersIdplayersNew = em.getReference(playersIdplayersNew.getClass(), playersIdplayersNew.getIdplayers());
                diceRolls.setPlayersIdplayers(playersIdplayersNew);
            }
            List<DiceResults> attachedDiceResultsListNew = new ArrayList<DiceResults>();
            for (DiceResults diceResultsListNewDiceResultsToAttach : diceResultsListNew)
            {
                diceResultsListNewDiceResultsToAttach = em.getReference(diceResultsListNewDiceResultsToAttach.getClass(), diceResultsListNewDiceResultsToAttach.getDiceResultsPK());
                attachedDiceResultsListNew.add(diceResultsListNewDiceResultsToAttach);
            }
            diceResultsListNew = attachedDiceResultsListNew;
            diceRolls.setDiceResultsList(diceResultsListNew);
            diceRolls = em.merge(diceRolls);
            if (playersIdplayersOld != null && !playersIdplayersOld.equals(playersIdplayersNew))
            {
                playersIdplayersOld.getDiceRollsList().remove(diceRolls);
                playersIdplayersOld = em.merge(playersIdplayersOld);
            }
            if (playersIdplayersNew != null && !playersIdplayersNew.equals(playersIdplayersOld))
            {
                playersIdplayersNew.getDiceRollsList().add(diceRolls);
                playersIdplayersNew = em.merge(playersIdplayersNew);
            }
            for (DiceResults diceResultsListNewDiceResults : diceResultsListNew)
            {
                if (!diceResultsListOld.contains(diceResultsListNewDiceResults))
                {
                    DiceRolls oldDiceRollsOfDiceResultsListNewDiceResults = diceResultsListNewDiceResults.getDiceRolls();
                    diceResultsListNewDiceResults.setDiceRolls(diceRolls);
                    diceResultsListNewDiceResults = em.merge(diceResultsListNewDiceResults);
                    if (oldDiceRollsOfDiceResultsListNewDiceResults != null && !oldDiceRollsOfDiceResultsListNewDiceResults.equals(diceRolls))
                    {
                        oldDiceRollsOfDiceResultsListNewDiceResults.getDiceResultsList().remove(diceResultsListNewDiceResults);
                        oldDiceRollsOfDiceResultsListNewDiceResults = em.merge(oldDiceRollsOfDiceResultsListNewDiceResults);
                    }
                }
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0)
            {
                String id = diceRolls.getIdRoll();
                if (findDiceRolls(id) == null)
                {
                    throw new NonexistentEntityException("The diceRolls with id " + id + " no longer exists.");
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            DiceRolls diceRolls;
            try
            {
                diceRolls = em.getReference(DiceRolls.class, id);
                diceRolls.getIdRoll();
            }
            catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The diceRolls with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DiceResults> diceResultsListOrphanCheck = diceRolls.getDiceResultsList();
            for (DiceResults diceResultsListOrphanCheckDiceResults : diceResultsListOrphanCheck)
            {
                if (illegalOrphanMessages == null)
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DiceRolls (" + diceRolls + ") cannot be destroyed since the DiceResults " + diceResultsListOrphanCheckDiceResults + " in its diceResultsList field has a non-nullable diceRolls field.");
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Players playersIdplayers = diceRolls.getPlayersIdplayers();
            if (playersIdplayers != null)
            {
                playersIdplayers.getDiceRollsList().remove(diceRolls);
                playersIdplayers = em.merge(playersIdplayers);
            }
            em.remove(diceRolls);
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

    public List<DiceRolls> findDiceRollsEntities()
    {
        return findDiceRollsEntities(true, -1, -1);
    }

    public List<DiceRolls> findDiceRollsEntities(int maxResults, int firstResult)
    {
        return findDiceRollsEntities(false, maxResults, firstResult);
    }

    private List<DiceRolls> findDiceRollsEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DiceRolls.class));
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

    public DiceRolls findDiceRolls(String id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(DiceRolls.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getDiceRollsCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DiceRolls> rt = cq.from(DiceRolls.class);
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
