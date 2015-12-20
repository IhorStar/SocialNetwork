package servicetest;

import dao.DAOException;
import dao.implementation.RelationDAOImpl;
import entity.Relation;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.implementation.RelationServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RelationServiceImplTest {
    private RelationServiceImpl relationService;
    private RelationDAOImpl relationDAO;

    Relation relation1 = new Relation();
    Relation relation2 = new Relation();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws DAOException {
        relationDAO = mock(RelationDAOImpl.class);

        relationService = new RelationServiceImpl();
        relationService.setDao(relationDAO);

        relation1.setRelationId(1);
        relation1.setUser1Id(1);
        relation1.setUser2Id(2);
        relation1.setRelationTypeId(1);

        relation2.setRelationId(2);
        relation2.setUser1Id(1);
        relation2.setUser2Id(3);
        relation2.setRelationTypeId(2);
    }

    @Test
    public void testAddRelation() throws DAOException {
        relationService.addRelation(1, 2, 1);
        verify(relationDAO).addRelation(1, 2, 1);
    }

    @Test
    public void testGetRelationById() throws DAOException {
        when(relationDAO.getRelationById(1)).thenReturn(relation1);
        Relation relation = relationService.getRelationById(1);
        verify(relationDAO).getRelationById(1);
        assertEquals(1, relation.getRelationTypeId());
    }

    @Test
    public void testGetRelationById2() throws DAOException {
        doThrow(new DAOException("cannot find relation with id = 0")).when(relationDAO).getRelationById(0);
        exception.expect(DAOException.class);
        exception.expectMessage("cannot find relation with id = 0");
        Relation relation = relationService.getRelationById(0);
    }

    @Test
    public void testUpdateRelation() throws DAOException {
        relationService.updateRelation(relation1);
        verify(relationDAO).updateRelation(relation1);
    }

    @Test
    public void testDeleteRelation() throws DAOException {
        relationService.deleteRelationBy(1, 2);
        verify(relationDAO).deleteRelationBy(1, 2);
    }

    @Test
    public void testGetAllRelation() throws DAOException {
        List<Relation> allRelations = new ArrayList<Relation>();
        int counter = 0;

        allRelations.add(relation1);
        allRelations.add(relation2);

        when(relationDAO.getAllRelationBy(1)).thenReturn(allRelations);
        List<Relation> result = relationService.getAllRelationBy(1);
        for(Relation relation : result) {
            counter++;
        }
        verify(relationDAO).getAllRelationBy(1);
        assertEquals(2, counter);
    }
}
