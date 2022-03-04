package message;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository {

    private JdbcTemplate jdbcTemplate;

    public MessageRepository(MariaDbDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertMessage(Message message){
        jdbcTemplate.update("insert into messages(sender_id,receiver_id,message) values(?,?,?)",message.getSenderId(),
                message.getReceiverId(),message.getMessage());
    }

    public List<String> findMessagesBySenderId(long senderId){
        List<String> messages = new ArrayList<>();
        jdbcTemplate.query("select * from messages where sender_id=?",
                ((rs, i) -> messages.add(rs.getString("message"))), Long.toString(senderId));
        return messages;
    }

}
