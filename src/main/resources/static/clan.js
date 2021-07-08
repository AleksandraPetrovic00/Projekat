$("#singOut").on("click", function(event){
    event.preventDefault();

    window.localStorage.removeItem("Id");
    window.localStorage.removeItem("Uloga");

    window.location.href="login.html";

})