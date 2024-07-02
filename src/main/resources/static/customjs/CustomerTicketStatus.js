// var CustomerTicketStatusTable;
//    $(document).ready(function() {
//        CustomerTicketStatusTable = $('#CustomerTicketStatusTable').DataTable({
//            "processing": true,
//            "serverSide": true,
//            "ajax": {
//                "url": "/getCustomerTicketStatusdetails",
//                "type": "POST",
//                "contentType": "application/json",
//                "data": function(d) {
//                    return JSON.stringify({ id: Ticketid });
//                },
//                "dataSrc": "data"
//            },
//            "columns": [
//                { "data": null, "title": "Serial Number", "width": "5%" },
//                { "data": "Ticketid", "title": "Ticket Id", "width": "10%" },
//                { "data": "CustomerName", "title": "Customer Name", "width": "10%" },
//                { "data": "Complaint", "title": "Complaint", "width": "15%" },
//                { "data": "Solveddate", "title": "Solved Date", "width": "10%" },
//                { "data": "AgentName", "title": "Agent Name", "width": "10%" },
//                { "data": "Issue", "title": "Issue", "width": "15%" },
//                { "data": "Status", "title": "Status", "width": "10%" },
//                {
//                    "data": null,
//                    "title": "Actions",
//                    "width": "10%",
//                    "render": function(data, type, row, meta) {
//                        return '<button class="btn btn-primary btn-sm btn-flat" onclick="EditCustomerDetails(' + row.Ticketid + ')">&#x270E;</button>' + " " +
//                               '<button class="btn btn-danger btn-sm" style="background-color: red; color: white;" onclick="DeleteCustomerDetails(' + row.Ticketid + ')">X</button>';
//                    }
//                }
//            ],
//            "columnDefs": [
//                {
//                    "targets": 0,
//                    "render": function(data, type, row, meta) {
//                        return meta.row + meta.settings._iDisplayStart + 1;
//                    }
//                }
//            ]
//        });
//    });