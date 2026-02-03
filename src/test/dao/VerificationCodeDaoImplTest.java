package test.dao;

import com.revpasswordmanager.dao.VerificationCodeDaoImpl;
import com.revpasswordmanager.model.VerificationCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VerificationCodeDaoImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement pstmt;

    @Mock
    private ResultSet resultSet;

    private VerificationCodeDaoImpl verificationDao;

    @BeforeEach
    void setUp() {
        verificationDao = new VerificationCodeDaoImpl(connection);
    }

    @Test
    void testCreate() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(pstmt);

        VerificationCode code = new VerificationCode(1, "123456", "TEST", new Timestamp(System.currentTimeMillis()));
        verificationDao.create(code);

        verify(pstmt).executeUpdate();
    }
}
