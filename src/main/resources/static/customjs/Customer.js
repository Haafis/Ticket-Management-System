
var CustomerTable;
$(document).ready(function() {
    $('#id').val(0);
    CustomerTable = $('#CustomerTable').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "/getAllCustomertDetails",
            "type": "POST",
            "dataType": "json",
            "contentType": "application/json",
            "data": function(d) {
                return JSON.stringify(d);
            }
        },
        "columns": [
            { "data": null, "title": "Serial Number", "width": "5%" },
            { "data": "firstname", "title": "First Name", "width": "10%" },
            { "data": "lastname", "title": "Last Name", "width": "10%" },
            { "data": "email", "title": "Email", "width": "15%" },
            { "data": "phonenumber", "title": "Phone Number", "width": "10%" },
            { "data": "complaint_notes", "title": "Complaints", "width": "10%" },
            {
                "data": null,
                "title": "Actions",
                "width": "10%",
                "render": function(data, type, row, meta) {
                    return '<button class="btn btn-primary btn-sm btn-flat" onclick="EditCustomerDetails(' + row.id + ')">&#x270E;</button>' + " " +
                           '<button class="btn btn-danger btn-sm" style="background-color: red; color: white;" onclick="DeleteCustomerDetails(' + row.id + ')">X</button>';
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

   hidetable();
});

function RegisterComplaint(event) {
    event.preventDefault();

    var id = $('#id').val();
    var firstname = $('#firstname').val();
    var lastname = $('#lastname').val();
    var email = $('#email').val();
    var phonenumber = $('#phonenumber').val();
    var complaint_notes = $('#complaint_notes').val();


    if (!firstname || !lastname || !email || !phonenumber || !complaint_notes) {
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
        complaint_notes: complaint_notes
    };

    var jsonData = JSON.stringify(Data);

    $.ajax({
        type: "POST",
        url: "/addcustomerdetails",
        contentType: "application/json",
        data: jsonData,
        success: function(response) {
            if (response.successFlag == 1) {
                Swal.fire({
                    title: "Success!",
                    html: "Ticket ID: <strong>AB#2024" + response.masterID + "</strong><br><br>" +
                          "<p>Please note this Ticket ID for further enquiry.</p>" +
                          "<button id='copyButton' class='swal2-confirm swal2-styled' onclick='copyTicketID(" + response.masterID +")'>Copy Ticket#</button>",
                    icon: "success",
                    showConfirmButton: false
                });

                clearAll();
              CustomerTable.ajax.reload();
            }
        },
        error: function(xhr, status, error) {
            Swal.fire({
                title: "Error!",
                text: xhr.responseText,
                icon: "error"
            });
            clearAll();
             CustomerTable.ajax.reload();
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
    $('#complaint_notes').val("");
}

function copyTicketID(id) {
    var ticketID = "AB#2024" + id + "";
    navigator.clipboard.writeText(ticketID).then(() => {
        Swal.fire({
            title: "Copied!",
            text: "Ticket ID has been copied to clipboard.",
            icon: "success"
        });
    }).catch(err => {
        Swal.fire({
            title: "Error!",
            text: "Failed to copy Ticket ID.",
            icon: "error"
        });
    });


}

function EditCustomerDetails(id) {
    $('#id').val(id);
    $.ajax({
        type: "GET",
        url: "/getCustomerDetailsByID",
        contentType: "application/json",
        data: { id: id },
        success: function(response) {
            $("#firstname").val(response.firstname);
            $("#lastname").val(response.lastname);
            $("#email").val(response.email);
            $("#phonenumber").val(response.phonenumber);
            $("#complaint_notes").val(response.complaint_notes);
        },
        error: function(xhr, status, error) {

        }
    });
}

function DeleteCustomerDetails(id) {
    $.ajax({
        type: "DELETE",
        url: "/deletecustomerdetailsbyid",
        data: { id: id },
        success: function(serverMessageDto) {
            if (serverMessageDto.successFlag == 1) {
        Swal.fire({
            title: "Deleted!",
            text: "Deleted Sucessfully.",
            icon: "success"
        });
       CustomerTable.ajax.reload();

            }
        },
        error: function(xhr, status, error) {
        }
    });
}
function Logout(){
   window.location.href = '/index';
}

function hidetable(){
        if (userID > 0) {
           $("#CustomerTableDiv").show();
        } else {
           $("#CustomerTableDiv").hide();
        }
}