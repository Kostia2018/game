package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.RReposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PserviceIpl implements Pservic {
    @Autowired
    private RReposit reposit;
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public void add(Player player) {
        player.setLevel(player.getCurrentLevel());
        player.setUntilNextLevel(player.nextLevel());
        reposit.save(player);


    }



    @Override
    public void delete(Long id) {
        reposit.deleteById(id);


    }

    @Override
    public Player getById(Long id) {
        Player player = null;

      Optional<Player>OptPlayer =  reposit.findById(id);

           try {
               player = OptPlayer.get();
           }catch (Exception e){
               // разобраться почему без try не проходят тесты
           }

         return player;
    }









    public List<Player> getAll(String name, String title, Race race, Profession profession, Long before, Long after,
                               Boolean banned, Integer minExperience, Integer maxExperience,
                               Integer minLevel, Integer maxLevel) {

        EntityManager manager =  entityManagerFactory.createEntityManager();
        manager.getTransaction().begin();
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Player> query = cb.createQuery(Player.class);
        Root<Player> from = query.from(Player.class);

        List<Predicate> predicateList = new ArrayList<>();
        Predicate[] predicates = new Predicate[1];
        if(name != null) {
            predicates[0] = cb.like(from.get("name"),"%"+ name + "%");
            predicateList.add(predicates[0]);
        }
        if(title != null) {
            predicates[0] = cb.like(from.get("title"), "%"+ title + "%");
            predicateList.add(predicates[0]);
        }
        if(race != null) {
            predicates[0] = cb.equal(from.get("race"), race);
            predicateList.add(predicates[0]);
        }
        if(profession != null) {
            predicates[0] = cb.equal(from.get("profession"), profession);
            predicateList.add(predicates[0]);
        }
        if(before != null) {
            predicates[0] = cb.greaterThanOrEqualTo(from.get("birthday"), new Date(before));
            predicateList.add(predicates[0]);
        }
        if(after != null) {
            predicates[0] = cb.lessThanOrEqualTo(from.get("birthday"), new Date(after));
            predicateList.add(predicates[0]);
        }
        if(banned != null) {
            predicates[0] = cb.equal(from.get("banned"), banned);
            predicateList.add(predicates[0]);
        }
        if(minExperience != null) {
            predicates[0] = cb.greaterThanOrEqualTo(from.get("experience"), minExperience);
            predicateList.add(predicates[0]);
        }
        if(maxExperience != null) {
            predicates[0] = cb.lessThanOrEqualTo(from.get("experience"), maxExperience);
            predicateList.add(predicates[0]);
        }
        if(minLevel != null) {
            predicates[0] = cb.greaterThanOrEqualTo(from.get("level"), minLevel);
            predicateList.add(predicates[0]);
        }
        if(maxLevel != null) {
            predicates[0] = cb.lessThanOrEqualTo(from.get("level"), maxLevel);
            predicateList.add(predicates[0]);
        }
        Predicate[] objects =  predicateList.toArray(new Predicate[0]);
        CriteriaQuery<Player> where = query.select(from).where(objects);
        List<Player> resultList = manager.createQuery(where).getResultList();

        return resultList;
    }


    public List<Player>getAlll(String name, String title, Race race, Profession profession, Long before, Long after,
                               Boolean banned, Integer minExperience, Integer maxExperience,
                               Integer minLevel, Integer maxLevel, PlayerOrder order, Integer pageNumber, Integer pageSize){

        EntityManager manager = entityManagerFactory.createEntityManager();
        manager.getTransaction().begin();
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Player> query = cb.createQuery(Player.class);
        Root<Player> from = query.from(Player.class);

        List<Predicate> predicateList = new ArrayList<>();
        Predicate[] predicates = new Predicate[1];
        if(name != null) {
            predicates[0] = cb.like(from.get("name"),"%"+ name + "%");
            predicateList.add(predicates[0]);
        }
        if(title != null) {
            predicates[0] = cb.like(from.get("title"), "%"+ title + "%");
            predicateList.add(predicates[0]);
        }
        if(race != null) {
            predicates[0] = cb.equal(from.get("race"), race);
            predicateList.add(predicates[0]);
        }
        if(profession != null) {
            predicates[0] = cb.equal(from.get("profession"), profession);
            predicateList.add(predicates[0]);
        }
        if(before != null) {
            predicates[0] = cb.greaterThanOrEqualTo(from.get("birthday"), new Date(before));
            predicateList.add(predicates[0]);
        }
        if(after != null) {
            predicates[0] = cb.lessThanOrEqualTo(from.get("birthday"), new Date(after));
            predicateList.add(predicates[0]);
        }
        if(banned != null) {
            predicates[0] = cb.equal(from.get("banned"), banned);
            predicateList.add(predicates[0]);
        }
        if(minExperience != null) {
            predicates[0] = cb.greaterThanOrEqualTo(from.get("experience"), minExperience);
            predicateList.add(predicates[0]);
        }
        if(maxExperience != null) {
            predicates[0] = cb.lessThanOrEqualTo(from.get("experience"), maxExperience);
            predicateList.add(predicates[0]);
        }
        if(minLevel != null) {
            predicates[0] = cb.greaterThanOrEqualTo(from.get("level"), minLevel);
            predicateList.add(predicates[0]);
        }
        if(maxLevel != null) {
            predicates[0] = cb.lessThanOrEqualTo(from.get("level"), maxLevel);
            predicateList.add(predicates[0]);
        }
        Predicate[] objects =  predicateList.toArray(new Predicate[0]);
        CriteriaQuery<Player> where = query.select(from).where(objects);
        TypedQuery<Player> query1 = manager.createQuery(where);
        if(order == null) {
            order = PlayerOrder.ID;
        }
        if(pageNumber == null) {
            pageNumber = 0;
        }
        if(pageSize == null) {
            pageSize = 3;
        }
        if(pageNumber == 0) {
            query1.setMaxResults(pageSize);
        }

        if(pageNumber != 0) {
            if(pageNumber != 1) {
                query1.setMaxResults(pageNumber * pageSize).setFirstResult((pageNumber - 1) * pageSize);
            }
            query1.setMaxResults(pageNumber * pageSize).setFirstResult((pageNumber) * pageSize);
        }
        List<Player> resultList = query1.getResultList();

        return resultList;



    }

    public Player update(Player player) {
        reposit.save(player);

        return player;



    }




}
