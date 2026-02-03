package test.dao;

import com.revpasswordmanager.dao.UserDaoImpl;
import com.revpasswordmanager.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDaoImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement pstmt;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private UserDaoImpl userDao;

    @BeforeEach
    void setUp() throws SQLException {
        // Initialize if strictly needed, but InjectMocks handles most
        // Manually injecting connection if constructor injection is used (which it is)
        userDao = new UserDaoImpl(connection);
    }

    @Test
    void testCreateUser() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(pstmt);

        User user = new User("user", "hash", "Name", "email");
        userDao.createUser(user);

        verify(pstmt).setString(1, "user");
        verify(pstmt).executeUpdate();
    }

    @Test
    void testGetUserByUsername() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(pstmt);
        when(pstmt.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("username")).thenReturn("user");
        when(resultSet.getString("master_password_hash")).thenReturn("hash");
        when(resultSet.getString("name")).thenReturn("Name");
        when(resultSet.getString("email")).thenReturn("email");

        User user = userDao.getUserByUsername("user");

        assertNotNull(user);
        assertEquals("user", user.getUsername());
    }
}
