async function ForEditModal(id) {
    $('#edit').modal('show')
    let urlEdit = 'api/users/' + id;
    let usersEdit = await fetch(urlEdit);
    const formEdit = document.forms["formEdit"];
    if (usersEdit.ok) {
        await usersEdit.json().then(user => {
            formEdit.idEdit.value = `${user.id}`;
            formEdit.firstnameEdit.value = `${user.firstname}`;
            formEdit.lastnameEdit.value = `${user.lastname}`;
            formEdit.ageEdit.value = `${user.age}`;
            formEdit.emailEdit.value = `${user.email}`;
            formEdit.passwordEdit.value = `${user.password}`;
        })
    } else {
        alert(`Error, ${usersEdit.status}`)
    }
}

const applicantForm1 = document.getElementById('formEdit')
applicantForm1.addEventListener('submit', handleFormSubmitEdit)

async function handleFormSubmitEdit(event) {
    // Просим форму не отправлять данные самостоятельно
    event.preventDefault()
    let EditUserRoles = [];
    for (let i = 0; i < formEdit.roles.options.length; i++) {
        if (formEdit.roles.options[i].selected) EditUserRoles.push({
            id: formEdit.roles.options[i].value,
            name: "ROLE_" + formEdit.roles.options[i].text
        })
    }
    fetch("http://localhost:8080/api/users/" + document.getElementById('idEdit').value, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({

            firstname: formEdit.firstnameEdit.value,
            lastname: formEdit.lastnameEdit.value,
            age: formEdit.ageEdit.value,
            email: formEdit.emailEdit.value,
            password: formEdit.passwordEdit.value,
            roles: EditUserRoles
        })
    }).then(() => {
        $('#editClose').click();
        allUsers();
    })
}
