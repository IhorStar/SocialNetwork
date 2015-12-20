package servicetest;

import dao.DAOException;
import dao.implementation.RelationTypeDAOImpl;
import entity.RelationType;
import org.junit.Before;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.implementation.RelationTypeServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RelationTypeServiceImplTest {
    private RelationTypeServiceImpl relationTypeService;
    private RelationTypeDAOImpl relationTypeDAO;

    RelationType relationType1 = new RelationType();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws DAOException {
        relationTypeDAO = mock(RelationTypeDAOImpl.class);

        relationTypeService = new RelationTypeServiceImpl();
        relationTypeService.setDao(relationTypeDAO);

        relationType1.setRelationTypeId(1);
        relationType1.setRelationTypeName("friend");

    }

    @Test
    public void testAddRelationType() throws DAOException {
        relationTypeService.addRelationType(relationType1);
        verify(relationTypeDAO).addRelationType(relationType1);
    }

    @Test
    public void testGetRelationById() throws DAOException {
        when(relationTypeDAO.getRelationTypeById(1)).thenReturn(relationType1);
        RelationType relation = relationTypeService.getRelationTypeById(1);
        verify(relationTypeDAO).getRelationTypeById(1);
        assertEquals("friend", relation.getRelationTypeName());
    }

    @Test
    public void testGetRelationById2() throws DAOException {
        doThrow(new DAOException("cannot find relation type with id = 0")).when(relationTypeDAO).getRelationTypeById(0);
        exception.expect(DAOException.class);
        exception.expectMessage("cannot find relation type with id = 0");
        RelationType relationType = relationTypeService.getRelationTypeById(0);
    }

    @Test
    public void testUpdateRelationType() throws DAOException {
        relationTypeService.updateRelationType(relationType1);
        verify(relationTypeDAO).updateRelationType(relationType1);
    }

    @Test
    public void testDeleteRelationTypeById() throws DAOException {
        relationTypeService.deleteRelationTypeById(1);
        verify(relationTypeDAO).deleteRelationTypeById(1);
    }
}
