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