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
