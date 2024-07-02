

var TicketTable;
$(document).ready(function() {
    $('#id').val(0);
    TicketTable = $('#TicketTable').DataTable({
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
            { "data": "id", "title": "Ticket Id", "width": "10%", "render": function(data, type, row) { return "AB#" + data + "2024"; } },
            {
                "data": null,
                "title": "Full Name",
                "width": "20%",
                "render": function(data, type, row, meta) {
                    return row.firstname + ' ' + row.lastname;
                }
            },
            { "data": "email", "title": "Email", "width": "15%" },
            { "data": "phonenumber", "title": "Phone Number", "width": "10%" },
            { "data": "complaint_notes", "title": "Issue Description", "width": "20%" },
            {
                "data": null,
                "title": "Actions",
                "width": "10%",
                "render": function(data, type, row, meta) {
                    return '<button class="btn btn-primary btn-sm btn-flat" onclick="SolveTicketIssue(' + row.id + ')">Solve Ticket Issue</button>';
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


function SolveTicketIssue(customerid, userID) {
   var userID = useridValue;
   window.location.href = 'PopManageTickets?customerid=' + customerid + '&userID=' + userID;
}

