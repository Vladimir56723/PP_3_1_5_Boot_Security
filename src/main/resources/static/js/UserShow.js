$(async function () {
    await showUser();
});
const table1 = $('#showUserTable');
const email = $('#userEmail');
const roles = $('#userRoles');

async function showUser() {
    table1.empty()
    fetch("http://localhost:8080/api/user")
        .then(res => res.json())
        .then(data => {
            let userEmail = data.username;
            email.append(userEmail);
            let userRole = " " + data.roles.map(item => item.name.substring(5) + " ");
            roles.append(userRole);
            let tableUser = `$( 
                        <tr>
                            <td>${data.firstname}</td> 
                             <td>${data.lastname}</td>                                                        
                            <td>${data.age}</td>
                            <td>${data.username}</td>
                            <td>${userRole}</td>
                        </tr>
`;
            table1.append(tableUser);
        })
}

