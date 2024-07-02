

function openLogin() {
    window.location.href = '/login';
}

function showSignupForm() {
    document.getElementById("loginForm").style.display = "none";
    document.getElementById("signupForm").style.display = "block";
    return false;
}

function showLoginForm() {
    document.getElementById("signupForm").style.display = "none";
    document.getElementById("loginForm").style.display = "block";
    return false;
}

function SignUp(event) {
    event.preventDefault();

    var username = $('#signupUsername').val();
    var password = $('#signupPassword').val();
    var role = $('#userRole').val();

    if (!username || !password || role === '0') {
        Swal.fire({
            title: "Error!",
            text: "All fields are required.",
            icon: "error"
        });
        return false;
    }

    var SignupData = {
        username: username,
        password: password,
        role: role
    };

    var jsonData = JSON.stringify(SignupData);

    $.ajax({
        type: "POST",
        url: "/SignUp",
        contentType: "application/json",
        data: jsonData,
        success: function(response) {
            Swal.fire({
                icon: "success",
                title: "Success",
                text: "Signup successful!",
            }).then(() => {
                window.location.href = '/login';
            });
        },
        error: function(xhr, status, error) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: xhr.responseText,
            });
        }
    });

    return false;
}

function Login(event) {
    event.preventDefault();

    var loginUsername = $('#loginUsername').val();
    var loginPassword = $('#loginPassword').val();
    var loginRole = $('#loginRole').val();

    if (!loginUsername || !loginPassword || loginRole === '0') {
        Swal.fire({
            title: "Error!",
            text: "All fields are required.",
            icon: "error"
        });
        return false;
    }

    var loginData = {
        loginUsername: loginUsername,
        loginPassword: loginPassword,
        loginRole: loginRole
    };

    $.ajax({
        type: "POST",
        url: "/login",
        contentType: "application/json",
        data: JSON.stringify(loginData),
        success: function(response) {
            var role = response.role;
            var userid = response.userid;
            if (loginRole === role) {
                if (role === "1") {
                    window.location.href = '/PopDashboard?userid=' + userid;
                } else if (role === "2") {
                    window.location.href = '/PopTickets?userid=' + userid;
                } else {
                    Swal.fire({
                        icon: "warning",
                        title: "Oops...",
                        text: "User Role not Found.",
                    });
                }
            }
        },
        error: function(xhr, status, error) {
            Swal.fire({
                icon: "error",
                title: "Error...",
                text: xhr.responseText,
            });
        }
    });

    return false;
}
