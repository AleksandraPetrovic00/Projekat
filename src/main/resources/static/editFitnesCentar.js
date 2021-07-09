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
$("#centarSubmit").on("click", function(event){
    event.preventDefault();

    let id=window.localStorage.getItem("centarId");
    let naziv = $("#naziv").val();
    let adresa = $("#adresa").val();
    let brojTelefonaCentrale = $("#brojTelefona").val();
    let email = $("#email").val();


    let updateFitnesCentar = {
        naziv,
        adresa,
        brojTelefonaCentrale,
        email
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8090/api/fitnesCentri/"+id,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(updateFitnesCentar),
        success: function(response){
            console.log(response);
            alert("Fitnes centar " +response.id + " je izmenjen");
        },
        error:function(e){
            alert("Greska prilikom izmene");
        }


    });


});