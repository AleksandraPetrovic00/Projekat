    $("#signIn").on("click", function(event){
    event.preventDefault();

    let korisnickoIme = $("#username").val();
    let lozinka = $("#password").val();


    let newKorisnik = {
        korisnickoIme,
        lozinka
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8090/api/korisnici/prijava",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newKorisnik),
        success: function(response){
            console.log(response);

            alert("Korisnik" +response.id + "je ulogovan");
            //window.location.href = "home.html";
        },
        error:function(e){
            alert("Greska prilikom registracije");
        }


    });


});