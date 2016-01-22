package repository.implementation;

import entity.Relation;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import repository.RelationDAO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RelationDAOImpl implements RelationDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addRelation(Relation relation) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(relation);
    }

    public Relation getRelationById(int relationId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Relation.class, relationId);
    }

    public void updateRelation(Relation relation) {
        Session currentSession = sessionFactory.getCurrentSession();
        Relation existingRelation = currentSession.get(Relation.class, relation.getRelationId());
        existingRelation.setRelationType(relation.getRelationType());
        currentSession.save(existingRelation);
    }

    public void deleteRelation(Relation relation) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(relation);
    }

    public List<Relation> getAllRelationBy(int userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        User user = currentSession.get(User.class, userId);
        return new ArrayList<Relation>(user.getRelation());
    }
}
