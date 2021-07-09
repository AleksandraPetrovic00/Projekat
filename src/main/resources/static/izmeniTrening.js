$(document).ready(function (event) {

    let newUpit = JSON.parse(window.localStorage.getItem("korisnik"));
    $.ajax({
        type: "POST",
        url: "http://localhost:8090/pristup",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newUpit),
        success: function (response) {
            if (response.uloga === "TRENER") {
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
$("#treningSubmit").on("click", function(event){
    event.preventDefault();

    let id=window.localStorage.getItem("treningId");
    let naziv = $("#naziv").val();
    let opis = $("#opis").val();
    let tipTreninga = $("#tipTreninga").val();
    let trajanje = $("#trajanje").val();

    let updateTrening = {
        naziv,
        opis,
        tipTreninga,
        trajanje
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8090/api/treninzi/update/"+id,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(updateTrening),
        success: function(response){
            console.log(response);
            alert("Trening " +response.id + " je izmenjen");
            window.location.href="trenerTreninzi.html";
        },
        error:function(e){
            alert("Greska prilikom izmene");
        }


    });


});