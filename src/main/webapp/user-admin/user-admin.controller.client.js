(function () {
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld, $roleFld;
    var $userRowTemplate, $tbody;
    var $currUser, $updateBtn;
    const userService = new AdminUserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#usernameFld")
        $passwordFld = $("#passwordFld")//why is there a password fld
        $removeBtn = $(".wbdv-remove")
        $editBtn = $(".wbdv-edit")
        $createBtn = $(".wbdv-create")
        $updateBtn = $(".wbdv-update")
        $firstNameFld = $("#firstNameFld")
        $lastNameFld = $("#lastNameFld")
        $roleFld = $("#roleFld")
        $userRowTemplate = $(".wbdv-template")
        $tbody = $(".wbdv-tbody")


        findAllUsers()

        $createBtn.click(createUser)
        $updateBtn.click(updateUser)

    }
    function createUser() {
        const newUser = {
            username: $usernameFld.val(),
            first: $firstNameFld.val(),
            last: $lastNameFld.val(),
            role: $roleFld.val()
        }
        userService.createUser(newUser)
        findAllUsers()
    }

    function findAllUsers() {
        userService.findAllUsers().then(_users => renderUsers(_users))
    }
    function findUserById() {
        const userId = $currUser._id
        userService.findUserById(userId)
    }

    function deleteUser(user) {
        const userId = user._id
        userService.deleteUser(userId)
            .then(status => console.log(status))
        findAllUsers()
    }
    function selectUser(user) {
        $usernameFld.val(user.username)
        $firstNameFld.val(user.first)
        $lastNameFld.val(user.last)
        $passwordFld.val(user.password)
        $roleFld.val(user.role)
        $currUser = user
    }

    function updateUser() {
        const updatedFields = {
            username: $usernameFld.val(),
            first: $firstNameFld.val(),
            last: $lastNameFld.val(),
            password: $passwordFld.val(),
            role: $roleFld.val()
        }
        userService.updateUser($currUser._id, updatedFields)
            .then(status => console.log(status))
        findAllUsers()
    }


    function renderUser(user) {
        const $rowClone = $userRowTemplate.clone();
        $rowClone.removeClass(".wbdv-hidden")
        $rowClone.find(".wbdv-username").html(user.username)
        $rowClone.find(".wbdv-first-name").html(user.first)
        $rowClone.find(".wbdv-last-name").html(user.last)
        $rowClone.find(".wbdv-role").html(user.role)

        $rowClone.find(".wbdv-edit").click(() => selectUser(user))
        $rowClone.find(".wbdv-remove").click(() => deleteUser(user))

        $tbody.append($rowClone)
    }


    function renderUsers(users) {
        $tbody.empty();
        for (let i = 0; i < users.length; i++) {
            const user = users[i];
            renderUser(user)
        }
    }
})();