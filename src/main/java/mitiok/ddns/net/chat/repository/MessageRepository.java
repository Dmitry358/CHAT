package mitiok.ddns.net.chat.repository;

import mitiok.ddns.net.chat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  // Получить всю переписку между двумя пользователями, по времени
  List<Message> findBySenderAndRecipientOrRecipientAndSenderOrderByTimestampAsc(
    String sender1, String recipient1,
    String sender2, String recipient2
  );

  // Получить все сообщения для конкретного пользователя (отправленные и полученные)
  List<Message> findBySenderOrRecipientOrderByTimestampAsc(String sender, String recipient);
}
