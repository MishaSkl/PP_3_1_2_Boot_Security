const form_ed = document.getElementById('formForEditing');
const id_ed = document.getElementById('id_ed');
const username_ed = document.getElementById('username_ed');
const surname_ed = document.getElementById('surname_ed');
const age_ed = document.getElementById('age_ed');
const email_ed = document.getElementById('email_ed');
const password_ed = document.getElementById('password_ed');

async function editModalData(id) {
    const urlDataEd = '/rest/admin/users/' + id;
    let usersPageEd = await fetch(urlDataEd);
    if (usersPageEd.ok) {
        let userData =
            await usersPageEd.json().then(async user => {
                id_ed.value = `${user.id}`;
                username_ed.value = `${user.username}`;
                surname_ed.value = `${user.surname}`;
                age_ed.value = `${user.age}`;
                email_ed.value = `${user.email}`;
                password_ed.value = `${user.password}`;
            })
    } else {
        alert(`Error, ${usersPageEd.status}`)
    }
}

async function editUser() {
    let urlEdit = '/rest/admin/users/' + id_ed.value
    let listOfRole = [];

    for (let i = 0; i < form_ed.rolesForEditing.options.length; i++) {
        if (form_ed.rolesForEditing.options[i].selected) {
            listOfRole.push("ROLE_" + form_ed.rolesForEditing.options[i].value);
        }
    }

    let method = {
        method: 'PUT',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: form_ed.username.value,
            surname: form_ed.surname.value,
            age: form_ed.age.value,
            email: form_ed.email.value,
            password: form_ed.password.value,
            roles: listOfRole
        })
    }

    await fetch(urlEdit, method).then(() => {
        $('#editCloseBtn').click();
        getAllUserRest();
    })
}


