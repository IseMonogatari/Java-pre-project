package org.preproject.CRUDSecAndFront.service;

import org.preproject.CRUDSecAndFront.model.Status;
import org.preproject.CRUDSecAndFront.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface VkService {
    //TODO Из ВК
    ResponseEntity<String> sendCommentToVK(Integer id, String message);
    ResponseEntity<String> getCommentsFromVK();
    ResponseEntity<String> editCommentFromVK(String commentId, String message);
    ResponseEntity<String> deleteCommentFromVK(String commentId);

    //TODO из БД
    void changeUserStatus(Integer userId, boolean status);
    List<User> getNewAdminFromDB();
    boolean getUserStatusForButton(Integer userId);
}
