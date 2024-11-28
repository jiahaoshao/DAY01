package demo.day01.controller;

import demo.day01.domain.*;
import demo.day01.repository.InformationDao;
import demo.day01.repository.exampleDao;
import demo.day01.service.UserService;
import demo.day01.service.servicelmpl.*;
import demo.day01.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RequestMapping(value = "/user")
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @Autowired
    private exampleDao userRepository;
    @Autowired
    private InformationDao informationRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private Informationlmpl informationlmpl;
    @Autowired
    private relationshiplmpl relationshiplmpl;
    @Autowired
    private demo.day01.service.servicelmpl.messagelmpl messagelmpl;
    @Autowired
    private demo.day01.service.servicelmpl.groupslmpl groupslmpl;
    @Autowired
    private categorylmpl categorylmpl;
    @Autowired
    private demo.day01.service.servicelmpl.group_categorieslmpl group_categorieslmpl;
    @Autowired
    private demo.day01.repository.groupsDao groupsDao;
    @Autowired
    private demo.day01.service.servicelmpl.userAndgroupslmpl userAndgroupslmpl;
    @Autowired
    private demo.day01.repository.userAndgroupsDao userAndgroupsDao;
    @Autowired
    private postslmpl postslmpl;
    @Autowired
    private demo.day01.repository.postsDao postsDao;
    @Autowired
    private commentslmpl commentslmpl;
    @Autowired
    private demo.day01.repository.commentsDao commentsDao;
    @Autowired
    private InformationDao informationDao;

    @PostMapping("/login" )
    public Result<User> loginController(@RequestParam String username, @RequestParam String password) {
        User user = userService.loginService(username, password);
        if (user != null) {
            return Result.success(user,"登陆成功！");
        }
        else{
            return Result.error("123" , "账号或密码错误!");
        }
    }

    @PostMapping("/register")
    public Result registerController(@RequestBody User newUser) {
        User user = userService.registerService(newUser);
        if (user != null) {
            userService.saveUser(newUser);
            return Result.success(user , "注册成功！");
        }
        else{
            return Result.error("456" , "用户名已存在，请重新输入!");
        }
    }

    @PostMapping("/users/{username}")
    public List<Information> getInformation(@PathVariable String username) {
//        System.out.println(username);
        return informationRepository.findByUsername(username);
    }

    @PostMapping("/users/search/{username}")
    public List<relationship> getInformationSearch(@PathVariable String username) {
        return relationshiplmpl.findByReceiver(username);
    }

    @PostMapping("/users/update")
    public ResponseEntity<String> updateUser(@RequestBody Information information) {
        informationlmpl.updateInformation(information);
        return ResponseEntity.ok("User updated successfully");
    }

    @PostMapping("/users/apply/{requester}/{receiver}")
    public ResponseEntity<relationship> apply(@PathVariable String requester, @PathVariable String receiver) {
        try {
            relationship relationship = relationshiplmpl.updaterelatonship(requester, receiver);
            return ResponseEntity.ok(relationship);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/users/change/{receiver}/{requester}")
    public ResponseEntity<relationship> change(@PathVariable String receiver, @PathVariable String requester) {
        try {
            System.out.println("wolail");
            System.out.println(requester);
            System.out.println(receiver);
            System.out.println("---------------------");
            relationship relationship = relationshiplmpl.changerelatonship(requester, receiver);
            return ResponseEntity.ok(relationship);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/users/success/{requester}/{receiver}")
    public ResponseEntity<relationship> Success(@PathVariable String requester, @PathVariable String receiver) {
        try {
            relationship relationship = relationshiplmpl.Successrelatonship(requester, receiver);
            return ResponseEntity.ok(relationship);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/users/successSearch/{username}")
    public List<relationship> SuccessSearch(@PathVariable String username) {
        return relationshiplmpl.findByReceiverOrRequester(username ,username);
    }

    @PostMapping("/users/send/{requesterId}/{receiverId}")
    public ResponseEntity<?> sendMessage(
            @PathVariable String requesterId,
            @PathVariable String receiverId,
            @RequestBody message messageDTO) {
        messagelmpl.updateMessage(requesterId, receiverId, messageDTO);;
        return ResponseEntity.ok("Message sent successfully");
    }

    @PostMapping("/users/searchMessage/{requester}/{receiver}")
    public ResponseEntity<List<message>> searchMessage(@PathVariable String requester, @PathVariable String receiver) {
        try {
            return messagelmpl.findmessageByRequesterAndReceiver(requester, receiver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/groups/{id}")
    public List<groupMain> getGroupById(@PathVariable int id) {
        System.out.println(groupslmpl.findGroupById(id));
        return groupslmpl.findGroupById(id);

    }


    // 定义文件上传的目录
//    private static final String UPLOADED_FOLDER = "D:\\website\\saveJpg\\";

    // 假设这个值是从配置文件中读取的，指向一个Web服务器可访问的目录
    @Value("${upload.dir.url-prefix}")
    private String uploadDirUrlPrefix;

    // 假设这个值是从配置文件中读取的，指向服务器上的存储目录
    @Value("${upload.dir.path}")
    private String uploadedFolderPath;

    @PostMapping("/upload")
    public int uploadImage(@RequestParam("image") MultipartFile file,
                                         @RequestParam("name") String name,
                                         @RequestParam("description") String description,
                                         @RequestParam("createName") String crerataName,
                                         @RequestParam("categories") String categories
    ) throws IOException {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file!", HttpStatus.BAD_REQUEST).getStatusCodeValue();
        }
        System.out.println(file);
        // 获取文件名
            String fileName = file.getOriginalFilename();

            // 创建文件路径
            String filePath = uploadedFolderPath + File.separator + fileName;

            File dest = new File(filePath);

            // 确保上传目录存在
            dest.getParentFile().mkdirs();

            // 保存文件到指定路径
            file.transferTo(dest);

            String fileUrl = uploadDirUrlPrefix + fileName;

        int groupId = groupslmpl.createdGroups(name, description, fileUrl, crerataName);
        List<Integer> categoryList = categorylmpl.createCategory(categories);
        group_categorieslmpl.update(groupId, categoryList);
        return groupId;

    }
    @PostMapping("/users/allGroups")
    public List<groupMain> allGroups() {
        List<groupMain> firstList= groupsDao.findAll();
        for (groupMain group : firstList) {
            int firstId = group.getGroupId();
            List<group_categories> secondList = group_categorieslmpl.findByGroupId(firstId);
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            for (group_categories group_category : secondList) {
                arrayList.add(group_category.getCategoryId());
            }
            ArrayList<String> endList = new ArrayList<>();
            for (int i = 0; i < arrayList.size(); i++) {
                Optional<Category> fourthList = categorylmpl.findById(arrayList.get(i));
                endList.add(fourthList.get().getCategoryName());
            }
            String categoryNamesString = String.join(",", endList);
            group.setCategories(categoryNamesString);
        }
        return firstList;
    }

    @PostMapping("/enterGroups/{username}/{groupId}")
    public ResponseEntity<?> enterGroup(@PathVariable String username, @PathVariable int groupId) {
        return userAndgroupslmpl.update(username,groupId);
    }

    @PostMapping("/personalGroups/{username}")
    public List<Integer> personalGroup(@PathVariable String username) {
        List<userAndgroups> list = userAndgroupsDao.findAllByUsername(username);
        List<Integer> ending =new ArrayList<>();
        for (userAndgroups user : list) {
            ending.add(user.getGroupId());
        }
        return ending;
    }

    @PostMapping("/sendPosts/{name}")
    public ResponseEntity<?> sendPosts(
            @PathVariable String name,
            @RequestBody commentsDTO postsDTO) {
        postslmpl.updatePosts(name,postsDTO);
        return ResponseEntity.ok("Message sent successfully");
    }

    @PostMapping("/getPosts/{groupId}")
    public List<commentsDTO> getPosts(
            @PathVariable int groupId) {
        return postsDao.findAllByGroupId(groupId);
    }

    @PostMapping("/updatePosts/{postId}/{tips}/{tipss}")
    public ResponseEntity<?> updatePosts(
            @PathVariable int postId,
            @PathVariable String tips,
            @PathVariable String tipss) {
        if (Objects.equals(tipss, "jia")){
            postslmpl.updatePostsElement(postId, tips);
        }
        else {
            postslmpl.updatePostsElementdown(postId, tips);
        }
        return ResponseEntity.ok("Posts have updated successfully");
    }

    @PostMapping("/submitComments/{postId}/{name}")
    public ResponseEntity<?> submitComments(
            @PathVariable String name,
            @PathVariable int postId,
            @RequestBody comments commentsDTO) {
        commentslmpl.updateComments(name, postId, commentsDTO);
        return ResponseEntity.ok("Message sent successfully");
    }

    @PostMapping("/searchComments/{postId}")
    public List<comments> searchComments(
            @PathVariable int postId) {
        return commentsDao.findAllByPostId(postId);
    }

    @PostMapping("/searchGroupsUser/{groupId}")
    public List<List<Information>> searchGroupsUser(
            @PathVariable int groupId) {
        List<userAndgroups> list1 = userAndgroupsDao.findAllByGroupId(groupId);
        List<String> list2 = new ArrayList<>();
        for (userAndgroups user : list1) {
            list2.add(user.getUsername());
        }
        List<List<Information>> list3 = new ArrayList<>();
        for (String username : list2) {
            list3.add(informationRepository.findByUsername(username));
        }
        return list3;
    }

    @PostMapping("/searchTogether/{username}")
    public List<Information> searchTogether(@PathVariable String username) {
       List<relationship> list1 = relationshiplmpl.findByReceiverOrRequester(username,username);
       // list2是当前用户的好友
       List<String> list2 = new ArrayList<>();
       for (relationship relationship : list1) {
           if (Objects.equals(relationship.getRequester(), username)) {
               list2.add(relationship.getReceiver());
           }
           else {
               list2.add(relationship.getRequester());
           }
       }
       List<String> list6 = new ArrayList<>();
       for (String string : list2) {
           List<String> list5 = new ArrayList<>();
           List<relationship> list4 = relationshiplmpl.findByReceiverOrRequester(string,string);
           for (relationship relationship : list4) {
               if (Objects.equals(relationship.getRequester(), username)) {
                   list5.add(relationship.getReceiver());
               }
               else {
                   list5.add(relationship.getRequester());
               }
           }
           for (String string2 : list5) {
               if (!Objects.equals(string2, username)) {
                   list6.add(string2);
               }
           }
       }
        List<String> tempList = new ArrayList<>();
        for (String string2 : list6) {
            if (!list2.contains(string2)) {
                tempList.add(string2);
            }
        }
        list6 = tempList; // 如果需要更新原列表，可以这样赋值

       Set<String> list66 = new HashSet<>(list6);
       List<Information> list7 = new ArrayList<>();
       for (String string : list66) {
           list7.add(informationDao.findByusername(string));
       }
       return list7;
    }
}
