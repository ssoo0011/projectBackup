package com.example.yj.post;

import com.example.yj.DataNotFoundException;
import com.example.yj.entity.*;
import com.example.yj.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor

public class PostService {
    private final PostRepository postRepository;
    private final MyScheduleRepository myScheduleRepository;
    private final PostFileRepository postFileRepository;
    private final PostLikeUserRepository postLikeUserRepository;
    private final VisitUserIpsRepository visitUserIpsRepository;
    private final TourListRepository tourListRepository;

    public List<Post> getList() {
        return postRepository.findAll();
    }

    //파일 저장
    public void file1(List<MultipartFile> files, Long bno) throws Exception{

        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\"; // 파일 저장 장소

        for (MultipartFile file : files) {

            String origName = file.getOriginalFilename(); // 파일 이름 추출

            String extension = origName.substring(origName.lastIndexOf(".")); // 파일 이름에서 확장자 추출(ex : .png, .jpg)

            String uuid = UUID.randomUUID().toString(); // 파일 이름으로 쓸 uuid 생성

            String savedName = uuid + extension; // uuid + 확장자

            String savedPath = path + savedName; // 파일 경로 + 파일 이름

            file.transferTo(new java.io.File(savedPath)); // 로컬에 저장

            PostFile pf = PostFile.builder()
                    .fileName(savedName)
                    .postBno(bno)
                    .build();
            postFileRepository.save(pf);
        }
    }

    //게시글 수정

    public void savePost(Post post, Long scdId, List<MultipartFile> imgFile) throws Exception {

        String fileName = "";
        Post p = Post.builder()
                .scdId(scdId)
                .visitSpot(post.getVisitSpot()) //방문장소
                .postDate(LocalDate.now()) //글쓴시간
                .title(post.getTitle()) // 제목
                .content(post.getContent()) // 내용
                .visitPost(0) //기본 조회수
                .likeNum(0) // 기본 좋아요수 0개
                .pub(post.getPub()) // 공개여부 t || f
                .visitDate(post.getVisitDate()) // 방문날짜
                .writer(post.getWriter())
                .build();

        postRepository.save(p);

        for (MultipartFile file : imgFile){
            fileName = file.getOriginalFilename();
        }
        if (!fileName.equals("")){
            file1(imgFile, p.getBno());
        }

    }
    public void modifyPost(Post post, List<MultipartFile> imgFile) throws Exception {

        String fileName = "";

        Optional<Post> result = postRepository.findById(post.getBno());

        if (result.isPresent()) {
            Post post1 = result.get();
            post1.update(post.getTitle(), post.getContent(),
                    post.getPub());
            postRepository.save(post1);

            for (MultipartFile file : imgFile){
                fileName = file.getOriginalFilename();
            }
            if (!fileName.equals("")){
                file1(imgFile, post1.getBno());
            }
        }

    }

    public Page<Post> getList(int page) { //페이징
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("postDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); // 한 페이지에 10개씩, 정렬기준-> 최신게시물순
        return this.postRepository.findByPub("t", pageable); //공개여부 공개로 되어있는 게시글만 보기
    }

    public Post getPost(Long id, String userIp) {

        Optional<Post> result = this.postRepository.findById(id);
        if (result.isPresent()) {
            updateVisitPost(id, userIp);
            return result.get();
        } else {
            throw new DataNotFoundException("post not found");
        }
    }


    public Post getPost(Long bno) { //스케쥴 아이디로 게시글 여부 체크

        Optional<Post> result = this.postRepository.findById(bno);

        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    public List<PostFile> getFile (Long bno){

        List<PostFile> result = postFileRepository.findByPostBno(bno);
        if(result != null){
            return result;
        }else return null;
    }

    public void updateVisitPost(Long bno, String userIp) { // ip주소로 조회수 체크0

        Post post = postRepository.findById(bno).orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));
        Optional<VisitUserIps> result = visitUserIpsRepository.findById(userIp);

        if (!result.isPresent()){ //ip가 없으면 +1, 저장하기
            post.addVisitPost(post.getVisitPost() + 1);
            VisitUserIps visitUserIps = VisitUserIps.builder()
                    .userIps(userIp)
                    .bno(bno)
                    .build();
            visitUserIpsRepository.save(visitUserIps);
            postRepository.save(post);
        }
    }

    //수정시 보여질 화면에 출력될 내용 들고오기
    public Post modifyView(Long bno) {
        Optional<Post> post = postRepository.findById(bno);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new DataNotFoundException("post not found");
        }
    }

    public List<String> popularSpot() {
        List<String> popSpot = myScheduleRepository.findPopularSpot(); //인기장소 리스트
        return popSpot;
    }

    public Map<String, String> popularSpotImg(){
        List<String> imgList = new ArrayList<>(); // 이미지를 저장할 리스트
        List<String> popSpotList = popularSpot(); // 한 장소씩 나옴

        for (String spot : popSpotList) {

            List<TourList> tour = tourListRepository.findFirstBySpot(spot); // spot에 맞는 tourList들고오기

            if (tour != null) {
                for (int i = 0; i < tour.size(); i++){
                    String img = tour.get(i).getImgSrc();
                    imgList.add(img);
                }
            }
        }
        Map<String, String> popSpotImgMap = new LinkedHashMap<>(); //순서 있는 맵 만들기

        for (int i = 0; i < popSpotList.size(); i++) {
            String popSpot = popSpotList.get(i);
            String img = imgList.get(i);
            popSpotImgMap.put(popSpot, img);
        }

        return popSpotImgMap;

    }
    public List<Post> popularPost() { //젤 인기쟁이 글
        List<Post> popPost = postRepository.findPopPost();
        return popPost;
    }

    public void likePost(Long bno) { //좋아요 +1
        Optional<Post> result = postRepository.findById(bno);
        if (result.isPresent()) {
            Post post = result.get();
            post.addLikeNum(post.getLikeNum() + 1);
            postRepository.save(post);
        }
    }
    public void unLikePost(Long bno) { //좋아요 -1
        Optional<Post> result = postRepository.findById(bno);
        if (result.isPresent()) {
            Post post = result.get();
            post.addLikeNum(post.getLikeNum() -1 );
            postRepository.save(post);
        }
    }

    public int voteUser(Long bno, String userId) {

        List<PostLikeUser> likeUsers = postLikeUserRepository.findByBnoAndLikeUserId(bno, userId);

        if (likeUsers.size() == 0) { //좋아요 안했으면

            PostLikeUser postLikeUser = PostLikeUser.builder()
                    .likeUserId(userId)
                    .bno(bno)
                    .build();
            postLikeUserRepository.save(postLikeUser); //레파지토리에 저장
            likeUsers.add(postLikeUser);
            likePost(bno);
            return 1;

        } else{ //좋아요 이미 했으면
            postLikeUserRepository.deleteLikeUserId(bno, userId);
            unLikePost(bno);
            return 0;
        }
    }

    public Page<Post> searchSpot(String visitSpot, int page) { // 장소명으로 게시글 검색

        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("postDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); // 한 페이지에 10개씩, 정렬기준-> 최신게시물순

        return postRepository.findByVisitSpot(visitSpot, pageable);
    }

    public void deletePost(Long bno) {
        postRepository.deleteById(bno);
    }
}
