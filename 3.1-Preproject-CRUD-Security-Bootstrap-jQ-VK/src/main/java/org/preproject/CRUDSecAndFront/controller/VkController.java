package org.preproject.CRUDSecAndFront.controller;


import org.preproject.CRUDSecAndFront.model.Status;
import org.preproject.CRUDSecAndFront.model.User;
import org.preproject.CRUDSecAndFront.service.VkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class VkController {

    @Autowired
    private VkService vkService;


    //TODO методы для работы с ВК


    //TODO Отправляем комментарий в ВК
    @PostMapping("/want_to_be_admin")
    public ResponseEntity<String> postToVK(@RequestParam("id") Integer id,
                                           @RequestParam("message") String message) {
        return vkService.sendCommentToVK(id, message);
    }

    //TODO Получаем данные из ВК
    @GetMapping("/get_new_admin")
    public ResponseEntity<String> getFromVK() {
        return vkService.getCommentsFromVK();
    }

    //TODO Редактируем комментарий в ВК
    @GetMapping("/edit_comment")
    public ResponseEntity<String> checkResponseAdmin(@RequestParam("comment_id") String commentId,
                                                     @RequestParam("message") String message) {
        return vkService.editCommentFromVK(commentId, message);
    }

    //TODO Удаляем комментарий из ВК
    @GetMapping("/delete_post")
    public ResponseEntity<String> deleteComment(@RequestParam("commentId") String commentId) {
        return vkService.deleteCommentFromVK(commentId);
    }


    //TODO методы для работы с БД


    //TODO Получаем данные о запросивших повышение роли пользователях из БД
    @GetMapping("/get_new_admin_from_DB")
    public List<User> getNewAdminFromDB() {
        return vkService.getNewAdminFromDB();
    }

    //TODO Делаем проверку состояния для кнопки запроса на роль АДМИН
    @GetMapping("/get_button_status")
    public boolean getUserStatusForButton(@RequestParam("user_id") Integer userId) {
        return vkService.getUserStatusForButton(userId);
    }

    //TODO Меняем СТАТУС запроса пользователя
    @PostMapping("/change_user_status_to_false")
    public ResponseEntity<String> changeUserStatus(@RequestParam("userId") Integer id) {
        vkService.changeUserStatus(id, false);
        return new ResponseEntity<>("Изменили состояние статуса пользователя", HttpStatus.OK);
    }





//    старые версии методов
//    @PostMapping("/want_to_be_admin")
//    public ResponseEntity<String> postToVK(@RequestBody String MESSAGE) {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.set("Authorization", TOKEN);
//
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("owner_id", OWNER_ID);
//        map.add("post_id", POST_ID);
//        map.add("message", MESSAGE);
//        map.add("v", V);
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(
//                URL_CREATE_COMMENT, request , String.class);
//        return new ResponseEntity<>("Запрос отправен в ВК", HttpStatus.OK);
//    }
//
//    @GetMapping("/get_new_admin")
//    public ResponseEntity<String> getFromVK() {
////        RestTemplate restTemplate = new RestTemplate();
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
////        headers.set("Authorization", TOKEN);
//
////        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
////        map.add("owner_id", OWNER_ID);
////        map.add("post_id", POST_ID);
////        map.add("preview_length", PREVIEW_LENGTH);
////        map.add("v", V);
//
////        HttpEntity<String> request = new HttpEntity<>("", headers); //map, headers
//
////        ResponseEntity<String> response
////                = restTemplate.getForEntity(URL_GET_COMMENTS, String.class, request);
////        ResponseEntity<String> response =
////                restTemplate.exchange(URL_GET_COMMENTS, HttpMethod.GET, request, String.class);
//
//        Map<String, String> params = new HashMap<>();
//        params.put("owner_id", OWNER_ID);
//        params.put("post_id", POST_ID);
//        params.put("preview_length", PREVIEW_LENGTH);
//        params.put("v", V);
//
////        ResponseEntity<String> response = restTemplate.getForEntity("https://bhub.ru/add/{value}", String.class, params);
//
//        String uri = "https://api.vk.com/method/wall.getComments?owner_id=-220604044&post_id=2&preview_length=0" +
//                "&access_token=" + TOKEN1 +
//                "&v=5.131";
//        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
//
////        System.out.println("\n" + response + "\n");
//
//        return response;
//    }
//
//    @GetMapping("/delete_post")
//    public ResponseEntity<String> deleteComment(@RequestParam String commentId) {
////        System.out.println("\n" + commentId + "\n");
////        RestTemplate restTemplate = new RestTemplate();
//////        String commentId = "28";
////        String uri = "https://api.vk.com/method/wall.deleteComment" +
////                "?owner_id=-220604044" +
////                "&comment_id=" + commentId +
////                "&preview_length=0" +
////                "&access_token=" + TOKEN1 +
////                "&v=5.131";
//////        restTemplate.delete(uri);
////        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
////        return response;
//
////        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.set("Authorization", TOKEN);
//
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("owner_id", OWNER_ID);
//        map.add("comment_id", commentId);
//        map.add("preview_length", "0");
//        map.add("v", V);
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(
//                URL_DELETE_COMMENT, request , String.class);
//        return new ResponseEntity<>("Запрос на удаление отправен в ВК", HttpStatus.OK);
//    }
//
//    @GetMapping("/edit_comment")
//    public ResponseEntity<String> checkResponseAdmin(@RequestParam("comment_id") String comment_id,
//                                                     @RequestParam("message") String message) {
////        RestTemplate restTemplate = new RestTemplate();
//
//        String uri = "https://api.vk.com/method/wall.editComment" +
//                "?owner_id=" + OWNER_ID +
//                "&comment_id=" + comment_id +
//                "&message=" + message +
//                "&access_token=" + TOKEN1 +
//                "&v=" + V;
//        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
//
//        System.out.println("\n" + response + "\n");
//
//        return response;
//    }
}
