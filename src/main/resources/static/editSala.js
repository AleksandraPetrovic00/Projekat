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
$("#salaSubmit").on("click", function(event){
    event.preventDefault();

    let id=window.localStorage.getItem("salaId");
    let kapacitet = $("#kapacitet").val();
    let oznakaSale = $("#oznakaSale").val();

    let updateSala = {
        kapacitet,
        oznakaSale
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8090/api/sale/update/"+id,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(updateSala),
        success: function(response){
            console.log(response);
            alert("Sala " +response.id + " je izmenjena");
        },
        error:function(e){
            alert("Greska prilikom izmene");
        }


    });


});