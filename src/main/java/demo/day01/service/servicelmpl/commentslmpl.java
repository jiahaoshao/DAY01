package demo.day01.service.servicelmpl;

import demo.day01.domain.comments;
import demo.day01.repository.commentsDao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class commentslmpl {
    @Resource
    private commentsDao commentsDao;

    public ResponseEntity<?> updateComments(String name, int postId, comments commentsDTO) {
        comments comments = new comments();
        comments.setUsername(name);
        comments.setFenxiang(commentsDTO.getFenxiang());
        comments.setContent(commentsDTO.getContent());
        comments.setDianzan(commentsDTO.getDianzan());
        comments.setDianzan(commentsDTO.getDianzan());
        comments.setTime(commentsDTO.getTime());
        comments.setPostId(postId);
        comments.setDate(commentsDTO.getDate());
        commentsDao.save(comments);
        return ResponseEntity.ok().build();
    }


}
