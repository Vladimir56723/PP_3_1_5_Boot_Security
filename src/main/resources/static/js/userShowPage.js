$(async function () {
    await showUser1();
});
const table11 = $('#showUserTable1');
const email = $('#userEmail1');
const roles = $('#userRoles1');

async function showUser1() {
    table11.empty()
    fetch("http://localhost:8080/userPage")
        .then(res => res.json())
        .then(data => {
            let userEmail1 = data.username;
            email.append(userEmail1);
            let userRole1 = " " + data.roles.map(item => item.name.substring(5) + " ");
            roles.append(userRole1);
            let tableUser1 = `$( 
                        <tr>
                            <td>${data.firstname}</td> 
                             <td>${data.lastname}</td>                                                        
                            <td>${data.age}</td>
                            <td>${data.username}</td>
                            <td>${userRole1}</td>
                        </tr>
`;
            table11.append(tableUser1);
        })
}

