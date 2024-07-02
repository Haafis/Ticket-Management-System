$(document).ready(function() {
    CustomerDetails();
});

function CustomerDetails() {
    var id = Ticketid;

    if (!id) {
        console.error("Ticketid is not defined");
        return;
    }

    $.ajax({
        type: "GET",
        url: "/getCustomerTicketStatusdetails",
        contentType: "application/json",
        data: { id: id },
        success: function(response) {
            if (response) {
                var TicketId = "AB#2024" + response.ticketid + "";
                $("#ticketIdDisplay").text(TicketId || 'N/A').css('color', 'blue');
                $("#fullNameDisplay").text(response.customername || 'N/A').css('color', 'blue');
                $("#ComplaintDisplay").text(response.complaint || 'N/A').css('color', 'blue');
                $("#SolvedDateDisplay").text(response.solveddate || 'N/A').css('color', 'blue');
                $("#AgentNameDisplay").text(response.agentname || 'N/A').css('color', 'blue');
                $("#IssueDisplay").text(response.issue || 'N/A').css('color', 'blue');
                $("#StatusDisplay").text(response.status || 'N/A').css('color', 'blue');
            } else {
                console.error("Empty response received from the server");
            }
        },
        error: function(xhr, status, error) {
            console.error("Error fetching customer details: ", error);
        }
    });
}
function Logout(){
   window.location.href = '/index';
}

function ReregisterComplaint(){
       window.location.href = '/PopCustomer?userid=99';
}