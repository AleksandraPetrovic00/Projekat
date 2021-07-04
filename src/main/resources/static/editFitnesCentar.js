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