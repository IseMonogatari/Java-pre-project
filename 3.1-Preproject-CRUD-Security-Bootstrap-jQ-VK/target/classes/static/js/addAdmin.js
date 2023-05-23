// TODO какие функции у нас будут выполняться при запуске страницы
$(document).ready(function() {
    $('#wantToBeAdmin').click(function() {
        $(this).attr('disabled', true); // Либо добавить атрибут disabled
    });
    if (buttonStatus(showAuthorizedUser().id)) {
        $('#wantToBeAdmin').attr('disabled', true); // Либо добавить атрибут disabled
    } else {
        $('#wantToBeAdmin').attr('disabled', false); // Либо добавить атрибут disabled
    }
});

// // TODO Что решили по поводу роли АДМИН?
// function buttonRefuse() {
//     if (checkRefuseUpgradeRoleUser(showAuthorizedUser().id)) {
//         set_message_error("#refuseMessage", "К сожалению, но да...");
//         $('#wantToBeAdmin').attr('disabled', false); // Либо добавить атрибут disabled
//     } else {
//         set_message_info("#refuseMessage", "Запрос пока в обработке.");
//     }
// }

// TODO Определяем статус кнопки из БД!
function buttonStatus(USER_ID) {
    let status = false;

    $.ajax({
        url: '/get_button_status',
        type: 'GET',
        cache: false,
        async: false,
        data: {
            user_id: USER_ID
        },
        success: function(result) {
            console.log("Отпределён статус кнопки?" + result);
            status = result;
        },
        error: function (error) {
            console.log("ошибка с определением статуса кнопки" + error);
        }
    });
    return status;
}

// TODO изменяем цвет таба при отправке запроса "стать админом"
function changeColor() {
    let tab = document.getElementById('new_admin');
    tab.style.backgroundColor = '#aa3402';
    tab.style.color = '#ffffff';
}

// // TODO Получаем запрос на "стать админом"
// function adminRequest() {
//     let clickButton = false;
//     let user = showAuthorizedUser();
//
//     $.ajax({
//         url: '/want_to_be_admin',    //нужный url вставить
//         type: 'POST',
//         cache: false,
//         async: false,
//         data: {
//             id: user.id,
//             message: "Я, " + user.name + ", хочу стать администратором Вашего ЗАМЕЧАТЕЛЬНОГО сайта."
//         },
//         success: function(result) {
//             console.log(result);
//             clickButton = true;
//         },
//         error: function (error) {
//             console.log("ошибка запроса 'стать админом'" + error);
//             clickButton = false;
//         }
//     });
//     return clickButton;
// }

// // TODO достаём пользователей из ВК
// function showVKUsers() {
//     //let haveNewComment = false;
//     //     $.ajax({
//     //         url: '/get_new_admin',
//     //         type: 'GET',
//     //         async: false,
//     //         contentType: 'application/json',
//     //         success: function (responseFromVK) {
//     //             let len = responseFromVK.response.count;
//     //             if (len !== 0) {    //првоеряем есть ли у нас комментарии уже сейчас или нет
//     //                 haveNewComment = true;
//     //             }
//     //             // TODO Вызываем пользователей и из БД
//     //             let users = getNewAdminFromDB();
//     //
//     //             let table;
//     //
//     //             for (i = 0; i < len; i++) {
//     //                 let idCom = responseFromVK.response.items[i].id;
//     //                 let message = String(responseFromVK.response.items[i].text);
//     //                 let userId = users[i].id;
//     //                 table = table +
//     //                     "<tr>" +
//     //                     "   <td>" + idCom + "</td>" +
//     //                     "   <td>" + message + "</td>" +
//     //                     "   <td>" + userId + "</td>" +
//     //                     "   <td>" + `<button type=\"button\" class=\"btn btn-primary\" onclick='editRoleUser("${idCom}", "${userId}")'>Добавить роль</button>`
//     //                     + " "
//     //                     + `<button type=\"button\" class=\"btn btn-danger\" onclick='refuseRoleUpgrade("${idCom}", "${message}")'>Отказать</button>`
//     //                     + "</td>" +
//     //                 "</tr>";
//     //             }
//     //             $("#get_VK").html(table);
//     //         },
//     //         error: function (error) {
//     //             console.log(error);
//     //         }
//     //     });
//     //     return haveNewComment;
//     let responseFromVK;
//     $.ajax({
//         url: '/get_new_admin',
//         type: 'GET',
//         async: false,
//         contentType: 'application/json',
//         success: function (response) {
//             responseFromVK = response;
//         },
//         error: function (error) {
//             console.log(error);
//         }
//     });
//     return responseFromVK;
// }

// //TODO функция обновления РОЛИ пользователя
// function editRoleUser(COMMENT_ID, USER_ID) {
//     console.log("В начале метода. Переданный id пользователя: " + USER_ID);
//     let user = showUser(USER_ID);
//     console.log("после запроса к пользователю");
//     $.ajax({
//         url: '/update_user',
//         type: 'POST',
//         cache: false,
//         async: false,
//         contentType: 'application/json',
//         data: JSON.stringify({
//             id: user.id,
//             lastName: user.lastName,
//             name: user.name,
//             email: user.email,
//             password: null, //не передаём пароль, ибо он зашифрованный, что нельзя будет сравнить
//             role: "ROLE_ADMIN"
//         }),
//         success: function(result) {
//             console.log(result);
//             deleteComment(COMMENT_ID);
//             changeUserStatusToFalseDB(user.id);
//             showVKUsers();
//             showAllUsers();
//         },
//         error: function (error) {
//             console.log("какая-то ошибка обновления" + error);
//         }
//     });
// }

// TODO Удаляем комментарий с запросом на получение РОЛИ "АДМИН"
function deleteComment(commentId) {
    $.ajax({
        url: '/delete_post',
        type: 'GET',
        cache: false,
        async: false,
        data: {
            commentId: commentId
        },
        success: function(result) {
            console.log(result);
        },
        error: function (error) {
            console.log("какая-то ошибка удаления комментария" + error);
        }
    });
}

// //TODO функция отказа в обновлении РОЛИ пользователя
// function refuseRoleUpgrade(COMMENT_ID, MESSAGE) {
//     let refuse = String("– " + MESSAGE +
//         "\n– Простите, но мы не можем повысить Вас в правах доступа к нашему сайту. (Администрация сайта)");
//
//     $.ajax({
//         url: '/edit_comment',
//         type: 'GET',
//         cache: false,
//         async: false,
//         data: {
//             comment_id: COMMENT_ID,
//             message: refuse,
//         },
//         success: function () {
//             console.log("обновили сообщение отказа");
//             showVKUsers();
//         },
//         error: function (error) {
//             console.log(error);
//         }
//     });
// }



// //TODO функция проверки отказа в обновлении РОЛИ пользователя
// function checkRefuseUpgradeRoleUser(USER_ID) {
//     let user = showAuthorizedUser();
//
//     let goodOrNot = false;
//
//     if (user.status !== null && user.status.statusUpdate) {
//         goodOrNot = true;
//     }
//
//     let messageFromAdmin = "(Администрация сайта)"
//
//     $.ajax({
//         url: '/get_new_admin',
//         type: 'GET',
//         async: false,
//         contentType: 'application/json',
//         success: function (responseFromVK) {
//             let len = responseFromVK.response.count;
//             for (i = 0; i < len; i++) {
//                 if (responseFromVK.response.items[i].text.indexOf(messageFromAdmin) !== -1) { //TODO подумать, как обойти это!!!
//                     deleteComment(responseFromVK.response.items[i].id);
//                     changeUserStatusToFalseDB(USER_ID);
//                     console.log("Удачный запрос на измненеие статуса в БД при нажатии на кнопку")
//                 }
//             }
//             console.log("Удачный, но нет, запрос на измненеие статуса в БД при нажатии на кнопку")
//         },
//         error: function (error) {
//             console.log(error);
//         }
//     });
//     return goodOrNot;
// }


//TODO Работа с БД "СТАТУС"


// TODO Получаем пользователей, которые хотят получить роль АДМИНИСТРАТОР, из БД!
// function getNewAdminFromDB() {
//     //let users;
//     //     $.ajax({
//     //         url: '/get_new_admin_from_DB',
//     //         type: 'GET',
//     //         async: false,
//     //         contentType: 'application/json',
//     //         success: function (listUsers) {
//     //             users = listUsers;
//     //             console.log("Пользователеи, которые хотят быть новыми админами");
//     //             console.log(listUsers);
//     //             console.log("________________")
//     //         },
//     //         error: function (error) {
//     //             console.log("Ошибка в получении пользователей \"НА АДМИНКУ\" из БД " + error);
//     //         }
//     //     });
//     //     return users;
//     let upStatus = false;
//     $.ajax({
//         url: '/get_new_admin_from_DB',
//         type: 'GET',
//         async: false,
//         contentType: 'application/json',
//         success: function (listUsers) {
//
//             let responseFromVK = showVKUsers();
//
//             let table;
//
//             for (i = 0; i < listUsers.length; i++) {
//                 let idCom = responseFromVK.response.items[i].id;
//                 let message = responseFromVK.response.items[i].text;
//                 let userId = listUsers[i].id;
//
//                 if (listUsers[i].status.statusUpdate === false && message.indexOf(listUsers[i].name) !== -1) {
//                 //     TODO тут хотел избавиться от привязки в ВК. Но ничего не вышло пока. Подумай!
//                 }
//
//                 table = table +
//                     "<tr>" +
//                     "   <td>" + idCom + "</td>" +
//                     "   <td>" + message + "</td>" +
//                     "   <td>" + userId + "</td>" +
//                     "   <td>" + `<button type=\"button\" class=\"btn btn-primary\" onclick='editRoleUser("${idCom}", "${userId}")'>Добавить роль</button>`
//                     + " "
//                     + `<button type=\"button\" class=\"btn btn-danger\" onclick='refuseRoleUpgrade("${idCom}", "${message}")'>Отказать</button>`
//                     + "</td>" +
//                     "</tr>";
//             }
//             $("#get_VK").html(table);
//         },
//         error: function (error) {
//             console.log("Ошибка в получении пользователей \"НА АДМИНКУ\" из БД " + error);
//         }
//     });
//     return users;
//
//     let haveNewComment = false;
//     $.ajax({
//         url: '/get_new_admin',
//         type: 'GET',
//         async: false,
//         contentType: 'application/json',
//         success: function (responseFromVK) {
//             let len = responseFromVK.response.count;
//             if (len !== 0) {    //првоеряем есть ли у нас комментарии уже сейчас или нет
//                 haveNewComment = true;
//             }
//             // TODO Вызываем пользователей и из БД
//             let users = getNewAdminFromDB();
//
//             let table;
//
//             for (i = 0; i < len; i++) {
//                 let idCom = responseFromVK.response.items[i].id;
//                 let message = String(responseFromVK.response.items[i].text);
//                 let userId = users[i].id;
//                 table = table +
//                     "<tr>" +
//                     "   <td>" + idCom + "</td>" +
//                     "   <td>" + message + "</td>" +
//                     "   <td>" + userId + "</td>" +
//                     "   <td>" + `<button type=\"button\" class=\"btn btn-primary\" onclick='editRoleUser("${idCom}", "${userId}")'>Добавить роль</button>`
//                     + " "
//                     + `<button type=\"button\" class=\"btn btn-danger\" onclick='refuseRoleUpgrade("${idCom}", "${message}")'>Отказать</button>`
//                     + "</td>" +
//                     "</tr>";
//             }
//             $("#get_VK").html(table);
//         },
//         error: function (error) {
//             console.log(error);
//         }
//     });
//     return haveNewComment;
// }

// TODO После того, как дали новому пользователю роль "АДМИН", меняем статус этого пользователя в БД!
function changeUserStatusToFalseDB(ID) {
    $.ajax({
        url: '/change_user_status_to_false',
        type: 'POST',
        cache: false,
        async: false,
        data: {
            userId: ID
        },
        success: function(result) {
            console.log("Изменили статус в БД на false " + result);
        },
        error: function (error) {
            console.log("какая-то ошибка обновления " + error);
        }
    });
}