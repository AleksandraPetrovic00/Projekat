$(document).ready(function (event) {

    let newUpit = JSON.parse(window.localStorage.getItem("korisnik"));
    $.ajax({
        type: "POST",
        url: "http://localhost:8090/pristup",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newUpit),
        success: function (response) {
            if (response.uloga === "ADMINISTRATOR") {
            }
            else {
                alert("Nemate pristup ovoj stranici!");
                window.location.href = "login.html";
            }
        },
        error: function (response) {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "login.html";
        }
    });

});

$(document).on("submit", "#fcentar",function(event){
    event.preventDefault();

    let naziv = $("#naziv").val();
    let adresa = $("#adresa").val();
    let brojTelefonaCentrale = $("#brojTelefona").val();
    let email = $("#email").val();

    let newCentar = {
        naziv,
        adresa,
        brojTelefonaCentrale,
        email
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8090/api/fitnesCentri/dodaj",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newCentar),
        success: function(response){
            console.log(response);

            alert("Centar" +response.id + "je dodat");
           window.location.href = "fitnesCentri.html";

        },
        error:function(e){
            alert("Greska prilikom dodavanja");
        }

    });


});