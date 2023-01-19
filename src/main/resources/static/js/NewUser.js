const form = document.forms["NewForm"];
const applicantForm = document.getElementById('NewForm')
applicantForm.addEventListener('submit', handleFormSubmit)

async function handleFormSubmit(event) {
    // Просим форму не отправлять данные самостоятельно
    event.preventDefault();
    let newUserRoles = [];
    for (let i = 0; i < form.roles.options.length; i++) {
        if (form.roles.options[i].selected) newUserRoles.push({
            id: form.roles.options[i].value,
            name: "ROLE_" + form.roles.options[i].text
        })
    }
    fetch("http://localhost:8080/api/save", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            firstname: form.firstname.value,
            lastname: form.lastname.value,
            age: form.age.value,
            email: form.email.value,
            password: form.password.value,
            roles: newUserRoles
        })
    }).then(() => {
        form.reset();
        allUsers();
        $('#allTable').click();
    })
}
