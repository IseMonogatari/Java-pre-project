function postToVKAndChangeUserStatusToTrueInDB() {
    let user = showAuthorizedUser();

    $.ajax({
        url: '/want_to_be_admin',
        type: 'POST',
        cache: false,
        async: false,
        data: {
            id: user.id,
            message: "Я, " + user.name + ", хочу стать администратором Вашего ЗАМЕЧАТЕЛЬНОГО сайта."
        },
        success: function(result) {
            console.log(result);
        },
        error: function (error) {
            console.log("ошибка запроса 'стать админом'" + error);
        }
    });
}

// TODO Что решили по поводу роли АДМИН?
function checkUserStatusInDBAndDeleteCommentInVKIfUserStatusChangeToFalse() {
    if (checkUpgradeRoleUser()) {
        set_message_error("#refuseMessage", "К сожалению, но да...");
        $('#wantToBeAdmin').attr('disabled', false); //делаю доступной кнопку для нового запроса "хочу стать админом"
    } else {
        set_message_info("#refuseMessage", "Запрос пока в обработке.");
    }
}

//TODO функция проверки отказа в обновлении РОЛИ пользователя
function checkUpgradeRoleUser() {
    let user = showAuthorizedUser();
    let goodOrNot = false;

    if (user.status !== null && user.status.statusUpdate === false) {
        goodOrNot = true;

        let messageFromAdmin1 = user.name;
        let messageFromAdmin2 = "(Администрация сайта)"

        $.ajax({
            url: '/get_new_admin',
            type: 'GET',
            async: false,
            contentType: 'application/json',
            success: function (responseFromVK) {
                let len = responseFromVK.response.count;
                for (i = 0; i < len; i++) {
                    let message = responseFromVK.response.items[i].text;
                    if (message.indexOf(messageFromAdmin1) !== -1 &&
                        message.indexOf(messageFromAdmin2) !== -1) {//содержит ли комментарий имя пользователя и отзыв от админа
                        deleteComment(responseFromVK.response.items[i].id);
                        console.log("Удачный запрос на удаление комментария из ВК при нажатии на кнопку")
                    }
                }
                console.log("Удачный, но нет, запрос удаление комментария из ВК при нажатии на кнопку")
            },
            error: function (error) {
                console.log(error);
            }
        });
    }
    return goodOrNot;
}





function getUsersFromVK() {
    let responseFromVK;
    $.ajax({
        url: '/get_new_admin',
        type: 'GET',
        async: false,
        contentType: 'application/json',
        success: function (response) {
            responseFromVK = response;
        },
        error: function (error) {
            console.log(error);
        }
    });
    return responseFromVK;
}

// TODO Получаем пользователей, которые хотят получить роль АДМИНИСТРАТОР, из БД!
function getUserStatusFromDBForTable() {
    $.ajax({
        url: '/get_new_admin_from_DB',
        type: 'GET',
        async: false,
        contentType: 'application/json',
        success: function (listUsers) {
            console.log("список желающих \"админа\"");
            let table;

            for (i = 0; i < listUsers.length; i++) {
                let userId = listUsers[i].id;
                let userName = listUsers[i].name;

                table = table +
                    "<tr>" +
                    "   <td>" + userId + "</td>" +
                    "   <td>" + userName + "</td>" +
                    "   <td>" + `<button type=\"button\" class=\"btn btn-primary\" onclick='changeUserRoleToAdminInDBAndDeleteCommentInVK("${userId}")'>Добавить роль</button>`
                    + " "
                    + `<button type=\"button\" class=\"btn btn-danger\" onclick='notChangeUserRoleToAdminInDBAndChangeCommentInVK("${userId}")'>Отказать</button>`
                    + "</td>" +
                    "</tr>";
            }
            $("#get_VK").html(table);
        },
        error: function (error) {
            console.log("Ошибка в получении пользователей \"НА АДМИНКУ\" из БД " + error);
        }
    });
}

// TODO Проверяем в БД, если кто-то, кто хочет полуить роль админа
function getUserStatusFromDBForButton() {
    let userToExist = false;
    $.ajax({
        url: '/get_new_admin_from_DB',
        type: 'GET',
        async: false,
        contentType: 'application/json',
        success: function (listUsers) {
            if (listUsers.length > 0) {
                userToExist = true;
                console.log("Всё окей для кнопки");
            }
        },
        error: function (error) {
            console.log("Ошибка в получении пользователей \"НА АДМИНКУ\" из БД для кнопки" + error);
        }
    });
}

//TODO функция обновления РОЛИ пользователя
function changeUserRoleToAdminInDBAndDeleteCommentInVK(USER_ID) {
    let user = showUser(USER_ID);
    $.ajax({
        url: '/update_user',
        type: 'POST',
        cache: false,
        async: false,
        contentType: 'application/json',
        data: JSON.stringify({
            id: user.id,
            lastName: user.lastName,
            name: user.name,
            email: user.email,
            password: null, //не передаём пароль, ибо он зашифрованный, что нельзя будет сравнить
            role: "ROLE_ADMIN"
        }),
        success: function(result) {
            let responseFromVK = getUsersFromVK();
            let len = responseFromVK.response.count;

            for (i = 0; i < len; i++) {
                let message = String(responseFromVK.response.items[i].text);
                if (message.indexOf(user.name) !== -1) {    //если содержит имя пользователя
                    deleteComment(responseFromVK.response.items[i].id); //удаляем комментарий
                    console.log("Успешно удалили комментарий из ВК");
                }
            }
            changeUserStatusToFalseDB(user.id); //Изменяем статус на "админ отреагировал на запрос"
            getUserStatusFromDBForTable();  //обновляем страницу с запросами на новых админов
            showAllUsers(); //обновняем таблицу всех пользователей
            console.log(result);
        },
        error: function (error) {
            console.log("какая-то ошибка обновления в новом методе " + error);
        }
    });
}

//TODO функция отказа в обновлении РОЛИ пользователя
function notChangeUserRoleToAdminInDBAndChangeCommentInVK(USER_ID) {
    changeUserStatusToFalseDB(USER_ID);

    let MESSAGE;
    let COMMENT_ID;
    let responseFromVK = getUsersFromVK();
    let user = showUser(USER_ID);

    let len = responseFromVK.response.count;
    for (i = 0; i < len; i++) {
        let message = String(responseFromVK.response.items[i].text);
        if (message.indexOf(user.name) !== -1) {    //если содержит имя пользователя
            COMMENT_ID = responseFromVK.response.items[i].id;
            MESSAGE = message;
            console.log("Успешно получили текст и id комментария из ВК");
        }
    }
    refuseRoleUpgrade_sendToVK(COMMENT_ID, MESSAGE);
}

//TODO функция отказа в обновлении РОЛИ пользователя
function refuseRoleUpgrade_sendToVK(COMMENT_ID, MESSAGE) {
    let refuse = String("– " + MESSAGE +
        "\n– Простите, но мы не можем повысить Вас в правах доступа к нашему сайту. (Администрация сайта)");

    $.ajax({
        url: '/edit_comment',
        type: 'GET',
        cache: false,
        async: false,
        data: {
            comment_id: COMMENT_ID,
            message: refuse,
        },
        success: function () {
            console.log("обновили сообщение отказа");
            getUserStatusFromDBForTable();  //обновляем страницу с запросами на новых админов
        },
        error: function (error) {
            console.log(error);
        }
    });
}