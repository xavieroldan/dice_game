package com.dicegame.control;

import com.dicegame.control.exceptions.IllegalOrphanException;
import com.dicegame.control.exceptions.NonexistentEntityException;
import com.dicegame.control.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.dicegame.models.DiceRolls;
import com.dicegame.models.Players;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class PlayersJpaController implements Serializable
{
    public PlayersJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(Players players) throws PreexistingEntityException, Exception
    {
        if (players.getDiceRollsList() == null)
        {
            players.setDiceRollsList(new ArrayList<DiceRolls>());
        }
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DiceRolls> attachedDiceRollsList = new ArrayList<DiceRolls>();
            for (DiceRolls diceRollsListDiceRollsToAttach : players.getDiceRollsList())
            {
                diceRollsListDiceRollsToAttach = em.getReference(diceRollsListDiceRollsToAttach.getClass(), diceRollsListDiceRollsToAttach.getIdRoll());
                attachedDiceRollsList.add(diceRollsListDiceRollsToAttach);
            }
            players.setDiceRollsList(attachedDiceRollsList);
            em.persist(players);
            for (DiceRolls diceRollsListDiceRolls : players.getDiceRollsList())
            {
                Players oldPlayersIdplayersOfDiceRollsListDiceRolls = diceRollsListDiceRolls.getPlayersIdplayers();
                diceRollsListDiceRolls.setPlayersIdplayers(players);
                diceRollsListDiceRolls = em.merge(diceRollsListDiceRolls);
                if (oldPlayersIdplayersOfDiceRollsListDiceRolls != null)
                {
                    oldPlayersIdplayersOfDiceRollsListDiceRolls.getDiceRollsList().remove(diceRollsListDiceRolls);
                    oldPlayersIdplayersOfDiceRollsListDiceRolls = em.merge(oldPlayersIdplayersOfDiceRollsListDiceRolls);
                }
            }
            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            if (findPlayers(players.getIdplayers()) != null)
            {
                throw new PreexistingEntityException("Players " + players + " already exists.", ex);
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

    public void edit(Players players) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Players persistentPlayers = em.find(Players.class, players.getIdplayers());
            List<DiceRolls> diceRollsListOld = persistentPlayers.getDiceRollsList();
            List<DiceRolls> diceRollsListNew = players.getDiceRollsList();
            List<String> illegalOrphanMessages = null;
            for (DiceRolls diceRollsListOldDiceRolls : diceRollsListOld)
            {
                if (!diceRollsListNew.contains(diceRollsListOldDiceRolls))
                {
                    if (illegalOrphanMessages == null)
                    {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DiceRolls " + diceRollsListOldDiceRolls + " since its playersIdplayers field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<DiceRolls> attachedDiceRollsListNew = new ArrayList<DiceRolls>();
            for (DiceRolls diceRollsListNewDiceRollsToAttach : diceRollsListNew)
            {
                diceRollsListNewDiceRollsToAttach = em.getReference(diceRollsListNewDiceRollsToAttach.getClass(), diceRollsListNewDiceRollsToAttach.getIdRoll());
                attachedDiceRollsListNew.add(diceRollsListNewDiceRollsToAttach);
            }
            diceRollsListNew = attachedDiceRollsListNew;
            players.setDiceRollsList(diceRollsListNew);
            players = em.merge(players);
            for (DiceRolls diceRollsListNewDiceRolls : diceRollsListNew)
            {
                if (!diceRollsListOld.contains(diceRollsListNewDiceRolls))
                {
                    Players oldPlayersIdplayersOfDiceRollsListNewDiceRolls = diceRollsListNewDiceRolls.getPlayersIdplayers();
                    diceRollsListNewDiceRolls.setPlayersIdplayers(players);
                    diceRollsListNewDiceRolls = em.merge(diceRollsListNewDiceRolls);
                    if (oldPlayersIdplayersOfDiceRollsListNewDiceRolls != null && !oldPlayersIdplayersOfDiceRollsListNewDiceRolls.equals(players))
                    {
                        oldPlayersIdplayersOfDiceRollsListNewDiceRolls.getDiceRollsList().remove(diceRollsListNewDiceRolls);
                        oldPlayersIdplayersOfDiceRollsListNewDiceRolls = em.merge(oldPlayersIdplayersOfDiceRollsListNewDiceRolls);
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
                String id = players.getIdplayers();
                if (findPlayers(id) == null)
                {
                    throw new NonexistentEntityException("The players with id " + id + " no longer exists.");
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

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Players players;
            try
            {
                players = em.getReference(Players.class, id);
                players.getIdplayers();
            }
            catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The players with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DiceRolls> diceRollsListOrphanCheck = players.getDiceRollsList();
            for (DiceRolls diceRollsListOrphanCheckDiceRolls : diceRollsListOrphanCheck)
            {
                if (illegalOrphanMessages == null)
                {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Players (" + players + ") cannot be destroyed since the DiceRolls " + diceRollsListOrphanCheckDiceRolls + " in its diceRollsList field has a non-nullable playersIdplayers field.");
            }
            if (illegalOrphanMessages != null)
            {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(players);
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

    public List<Players> findPlayersEntities()
    {
        return findPlayersEntities(true, -1, -1);
    }

    public List<Players> findPlayersEntities(int maxResults, int firstResult)
    {
        return findPlayersEntities(false, maxResults, firstResult);
    }

    private List<Players> findPlayersEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Players.class));
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

    public Players findPlayers(String id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Players.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getPlayersCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Players> rt = cq.from(Players.class);
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
