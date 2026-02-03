package test.dao;

import com.revpasswordmanager.dao.CredentialDaoImpl;
import com.revpasswordmanager.model.Credential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CredentialDaoImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement pstmt;

    @Mock
    private ResultSet resultSet;

    private CredentialDaoImpl credentialDao;

    @BeforeEach
    void setUp() {
        credentialDao = new CredentialDaoImpl(connection);
    }

    @Test
    void testAddCredential() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(pstmt);

        Credential cred = new Credential(1, "Google", "user", "enc", "url", "notes");
        credentialDao.addCredential(cred);

        verify(pstmt).executeUpdate();
    }

    @Test
    void testGetCredentialsByUserId() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(pstmt);
        when(pstmt.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // One result then stop
        when(resultSet.getInt("id")).thenReturn(10);
        when(resultSet.getInt("user_id")).thenReturn(1);
        when(resultSet.getString("account_name")).thenReturn("Google");

        List<Credential> list = credentialDao.getCredentialsByUserId(1);

        assertEquals(1, list.size());
        assertEquals("Google", list.get(0).getAccountName());
    }
}
