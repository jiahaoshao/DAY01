package demo.day01.service.servicelmpl;

import demo.day01.domain.Information;
import demo.day01.domain.message;
import demo.day01.repository.messageDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class messagelmpl {
    @Resource
    private messageDao messageDao;

    public message updateMessage(String requester,String receiver,message message) {
       message message1= new message();
       message1.setContent(String.valueOf(message.getContent()));
       message1.setReceiver(receiver);
       message1.setSender(requester);
       message1.setTimestamp(message.getTimestamp());
       message1.setSelf(message.isSelf());
       messageDao.save(message1);
       return message1;
    }

    public ResponseEntity<List<message>> findmessageByRequesterAndReceiver(String requester, String receiver) {
        List<message> messages = messageDao.findmessageByRequesterAndReceiver(requester, receiver);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
