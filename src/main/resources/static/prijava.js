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
            var korisnik = {
                "id": response.id,
                "uloga": response.uloga
            }
            localStorage.setItem("korisnik", JSON.stringify(korisnik));

            window.localStorage.setItem("Id", response.id);
            window.localStorage.setItem("Uloga", response.uloga);
            if(localStorage.getItem("Uloga")=="ADMINISTRATOR"){
                window.location.href = "admin.html"
                alert("Korisnik " +response.id + " je ulogovan");
            }else if(localStorage.getItem("Uloga")=="TRENER"){
                window.location.href = "trener.html";
                alert("Korisnik " +response.id + " je ulogovan");
            }else if(localStorage.getItem("Uloga")=="CLAN"){
                window.location.href = "clan.html";
                alert("Korisnik " +response.id + " je ulogovan");
            }else{
                window.location.href="login.html";
            }
        },
        error:function(e){
            alert("Greska prilikom prijave");
        }


    });


});
