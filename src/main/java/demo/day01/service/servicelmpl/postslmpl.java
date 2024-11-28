package demo.day01.service.servicelmpl;

import demo.day01.domain.commentsDTO;
import demo.day01.repository.postsDao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class postslmpl {
    @Resource
    private postsDao postsDao;

    public ResponseEntity<?> updatePosts(String username, commentsDTO posts) {
        commentsDTO posts1=new commentsDTO();
        posts1.setUsername(username);
        posts1.setFenxiang(posts.getFenxiang());
        posts1.setContent(posts.getContent());
        posts1.setDianzan(posts.getDianzan());
        posts1.setDianzan(posts.getDianzan());
        posts1.setTime(posts.getTime());
        posts1.setGroupId(posts.getGroupId());
        posts1.setDate(posts.getDate());
        postsDao.save(posts1);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> updatePostsElement(int postId, String tips) {
        List<commentsDTO> list = postsDao.findAllById(Collections.singleton(postId));
        if (Objects.equals(tips, "dianzan")){
            commentsDTO posts1=list.get(0);
            posts1.setDianzan(posts1.getDianzan()+1);
            postsDao.save(posts1);
            return ResponseEntity.ok().build();
        }
        else if (Objects.equals(tips, "pinglun")){
            commentsDTO posts1=list.get(0);
            posts1.setPinglun(posts1.getPinglun()+1);
            postsDao.save(posts1);
            return ResponseEntity.ok().build();
        }
        else {
            commentsDTO posts1=list.get(0);
            posts1.setFenxiang(posts1.getFenxiang()+1);
            postsDao.save(posts1);
            return ResponseEntity.ok().build();
        }
    }

    public ResponseEntity<?> updatePostsElementdown(int postId, String tips) {
        List<commentsDTO> list = postsDao.findAllById(Collections.singleton(postId));
        if (Objects.equals(tips, "dianzan")){
            commentsDTO posts1=list.get(0);
            posts1.setDianzan(posts1.getDianzan()-1);
            postsDao.save(posts1);
            return ResponseEntity.ok().build();
        }
        else if (Objects.equals(tips, "pinglun")){
            commentsDTO posts1=list.get(0);
            posts1.setPinglun(posts1.getPinglun()-1);
            postsDao.save(posts1);
            return ResponseEntity.ok().build();
        }
        else {
            commentsDTO posts1=list.get(0);
            posts1.setFenxiang(posts1.getFenxiang()-1);
            postsDao.save(posts1);
            return ResponseEntity.ok().build();
        }
    }

}
