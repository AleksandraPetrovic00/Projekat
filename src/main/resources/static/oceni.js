$(document).ready(function (event) {

    let newUpit = JSON.parse(window.localStorage.getItem("korisnik"));
    $.ajax({
        type: "POST",
        url: "http://localhost:8090/pristup",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newUpit),
        success: function (response) {
            if (response.uloga === "CLAN") {
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
$(document).ready(function() {
    $("#ocenaSubmit").on("click", function(event){
        event.preventDefault();
        window.location.href = "odradjeniNeocenjeni.html";

        console.log(2);
    
        let ocena = $("#ocena").val();
        let idKorisnik = window.localStorage.getItem("Id");
        let idTermin = window.localStorage.getItem("oceniTerminId");
    
        let newOcena = {
            ocena
        }
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/api/ocene/oceniTrening/"+idKorisnik+"/"+idTermin,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(newOcena),
            success: function(response){
                console.log(response);
    
                alert("Upesno ste ocenili trening");
    
            },
            error:function(e){
                alert("Greska prilikom dodavanja");

            }
    
        });
    
    
    });
    
});
