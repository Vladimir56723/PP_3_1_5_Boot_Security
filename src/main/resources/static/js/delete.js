async function FormDeleteModal(id) {
    $('#delete').modal('show')
    let urlDelete = 'api/users/' + id;
    let usersDelete = await fetch(urlDelete);
    const formDelete = document.forms["formDelete"];
    if (usersDelete.ok) {
        await usersDelete.json().then(user => {
            formDelete.idDelete.value = `${user.id}`;
            formDelete.firstnameDelete.value = `${user.firstname}`;
            formDelete.lastnameDelete.value = `${user.lastname}`;
            formDelete.ageDelete.value = `${user.age}`;
            formDelete.emailDelete.value = `${user.email}`;
            formDelete.passwordDelete.value = `${user.password}`;
        })
    } else {
        alert(`Error, ${usersDelete.status}`)
    }
}

const applicantForm2 = document.getElementById('formDelete')
applicantForm2.addEventListener('submit', handleFormSubmitDelete)

async function handleFormSubmitDelete(eve) {
    // Просим форму не отправлять данные самостоятельно
    eve.preventDefault()

    fetch("http://localhost:8080/api/delete/" + document.getElementById('idDelete').value, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(() => {
        $('#deleteClose').click();
        allUsers();
    })
}
