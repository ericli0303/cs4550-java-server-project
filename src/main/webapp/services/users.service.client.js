function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    // this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.url = 'https://wbdv-generic-server.herokuapp.com/api/ericli0303/users';
    var self = this;
    function createUser(newUser) {
        return fetch(this.url, {
            method: "POST",
            body: JSON.stringify(newUser),
            headers: {
                "content-type": "application/json"
            }
        })
            .then(response => response.json())
    }

    function findAllUsers() {
        return fetch(this.url).then(response => response.json())
        //why must we need this.url instead of just url
    }

    function findUserById(userId) {
        return fetch(`${this.url}/${userId}`, {
            method: "GET"
        })
            .then(response => response.json())
    }

    function updateUser(userId, user) {
        return fetch(`${this.url}/${userId}`, {
            method: "PUT",
            body: JSON.stringify(user),
            headers: {
                "content-type": "application/json"
            }
        })
            .then(response => response.json())
    }

    function deleteUser(userId) {
        return fetch(`${this.url}/${userId}`, { method: "DELETE" })
            .then(response => response.json())
    }
}