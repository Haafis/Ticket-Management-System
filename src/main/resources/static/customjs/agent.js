var AgentTable;

$(document).ready(function() {
    $('#id').val(0);
    AgentTable = $('#AgentTable').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "/getAllagentDetails",
            "type": "POST",
            "dataType": "json",
            "contentType": "application/json",
            "data": function(d) {
                return JSON.stringify(d);
            }
        },
        "columns": [
            { "data": null, "title": "Serial Number", "width": "5%" },
            {
                "data": function(row) {
                    return row.firstname + ' ' + row.lastname;
                },
                "title": "Name",
                "width": "20%"
            },
            { "data": "username", "title": "User Name", "width": "15%" },
            { "data": "password", "title": "Password", "width": "15%" },
            { "data": "email", "title": "Email", "width": "15%" },

            { "data": "phonenumber", "title": "Phone Number", "width": "8%" },
            { "data": "role", "title": "Role", "width": "8%" },
            {
                "data": "status",
                "title": "Status",
                "width": "10%",
                "render": function(data, type, row, meta) {
                    if (data == 1) {
                        return '<span class="label label-success" style="color: green;">Active</span>';
                    } else {
                        return '<span class="label label-danger" style="color: red;">Inactive</span>';
                    }
                }
            },
            { "data": "department", "title": "Department", "width": "8%" },
            {
                "data": "dateofjoining",
                "title": "Date of Joining",
                "width": "10%",
                "render": function(data, type, row, meta) {

                    var dateObj = new Date(data);
                    var formattedDate = ('0' + dateObj.getUTCDate()).slice(-2) + '-' + ('0' + (dateObj.getUTCMonth() + 1)).slice(-2) + '-' + dateObj.getUTCFullYear();
                    return formattedDate;
                }
            },
            { "data": "address", "title": "Address", "width": "10%" },
            {
                "data": null,
                "title": "Actions",
                "width": "12%",
                "render": function(data, type, row, meta) {
                    return '<button class="btn btn-primary btn-sm btn-flat" onclick="EditAgentDetails(' + row.id + ')">&#x270E;</button>' + " " +
                           '<button class="btn btn-danger btn-sm" style="background-color: red; color: white;" onclick="DeleteAgentDetails(' + row.id + ')">X</button>';
                }
            }
        ],
        "columnDefs": [
            {
                "targets": 0,
                "render": function(data, type, row, meta) {
                    var displayIndex = meta.row + meta.settings._iDisplayStart + 1;
                    return displayIndex;
                }
            }
        ]
    });
});

function addAgent(event) {
    event.preventDefault();
    var id = $('#id').val();
    var firstname = $('#firstname').val();
    var lastname = $('#lastname').val();
    var username = $('#username').val();
    var password = $('#password').val();
    var email = $('#email').val();
    var phonenumber = $('#phonenumber').val();
    var role = $('#role').val();
    var status = $('#status').val();
    var department = $('#department').val();
    var dateofjoining = $('#dateofjoining').val();
    var address = $('#address').val();


    if (!firstname || !lastname || !email || !phonenumber || !role ||!status || !department || !dateofjoining || !address|| !username || !password){
        Swal.fire({
            title: "Error!",
            text: "All fields are required.",
            icon: "warning"
        });
        return;
    }

    if (!validateEmail(email)) {
        Swal.fire({
            title: "Error!",
            text: "Invalid email format.",
            icon: "warning"
        });
        return;
    }

    if (isNaN(phonenumber) || phonenumber.length < 10) {
        Swal.fire({
            title: "Error!",
            text: "Invalid phone number.",
            icon: "warning"
        });
        return;
    }

    var Data = {
        id: id,
        firstname: firstname,
        lastname: lastname,
        email: email,
        phonenumber: phonenumber,
        role: role,
        status: status,
        department: department,
        dateofjoining: dateofjoining,
        address: address,
        username: username,
        password: password
    };

    var jsonData = JSON.stringify(Data);

    $.ajax({
        type: "POST",
        url: "/addagentdetails",
        contentType: "application/json",
        data: jsonData,
        success: function(response) {
            if (response == "Agent added successfully") {
           Swal.fire({
                title: "Success!",
                text:"Agent Added Sucessfully",
                icon: "success"
            });

                clearAll();
                AgentTable.ajax.reload();
            }
        },
        error: function(xhr, status, error) {
            Swal.fire({
                title: "Error!",
                text: xhr.responseText,
                icon: "error"
            });
            clearAll();
             AgentTable.ajax.reload();
        }
    });
}

function validateEmail(email) {
    var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

function clearAll() {
    $('#id').val(0);
   $('#firstname').val("");
    $('#lastname').val("");
    $('#email').val("");
    $('#phonenumber').val("");
    $('#role').val("");
    $('#status').val(1);
    $('#department').val("");
    $('#dateofjoining').val("");
    $('#address').val("");
    $('#dateofjoining').val("");
    $('#address').val("");
    $('#username').val("");
    $('#password').val("");
}


function EditAgentDetails(id) {
    $('#id').val(id);
    $.ajax({
        type: "GET",
        url: "/getAgentDetailsByID",
        contentType: "application/json",
        data: { id: id },
        success: function(response) {
            $("#firstname").val(response.firstname);
            $("#lastname").val(response.lastname);
            $("#email").val(response.email);
            $("#phonenumber").val(response.phonenumber);
            $("#role").val(response.role);
            $("#status").val(response.status);
            $("#department").val(response.department);
            $("#address").val(response.address);
            var dateofjoining = new Date(response.dateofjoining);
            var formattedDate = dateofjoining.toISOString().split('T')[0];
            $("#dateofjoining").val(formattedDate);
            $('#username').val(response.username);
            $('#password').val(response.password);
        },
        error: function(xhr, status, error) {

        }
    });
}


function DeleteAgentDetails(id) {
    $.ajax({
        type: "DELETE",
        url: "/deleteAgentDetailsByID",
        data: { id: id },
        success: function(serverMessageDto) {
            if (serverMessageDto.successFlag == 1) {
        Swal.fire({
            title: "Deleted!",
            text: "Deleted Sucessfully.",
            icon: "success"
        });

         AgentTable.ajax.reload();
            } else {
               Swal.fire({
                   title: "Oops!",
                   text: "Unable to Delete.",
                   icon: "error"
               });
               AgentTable.ajax.reload();
            }

        },
        error: function(xhr, status, error) {


        }
    });
}

