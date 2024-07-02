
var ManageComplaintsTable;

$(document).ready(function() {
    $('#ManageComplaintsForm').on('submit', function(e) {
        e.preventDefault();
        ManageComplaints();
    });

    $('#id').val(0);
    ManageComplaintsTable = $('#ManageComplaintsTable').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "/getAllMangedCompDetails",
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
                "data": "issueSolvedDate",
                "title": "Issue Solved Date",
                "width": "8%",
                "render": function(data, type, row, meta) {
                    if (data) {
                        var date = new Date(data);
                        var day = String(date.getDate()).padStart(2, '0');
                        var month = String(date.getMonth() + 1).padStart(2, '0');
                        var year = date.getFullYear();
                        return day + '-' + month + '-' + year;
                    }
                    return "";
                }
            },
            { "data": "status", "title": "Status", "width": "10%" },
            { "data": "description", "title": "Description", "width": "10%" },
            {
                "data": null,
                "title": "Actions",
                "width": "10%",
                "render": function(data, type, row, meta) {
                    return '<button class="btn btn-primary btn-sm btn-flat" onclick="EditManageComplaintsDetails(' + row.id + ')">&#x270E;</button>' + " " +
                           '<button class="btn btn-danger btn-sm" style="background-color: red; color: white;" onclick="DeleteComplanitStatus(' + row.id + ')">X</button>';
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
        ],
        "drawCallback": function(settings) {
            var api = this.api();
            var dataCount = api.data().count();
            if (dataCount > 0) {
                $('#Submitid').hide();
            } else {
                $('#Submitid').show();
            }
        }
    });
    CustomerDetails();
});


function ManageComplaints() {
    var id = $('#id').val();
    var issueSolvedDate = $('#IssueSolvedDate').val();
    var status = $('#status').val();
    var description = $('#Description').val();

    if (issueSolvedDate === "" || status === "0" || description === "") {
        Swal.fire({
            title: " Error!",
            text: "Please fill in all required fields.",
            icon: "error"
        });
        return false;
    }
    var data = {
        id: id,
        customerid: customeridValue,
        agentid: agentIDValue,
        issueSolvedDate: issueSolvedDate,
        status: status,
        description: description
    };

    var jsonData = JSON.stringify(data);

    $.ajax({
        type: "POST",
        url: "/addcomplaintstatus",
        contentType: "application/json",
        data: jsonData,
        success: function(response, status) {
            if (response == "Status added successfully") {
            $('#Submitid').hide();
           Swal.fire({
                title: "Success!",
                text:"Status added successfully",
                icon: "success"
            });

                clearAll();
                ManageComplaintsTable.ajax.reload();
            }
            ManageComplaintsTable.ajax.reload();
        },
        error: function(xhr, status, error) {
        }
    });
}


function EditManageComplaintsDetails(id) {
   $('#Submitid').show();
    $('#id').val(id);
    $.ajax({
        type: "GET",
        url: "/getManageComplaintsByID",
        contentType: "application/json",
        data: { id: id },
        success: function(response) {
            $("#status").val(response.status);
            $("#Description").val(response.description);
            var IssueSolvedDate = new Date(response.issueSolvedDate);
            var formattedDate = IssueSolvedDate.toISOString().split('T')[0];
            $("#IssueSolvedDate").val(formattedDate);
        },
        error: function(xhr, status, error) {

        }
    });
}


function DeleteComplanitStatus(id) {
    $.ajax({
        type: "DELETE",
        url: "/deleteComplanitStatusByID",
        data: { id: id },
        success: function(serverMessageDto) {
            if (serverMessageDto.successFlag == 1) {
        Swal.fire({
            title: "Deleted!",
            text: "Deleted Sucessfully.",
            icon: "success"
        });
       ManageComplaintsTable.ajax.reload();
            } else {
               Swal.fire({
                   title: "Oops!",
                   text: "Unable to Delete.",
                   icon: "error"
               });
               ManageComplaintsTable.ajax.reload();
            }

        },
        error: function(xhr, status, error) {
            console.error(xhr.responseText);
        }
    });
}

function clearAll() {
  $('#id').val(0);
  $('#IssueSolvedDate').val("");
  $('#status').val(0);
  $('#Description').val("");
}

function CustomerDetails() {
    var id = customeridValue;

    $.ajax({
        type: "GET",
        url: "/getcustomertDetailsbyid",
        contentType: "application/json",
        data: { id: id },
        success: function(response) {
            var TicketId = "AB#2024" + response.id + "";
            $("#ticketIdDisplay").text(TicketId);
            $("#fullNameDisplay").text(response.firstname + " " + response.lastname);
            $("#emailDisplay").text(response.email);
            $("#phoneNumberDisplay").text(response.phonenumber);
            $("#issueDescriptionDisplay").text(response.complaint_notes);
        },
        error: function(xhr, status, error) {
            console.error("Error fetching customer details: ", error);
        }
    });
}
function Logout(){
   window.location.href = '/index';
}