
function  LogOut(){
   window.location.href = '/index';
}

    function Login() {
        window.location.href = 'login';
    }

    function RegisterComplaint() {
        window.location.href = 'PopCustomer';
    }

    function openSearchModal() {
        document.getElementById('searchModal').style.display = 'flex';
    }

    function closeSearchModal() {
        document.getElementById('searchModal').style.display = 'none';
    }



function searchTicket() {
    var ticketId = document.getElementById('ticket-id').value;
    if (ticketId) {
        var Ticketid = ticketId.substring(3, 4);
        alert('Extracted digit: ' + Ticketid);


        alert('Searching for Ticket ID: ' + ticketId);
        window.open('PopCustomerTicketStatus');
          window.location.href = '/PopCustomerTicketStatus?Ticketid=' + Ticketid;
    } else {
        Swal.fire({
            icon: "warning",
            title: "Oops...",
            text: "Please enter a Ticket ID!",
        });
    }
}
function openTickets(){
var userid = userID;
    window.location.href = '/PopTickets?userid=' + userid;
}
function openCustomer(){
var userid = userID;
//    window.location.href = '/PopCustomer?userid=' + userid;
     window.location.href = '/PopCustomer';
}