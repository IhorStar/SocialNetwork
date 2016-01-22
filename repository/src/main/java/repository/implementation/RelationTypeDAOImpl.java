package repository.implementation;

import entity.RelationType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import repository.RelationTypeDAO;

@Repository
public class RelationTypeDAOImpl implements RelationTypeDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addRelationType(RelationType relationType) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(relationType);
    }

    public RelationType getRelationTypeById(int relationTypeId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(RelationType.class, relationTypeId);
    }

    public void updateRelationType(RelationType relationType) {
        Session currentSession = sessionFactory.getCurrentSession();
        RelationType existingRelationType = currentSession.get(RelationType.class, relationType.getRelationTypeId());
        existingRelationType.setRelationType(relationType.getRelationType());
        currentSession.save(existingRelationType);
    }

    public void deleteRelationType(RelationType relationType) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(relationType);
    }
}
